# Allow only string-valued positional arguments and retrieve them from the command-line.

## Use Cases

Assume VolumeCalculator.java allows for three positional arguments, named `length`, `width`, and `height`, respectively.

    java VolumeCalculator 7 5 2

This should ensure that `length` has the value of "7", `width` has the value of "5", and `height` has the value of "2".

If a positional argument is not supplied, the program should exit, and the usage information should be displayed along with the error stating the missing argument.

    java VolumeCalculator 7 5

should produce something like the following:

    usage: java VolumeCalculator length width height
    VolumeCalculator.java: error: the following arguments are required: height

If an additional (i.e., one too many) positional argument is specified, then the program should exit, and the usage information should be displayed along with the error stating the additional argument.

    java VolumeCalculator 7 5 2 43

should produce the following:

    usage: java VolumeCalculator length width height
    VolumeCalculator.java: error: unrecognized arguments: 43

## Acceptance Tests

    | *Variable*  | *Value*                                                                                                    |
    | ${expected} | usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: unrecognized arguments: 43 |
    
    | *Test Case*                            | *Action*                               | *Argument*         | *Argument*   | *Argument* | *Argument* |
    | Test Volume Calculator Normal Function | Start Program With Arguments           | 7                  | 5            | 2          |            |
    |                                        | ${length}=                             | Get Length         |              |            |            |
    |                                        | Should Be Equal                        | 7                  | ${length}    |            |            |
    |                                        | ${width}=                              | Get Width          |              |            |            |
    |                                        | Should Be Equal                        | 5                  | ${width}     |            |            |
    |                                        | ${height}=                             | Get Height         |              |            |            |
    |                                        | Should Be Equal                        | 2                  | ${height}    |            |            |
    |                                        | ${output}=                             | Get Program Output |              |            |            |
    |                                        | Should Be Equal                        | 70                 | ${output}    |            |            |
    | Test Unrecognized Argument             | Start Program With Arguments           | 7                  | 5            | 2          | 43         |
    |                                        | ${output}=                             | Get Program Output |              |            |            |
    |                                        | Should Be Equal                        | ${expected}        | ${output}    |            |            |
    | Test Absurd Program Normal Function    | Start Absurd Program With Arguments    | dog                | 2            | true       | 3.5        |
    |                                        | ${pet}=                                | Get Pet            |              |            |            |
    |                                        | Should Be Equal                        | dog                | ${pet}       |            |            |
    |                                        | ${number}=                             | Get Number         |              |            |            |
    |                                        | Should Be Equal                        | 2                  | ${number}    |            |            |
    |                                        | ${rainy}=                              | Get Rainy          |              |            |            |
    |                                        | Should Be Equal                        | true               | ${rainy}     |            |            |
    |                                        | ${bathrooms}=                          | Get Bathrooms      |              |            |            |
    |                                        | Should Be Equal                        | 3.5                | ${bathrooms} |            |            |

