*** Settings ***
Library     keywords.Feature01


*** Keywords ***
The user views the output
    No Operation

The output should be
    [Arguments]     ${anagrams}
    ${a}=   Get output as single line
    Should Be Equal  ${a}  ${anagrams}
    
The output should contain one of
    [Arguments]     ${options}
    ${str}=   Get output as single line
    ${val}=   The string should contain exactly one of options  ${str}  ${options}
    Should Be True  ${val}
    
Scenario Outline: Get all anagrams of a word
    [Arguments]     ${word}  ${anagrams}
    Given the program is started with  ${word}
    When the user views the output
    Then the output should be  ${anagrams}

Scenario Outline: Get anagrams of a random word
    [Arguments]     ${num}   ${anagrams}
    Given the program is started with  ${num}
    When the user views the output
    Then the output should contain one of  ${anagrams}


*** Test Cases ***
Scenario: Get all anagrams of a word
    [Template]  Scenario Outline: Get all anagrams of a word

    # Examples:
    # word              anagrams
    sleep               peels peles sleep speel
    paralinGUIstic      paralinguistic
    apes                apes apse pase peas pesa spae
    sleepy              sleepy
    epels               ${EMPTY}
    
Scenario: Get all ${num} anagrams of a random word
    [Template]  Scenario Outline: Get anagrams of a random word

    # Examples:
    # num               anagrams
    15                  ratels
    13                  races pales
    0                   ${EMPTY}