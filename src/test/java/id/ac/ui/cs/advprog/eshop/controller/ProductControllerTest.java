package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);

        verify(model).addAttribute(eq("product"), any(Product.class));
        assertEquals("createProduct", viewName);
    }

    @Test
    void testCreateProductPostSuccess() {
        when(productService.create(any(Product.class))).thenReturn(product);

        String viewName = productController.createProductPost(product, redirectAttributes);

        verify(productService).create(product);
        verify(redirectAttributes).addFlashAttribute("successMessage", "Product created successfully!");
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testCreateProductPostError() {
        when(productService.create(any(Product.class))).thenThrow(new RuntimeException("Creation failed"));

        String viewName = productController.createProductPost(product, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("errorMessage", "Error creating product: Creation failed");
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testProductListPageSuccess() {
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productService.findAll()).thenReturn(products);

        String viewName = productController.productListPage(model);

        verify(model).addAttribute("products", products);
        assertEquals("productList", viewName);
    }

    @Test
    void testProductListPageError() {
        when(productService.findAll()).thenThrow(new RuntimeException("Retrieval failed"));

        String viewName = productController.productListPage(model);

        verify(model).addAttribute("errorMessage", "Error retrieving product list: Retrieval failed");
        assertEquals("productList", viewName);
    }

    @Test
    void testEditProductPageSuccess() {
        when(productService.get(anyString())).thenReturn(product);

        String viewName = productController.editProductPage(model, "testId", redirectAttributes);

        verify(model).addAttribute("product", product);
        assertEquals("editProduct", viewName);
    }

    @Test
    void testEditProductPageNotFound() {
        when(productService.get(anyString())).thenReturn(null);

        String viewName = productController.editProductPage(model, "testId", redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("errorMessage", "Product not found.");
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPageError() {
        when(productService.get(anyString())).thenThrow(new RuntimeException("Retrieval failed"));

        String viewName = productController.editProductPage(model, "testId", redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("errorMessage", "Error retrieving product: Retrieval failed");
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPostSuccess() {
        doNothing().when(productService).update(any(Product.class));

        String viewName = productController.editProductPost(product, "testId", redirectAttributes);

        verify(productService).update(product);
        verify(redirectAttributes).addFlashAttribute("successMessage", "Product updated successfully!");
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPostError() {
        doThrow(new RuntimeException("Update failed")).when(productService).update(any(Product.class));

        String viewName = productController.editProductPost(product, "testId", redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("errorMessage", "Error updating product: Update failed");
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testDeleteProductPostSuccess() {
        when(productService.delete(anyString())).thenReturn(true);

        String viewName = productController.deleteProductPost("testId", redirectAttributes);

        verify(productService).delete("testId");
        verify(redirectAttributes).addFlashAttribute("successMessage", "Product deleted successfully!");
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testDeleteProductPostNotFound() {
        when(productService.delete(anyString())).thenReturn(false);

        String viewName = productController.deleteProductPost("testId", redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("errorMessage", "Product not found.");
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testDeleteProductPostError() {
        when(productService.delete(anyString())).thenThrow(new RuntimeException("Deletion failed"));

        String viewName = productController.deleteProductPost("testId", redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("errorMessage", "Error deleting product: Deletion failed");
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testErrorPage() {
        String viewName = productController.errorPage(model);
        assertEquals("error", viewName);
    }
}