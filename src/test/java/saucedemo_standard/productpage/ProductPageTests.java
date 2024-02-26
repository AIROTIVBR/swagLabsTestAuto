package saucedemo_standard.productpage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes automatizados da funcionalidade do título link de cada produto")
public class ProductPageTests {
    @Test
    @DisplayName("Abrir a página com as informações do produto")
    public void testAbrirAPaginaComAsInformacoesDoProduto(){
        //Deve-se abrir o navegador de preferência
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Então pesquisar por Saucedemo Swag Labs e clique no link
        navegador.get("https://www.saucedemo.com/v1/");
        //Nesse momento faça o login utilizando o usuário standard_user e a senha secret_sauce
        navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();
        //Ao abrir a página com os produtos clique no título do produto que possui o id "item_4_title_link"
        String tituloDoItemInicio = navegador.findElement(By.className("inventory_item_name")).getText();
        navegador.findElement(By.className("btn_primary")).click();
        navegador.findElement(By.className("btn_secondary")).click();
        navegador.findElement(By.id("item_4_title_link")).click();
        //Verificar se o nome do item está certo ou não
        String tituloDoItemPagEspc = navegador.findElement(By.className("inventory_details_name")).getText();
        Assertions.assertEquals(tituloDoItemInicio,tituloDoItemPagEspc);
        navegador.findElement(By.className("btn_primary")).click();
        navegador.findElement(By.className("svg-inline--fa")).click();
        navegador.findElement(By.className("btn_action")).click();
        navegador.findElement(By.id("first-name")).sendKeys("Testes");
        navegador.findElement(By.id("last-name")).sendKeys("Automatico");
        navegador.findElement(By.id("postal-code")).sendKeys("55555555");
        navegador.findElement(By.className("btn_primary")).click();
        String produto = navegador.findElement(By.className("inventory_item_name")).getText();
        Assertions.assertEquals(tituloDoItemInicio,produto);
        navegador.findElement(By.className("btn_action")).click();
        //Fechar o navegador
        navegador.quit();
    }
}
