@echo off
echo.
echo [Ϣ] Web̡
echo.

cd %~dp0
cd ../joine-admin/target

set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar %JAVA_OPTS% joine-admin.jar

cd bin
pause