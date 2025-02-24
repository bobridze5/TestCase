# Тестовое задание от SimbirSoft

## Описание
Проект предназначен для автоматизированного тестирования веб-формы с использованием Selenium WebDriver и TestNG. 
Скрипт заполняет форму, отправляет её и проверяет успешность операции через появляющийся alert.

## Кейс. Работа с полями и формами
**Предусловие**:
1. Открыть браузер
2. Перейти по ссылке https://practice-automation.com/form-fields/

**Шаги**:

1. Заполнить поле <span style="color:dodgerblue">**_Name_**</span>
2. Заполнить поле <span style="color:dodgerblue">**Password**</span>
3. Из списка <span style="color:dodgerblue">**_What is your favorite drink?_**</span> выбрать <span style="color:dodgerblue">**_Milk_**</span> и <span style="color:dodgerblue">**_Coffee_**</span>
4. Из списка <span style="color:dodgerblue">**_What is your favorite color?_**</span> выбрать <span style="color:dodgerblue">_**Yellow**_</span>
5. В поле <span style="color:dodgerblue">**_Do you like automation?_**</span> выбрать любой вариант
6. Поле <span style="color:dodgerblue">**_Email_**</span> заполнить строкой формата <span style="color:dodgerblue">**_name@example.com_**</span>
7. В поле <span style="color:dodgerblue">**_Message_**</span> написать количество инструментов, описанных в пункте <span style="color:dodgerblue">**_Automation tools_**</span>

   **Дополнительный пункт повышенной сложности**
    - В поле <span style="color:dodgerblue">**_Message_**</span> дополнительно написать инструмент из списка <span style="color:dodgerblue">**_Automation tools_**</span>, содержащий
      наибольшее количество символов
8. Нажать на кнопку <span style="color:dodgerblue">**_Submit_**</span>

**Ожидаемый результат:**

Появился алерт с текстом <span style="color:dodgerblue">**_Message received!_**</span>

## Стек технологий
- **Java 17** (основной язык программирования)
- **Selenium WebDriver** (автоматизация браузера)
- **TestNG** (фреймворк для тестирования)
- **Maven** (система управления зависимостями)

## Структура проекта
```
ui-test-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pages/
│   │   │   │   ├── BasePage.java
│   │   │   │   ├── FormPage.java
│   ├── test/
│   │   ├── java/
│   │   │   ├── tests/
│   │   │   │   ├── FormTest.java
├── pom.xml
├── README.md
```


