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
import java.util.stream.Collectors;

/**
 * Страница формы для тестирования ввода данных.
 *
 */
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

    /**
     * Пользователь вводит имя в форму.
     *
     * @param name Имя пользователя.
     * @return {@link FormPage}.
     */
    public FormPage enterName(String name){
        actions.moveToElement(this.name).perform();
        this.name.sendKeys(name);
        return this;
    }

    /**
     * Пользователь вводит пароль в форму.
     *
     * @param password Пароль пользователя.
     * @return {@link FormPage}.
     */
    public FormPage enterPassword(String password) {
        actions.moveToElement(this.password).perform();
        this.password.sendKeys(password);
        return this;
    }

    /**
     * Пользователь выбирает любимые напитки.
     *
     * @param options Напитки, выбранные пользователем.
     * @return {@link FormPage}.
     */
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
    }

    /**
     * Пользователь выбирает цвет в форме.
     *
     * @param color Название цвета.
     * @return {@link FormPage}.
     */
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

    /**
     * Пользователь выбирает вариант из выпадающего меню (про автоматизацию).
     *
     * @param option Вариант из выпадающего меню.
     * @return {@link FormPage}.
     */
    public FormPage selectDropDownAutomation(String option) {
        actions.scrollToElement(selectAutomation).perform();
        Select automationSelect = new Select(selectAutomation);
        automationSelect.selectByVisibleText(option);

        return this;
    }

    /**
     * Пользователь вводит электронную почту.
     *
     * @param email Адрес электронной почты.
     * @return {@link FormPage}.
     */
    public FormPage enterEmail(String email) {
        actions.scrollToElement(this.email).perform();
        this.email.sendKeys(email);

        return this;
    }

    /**
     * Пользователь вводит сообщение в форму, указывая количество элементов в списке Automations Tools
     * и на следующей строке вводит название инструмента, которое имеет наибольшее количество символов.
     *
     * @return {@link FormPage}.
     */
    public FormPage enterMessage(){
        actions.scrollToElement(this.messageArea).perform();
        String inputText = automationToolsElements.size() + "\n" + findMaxLengthWord();
        messageArea.sendKeys(inputText);

        return this;
    }

    /**
     * Пользователь вводит произвольный текст в форму.
     *
     * @param text Сообщение пользователя
     * @return {@link FormPage}
     */
    public FormPage enterMessage(String text){
        actions.scrollToElement(this.messageArea).perform();
        messageArea.sendKeys(text);

        return this;
    }

    /**
     * Пользователь нажимает на кнопку <b><i>Submit</i></b>.
     */
    public void submit(){
        actions.scrollToElement(this.email).perform();
        submitButton.submit();
    }

    /**
     * Находит слово максимальной длины из списка "Automations Tools".
     * @return Слово максимальной длины, либо пустую строку, если список пуст.
     */
    private String findMaxLengthWord(){
        return automationToolsElements.stream()
                .map(WebElement::getText)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    /**
     * Преобразует список элементов в словарь.
     *
     * @param list Массив {@link WebElement}, который требуется преобразовать.
     * @return возвращает {@link HashMap}, где ключ - значение атрибута "value", а значение - {@link WebElement}
     */
    private HashMap<String, WebElement> initMap(List<WebElement> list){
        return list.stream()
                .collect(Collectors.toMap(
                        element -> element.getDomAttribute("value"),
                        element -> element,
                        (existing, replacement) -> existing,
                        HashMap::new
                ));
    }

}
