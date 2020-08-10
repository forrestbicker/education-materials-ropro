package common.motors.configs.interfaces;

import common.motors.interfaces.IMotor;

public interface IMotorConfig<TMotor extends IMotor> {
    boolean getInverted();
    int getPort();
    TMotor build();
}