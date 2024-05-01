package com.graphql.sp04440.service;

import com.graphql.sp04440.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private DiscoveryClient discoveryClient;

    public Mono<Book> findBook(String graphQuery) {
        var graphQlClient = HttpGraphQlClient.builder(createClient()).build();
        return graphQlClient.document(graphQuery)
                .retrieve("findBook")
                .toEntity(Book.class);
    }

    public Mono<List<Book>> findBooks(String graphQuery) {
        var graphQlClient = HttpGraphQlClient.builder(createClient()).build();
        return graphQlClient.document(graphQuery)
                .retrieve("findAllBooks")
                .toEntityList(Book.class);
    }

    public Mono<Book> updateBook(String graphQuery) {
        var graphQlClient = HttpGraphQlClient.builder(createClient()).build();
        return graphQlClient.document(graphQuery)
                .retrieve("updateBook")
                .toEntity(Book.class);
    }

    public Mono<Book> deleteBook(String graphQuery) {
        var graphQlClient = HttpGraphQlClient.builder(createClient()).build();
        return graphQlClient.document(graphQuery)
                .retrieve("deleteBook")
                .toEntity(Book.class);
    }

    public Mono<Book> addBook(String graphQuery) {
        var graphQlClient = HttpGraphQlClient.builder(createClient()).build();
        return graphQlClient.document(graphQuery)
                .retrieve("createBook")
                .toEntity(Book.class);
    }

    private WebClient createClient() {
        return WebClient.builder()
                .baseUrl(buildUrl("SERVICE-GRAPHQL","/service/api/query"))
                .build();
    }

    private String buildUrl(String serviceName, String uri) {
        var listOfInstances = discoveryClient.getInstances(serviceName);
        return listOfInstances.get(0).getUri().toString()+uri;
    }

}
