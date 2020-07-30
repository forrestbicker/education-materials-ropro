public class Robot extends SampleRobot {

    public void startGame() {
        if (!mInGame) {

            if (RunConstants.RUNNING_PNEUMATICS) {
                mCompressor.start();
            }

            if (RunConstants.RUNNING_HATCH) {
                mHatchIntake.contract();
            }

            if (RunConstants.RUNNING_LEADSCREW) {
                mLeadscrew.leadscrewInitialZero();
            }

            mInGame = true;
        }
    }

    public void autonomous() {
        // select auto commands
        ArrayList<AutonomousCommand> autonomousCommands;

        // start game
        startGame();

        // initialize step variables
        int currentStep = 0;
        int previousStep = -1;

        while (isAutonomous() && isEnabled()) {
            if (currentStep < autonomousCommands.size()) {
                AutonomousCommand command = autonomousCommands.get(currentStep);

                if (currentStep != previousStep) {
                    command.startup();
                    previousStep = currentStep;
                }

                boolean moveToNextStep = command.runCommand();
                if (moveToNextStep) {
                    currentStep++;
                }
            } // else we're done with auto

        }
    }

    public void operatorControl() {
        // start game, again
        startGame();

        while (isOperatorControl() && isEnabled()) {
            if (RunConstants.RUNNING_DRIVE) {
                swerveDrive();
            }

            if (RunConstants.RUNNING_HATCH && !RunConstants.RUNNING_LEADSCREW) {
                mHatchIntake.enactMovement();
            }

            if (RunConstants.RUNNING_BALL) {
                mBallIntake.enactMovement();
            }

            if (RunConstants.RUNNING_HATCH && RunConstants.RUNNING_LEADSCREW) {
                mIntake.enactMovement();
                if (mJoystick.getRawButtonReleased(16)) {
                    mHatchIntake.setRotaryOpposite();
                }
            }

            // put info on SmartDashboard
            SmartDashboard.putString("Current State", mCurrentState.toString());
            if (RunConstants.RUNNING_DRIVE) {
                for (int i = 0; i < 4; i++) {
                    SmartDashboard.putNumber("Motor Output Percent " + i, mDrive[i].getMotorOutputPercent());
                }
        }
    }
}