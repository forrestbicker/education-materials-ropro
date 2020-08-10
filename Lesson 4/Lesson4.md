# Lesson 3 - More About Motors: Servos and PID!

## Servos?

- A servo is a mechanical system that utilizes a certain type of motor that not only takes in commands from the code but also returns feedback about the motor position so error can be accounted for. All in all, it allows for very precise movement while resisting external force applied to the motor.
- You can think of a servo as just a simple motor used for percision rather than speed. Because of this it is often used for targeting shooting mechanisms or aligning the robot for routines that require near-perfect positioning i.e. climbing
- Servos also run off the PWM so they're less resource intensive

## PID?

- The PID system, (Proportional, Integral, Derivative) is another feedback loop that ensures everything is working in order and correct error in motor turning.
    - It revolves around finding constants that multiply by the error, its integral, and its derivative to minimize the amount of time this takes and the number of oscillations in voltage it needs to make before settling at a constant amount fed.
- For PID, we implement "feedforward", which aims to predict what the needed correction will need to be at each step to lower the downtime dedicated to calculations.

### PID Lingo
Set Point: The set point is normally a user entered value, in cruise control it would be the set speed, or for a heating system, it would be the set temperature.
Process Value: The process value is the value that is being controlled. For cruise control this would be the actual vehicle speed, or in a heating system, this would be the current temperature of the system.
Output: The output is the controlled value of a PID controller. In cruise control, the output would be the throttle valve, in a heating system, the output might be a 3 way valve in a heating loop, or the amount of fuel applied to a boiler.
Error: The error value is the value used by the PID controller to determine the how to manipulate the output to bring the process value to the set point.
Error = Setpoint – Process Value
Gain: Gain is the term used for “multiplication factor”. By adjusting the gain settings (or multiplication factor) of the proportional, the integral and the derivative, the user can control how much effect the PID controller has on the output, and how the controller will react to different changes in the process value.

### Tuning the PID
The equation for PID control is output = P×error+I×∑error+D×δerror/δt
- *P* Proportional control is using a predetermined constant (kP) to control how much a mechanism can move. Every time the PID code is run, the error is calculated, and the proportional gain is multiplied to this. In the car analogy, lets say the pressure you were applying was inversely proportional to the distance you were from the stop sign. P = 1/(error^2) and error is distance from the stop sign. (Note, P is 1/error^2 because you want the output to be 1/error.) Using this P value, we apply more pressure the closer we get, causing us to slow down. Using only Proportional control can be done, and is usually better for slow moving mechanisms, or mechanisms where you don’t need com
- *I* When controlling a motor with just P, you may find that it oscillates. This happens because it has too much power when it gets to the setpoint, and it overshoots. Then when it tries to correct, it overshoots again, and this cycle continues. One way to reduce this is to lower P. This could have some bad side effects though. By reducing P, your motor may not get all the way to where you want it to. It may be off by a few degrees or rotations. To overcome this steady-state error, an Integral gain is introduced. If you’ve taken calculus, you know the integral is the area under a curve or line. It’s the same with PID. The Integral gain is the sum of all the past error. This means the gain will increase more and more the longer the motor isn’t where it’s supposed to be. Even though this reduces steady state error, it may increase settling time. If you notice it oscillating a little bit before settling, you may need a Derivate gain.
- *D* Derivative gain works by calculating the change in error. By finding this change, it can predict future system behavior, and reduce settling time. It does this by applying a brake more or less. This can be useful if it is imperative that you don’t overshoot. This isn’t even used in the industry much, but if you find yourself with long settling times, it may help to introduce a Derivative gain.



Resources: `https://pidexplained.com/pid-controller-explained/`
`https://frc-pdr.readthedocs.io/en/latest/control/pid_control.htm`