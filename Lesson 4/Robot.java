public class Robot {

}

    public void runServo() {
		if (mJoystick.getRawButton(mConfig.SHOOTER.HOOD.CLOCKWISE_BUTTON)) {
			adjustableHoodServo.setOutput(1);
		} else if (mJoystick.getRawButton(mConfig.SHOOTER.HOOD.COUNTERCLOCKWISE_BUTTON)) {
			adjustableHoodServo.setOutput(-1);
		} else {
			adjustableHoodServo.setOutput(0);
        }}
