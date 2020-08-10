package common.motors.configs.interfaces;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import common.motors.TalonSRX;

public interface ITalonSRXConfig extends IMotorConfig<TalonSRX> {
    int getTimeout();
    NeutralMode getNeutralMode();
    int getPeakForwardOutput();
    int getPeakReverseOutput();
    int getPeakCurrentDuration();
    int getPeakCurrentLimit();
    int getContinuousCurrentLimit();
    boolean getCurrentLimitEnabled();
}