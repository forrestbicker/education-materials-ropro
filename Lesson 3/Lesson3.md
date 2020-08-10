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


## Wheel.java
- In Wheel.java, we showcase code that uses an encoder to take information about the angle of the motor via the getAngle() method. We also see instructions being sent to the SPARK and Talon motors to move them around and change the speed at which they are moving. (The calculations needed to determine how much voltage is supplied has already been built into the motor libraries.


