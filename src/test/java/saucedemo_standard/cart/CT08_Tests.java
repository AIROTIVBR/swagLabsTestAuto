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

@DisplayName("CT08- Adição de itens ao carrinho (tela de produto)")
public class CT08_Tests {
    @Test
    @DisplayName("Adição de itens ao carrinho (tela de produto)")
    public void testCartAdd(){
        WebDriverManager.chromedriver().setup();
        WebDriver navigator = new ChromeDriver();
        navigator.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navigator.get("https://www.saucedemo.com/v1/");

        navigator.findElement(By.id("user-name")).sendKeys("standard_user");
        navigator.findElement(By.id("password")).sendKeys("secret_sauce");
        navigator.findElement(By.id("login-button")).click();

        WebElement[] productlinks = navigator.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);

        String[] links = Arrays.stream(productlinks)
                .map(WebElement::getText)
                .toArray(String[]::new);

        for (int i = 0; i < productlinks.length; i++) {
            WebElement element = productlinks[i];
            String text = links[i];
            if (Arrays.asList(links).contains(text)) {
                element.click();
                navigator.findElement(By.className("btn_primary")).click();
                navigator.navigate().back();

            }
        }

        navigator.findElement(By.className("svg-inline--fa")).click();

        WebElement[] cartProducts = navigator.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);

        String[] products = Arrays.stream(cartProducts)
                .map(WebElement::getText)
                .toArray(String[]::new);

        Assertions.assertArrayEquals(links,products);

        navigator.quit();
    }
}
