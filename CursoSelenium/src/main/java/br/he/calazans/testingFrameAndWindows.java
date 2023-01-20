package br.he.calazans;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testingFrameAndWindows {
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
    public void interactingFrames() {
        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);

        alert.accept();
        // Another way to do the same thing:
        /*WebElement nameField = driver.findElement(By.id("elementosForm:nome"));
        nameField.sendKeys(msg);
        Assert.assertEquals(nameField.getText(), msg);*/
    }

    @Test
    public void interactingWindows() {
        String winHandBefore = driver.getWindowHandle();
        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo?");

        driver.close();

        driver.switchTo().window(winHandBefore);

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("e agora?");
        System.out.println("depois de fechar");
    }

    @Test
    public void usingWindowHandler() {
        driver.findElement(By.id("buttonPopUpHard")).click();

        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo?");
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
        driver.close();
    }

}
