package com.gateway.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}


	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p->p
						.method("GET")
						.and()
						.path("/service/a/get")
						.uri("lb://SERVICE-A")
				      )
				.route(p->p
						.method("POST")
						.and()
						.path("/service/a/post")
						.uri("lb://SERVICE-A")
				)
				.route(p->p
						.method("GET")
						.and()
						.path("/service/b/get")
						.uri("lb://SERVICE-B")
				)
				.route(p->p
						.method("GET")
						.and()
						.path("/service/b/reset")
						.uri("lb://SERVICE-B")
				)
				.route(p->p
						.method("POST")
						.and()
						.path("/service/b/post")
						.uri("lb://SERVICE-B")
				)
				.route(p->p
						.method("POST")
						.and()
						.path("/addBook","/getAllBooks","/getById","/updateBook","/deleteBook")
						.uri("lb://SERVICE-GRAPHQL-CLIENT")
				)
				.build();
	}

}
