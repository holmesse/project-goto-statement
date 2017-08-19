# Allow variable numbers of argument values to be specified by a single argument.

## Additional Information

Until now, each argument has had at most one value associated with it. Sometimes, however, it is useful to allow a single argument to have multiple values. In this case, the client must specify exactly how many values should be associated with a particular argument.

The XML format should accommodate this new feature.

## Use Cases

Assume SumNumbers.java allows for a single positional argument (`numbers`) with five values.

    java SumNumbers 6 3 7 12 8

would produce correct output (36), while

    java SumNumbers 6 2 7
    
would throw an exception explaining that the `numbers` argument requires five values.

## Acceptance Tests

    | *Test Case*                      | *Action*                         | *Argument*  | *Argument* | *Argument* | *Argument* | *Argument* |
    | Test Sum Numbers Multiple Values | Start Sum Numbers With Arguments | 6           | 3          | 7          | 12         | 8          |
    |                                  | ${numbers}=                      | Get Numbers |            |            |            |            |
    |                                  | Should Contain                   | ${numbers}  | 6          |            |            |            |
    |                                  | Should Contain                   | ${numbers}  | 3          |            |            |            |
    |                                  | Should Contain                   | ${numbers}  | 7          |            |            |            |
    |                                  | Should Contain                   | ${numbers}  | 12         |            |            |            |
    |                                  | Should Contain                   | ${numbers}  | 8          |            |            |            |
