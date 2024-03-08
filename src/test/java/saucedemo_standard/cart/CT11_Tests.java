package saucedemo_standard.cart;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.util.List;

@DisplayName("CT11- Remoção de itens do carrinho (Tela do carrinho)")
public class CT11_Tests {
    @Test
    @DisplayName("Remoção de itens do carrinho (Tela do carrinho)")
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
            add.click();
        }

        navigator.findElement(By.className("svg-inline--fa")).click();

        List<WebElement> removeButtons = navigator.findElements(By.className("btn_secondary"));

        for (WebElement remove : removeButtons){
            remove.click();
        }

        try {
            WebElement cartAfter = navigator.findElement(By.className("fa-layers-counter"));
            System.out.println("The shopping cart is no empty");
        } catch (NoSuchElementException e) {
            System.out.println("The shopping cart is empty.");
        }

        navigator.quit();
    }
}
