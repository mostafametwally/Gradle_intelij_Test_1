package login;



import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Login_Test {


    @BeforeEach
    public void setupMethod() throws InterruptedException{

        //Configuration.baseUrl="https://";
        Configuration.browserSize = "1296x687";
        Configuration.holdBrowserOpen= true;
        Configuration.browser="chrome";
        //com.codeborne.selenide.Configuration.chromeSwitches = "no-sandbox";
        Configuration.headless= true;

        Configuration.browserPosition="0x0";

        //open("www.google.com");
        //open("https://the-internet.herokuapp.com/");
        // taskkill /F /IM chromedriver.exe /T

        System.setProperty("selenide.remote", "http://0.0.0.0:4444/wd/hub");

    }
    @Test
    public void switchToFrame() throws InterruptedException {
        open("https://the-internet.herokuapp.com/nested_frames");
        System.out.println($$(By.tagName("frame")).size());
        switchTo().frame(0);
        System.out.println($$(By.tagName("frame")).size());
        for (int i=0;i<$$(By.tagName("frame")).size();i++){
            switchTo().frame(i);
            System.out.println($(By.tagName("body")).getText());
            switchTo().parentFrame();
        }
        switchTo().parentFrame();
        switchTo().frame(1);
        System.out.println($(By.tagName("body")).getText());
       Assert.assertTrue(true);
    }
    @Test
    public void switchToAlert() {
        open("https://the-internet.herokuapp.com/javascript_alerts");
        $(By.tagName("button")).click();
        Alert alert = switchTo().alert();
        String actual= alert.getText();
        alert.accept();
        String expected= "I am a JS Alert";
        //Assert.
        assertEquals(actual, expected);
    }
    @Test
    public void findelements() {
        open("https://the-internet.herokuapp.com/");
        ElementsCollection coll= $$(By.tagName("a"));
        List<String> x= coll.texts();
        int size = coll.size();
        System.out.println(size);
        x.forEach(e -> System.out.println(e));
        coll.filter(Condition.ownText("File"));
        for  (SelenideElement element: coll
        ) {
            System.out.println(element.getText());
        }
    }

    @Test
    public void selectHandling(){

        open("https://the-internet.herokuapp.com/dropdown");
        $(By.id("dropdown")).selectOption(2);
    }
    @Test
    public void mouseAction() throws InterruptedException {
        open("https://jqueryui.com/droppable/");
        switchTo().frame(0);
        //actions().moveToElement($(By.id("column-a"))).doubleClick().build().perform();
        Thread.sleep(7000);
        actions().dragAndDrop($(By.id("draggablee")), $(By.id("droppable"))).build().perform();
        Thread.sleep(3000);
    }
    @Test
    public void helloWorld(){
        System.out.println("Hello World This is mostafa from the Earth");
    }
}