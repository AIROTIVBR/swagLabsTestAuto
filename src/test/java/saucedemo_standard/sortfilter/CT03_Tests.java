package saucedemo_standard.sortfilter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.time.Duration;
@DisplayName("CT03- Filtro de ordenação de produtos (opção “Name( A to Z )”)")
public class CT03_Tests {
    @Test
    @DisplayName("Filtro de ordenação de produtos (opção “Name( A to Z )”)")
    public void testSortAZ(){
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
        dropdown.selectByValue("az");
        WebElement[] nomesProds = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);
        String[] nomeProd = Arrays.stream(nomesProds)
                .map(WebElement::getText)
                .toArray(String[]::new);
        Assertions.assertArrayEquals(nomeProd,tituloProd);
    }
}
