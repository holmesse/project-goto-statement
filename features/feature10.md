# Allow argument information to be saved to an XML file.

## Additional Information

If an argument list has already been implemented in Java code, it would be helpful to be able to save the list information to a file for use later. As before with reading argument information from a file, XML provides a good format for this data. The XML file should store all of the argument information, including long-form names, short-form names, datatypes, default values, and whether an argument is positional, just as in the case of reading the argument information.

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