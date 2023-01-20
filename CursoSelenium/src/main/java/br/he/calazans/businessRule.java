package br.he.calazans;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class businessRule {
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
    public void nameIsMandatory() {
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());

    }

    @Test
    public void lastNameIsMandatory() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Henrique");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();

        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());

    }
    @Test
    public void genderIsMandatory() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Henrique");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Calazans");

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();

        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    }

    @Test
    public void areYouSureWereYouVegetarian() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Henrique");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Calazans");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }
    @Test
    public void doYouPracticeSportsOrNot () {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Henrique");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Calazans");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select (element);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("O que eh esporte?");


        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());

    }



}
