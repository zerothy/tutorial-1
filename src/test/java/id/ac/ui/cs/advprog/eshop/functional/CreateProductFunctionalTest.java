package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_isSuccessful(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        nameInput.sendKeys("Cap Bangao");
        quantityInput.sendKeys("1212");

        submitButton.click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.endsWith("/product/list"));

        String pageContent = driver.getPageSource();
        assertTrue(pageContent.contains("Cap Bangao"));
        assertTrue(pageContent.contains("1212"));
    }

    @Test
    void createProduct_withEmptyName_showsError(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        quantityInput.sendKeys("3434");

        submitButton.click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.endsWith("/product/list"));

        String pageContent = driver.getPageSource();
        assertTrue(pageContent.contains("Error creating product"));
    }

    @Test
    void createProduct_withInvalidQuantity_showsError(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        nameInput.sendKeys("Gacor gasih");
        quantityInput.sendKeys("-0101");

        submitButton.click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.endsWith("/product/list"));

        String pageContent = driver.getPageSource();
        assertTrue(pageContent.contains("Error creating product"));
    }
}