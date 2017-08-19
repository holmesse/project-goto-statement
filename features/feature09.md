# Allow argument information to be loaded from an XML file.

## Additional Information

While specifying command-line arguments in code is fine, it becomes tedious and error-prone whenever multiple programs have exactly the same argument lists. In such cases, it would be easier to pull the argument lists directly from a file. The XML specification provides a human-readable, structured format for storing textual data. The XML file should store all of the argument information, including long-form names, short-form names, datatypes, default values, and whether an argument is positional.

## Use Cases

Assume VolumeCalculator.java allows for three positional arguments, named `length`, `width`, and `height`, respectively, each representing float values. Assume that it also allows two optional named arguments: `type`, a string that defaults to "box" with short-form `t`, and `digits`, an integer that defaults to 4 with short-form `d`.

The following XML file might represent that argument list:

    <arguments>
        <positional>
            <name>length</name>
            <type>float</type>
            <position>1</position>
        </positional>
        <positional>
            <name>width</name>
            <type>float</type>
            <position>2</position>
        </positional>
        <positional>
            <name>height</name>
            <type>float</type>
            <position>3</position>
        </positional>
        <named>
            <name>type</name>
            <shortname>t</shortname>
            <type>string</type>
            <default>box</default>
        </named>
        <named>
            <name>digits</name>
            <shortname>d</shortname>
            <type>integer</type>
            <default>4</default>
        </named>
    </arguments>
    
    
## Acceptance Tests

The acceptance of this feature must be done by inspection of the library and by exhibiting demonstration programs that use the functionality.