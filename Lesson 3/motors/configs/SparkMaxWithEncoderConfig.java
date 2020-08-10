package common.motors.configs;

import common.encoders.configs.interfaces.IEncoderConfig;
import common.motors.SparkMax;
import common.pid.configs.interfaces.IPIDConfig;

public class SparkMaxWithEncoderConfig extends BaseMotorWithEncoderConfig<SparkMax, SparkMaxConfig> {
    public SparkMaxWithEncoderConfig(SparkMaxConfig motorConfig, IEncoderConfig encoderConfig, IPIDConfig pidConfig) {
        super(motorConfig,encoderConfig,pidConfig);
    }

    @Override
    public SparkMax build() {
        return new SparkMax(this);
    }
}