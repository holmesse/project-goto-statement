# Throw exceptions if a specified argument does not exist or has the wrong datatype.

## Additional Information

Until now, we have dealt with errors by printing directly to the command line. For example,

    java VolumeCalculator 7 --myarg myval 3 2

could produce the following output:

    usage: java VolumeCalculator length width height
    VolumeCalculator.java: error: argument myarg does not exist

Instead, throw a descriptive exception whenever a specified argument does not exist. The user can then catch that exception and take the appropriate action. Likewise, throw an exception whenever an argument has a datatype but its value does not (or cannot be made to) match that type.

## Use Cases

Assume VolumeCalculator.java allows for three positional arguments, named `length`, `width`, and `height`, respectively, each representing float values. Assume further that it does not allow for a `myarg` argument.

    java VolumeCalculator 7 --myarg myval 3 2

would produce an exception specifying that `myarg` is an unknown argument, while

    java VolumeCalculator 7 something 2

would produce an exception specifying that `width` requires a float value and "something" is not a float.

## Acceptance Tests

The acceptance of this feature must be done by inspection of the library and by exhibiting demonstration programs that use the functionality.