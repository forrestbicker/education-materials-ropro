package robotcode.driving;

import constants.DriveConstants;
import constants.RunConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import resource.Vector;

public class SwerveDrive {
	private Vector[] mOffsets;
	private Vector[] mOutputs;

	public void calculateHoldDirection(double pAngularVelocity, Vector pRobotVelocity) {
		Vector normalizedRobotVel = Vector.normalized(pRobotVelocity);

		Vector[] fakeVelocities = new Vector[4];
		Vector[] velocities = new Vector[4];
		double maximumLength = 0;
		for (int i = 0; i < 4; i++) {
			double angularComponent_angle = mOffsets[i].getAngle() + 90;
			double angularComponent_speed = mOffsets[i].getMagnitude() * pAngularVelocity;

			Vector angularComponent = Vector.createPolar(angularComponent_angle, angularComponent_speed);
			fakeVelocities[i] = Vector.add(angularComponent, pRobotVelocity);

			double length = Vector.dot(fakeVelocities[i], normalizedRobotVel);
			velocities[i] = Vector.createPolar(normalizedRobotVel.getAngle(), length);

			if (velocities[i].getMagnitude() > maximumLength) {
				maximumLength = velocities[i].getMagnitude();
			}
		}
		if (maximumLength > DriveConstants.MAX_INDIVIDUAL_VELOCITY) {
			double velScale = DriveConstants.MAX_INDIVIDUAL_VELOCITY / maximumLength;
			for (int i = 0; i < 4; i++) {
				velocities[i].scaleTotal(velScale);
			}
		}

		for (int i = 0; i < 4; i++) {
			mOutputs[i] = new Vector(velocities[i]);
		}
	}
}