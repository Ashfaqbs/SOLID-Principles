package com.ashfaq.solidexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashfaq.solidexample.model.Product;
import com.ashfaq.solidexample.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

 private final ProductRepository productRepository;

 @Autowired
 public ProductServiceImpl(ProductRepository productRepository) {
     this.productRepository = productRepository;
 }

 @Override
 public Product saveProduct(Product product) {
     return productRepository.save(product);
 }

 @Override
 public List<Product> getAllProducts() {
     return productRepository.findAll();
 }

 @Override
 public Optional<Product> getProductById(Long id) {
     return productRepository.findById(id);
 }

 @Override
 public Product updateProduct(Long id, Product product) {
     if (productRepository.existsById(id)) {
         product.setId(id);
         return productRepository.save(product);
     }
     return null;
 }

 @Override
 public void deleteProduct(Long id) {
     productRepository.deleteById(id);
 }
}

