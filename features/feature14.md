# Allow named arguments to be grouped into mutually exclusive groups.

## Additional Information

Occasionally, there are distinct groups of named arguments that do not make sense when they are combined. For instance, you may have a named flag `quiet` that causes the program to produce no console output, and you may also have a named argument `precision` that specifies the decimal precision that values should be printed to the console. Clearly, these two arguments are mutually exclusive, and they should be treated as such. 

You should assume that if there is more than one group of mutually exclusive arguments, then it should be illegal to use arguments from more than one such group.

The XML format should accommodate this new feature.

## Use Cases

Assume VolumeCalculator.java allows for three positional arguments, named `length`, `width`, and `height`, respectively. Assume that it also allows three optional named arguments: `type`, a string representing the type of solid, `digits`, an integer representing the number of digits after the decimal place to truncate, and `exact`, a flag specifying that the exact value should be produced.

These invocations would be legal:

    java VolumeCalculator --type ellipsoid 7 3 --digits 1 2 
    java VolumeCalculator --type ellipsoid 7 3 2 
    java VolumeCalculator 7 --type ellipsoid 3 --exact 2 

but this invocation would throw an exception:

    java VolumeCalculator 7 --type ellipsoid 3 --digits 1 --exact 2 

because `digits` and `exact` are mutually exclusive.


## Acceptance Tests

The acceptance of this feature must be done by inspection of the library and by exhibiting demonstration programs that use the functionality.