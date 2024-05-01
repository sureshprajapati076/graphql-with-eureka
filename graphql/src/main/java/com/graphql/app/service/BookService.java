package com.graphql.app.service;

import com.graphql.app.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private JdbcClient jdbcClient;

    public List<Book> findAll(){
        return jdbcClient.sql("select * from book").query(Book.class).list();
    }

    public Optional<Book> findById(Long id){
        return jdbcClient.sql("select * from book where id=:id").param("id",id).query(Book.class).optional();
    }
}
