# Introduction 
This repo provides a comprehensive framework for automating the testing of ITM Platform using Selenium WebDriver and Java.

The aim is to reduce manual testing efforts, automate different test scenarios which include smoke tests, regression tests, bug fixes, end-to-end tests and increase test coverage.

# Getting Started
### 1. Software dependencies

- Java Development Kit (JDK): Version 17 or higher
- IntelliJ IDE community version
- Selenium WebDriver: Latest version
- Maven: For managing project dependencies
- TestNG: For organizing and running test cases

Make sure to install Java and set path of the java bin folder in **path** system environment Variable. Must have IntelliJ IDE to execute the tests. 

The other dependencies come along with the project.
   
### 2. Installation

Clone the project using git clone command or download as zip. Open in IntelliJ and you are all set to proceed.

### 3. API references
Refer to the following documentation for detailed API references:

- Selenium WebDriver: [Selenium API Documentation](https://www.selenium.dev/documentation/)
- TestNG: [TestNG Documentation](https://testng.org/)
- Maven: [Maven Documentation](https://maven.apache.org/guides/index.html)

# Build and Test
To build the project open the IDE terminal by clicking ctrl twice and run **mvn clean install** command. This would compile, test & package the project and install/copy the built .jar/.war file from the pom.xml file into your local maven repository.

To run the tests right-click the TestRunner/TestSuite.xml file and select **Run** to start the test execution. The TestRunner has multiple runner files based on the type of test cases. To execute particular test case groups use the specific runner file and to execute all the test cases use TestSuite.xml file. The tests report result is generated in the src/test/java/TestReport folder.

To run tests on stage or production choose the desired URL from the src/test/java/Utils/EnvironmentVariables.properties file and use that URL in **driver.navigate().to()** command in the **setupBrowserAndNavigateToWebApp()** method in src/test/java/Utils/Config/BaseClass file.

# Contribute

- Submit bugs and feature requests, and help us verify as they are checked in
- Review source code changes