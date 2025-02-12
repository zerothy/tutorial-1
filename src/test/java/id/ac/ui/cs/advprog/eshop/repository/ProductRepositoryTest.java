package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        Product savedProduct = productRepository.create(product);
        String productId = savedProduct.getProductId();

        Product updatedProduct = new Product();
        updatedProduct.setProductId(productId);
        updatedProduct.setProductName("Sampo Cap Bambang Baru");
        updatedProduct.setProductQuantity(200);
        productRepository.update(updatedProduct);

        Product retrievedProduct = productRepository.get(productId);
        assertNotNull(retrievedProduct);
        assertEquals(updatedProduct.getProductName(), retrievedProduct.getProductName());
        assertEquals(updatedProduct.getProductQuantity(), retrievedProduct.getProductQuantity());
    }

    @Test
    void testEditNonExistentProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("non-existent-id");
        updatedProduct.setProductName("Sampo Cap Bambang Baru");
        updatedProduct.setProductQuantity(200);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productRepository.update(updatedProduct);
        });
        assertEquals("Product not found with ID: non-existent-id", exception.getMessage());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        Product savedProduct = productRepository.create(product);
        String productId = savedProduct.getProductId();

        boolean isDeleted = productRepository.delete(productId);
        assertTrue(isDeleted);

        Product deletedProduct = productRepository.get(productId);
        assertNull(deletedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteNonExistentProduct() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productRepository.delete("non-existent-id");
        });
        assertEquals("Product not found with ID: non-existent-id", exception.getMessage());
    }
}