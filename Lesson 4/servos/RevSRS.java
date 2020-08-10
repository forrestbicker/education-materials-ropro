package common.servos;

import common.servos.configs.interfaces.IPWMConfig;
import common.servos.interfaces.IAngularServo;
import common.servos.interfaces.IContinuousServo;
import edu.wpi.first.wpilibj.PWM;

/**
 * REV Robotics Smart Robot Servo.
 */
public class RevSRS extends PWM implements IAngularServo, IContinuousServo {
    public enum Mode {
        Continuous,
        Angular
    }

    private Mode mode;
    private final double minPeriod = .5d; //milliseconds
    private final double maxPeriod = 2.5d;
    private final double centerPeriod = (maxPeriod - minPeriod) / 2d + minPeriod;
    private final double deadbandMargin = 0.1d;

    private final double maxSpeed = 1d;
    private final double minSpeed = -0.998d;

    private final double minAngle = -90d; //is this 130 for our servo?
    private final double maxAngle = 90d;

    public RevSRS(IPWMConfig config, Mode mode) {
        super(config.getChannel());
        this.mode = mode;
        setBounds(maxPeriod, 
                  centerPeriod + deadbandMargin, 
                  centerPeriod, 
                  centerPeriod - deadbandMargin, 
                  minPeriod);
    }

    @Override
    public void setRawAngle(double angle) {
        if(mode != Mode.Angular) return;
        //scale angle into 0 to 1 range
        setPosition((angle - minAngle) / (maxAngle - minAngle));
    }

    @Override
    public double getRawAngle() {
        if(mode != Mode.Angular) return 0;
        //scale back into angle
        return minAngle + getPosition() * (maxAngle - minAngle);
    }

    @Override
    public void setOutput(double output) {
        if(mode != Mode.Continuous) return;
        if(output < minSpeed) output = minSpeed;
        else if(output > maxSpeed) output = maxSpeed;
        setSpeed(output);
    }

    @Override
    public double getOutput() {
        if(mode != Mode.Continuous) return 0;
        return getSpeed();
    }
}