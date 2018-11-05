*** Settings ***
Library         keywords.Feature06
Library         SwingLibrary
Library         String
Library         Screenshot
Test Teardown   Custom Teardown


*** Keywords ***
Custom Teardown
    Run Keyword If Test Failed      Take Screenshot
    Close All Dialogs
    Close Window        WordOff

Get timer seconds
    ${timerstr}=    Get Label Content   timer
    @{parts}=       Split String    ${timerstr}   :
    ${tmins}=       Convert To Integer  @{parts}[0]
    ${tsecs}=       Convert To Integer  @{parts}[1]
    ${timerint}=    Evaluate    ${tmins} * ${60} + ${tsecs}
    [Return]        ${timerint}    

The game has started with
    [Arguments]         ${arg}
    Start Application   edu.wofford.wordoff.Feature06Main   ${arg}
    Select Window       WordOff
    Button Should Be Enabled        button
    Text Field Should Be Enabled    guess

The player enters all guesses
    ${word}=        Get Label Content   target
    @{guesslist}=   Get guesses for word  ${word}
    :FOR    ${guess}    IN    @{guesslist}
    \   Type Into Text Field    guess    ${guess}
    \   Click On Component      button

The system waits for ${seconds} seconds
    Sleep   ${seconds} seconds

The game should end successfully
    Button Should Be Disabled       button
    Text Field Should Be Disabled   guess

The timer should show at least ${seconds} seconds
    ${timerint}=      Get timer seconds
    ${secsint}=       Convert To Integer  ${seconds}
    Should Be True   ${timerint} >= ${secsint}

The timer should show exactly ${seconds} seconds
    ${timerint}=      Get timer seconds
    ${secsint}=       Convert To Integer  ${seconds}
    Should Be Equal   ${timerint}   ${secsint}

Scenario Outline: Winning the game with a starting difficulty
    [Arguments]     ${difficulty}
    ${diffint}=     Convert To Integer  ${difficulty}
    Given the game has started with  ${difficulty}
    And the player enters all guesses
    When the system waits for 3 seconds
    Then the game should end successfully
    And the timer should show at least ${diffint * 10 - 2} seconds

Scenario Outline: Losing the game
    Given the game has started with   1
    When the system waits for 12 seconds
    Then the game should end successfully
    And the timer should show exactly 0 seconds


*** Test Cases ***
Scenario: Winning the game with a starting difficulty 1 
    Scenario Outline: Winning the game with a starting difficulty   1

Scenario: Winning the game with a starting difficulty 2 
    Scenario Outline: Winning the game with a starting difficulty   2

Scenario: Winning the game with a starting difficulty 3
    Scenario Outline: Winning the game with a starting difficulty   3

Scenario: Winning the game with a starting difficulty 4 
    Scenario Outline: Winning the game with a starting difficulty   4

Scenario: Winning the game with a starting difficulty 5 
    Scenario Outline: Winning the game with a starting difficulty   5

Scenario: Losing the game 
    Scenario Outline: Losing the game

Scenario: Playing the game with a starting difficulty too large
    Start Application   edu.wofford.wordoff.Feature06Main   10
    Run Keyword And Expect Error    Any javax.swing.JFrame   Select Main Window
    [Teardown]  Run Keyword If Test Failed    Custom Teardown

Scenario: Playing the game with a starting difficulty too small
    Start Application   edu.wofford.wordoff.Feature06Main   0
    Run Keyword And Expect Error    Any javax.swing.JFrame   Select Main Window
    [Teardown]  Run Keyword If Test Failed    Custom Teardown

Scenario: Playing the game with a starting difficulty invalid
    Start Application   edu.wofford.wordoff.Feature06Main   asdf
    Run Keyword And Expect Error    Any javax.swing.JFrame   Select Main Window
    [Teardown]  Run Keyword If Test Failed    Custom Teardown

