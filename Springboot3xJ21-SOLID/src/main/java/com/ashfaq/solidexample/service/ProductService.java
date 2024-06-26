package com.ashfaq.solidexample.service;

import java.util.List;
import java.util.Optional;

import com.ashfaq.solidexample.model.Product;

public interface ProductService {
 Product saveProduct(Product product);
 List<Product> getAllProducts();
 Optional<Product> getProductById(Long id);
 Product updateProduct(Long id, Product product);
 void deleteProduct(Long id);
}

