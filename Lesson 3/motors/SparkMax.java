package common.motors;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import common.motors.configs.SparkMaxConfig;
import common.motors.configs.interfaces.IMotorConfig;
import common.motors.configs.interfaces.IMotorWithEncoderConfig;

public class SparkMax extends BaseTargetedMotorWithEncoder<SparkMax, SparkMaxConfig> {

    private CANSparkMax spark;

    public SparkMax(IMotorWithEncoderConfig<SparkMax, IMotorConfig<SparkMax>> config) {
        super(config);
        offset = config.getEncoderConfig().getOffset();
        var pid = spark.getPIDController();
        pid.setP(config.getPIDConfig().getP());
        pid.setI(config.getPIDConfig().getI());
        pid.setD(config.getPIDConfig().getD());
        pid.setIZone(config.getPIDConfig().getIZone());
    }

    public SparkMax(IMotorConfig<SparkMax> config) {
        super(config);
        spark = new CANSparkMax(config.getPort(), MotorType.kBrushless);
        isReversed = false;
        spark.setInverted(config.getInverted());
        spark.setIdleMode(IdleMode.kBrake);
        spark.setCANTimeout(10);
        spark.setOpenLoopRampRate(0.35);
    }

    @Override
    public void setOutput(double percentage) {
        spark.set(percentage);
    }

    @Override
    public double getOutput() {
        return spark.get();
    }

    @Override
    public double getVelocity() {
        return spark.getEncoder().getVelocity();
    }

    @Override
    public void setVelocity(double velocity) {
        spark.getPIDController().setReference(velocity, ControlType.kVelocity);
    }

    @Override
    public void setRawPosition(double ticks) {
        spark.getPIDController().setReference(ticks, ControlType.kPosition);
    }

    @Override
    public void setOffsetPosition(double ticks) {
        spark.getPIDController().setReference(ticks - offset, ControlType.kPosition);
    }

    @Override
    public double getRawPosition() {
        return (double) spark.getEncoder().getPosition();
    }

    @Override
    public double getOffsetPosition() {
        return (double) spark.getEncoder().getPosition() - offset;
    }

    @Override
    public boolean getInverted() {
        return spark.getInverted();
    }

    @Override
    public void setInverted(boolean inverted) {
        spark.setInverted(inverted);
    }

    @Override
    public double getCurrent() {
        return spark.getOutputCurrent();
    }

    @Override
    public void setCurrent(double amps) {
        spark.getPIDController().setReference(amps, ControlType.kCurrent);
    }

    @Override
    protected double getTicksPerRotation() {
        return 1;
    }
}