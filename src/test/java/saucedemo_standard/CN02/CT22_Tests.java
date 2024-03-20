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

@DisplayName("CT2.2 - Filtro de ordenação de produtos (opção “Name ( Z to A )”)")
public class CT22_Tests {
    @Test
    @DisplayName("Filtro de ordenação de produtos (opção “Name ( Z to A )”)")
    public void testOrdenacaoZA(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");

        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        WebElement[] titulosProds = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);

        String[] tituloProd = Arrays.stream(titulosProds)
                .map(WebElement::getText)
                .toArray(String[]::new);

        Arrays.sort(tituloProd);

        WebElement sortFilter = navegador.findElement(By.className("product_sort_container"));
        Select dropdown = new Select(sortFilter);
        dropdown.selectByValue("za");

        WebElement[] nomesProds = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);

        String[] nomeProd = Arrays.stream(nomesProds)
                .map(WebElement::getText)
                .toArray(String[]::new);

        Arrays.sort(tituloProd, Collections.reverseOrder());

        Assertions.assertArrayEquals(tituloProd,nomeProd);

        navegador.quit();
    }
}