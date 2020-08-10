package common.motors.configs.interfaces;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import common.motors.TalonSRX;

public interface ITalonSRXWithEncoderConfig extends ITalonSRXConfig, IMotorWithEncoderConfig<TalonSRX, ITalonSRXConfig> {
    int getSensorPosition();
    int getRotationTolerance();
    int getPIDIndex();
    FeedbackDevice getSensorType();
}