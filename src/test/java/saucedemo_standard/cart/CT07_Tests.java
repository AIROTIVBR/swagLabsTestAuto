package saucedemo_standard.cart;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@DisplayName("CT07- Adição de itens ao carrinho (tela inicial)")
public class CT07_Tests {
    @Test
    @DisplayName("Adição de itens ao carrinho (tela inicial)")
    public void testCartAdd(){
        WebDriverManager.chromedriver().setup();
        WebDriver navigator = new ChromeDriver();
        navigator.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navigator.get("https://www.saucedemo.com/v1/");

        navigator.findElement(By.id("user-name")).sendKeys("standard_user");
        navigator.findElement(By.id("password")).sendKeys("secret_sauce");
        navigator.findElement(By.id("login-button")).click();

        List<WebElement> addButtons = navigator.findElements(By.className("btn_primary"));

        for (WebElement button : addButtons){
            navigator.findElement(By.className("btn_primary")).click();
        }

        WebElement[] buttoncontent = navigator.findElements(By.className("btn_secondary")).toArray(new WebElement[0]);

        String[] content = Arrays.stream(buttoncontent)
                .map(WebElement::getText)
                .toArray(String[]::new);

        String[] expect = {"REMOVE", "REMOVE", "REMOVE", "REMOVE", "REMOVE", "REMOVE"};

        Assertions.assertArrayEquals(content,expect);
        navigator.quit();
    }
}
