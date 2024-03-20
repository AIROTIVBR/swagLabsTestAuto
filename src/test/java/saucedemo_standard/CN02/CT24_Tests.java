package saucedemo_standard.CN02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

@DisplayName("CT2.4 - Filtro de ordenação de produtos (opção “Price( high to low )”)")
public class CT24_Tests {
    @Test
    @DisplayName("Filtro de ordenação de produtos (opção “Price( high to low )”)")
    public void testOrdenacaoHiLo(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");

        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        WebElement[] precoProds = navegador.findElements(By.className("inventory_item_price")).toArray(new WebElement[0]);

        String[] precoProduto = Arrays.stream(precoProds)
                .map(WebElement::getText)
                .toArray(String[]::new);

        Arrays.sort(precoProduto, Collections.reverseOrder());

        WebElement sortFilter = navegador.findElement(By.className("product_sort_container"));
        Select dropdown = new Select(sortFilter);
        dropdown.selectByValue("hilo");

        WebElement[] precos = navegador.findElements(By.className("inventory_item_price")).toArray(new WebElement[0]);

        String[] preco = Arrays.stream(precos)
                .map(WebElement::getText)
                .toArray(String[]::new);

        Arrays.sort(preco, Collections.reverseOrder());

        Assertions.assertArrayEquals(precoProduto,preco);

        navegador.quit();
    }
}
