# Lesson 7 - Logging and Debugging

- Now that we've tackled much of how the robot code interacts with its machinery, it's time to talk about how we make the code better!
    - Using a logging system, we're able to tell what happens when, and whether everything is going to plan.

## SmartDashboard
- A great logger has been provided by FRC for use with our robots called SmartDashboard. 
    - The SmartDashboard class works a bit like a dictionary, where each entry is put in via a function such as putString(k, v) or putNumber(k, v) where k and v represent keys and values to be added respectively. 
    - From there we can either retrieve it using getString (or any similar functions) or using the SmartDashboard gui!