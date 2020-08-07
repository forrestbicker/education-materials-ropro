package robotcode.driving;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

import constants.DriveConstants;
import resource.ResourceFunctions;
import resource.Vector;
import sensors.TalonAbsoluteEncoder;

public class Wheel {

	private WPI_TalonSRX mTurn;
	private CANSparkMax mDrive;
	private TalonAbsoluteEncoder mEncoder;

	public Wheel(WPI_TalonSRX pTurn, CANSparkMax pDrive, TalonAbsoluteEncoder pEncoder) {
		mTurn = pTurn;
		mDrive = pDrive;
		mEncoder = pEncoder;
	}

	/**
	 * Set wheel angle & speed
	 * 
	 * @param pWheelVelocity Vector of wheel velocity
	 */
	public void set(Vector pWheelVelocity) {
		set(pWheelVelocity.getAngle(), pWheelVelocity.getMagnitude());
	}

	/**
	 * Set wheel angle & speed
	 * 
	 * @param angle direction to point the wheel
	 * @param speed magnitude to drive the wheel
	 */
	public void set(double angle, double speed) {
		setAngle(angle);
		setLinearVelocity(speed);
	}

	public void setLinearVelocity(double pSpeed) {
		double speed = Math.signum(pSpeed) * Math.min(Math.abs(pSpeed), DriveConstants.MAX_LINEAR_VELOCITY);
		mDrive.set(speed);
	}

	public void setAngle(double pAngle) {
		TalonPID(pAngle);
		// TalonMotionMagic(pAngle);
	}

	public double getAngle() {
		return mEncoder.getAngleDegrees();
	}

	public boolean isInRange(double pTarget) {
		double realCurrent = mEncoder.getAngleDegrees();
		double error = ResourceFunctions.continuousAngleDif(pTarget, ResourceFunctions.putAngleInRange(realCurrent));
		return Math.abs(error) < DriveConstants.ActualRobot.ROTATION_TOLERANCE[0];
	}