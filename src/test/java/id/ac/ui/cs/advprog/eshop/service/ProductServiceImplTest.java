package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    @Test
    void testCreateProduct_ValidProduct_ReturnsCreatedProduct() {
        when(productRepository.create(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testCreateProduct_NullName_ThrowsException() {
        product.setProductName(null);

        assertThrows(IllegalArgumentException.class, () -> productService.create(product));
        verify(productRepository, never()).create(any());
    }

    @Test
    void testCreateProduct_EmptyName_ThrowsException() {
        product.setProductName("");

        assertThrows(IllegalArgumentException.class, () -> productService.create(product));
        verify(productRepository, never()).create(any());
    }

    @Test
    void testCreateProduct_NegativeQuantity_ThrowsException() {
        product.setProductQuantity(-1);

        assertThrows(IllegalArgumentException.class, () -> productService.create(product));
        verify(productRepository, never()).create(any());
    }

    @Test
    void testFindAllProducts_ReturnsAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertEquals(1, result.size());
        assertEquals(product, result.getFirst());
    }

    @Test
    void testGetProduct_ExistingId_ReturnsProduct() {
        when(productRepository.get(product.getProductId())).thenReturn(product);

        Product foundProduct = productService.get(product.getProductId());

        assertEquals(product, foundProduct);
    }

    @Test
    void testGetProduct_NonExistingId_ThrowsException() {
        String nonExistingId = "invalid-id";
        when(productRepository.get(nonExistingId)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> productService.get(nonExistingId));
    }

    @Test
    void testUpdateProduct_ValidProduct_UpdatesSuccessfully() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId(product.getProductId());
        updatedProduct.setProductName("Daniel Liman");
        updatedProduct.setProductQuantity(1);

        when(productRepository.get(product.getProductId())).thenReturn(product);

        productService.update(updatedProduct);

        verify(productRepository, times(1)).update(updatedProduct);
    }

    @Test
    void testUpdateProduct_NullId_ThrowsException() {
        product.setProductId(null);

        assertThrows(IllegalArgumentException.class, () -> productService.update(product));
        verify(productRepository, never()).update(any());
    }

    @Test
    void testUpdateProduct_EmptyId_ThrowsException() {
        product.setProductId("");

        assertThrows(IllegalArgumentException.class, () -> productService.update(product));
        verify(productRepository, never()).update(any());
    }

    @Test
    void testUpdateProduct_NullName_ThrowsException() {
        product.setProductName(null);

        assertThrows(IllegalArgumentException.class, () -> productService.update(product));
        verify(productRepository, never()).update(any());
    }

    @Test
    void testUpdateProduct_EmptyName_ThrowsException() {
        product.setProductName("");

        assertThrows(IllegalArgumentException.class, () -> productService.update(product));
        verify(productRepository, never()).update(any());
    }

    @Test
    void testUpdateProduct_NegativeQuantity_ThrowsException() {
        product.setProductQuantity(-1);

        assertThrows(IllegalArgumentException.class, () -> productService.update(product));
        verify(productRepository, never()).update(any());
    }

    @Test
    void testUpdateProduct_NonExistingProduct_ThrowsException() {
        when(productRepository.get(product.getProductId())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> productService.update(product));
        verify(productRepository, never()).update(any());
    }

    @Test
    void testDeleteProduct_ValidId_DeletesSuccessfully() {
        when(productRepository.delete(product.getProductId())).thenReturn(true);

        boolean result = productService.delete(product.getProductId());

        assertTrue(result);
        verify(productRepository, times(1)).delete(product.getProductId());
    }

    @Test
    void testDeleteProduct_EmptyId_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> productService.delete(""));
        verify(productRepository, never()).delete(any());
    }

    @Test
    void testDeleteProduct_NullId_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> productService.delete(null));
        verify(productRepository, never()).delete(any());
    }

    @Test
    void testDeleteProduct_NonExistingId_ThrowsException() {
        String nonExistingId = "invalid-id";
        when(productRepository.delete(nonExistingId)).thenThrow(
                new IllegalArgumentException("Product not found")
        );

        assertThrows(IllegalArgumentException.class, () -> productService.delete(nonExistingId));
    }
}