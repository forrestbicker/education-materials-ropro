package common.motors.configs.interfaces;

import common.encoders.configs.interfaces.IEncoderConfig;
import common.motors.interfaces.IMotorWithEncoder;
import common.pid.configs.interfaces.IPIDConfig;

public interface IMotorWithEncoderConfig<TMotor extends IMotorWithEncoder, TMotorConfig extends IMotorConfig<TMotor>> extends IMotorConfig<TMotor> {
    TMotorConfig getMotorConfig();

    IEncoderConfig getEncoderConfig();

    IPIDConfig getPIDConfig();

    TMotor build();
}