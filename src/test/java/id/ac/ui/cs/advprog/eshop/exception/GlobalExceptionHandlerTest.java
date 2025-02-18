package id.ac.ui.cs.advprog.eshop.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.http.HttpHeaders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(TestController.class)
@ContextConfiguration(classes = {GlobalExceptionHandler.class, TestController.class})
public class GlobalExceptionHandlerTest {
    final String error = "errorMessage";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void testHandleHttpRequestMethodNotSupported() throws Exception {
        // Test through MockMvc
        mockMvc.perform(post("/test-get"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"))
                .andExpect(flash().attribute(error, "Invalid HTTP method for this endpoint."));

        // Direct method call for complete coverage
        HttpRequestMethodNotSupportedException exception =
                new HttpRequestMethodNotSupportedException("POST", List.of(new String[]{"GET"}));
        String result = globalExceptionHandler.handleMethodNotSupported(
                exception,
                new RedirectAttributesModelMap()
        );
        assertEquals("redirect:/product/list", result);
    }

    @Test
    void testHandleNoHandlerFoundException() throws Exception {
        // Direct method call since MockMvc setup for 404 is complex
        MockHttpServletRequest request = new MockHttpServletRequest();
        NoHandlerFoundException exception = new NoHandlerFoundException(
                "GET",
                "/non-existent",
                new HttpHeaders()
        );

        String result = globalExceptionHandler.handleNotFound(
                exception,
                new RedirectAttributesModelMap()
        );
        assertEquals("redirect:/product/list", result);
    }

    @Test
    void testHandleGenericException() throws Exception {
        // Test through MockMvc
        mockMvc.perform(get("/throw-exception"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"))
                .andExpect(flash().attribute(error, "An unexpected error occurred: Test exception"));

        // Direct method call for complete coverage
        Exception exception = new RuntimeException("Test generic exception");
        String result = globalExceptionHandler.handleGenericException(
                exception,
                new RedirectAttributesModelMap()
        );
        assertEquals("redirect:/error", result);
    }
}

@RestController
class TestController {
    @GetMapping("/test-get")
    public String testGet() {
        return "OK";
    }

    @GetMapping("/throw-exception")
    public String throwException() {
        throw new RuntimeException("Test exception");
    }
}