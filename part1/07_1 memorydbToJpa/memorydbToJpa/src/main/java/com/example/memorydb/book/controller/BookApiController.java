package com.example.memorydb.book.controller;

import com.example.memorydb.book.db.entity.BookEntity;
import com.example.memorydb.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    @PostMapping("")
    public BookEntity create(
            @RequestBody BookEntity book
    ){
        return bookService.create(book);
    }

    @GetMapping("/all")
    public List<BookEntity> findAll(){
        return bookService.findAll();
    }

    @DeleteMapping("/id/{id}")
    public void delete(
        @PathVariable Long id
    ){
        //bookService.delete(id);
    }

    @GetMapping("/id/{id}")
    public BookEntity findById(
            @PathVariable Long id
    ){
        var response = bookService.findOne(id);
        return response.get();
    }
}
