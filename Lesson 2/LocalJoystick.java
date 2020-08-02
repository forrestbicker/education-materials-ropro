import java.util.Arrays;

import constants.JoystickConstants;
import constants.RunConstants;
import edu.wpi.first.wpilibj.Joystick;

public class LocalJoystick extends Joystick {

    private int mProfiles = JoystickConstants.NUM_PROFILES;
    private int mCurrentProfile = 0;

    private boolean[] mButtonsReleased = new boolean[32];
    private boolean[] mButtonsPressed = new boolean[32];

    private long[] mTimesReleased = new long[32];
    private long[] mTimesPressed = new long[32];

    public LocalJoystick(int port) {
        super(port);

        Arrays.fill(mButtonsReleased, false);
        Arrays.fill(mButtonsPressed, false);

        Arrays.fill(mTimesReleased, 0);
        Arrays.fill(mTimesPressed, 0);
    }
    public boolean getRawButtonPressed(int pButton) {
        int realButton = pButton - mCurrentProfile * 10;   
        if (realButton <= 1 || realButton > 11) {
            return false;
        }
        return super.getRawButtonPressed(realButton);
    }
}