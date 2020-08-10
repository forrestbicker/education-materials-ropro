package common.motors.configs;

import common.encoders.configs.interfaces.IEncoderConfig;
import common.motors.configs.interfaces.IMotorConfig;
import common.motors.configs.interfaces.IMotorWithEncoderConfig;
import common.motors.interfaces.IMotorWithEncoder;
import common.pid.configs.interfaces.IPIDConfig;

public abstract class BaseMotorWithEncoderConfig<TMotor extends IMotorWithEncoder, TMotorConfig extends IMotorConfig<TMotor>>
                      extends BaseMotorConfig<TMotor> 
                      implements IMotorWithEncoderConfig<TMotor, TMotorConfig> {
    
    protected final TMotorConfig motorConfig;
    protected final IEncoderConfig encoderConfig;
    protected final IPIDConfig pidConfig;

    public BaseMotorWithEncoderConfig(TMotorConfig motorConfig, IEncoderConfig encoderConfig, IPIDConfig pidConfig) {
        super(motorConfig);
        this.motorConfig = motorConfig;
        this.encoderConfig = encoderConfig;
        this.pidConfig = pidConfig;
    }

    @Override
    public TMotorConfig getMotorConfig() {
        return motorConfig;
    }

    @Override
    public IEncoderConfig getEncoderConfig() {
        return encoderConfig;
    }

    @Override
    public IPIDConfig getPIDConfig() {
        return pidConfig;
    }
}