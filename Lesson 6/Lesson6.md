# Lesson 6 - Compressors and Solenoids

- Compressors and Solenoids make up the pneumatics part of the robot, which take compressed air and use it to exert a large amount of work. 

## Compressors
- The compressors take in air around the robot and pressurizes it for use around the robot. 
- The Compressor class is a SendableBase class for operating a compressor connected to a PCM (Pneumatic Control Module). The PCM will automatically run in closed loop mode by default whenever a Solenoid object is created. For most cases, a Compressor object does not need to be instantiated or used in a robot program. This class is only required in cases where the robot program needs a more detailed status of the compressor or to enable/disable closed loop control.


## Solenoids
- The solenoid valves use voltage supplied to the pneumatics system to change the flow of air out of the compressors. 

## Uses!
- Shooting: Although we don't currently use pneumatics for shooting, using compressed air pistons can be a very effective way to shoot balls from our robot.
- Climbing: Because of the high force that pneumatics can exert, pneumatics works perfectly for raising the robot into the air and grappling onto the climbing stations. 


# Resources:
- FRC Pneumatics Manual: https://firstfrc.blob.core.windows.net/frc2017/pneumatics-manual.pdf