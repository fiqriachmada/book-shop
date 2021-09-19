package com.enigma.demo.service;

import com.enigma.demo.entity.PurchaseDetail;
import com.enigma.demo.repository.PurchaseDetailRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService{

    @Autowired
    PurchaseDetailRepositary purchaseDetailRepository;

    @Override
    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }
}
