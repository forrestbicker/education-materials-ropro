WPILib docs - https://docs.wpilib.org/en/stable/

Robot Archetypes

-   TimedRobot
    - The TimedRobot class is the the base class recommended for most users. It provides control of the robot program through a collection of init() and periodic() methods, which are called by WPILib during specific robot states (e.g. autonomous or teleoperated).
-   IterativeRobotBase
    - This is identical to TimedRobot, except the main robot loop is not run automatically - users are required to implement it inside of the startCompetition() method. This gives more freedom for advanced users to handle the loop timing in different ways, but is also less-convenient.
    - Rather than checking the mode and calling the various init() and periodic() methods themselves, user implementations can simply call the loopFunc() method from their main loop implementation. However, the robotInit() method must be called manually.
-   RobotBase
    - The RobotBase class is the most minimal base-class offered, and is generally not recommended for direct use. No robot control flow is handled for the user; everything must be written from scratch inside the startCompetition() method.
- Not Using a Base Class
    - If desired, users can omit a base class entirely and simply write their program in a main() method, as they would for any other program. This is highly discouraged - users should not “reinvent the wheel” when writing their robot code - but it is supported for those who wish to have absolute control over their program flow.

`https://docs.wpilib.org/en/stable/docs/software/wpilib-overview/creating-robot-program.html#creating-a-robot-program`
