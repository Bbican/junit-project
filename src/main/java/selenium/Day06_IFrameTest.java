package selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Day06_IFrameTest {

    WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver"); //MAC
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/iframe");

    }
    /*
        If your test case fails, there might be different reasons:
        1. Locator may not be correct
        2. If locator is correct, then it can be time out issue. Yuo can add more waits
        3. If it is not timeout issue, then element moght be inside the iframe. You must switch to iframe first
     */

//    Create a class: IFrameTest
//    Create a method: iframeTest
//    Go to https://the-internet.herokuapp.com/iframe
//    Verify the Bolded text contains “Editor”
//    Locate the text box
//    Delete the text in the text box
//    Type “This text box is inside the iframe”
//    Verify the text Elemental Selenium text is displayed on the page

    @Test
    public void iframeTest(){
        //    Verify the Bolded text contains “Editor”

        String boldText = driver.findElement(By.xpath("//h3")).getText();
        Assert.assertTrue(boldText.contains("Editor"));

        //       WE MUST SWITCH TO THE IFRAME TO LOCATE THE ELEMENTS INSIDE THE IFRAME
//        THERE ARE 3 WAYS TO SWITCH TO IFRAME
//        1. INDEX. index starts from 0
//        driver.switchTo().frame(0);// switching the first frame by index
//
//        2. SWITCH BY ID OR NAME
//        driver.switchTo().frame("mce_0_ifr");
//
//        3. WEBELEMENT
        WebElement iframeElement = driver.findElement(By.xpath("//*[@id='mce_0_ifr']"));
        driver.switchTo().frame(iframeElement);


        //    Locate the text box
        WebElement textBox = driver.findElement(By.xpath("//p"));

        //    Delete the text in the text box
        textBox.clear();

        //Type "This text box is inside the iframe"
        textBox.sendKeys("This text box is inside the iframe");

        //    Verify the text Elemental Selenium text is displayed on the page
        //  That element is OUTSIDE of the iframe. Thern switch to the Parent  or Default content
        driver.switchTo().parentFrame();
        WebElement elementalSelenium =  driver.findElement(By.linkText("Elemental Selenium"));
        Assert.assertTrue(elementalSelenium.isDisplayed());


    }
    @After
    public void tearDown (){
        driver.quit();
    }

}
