*** Settings ***
Library     keywords.Feature02


*** Keywords ***
The program should end successfully
    ${success}=  Did the program end successfully
    Should Be True  ${success}

Scenario Outline: Play game with given difficulty
    [Arguments]     ${difficulty}   ${anagrams}
    Given the program is started with  ${difficulty}
    When the user guesses all anagrams of the target word  ${anagrams}
    Then the program should end successfully


*** Test Cases ***
Scenario: Play game with given difficulty
    [Template]  Scenario Outline: Play game with given difficulty

    # Examples:
    # difficulty    anagrams
    15              alerts alTeRs artels laster lastre rAstle talers ratels relast RESALT salter slater staler stelar estral
    13              elaps lapse leaps lepas pales peals pleas salep saple sepal slape spale speal acres arces cares carse caser ceras cesar escar races sacre scare scrae serac
    1               ${EMPTY}
    0               ${EMPTY}
    ${EMPTY}        ${EMPTY}
    oops            ${EMPTY}
