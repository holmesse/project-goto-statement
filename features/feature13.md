# Allow named arguments to be specified as required arguments.

## Additional Information

Until now, named arguments have been treated as optional. There are times, however, when we need named arguments to be required. In this case, their default values will not matter because failure to include the argument/value pair will throw an exception just like any other missing argument.

The XML format should accommodate this new feature.

## Use Cases

Assume VolumeCalculator.java allows for three positional arguments, named `length`, `width`, and `height`, respectively, each representing a float. Assume that it also allows a required named argument called `type` representing a string that has values that are restricted to the set ("box", "ellipsoid", "pyramid").

    java VolumeCalculator 7 --type ellipsoid 3 2

would produce correct output, while

    java VolumeCalculator 7 3 2
    
would throw an exception explaining that `type` is reqired.


## Acceptance Tests

The acceptance of this feature must be done by inspection of the library and by exhibiting demonstration programs that use the functionality.