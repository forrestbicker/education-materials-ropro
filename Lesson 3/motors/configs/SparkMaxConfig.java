package common.motors.configs;

import common.motors.SparkMax;

public class SparkMaxConfig extends BaseMotorConfig<SparkMax> {
    public SparkMaxConfig(int port, boolean inverted) {
        super(port,inverted);
    }

    @Override
    public SparkMax build() {
        return new SparkMax(this);
    }
}