# Lesson 3 - Motors. Motors? Motors!

## Talons and Sparks? 

In order to control the way each individual motor moves in our robot, we use motor controllers such as the TalonSRX, the Falcon, and the Spark Max.
- Motor controllers take signals from our main RoboRIO chip and feed voltage to the motors, which speeds them up or slows them down. 
- The latter two controllers mentioned above also have the capability of being motor encoders, which take information from the motors, such as the velocity at which they're spinning at or their position. 

## Motors and Encoders Hierarchy
|—>Wheel
    |—> DriveMotor
        |—> Motor 
            |—> setOutput()
    |—> TurnMotor
        |—> Motor 
            |—> setOutput()
        |—> Encoder
            |—> Angle
                |—> getReversed()
                |—> setReversed()
                |—> getRawAngle()
                |—> setRawAngle()
                |—> getAngle()
                |—> setAngle()
            |—> Position
                |—> getPosition()
                |—> setPosition()
            |—> Velocity
                |—> getVelocity()
                |—> setVelocity()
Motors with encoders can measure their position in "ticks" a unit of rotation similar to degrees. The number of ticks in a full rotation depends on the motor, falcons use 2048, talons use 4096, sparks use 1. We can take the 360 * (ticks % ticksPerRotation) / ticksPerRotation = angle in degrees.

When a wheel points in a direction, say at point A, and needs to rotate to a new user input at point B there are 4 possible ways the wheel can rotate. The wheel can of course rotate clockwise or counterclockwise, that's 2. To understand the next 2 you have to first conceptualize the idea of wheel faces. A car in drive mode will have it's wheels spin forwards, in reverse the wheels spin reverse. On a swerve drive, the motor that controls forward/reversed motion is the DRIVE MOTOR, whereas the TURN MOTOR controls clockwise or counterclockwise rotation of the direction of the wheel (in other words steering). Now, if we have our robot wheels pointing North, we pick up a game piece and now want to make a complete 180 and start driving backwards. We can choose to go clockwise or counterclockwise on the TURN MOTOR, but either way we will have to make an entire 180° turn. That's a lot of energy and valuable time wasted. What if instead, we just invert the rotation of the Drive Motor which is just an instant software switch (well, technically it takes as long as one roborio cycle to update). We save a lot of valuable time and minimize turning. That's the idea of reversing a wheel's drive motor, and it introduces the final 2 options. Reverse drive motor and spin clockwise, reverse drive motor and spin counterclockwise. We algorithmically determine the shortest path all within a simple motor abstraction method.


## Wheel.java
- In Wheel.java, we showcase code that uses an encoder to take information about the angle of the motor via the getAngle() method. We also see instructions being sent to the SPARK and Talon motors to move them around and change the speed at which they are moving. (The calculations needed to determine how much voltage is supplied has already been built into the motor libraries.


