# Allow arguments to have a restricted set of possible choices for their values.

## Additional Information

Sometimes an argument can only attain certain values from a discrete set. Those allowable values should be specified by the client. Providing any value for the argument that is not in the set should throw an informative exception.

The XML format should accommodate this new feature.

## Use Cases

Assume VolumeCalculator.java allows for three positional arguments, named `length`, `width`, and `height`, respectively, each representing a float. Assume that it also allows an optional named argument called `type` representing a string that has values that are restricted to the set ("box", "ellipsoid", "pyramid").

    java VolumeCalculator 7 --type ellipsoid 3 2

would produce correct output, while

    java VolumeCalculator 7 3 --type frustum 2
    
would throw an exception explaining that "frustum" was not an allowed value for `type`.
    
## Acceptance Tests

The acceptance of this feature must be done by inspection of the library and by exhibiting demonstration programs that use the functionality.