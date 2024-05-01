package com.graphql.app.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String comment;
    @ManyToOne
    private Book book;

    public Review() {
    }

    public Review(String title, String comment) {
        this.title = title;
        this.comment = comment;
    }


}
