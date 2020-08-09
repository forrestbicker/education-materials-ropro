# Lesson 3 - More About Motors: Servos and PID!

## Servos?

- A servo is a mechanical system that utilizes a certain type of motor that not only takes in commands from the code but also returns feedback about the motor position so error can be accounted for. All in all, it allows for very precise movement while resisting external force applied to the motor.

## PID?

- The PID system, (Proportional, Integral, Derivative) is another feedback loop that ensures everything is working in order and correct error in motor turning.
    - It revolves around finding constants that multiply by the error, its integral, and its derivative to minimize the amount of time this takes and the number of oscillations in voltage it needs to make before settling at a constant amount fed.
