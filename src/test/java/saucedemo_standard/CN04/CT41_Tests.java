package saucedemo_standard.CN04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;


@DisplayName("CT4.1 - Remoção de itens do carrinho ( Tela inicial )")
public class CT41_Tests {
    @Test
    @DisplayName("Remoção de itens ao carrinho (Tela inicial)")
    public void testRemocaoInicial(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");

        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        List<WebElement> addButtons = navegador.findElements(By.className("btn_primary"));

        for (WebElement add : addButtons){
            navegador.findElement(By.className("btn_primary")).click();
        }

        String seloBefore = navegador.findElement(By.className("fa-layers-counter")).getText();

        List<WebElement> removeButtons = navegador.findElements(By.className("btn_secondary"));

        for (WebElement remove : removeButtons){
            navegador.findElement(By.className("btn_secondary")).click();
        }

        String seloAfter = "0";

        navegador.quit();
    }
}
