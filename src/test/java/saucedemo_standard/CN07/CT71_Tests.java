package saucedemo_standard.CN07;

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

        WebElement[] productDescription = navegador.findElements(By.className("inventory_item_desc")).toArray(new WebElement[0]);
        String[] descp = Arrays.stream(productDescription)
                .map(WebElement::getText)
                .toArray(String[]::new);

        List<WebElement> priceElements = navegador.findElements(By.className("inventory_item_price"));
        List<Float> precosLista = priceElements.stream()
                .map(element -> Float.parseFloat(element.getText().replace("$", "")))
                .toList();

        List<WebElement> precoFloat = navegador.findElements(By.className("inventory_item_price"));
        Float[] precoF = precoFloat.stream()
                .map(element -> Float.parseFloat(element.getText().replace("$", "")))
                .toArray(Float[]::new);
        String[] precoS = Arrays.stream(precoF)
                .map(String::valueOf)
                .toArray(String[]::new);

        float soma = (float) Arrays.stream(precoF)
                .mapToDouble(Float::doubleValue)
                .sum();

        List<WebElement> linksProdutos = navegador.findElements(By.className("inventory_item_name"));
        List<String> link = linksProdutos.stream()
                .map(WebElement::getText)
                .toList();

        for (WebElement links : linksProdutos){
            navegador.findElement(By.className("btn_primary")).click();
        }

        String[] linkArray = link.toArray(new String[0]);

        navegador.findElement(By.className("svg-inline--fa")).click();

        WebElement[] itemNames = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);
        String[] item = Arrays.stream(itemNames)
                .map(WebElement::getText)
                .toArray(String[]::new);

        Assertions.assertArrayEquals(item,name);

        WebElement[] itemDescription = navegador.findElements(By.className("inventory_item_desc")).toArray(new WebElement[0]);
        String[] itemDes = Arrays.stream(itemDescription)
                .map(WebElement::getText)
                .toArray(String[]::new);

        Assertions.assertArrayEquals(itemDes,descp);

        WebElement[] precoItem = navegador.findElements(By.className("inventory_item_price")).toArray(new WebElement[0]);
        String[]  precoI = Arrays.stream(precoItem)
                .map(WebElement::getText)
                .toArray(String[]::new);

        Assertions.assertArrayEquals(precoI,precoS);

        navegador.findElement(By.className("btn_action")).click();

        navegador.findElement(By.id("first-name")).sendKeys("Nome");
        navegador.findElement(By.id("last-name")).sendKeys("Sobrenome");
        navegador.findElement(By.id("postal-code")).sendKeys("55555-555");

        navegador.findElement(By.className("btn_primary")).click();

        WebElement[] Names = navegador.findElements(By.className("inventory_item_name")).toArray(new WebElement[0]);
        String[] nam = Arrays.stream(Names)
                .map(WebElement::getText)
                .toArray(String[]::new);

        Assertions.assertArrayEquals(item,name);

        WebElement[] itemDescrip = navegador.findElements(By.className("inventory_item_desc")).toArray(new WebElement[0]);
        String[] des = Arrays.stream(itemDescrip)
                .map(WebElement::getText)
                .toArray(String[]::new);

        Assertions.assertArrayEquals(des,descp);

        List<WebElement> precoElemento = navegador.findElements(By.className("inventory_item_price"));
        Float[] precoE = precoElemento.stream()
                .map(element -> Float.parseFloat(element.getText().replace("$", "")))
                .toArray(Float[]::new);

        Assertions.assertArrayEquals(precoE,precoF);

        Float valorItens = 129.94F;

        Assertions.assertEquals(soma,valorItens);

        navegador.findElement(By.className("btn_action"));

        navegador.quit();
    }
}
