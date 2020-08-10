package common.motors.interfaces;

import common.pid.interfaces.IInvertible;
import common.pid.interfaces.ITargetCurrent;
import common.pid.interfaces.ITargetPercentOutput;

public interface IMotor extends IInvertible, ITargetPercentOutput, ITargetCurrent {
    
}