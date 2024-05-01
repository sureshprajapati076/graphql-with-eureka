package com.graphql.sp04440.controller;


import com.graphql.sp04440.model.Book;
import com.graphql.sp04440.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/getById", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book get(@RequestBody String graphQuery){
        return bookService.findBook(graphQuery).block();
    }

    @PostMapping(value = "/addBook", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book addBook(@RequestBody String graphQuery){
        return bookService.addBook(graphQuery).block();
    }

    @PostMapping(value = "/updateBook", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book update(@RequestBody String graphQuery){
        return bookService.updateBook(graphQuery).block();
    }

    @PostMapping(value = "/getAllBooks", consumes =  MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getBooks(@RequestBody String graphQuery){
        return bookService.findBooks(graphQuery).block();
    }

    @PostMapping(value = "/deleteBook", consumes =  MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book delete(@RequestBody String graphQuery){
        return bookService.deleteBook(graphQuery).block();
    }

}
