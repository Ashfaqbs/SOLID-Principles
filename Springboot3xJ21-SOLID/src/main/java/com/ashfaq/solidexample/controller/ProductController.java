package com.ashfaq.solidexample.controller;

import java.util.List;
import java.util.Optional;

//src/main/java/com/example/productapp/controller/ProductController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashfaq.solidexample.model.Product;
import com.ashfaq.solidexample.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

 private final ProductService productService;

 @Autowired
 public ProductController(ProductService productService) {
     this.productService = productService;
 }

 @PostMapping
 public ResponseEntity<Product> createProduct(@RequestBody Product product) {
     Product savedProduct = productService.saveProduct(product);
     return ResponseEntity.ok(savedProduct);
 }

 @GetMapping
 public ResponseEntity<List<Product>> getAllProducts() {
     List<Product> products = productService.getAllProducts();
     return ResponseEntity.ok(products);
 }

 @GetMapping("/{id}")
 public ResponseEntity<Product> getProductById(@PathVariable Long id) {
     Optional<Product> product = productService.getProductById(id);
     return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
 }

 @PutMapping("/{id}")
 public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
     Product updatedProduct = productService.updateProduct(id, product);
     if (updatedProduct != null) {
         return ResponseEntity.ok(updatedProduct);
     } else {
         return ResponseEntity.notFound().build();
     }
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
     productService.deleteProduct(id);
     return ResponseEntity.noContent().build();
 }
}
