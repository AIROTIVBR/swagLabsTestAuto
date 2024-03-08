package saucedemo_standard.cart;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@DisplayName("CT09- Remoção de itens ao carrinho (Tela inicial)")
public class CT09_Tests {
    @Test
    @DisplayName("Remoção de itens ao carrinho (Tela inicial)")
    public void testCartRemove(){
        WebDriverManager.chromedriver().setup();
        WebDriver navigator = new ChromeDriver();
        navigator.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navigator.get("https://www.saucedemo.com/v1/");

        navigator.findElement(By.id("user-name")).sendKeys("standard_user");
        navigator.findElement(By.id("password")).sendKeys("secret_sauce");
        navigator.findElement(By.id("login-button")).click();

        List<WebElement> addButtons = navigator.findElements(By.className("btn_primary"));

        for (WebElement add : addButtons){
            navigator.findElement(By.className("btn_primary")).click();
        }

        String seloBefore = navigator.findElement(By.className("fa-layers-counter")).getText();

        List<WebElement> removeButtons = navigator.findElements(By.className("btn_secondary"));

        for (WebElement remove : removeButtons){
            navigator.findElement(By.className("btn_secondary")).click();
        }

        String seloAfter = "0";

        navigator.quit();
    }
}
