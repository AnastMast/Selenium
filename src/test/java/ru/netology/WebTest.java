package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChromeTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static class WebTest {
        private WebDriver driver;

        @BeforeAll
        static void setUpAll() {
            System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");

        }

        @BeforeEach
        void setUp() {
            driver = new ChromeDriver();
            driver.get("http://localhost:9999");
        }

        @AfterEach
        void tearDown() {
            driver.quit();
            driver = null;
        }

        @Test
        void Test1() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);

            driver.get("http://localhost:9999");
            List<WebElement> elements = driver.findElements(By.className("input__control"));
            elements.get(0).sendKeys("Сергей");
            elements.get(1).sendKeys("+79870000000");
            driver.findElement(By.className("checkbox__box")).click();
            driver.findElement(By.className("button")).click();
            String text = driver.findElement(By.className("alert-success")).getText();
            assertEquals("Ваша заявка успешно отправлена!", text.strip()); // or trim()
        }

        @Test
        void Test2() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);

            driver.get("http://localhost:9999");
            WebElement form = driver.findElement(By.cssSelector("[data-test-id=callback-form]"));
            form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Василий");
            form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79290000000");
            form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
            form.findElement(By.cssSelector("[data-test-id=submit]")).click();
            String text = driver.findElement(By.className("alert-success")).getText();
            assertEquals("Ваша заявка успешно отправлена!", text.trim());
        }
    }
}


