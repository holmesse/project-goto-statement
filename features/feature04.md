# Allow named arguments with single values, which may have help and datatype information, after all positional arguments.

## Additional Information

This is specifically about long-form named arguments (e.g., `--help` instead of `-h`).
Later on, we will want to do things such as
    --help (no values)
or
    --location 3 7 2
where `3 7 2` is not a part of the `length`, `width` , and `height`; it is part of `location`.

This should allow multiple long-form arguments.

All optional named arguments are required to have default values specified within the argument parser (in the event that they are not given on the command line).

## Use Cases

Assume VolumeCalculator.java allows for three positional arguments, named `length`, `width`, and `height`, respectively. Assume that it also allows an optional named argument called `type`.

    java VolumeCalculator 7 3 2 --type ellipsoid
    
should output the volume of the ellipsoid.

Multiple optional named arguments are also allowed (for instance, both `type` and `digits`):

    java VolumeCalculator 7 3 2 --type ellipsoid --digits 1
    
In the first use case, the `digits` variable should take on its default value (since it was not specified).

## Acceptance Tests

    | *Test Case*                  | *Action*                       | *Argument*         | *Argument*   | *Argument* | *Argument* | *Argument* | *Argument* | *Argument* |
    | Test Named Argument Defaults | Start Program With Arguments   | 7                  | 3            | 2          |            |            |            |            |
    |                              | ${length}=                     | Get Length         |              |            |            |            |            |            |
    |                              | Should Be Equal                | 7                  | ${length}    |            |            |            |            |            |
    |                              | ${width}=                      | Get Width          |              |            |            |            |            |            |
    |                              | Should Be Equal                | 3                  | ${width}     |            |            |            |            |            |
    |                              | ${height}=                     | Get Height         |              |            |            |            |            |            |
    |                              | Should Be Equal                | 2                  | ${height}    |            |            |            |            |            |
    |                              | ${type}=                       | Get Type           |              |            |            |            |            |            |
    |                              | Should Be Equal                | box                | ${type}      |            |            |            |            |            |
    |                              | ${digits}=                     | Get Digits         |              |            |            |            |            |            |
    |                              | Should Be Equal                | 4                  | ${digits}    |            |            |            |            |            |
    | Test Named Argument Single   | Start Program With Arguments   | 7                  | 3            | 2          | --type     | ellipsoid  |            |            |
    |                              | ${length}=                     | Get Length         |              |            |            |            |            |            |
    |                              | Should Be Equal                | 7                  | ${length}    |            |            |            |            |            |
    |                              | ${width}=                      | Get Width          |              |            |            |            |            |            |
    |                              | Should Be Equal                | 3                  | ${width}     |            |            |            |            |            |
    |                              | ${height}=                     | Get Height         |              |            |            |            |            |            |
    |                              | Should Be Equal                | 2                  | ${height}    |            |            |            |            |            |
    |                              | ${type}=                       | Get Type           |              |            |            |            |            |            |
    |                              | Should Be Equal                | ellipsoid          | ${type}      |            |            |            |            |            |
    |                              | ${digits}=                     | Get Digits         |              |            |            |            |            |            |
    |                              | Should Be Equal                | 4                  | ${digits}    |            |            |            |            |            |
    | Test Named Argument Multiple | Start Program With Arguments   | 7                  | 3            | 2          | --type     | ellipsoid  | --digits   | 1          |
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
