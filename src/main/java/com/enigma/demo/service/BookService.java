package com.enigma.demo.service;

import com.enigma.demo.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    public Book addBook(Book book);

    public Book getBookByid(String id);

    public List<Book> getAllBook();

    public Book updateBook(Book book);

    public void deleteBookById(String id);

    public Page<Book> getBookPerPage(Pageable pageable);

    public List<Book> searchBookTitle(String title);

}
