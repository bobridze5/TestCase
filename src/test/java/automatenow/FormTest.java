package automatenow;

import automatenow.pages.FormPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class FormTest {
    private WebDriver driver;
    private FormPage formPage;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://practice-automation.com/form-fields/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(description = "Тест проверяет заполнение формы и получение ответа 'Message received'")
    public void TestCase(){
        formPage = new FormPage(driver);

        formPage.enterName("Dmitry")
                .enterPassword("Password")
                .selectFavoriteDrink("Milk", "Coffee")
                .selectColor("Yellow")
                .selectDropDownAutomation("Yes")
                .enterEmail("dima101203@gmail.com")
                .enterMessage()
                .submit();

        String alertText = driver.switchTo().alert().getText();
        Assert.assertEquals(alertText, "Message received!");
        driver.switchTo().alert().accept();
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
