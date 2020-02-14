# Drivetrain
- Tank/diff drive: 2x 2 NEO motor (SPARK Max)
-- May want position control for autonomous

# Shooter
- Flywheels: 2x NEO motor (SPARK Max)
-- Needs RPM control, options for RPM based on general region (user selectable... not by vision... we think)
- Turret Azimuth: 1 NEO 550 motor (SPARK Max)
-- Needs position control with homing / absolute position
-- Soft limits for overtravel (0 to 270 degrees)
-- Will be ultimately driven by vision targeting + user "nudge"

# Intake
- (Future) Actuation up-down... unsure how
- 1x 775Pro (Talon)
-- Dumb on/off, maybe tune speed, need reverse to un-jam
- 1x 775Pro (Talon)
-- Position control with encoders (0 is fully up)
-- Manual override for tests

# Hopper
- Agitator wheel: 1 BAG Motor (Talon)
-- Dumb on/off, maybe tune speed, may need reverse to un-jam

# Ballevator
- Ballevator itself: 1 775Pro (Talon)
-- The ballevator may need limit switches/photogates for ball position

# WHEEL. OF. FORTUNE!
- Interaction wheel: 1x BAG motor (Talon)
-- Dumb on/off in conjunction with the color sensor
- Need a color sensor, need to code it to act like an encoder

# Climber
- One NEO motor (SPARK Max)
- Two limit switches (one to stop at top, one to stop at bottom)
- Up (runs until top limit switch goes)
- Down (runs until bottom limit switch goes)
