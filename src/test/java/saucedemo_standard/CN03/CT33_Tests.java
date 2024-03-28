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

@DisplayName("CT3.3 - Adição de itens ao carrinho ( Duas telas )")
public class CT33_Tests {
    @Test
    @DisplayName("Adição de itens ao carrinho ( Duas telas )")
    public void testCarrinhoAdicaoDuas(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");

        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        WebElement[] linksProdutos = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);

        for (WebElement link : linksProdutos){
            link.click();
            navegador.findElement(By.className("btn_primary")).click();
            navegador.findElement(By.className("inventory_details_back_button")).click();
        }

        WebElement[] botaoAdd = navegador.findElements(By.className("btn_primary")).toArray(new WebElement[0]);

        for (WebElement botao : botaoAdd){
            String conteudo = botao.getText();
            Assertions.assertEquals(conteudo,"REMOVE");
        }

        boolean selo = navegador.findElement(By.className("fa-layers-counter")).isDisplayed();

        Assertions.assertTrue(selo);

        navegador.quit();
    }
}
