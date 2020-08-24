package robotcode.driving;

import constants.DriveConstants;
import constants.RunConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import resource.Vector;

/**
 * Performs calculations regarding the swerve drive Given a linear and angular
 * velocity, determines individual wheel velocities
 * 
 * @author 3419
 *
 */
public class SwerveDrive {

	private Vector[] mOffsets;
	private Vector[] mOutputs;

	/**
	 * Initializes a swerve drive calculator
	 * 
	 * @param wheels
	 *            wheels to initialize with; used to compute distances relative to
	 *            robot center
	 */
	public SwerveDrive(final Wheel[] wheels) {
		mOffsets = new Vector[4];
		mOutputs = new Vector[4];
		double sumDistFromCenter = 0;
		for (int i = 0; i < 4; i++) {
			if (RunConstants.IS_PROTOTYPE) {
				mOffsets[i] = new Vector(DriveConstants.PrototypeRobot.X_OFF[i], DriveConstants.PrototypeRobot.Y_OFF[i]);
			} else {
				mOffsets[i] = new Vector(DriveConstants.ActualRobot.X_OFF[i], DriveConstants.ActualRobot.Y_OFF[i]);
			}
			mOutputs[i] = new Vector();
			sumDistFromCenter += mOffsets[i].getMagnitude();
		}

		// normalize all the distances average to 1
		for (int i = 0; i < 4; i++) {
			double scale = 4 * mOffsets[i].getMagnitude() / sumDistFromCenter;
			mOffsets[i].setTotal(scale);
		}
	}

	/**
	 * Calculates wheel vectors to give some linear & angular velocity
	 * 
	 * @param pAngularVelocity
	 *            value between -1 and 1; clockwise is negative, counter-clockwise
	 *            is positive
	 * @param pRobotVelocity
	 *            Vector corresponding to robot velocity: x-axis points forward,
	 *            y-axis points to the right
	 */
	public void calculate(double pAngularVelocity, Vector pRobotVelocity) {
		Vector[] velocities = new Vector[4];
		double maximumLength = 0;
		for (int i = 0; i < 4; i++) {
			double angularComponent_angle = mOffsets[i].getAngle() + 90;
			// angle from center to wheel, +90 for perpendicular
			double angularComponent_speed = mOffsets[i].getMagnitude() * pAngularVelocity;

			Vector angularComponent = Vector.createPolar(angularComponent_angle, angularComponent_speed);
			// if we only used these vectors, would only turn
			Vector velocityComponent = new Vector(pRobotVelocity);
			// if we only used these vectors, would drive linearly

			// add two components together
			velocities[i] = Vector.add(angularComponent, velocityComponent);
			if (velocities[i].getMagnitude() > maximumLength) {
				maximumLength = velocities[i].getMagnitude();
			}
		}

		// if our maximum empirical length is too big, scale it all down
		if (maximumLength > DriveConstants.MAX_INDIVIDUAL_VELOCITY) {
			double velScale = DriveConstants.MAX_INDIVIDUAL_VELOCITY / maximumLength;
			for (int i = 0; i < 4; i++) {
				velocities[i].scaleTotal(velScale);
			}
		}

		for (int i = 0; i < 4; i++) {
			mOutputs[i] = new Vector(velocities[i]);
			SmartDashboard.putNumber("Vector Angle " + i, mOutputs[i].getAngle());
			SmartDashboard.putNumber("Vector Mag " + i, mOutputs[i].getMagnitude());
		}
	}

	/**
	 * Calculates wheel vectors to give some linear & angular velocity while holding
	 * wheel direction towards robot velocity
	 * 
	 * @param pAngularVelocity
	 *            value between -1 and 1; clockwise is negative, counter-clockwise
	 *            is positive
	 * @param pRobotVelocity
	 *            Vector corresponding to robot velocity: x-axis points forward,
	 *            y-axis points to the right
	 */
	public void calculateHoldDirection(double pAngularVelocity, Vector pRobotVelocity) {
		Vector normalizedRobotVel = Vector.normalized(pRobotVelocity);

		Vector[] fakeVelocities = new Vector[4];
		Vector[] velocities = new Vector[4];
		double maximumLength = 0;
		for (int i = 0; i < 4; i++) {
			double angularComponent_angle = mOffsets[i].getAngle() + 90;
			// angle from center to wheel, +90 for perpendicular
			double angularComponent_speed = mOffsets[i].getMagnitude() * pAngularVelocity;

			Vector angularComponent = Vector.createPolar(angularComponent_angle, angularComponent_speed);
			// if we only used these vectors, would only turn

			// add two components together
			fakeVelocities[i] = Vector.add(angularComponent, pRobotVelocity);

			double length = Vector.dot(fakeVelocities[i], normalizedRobotVel);
			velocities[i] = Vector.createPolar(normalizedRobotVel.getAngle(), length);

			if (velocities[i].getMagnitude() > maximumLength) {
				maximumLength = velocities[i].getMagnitude();
			}
		}

		// if our maximum empirical length is too big, scale it all down
		if (maximumLength > DriveConstants.MAX_INDIVIDUAL_VELOCITY) {
			double velScale = DriveConstants.MAX_INDIVIDUAL_VELOCITY / maximumLength;
			for (int i = 0; i < 4; i++) {
				velocities[i].scaleTotal(velScale);
			}
		}

		for (int i = 0; i < 4; i++) {
			mOutputs[i] = new Vector(velocities[i]);
			SmartDashboard.putNumber("Vector Angle " + i, mOutputs[i].getAngle());
			SmartDashboard.putNumber("Vector Mag " + i, mOutputs[i].getMagnitude());
		}
	}

	/**
	 * Gets the previously computed wheel vector
	 * 
	 * @param index
	 *            Which wheel to retrieve the output
	 * @return Output vector for that wheel
	 */
	public Vector getOutput(int index) {
		return mOutputs[index];
	}
}
