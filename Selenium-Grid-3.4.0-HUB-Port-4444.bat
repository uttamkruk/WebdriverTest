Echo off
Title Server HUB on port 4444

Echo Setting Project Location
set projectLocation=C:\Testing\WebdriverTest
pushd %projectLocation%

Echo Loading HUB on default port 4444
java -jar selenium-server-standalone-3.4.0.jar -role hub
pause