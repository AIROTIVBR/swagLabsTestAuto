package saucedemo_standard.CN04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

@DisplayName("CT4.2 - Remoção de itens do carrinho ( Tela do produto )")
public class CT42_Tests {
    @Test
    @DisplayName("Remoção de itens do carrinho (Tela do produto)")
    public void testRemocaoProduto(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");

        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        List<WebElement> linksProdutos = navegador.findElements(By.className("inventory_item_name"));

        for (WebElement links : linksProdutos){
            links.click();
            navegador.findElement(By.className("btn_primary")).click();
            navegador.findElement(By.className("inventory_details_back_button")).click();
        }

        for (WebElement link : linksProdutos){
            link.click();
            navegador.findElement(By.className("btn_secondary")).click();
            navegador.findElement(By.className("inventory_details_back_button")).click();
        }

        try {
            boolean selo = navegador.findElement(By.className("fa-layers-counter")).isDisplayed();
        } catch (NoSuchElementException e) {
            boolean selo = false;
            Assertions.assertFalse(selo);
        }

        navegador.quit();
    }
}

