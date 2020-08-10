package common.motors.interfaces;

import common.encoders.interfaces.IEncoder;
import common.pid.interfaces.ITargetAngleReversible;

public interface IMotorWithEncoder extends IMotor, IEncoder, ITargetAngleReversible {
}