package saucedemo_standard.CN06;

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

@DisplayName("CT6.2 - Acessar a tela do produto ( Tela do carrinho )")
public class CT62_Tests {
    @Test
    @DisplayName("Acessar a tela do produto ( Tela do carrinho )")
    public void testAcessarCarrinho(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");

        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        List<WebElement> addBotao = navegador.findElements(By.className("btn_primary"));

        for (WebElement add : addBotao){
            add.click();
        }

        navegador.findElement(By.className("svg-inline--fa")).click();

        WebElement[] nomesProdutos = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);

        String[] nomes = Arrays.stream(nomesProdutos)
                .map(WebElement::getText)
                .toArray(String[]::new);

        List<WebElement> linksProdutos = navegador.findElements(By.className("inventory_item_name"));

        for (WebElement links : linksProdutos){
            links.click();
            navegador.findElement(By.className("btn_secondary")).click();
            navegador.findElement(By.className("btn_primary")).click();
            navegador.findElement(By.className("inventory_details_back_button")).click();
        }

        navegador.quit();
    }
}