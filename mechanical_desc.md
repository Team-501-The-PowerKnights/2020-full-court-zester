# Drivetrain
- Tank/diff drive: 2x 2 NEO motor (SPARK Max)
-- May want position control for autonomous

# Shooter
- Flywheels: 1 to 4 NEO motor (SPARK Max)
-- Needs RPM control, options for RPM based on general region (user selectable... not by vision... we think)
- Turret Azimuth: 1 NEO 550 motor (SPARK Max)
-- Needs position control with homing / absolute position
-- Soft limits for overtravel (0 to 270 degrees)
-- Will be ultimately driven by vision targeting + user "nudge"

# Intake
- (Future) Actuation up-down... may be pneumatic cylinder
- 1x 775Pro (Talon)
-- Dumb on/off, maybe tune speed, may need reverse to un-jam

# Ballevator
- Ballevator itself: 1 775Pro (Talon)
-- The ballevator may need limit switches/photogates for ball position

# Hopper
- (Maybe) Agitator wheel: 1 NEO 550 (SPARK Max) or 775Pro? Unsure.
-- Dumb on/off, maybe tune speed, may need reverse to un-jam

# WHEEL. OF. FORTUNE!
- Interaction wheel: 1x NEO550
-- Dumb on/off in conjunction with the color sensor
- Need a color sensor, need to code it to act like an encoder

# Climber
- Don't know ATM
