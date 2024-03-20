package saucedemo_standard.CN01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("CT1.2- Login com credenciais corretas")
public class CT12_Tests {
    @Test
    @DisplayName("Login com credenciais corretas")
    public void testLoginSucesso(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");
        
        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        String linkCorreto = "https://www.saucedemo.com/v1/inventory.html";
        String pagInicial = navegador.getCurrentUrl();

        Assertions.assertEquals(linkCorreto,pagInicial);

        navegador.quit();
    }
}

