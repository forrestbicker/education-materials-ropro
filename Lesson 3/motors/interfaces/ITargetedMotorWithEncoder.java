package common.motors.interfaces;

import common.pid.interfaces.ITargetCurrent;

public interface ITargetedMotorWithEncoder {
    // TODO: break into smaller interfaces

    public void setNativePosition(double position);
    public double getNativePosition();

    public void setNativeVelocity(double velocity);
    public double getNativeVelocity();

    public void setReversed(boolean reversed);
    public boolean getReversed();

    public void setTargetPosition(double target);
    public double getTargetPosition();
    public void updatePosition();

    public double getPosition();

    public void setTargetVelocity(double target);
    public double getTargetVelocity();
    public void updateVelocity();
}
