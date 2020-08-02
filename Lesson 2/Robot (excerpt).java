/*
 * This is our Robot.java! (At least, a part of it.) There are three methods here that: 1.
 * Initialize and (reset?) the positions for the pneumatics, intake, and leadscrews 2. Begin the
 * autonomous loop of the robot, which is activated for the first few seconds of competition where
 * human players do not control the robot 3. Begin the teleoperated loop of the robot, which is
 * activated when humans begin to pick up their controllers to move the robot manually.
 */
public class Robot extends SampleRobot {

	private void runLift() {
		//check secondary for speed change
		if (mJoystick.getRawButtonReleased(mConfig.LIFTER.SPEED_UP_BUTTON)) {
			liftOutput += mConfig.LIFTER.SPEED_INCREMENT;
		} else if (mJoystick.getRawButtonReleased(mConfig.LIFTER.SPEED_DOWN_BUTTON)) {
			liftOutput -= mConfig.LIFTER.SPEED_INCREMENT;
		}

		if (mJoystick.getRawButton(mConfig.LIFTER.DRIVE_BUTTON)) {
			liftMotor.setOutput(liftOutput);
		} else if (mJoystick.getRawButton(mConfig.LIFTER.REVERSE_BUTTON)) {
			liftMotor.setOutput(-liftOutput);

		} else {
			liftMotor.setOutput(0);
		}
		SmartDashboard.putNumber("Lift speed", liftMotor.getOutput());
		SmartDashboard.putNumber("Lift speed we will set it to", liftOutput);
		SmartDashboard.putNumber("Lift motor current draw", liftMotor.getCurrent());
	}

	public void runServo() {
		if (mJoystick.getRawButton(mConfig.SHOOTER.HOOD.CLOCKWISE_BUTTON)) {
			adjustableHoodServo.setOutput(1);
		} else if (mJoystick.getRawButton(mConfig.SHOOTER.HOOD.COUNTERCLOCKWISE_BUTTON)) {
			adjustableHoodServo.setOutput(-1);
		} else {
			adjustableHoodServo.setOutput(0);
		}

		SmartDashboard.putNumber("adjustableHoodServo speed", adjustableHoodServo.getOutput());
		SmartDashboard.putNumber("adjustableHoodServo speed we will set it to", adjustableHoodSerOutput);
	}

	public void runSolenoid() {
		if (mJoystick.getRawButtonPressed(2)) { //TODO: put button in config
			rotaryPistonSolenoid.setOpposite();
		}
	}
}
