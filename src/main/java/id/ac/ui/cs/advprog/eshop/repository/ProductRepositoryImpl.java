package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    @Override
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    @Override
    public void update(Product product) {
        for (Product p : productData) {
            if (p.getProductId().equals(product.getProductId())) {
                p.setProductName(product.getProductName());
                p.setProductQuantity(product.getProductQuantity());
                return;
            }
        }
        throw new IllegalArgumentException("Product not found with ID: " + product.getProductId());
    }

    @Override
    public Product get(String productId) {
        for (Product p : productData) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean delete(String productId) {
        for (Product p : productData) {
            if (p.getProductId().equals(productId)) {
                productData.remove(p);
                return true;
            }
        }
        throw new IllegalArgumentException("Product not found with ID: " + productId);
    }
}
