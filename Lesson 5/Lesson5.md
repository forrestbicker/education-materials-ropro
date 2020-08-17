# Lesson 3 - Vision!

## Limelight: 
Limelights are the eyes of our robot. They're essentially cameras that are good at finding simple shapes and colors and telling us information about them. The limelight only really works reliably when the thing you are looking for is highly reflective, so the GDC usually plasters reflective tape near all the major scoring or pickup stations so we can use the limelight to align our mechanisms properly.

If we are able to find a target in the limelight viewfinder, we can read the target coordinates using the NetworkTable API and set a PID to align the limelight until the target is in the desired position. This technique allows us to easily target a shooting mechanism to shoot into a goal or center our swerve drive to position at the exact spot for game piece pickup, all with the push of a button.

With a simple call to the NetworkTable as shown in the java snippet, we can retrieve the folowing information:

- `tv`: Whether the limelight has any valid targets (0 or 1)
- `tx`: Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees)
- `ty`: Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
- `ta`: Target Area (0% of image to 100% of image)
- `ts`: Skew or rotation (-90 degrees to 0 degrees)
- `tl`: The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
- `tshort`: Sidelength of shortest side of the fitted bounding box (pixels)
- `tlong`: Sidelength of longest side of the fitted bounding box (pixels)
- `thor`: Horizontal sidelength of the rough bounding box (0 - 320 pixels)
- `tvert`: Vertical sidelength of the rough bounding box (0 - 320 pixels)
- `getpipe`: True active pipeline index of the camera (0 .. 9)
- `camtran`: Results of a 3D position solution, 6 numbers: Translation (x,y,y) Rotation(pitch,yaw,roll)


## Gyroscopes:
- In order to determine the position of the robot rotation-wise, we use gyroscopes located on a NavX navigation sensor. Code-wise, much of getting data from the NavX is already provided in their libraries. Check out our RobotAngle.java!





## Resources:

- LimeLight Info: https://docs.limelightvision.io/en/latest/
- NavX User Guide: https://andymark-weblinc.netdna-ssl.com/media/W1siZiIsIjIwMTkvMDcvMTIvMTEvNTUvMjcvNzk5MjJlNWEtYWJmOC00YTFhLWJiYjEtNjU4NDUxMTczZTAzL25hdlgtTVhQIFJvYm90aWNzIE5hdmlnYXRpb24gU2Vuc29yIFVzZXIgR3VpZGUucGRmIl1d/navX-MXP%20Robotics%20Navigation%20Sensor%20User%20Guide.pdf?sha=91f86dbf3ff65c35