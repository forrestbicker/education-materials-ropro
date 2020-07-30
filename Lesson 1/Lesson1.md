# Lesson 1 - Environment setup & WPI lib

## Setting up the environment
1) Download VS Code (`https://code.visualstudio.com`)
2) Download Java SE Development Kit 11 (`https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html`). WPILIb expects Java 11 on most systems. If you have a newer version of the Java SDK, either change your java home directory from the newer version to Java 11 or uninstall the new version. If you need help with this, please let me know.
3) Download the respective WPILib release. `https://github.com/wpilibsuite/allwpilib/releases`. See the image attached to see what download you should be looking for.
4) Once you download the files from github, go to `https://frc-docs.readthedocs.io/en/latest/docs/getting-started/getting-started-frc-control-system/wpilib-setup.html`. Go to your respective system (Windows, Mac OS, or Linux) and follow the instructions there. 
5) When you finish step 4, email me your github username so I can add you to the RoHawks github group. 
6) Download the latest github Desktop. `https://desktop.github.com`. Install and sign into your github account.
7) Clone the files from `https://github.com/RoHawks/BaseRobotCode` onto your machine. VS Code has good support for Git so you can use their git clone command.
NOTE) If you are unfamiliar with git or VS Code's environment, email Eric or me when you are free so I can walk you through both downloading the code and making your own changes to it. WPILib docs including a complete installation guide can be referenced at any time at `https://docs.wpilib.org/en/stable/`

## Robot Archetypes

-   TimedRobot
    - The TimedRobot class is the the base class recommended for most users. It provides control of the robot program through a collection of init() and periodic() methods, which are called by WPILib during specific robot states (e.g. autonomous or teleoperated).
-   IterativeRobotBase
    - This is identical to TimedRobot, except the main robot loop is not run automatically - users are required to implement it inside of the startCompetition() method. This gives more freedom for advanced users to handle the loop timing in different ways, but is also less-convenient.
    - Rather than checking the mode and calling the various init() and periodic() methods themselves, user implementations can simply call the loopFunc() method from their main loop implementation. However, the robotInit() method must be called manually.
-   RobotBase
    - The RobotBase class is the most minimal base-class offered, and is generally not recommended for direct use. No robot control flow is handled for the user; everything must be written from scratch inside the startCompetition() method.
- Not Using a Base Class
    - If desired, users can omit a base class entirely and simply write their program in a main() method, as they would for any other program. This is highly discouraged - users should not “reinvent the wheel” when writing their robot code - but it is supported for those who wish to have absolute control over their program flow.

Reference: `https://docs.wpilib.org/en/stable/docs/software/wpilib-overview/creating-robot-program.html#creating-a-robot-program`
