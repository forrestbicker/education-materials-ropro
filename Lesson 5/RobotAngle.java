package sensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class RobotAngle extends RotationInputter {
	AHRS mNavX;
	boolean mReversed;
    PIDSourceType mPIDSourceType = PIDSourceType.kDisplacement;

    public RobotAngle(AHRS pNavX, boolean pReversed, double pOffset) {
		super(pOffset);
		mReversed = pReversed;
		mNavX = pNavX;
    }
    public double getRawAngleDegrees() {
		return mNavX.getAngle() % 360;
    }
    public void reset() {
		mNavX.reset();
	}
}