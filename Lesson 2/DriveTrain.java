/* 
package robotcode.driving;

import constants.DriveConstants;
import constants.JoystickConstants;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import resource.ResourceFunctions;
import resource.Vector;
import robotcode.LocalJoystick;
import robotcode.pid.GenericPIDOutput;
import robotcode.pid.LocalPIDController;
import sensors.RobotAngle;
*/ 

public class DriveTrain {

    public DriveTrain(LocalJoystick pJoystick) {
		mJoystick = pJoystick;
		mJoystickAngle = 0; 
		mRotationalVel = RotationalVelocity.NONE;

		pidInit();
    }
    
    private RotationalVelocity getRotationalVelocityState() {
		if(mJoystick.getRawButton(JoystickConstants.FinalRobotButtons.LEFT_ROCKET_BOTTOM)
		 || mJoystick.getRawButton(JoystickConstants.FinalRobotButtons.LEFT_ROCKET_TOP)
		 || mJoystick.getRawButton(JoystickConstants.FinalRobotButtons.RIGHT_ROCKET_BOTTOM)
		 || mJoystick.getRawButton(JoystickConstants.FinalRobotButtons.RIGHT_ROCKET_TOP)){
			return RotationalVelocity.SECONDARY;
		 }
		 else if(getLetterAngle() >= 0){
			 return RotationalVelocity.POV;
		 }
		else if (mController.getBumper(Hand.kRight) || mController.getBumper(Hand.kLeft)) {
			return RotationalVelocity.NUDGE;
		} 
		else if (getStickAngularVel() != 0) {
			return RotationalVelocity.NORMAL;
		}
		return RotationalVelocity.NONE;
	}
