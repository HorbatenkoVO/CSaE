#include <Servo.h>

Servo servoOneNfc;
int innerData;
#define firstNFCFingerPin 2

void setup() {
  servoOneNfc.attach(firstNFCFingerPin);
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
      case 9998 : Serial.println("QaArm");
        break;
      case 9999 : Serial.println("v.1f.03");
        break;
      default: Serial.println("unknown command");
        break;
    }
  }

  innerData = 0;
}

void firstNfcFingerDown() {
  Serial.println("first NFS down");
  int pos = 90;
  for (pos; pos <= 170; pos += 1) {
    servoOneNfc.write(pos);
    delay(15);
  }
}

void firstNfcFingerUp() {
  Serial.println("first NFS up");
  int pos = 170;
  for (pos; pos >= 90; pos -= 1) {
    servoOneNfc.write(pos);
    delay(15);
  }
}