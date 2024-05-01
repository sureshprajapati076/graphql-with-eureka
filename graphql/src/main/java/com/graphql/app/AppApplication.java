package com.graphql.app;

import com.graphql.app.model.Book;
import com.graphql.app.model.Review;
import com.graphql.app.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BookRepository bookRepository) {
		return args -> {

			Book reactiveSpring = new Book("Reactive Spring", 484, "Josh Long");
			Review review = new Review("Great book", "I really enjoyed this book");
			// bidirectional relationship
			review.setBook(reactiveSpring);
			reactiveSpring.setReviews(List.of(review));
			bookRepository.save(reactiveSpring);
			bookRepository.save(new Book("Spring Boot Up & Running", 328, "Mark Heckler"));
			bookRepository.save(new Book("Hacking with Spring Boot 2.3", 392, "Greg Turnquist"));
		};
	}

}
