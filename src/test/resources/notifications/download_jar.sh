cd src/test/resources/notifications
FILE=allure-notifications-4.5.0.jar
if [ ! -f "$FILE" ]; then
   wget https://github.com/qa-guru/allure-notifications/releases/download/4.5.0/allure-notifications-4.5.0.jar
fi