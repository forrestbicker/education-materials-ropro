@SuppressWarnings
public class Robot extends SampleRobot {
    
    public void startGame() {
		if (!mInGame) {

			if (RunConstants.RUNNING_PNEUMATICS) {
				mCompressor.start();
			}

			if (RunConstants.RUNNING_HATCH){
				mHatchIntake.contract();
			}

			if(RunConstants.RUNNING_LEADSCREW){
				mLeadscrew.leadscrewInitialZero();
			}

			mInGame = true;
		}
    }


	public void autonomous() {
        // select auto commands
        ArrayList<AutonomousCommand> autonomousCommands;

        if (mAutonomousRoutine == AutonomousRoutineType.DEFAULT) {
            autonomousCommands = (new DefaultRoutine(this)).getAutonomousCommands();
        } else {
            autonomousCommands = (new DoNothingRoutine()).getAutonomousCommands();
        }

        // start game
        startGame();

        // initialize step variables
        int currentStep = 0;
        int previousStep = -1;

        while (isAutonomous() && isEnabled()) {
            SmartDashboard.putNumber("Autonomous step", currentStep);

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

            Timer.delay(0.005);
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

            if (RunConstants.RUNNING_LEADSCREW && !RunConstants.RUNNING_HATCH) { // test this then test intake.intake() lol
                mLeadscrew.enactMovement();
                SmartDashboard.putBoolean("Forward Limit Switch Closed", mLeadscrewTalon.getSensorCollection().isFwdLimitSwitchClosed());
                SmartDashboard.putBoolean("Reverse Limit Switch Closed", mLeadscrewTalon.getSensorCollection().isRevLimitSwitchClosed());
                SmartDashboard.putNumber("Leadscrew raw ticks", mLeadscrewEncoder.getRawTicks());
                SmartDashboard.putNumber("leadscrew cooked ticks", mLeadscrewEncoder.getTicksFromEnd());
                SmartDashboard.putNumber("Leadscrew inches", mLeadscrewEncoder.getDistanceInInchesFromEnd());
                SmartDashboard.putNumber("Limelight angle", NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0));
                SmartDashboard.putNumber("Limelight error", CameraConstants.LimelightConstants.HEIGHT * Math.tan(Math.toRadians(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0))));
                SmartDashboard.putNumber("Leadscrew motor goal ticks", mLeadscrewTalon.getClosedLoopTarget());
                SmartDashboard.putNumber("Leadscrew motor goal inches", LeadscrewEncoder.leadscrewTickToInch(mLeadscrewTalon.getClosedLoopTarget()));
                SmartDashboard.putNumber("Leadscrew motor output", mLeadscrewTalon.getMotorOutputPercent());
                SmartDashboard.putNumber("Leadscrew error", mLeadscrewTalon.getClosedLoopError());
            }

            if (RunConstants.RUNNING_BALL) {
                SmartDashboard.putNumber("Ball Holder Raw Ticks", mBallHolder.getSelectedSensorPosition());
                mBallIntake.enactMovement();
            }

            if (RunConstants.RUNNING_HATCH && RunConstants.RUNNING_LEADSCREW) {
                mIntake.enactMovement();
                SmartDashboard.putNumber("is enacting movement", System.currentTimeMillis());
                SmartDashboard.putBoolean("Forward Limit Switch Closed", mLeadscrewTalon.getSensorCollection().isFwdLimitSwitchClosed());
                SmartDashboard.putBoolean("Reverse Limit Switch Closed", mLeadscrewTalon.getSensorCollection().isRevLimitSwitchClosed());
                SmartDashboard.putNumber("Leadscrew raw ticks", mLeadscrewEncoder.getRawTicks());
                SmartDashboard.putNumber("leadscrew cooked ticks", mLeadscrewEncoder.getTicksFromEnd());
                SmartDashboard.putNumber("Leadscrew inches", mLeadscrewEncoder.getDistanceInInchesFromEnd());
                SmartDashboard.putNumber("Limelight angle", NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0));
                SmartDashboard.putNumber("Limelight error", CameraConstants.LimelightConstants.HEIGHT * Math.tan(Math.toRadians(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0))));
                SmartDashboard.putNumber("Leadscrew motor goal ticks", mLeadscrewTalon.getClosedLoopTarget());
                SmartDashboard.putNumber("Leadscrew motor goal inches", LeadscrewEncoder.leadscrewTickToInch(mLeadscrewTalon.getClosedLoopTarget()));
                SmartDashboard.putNumber("Leadscrew motor output", mLeadscrewTalon.getMotorOutputPercent());
                SmartDashboard.putNumber("Leadscrew error", mLeadscrewTalon.getClosedLoopError());
                if (mJoystick.getRawButtonReleased(16)) {
                    mHatchIntake.setRotaryOpposite();
                }
            }

            if (RunConstants.RUNNING_EVERYTHING) {
                doWork();
            }

            // put info on SmartDashboard
            SmartDashboard.putString("Current State", mCurrentState.toString());
            if (RunConstants.RUNNING_DRIVE) {
                for (int i = 0; i < 4; i++) {
                    SmartDashboard.putNumber("Motor Output Percent " + i, mDrive[i].getMotorOutputPercent());
                }
            }

            mJoystick.updateProfile();
            SmartDashboard.putNumber("JOYSTICK PROFILE NUMBER", mJoystick.getProfile());
            SmartDashboard.putString("JOYSTICK PROFILE", (mJoystick.getProfile() == 0) ? "HATCH/LEADSCREW" : "BALL");
            SmartDashboard.putBoolean("BUTTON 21 XD", mJoystick.getRawButton(21));

            Timer.delay(0.005); // wait for a motor update time
        }
    }

    private void doWork() {
        switch (mCurrentState) {
        case DEFAULT:
            doSomeAction();
            break;
        default:
            throw new RuntimeException("Unknown state");
        }

        SmartDashboard.putString("Current State", mCurrentState.name());
    }
}