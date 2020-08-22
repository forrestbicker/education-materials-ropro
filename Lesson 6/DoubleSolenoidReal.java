package robotcode.systems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class DoubleSolenoidReal {
	private DoubleSolenoid mDoubleSolenoid;

	public DoubleSolenoidReal(int pInPort, int pOutPort) {
		mDoubleSolenoid = new DoubleSolenoid(pInPort, pOutPort);
	}

	public void set(Value pDirection) {
		mDoubleSolenoid.set(pDirection);
	}

	public Value get() {
		return mDoubleSolenoid.get();
	}

	public void setOpposite() {
		this.set(this.get().equals(Value.kForward) ? Value.kReverse : Value.kForward);
	}

}
