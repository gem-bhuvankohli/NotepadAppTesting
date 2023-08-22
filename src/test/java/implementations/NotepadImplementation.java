package implementations;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import locators.NotepadLocators;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigReader;

import java.net.URL;
import java.time.Duration;
import java.util.List;

/**
 * Contains implementations related to the Notepad application.
 */
public class NotepadImplementation {
    public static AppiumDriver driver;
    static List<WebElement> noteElementsList;
    public static final String addText = "helloWorld ";
    public static final String updateText = "updated note";
    public static int screenshotCount = 1;

    /**
     * Opens the Notepad application.
     * Initializes the appium driver with desired capabilities and opens the application.
     */
    public static void openApplication() {
        try {
            // Set desired capabilities for the driver
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("deviceName", ConfigReader.getProperty("appium.deviceName"));
            cap.setCapability("udid", ConfigReader.getProperty("appium.udid"));
            cap.setCapability("platformName", ConfigReader.getProperty("appium.platformName"));
            cap.setCapability("app", System.getProperty("user.dir") + ConfigReader.getProperty("appium.appPath"));
            cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

            // Initialize the driver
            URL url = new URL(ConfigReader.getProperty("appium.appUrl"));
            driver = new AndroidDriver(url, cap);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Click on the skip button for introduction
            driver.findElement(NotepadLocators.skipBtn).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if the Notepad application is open.
     * Checks if the required buttons are displayed.
     */
    public static void verifyApplicationIsOpen() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebElement addNoteBtn = driver.findElement(NotepadLocators.addNoteBtn);
            Assert.assertTrue("Add note button is not displayed.", addNoteBtn.isDisplayed());
            WebElement searchBtn = driver.findElement(NotepadLocators.searchBtn);
            Assert.assertTrue("Search button is not displayed.", searchBtn.isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds multiple notes to the Notepad application.
     */
    public static void addNotes() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Add multiple notes
            for (int i = 1; i <= 3; i++) {
                WebElement addNoteBtn = driver.findElement(NotepadLocators.addNoteBtn);
                addNoteBtn.click();

                WebElement textOption = driver.findElement(NotepadLocators.textOption);
                textOption.click();

                WebElement addTitle = driver.findElement(NotepadLocators.addTitle);
                addTitle.sendKeys("Title " + i);

                WebElement addNote = driver.findElement(NotepadLocators.addNote);
                addNote.sendKeys(addText);

                WebElement backBtn = driver.findElement(NotepadLocators.backBtn);
                backBtn.click();
                backBtn.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if notes are added by checking the note elements list.
     */
    public static void verifyIfNotesAreAdded() {
        try {
            // Find and verify note elements list
            noteElementsList = driver.findElements(NotepadLocators.noteElementsList);
            Assert.assertTrue("No notes were added.", noteElementsList.size() != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the content of an existing note in the Notepad application.
     */
    public static void updateExistingNote() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebElement noteElement = noteElementsList.get(0);
            noteElement.click();
            WebElement editBtn = driver.findElement(NotepadLocators.editBtn);
            editBtn.click();
            WebElement updateNotes = driver.findElement(NotepadLocators.addNote);
            updateNotes.sendKeys(updateText);
            WebElement updateBtn = driver.findElement(NotepadLocators.backBtn);
            updateBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if the note has been updated.
     * Compares the expected and actual note content.
     */
    public static void verifyNoteIsUpdated() {
        WebElement textArea = driver.findElement(NotepadLocators.viewNote);
        String expectedValue = addText + updateText;
        String actualValue = textArea.getText();
        Assert.assertEquals("Note content is not updated as expected.", expectedValue, actualValue);
        WebElement backBtn = driver.findElement(NotepadLocators.backBtn);
        backBtn.click();
    }

    /**
     * Deletes a note from the Notepad application.
     */
    public static void deleteANote() {
        try {
            WebElement elementToDelete = noteElementsList.get(0);
            LongPressOptions longPressOptions = new LongPressOptions();
            longPressOptions.withElement(ElementOption.element(elementToDelete));
            TouchAction touchAction = new TouchAction<>((PerformsTouchActions) driver);
            touchAction.longPress(longPressOptions).release().perform();

            WebElement deleteBtn = driver.findElement(NotepadLocators.deleteBtn);
            deleteBtn.click();
            WebElement okBtn = driver.findElement(NotepadLocators.okBtn);
            okBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if the note is successfully deleted.
     * Compares the number of notes before and after deletion.
     */
    public static void verifyDeletion() {
        int numberOfNotesBeforeDeletion = noteElementsList.size();
        int numberOfNotesAfterDeletion = driver.findElements(NotepadLocators.noteElementsList).size();

        // The number of notes before and after deletion should differ by 1
        Assert.assertEquals("Note deletion was not successful.", numberOfNotesBeforeDeletion - 1, numberOfNotesAfterDeletion);
    }
}