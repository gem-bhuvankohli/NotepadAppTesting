@NotepadTest
Feature: Notepad App Actions

  Scenario: Opening Notepad Application
    Given The Notepad application is opened
    Then  verifying application is open by verifying visibility of elements
    And  capturing screenshot

  Scenario: Adding Multiple Notes
    Given the user adds multiple notes
    Then  verifying notes are added by verifying list size is non zero
    And  capturing screenshot

  Scenario: Update existing note
    Given the user updates the created note
    Then verifying note is updated by verifying it's text value
    And  capturing screenshot

  Scenario: Delete existing note
    Given the user deletes the updated note
    Then  verifying note is successfully deleted by verifying reduction in notes number
    And  capturing screenshot