Echo off
Title FirefoxDriver NODE on port 5556

Echo Setting Project Location for Node
set projectLocation=C:\Testing\WebdriverTest
pushd %projectLocation%

Echo Setting FirefoxDriver Path and Create node on port 5556
java -Dwebdriver.gecko.driver=%projectLocation%\driver\geckodriver.exe -jar %projectLocation%\selenium-server-standalone-3.4.0.jar -role node -hub http://192.168.246.113:4444/grid/register  -port 5556
pause