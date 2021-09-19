package com.enigma.demo.repository;

import com.enigma.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findBooksByTitleContaining(String title);
}
