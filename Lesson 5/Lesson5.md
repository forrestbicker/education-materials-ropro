# Lesson 3 - Vision!

## Limelight: 
Limelights are the eyes of our robot. They're essentially cameras that are good at finding simple shapes and colors and telling us information about them. The limelight only really works reliably when the thing you are looking for is highly reflective, so the GDC usually plasters reflective tape near all the major scoring or pickup stations so we can use the limelight to align our mechanisms properly.

If we are able to find a target in the limelight viewfinder, we can read the target coordinates using the NetworkTable API and set a PID to align the limelight until the target is in the desired position. This technique allows us to easily target a shooting mechanism to shoot into a goal or center our swerve drive to position at the exact spot for game peice pickup, all with the push of a button.

## Gyroscopes:
- In order to determine the position of the robot rotation-wise, we use gyroscopes located on a NavX navigation sensor. Code-wise, much of getting data from the NavX is already provided in their libraries. Check out our RobotAngle.java!





## Resources:

- LimeLight Info: https://docs.limelightvision.io/en/latest/
- NavX User Guide: https://andymark-weblinc.netdna-ssl.com/media/W1siZiIsIjIwMTkvMDcvMTIvMTEvNTUvMjcvNzk5MjJlNWEtYWJmOC00YTFhLWJiYjEtNjU4NDUxMTczZTAzL25hdlgtTVhQIFJvYm90aWNzIE5hdmlnYXRpb24gU2Vuc29yIFVzZXIgR3VpZGUucGRmIl1d/navX-MXP%20Robotics%20Navigation%20Sensor%20User%20Guide.pdf?sha=91f86dbf3ff65c35