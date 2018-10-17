*** Settings ***
Library         keywords.Feature07
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

Populate small leaderboard database
    Clear leaderboard database
    Add to leaderboard database     two_       7       49
    Add to leaderboard database     four_      8       40
    Add to leaderboard database     three_     9       54

Populate large leaderboard database
    Clear leaderboard database
    Add to leaderboard database     two_       7       49
    Add to leaderboard database     four_      8       40
    Add to leaderboard database     three_     9       54
    Add to leaderboard database     six_       10      30
    Add to leaderboard database     five_      11      44

The game has started with
    [Arguments]         ${arg}
    Start Application   edu.wofford.wordoff.Feature07Main   ${arg}
    Select Window       WordOff
    Button Should Be Enabled        button
    Text Field Should Be Enabled    guess

The ${dbsize} leaderboard database has been populated
    Run Keyword If  '${dbsize}' == 'small'      Populate small leaderboard database
    ...             ELSE                        Populate large leaderboard database

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

The leaderboard should show the leaders with player at top
    [Arguments]     ${difficulty}   ${dbsize}
    ${nrows}=       Run Keyword If  '${dbsize}' == 'small'  Evaluate    4    ELSE    Evaluate    5
    ${nrows}=       Convert To Integer  ${nrows}
    ${diffint}=     Convert To Integer  ${difficulty}
    ${secsleft}=    Evaluate    ${diffint} * 10 - 2
    Dialog Should Be Open   Leaderboard
    Select Dialog   Leaderboard
    ${cols}=        Get Table Column Count      leaderboard
    Should Be Equal As Integers     ${cols}     4
    ${rows}=        Get Table Row Count         leaderboard
    Should Be Equal As Integers     ${rows}     ${nrows}
    @{headers}=     Get Table Headers           leaderboard
    Should Be Equal     @{headers}[0]   Rank
    Should Be Equal     @{headers}[1]   Word
    Should Be Equal     @{headers}[2]   Difficulty
    Should Be Equal     @{headers}[3]   Seconds Left
    ${cells}=       Get Table Values            leaderboard
    Should Be Equal As Integers     ${cells[0][0]}  1
    Should Be Equal As Integers     ${cells[0][2]}  ${difficulty}
    ${c03int}=      Convert To Integer  ${cells[0][3]}
    Should Be True     ${c03int} >= ${secsleft}
    ${expword}=     Create List     one_     two_     three_   four_    five_    six_
    :FOR    ${index}    IN RANGE    1    ${nrows}
    \    Should Be Equal    ${cells[${index}][1]}       ${expword[${index}]}

    Close Dialog    Leaderboard

The leaderboard should show the leaders without player
    [Arguments]     ${difficulty}   ${dbsize}
    ${nrows}=       Run Keyword If  '${dbsize}' == 'small'  Evaluate    3    ELSE    Evaluate    5
    ${nrows}=       Convert To Integer  ${nrows}
    Dialog Should Be Open   Leaderboard
    Select Dialog   Leaderboard
    ${cols}=        Get Table Column Count      leaderboard
    Should Be Equal As Integers     ${cols}     4
    ${rows}=        Get Table Row Count         leaderboard
    Should Be Equal As Integers     ${rows}     ${nrows}
    @{headers}=     Get Table Headers           leaderboard
    Should Be Equal     @{headers}[0]   Rank
    Should Be Equal     @{headers}[1]   Word
    Should Be Equal     @{headers}[2]   Difficulty
    Should Be Equal     @{headers}[3]   Seconds Left
    ${cells}=       Get Table Values            leaderboard
    ${expword}=     Create List     two_     three_   four_    five_    six_
    :FOR    ${index}    IN RANGE    0    ${nrows}
    \    Should Be Equal    ${cells[${index}][1]}       ${expword[${index}]}
    Close Dialog    Leaderboard

Scenario Outline: Winning the game with a starting difficulty
    [Arguments]     ${difficulty}   ${dbsize}
    ${diffint}=     Convert To Integer  ${difficulty}
    Given the ${dbsize} leaderboard database has been populated
    And the game has started with   ${difficulty}
    And the player enters all guesses
    When the system waits for 3 seconds
    Then the game should end successfully
    And the timer should show at least ${diffint * 10 - 2} seconds
    And the leaderboard should show the leaders with player at top     ${difficulty}   ${dbsize}

Scenario Outline: Losing the game with database
    Given the small leaderboard database has been populated
    And the game has started with   1
    When the system waits for 12 seconds
    Then the game should end successfully
    And the timer should show exactly 0 seconds
    And the leaderboard should show the leaders without player    1     small

Scenario Outline: Losing the game without database
    Clear leaderboard database
    Given the game has started with   1
    When the system waits for 12 seconds
    Then the game should end successfully
    And the timer should show exactly 0 seconds
    Dialog Should Not Be Open   Leaderboard


*** Test Cases ***
Scenario: Winning the game with a starting difficulty 1 and small database
    Scenario Outline: Winning the game with a starting difficulty   1   small

Scenario: Winning the game with a starting difficulty 1 and large database
    Scenario Outline: Winning the game with a starting difficulty   1   large

Scenario: Winning the game with a starting difficulty 2 and small database
    Scenario Outline: Winning the game with a starting difficulty   2   small

Scenario: Winning the game with a starting difficulty 2 and large database
    Scenario Outline: Winning the game with a starting difficulty   2   large

Scenario: Winning the game with a starting difficulty 3 and small database
    Scenario Outline: Winning the game with a starting difficulty   3   small

Scenario: Winning the game with a starting difficulty 3 and large database
    Scenario Outline: Winning the game with a starting difficulty   3   large

Scenario: Losing the game with database
    Scenario Outline: Losing the game with database

Scenario: Losing the game without database
    Scenario Outline: Losing the game without database

Scenario: Playing the game with a starting difficulty too large
    Start Application   edu.wofford.wordoff.Feature07Main   10
    Run Keyword And Expect Error    Any javax.swing.JFrame   Select Main Window
    [Teardown]  Run Keyword If Test Failed    Custom Teardown

Scenario: Playing the game with a starting difficulty too small
    Start Application   edu.wofford.wordoff.Feature07Main   0
    Run Keyword And Expect Error    Any javax.swing.JFrame   Select Main Window
    [Teardown]  Run Keyword If Test Failed    Custom Teardown

Scenario: Playing the game with a starting difficulty invalid
    Start Application   edu.wofford.wordoff.Feature07Main   asdf
    Run Keyword And Expect Error    Any javax.swing.JFrame   Select Main Window
    [Teardown]  Run Keyword If Test Failed    Custom Teardown

