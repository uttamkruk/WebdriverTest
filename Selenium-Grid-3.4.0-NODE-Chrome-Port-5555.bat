Echo off
Title ChromeDriver NODE on port 5555

Echo Setting Project Location for Node
set projectLocation=C:\Testing\WebdriverTest
pushd %projectLocation%

Echo Setting ChromeDriver Path and Create node on port 5555
java -Dwebdriver.chrome.driver=%projectLocation%\driver\chromedriver.exe -jar %projectLocation%\selenium-server-standalone-3.4.0.jar -role node -hub http://192.168.246.113:4444/grid/register  -port 5555
pause