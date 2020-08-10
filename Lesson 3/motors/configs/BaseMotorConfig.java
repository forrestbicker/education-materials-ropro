package common.motors.configs;

import common.motors.configs.interfaces.IMotorConfig;
import common.motors.interfaces.IMotor;

public abstract class BaseMotorConfig<TMotor extends IMotor> implements IMotorConfig<TMotor> {
    protected final boolean inverted;
    protected final int port;

    public BaseMotorConfig(int port, boolean inverted) {
        this.port = port;
        this.inverted = inverted;
    }
    public BaseMotorConfig(IMotorConfig<TMotor> config) {
        this.port = config.getPort();
        this.inverted = config.getInverted();
    }

    @Override
    public boolean getInverted() {
        return inverted;
    }

    @Override
    public int getPort() {
        return port;
    }
}