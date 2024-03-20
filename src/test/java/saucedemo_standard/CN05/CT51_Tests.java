package saucedemo_standard.CN05;


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


@DisplayName("CT5.1 - Sair do carrinho para a página inicial e continuar comprando")
public class CT51_Tests {

    @Test
    @DisplayName("Sair do carrinho para a página inicial e continuar comprando")
    public void testContinuarComprando(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");

        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        WebElement[] titulosProds = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);

        String[] tituloProd = Arrays.stream(titulosProds)
                .map(WebElement::getText)
                .toArray(String[]::new);

        List<WebElement> addButtons = navegador.findElements(By.className("btn_primary"));

        for (WebElement add : addButtons){
            navegador.findElement(By.className("btn_primary")).click();
        }

        navegador.findElement(By.className("svg-inline--fa")).click();

        WebElement[] nomesProds = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);

        String[] nomeProd = Arrays.stream(nomesProds)
                .map(WebElement::getText)
                .toArray(String[]::new);

        Assertions.assertArrayEquals(tituloProd,nomeProd);

        navegador.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[1]")).click();

        String selocheck = navegador.findElement(By.className("fa-layers-counter")).getText();

        Assertions.assertEquals(selocheck, "6");

        navegador.quit();
    }
}
