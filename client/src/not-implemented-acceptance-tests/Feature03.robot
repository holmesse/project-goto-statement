*** Settings ***
Library     keywords.Feature03


*** Keywords ***
The user views the output
    No Operation

The output should be
    [Arguments]     ${anagrams}
    ${a}=   Get output as single line
    Should Be Equal  ${a}  ${anagrams}
    
Scenario Outline: Get all anagrams and subanagrams of a word
    [Arguments]     ${word}  ${anagrams}
    Given the program is started with  ${word}
    When the user views the output
    Then the output should be  ${anagrams}


*** Test Cases ***
Scenario: Get all anagrams and subanagrams of a word
    [Template]  Scenario Outline: Get all anagrams and subanagrams of a word

    # Examples:
    # word              anagrams
    star                a ar ars art arts as ast astr at r ra ras rat rats rs rt s sa sar sart sat sr st sta star str stra t ta tar tars tas tr tra trs ts tsar
    cat                 a ac act at c ca cat ct t ta tc
    atrs                ${EMPTY}
    ${EMPTY}            ${EMPTY}
