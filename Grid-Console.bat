set projectLocation=C:\Testing\WebdriverTest
pushd %projectLocation%
set classpath=%projectLocation%\lib\*;%projectLocation%\target\test-classes\gridTest
java org.testng.TestNG %projectLocation%\testng-grid.xml
pause