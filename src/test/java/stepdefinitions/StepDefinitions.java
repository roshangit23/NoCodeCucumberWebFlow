package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.yaml.snakeyaml.Yaml;
import utils.DriverFactory;
import utils.WebElementUtils;

import java.io.IOException;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class StepDefinitions {

    private WebDriver driver;
    private WebElementUtils webElementUtils;

    public StepDefinitions() {
        this.driver = DriverFactory.getDriver(); // Assume DriverFactory is a utility class to get WebDriver instance
        this.webElementUtils = new WebElementUtils(driver);
    }

    private String getXPathFromYaml(String elementName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("xpaths/xpaths.yaml")) {
            Yaml yaml = new Yaml();
            Map<String, Map<String, String>> data = yaml.load(inputStream);

            for (Map.Entry<String, Map<String, String>> page : data.entrySet()) {
                Map<String, String> elements = page.getValue();
                if (elements.containsKey(elementName)) {
                    return elements.get(elementName);
                }
            }
            throw new IllegalArgumentException("Element '" + elementName + "' not found in the YAML file.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load or parse the xpaths.yaml file.", e);
        }
    }

    @When("I click the {string}")
    public void iClickThe(String elementName) {
        String xpath = getXPathFromYaml(elementName);
        webElementUtils.click(By.xpath(xpath));
    }

    @When("I enter {string} into the {string}")
    public void iEnterIntoThe(String text, String elementName) {
        String xpath = getXPathFromYaml(elementName);
        webElementUtils.enterText(By.xpath(xpath), text);
    }
   @Given("I am on the {string} page")
   public void iAmOnThePage(String elementName){
        theElementShouldBeDisplayed(elementName);
    }
    @Given("I am on the {string} section")
    public void iAmOnTheSection(String elementName){
        theElementShouldBeDisplayed(elementName);
    }
    @When("I select {string} from the {string} dropdown")
    public void iSelectFromTheDropdown(String option, String elementName) {
        String xpath = getXPathFromYaml(elementName);
        webElementUtils.selectFromDropdownByVisibleText(By.xpath(xpath), option);
    }

    @When("I check the checkbox {string}")
    public void iCheckTheCheckbox(String checkboxName) {
        String xpath = getXPathFromYaml(checkboxName);
        webElementUtils.checkCheckbox(By.xpath(xpath));
    }

    @When("I uncheck the checkbox {string}")
    public void iUncheckTheCheckbox(String checkboxName) {
        String xpath = getXPathFromYaml(checkboxName);
        webElementUtils.uncheckCheckbox(By.xpath(xpath));
    }

    @Then("The element {string} should be displayed")
    public void theElementShouldBeDisplayed(String elementName) {
        String xpath = getXPathFromYaml(elementName);
        Assert.assertTrue(webElementUtils.isElementDisplayed(By.xpath(xpath)),
                "The element with name '" + elementName + "' is not displayed.");
    }
    @Then("The element {string} should not be displayed")
    public void theElementShouldNotBeDisplayed(String elementName) {
        String xpath = getXPathFromYaml(elementName);
        Assert.assertTrue(webElementUtils.isElementNotDisplayed(By.xpath(xpath)),
                "The element with name '" + elementName + "' is displayed.");
    }
    @Then("The element {string} should be enabled")
    public void theElementShouldBeEnabled(String elementName) {
        String xpath = getXPathFromYaml(elementName);
        Assert.assertTrue(webElementUtils.isElementEnabled(By.xpath(xpath)),
                "The element with name '" + elementName + "' is not enabled.");
    }

    @Then("The element {string} should be selected")
    public void theElementShouldBeSelected(String elementName) {
        String xpath = getXPathFromYaml(elementName);
        Assert.assertTrue(webElementUtils.isElementSelected(By.xpath(xpath)),
                "The element with name '" + elementName + "' is not selected.");
    }
    @Then("The element {string} should not be selected")
    public void theElementShouldNotBeSelected(String elementName) {
        String xpath = getXPathFromYaml(elementName);
        Assert.assertTrue(webElementUtils.isElementNotSelected(By.xpath(xpath)),
                "The element with name '" + elementName + "' is selected.");
    }
    @When("I hover over the element {string}")
    public void iHoverOverTheElement(String elementName) {
        String xpath = getXPathFromYaml(elementName);
        webElementUtils.hoverOverElement(By.xpath(xpath));
    }

    @When("I double click the element {string}")
    public void iDoubleClickTheElement(String elementName) {
        String xpath = getXPathFromYaml(elementName);
        webElementUtils.doubleClick(By.xpath(xpath));
    }

    @When("I right click the element {string}")
    public void iRightClickTheElement(String elementName) {
        String xpath = getXPathFromYaml(elementName);
        webElementUtils.rightClick(By.xpath(xpath));
    }

    @When("I drag the element {string} and drop it on {string}")
    public void iDragTheElementAndDropItOn(String sourceElement, String targetElement) {
        String sourceXpath = getXPathFromYaml(sourceElement);
        String targetXpath = getXPathFromYaml(targetElement);
        webElementUtils.dragAndDrop(By.xpath(sourceXpath), By.xpath(targetXpath));
    }

    @When("I scroll to the element {string}")
    public void iScrollToTheElement(String elementName) {
        String xpath = getXPathFromYaml(elementName);
        webElementUtils.scrollToElement(By.xpath(xpath));
    }


    @When("I switch to frame with index {int}")
    public void iSwitchToFrameWithIndex(int frameIndex) {
        webElementUtils.switchToFrame(frameIndex);
    }

    @When("I switch back to the default content")
    public void iSwitchBackToTheDefaultContent() {
        webElementUtils.switchToDefaultContent();
    }
    @When("I select value {string} from the dropdown {string}")
    public void iSelectValueFromDropdown(String value, String dropdownName) {
        String xpath = getXPathFromYaml(dropdownName);
        webElementUtils.selectFromDropdownByValue(By.xpath(xpath), value);
    }

    @When("I select index {int} from the dropdown {string}")
    public void iSelectIndexFromDropdown(int index, String dropdownName) {
        String xpath = getXPathFromYaml(dropdownName);
        webElementUtils.selectFromDropdownByIndex(By.xpath(xpath), index);
    }

    @Then("The text of element {string} should be {string}")
    public void theTextOfElementShouldBe(String elementName, String expectedText) {
        String xpath = getXPathFromYaml(elementName);
        String actualText = webElementUtils.getElementText(By.xpath(xpath));
        Assert.assertEquals(actualText, expectedText,
                "The text of the element '" + elementName + "' does not match the expected text.");
    }

    @Then("The text of the input field {string} should be {string}")
    public void theTextOfInputFieldShouldBe(String elementName, String expectedText) {
        String xpath = getXPathFromYaml(elementName);
        Boolean result = webElementUtils.verifyInputFieldText(By.xpath(xpath), expectedText);
        Assert.assertTrue(result, "The text of the input field '" + elementName +
                "' does not match the expected text '" + expectedText + "'.");
    }

    @Then("The element {string} should be present")
    public void theElementShouldBePresent(String elementName) {
        String xpath = getXPathFromYaml(elementName);
        Assert.assertTrue(webElementUtils.isElementPresent(By.xpath(xpath)),
                "The element '" + elementName + "' is not present.");
    }

    @When("I click the radio button {string}")
    public void iClickTheRadioButton(String radioButtonName) {
        String xpath = getXPathFromYaml(radioButtonName);
        webElementUtils.clickRadioButton(By.xpath(xpath));
    }

    @When("I switch to frame with locator {string}")
    public void iSwitchToFrameWithLocator(String frameLocatorName) {
        String xpath = getXPathFromYaml(frameLocatorName);
        webElementUtils.switchToFrame(By.xpath(xpath));
    }

    @When("I wait for element {string} to be visible for {int} seconds")
    public void iWaitForElementToBeVisible(String elementName, int timeoutInSeconds) {
        String xpath = getXPathFromYaml(elementName);
        webElementUtils.waitForElementVisible(By.xpath(xpath), timeoutInSeconds);
    }

    @When("I get text from cell at row {int} column {int} of table {string} which should be {string}")
    public void iGetTextFromTableCell(int row, int column, String tableName, String textToVerify) {
        String xpath = getXPathFromYaml(tableName);
        String actualText = webElementUtils.getTableCellText(By.xpath(xpath), row, column);
        Assert.assertEquals(actualText, textToVerify,
                "Text from the table cell at row " + row + " and column " + column +
                        " does not match the expected text '" + textToVerify + "'.");
    }

    @Then("The row count of table {string} should be {int}")
    public void theRowCountOfTableShouldBe(String tableName, int expectedCount) {
        String xpath = getXPathFromYaml(tableName);
        int rowCount = webElementUtils.getRowCount(By.xpath(xpath));
        Assert.assertEquals(rowCount, expectedCount,
                "Row count of table '" + tableName + "' does not match the expected count.");
    }

    @Then("The column count of row {int} in table {string} should be {int}")
    public void theColumnCountOfRowInTableShouldBe(int row, String tableName, int expectedCount) {
        String xpath = getXPathFromYaml(tableName);
        int columnCount = webElementUtils.getColumnCount(By.xpath(xpath), row);
        Assert.assertEquals(columnCount, expectedCount,
                "Column count of row " + row + " in table '" + tableName +
                        "' does not match the expected count.");
    }

    @Then("I should get all data from table {string}")
    public void iShouldGetAllDataFromTable(String tableName) {
        String xpath = getXPathFromYaml(tableName);
        List<List<String>> tableData = webElementUtils.getAllTableData(By.xpath(xpath));
        // Further assertions or use of tableData can be implemented here
    }

    @When("I find a row by text {string} in table {string}")
    public void iFindRowByTextInTable(String searchText, String tableName) {
        String xpath = getXPathFromYaml(tableName);
        WebElement row = webElementUtils.findRowByCellText(By.xpath(xpath), searchText);
        // Further actions on the row can be implemented here
    }

    @Then("Cell in row {int} and column {int} of table {string} should contain text {string}")
    public void cellInTableShouldContainText(int row, int column, String tableName, String expectedText) {
        String xpath = getXPathFromYaml(tableName);
        boolean isPresent = webElementUtils.isCellTextPresent(By.xpath(xpath), row, column, expectedText);
        Assert.assertTrue(isPresent, "The cell at row " + row + " and column " + column +
                " in table '" + tableName + "' does not contain the expected text '" + expectedText + "'.");
    }

    @Then("I should get header names from table {string}")
    public void iShouldGetHeaderNamesFromTable(String tableName) {
        String xpath = getXPathFromYaml(tableName);
        List<String> headers = webElementUtils.getHeaderNames(By.xpath(xpath));
        // Further assertions or use of headers can be implemented here
    }

    @Then("Column {int} data from table {string} should be retrieved")
    public void columnDataFromTableShouldBeRetrieved(int column, String tableName) {
        String xpath = getXPathFromYaml(tableName);
        List<String> columnData = webElementUtils.getColumnData(By.xpath(xpath), column);
        // Further assertions or use of columnData can be implemented here
    }

    @Then("Column {int} in table {string} should be sorted in {string} order")
    public void columnInTableShouldBeSorted(int column, String tableName, String order) {
        String xpath = getXPathFromYaml(tableName);
        boolean isSorted = webElementUtils.isColumnSorted(By.xpath(xpath), column, order.equalsIgnoreCase("ascending"));
        Assert.assertTrue(isSorted, "Column " + column + " in table '" + tableName +
                "' is not sorted in " + order + " order as expected.");
    }

    @When("I filter data in column {int} of table {string} by text {string}")
    public void iFilterDataInTableBy(int column, String tableName, String filterText) {
        String xpath = getXPathFromYaml(tableName);
        List<WebElement> filteredRows = webElementUtils.filterTableData(By.xpath(xpath), column, filterText);
        // Further assertions or actions on filteredRows can be implemented here
    }

    @When("I edit cell in row {int}, column {int} of table {string} to text {string}")
    public void iEditCellInTable(int row, int column, String tableName, String newText) {
        String xpath = getXPathFromYaml(tableName);
        webElementUtils.editTableCell(By.xpath(xpath), row, column, newText);
    }

    @When("I accept the alert")
    public void iAcceptTheAlert() {
        webElementUtils.acceptAlert();
    }

    @Then("The alert text should be {string}")
    public void theAlertTextShouldBe(String expectedText) {
        String actualText = webElementUtils.getAlertText();
        Assert.assertEquals(actualText, expectedText,
                "The actual text of the alert does not match the expected text.");
    }
    @When("I accept the confirmation alert")
    public void iAcceptTheConfirmationAlert() {
        webElementUtils.acceptConfirm();
    }

    @When("I dismiss the confirmation alert")
    public void iDismissTheConfirmationAlert() {
        webElementUtils.dismissConfirm();
    }

    @When("I execute JavaScript {string}")
    public void iExecuteJavaScript(String script) {
        webElementUtils.executeJavaScript(script);
    }

    @When("I switch to window with url {string}")
    public void iSwitchToWindowWithHandle(String windowUrl) {
        webElementUtils.switchToWindow(windowUrl);
    }

    @When("I upload file {string} to element {string}")
    public void iUploadFileToElement(String filePath, String elementName) {
        String xpath = getXPathFromYaml(elementName);
        webElementUtils.uploadFile(By.xpath(xpath), filePath);
    }

    @Then("The attribute {string} of element {string} should be {string}")
    public void theAttributeOfElementShouldBe(String attribute, String elementName, String expectedValue) {
        String xpath = getXPathFromYaml(elementName);
        String actualValue = webElementUtils.getAttribute(By.xpath(xpath), attribute);
        Assert.assertEquals(actualValue, expectedValue,
                "The value of the attribute '" + attribute + "' for element '" + elementName +
                        "' does not match the expected value.");
    }

    @When("I close the popup {string}")
    public void iCloseThePopup(String popupName) {
        String xpath = getXPathFromYaml(popupName);
        webElementUtils.closePopup(By.xpath(xpath));
    }

    @When("I resize the browser to width {int} and height {int}")
    public void iResizeTheBrowser(int width, int height) {
        webElementUtils.resizeBrowser(width, height);
    }

    @When("I move the browser to x {int} and y {int}")
    public void iMoveTheBrowser(int x, int y) {
        webElementUtils.moveBrowser(x, y);
    }

    @When("I zoom the page to {double}")
    public void iZoomThePage(double zoomLevel) {
        webElementUtils.zoomPage(zoomLevel);
    }

    @When("I scroll by x {int} and y {int}")
    public void iScrollBy(int x, int y) {
        webElementUtils.scrollBy(x, y);
    }

    @When("I refresh the browser")
    public void iRefreshTheBrowser() {
        webElementUtils.refreshBrowser();
    }

    @When("I navigate to URL {string}")
    public void iNavigateToURL(String url) {
        webElementUtils.navigateTo(url);
    }

    @Then("The current page URL should be {string}")
    public void theCurrentPageURLShouldBe(String expectedUrl) {
        String actualUrl = webElementUtils.getCurrentPageURL();
        Assert.assertEquals(actualUrl, expectedUrl,
                "The current page URL does not match the expected URL.");
    }

    @Then("The current page title should be {string}")
    public void theCurrentPageTitleShouldBe(String expectedTitle) {
        String actualTitle = webElementUtils.getCurrentPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle,
                "The current page title does not match the expected title.");
    }

    @When("I add a cookie with name {string} and value {string}")
    public void iAddACookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        webElementUtils.addCookie(cookie);
    }

    @Then("A cookie with name {string} should exist")
    public void aCookieWithNameShouldExist(String cookieName) {
        Cookie cookie = webElementUtils.getCookie(cookieName);
        Assert.assertNotNull(cookie, "A cookie with name '" + cookieName + "' should exist, but it does not.");
    }

    @Then("A cookie with name {string} should not exist")
    public void aCookieWithNameShouldNotExist(String cookieName) {
        Cookie cookie = webElementUtils.getCookie(cookieName);
        Assert.assertNull(cookie, "A cookie with name '" + cookieName + "' should not exist, but it does.");
    }

    @When("I wait for the page to load completely")
    public void iWaitForThePageToLoadCompletely() {
        webElementUtils.waitForPageLoadComplete();
    }
    @When("I select the option {string} for the dynamic dropdown {string} with option path {string}")
    public void iSelectTheDynamicDropdownOptionFor(String value, String dropdownElement, String optionPath) {
        String inputXpath = getXPathFromYaml(dropdownElement);
        String optionXpath = getXPathFromYaml(optionPath);
        webElementUtils.selectDynamicDropdown(By.xpath(inputXpath), By.xpath(optionXpath), value);
    }

    @When("I get the CSS value {string} of element {string}")
    public String iGetTheCssValueOfElement(String cssProperty, String elementName) {
        String xpath = getXPathFromYaml(elementName);
        return webElementUtils.getCssValue(By.xpath(xpath), cssProperty);
    }

    @Then("The color of element {string} should be {string}")
    public void theColorOfElementShouldBe(String elementName, String expectedColorValue) {
        String xpath = getXPathFromYaml(elementName);
        boolean result = webElementUtils.isElementColorAsExpected(By.xpath(xpath), "color", expectedColorValue);
        Assert.assertTrue(result, "The color of element '" + elementName +
                "' does not match the expected color value '" + expectedColorValue + "'.");
    }

    @Then("I should see all options in dropdown {string}")
    public List<String> iShouldSeeAllOptionsInDropdown(String dropdownElement) {
        String xpath = getXPathFromYaml(dropdownElement);
        return webElementUtils.getAllOptionsFromDropdown(By.xpath(xpath));
    }

    @When("I delete all cookies")
    public void iDeleteAllCookies() {
        webElementUtils.deleteAllCookies();
    }

    @When("I take a screenshot of element {string} and save it as {string}")
    public void iTakeAScreenshotOfElement(String elementName, String filePath) throws IOException {
        String xpath = getXPathFromYaml(elementName);
        webElementUtils.takeElementScreenshot(By.xpath(xpath), filePath);
    }

    @When("I find the shadow DOM element {string} inside host {string}")
    public WebElement iFindTheShadowDomElement(String cssSelector, String hostElement) {
        String hostXpath = getXPathFromYaml(hostElement);
        WebElement shadowHost = driver.findElement(By.xpath(hostXpath));
        return webElementUtils.getShadowDomElement(shadowHost, cssSelector);
    }

    @When("I retrieve performance metrics")
    public Map<String, Object> iRetrievePerformanceMetrics() {
        return webElementUtils.getPerformanceMetrics();
    }

    @When("I safely click on element {string}")
    public void iSafelyClickOnElement(String elementName) {
        String xpath = getXPathFromYaml(elementName);
        webElementUtils.safeClick(By.xpath(xpath));
    }

    @When("I enter date {string} into the field {string}")
    public void iEnterDateIntoTheField(String date, String elementName) {
        String xpath = getXPathFromYaml(elementName);
        webElementUtils.enterDateInSimpleField(By.xpath(xpath), date);
    }

    @When("I select day {string}, month {string}, year {string} from the calendar at {string} using previous button at {string}, next button at {string}, month element at {string}, year element at {string}, and day elements at {string}")
    public void iSelectDateFromLinkCalendar(String day, String month, String year, String calendarLocatorXpath, String prevButtonXpath, String nextButtonXpath, String monthLocatorXpath, String yearLocatorXpath, String dayLocatorXpath) {
        By calendarLocator = By.xpath(getXPathFromYaml(calendarLocatorXpath));
        By monthLocator = By.xpath(getXPathFromYaml(monthLocatorXpath));
        By yearLocator = By.xpath(getXPathFromYaml(yearLocatorXpath));
        By previousButtonLocator = By.xpath(getXPathFromYaml(prevButtonXpath));
        By nextButtonLocator = By.xpath(getXPathFromYaml(nextButtonXpath));
        By dayLocator = By.xpath(getXPathFromYaml(dayLocatorXpath));

        String targetMonthYear = month + " " + year;
        webElementUtils.selectDateFromLinkCalendar(calendarLocator, monthLocator, yearLocator, previousButtonLocator, nextButtonLocator, targetMonthYear, dayLocator, day);
    }

    @When("I select day {string}, month {string}, year {string} from dropdown calendar at {string} with month dropdown at {string}, year dropdown at {string}, and day elements at {string}")
    public void iSelectDateFromDropdownCalendar(String day, String month, String year, String calendarLocatorXpath, String monthDropdownXpath, String yearDropdownXpath, String dayLocatorXpath) {
        By calendarLocator = By.xpath(getXPathFromYaml(calendarLocatorXpath));
        By monthDropdown = By.xpath(getXPathFromYaml(monthDropdownXpath));
        By yearDropdown = By.xpath(getXPathFromYaml(yearDropdownXpath));
        By dayLocator = By.xpath(getXPathFromYaml(dayLocatorXpath));

        webElementUtils.selectDateFromDropdownCalendar(calendarLocator, monthDropdown, month, yearDropdown, year, dayLocator, day);
    }


    @When("I select date {string} and time {string} from date time picker {string}")
    public void iSelectDateAndTimeFromDateTimePicker(String date, String time, String dateTimePickerName) {
        By dateLocator = By.xpath(getXPathFromYaml(dateTimePickerName + "_date"));
        By timeLocator = By.xpath(getXPathFromYaml(dateTimePickerName + "_time"));
        webElementUtils.selectDateTime(dateLocator, timeLocator, date, time);
    }

    @When("I select the current date from calendar with locators calendar {string}, month {string}, year {string}, previous button {string}, next button {string}, and day {string}")
    public void iSelectCurrentDateFromCalendar(String calendarLocatorXpath, String monthLocatorXpath, String yearLocatorXpath, String prevButtonXpath, String nextButtonXpath, String dayLocatorXpath) {
        By calendarLocator = By.xpath(getXPathFromYaml(calendarLocatorXpath));
        By monthLocator = By.xpath(getXPathFromYaml(monthLocatorXpath));
        By yearLocator = By.xpath(getXPathFromYaml(yearLocatorXpath));
        By previousButtonLocator = By.xpath(getXPathFromYaml(prevButtonXpath));
        By nextButtonLocator = By.xpath(getXPathFromYaml(nextButtonXpath));
        By dayLocator = By.xpath(getXPathFromYaml(dayLocatorXpath));

        webElementUtils.selectCurrentDate(calendarLocator, monthLocator, yearLocator, previousButtonLocator, nextButtonLocator, dayLocator);
    }

    @When("I select a random future date {int} days from now from calendar with locators calendar {string}, month {string}, year {string}, previous button {string}, next button {string}, and day {string}")
    public void iSelectRandomFutureDateFromCalendar(int daysInFuture, String calendarLocatorXpath, String monthLocatorXpath, String yearLocatorXpath, String prevButtonXpath, String nextButtonXpath, String dayLocatorXpath) {
        By calendarLocator = By.xpath(getXPathFromYaml(calendarLocatorXpath));
        By monthLocator = By.xpath(getXPathFromYaml(monthLocatorXpath));
        By yearLocator = By.xpath(getXPathFromYaml(yearLocatorXpath));
        By previousButtonLocator = By.xpath(getXPathFromYaml(prevButtonXpath));
        By nextButtonLocator = By.xpath(getXPathFromYaml(nextButtonXpath));
        By dayLocator = By.xpath(getXPathFromYaml(dayLocatorXpath));

        webElementUtils.selectRandomFutureDate(calendarLocator, monthLocator, yearLocator, previousButtonLocator, nextButtonLocator, dayLocator, daysInFuture);
    }

    @When("I verify date {string} is not clickable in calendar with calendar locator {string} and day locator {string}")
    public void iVerifyDateIsNotClickableInCalendar(String date, String calendarLocatorXpath, String dayLocatorXpath) {
        By calendarLocator = By.xpath(getXPathFromYaml(calendarLocatorXpath));
        By dayLocator = By.xpath(getXPathFromYaml(dayLocatorXpath));

        webElementUtils.verifyDateNotClickable(calendarLocator, dayLocator, date);
    }

    @When("I select date range from {string} to {string} in calendar with calendar locator {string}, start day locator {string}, and end day locator {string}")
    public void iSelectDateRangeInCalendar(String startDate, String endDate, String calendarLocatorXpath, String startDayLocatorXpath, String endDayLocatorXpath) {
        By calendarLocator = By.xpath(getXPathFromYaml(calendarLocatorXpath));
        By startDayLocator = By.xpath(getXPathFromYaml(startDayLocatorXpath));
        By endDayLocator = By.xpath(getXPathFromYaml(endDayLocatorXpath));

        webElementUtils.selectDateRange(calendarLocator, startDayLocator, endDayLocator, startDate, endDate);
    }

    @When("I select date by day of current week {string} in calendar with calendar locator {string} and day locator {string}")
    public void iSelectDateByDayOfCurrentWeekInCalendar(String dayOfWeek, String calendarLocatorXpath, String dayLocatorXpath) {
        By calendarLocator = By.xpath(getXPathFromYaml(calendarLocatorXpath));
        By dayLocator = By.xpath(getXPathFromYaml(dayLocatorXpath));

        DayOfWeek targetDay = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
        webElementUtils.selectDateByDayOfCurrentWeek(calendarLocator, dayLocator, targetDay);
    }

}



