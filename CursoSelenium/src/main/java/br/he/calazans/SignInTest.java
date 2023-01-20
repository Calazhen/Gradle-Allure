package br.he.calazans;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class SignInTest {
    String driverPath = "C:\\Users\\calaz\\chromedriver.exe";
    private WebDriver driver;

    @Before
    public void inicialize (){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get ("file:///"+ System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        driver.manage().window().maximize();
    }
    @After
    public void turDown(){
        driver.quit();
    }

    @Test
    public void fillingFullForms (){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Henrique");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Calazans");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
        new Select(driver.findElement(By.id("ElementosForm:escolaridade"))).selectByVisibleText("Superior");
        new Select(driver.findElement(By.id("ElementosForm:esportes"))).selectByVisibleText("Futebol");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Henrique"));
        Assert.assertEquals("Sobrenome: Calazans",driver.findElement(By.id("descSobrenome")).getText());
        Assert.assertEquals("Sexo: Masculino",driver.findElement(By.id("descSexo")).getText());
        Assert.assertEquals("Comida: Frango",driver.findElement(By.id("descComida")).getText());
        Assert.assertEquals("Escolaridade: superior",driver.findElement(By.id("descEscolaridade")).getText());
        Assert.assertEquals("Sugestoes:",driver.findElement(By.id("descSugestoes")).getText());
    }
}
