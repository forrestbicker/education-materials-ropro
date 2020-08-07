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

    private void TalonPID(double pTarget) {
		double current = ResourceFunctions.tickToAngle(mTurn.getSelectedSensorPosition(0));
		double realCurrent = mEncoder.getAngleDegrees();

		double error = ResourceFunctions.continuousAngleDif(pTarget, ResourceFunctions.putAngleInRange(realCurrent));

		if (Math.abs(error) > 90) {
			mEncoder.setAdd180(!mEncoder.getAdd180());
			mDrive.setInverted(!mDrive.getInverted());
			error = ResourceFunctions.continuousAngleDif(pTarget, realCurrent);
		}
		mTurn.set(ControlMode.Position, ResourceFunctions.angleToTick(current + error));
	}

	private void TalonMotionMagic(double pTarget) { // Have to set velocity, acceleration, and PIDF constants
		double current = ResourceFunctions.tickToAngle(mTurn.getSelectedSensorPosition(0));
		double realCurrent = mEncoder.getAngleDegrees();

		double error = ResourceFunctions.continuousAngleDif(pTarget, ResourceFunctions.putAngleInRange(realCurrent));

		if (Math.abs(error) > 90) {
			mEncoder.setAdd180(!mEncoder.getAdd180());
			mDrive.setInverted(!mDrive.getInverted());
			error = ResourceFunctions.continuousAngleDif(pTarget, realCurrent);
		}
		mTurn.set(ControlMode.MotionMagic, ResourceFunctions.angleToTick(current + error));
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
