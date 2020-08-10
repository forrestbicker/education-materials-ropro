package common.motors.configs;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import common.motors.TalonSRX;
import common.motors.configs.interfaces.ITalonSRXConfig;

public class TalonSRXConfig extends BaseMotorConfig<TalonSRX> implements ITalonSRXConfig {
    //These are the default values.  Default current limits are taken from Talon documentation here: http://www.ctr-electronics.com/downloads/pdf/Victor-SP-Talon-SRX-Info-Sheet.pdf
    protected boolean currentLimitEnabled = true;
    protected NeutralMode neutralMode = NeutralMode.Brake;
    protected int peakForwardOutput = 1;
    protected int peakReverseOutput = -1;
    protected int peakCurrentLimit = 100; //amps
    protected int peakCurrentDuration = 2000; //milliseconds
    protected int continuousCurrentLimit = 60; //amps
    protected int timeout = 10; //milliseconds

    public TalonSRXConfig(ITalonSRXConfig original) {
        super(original.getPort(), original.getInverted());
        currentLimitEnabled = original.getCurrentLimitEnabled();
        neutralMode = original.getNeutralMode();
        peakForwardOutput = original.getPeakForwardOutput();
        peakReverseOutput = original.getPeakReverseOutput();
        peakCurrentLimit = original.getPeakCurrentLimit();
        peakCurrentDuration = original.getPeakCurrentDuration();
        continuousCurrentLimit = original.getContinuousCurrentLimit();
        timeout = original.getTimeout();
    }
    public TalonSRXConfig(int port, boolean inverted) {
        super(port, inverted);
    }

    @Override
    public TalonSRX build() {
        return new TalonSRX(this);
    }

    @Override
    public int getTimeout() {
        return timeout;
    }
    public TalonSRXConfig setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    @Override
    public NeutralMode getNeutralMode() {
        return neutralMode;
    }
    public TalonSRXConfig setNeutralMode(NeutralMode mode) {
        neutralMode = mode;
        return this;
    }

    @Override
    public int getPeakForwardOutput() {
        return peakForwardOutput;
    }
    public TalonSRXConfig setPeakForwardOutput(int output) {
        peakForwardOutput = output;
        return this;
    }

    @Override
    public int getPeakReverseOutput() {
        return peakReverseOutput;
    }
    public TalonSRXConfig setPeakReverseOutput(int output) {
        peakReverseOutput = output;
        return this;
    }

    @Override
    public int getPeakCurrentDuration() {
        return peakCurrentDuration;
    }
    public TalonSRXConfig setPeakCurrentDuration(int duration) {
        peakCurrentDuration = duration;
        return this;
    }

    @Override
    public int getPeakCurrentLimit() {
        return peakCurrentLimit;
    }
    public TalonSRXConfig setPeakCurrentLimit(int limit) {
        peakCurrentLimit = limit;
        return this;
    }

    @Override
    public int getContinuousCurrentLimit() {
        return continuousCurrentLimit;
    }
    public TalonSRXConfig setContinuousCurrentLimit(int limit) {
        continuousCurrentLimit = limit;
        return this;
    }

    @Override
    public boolean getCurrentLimitEnabled() {
		return currentLimitEnabled;
    }
    public TalonSRXConfig useCurrentLimit(boolean enabled) {
        currentLimitEnabled = enabled;
        return this;
    }
}