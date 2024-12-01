#include <Servo.h>

Servo servoOneNfc;
Servo servoTwoNfc;
int innerData;
#define firstNFCFingerPin 2
#define secondNFCFingerPin 3

void setup() {
  servoOneNfc.attach(firstNFCFingerPin);
  servoTwoNfc.attach(secondNFCFingerPin);
  Serial.begin(9600);
  innerData = 0;
}

void loop() {
  if (Serial.available() > 0) {
    innerData = Serial.parseInt();
  }

  if (innerData != 0) {
    switch (innerData) {
      case 1 : firstNfcFingerDown();
        break;
      case 2 : firstNfcFingerUp();
        break;
      case 3 : secondNfcFingerDown();
        break;
      case 4 : secondNfcFingerUp();
        break;
      case 9998 : Serial.println("QaArm");
        break;
      case 9999 : Serial.println("v.2f.02");
        break;
      default: Serial.println("unknown command");
        break;
    }
  }

  innerData = 0;
}

void firstNfcFingerDown() {
  int pos = 90;
  for (pos; pos >= 30; pos -= 1) {
    servoOneNfc.write(pos);
    delay(15);
  }
  Serial.println("1 NFS down");
}

void firstNfcFingerUp() {
  int pos = 30;
  for (pos; pos <= 90; pos += 1) {
    servoOneNfc.write(pos);
    delay(15);
  }
  Serial.println("1 NFS up");
}

void secondNfcFingerDown() {
  int pos = 90;
  for (pos; pos <= 150; pos += 1) {
    servoTwoNfc.write(pos);
    delay(15);
  }
  Serial.println("2 NFS down");
}

void secondNfcFingerUp() {
  int pos = 150;
  for (pos; pos >= 90; pos -= 1) {
    servoTwoNfc.write(pos);
    delay(15);
  }
  Serial.println("2 NFS up");
}