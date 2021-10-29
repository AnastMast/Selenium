package ru.netology;

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


public class WebTest {
        private WebDriver driver;

        @BeforeAll
        static void setUpAll() {
            System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");

        }

        @BeforeEach
        void setUp() {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            driver.get("http://localhost:7777");
        }

        @AfterEach
        void tearDown() {
            driver.quit();
            driver = null;
        }

        @Test
        void shouldOrderCardWithoutCssSelectors() {

            List<WebElement> elements = driver.findElements(By.className("input__control"));
            elements.get(0).sendKeys("Василий");
            elements.get(1).sendKeys("+79870000000");
            driver.findElement(By.className("checkbox__box")).click();
            driver.findElement(By.className("button")).click();
            String text = driver.findElement(By.className("paragraph_theme_alfa-on-white")).getText();
            assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.strip());


        }

        @Test
        void shouldOrderCardWithCssSelectors() {

            driver.findElement(By.cssSelector("[type='text']")).sendKeys("Василий");
            driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79290000000");
            driver.findElement(By.cssSelector(".checkbox__box")).click();
            driver.findElement(By.cssSelector("button")).click();
            String text = driver.findElement(By.cssSelector(".paragraph_theme_alfa-on-white")).getText();
            assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());


        }
    }



