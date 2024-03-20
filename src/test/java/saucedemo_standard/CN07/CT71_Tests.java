package saucedemo_standard.CN07;

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
import java.util.stream.Collectors;

@DisplayName("CT7.1 - Realizar uma compra ( Tela inicial )")
public class CT71_Tests {
    @Test
    @DisplayName("Realizar uma compra ( Tela inicial )")
    public void CompraInicial(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.get("https://www.saucedemo.com/v1/");

        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();

        WebElement[] productName = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);

        String[] name = Arrays.stream(productName)
                .map(WebElement::getText)
                .toArray(String[]::new);

        WebElement[] productDescription = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);

        String[] descp = Arrays.stream(productDescription)
                .map(WebElement::getText)
                .toArray(String[]::new);

        List<WebElement> priceElements = navegador.findElements(By.className("inventory_item_price"));

        List<Float> precosLista = priceElements.stream()
                .map(element -> Float.parseFloat(element.getText().replace("$", "")))
                .collect(Collectors.toList());

        Float[] precos = precosLista.toArray(new Float[0]);

        float soma = (float) Arrays.stream(precos).mapToDouble(Float::doubleValue).sum();

        List<WebElement> linksProdutos = navegador.findElements(By.className("inventory_item_name"));

        for (WebElement links : linksProdutos){
            navegador.findElement(By.className("btn_primary")).click();
        }

        navegador.findElement(By.className("svg-inline--fa")).click();

        navegador.findElement(By.className("btn_action")).click();

        navegador.findElement(By.id("first-name")).sendKeys("Nome");

        navegador.findElement(By.id("last-name")).sendKeys("Sobrenome");

        navegador.findElement(By.id("postal-code")).sendKeys("55555-555");

        navegador.findElement(By.className("btn_action")).click();
    }
}
