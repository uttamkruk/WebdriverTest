set projectLocation=C:\Testing\WebdriverTesting
pushd %projectLocation%
##call mvn -f %projectLocation%\pom.xml clean install -U   --> If <suiteXmlFiles>testng.xml</suiteXmlFiles> in pom.xml
mvn clean test -Dsurefire.suiteXmlFiles=%projectLocation%\testng-grid.xml
pause