package br.he.calazans;


import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class trainingGround {

    String driverPath = "C:\\Users\\calaz\\chromedriver.exe";
    String toBeWroteInTextArea = "\n Henrique\nC.\nG.\nBaptista";

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

// Testando e Validando Título da Página//

    @Test
    public void gettingTitle(){
        String tituloCampoTreinamentoAtual = driver.getTitle();
        Assert.assertEquals("Campo de Treinamento",tituloCampoTreinamentoAtual);
        driver.quit();
    }

// Testando e validando texto escrito //
    @Test
    public void interactingTextField(){
        driver.get ("file:///"+ System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Henrique C. G. Baptista");
        String typedName = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");
        Assert.assertEquals("Henrique C. G. Baptista", typedName);
    }

// Testando Text area, a diferença é que eu posso escrever várias linhas
    @Test
    public void interactingTextArea(){
        String tituloCampoTreinamento = driver.getTitle();
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys(toBeWroteInTextArea);
        String assertWritingInTextArea =  driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");
        Assert.assertEquals(toBeWroteInTextArea,assertWritingInTextArea);
    }
    @Test
    public void interactingRadioButton (){
        driver.findElement(By.id("ElementosForm:sexo:0")).click();
        Boolean assert_If_RadioBox_Is_Selected = driver.findElement(By.id("ElementosForm:sexo:0")).isSelected();
        Assert.assertTrue(assert_If_RadioBox_Is_Selected);
    }
    @Test
    public void interactingCheckBox (){
        driver.findElement(By.id("ElementosForm:comidaFavorita:1")).click();
        Boolean assert_If_RadioBox_Is_Selected = driver.findElement(By.id("ElementosForm:comidaFavorita:1")).isSelected();
        Assert.assertTrue(assert_If_RadioBox_Is_Selected);
    }
    @Test
    public void interactingCombo (){
    WebElement element = driver.findElement(By.id("ElementosForm:escolaridade"));
    Select combo = new Select (element);
    List<WebElement> options = combo.getOptions();
     Assert.assertEquals(8,options.size());

    //combo.selectByIndex(3);
    //combo.selectByValue("superior");
    /* Terceira opção de encontrar o combo + Verificação simples
  combo.selectByVisibleText("2o grau incompleto");
  Assert.assertEquals("2o grau incompleto",combo.getFirstSelectedOption().getText());*/

        Boolean encontrou = false;
        for (WebElement option : options){
            if(option.getText().equals("Mestrado")){

                encontrou = true;
                break;

            }
        }
        Assert.assertTrue(encontrou);
    }
    @Test
    public void interactingMultipleChoisesCombo(){
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select (element);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        //Selecionando 3 opções em uma lista com Scrollbar
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3,allSelectedOptions.size());

        // Retirando a seleção:
        combo.deselectByVisibleText("Corrida");
    }
    @Test
    public void interactingButtons () {
        String expectedResultAfterclickOnButton = "Obrigado!";
        WebElement buttonClick = driver.findElement(By.id("buttonSimple"));
        buttonClick.click();
        String actualButtonText= buttonClick.getAttribute("value");
        Assert.assertEquals(expectedResultAfterclickOnButton,actualButtonText );
    }

    @Test
    public void interactingLinks (){
        driver.findElement(By.linkText("Voltar")).click();
        String textAfterClickOnLinkText = driver.findElement(By.id("resultado")).getText();
        Assert.assertEquals("Voltou!", textAfterClickOnLinkText );
        driver.quit();
    }
    @Test
    public  void seachingTextsOnPage(){
        /*   Para buscar o todos os textos da página e verficiar se contém o valor "Campo de Treinamento".
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));   */

         /*  Para buscar o primeiro elemento de uma tag de uma página.
         Assert.assertEquals("Campo de Treinamento",driver.findElement(By.tagName("h3")).getText());*/

         // Para buscar uma elemento pelo nome da classe, ou seja, algo único que o referêncie na página.
         Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",driver.findElement(By.className("facilAchar")).getText());
    }

}