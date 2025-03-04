package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    final String success = "successMessage";
    final String error = "errorMessage";

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        try {
            service.create(product);
            redirectAttributes.addFlashAttribute(success, "Product created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(error, "Error creating product: " + e.getMessage());
        }
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        try {
            List<Product> allProducts = service.findAll();
            model.addAttribute("products", allProducts);
        } catch (Exception e) {
            model.addAttribute(error, "Error retrieving product list: " + e.getMessage());
        }
        return "productList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(Model model, @PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            Product product = service.get(id);
            if (product == null) {
                redirectAttributes.addFlashAttribute(error, "Product not found.");
                return "redirect:/product/list";
            }
            model.addAttribute("product", product);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(error, "Error retrieving product: " + e.getMessage());
            return "redirect:/product/list";
        }
        return "editProduct";
    }

    @PutMapping("/edit/{id}")
    public String editProductPost(@ModelAttribute Product product, @PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            product.setProductId(id);
            service.update(product);
            redirectAttributes.addFlashAttribute(success, "Product updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(error, "Error updating product: " + e.getMessage());
        }
        return "redirect:/product/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductPost(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            boolean isDeleted = service.delete(id);
            if (!isDeleted) {
                redirectAttributes.addFlashAttribute(error, "Product not found.");
            } else {
                redirectAttributes.addFlashAttribute(success, "Product deleted successfully!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(error, "Error deleting product: " + e.getMessage());
        }
        return "redirect:/product/list";
    }

    @GetMapping("/error")
    public String errorPage(Model model) {
        return "error";
    }
}