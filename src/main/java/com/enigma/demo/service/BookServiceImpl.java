package com.enigma.demo.service;

import com.enigma.demo.constant.ResponseMessage;
import com.enigma.demo.entity.Book;
import com.enigma.demo.exception.DataNotFoundException;
import com.enigma.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookByid(String id) {
        verifyBookById(id);
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Book book) {
        verifyBookById(book.getId());
        return bookRepository.save(book);
    }


    @Override
    public void deleteBookById(String id) {
        verifyBookById(id);
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> getBookPerPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> searchBookTitle(String title) {
        return bookRepository.findBooksByTitleContaining(title);
    }

    private void verifyBookById(String id) {
        if (!bookRepository.findById(id).isPresent()) {
            String message = String.format(ResponseMessage.NOT_FOUND_MESSAGE, "book", id);
            throw new DataNotFoundException(message);
        }
    }
}
