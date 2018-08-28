*** Settings ***
Library         keywords.Feature08


*** Keywords ***
The user requests the number of words in ${sourcetype} words
    Get request     http://localhost:8080/wordoff/${sourcetype}/numwords

The user requests to know if ${string} is a word in ${sourcetype} words
    Get request     http://localhost:8080/wordoff/${sourcetype}/isword/${string}

The user requests a word of length ${length} in ${sourcetype} words
    Get request     http://localhost:8080/wordoff/${sourcetype}/wordwithlength/${length}

The user requests anagrams of ${word} in ${sourcetype} words
    Get request     http://localhost:8080/wordoff/${sourcetype}/anagrams/${word}

The user requests subanagrams of ${word} in ${sourcetype} words
    Get request     http://localhost:8080/wordoff/${sourcetype}/subanagrams/${word}

The user requests all anagrams of ${word} in ${sourcetype} words
    Get request     http://localhost:8080/wordoff/${sourcetype}/allanagrams/${word}

The user gets the response
    No Operation

The response status should be success
    ${status}=      Get response status
    Should Be Equal As Strings      ${status}       200

The response should be
    [Arguments]     ${anagrams}
    ${response}=    Get response string
    Should Be Equal As Strings      ${response}     ${anagrams}    

The response should have length
    [Arguments]     ${length}
    ${response}=    Get response string
    ${len}=         Get Length      ${response}
    Should Be Equal As Integers   ${len}     ${length}    

Scenario Outline: Get number of words in source type
    [Arguments]     ${sourcetype}   ${numwords}
    Given the user requests the number of words in ${sourcetype} words
    When the user gets the response
    Then the response status should be success
    And the response should be      ${numwords}

Scenario Outline: Determine whether a string is a word from source type
    [Arguments]     ${string}     ${sourcetype}     ${isword}
    Given the user requests to know if ${string} is a word in ${sourcetype} words
    When the user gets the response
    Then the response status should be success
    And the response should be      ${isword}

Scenario Outline: Get a word with a given length from source type
    [Arguments]     ${length}     ${sourcetype}
    Given the user requests a word of length ${length} in ${sourcetype} words
    When the user gets the response
    Then the response status should be success
    And the response should have length     ${length}

Scenario Outline: Get all anagrams of a word from source type
    [Arguments]     ${word}     ${sourcetype}     ${anagrams}
    Given the user requests anagrams of ${word} in ${sourcetype} words
    When the user gets the response
    Then the response status should be success
    And the response should be      ${anagrams}

Scenario Outline: Get all subanagrams of a word from source type
    [Arguments]     ${word}     ${sourcetype}     ${subanagrams}
    Given the user requests subanagrams of ${word} in ${sourcetype} words
    When the user gets the response
    Then the response status should be success
    And the response should be      ${subanagrams}

Scenario Outline: Get all anagrams and subanagrams of a word from source type
    [Arguments]     ${word}     ${sourcetype}     ${allanagrams}
    Given the user requests all anagrams of ${word} in ${sourcetype} words
    When the user gets the response
    Then the response status should be success
    And the response should be      ${allanagrams}


*** Test Cases ***
Scenario: Get number of words in source type
    [Template]  Scenario Outline: Get number of words in source type
    
    #Examples:
    # sourcetype        numwords
    all                 370099
    common              58110

Scenario: Determine whether a string is a word from source type
    [Template]  Scenario Outline: Determine whether a string is a word from source type

    # Examples:
    # string            sourcetype      isword
    sleep               all             true
    sleepy              all             true
    d                   all             true
    epels               all             false
    sleep               common          true
    apes                common          true
    d                   common          false
    epels               common          false
    
Scenario: Get a word with a given length from source type
    [Template]  Scenario Outline: Get a word with a given length from source type
    
    # Examples:
    # length            sourcetype
    1                   all
    2                   all
    2                   common
    3                   all
    5                   common
    7                   all
    9                   common


Scenario: Get all anagrams of a word from source type
    [Template]  Scenario Outline: Get all anagrams of a word from source type

    # Examples:
    # word              sourcetype      anagrams
    sleep               all             ["peels","peles","sleep","speel"]
    apes                all             ["apes","apse","pase","peas","pesa","spae"]
    sleepy              all             ["sleepy"]
    epels               all             []
    
Scenario: Get all subanagrams of a word from source type
    [Template]  Scenario Outline: Get all subanagrams of a word from source type

    # Examples:
    # word              sourcetype      subanagrams
    star                all             ["a","ar","ars","art","as","ast","at","r","ra","ras","rat","rs","rt","s","sa","sar","sat","sr","st","sta","str","t","ta","tar","tas","tr","tra","trs","ts"]
    star                common          ["art","as","at","rat","sat","tar"]
    
Scenario: Get all anagrams and subanagrams of a word from source type
    [Template]  Scenario Outline: Get all anagrams and subanagrams of a word from source type

    # Examples:
    # word              sourcetype      allanagrams
    star                all             ["a","ar","ars","art","arts","as","ast","astr","at","r","ra","ras","rat","rats","rs","rt","s","sa","sar","sart","sat","sr","st","sta","star","str","stra","t","ta","tar","tars","tas","tr","tra","trs","ts","tsar"]
    star                common          ["art","arts","as","at","rat","rats","sat","star","tar","tars"]
