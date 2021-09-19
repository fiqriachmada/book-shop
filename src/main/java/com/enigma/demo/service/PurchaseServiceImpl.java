package com.enigma.demo.service;


import com.enigma.demo.config.KafkaConfig;
import com.enigma.demo.constant.ResponseMessage;
import com.enigma.demo.entity.Book;
import com.enigma.demo.entity.Member;
import com.enigma.demo.entity.Purchase;
import com.enigma.demo.entity.PurchaseDetail;
import com.enigma.demo.exception.DataNotFoundException;
import com.enigma.demo.repository.PurchaseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @Autowired
    BookService bookService;

    @Autowired
    MemberService memberService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;


    @Override
    @Transactional
    public void transaction(Purchase purchase) throws JsonProcessingException {
        Purchase purchase1 = purchaseRepository.save(purchase);

        Member member = memberService.getMemberById(purchase.getMember().getId());
        purchase1.setMember(member);

        for (PurchaseDetail purchaseDetail : purchase.getPurchaseDetails()) {
            purchaseDetail.setPurchase(purchase1);
            Book book = bookService.getBookByid(purchaseDetail.getBook().getId());

            Double grandTotal = 0.0;
            if (book.getStock() == 0) {
                throw new DataNotFoundException("Book is out of stock");
            }
            if (book.getStock() < purchaseDetail.getQuantity()) {
                String message = String.format(ResponseMessage.BAD_REQUEST, book.getStock(), purchaseDetail.getQuantity(), book.getTitle());
                throw new DataNotFoundException(message);
            }

            book.setStock(book.getStock() - purchaseDetail.getQuantity());

            purchaseDetail.setPriceSell((double) (book.getPrice() * purchaseDetail.getQuantity()));
            purchaseDetail.setBook(book);
            purchaseDetailService.savePurchaseDetail(purchaseDetail);

            purchase1.setGrandTotal(grandTotal += purchaseDetail.getPriceSell());

        }
        String response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(purchase1);
        this.kafkaTemplate.send(KafkaConfig.Topic, response);
    }
}
