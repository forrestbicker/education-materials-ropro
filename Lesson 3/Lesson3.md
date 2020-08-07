# Lesson 3 - Motors. Motors? Motors!

## Talons and Sparks? 

In order to control the way each individual motor moves in our robot, we use motor controllers such as the TalonSRX, the Falcon, and the Spark Max.
- Motor controllers take signals from our main RoboRIO chip and feed voltage to the motors, which speeds them up or slows them down. 
- The latter two controllers mentioned above also have the capability of being motor encoders, which take information from the motors, such as the velocity at which they're spinning at or their position. 

## Wheel.java
- In Wheel.java, we showcase code that uses an encoder to take information about the angle of the motor via the getAngle() method.


