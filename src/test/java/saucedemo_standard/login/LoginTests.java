package saucedemo_standard.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Teste automatizado para testar a funcionalidade de login")
public class LoginTests {
    @Test
    @DisplayName("Realizar login na aplicação utilizando as credenciais")
    public void testRealizarLogin(){
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
        //Fechar o navegador
        navegador.quit();
    }
}

