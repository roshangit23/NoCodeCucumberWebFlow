package utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.*;
import java.util.stream.Collectors;

public class WebElementUtils {

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(WebElementUtils.class);

    public WebElementUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        try {
            driver.findElement(locator).click();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
        } catch (ElementClickInterceptedException e) {
            logger.error("Element click intercepted for locator: " + locator, e);
            Assert.fail("Element click intercepted for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error clicking on element: " + locator, e);
            Assert.fail("Error clicking on element: " + locator);
        }
    }

    public void enterText(By locator, String text) {
        try {
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error entering text in element: " + locator, e);
            Assert.fail("Error entering text in element: " + locator);
        }
    }

    public void selectFromDropdownByVisibleText(By locator, String visibleText) {
        try {
            new Select(driver.findElement(locator)).selectByVisibleText(visibleText);
        } catch (NoSuchElementException e) {
            logger.error("Dropdown not found for locator: " + locator, e);
            Assert.fail("Dropdown not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error selecting from dropdown by visible text: " + locator, e);
            Assert.fail("Error selecting from dropdown by visible text: " + locator);
        }
    }

    public void selectFromDropdownByValue(By locator, String value) {
        try {
            new Select(driver.findElement(locator)).selectByValue(value);
        } catch (NoSuchElementException e) {
            logger.error("Dropdown not found for locator: " + locator, e);
            Assert.fail("Dropdown not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error selecting from dropdown by value: " + locator, e);
            Assert.fail("Error selecting from dropdown by value: " + locator);
        }
    }

    public void selectFromDropdownByIndex(By locator, int index) {
        try {
            new Select(driver.findElement(locator)).selectByIndex(index);
        } catch (NoSuchElementException e) {
            logger.error("Dropdown not found for locator: " + locator, e);
            Assert.fail("Dropdown not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error selecting from dropdown by index: " + locator, e);
            Assert.fail("Error selecting from dropdown by index: " + locator);
        }
    }

    public String getElementText(By locator) {
        try {
            return driver.findElement(locator).getText();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
            return "";
        } catch (Exception e) {
            logger.error("Error getting text from element: " + locator, e);
            Assert.fail("Error getting text from element: " + locator);
            return "";
        }
    }

    public boolean verifyInputFieldText(By locator, String expectedText) {
        try {
            WebElement inputField = driver.findElement(locator);
            String actualText = inputField.getAttribute("value");
            return actualText.equals(expectedText);
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error in verifying input field text: " + locator, e);
            Assert.fail("Error in verifying input field text: " + locator);
        }
        return false;
    }

    public void checkCheckbox(By locator) {
        try {
            WebElement checkbox = driver.findElement(locator);
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        } catch (NoSuchElementException e) {
            logger.error("Checkbox not found for locator: " + locator, e);
            Assert.fail("Checkbox not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error checking checkbox: " + locator, e);
            Assert.fail("Error checking checkbox: " + locator);
        }
    }

    public void uncheckCheckbox(By locator) {
        try {
            WebElement checkbox = driver.findElement(locator);
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        } catch (NoSuchElementException e) {
            logger.error("Checkbox not found for locator: " + locator, e);
            Assert.fail("Checkbox not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error unchecking checkbox: " + locator, e);
            Assert.fail("Error unchecking checkbox: " + locator);
        }
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
            return false;
        } catch (Exception e) {
            logger.error("Error checking if element is displayed: " + locator, e);
            Assert.fail("Error checking if element is displayed: " + locator);
            return false;
        }
    }

    public boolean isElementNotDisplayed(By locator) {
        try {
            return !driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            // Element not present is also considered as not displayed
            return true;
        } catch (Exception e) {
            logger.error("Error checking if element is not displayed: " + locator, e);
            return true; // Any error while checking is treated as not displayed for safety
        }
    }

    public boolean isElementEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
            return false;
        } catch (Exception e) {
            logger.error("Error checking if element is enabled: " + locator, e);
            Assert.fail("Error checking if element is enabled: " + locator);
            return false;
        }
    }

    public boolean isElementSelected(By locator) {
        try {
            return driver.findElement(locator).isSelected();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
            return false;
        } catch (Exception e) {
            logger.error("Error checking if element is selected: " + locator, e);
            Assert.fail("Error checking if element is selected: " + locator);
            return false;
        }
    }
    public boolean isElementNotSelected(By locator) {
        try {
            return !driver.findElement(locator).isSelected();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
            return false;
        } catch (Exception e) {
            logger.error("Error checking if element is not selected: " + locator, e);
            Assert.fail("Error checking if element is not selected: " + locator);
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
            return false;
        } catch (Exception e) {
            logger.error("Error checking if element is  present: " + locator, e);
            Assert.fail("Error checking if element is present: " + locator);
            return false;
        }
    }

    public void clickRadioButton(By locator) {
        try {
            WebElement radioButton = driver.findElement(locator);
            if (!radioButton.isSelected()) {
                radioButton.click();
            }
        } catch (NoSuchElementException e) {
            logger.error("Radio button not found for locator: " + locator, e);
            Assert.fail("Radio button not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error clicking radio button: " + locator, e);
            Assert.fail("Error clicking radio button: " + locator);
        }
    }

    public String getTableCellText(By tableLocator, int row, int column) {
        try {
            WebElement table = driver.findElement(tableLocator);
            WebElement cell = table.findElement(By.xpath(".//tbody/tr[" + row + "]/td[" + column + "]"));
            System.out.println("cell text is "+cell.getText());
            return cell.getText();
        } catch (NoSuchElementException e) {
            logger.error("Table cell not found for locator: " + tableLocator, e);
            Assert.fail("Table cell not found for locator: " + tableLocator);
            return "";
        } catch (Exception e) {
            logger.error("Error getting table cell text: " + tableLocator, e);
            Assert.fail("Error getting table cell text: " + tableLocator);
            return "";
        }
    }

    public int getRowCount(By tableLocator) {
        try {
            WebElement table = driver.findElement(tableLocator);
            return table.findElements(By.tagName("tr")).size();
        } catch (NoSuchElementException e) {
            logger.error("Table not found for locator: " + tableLocator, e);
            Assert.fail("Table not found for locator: " + tableLocator);
            return 0;
        } catch (Exception e) {
            logger.error("Error getting row count: " + tableLocator, e);
            Assert.fail("Error getting row count: " + tableLocator);
            return 0;
        }
    }

    public int getColumnCount(By tableLocator, int row) {
        try {
            WebElement tableRow = driver.findElement(tableLocator).findElements(By.tagName("tr")).get(row);
            return tableRow.findElements(By.tagName("td")).size();
        } catch (NoSuchElementException e) {
            logger.error("Table row not found for locator: " + tableLocator, e);
            Assert.fail("Table row not found for locator: " + tableLocator);
            return 0;
        } catch (Exception e) {
            logger.error("Error getting column count: " + tableLocator, e);
            Assert.fail("Error getting column count: " + tableLocator);
            return 0;
        }
    }
    public List<List<String>> getAllTableData(By tableLocator) {
        try {
            List<List<String>> tableData = new ArrayList<>();
            List<WebElement> rows = driver.findElement(tableLocator).findElements(By.tagName("tr"));

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                List<String> rowData = new ArrayList<>();
                for (WebElement cell : cells) {
                    rowData.add(cell.getText());
                }
                tableData.add(rowData);
            }
            return tableData;
        } catch (NoSuchElementException e) {
            logger.error("Table not found for locator: " + tableLocator, e);
            Assert.fail("Table not found for locator: " + tableLocator);
            return Collections.emptyList();
        } catch (Exception e) {
            logger.error("Error getting all table data: " + tableLocator, e);
            Assert.fail("Error getting all table data: " + tableLocator);
            return Collections.emptyList();
        }
    }

    public WebElement findRowByCellText(By tableLocator, String searchText) {
        try {
            List<WebElement> rows = driver.findElement(tableLocator).findElements(By.tagName("tr"));
            for (WebElement row : rows) {
                if (row.getText().contains(searchText)) {
                    return row;
                }
            }
            return null;
        } catch (NoSuchElementException e) {
            logger.error("Table not found for locator: " + tableLocator, e);
            Assert.fail("Table not found for locator: " + tableLocator);
            return null;
        } catch (Exception e) {
            logger.error("Error finding row by cell text: " + tableLocator, e);
            Assert.fail("Error finding row by cell text: " + tableLocator);
            return null;
        }
    }
    public boolean isCellTextPresent(By tableLocator, int row, int column, String expectedText) {
        try {
            WebElement cell = driver.findElement(tableLocator)
                    .findElements(By.tagName("tr")).get(row)
                    .findElements(By.tagName("td")).get(column);
            return cell.getText().contains(expectedText);
        } catch (NoSuchElementException e) {
            logger.error("Table cell not found for locator: " + tableLocator, e);
            Assert.fail("Table cell not found for locator: " + tableLocator);
            return false;
        } catch (Exception e) {
            logger.error("Error checking cell text: " + tableLocator, e);
            Assert.fail("Error checking cell text: " + tableLocator);
            return false;
        }
    }

    public List<String> getHeaderNames(By tableLocator) {
        try {
            List<String> headers = new ArrayList<>();
            WebElement headerRow = driver.findElement(tableLocator).findElement(By.tagName("thead")).findElement(By.tagName("tr"));
            List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
            for (WebElement headerCell : headerCells) {
                headers.add(headerCell.getText());
            }
            return headers;
        } catch (NoSuchElementException e) {
            logger.error("Table header not found for locator: " + tableLocator, e);
            Assert.fail("Table header not found for locator: " + tableLocator);
            return Collections.emptyList();
        } catch (Exception e) {
            logger.error("Error getting header names: " + tableLocator, e);
            Assert.fail("Error getting header names: " + tableLocator);
            return Collections.emptyList();
        }
    }

    public List<String> getColumnData(By tableLocator, int column) {
        try {
            List<String> columnData = new ArrayList<>();
            List<WebElement> rows = driver.findElement(tableLocator).findElements(By.tagName("tr"));

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (column < cells.size()) {
                    columnData.add(cells.get(column).getText());
                }
            }
            return columnData;
        } catch (NoSuchElementException e) {
            logger.error("Table column not found for locator: " + tableLocator, e);
            Assert.fail("Table column not found for locator: " + tableLocator);
            return Collections.emptyList();
        } catch (Exception e) {
            logger.error("Error getting column data: " + tableLocator, e);
            Assert.fail("Error getting column data: " + tableLocator);
            return Collections.emptyList();
        }
    }

    public boolean isColumnSorted(By tableLocator, int column, boolean ascending) {
        try {
            List<String> columnData = getColumnData(tableLocator, column);
            List<String> sortedData = new ArrayList<>(columnData);
            if (ascending) {
                Collections.sort(sortedData);
            } else {
                sortedData.sort(Collections.reverseOrder());
            }
            return columnData.equals(sortedData);
        }
        catch (NoSuchElementException e) {
            logger.error("Table not found for locator: " + tableLocator, e);
            Assert.fail("Table not found for locator: " + tableLocator);
            return false;
        }
        catch (Exception e) {
            logger.error("Error checking if column is sorted: " + tableLocator, e);
            Assert.fail("Error checking if column is sorted: " + tableLocator);
            return false;
        }
    }

    public List<WebElement> filterTableData(By tableLocator, int column, String filterText) {
        try {
            List<WebElement> filteredRows = new ArrayList<>();
            List<WebElement> rows = driver.findElement(tableLocator).findElements(By.tagName("tr"));

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (column < cells.size() && cells.get(column).getText().contains(filterText)) {
                    filteredRows.add(row);
                }
            }
            return filteredRows;
        } catch (NoSuchElementException e) {
            logger.error("Table not found for filtering: " + tableLocator, e);
            Assert.fail("Table not found for filtering: " + tableLocator);
            return Collections.emptyList();
        } catch (Exception e) {
            logger.error("Error filtering table data: " + tableLocator, e);
            Assert.fail("Error filtering table data: " + tableLocator);
            return Collections.emptyList();
        }
    }

    public void editTableCell(By tableLocator, int row, int column, String newText) {
        try {
            WebElement cell = driver.findElement(tableLocator)
                    .findElements(By.tagName("tr")).get(row)
                    .findElements(By.tagName("td")).get(column);
            cell.click(); // Adjust according to how the table enters edit mode
            cell.clear();
            cell.sendKeys(newText);
        } catch (NoSuchElementException e) {
            logger.error("Table cell not found for editing: " + tableLocator, e);
            Assert.fail("Table cell not found for editing: " + tableLocator);
        } catch (Exception e) {
            logger.error("Error editing table cell: " + tableLocator, e);
            Assert.fail("Error editing table cell: " + tableLocator);
        }
    }


    public void switchToFrame(By frameLocator) {
        try {
            driver.switchTo().frame(driver.findElement(frameLocator));
        } catch (NoSuchElementException e) {
            logger.error("Frame not found for locator: " + frameLocator, e);
            Assert.fail("Frame not found for locator: " + frameLocator);
        } catch (Exception e) {
            logger.error("Error switching to frame: " + frameLocator, e);
            Assert.fail("Error switching to frame: " + frameLocator);
        }
    }

    public void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            logger.error("Error switching to default content", e);
            Assert.fail("Error switching to default content");
        }
    }

    public void hoverOverElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error hovering over element: " + locator, e);
            Assert.fail("Error hovering over element: " + locator);
        }
    }

    public void doubleClick(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.doubleClick(element).build().perform();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error performing double click: " + locator, e);
            Assert.fail("Error performing double click: " + locator);
        }
    }

    public void rightClick(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.contextClick(element).build().perform();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error performing right click: " + locator, e);
            Assert.fail("Error performing right click: " + locator);
        }
    }

    public void dragAndDrop(By sourceLocator, By targetLocator) {
        try {
            WebElement source = driver.findElement(sourceLocator);
            WebElement target = driver.findElement(targetLocator);
            Actions actions = new Actions(driver);
            actions.dragAndDrop(source, target).build().perform();
        } catch (NoSuchElementException e) {
            logger.error("Source or target element not found for locators: " + sourceLocator + ", " + targetLocator, e);
            Assert.fail("Source or target element not found for locators: " + sourceLocator + ", " + targetLocator);
        } catch (Exception e) {
            logger.error("Error performing drag and drop: " + sourceLocator + ", " + targetLocator, e);
            Assert.fail("Error performing drag and drop: " + sourceLocator + ", " + targetLocator);
        }
    }

    public void scrollToElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error scrolling to element: " + locator, e);
            Assert.fail("Error scrolling to element: " + locator);
        }
    }

    public WebElement findElementInList(By locator, String text) {
        try {
            return driver.findElements(locator).stream()
                    .filter(element -> element.getText().equals(text))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Element with text " + text + " not found in list"));
        } catch (NoSuchElementException e) {
            logger.error("Element with text " + text + " not found in list: " + locator, e);
            Assert.fail("Element with text " + text + " not found in list: " + locator);
            throw e;
        } catch (Exception e) {
            logger.error("Error finding element in list: " + locator, e);
            Assert.fail("Error finding element in list: " + locator);
            throw e;
        }
    }

    public void waitForElementVisible(By locator, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for visibility of element: " + locator, e);
            Assert.fail("Timeout waiting for visibility of element: " + locator);
        } catch (Exception e) {
            logger.error("Error waiting for visibility of element: " + locator, e);
            Assert.fail("Error waiting for visibility of element: " + locator);
        }
    }

    public String getAlertText() {
        try {
            return driver.switchTo().alert().getText();
        } catch (NoAlertPresentException e) {
            logger.error("No alert present", e);
            Assert.fail("No alert present");
            return null;
        } catch (Exception e) {
            logger.error("Error in getting alert text", e);
            Assert.fail("Error in getting alert text");
            return null;
        }
    }

    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            logger.error("No alert present to accept", e);
            Assert.fail("No alert present to accept");
        } catch (Exception e) {
            logger.error("Error accepting alert", e);
            Assert.fail("Error accepting alert");
        }
    }
    public void acceptConfirm() {
        try {
            Alert confirmAlert = driver.switchTo().alert();
            confirmAlert.accept(); // Clicks on 'OK' in a confirmation alert
        } catch (NoAlertPresentException e) {
            logger.error("No confirmation alert present to accept", e);
            Assert.fail("No confirmation alert present to accept");
        } catch (Exception e) {
            logger.error("Error accepting confirmation alert", e);
            Assert.fail("Error accepting confirmation alert");
        }
    }

    public void dismissConfirm() {
        try {
            Alert confirmAlert = driver.switchTo().alert();
            confirmAlert.dismiss(); // Clicks on 'Cancel' in a confirmation alert
        } catch (NoAlertPresentException e) {
            logger.error("No confirmation alert present to dismiss", e);
            Assert.fail("No confirmation alert present to dismiss");
        } catch (Exception e) {
            logger.error("Error dismissing confirmation alert", e);
            Assert.fail("Error dismissing confirmation alert");
        }
    }

    public void executeJavaScript(String script, Object... args) {
        try {
            ((JavascriptExecutor) driver).executeScript(script, args);
        } catch (Exception e) {
            logger.error("Error executing JavaScript", e);
            Assert.fail("Error executing JavaScript");
        }
    }

    public void switchToWindow(String windowUrl) {
        try {
            Set<String> allWindowHandles = driver.getWindowHandles();

            for (String handle : allWindowHandles) {
                // Switch to the window
                driver.switchTo().window(handle);
                // Check if the window's title matches the target window's title
                if (driver.getCurrentUrl().equals(windowUrl)) {
                    break; // Break the loop once the correct window is found
                }
            }
        } catch (NoSuchWindowException e) {
            logger.error("No such window with url: " + windowUrl, e);
            Assert.fail("No such window with url: " + windowUrl);
        } catch (Exception e) {
            logger.error("Error switching to window with url: " + windowUrl, e);
            Assert.fail("Error switching to window with url: " + windowUrl);
        }
    }

    public void uploadFile(By locator, String filePath) {
        try {
            driver.findElement(locator).sendKeys(filePath);
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error uploading file to: " + locator, e);
            Assert.fail("Error uploading file to: " + locator);
        }
    }

    public String getAttribute(By locator, String attribute) {
        try {
            return driver.findElement(locator).getAttribute(attribute);
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
            return null;
        } catch (Exception e) {
            logger.error("Error getting attribute from element: " + locator, e);
            Assert.fail("Error getting attribute from element: " + locator);
            return null;
        }
    }

    public void closePopup(By closeButtonLocator) {
        try {
            driver.findElement(closeButtonLocator).click();
        } catch (NoSuchElementException e) {
            logger.error("Close button not found for locator: " + closeButtonLocator, e);
            Assert.fail("Close button not found for locator: " + closeButtonLocator);
        } catch (Exception e) {
            logger.error("Error closing popup for locator: " + closeButtonLocator, e);
            Assert.fail("Error closing popup for locator: " + closeButtonLocator);
        }
    }

    public void resizeBrowser(int width, int height) {
        try {
            driver.manage().window().setSize(new Dimension(width, height));
        } catch (Exception e) {
            logger.error("Error resizing browser", e);
            Assert.fail("Error resizing browser");
        }
    }

    public void moveBrowser(int x, int y) {
        try {
            driver.manage().window().setPosition(new Point(x, y));
        } catch (Exception e) {
            logger.error("Error moving browser", e);
            Assert.fail("Error moving browser");
        }
    }

    public void zoomPage(double zoomLevel) {
        try {
            executeJavaScript("document.body.style.zoom='" + zoomLevel + "'");
        } catch (Exception e) {
            logger.error("Error zooming page", e);
            Assert.fail("Error zooming page");
        }
    }

    public void scrollBy(int x, int y) {
        try {
            executeJavaScript("window.scrollBy(" + x + "," + y + ")");
        } catch (Exception e) {
            logger.error("Error scrolling by coordinates", e);
            Assert.fail("Error scrolling by coordinates");
        }
    }

    public void refreshBrowser() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            logger.error("Error refreshing browser", e);
            Assert.fail("Error refreshing browser");
        }
    }

    public void navigateTo(String url) {
        try {
            driver.navigate().to(url);
        } catch (Exception e) {
            logger.error("Error navigating to URL: " + url, e);
            Assert.fail("Error navigating to URL: " + url);
        }
    }

    public String getCurrentPageURL() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            logger.error("Error getting current page URL", e);
            Assert.fail("Error getting current page URL");
            return null;
        }
    }

    public String getCurrentPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            logger.error("Error getting current page title", e);
            Assert.fail("Error getting current page title");
            return null;
        }
    }

    public void waitForCondition(ExpectedCondition<?> condition, int timeout) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(condition);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for condition", e);
            Assert.fail("Timeout waiting for condition");
        } catch (Exception e) {
            logger.error("Error waiting for condition", e);
            Assert.fail("Error waiting for condition");
        }
    }

    public void addCookie(Cookie cookie) {
        try {
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            logger.error("Error adding cookie", e);
            Assert.fail("Error adding cookie");
        }
    }

    public Cookie getCookie(String name) {
        try {
            return driver.manage().getCookieNamed(name);
             }
        catch (Exception e) {
         logger.error("Error getting cookie: " + name, e);
         Assert.fail("Error getting cookie: " + name);
         return null;
    }
}

    public void waitForPageLoadComplete() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } catch (TimeoutException e) {
            logger.error("Timed out waiting for page load to complete", e);
            Assert.fail("Timed out waiting for page load to complete");
        } catch (Exception e) {
            logger.error("Error waiting for page load to complete", e);
            Assert.fail("Error waiting for page load to complete");
        }
    }

    public void switchToFrame(int index) {
        try {
            driver.switchTo().frame(index);
        } catch (NoSuchFrameException e) {
            logger.error("No such frame: " + index, e);
            Assert.fail("No such frame: " + index);
        } catch (Exception e) {
            logger.error("Error switching to frame: " + index, e);
            Assert.fail("Error switching to frame: " + index);
        }
    }

    public void selectDynamicDropdown(By inputLocator, By optionLocator, String value) {
        enterText(inputLocator, value);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
        WebElement optionToSelect = findElementInList(optionLocator, value);
        if (optionToSelect != null) {
            optionToSelect.click();
        } else {
            logger.error("Option with text " + value + " not found in dropdown");
            Assert.fail("Option with text " + value + " not found in dropdown");
        }
    }

    public String getCssValue(By locator, String cssProperty) {
        try {
            return driver.findElement(locator).getCssValue(cssProperty);
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
            return null;
        } catch (Exception e) {
            logger.error("Error getting CSS value for: " + locator, e);
            Assert.fail("Error getting CSS value for: " + locator);
            return null;
        }
    }

    public boolean isElementColorAsExpected(By locator, String cssProperty, String expectedColorValue) {
        String colorValue = getCssValue(locator, cssProperty);
        return colorValue != null && colorValue.equals(expectedColorValue);
    }

    public List<String> getAllOptionsFromDropdown(By locator) {
        try {
            Select dropdown = new Select(driver.findElement(locator));
            return dropdown.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
        } catch (NoSuchElementException e) {
            logger.error("Dropdown not found for locator: " + locator, e);
            Assert.fail("Dropdown not found for locator: " + locator);
            return Collections.emptyList();
        } catch (Exception e) {
            logger.error("Error getting all options from dropdown: " + locator, e);
            Assert.fail("Error getting all options from dropdown: " + locator);
            return Collections.emptyList();
        }
    }

    public void deleteAllCookies() {
        try {
            driver.manage().deleteAllCookies();
        } catch (Exception e) {
            logger.error("Error deleting all cookies", e);
            Assert.fail("Error deleting all cookies");
        }
    }

    public void takeElementScreenshot(By locator, String filePath) throws IOException {
        try {
            WebElement element = driver.findElement(locator);
            scrollToElement(locator);
            File screenshot = element.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));
        } catch (NoSuchElementException e) {
            logger.error("Element not found for screenshot: " + locator, e);
            Assert.fail("Element not found for screenshot: " + locator);
        } catch (IOException e) {
            logger.error("Error taking element screenshot", e);
            Assert.fail("Error taking element screenshot");
        }
    }

    public WebElement getShadowDomElement(WebElement shadowHost, String cssSelector) {
        try {
            WebElement shadowRoot = (WebElement) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].shadowRoot", shadowHost);
            return shadowRoot.findElement(By.cssSelector(cssSelector));
        } catch (NoSuchElementException e) {
            logger.error("Shadow element not found: " + cssSelector, e);
            Assert.fail("Shadow element not found: " + cssSelector);
            return null;
        } catch (Exception e) {
            logger.error("Error finding shadow DOM element", e);
            Assert.fail("Error finding shadow DOM element");
            return null;
        }
    }

    public Map<String, Object> getPerformanceMetrics() {
        try {
            return (Map<String, Object>) ((JavascriptExecutor) driver)
                    .executeScript("return window.performance.getEntries()[0]");
        } catch (Exception e) {
            logger.error("Error getting performance metrics", e);
            Assert.fail("Error getting performance metrics");
            return Collections.emptyMap();
        }
    }

    public void safeClick(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            element.click();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for safe click: " + locator, e);
            Assert.fail("Element not found for safe click: " + locator);
        } catch (Exception e) {
            logger.error("Error performing safe click on element: " + locator, e);
            Assert.fail("Error performing safe click on element: " + locator);
        }
    }

    //calendar util functions
    public void enterDateInSimpleField(By locator, String date) {
        try {
            WebElement dateField = driver.findElement(locator);
            dateField.clear();
            dateField.sendKeys(date);
        } catch (NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator, e);
            Assert.fail("Element not found for locator: " + locator);
        } catch (Exception e) {
            logger.error("Error entering date in simple field: " + locator, e);
            Assert.fail("Error entering date in simple field: " + locator);
        }
    }

    public void selectDateFromLinkCalendar(By calendarLocator, By monthLocator, By yearLocator, By previousButtonLocator, By nextButtonLocator, String targetMonthYear, By dayLocator, String day) {
        try {
            driver.findElement(calendarLocator).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(monthLocator));
            wait.until(ExpectedConditions.visibilityOfElementLocated(yearLocator));

            while (true) {
                String currentMonth = driver.findElement(monthLocator).getText();
                String currentYear = driver.findElement(yearLocator).getText();
                String currentMonthYear = currentMonth + " " + currentYear;

                if (currentMonthYear.equals(targetMonthYear)) {
                    break;
                } else if (isTargetDateBeforeCurrentDate(currentMonthYear, targetMonthYear)) {
                    driver.findElement(previousButtonLocator).click();
                } else {
                    driver.findElement(nextButtonLocator).click();
                }
                wait.until(ExpectedConditions.visibilityOfElementLocated(monthLocator));
                wait.until(ExpectedConditions.visibilityOfElementLocated(yearLocator));
            }

            List<WebElement> days = driver.findElements(dayLocator);
            for (WebElement d : days) {
                if (d.getText().equals(day)) {
                    d.click();
                    break;
                }
            }
        }catch (NoSuchElementException e) {
            logger.error("Element not found in selectDateFromLinkCalendar", e);
            Assert.fail("Element not found in selectDateFromLinkCalendar");
        } catch (TimeoutException e) {
            logger.error("Timeout in selectDateFromLinkCalendar", e);
            Assert.fail("Timeout in selectDateFromLinkCalendar");
        } catch (Exception e) {
            logger.error("Error in selectDateFromLinkCalendar", e);
            Assert.fail("Error in selectDateFromLinkCalendar");
        }
    }

    private boolean isTargetDateBeforeCurrentDate(String current, String target) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
        LocalDate currentDate = LocalDate.parse(current, formatter);
        LocalDate targetDate = LocalDate.parse(target, formatter);
        return targetDate.isBefore(currentDate);
    }


    public void selectDateFromDropdownCalendar(By calendarLocator, By monthDropdown, String month, By yearDropdown, String year, By dayLocator, String day) {
        try {
            driver.findElement(calendarLocator).click();
            new Select(driver.findElement(monthDropdown)).selectByVisibleText(month);
            new Select(driver.findElement(yearDropdown)).selectByVisibleText(year);
            List<WebElement> days = driver.findElements(dayLocator);
            for (WebElement d : days) {
                if (d.getText().equals(day)) {
                    d.click();
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            logger.error("Element not found in selectDateFromDropdownCalendar", e);
            Assert.fail("Element not found in selectDateFromDropdownCalendar");
        } catch (Exception e) {
            logger.error("Error in selectDateFromDropdownCalendar", e);
            Assert.fail("Error in selectDateFromDropdownCalendar");
        }
    }

    public void selectDateTime(By dateLocator, By timeLocator, String date, String time) {
        try {
            enterDateInSimpleField(dateLocator, date); // Reuse the updated method with exception handling
            WebElement timeField = driver.findElement(timeLocator);
            timeField.clear();
            timeField.sendKeys(time);
        } catch (NoSuchElementException e) {
            logger.error("Element not found in selectDateTime", e);
            Assert.fail("Element not found in selectDateTime");
        } catch (Exception e) {
            logger.error("Error in selectDateTime", e);
            Assert.fail("Error in selectDateTime");
        }
    }

    public void selectCurrentDate(By calendarLocator, By monthLocator, By yearLocator, By previousButtonLocator, By nextButtonLocator, By dayLocator) {
        try {
            LocalDate today = LocalDate.now();
            String day = String.valueOf(today.getDayOfMonth());
            String targetMonthYear = today.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
            selectDateFromLinkCalendar(calendarLocator, monthLocator, yearLocator, previousButtonLocator, nextButtonLocator, targetMonthYear, dayLocator, day);
        } catch (Exception e) {
            logger.error("Error in selectCurrentDate", e);
            Assert.fail("Error in selectCurrentDate");
        }
    }
    public void selectRandomFutureDate(By calendarLocator, By monthLocator, By yearLocator, By previousButtonLocator, By nextButtonLocator, By dayLocator, int daysInFuture) {
        try {
            LocalDate futureDate = LocalDate.now().plusDays(daysInFuture);
            String day = String.valueOf(futureDate.getDayOfMonth());
            String monthYear = futureDate.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
            selectDateFromLinkCalendar(calendarLocator, monthLocator, yearLocator, previousButtonLocator, nextButtonLocator, monthYear, dayLocator, day);
        } catch (Exception e) {
            logger.error("Error in selectRandomFutureDate", e);
            Assert.fail("Error in selectRandomFutureDate");
        }
    }

    public void verifyDateNotClickable(By calendarLocator, By dayLocator, String day) {
        try {
            driver.findElement(calendarLocator).click();
            List<WebElement> days = driver.findElements(dayLocator);
            for (WebElement d : days) {
                if (d.getText().equals(day)) {
                    if (!d.isEnabled() || !isElementClickable(d)) {
                        logger.info("Verified: The date " + day + " is not clickable.");
                        return;
                    } else {
                        Assert.fail("The date " + day + " is clickable, but it should not be.");
                    }
                }
            }
            Assert.fail("The date " + day + " was not found in the calendar.");
        } catch (NoSuchElementException e) {
            logger.error("Element not found in verifyDateNotClickable", e);
            Assert.fail("Element not found in verifyDateNotClickable");
        } catch (Exception e) {
            logger.error("Error in verifyDateNotClickable", e);
            Assert.fail("Error in verifyDateNotClickable");
        }
    }

    public boolean isElementClickable(WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            logger.error("Element is not clickable", e);
            Assert.fail("Element is not clickable");
            return false;
        } catch (Exception e) {
            logger.error("Error in isElementClickable", e);
            Assert.fail("Error in isElementClickable");
        }
        return false;
    }

    public void selectDateRange(By calendarLocator, By startDayLocator, By endDayLocator, String startDate, String endDate) {
        try {
            driver.findElement(calendarLocator).click();
            selectDateFromDayLocator(startDayLocator, startDate);
            selectDateFromDayLocator(endDayLocator, endDate);
        } catch (Exception e) {
            logger.error("Error in selectDateRange", e);
            Assert.fail("Error in selectDateRange");
        }
    }

    public void selectDateByDayOfCurrentWeek(By calendarLocator, By dayLocator, DayOfWeek targetDay) {
        try {
            LocalDate date = LocalDate.now();
            while (date.getDayOfWeek() != targetDay) {
                date = date.plusDays(1);
            }
            String day = String.valueOf(date.getDayOfMonth());
            driver.findElement(calendarLocator).click();
            selectDateFromDayLocator(dayLocator, day);
        } catch (Exception e) {
            logger.error("Error in selectDateByDayOfCurrentWeek", e);
            Assert.fail("Error in selectDateByDayOfCurrentWeek");
        }
    }

    public void selectDateFromDayLocator(By dayLocator, String day) {
        try {
            List<WebElement> days = driver.findElements(dayLocator);
            for (WebElement d : days) {
                if (d.getText().equals(day)) {
                    d.click();
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            logger.error("Element not found in selectDateFromDayLocator", e);
            Assert.fail("Element not found in selectDateFromDayLocator");
        } catch (Exception e) {
            logger.error("Error in selectDateFromDayLocator", e);
            Assert.fail("Error in selectDateFromDayLocator");
        }
    }

}
