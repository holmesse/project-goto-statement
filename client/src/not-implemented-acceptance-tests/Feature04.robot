*** Settings ***
Library     keywords.Feature04


*** Keywords ***
The program should end successfully
    ${success}=  Did the program end successfully
    Should Be True  ${success}

Scenario Outline: Play game with given input
    [Arguments]     ${input}   ${anagrams}
    Given the program is started with  ${input}
    When the user guesses all subanagrams of the target word  ${anagrams}
    Then the program should end successfully


*** Test Cases ***
Scenario: Play game with given input
    [Template]  Scenario Outline: Play game with given input

    # Examples:
    # input         anagrams
    star            rat RAts sAT art arts AS at star tar tars
    cat             at dog act
    2               ${EMPTY}
    1               ${EMPTY}
    0               ${EMPTY}
    ${EMPTY}        ${EMPTY}
