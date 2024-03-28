package saucedemo_standard.CN03;

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

@DisplayName("CT3.1 - Adição de itens ao carrinho ( Tela inicial )")
public class CT31_Tests {
    @Test
    @DisplayName("Adição de itens ao carrinho ( Tela inicial )")
    public void testCarrinhoAdicaoInicial(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");

        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        WebElement[] productlinks = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);

        String[] links = Arrays.stream(productlinks)
                .map(WebElement::getText)
                .toArray(String[]::new);

        List<WebElement> addButtons = navegador.findElements(By.className("btn_primary"));

        for (WebElement button : addButtons){
            navegador.findElement(By.className("btn_primary")).click();
        }

        navegador.findElement(By.className("svg-inline--fa")).click();

        WebElement[] cartProducts = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);

        String[] products = Arrays.stream(cartProducts)
                .map(WebElement::getText)
                .toArray(String[]::new);

        Assertions.assertArrayEquals(products,links);

        navegador.quit();
    }
}
