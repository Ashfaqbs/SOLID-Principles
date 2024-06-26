package com.ashfaq.solidexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot3xJ21SolidApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot3xJ21SolidApplication.class, args);
	}
	
//	Single Responsibility Principle (SRP):
//
//		Each class has a single responsibility. For example, ProductController handles HTTP requests, ProductServiceImpl handles business logic, and ProductRepository handles data persistence.
//		Open/Closed Principle (OCP):
//
//		The system is open for extension but closed for modification. For example, adding new business logic can be done by extending the service layer without modifying existing classes.
//		Liskov Substitution Principle (LSP):
//
//		Subtypes (ProductServiceImpl) can replace their base types (ProductService) without affecting the program correctness.
//		Interface Segregation Principle (ISP):
//
//		Interfaces are specific to their purposes. For example, ProductService defines only the methods relevant to product operations.
//		Dependency Inversion Principle (DIP):
//
//		High-level modules (controllers) and low-level modules (repositories) depend on abstractions (ProductService). This decouples the implementation and promotes flexibility.

}
