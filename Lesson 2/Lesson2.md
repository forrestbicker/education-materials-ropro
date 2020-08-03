# Lesson 2 - User Input and Controls

## The Joystick Class

This is the first class in WPILib we are going to be examining in detail.
- Smart dashboard

We have a LocalJoystick class that allows us to read inputs from a controller and output them in an easier to process form.
- The first thing that it does is set up a few arrays called mButtonsPressed, mButtonsReleased, mTimesPressed, and mTimesReleased. 
	- The first two are stored as boolean arrays that help us know which button (from a list of up to 32 buttons) is being pressed or released, and the last two are long arrays that tell us how many times these buttons have been pressed or released.
- Next, the method getRawButtonPressed() figures out which button is being pressed via the parent class, Joystick (which has been graciously provided by wpilib), converting it to the numbers corresponding to our arrays.


Resources: 
- Joystick Docs - `https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/Joystick.html`
- XboxController Docs - `https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/XboxController.html`

## User Input

-   Code except: Observe how Robot.java reads joystick controls within mechanisms subroutines inside the operatorControl loop

## Where is this used?:

- Our DriveTrain class relies on LocalJoystick to tell if certain buttons are being held down
- The Robot.java also uses it to control a lift mechanism for the robot. 
