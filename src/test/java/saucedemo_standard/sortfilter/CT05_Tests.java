package saucedemo_standard.sortfilter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

@DisplayName("CT05- Filtro de ordenação de produtos (opção  “Price( low to high )”)")
public class CT05_Tests {
    @Test
    @DisplayName("Filtro de ordenação de produtos (opção  “Price( low to high )”)")
    public void testSortLoHi(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");
        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();
        WebElement sortFilter = navegador.findElement(By.className("product_sort_container"));
        Select dropdown = new Select(sortFilter);
        dropdown.selectByValue("lohi");
    }
}
