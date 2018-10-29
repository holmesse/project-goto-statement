*** Settings ***
Library         keywords.Feature05
Library         SwingLibrary
Library         Screenshot
Test Teardown   Custom Teardown


*** Keywords ***
Custom Teardown
    Run Keyword If Test Failed      Take Screenshot
    Close Window        WordOff

The game has started with
    [Arguments]         ${arg}
    Start Application   edu.wofford.wordoff.Feature05Main   ${arg}
    Select Window       WordOff
    Button Should Be Enabled        button
    Text Field Should Be Enabled    guess

The player enters all guesses
    ${word}=        Get Label Content   target
    @{guesslist}=   Get guesses for word  ${word}
    :FOR    ${guess}    IN    @{guesslist}
    \   Type Into Text Field    guess    ${guess}
    \   Click On Component      button

The game should end successfully
    Button Should Be Disabled       button
    Text Field Should Be Disabled   guess

Scenario Outline: Playing the game with a starting difficulty
    [Arguments]     ${difficulty}
    Given the game has started with  ${difficulty}
    When the player enters all guesses
    Then the game should end successfully


*** Test Cases ***
Scenario: Playing the game with a starting difficulty 1 
    Scenario Outline: Playing the game with a starting difficulty   1

Scenario: Playing the game with a starting difficulty 2 
    Scenario Outline: Playing the game with a starting difficulty   2

Scenario: Playing the game with a starting difficulty 3
    Scenario Outline: Playing the game with a starting difficulty   3

Scenario: Playing the game with a starting difficulty 4 
    Scenario Outline: Playing the game with a starting difficulty   4

Scenario: Playing the game with a starting difficulty 5 
    Scenario Outline: Playing the game with a starting difficulty   5

Scenario: Playing the game with a starting difficulty too large
    Start Application   edu.wofford.wordoff.Feature05Main   10
    Run Keyword And Expect Error    Any javax.swing.JFrame   Select Main Window
    [Teardown]  Run Keyword If Test Failed    Custom Teardown

Scenario: Playing the game with a starting difficulty too small
    Start Application   edu.wofford.wordoff.Feature05Main   0
    Run Keyword And Expect Error    Any javax.swing.JFrame   Select Main Window
    [Teardown]  Run Keyword If Test Failed    Custom Teardown

Scenario: Playing the game with a starting difficulty invalid
    Start Application   edu.wofford.wordoff.Feature05Main   asdf
    Run Keyword And Expect Error    Any javax.swing.JFrame   Select Main Window
    [Teardown]  Run Keyword If Test Failed    Custom Teardown

