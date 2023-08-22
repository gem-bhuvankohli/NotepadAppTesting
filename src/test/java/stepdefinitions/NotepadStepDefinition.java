package stepdefinitions;

import implementations.NotepadImplementation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.TakeScreenshot;

import static implementations.NotepadImplementation.screenshotCount;

public class NotepadStepDefinition {

    @Given("The Notepad application is opened")
    public void openTheNotepadApplication() {
        NotepadImplementation.openApplication();
    }

    @Then("verifying application is open by verifying visibility of elements")
    public void verifyingApplicationIsOpen() {
        NotepadImplementation.verifyApplicationIsOpen();
    }

    @Given("the user adds multiple notes")
    public void addMultipleNotes() {
        NotepadImplementation.addNotes();
    }

    @Then("verifying notes are added by verifying list size is non zero")
    public void verifyIfNotesAreAddedSuccessfully() {
        NotepadImplementation.verifyIfNotesAreAdded();
    }

    @Given("the user updates the created note")
    public void theUserUpdatesTheCreatedNote() {
        NotepadImplementation.updateExistingNote();
    }

    @Then("verifying note is updated by verifying it's text value")
    public void verifyingNoteIsUpdated() {
        NotepadImplementation.verifyNoteIsUpdated();
    }

    @Given("the user deletes the updated note")
    public void theUserDeletesTheUpdatedNote() {
        NotepadImplementation.deleteANote();
    }

    @Then("verifying note is successfully deleted by verifying reduction in notes number")
    public void verifyingNoteIsSuccessfullyDeleted() {
        NotepadImplementation.verifyDeletion();
    }

    @And("capturing screenshot")
    public void takeScreenshot() {
        TakeScreenshot.screenshot("Screenshot" + (screenshotCount++) + ".jpg");
    }
}