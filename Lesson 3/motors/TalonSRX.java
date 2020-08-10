package common.motors;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import common.motors.configs.interfaces.ITalonSRXConfig;
import common.motors.configs.interfaces.ITalonSRXWithEncoderConfig;

public class TalonSRX extends BaseTargetedMotorWithEncoder<TalonSRX, ITalonSRXWithEncoderConfig> {
    protected int sensorPosition;
    protected WPI_TalonSRX talon;
    
    // could potentially make sensor position an optional parameter because getSelectedSensorPosition/Velocity have parameterless overloads
    public TalonSRX(ITalonSRXWithEncoderConfig config) {
        this(config.getMotorConfig());
        talon.setSensorPhase(config.getEncoderConfig().getReversed());
        this.sensorPosition = config.getSensorPosition();
        talon.configSelectedFeedbackSensor(config.getSensorType(), config.getPIDIndex(), config.getTimeout());
        talon.config_kP(0, config.getPIDConfig().getP(), config.getTimeout());
        talon.config_kI(0, config.getPIDConfig().getI(), config.getTimeout());
        talon.config_kD(0, config.getPIDConfig().getD(), config.getTimeout());
        talon.config_IntegralZone(0, (int)config.getPIDConfig().getIZone(), config.getTimeout());
        talon.configAllowableClosedloopError(0, config.getRotationTolerance(), config.getTimeout());

        var startAngle = getOffsetAngle();
        isReversed = startAngle > 90 && startAngle < 270 ? true : false;
    }
    public TalonSRX(ITalonSRXConfig config) {
        super(config);
        talon = new WPI_TalonSRX(config.getPort());
        talon.setInverted(config.getInverted());
        talon.setNeutralMode(config.getNeutralMode());
        talon.configPeakOutputForward(config.getPeakForwardOutput(), config.getTimeout());
        talon.configPeakOutputReverse(config.getPeakReverseOutput(), config.getTimeout());
        talon.configPeakCurrentDuration(config.getPeakCurrentDuration(), config.getTimeout());
        talon.configPeakCurrentLimit(config.getPeakCurrentLimit(), config.getTimeout());
        talon.configContinuousCurrentLimit(config.getContinuousCurrentLimit(), config.getTimeout());
        talon.enableCurrentLimit(config.getCurrentLimitEnabled());
    }

    public double getPIDTarget() {
        return talon.getClosedLoopTarget();
    }

    public void setRawPosition(double ticks) {
        talon.set(ControlMode.Position, ticks);
    }

    public void setOffsetPosition(double ticks) {
        talon.set(ControlMode.Position, ticks + offset);
    }

    public double getRawPosition() {
        return talon.getSelectedSensorPosition(sensorPosition);
    }

    public double getOffsetPosition() {
        return talon.getSelectedSensorPosition(sensorPosition) - offset;
    }

    public void setVelocity(double velocity) {
        talon.set(ControlMode.Velocity, velocity);
    }

    public double getVelocity() {
        return talon.getSelectedSensorVelocity(sensorPosition);
    }

    @Override
    public void setOutput(double percentage) {
        talon.set(percentage);
    }
    
    @Override
    public double getOutput() {
        return talon.get();
    }

    @Override
    public boolean getInverted() {
        return talon.getInverted();
    }

    @Override
    public void setInverted(boolean inverted) {
        talon.setInverted(inverted);
    }

    @Override
    public double getCurrent() {
        return talon.getOutputCurrent();
    }

    @Override
    public void setCurrent(double amps) {
        talon.set(ControlMode.Current, amps);
    }

    @Override
    protected double getTicksPerRotation() {
        return 4096;
    }

}