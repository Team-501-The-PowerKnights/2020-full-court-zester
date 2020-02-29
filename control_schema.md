# Controls Layout / Interface

## Types of Inputs
- [button] - button, active high
- [switch] - two pole switch, active high
- [analog] - analog value

## Driver
- [analog] (Left Joystick U-D): Drivetrain Speed (forward/backward)
- [analog] (Right Joystick L-R): Drivetrain Turn (left/right)
- [button] (Right Bumper): Drivetrain Crawl Enabled *{whenHeld}*
- ~~xxxxx : Drive Train Turbo Enabled *{whenHeld}*~~
- [button] (???) Switch Drive Direction
- [button] (Left Bumper): Toggle Between Intake Deploy/Stow
- [analog] (Left Trigger): Intake In & Hopper
- [analog] (Right Trigger): Intake Out & Hopper

## Operator
- [button] (button 1): Fire Pose *{whenHeld}*
  - if shooter is not revved up, rev up shooter
  - run intake and hopper
  - advance balls via ballevator into shooter
- [switch] (button 2): Rev Shooter *{whenHeld}*
  - use PID and selected set_point
  - else idle shooter at constant preset in code
- [button] (button 3): Select Field Position full-court *{whenPressed}*
  - determine set_point for turret rotation
  - determines set_point for shooter speed
- [button] (button 4): Select Field Position trench *{whenPressed}*
  - determines set_point for turret rotation
  - determines set_point for shooter speed
- [button] (button 5): Select Field Position mid-field *{whenPressed}*
  - determines set_point for turret rotation
  - determines set_point for shooter speed
- [button] (button 6): Ballevator up *{whenHeld}*
  - cancels Fire Pose (but leaves shooter running)
  - button 6 & 7 simultaneous cancel
- [button] (button 7): Ballevator down *{whenHeld}*
  - cancels Fire Pose (but leaves shooter running)
  - button 6 & 7 simultaneous cancel
- [button] (buttons 8): Select Turret Orientation back *{whenPressed}*
  - determines set_point for turret rotation
  - default
- [button] (button 9): Select Turret Orientation right *{whenPressed}*
  - determines set_point for turret rotation
- [button] (button 10) Select Turret Orientation front *{whenPressed}*
  - determines set_point for turret rotation
- [analog] (pot 2): Turret fine angle adjustment off of baseline
  - +/- range **~3** degrees
  - funky start enable processing
- [analog] (pot 3): Shooter RPM fine adjustment off of baseline
  - +/- range **~50** RPM
  - funky start enable processing
- [button] (buttons 11): Turret jog *{whenHeld}*
  - clockwise at constant preset in code
  - additive to final turret rotation value
  - stays from one shot sequence to next
- [button] (button 12): Turret jog *{whenHeld}*
  - counter-clockwise at constant preset in code
  - additive to final turret rotation value
  - stays from one shot sequence to next
- [switch] (button 13): Enable vision targeting *{whenHeld}*
  - enforce turret rotation with limits
- ~~[button] (button 14): Activate wheel of fortune - Position Control~~
- ~~[button] (button 15): Activate wheel of fortune - Rotation Control~~
- [button] (button 16): Home Turret *{whenPressed}*
  - hold control until homed?
- ~~(button 17): Spare button~~
- ~~(button 18): Spare button~~
- [button] button 19): Extend Climber *{whenPressed}*
  - limit by encoder or limit switch
  - speed is constant preset in code
- [button] (button 20): Retract Climber *{whenHeld}*
   - speed is constant preset in code


## Special
- When some special combo of buttons is pressed, the climber reverses
- Final turret position is based on:
  1. turret robot orientation (buttons 9, 10, 11)
  1. field position (buttons 3, 4, 5)
  1. jogs (buttons 11, 12)
