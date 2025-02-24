package automatenow.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class FormPage extends BasePage{

    @FindBy(xpath = "//input[@name='name-input']")
    private WebElement name;

    @FindBy(css = "input[type='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@name='fav_drink']")
    private List<WebElement> drinksCheckBoxes;

    @FindBy(xpath = "//input[@name='fav_color']")
    private List<WebElement> colorsRadioButtons;

    @FindBy(id = "automation")
    private WebElement selectAutomation;

    @FindBy(css = "form#feedbackForm > ul > li")
    private List<WebElement> automationToolsElements;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "message")
    private WebElement messageArea;

    @FindBy(id = "submit-btn")
    private WebElement submitButton;

    private final HashMap<String, WebElement> drinksCheckBoxMap;
    private final Actions actions;


    public FormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

        this.drinksCheckBoxMap = initMap(this.drinksCheckBoxes);
        this.actions = new Actions(driver);
    }

    public FormPage enterName(String name){
        actions.moveToElement(this.name).perform();
        this.name.sendKeys(name);
        return this;
    }

    public FormPage enterPassword(String password) {
        actions.moveToElement(this.password).perform();
        this.password.sendKeys(password);
        return this;
    }

    public FormPage selectFavoriteDrink(String... options){

        for(String option : options){

            WebElement checkBoxToSelect = drinksCheckBoxMap.get(option);

            if (checkBoxToSelect != null){

                actions.moveToElement(checkBoxToSelect).perform();

                if(!checkBoxToSelect.isSelected()){
                    checkBoxToSelect.click();
                }

            }

        }

        return this;


//        System.out.println(checkBox.get(0).getDomAttribute("value"));
    }

    public FormPage selectColor(String color) {

        for (WebElement colorRadioButton : colorsRadioButtons){
            actions.scrollToElement(colorRadioButton).perform();

            if(Objects.equals(colorRadioButton.getDomAttribute("value"), color)){
                colorRadioButton.click();
                break;
            }
        }

        return this;
    }

    private HashMap<String, WebElement> initMap(List<WebElement> list){

        HashMap<String, WebElement> map = new HashMap<>();

        for (WebElement element : list){
            String value = element.getDomAttribute("value");
            map.put(value, element);
        }

        return map;
    }

    public FormPage selectDropDownAutomation(String option) {
        actions.scrollToElement(selectAutomation).perform();
        Select select = new Select(selectAutomation);
        select.selectByVisibleText(option);

        return this;
    }

    public FormPage enterEmail(String email) {
        actions.scrollToElement(this.email).perform();
        this.email.sendKeys(email);

        return this;
    }

    public FormPage enterMessage(){
        actions.scrollToElement(this.messageArea).perform();
        String inputText = automationToolsElements.size() + "\n" + findMaxLengthWord();
        messageArea.sendKeys(inputText);

        return this;
    }

    public FormPage enterMessage(String text){
        actions.scrollToElement(this.messageArea).perform();
        messageArea.sendKeys(text);

        return this;
    }

    public void submit(){
        actions.scrollToElement(this.email).perform();
        submitButton.submit();
    }

    private String findMaxLengthWord(){
        return automationToolsElements.stream()
                .map(WebElement::getText)
                .max(Comparator.comparing(String::length))
                .orElse("");
    }

}
