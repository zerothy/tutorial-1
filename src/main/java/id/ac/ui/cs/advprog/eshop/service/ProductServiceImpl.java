package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        if (product.getProductQuantity() < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative.");
        }
        return productRepository.create(product);
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product get(String id) {
        Product product = productRepository.get(id);
        if (product == null) {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
        return product;
    }

    @Override
    public void update(Product product) {
        if (product.getProductId() == null || product.getProductId().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty.");
        }
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        if (product.getProductQuantity() < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative.");
        }
        Product existingProduct = productRepository.get(product.getProductId());
        if (existingProduct == null) {
            throw new IllegalArgumentException("Product not found with ID: " + product.getProductId());
        }
        productRepository.update(product);
    }

    @Override
    public boolean delete(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty");
        }
        return productRepository.delete(id);
    }
}