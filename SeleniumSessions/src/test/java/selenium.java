import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.http.io.SessionOutputBuffer;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v124.indexeddb.model.Key;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class selenium {
    public static WebDriver driver ;

    public static String firstTab;
    public static String secondTab;
    public static void main(String[] args) throws IOException, URISyntaxException {
        initializeDriver();

        openBrowser("https://the-internet.herokuapp.com/broken_images");
//        openBrowser("https://demo.guru99.com/test/radio.html");
//        WebElement usernameInput = driver.findElement(By.id("username"));
//        By usernameInput = By.id("username");
//        byToWebElement(usernameInput);

//        entreText("tomsmith","SuperSecretPassword!");
//        clearText();
//        clicking();
//        getTextFromField();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//         getHelloWorld();
//        handlingDropdown();
//        clickAndHold();
//        moveToTab("");

//        Set<String> handles = openNewTab();
//        for (String h : handles)
//        {
//            if(!h.equals(firstTab))
//                secondTab = h;
//        }
//        System.out.println(firstTab);
//        System.out.println(secondTab);
//        System.out.println(driver.getCurrentUrl());
//        switchingTab(secondTab);
//        System.out.println(driver.getCurrentUrl());

//        iFrame();
//        nestedFrame();

//        acceptAlert();
//        dismissAlert();
//        prompt();

//        keyUsingSendKey();
//        keysUsingAction();

//        try {
//            takingScreenShoot("screenshot1");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        uploadUsingSendKeys("E:\\_Testing\\Nezam Academy\\Automation\\SeleniumSessions\\src\\main\\resources\\screenshot1.png");
        List<WebElement> elements = driver.findElements(By.tagName("img"));
        checkBrokenUsingRestAssured(elements,"photo");
    }
    public static void initializeDriver()
    {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--guest");
        edgeOptions.addArguments("--start-maximized");// --headlees -> no UI
        driver = new EdgeDriver(edgeOptions);
    }

    public static void checkBrokenUsingRestAssured(List<WebElement> elements,String type)throws IOException, URISyntaxException
    {
        URL url = null;
        String attribute;
        if(type.equals("photo"))
            attribute = "src";
        else
            attribute = "href";
        for(WebElement element : elements){
            url = new URI(element.getAttribute(attribute)).toURL();
            Response response = RestAssured.given().get(url);
            System.out.println(response.getStatusLine());

        }
    }

    public static void checkBroken(List<WebElement> elements,String type)throws IOException, URISyntaxException
    {
        URL url = null;
        String attribute;
        if(type.equals("photo"))
            attribute = "src";
        else
            attribute = "href";
        for(WebElement element : elements){
            url = new URI(element.getAttribute(attribute)).toURL();
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            System.out.println(httpURLConnection.getResponseMessage()+" "+httpURLConnection.getResponseCode());
        }
    }

    public static void uploadFileUsingRobot()
    {

    }

    public static void uploadUsingSendKeys(String path)
    {
        driver.findElement(By.id("file-upload")).sendKeys(path);
    }

    public static void takingScreenShoot(String imageName) throws IOException {
        String path = "E:\\_Testing\\Nezam Academy\\Automation\\SeleniumSessions\\src\\main\\resources\\";
        File src = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.FILE);
        File target = new File(path+imageName+".png");
        FileUtils.copyFile(src,target);
    }

    public static void keysUsingAction()
    {
        new Actions(driver).keyDown(Keys.SHIFT).perform();
    }

    public static void keyUsingSendKey()
    {
        driver.findElement(By.id("target")).sendKeys(Keys.ARROW_DOWN);
    }
    public static void prompt()
    {
        driver.findElement(By.cssSelector("[onclick='jsPrompt()']")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Amr");
        alert.accept();
    }
    public static void dismissAlert()
    {
        driver.findElement(By.cssSelector("[onclick='jsConfirm()']")).click();
        driver.switchTo().alert().dismiss();
    }
    public static void acceptAlert()
    {
        driver.findElement(By.cssSelector("[onclick='jsAlert()']")).click();
        driver.switchTo().alert().accept();
    }

    public static void nestedFrame()
    {
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        System.out.println(driver.findElement(By.tagName("body")).getText());
        driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-bottom");
        System.out.println(driver.findElement(By.tagName("body")).getText());
    }

    public static void iFrame()
    {
        By textArea = By.cssSelector("body#tinymce p");
        driver.switchTo().frame("mce_0_ifr");
        driver.findElement(textArea).clear();
        driver.findElement(textArea).sendKeys("Ahmed");
        driver.switchTo().parentFrame();
    }

    public static void switchingTab(String handle)
    {
        driver.switchTo().window(handle);
    }
    public static Set<String> openNewTab()
    {
        By newTabButton = By.cssSelector("a[href='/windows/new']");
        driver.findElement(newTabButton).click();
        firstTab = driver.getWindowHandle();
        return driver.getWindowHandles();


    }

    public static void clickAndHold()
    {
        By box_a = By.id("column-a");
        By box_b = By.id("column-b");
        new Actions(driver).clickAndHold(byToWebElement(box_b))
                .moveToElement(byToWebElement(box_a))
                .release()
                .build()
                .perform();
    }

    public static void dragAndDrop()
    {
        By box_a = By.id("column-a");
        By box_b = By.id("column-b");
        new Actions(driver).dragAndDrop(byToWebElement(box_a), byToWebElement(box_b)).perform();
    }
    public static void rightClick()
    {
        By box =By.id("hot-spot");
        new Actions(driver).contextClick((WebElement) box).perform();
    }

    public static void handlingCheckboxes()
    {
        By checkbox = By.cssSelector("input[type='checkbox']:nth-of-type(1)");
        driver.findElement(checkbox).click();
    }

    public static void handlingRadioButtons()
    {
        By radio = By.id("vfb-7-1");
        driver.findElement(radio).click();
    }

    public static void handlingDropdown()
    {
        By dropDown = By.cssSelector("select#dropdown");
        new Select((WebElement)dropDown).selectByValue("1");
    }

    public static void fluentWait(By locator)
    {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
                .withMessage(locator.toString()+ "doesn't meet the criteria")
                .pollingEvery(Duration.ofMillis(2L))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public static void explicitWait(By locator)
    {
        new WebDriverWait(driver,Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void getHelloWorld()
    {
        By startButton = By.tagName("button");
        By hellowordMessage = By.xpath("//div[@id='finish']//h4");
        driver.findElement(startButton).click();
        fluentWait(hellowordMessage);
        String msg = driver.findElement(hellowordMessage).getText();
        System.out.println(msg);

    }

    public static void clearText()
    {
        By usernameLocator = By.id("username");

        driver.findElement(usernameLocator).clear();

    }

    public static void entreText(String username, String password)
    {
        By usernameLocator = By.id("username");
        By passwordLocator = By.id("password");
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);


    }

    public static void getTextFromField()
    {
        By flashmessage = By.className("flash");
        String msg = driver.findElement(flashmessage).getText();
        System.out.println(msg);

    }

    public static void clicking()
    {
      By loginButton = By.className("radius");
      driver.findElement(loginButton).click();
    }

    public static WebElement byToWebElement(By locator)
    {
        return driver.findElement(locator);
    }


    

    public static void openBrowser(String url)
    {
        driver.get(url);

    }
}
