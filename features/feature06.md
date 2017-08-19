# Allow named arguments to serve as flags (true if present).

## Additional Information

Any boolean-valued named argument is called a "flag". Flag arguments never take values (e.g., `--myarg false`). If the argument is present, that implies that it has taken the value of true (e.g., `--myarg`). This should also be used to allow the long-form alternative to the `h` help argument (`--help`) as a default behavior.

## Use Cases

Assume VolumeCalculator.java allows for three positional arguments, named `length`, `width`, and `height`, respectively. Assume that it also allows an optional flag called `help`.

    java VolumeCalculator 7 --help 3 2

Since `help` is true, the program should print the usage information and exit.

    usage: java VolumeCalculator length width height
    Calculate the volume of a box.
    positional arguments:
       length the length of the box (float)
       width the width of the box (float)
       height the height of the box (float)


## Acceptance Tests

    | *Variable*  | *Value*                                                                                                                                                                                                                          |
    | ${expected} | usage: java VolumeCalculator length width height\nCalculate the volume of a box.\npositional arguments:\n   length the length of the box (float)\n   width the width of the box (float)\n   height the height of the box (float) |
    
    | *Test Case*        | *Action*                     | *Argument*         | *Argument*  | *Argument*  | *Argument*  |
    | Test Help Flag     | Start Program With Arguments | 7                  | --help      | 3           | 2           |
    |                    | ${output}=                   | Get Program Output |             |             |             |
    |                    | Should Be Equal              | ${expected}        | ${output}   |             |             |
