package saucedemo_standard.CN07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("CT7.2 - Realizar uma compra ( Tela produto )")
public class CT72_Tests {
    @Test
    @DisplayName("Realizar uma compra ( Tela produto )")
    public void CompraProduto(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");

        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        String nome = navegador.findElement(By.id("item_4_title_link")).getText();
        String descricao = navegador.findElement(By.className("inventory_item_desc")).getText();
        String preco = navegador.findElement(By.className("inventory_item_price")).getText();
        preco = preco.replace("$", "");


        navegador.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).click();
        navegador.findElement(By.className("btn_primary")).click();
        navegador.findElement(By.className("svg-inline--fa")).click();

        String produto = navegador.findElement(By.className("inventory_item_name")).getText();
        String infos = navegador.findElement(By.className("inventory_item_desc")).getText();
        String valor = navegador.findElement(By.className("inventory_item_price")).getText();

        Assertions.assertEquals(nome, produto);
        Assertions.assertEquals(descricao, infos);
        Assertions.assertEquals(preco, valor);

        navegador.quit();
    }
}