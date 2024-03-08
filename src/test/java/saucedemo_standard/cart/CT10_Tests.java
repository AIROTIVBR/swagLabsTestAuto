package saucedemo_standard.cart;

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

@DisplayName("CT10- Remoção de itens do carrinho (Tela do produto)")
public class CT10_Tests {
    @Test
    @DisplayName("Remoção de itens do carrinho (Tela do produto)")
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

        List<WebElement> productsLinks = navigator.findElements(By.className("inventory_item_name"));

        for (WebElement links : productsLinks){
            links.click();
            navigator.findElement(By.className("btn_secondary")).click();
            navigator.findElement(By.className("inventory_details_back_button")).click();
        }

        boolean seloAfter = navigator.findElement(By.className("fa-layers-counter")).isDisplayed();

        Assertions.assertFalse(seloAfter);

        navigator.quit();
    }
}

