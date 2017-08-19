# Allow short-form names for named arguments, in addition to long-form names.

## Additional Information

The short-form name of an argument is a one-character abbreviated name. When entered in the command-line, short-form names always begin with a hyphen (instead of a double-hyphen for long-form names). Short-form flags (because they have no associated values) can also be combined in a single specification. For instance, `-d -a -p` is equivalent to `-dap`. Notice that the combination `-dap` is different from a long-form argument called `--dap`. Both of those should be possible (albeit confusing).

This short-form name should not be auto-generated from the long-form name; it should be specified by the user. For instance, if a long-form named argument is `digits`, you should not assume that the short-form name will be `d`. This is because long-form names may share the same first letters (e.g., `precision` and `print`).

Now that the short-form names are available, the `h` help argument should be treated as such (since that is really what it is). Also, it should be an error for the user to try to name another short-form argument `h` (taken by default to be "help").

## Use Cases

Assume VolumeCalculator.java allows for three positional arguments, named `length`, `width`, and `height`, respectively. Assume that it also allows two optional named arguments called `type` (short-form `t`) and `digits` (short-form `d`). The following calls would be equivalent:

    java VolumeCalculator --type ellipsoid 7 3 --digits 1 2
    java VolumeCalculator 7 -t ellipsoid 3 --digits 1 2
    java VolumeCalculator 7 3 --type ellipsoid -d 1 2
    java VolumeCalculator 7 -d 1 3 -t ellipsoid 2


## Acceptance Tests

    | *Test Case*                  | *Action*                       | *Argument*         | *Argument*   | *Argument* | *Argument* | *Argument* | *Argument* | *Argument* |
    | Test Short Form Arguments    | Start Program With Arguments   | -t                 | ellipsoid    | 7          | 3          | -d         | 1          | 2          |
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
