# Allow named arguments to be mixed with positional arguments in any order.

## Additional Information

Rather than placing all named arguments at the end (after all positional arguments), they can be placed anywhere in the argument list.

## Use Cases

Assume VolumeCalculator.java allows for three positional arguments, named `length`, `width`, and `height`, respectively. Assume that it also allows two optional named arguments called `type` and `digits`.

    java VolumeCalculator --type ellipsoid 7 3 --digits 1 2

The named argument and its value must always stay together. Thus, it would be incorrect to say something like

    java VolumeCalculator --type 7 ellipsoid 3 --digits 1 2

because it implies that the `type` should be 7.
    
## Acceptance Tests

    | *Test Case*                  | *Action*                       | *Argument*         | *Argument*   | *Argument* | *Argument* | *Argument* | *Argument* | *Argument* |
    | Test Named Argument Anywhere | Start Program With Arguments   | --type             | ellipsoid    | 7          | 3          | --digits   | 1          | 2          |
    |                              | ${length}=                     | Get Length         |              |            |            |            |            |            |
    |                              | Should Be Equal                | 7                  | ${length}    |            |            |            |            |            |
    |                              | ${width}=                      | Get Width          |              |            |            |            |            |            |
    |                              | Should Be Equal                | 3                  | ${width}     |            |            |            |            |            |
    |                              | ${height}=                     | Get Height         |              |            |            |            |            |            |
    |                              | Should Be Equal                | 2                  | ${height}    |            |            |            |            |            |
    |                              | ${type}=                       | Get Type           |              |            |            |            |            |            |
    |                              | Should Be Equal                | ellipsoid          | ${type}      |            |            |            |            |            |
    |                              | ${digits}=                     | Get Digits         |              |            |            |            |            |            |
    |                              | Should Be Equal                | 1                  | ${digits}    |            |            |            |            |            |
