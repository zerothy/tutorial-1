package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.Iterator;

public interface ProductRepository {
    Product create(Product product);
    Iterator<Product> findAll();
    void update(Product product);
    Product get(String productId);
    boolean delete(String productId);
}
