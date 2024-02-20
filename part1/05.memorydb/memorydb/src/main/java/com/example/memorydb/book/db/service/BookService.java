package com.example.memorydb.book.db.service;

import com.example.memorydb.book.db.entity.BookEntity;
import com.example.memorydb.book.db.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor // 아래 코드와 동일함
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // BookRepository.java 가 @Service로 되어있기 때문에 bean으로 등록되어있음
    /*public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }*/

    // Create , Update
    public BookEntity create(BookEntity book){
        return bookRepository.save(book);
    }

    // findAll
    public List<BookEntity> findAll(){
        return bookRepository.findAll();
    }

    // delete
    public void delete(Long id){
        bookRepository.delete(id);
    }

    // findOne
    public Optional<BookEntity> findOne(Long id){
        return bookRepository.findById(id);
    }

}
