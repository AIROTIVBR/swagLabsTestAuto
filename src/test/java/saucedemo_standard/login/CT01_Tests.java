package saucedemo_standard.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
@DisplayName("CT01 - Login com credenciais erradas")
public class CT01_Tests {
    @Test
    @DisplayName("Login com credenciais erradas")
    public void testLoginWrong(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");
        navegador.findElement(By.id("user-name")).sendKeys("Standard-user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();
        WebElement msgErro = (WebElement) navegador.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/h3"));
        String textError = msgErro.getText();
        String expctError = "Epic sadface: Username and password do not match any user in this service";
        Assertions.assertEquals(textError,expctError);
        navegador.quit();
    }
}
