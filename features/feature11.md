# Provide comprehensive documentation on library features, including examples of use.

## Additional Information

A library is only as good as its documentation. In fact, it is the documentation that determines whether a library will be a success or failure. Every class and every public method and attribute should be documented and fully explained. 

You should use Javadoc to document the library so that Gradle can automatically generate the documentation. You should also include usage examples and demonstration programs that give the user good, working examples.

## Use Cases

None

## Acceptance Tests

Every class and every public member should be documented in the API documentation. Additionally, the main parsing class should have documentation providing examples of common usage. (See the API documentation for java.lang.String for an example of this.) Finally, you should provide at least three different demonstration programs (placed in a "demos" directory of the project) that shows how the library can be used by the client. These programs should be implemented *exactly* the way the client would implement them. (For example, do not put them inside the package because the client will not be able to do that.)