package robotcode.pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

public class SingleSolenoidReal implements SolenoidInterface {
	private Solenoid mSingleSolenoid;
	private Value mCurrent;

	public SingleSolenoidReal(int pPort) {
		mSingleSolenoid = new Solenoid(pPort);
		mCurrent = this.getActual();
    }
    
	public void set(Value pDirection) {
		if(pDirection != mCurrent){
			// forward maps to true, backward maps to false
			mSingleSolenoid.set(pDirection == Value.kForward);
			mCurrent = pDirection;
		}
	}

	public Value get() {
		return mCurrent;
	}

	public Value getActual() {
		return mSingleSolenoid.get() ? Value.kForward : Value.kReverse;

	}

	public void setOpposite() {
		this.set((this.get() == Value.kForward) ? Value.kReverse : Value.kForward);
	}

}