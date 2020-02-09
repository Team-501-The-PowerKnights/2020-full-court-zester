/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.modules.IModule;
import frc.robot.modules.ModuleFactory;
import frc.robot.preferences.PreferencesInitializer;
import frc.robot.properties.PropertiesManager;
import frc.robot.sensors.ISensor;
import frc.robot.sensors.SensorFactory;
import frc.robot.telemetry.SchedulerProvider;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.telemetry.TelemetryNames.Preferences;
import frc.robot.utils.PKStatus;
import frc.robot.subsystems.ISubsystem;
import frc.robot.subsystems.SubsystemFactory;

import riolog.RioLogger;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    /* Our classes logger */
    private static final Logger logger = RioLogger.getLogger(Robot.class.getName());

    private TelemetryManager tlmMgr;

    //
    private List<IModule> modules;
    //
    private List<ISensor> sensors;
    //
    private List<ISubsystem> subsystems;

    // Flag for having completed autonomous part of match
    private boolean autonomousComplete;
    // Flag for having run first autonomous loop
    private boolean autonomousFirstRun;
    // Flag for having completed operator control part of match
    private boolean teleopComplete;
    // Flag for having run first operator control loop
    private boolean teleopFirstRun;

    // FIXME - Delete?
    private SendableChooser<Command> autoChooser;
    private Command autoCommand;
    // FIXME - Delete?
    private RobotContainer m_robotContainer;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        logger.info("initializing");

        // Wait until we get the configuration data from driver station
        waitForDriverStationData();

        // Make sure Preferences are initialized
        intializePreferences();

        // Make sure Properties file exists and can be parsed
        initializeProperties();

        // Create telemetry manager
        TelemetryManager.constructInstance();
        tlmMgr = TelemetryManager.getInstance();

        // Initialize the OI "subsystem"
        OI.constructInstance();
        tlmMgr.addProvider(OI.getInstance());

        // Create command manager
        SchedulerProvider.constructInstance();
        tlmMgr.addProvider(SchedulerProvider.getInstance());

        // Initialize all the modules
        modules = ModuleFactory.constructModules();
        // Initialize all the sensors
        sensors = SensorFactory.constructSensors();
        // Initialize all the subsystems
        subsystems = SubsystemFactory.constructSubsystems();

        createAutoChooser();

        autonomousComplete = false;
        autonomousFirstRun = false;
        teleopComplete = false;
        teleopFirstRun = false;

        // FIXME - Delete?
        // Instantiate our RobotContainer. This will perform all our button bindings,
        // and put our
        // autonomous chooser on the dashboard.
        // initSubsystems();
        m_robotContainer = new RobotContainer();

        logger.info("initialized");
    }

    /**
     * Holds the constructor until we receive at least one update of the control
     * data, which holds the run-spinTime configuration.
     **/
    private void waitForDriverStationData() {
        long count = 0;
        while (!DriverStation.getInstance().isNewControlData()) {
            if ((count % 100) == 0) {
                logger.trace("Waiting ...");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                logger.error("exception for sleep: ", ex);
            }
            count++;
        }
    }

    private void intializePreferences() {
        // Needs to be here or conflict with class from WPILib? wth?
        SmartDashboard.putNumber(Preferences.status, PKStatus.inProgress.tlmValue);

        PreferencesInitializer.initialize();

        SmartDashboard.putNumber(Preferences.status, PKStatus.success.tlmValue);
    }

    private void initializeProperties() {
        // Reads and stores all the properties
        PropertiesManager.constructInstance();

        PropertiesManager.getInstance().listProperties();
    }

    private void createAutoChooser() {
        // TODO - Implement this
        // autoChooser = new SendableChooser<>();

        // autoChooser.setDefaultOption("Do Nothing", new DoNothing());

        // SmartDashboard.putData( "Auto Mode", autoChooser );
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled commands, running already-scheduled commands, removing
        // finished or interrupted commands, and running subsystem periodic()
        // methods. This must be called from the robot's periodic block in order
        // for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();

        // Update the telemetry
        tlmMgr.sendTelemetry();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit() {
        logger.info("disabling");

        validateCalibrations();

        if (autonomousComplete && teleopComplete) {
            logger.info("match complete");

            logMatchData();

            logVisionData();
        }

        logger.info("disabled");
    }

    /**
     * Calls each subsystem to validate their calibration and update the appropriate
     * telemetry points.
     */
    private void validateCalibrations() {
        for (ISubsystem s : subsystems) {
            s.validateCalibration();
        }
    }

    /**
     * Log the data associated with the match to the tail of the log file. This
     * allows us to easily determine whether it is a real match, and what match it
     * was.
     **/
    private void logMatchData() {
        final DriverStation ds = DriverStation.getInstance();
        logger.info("EventName:     {}", ds.getEventName());
        logger.info("MatchType:     {}", ds.getMatchType());
        logger.info("MatchNumber:   {}", ds.getMatchNumber());
        logger.info("ReplayNumber:  {}", ds.getReplayNumber());
        logger.info("Alliance:      {}", ds.getAlliance());
        logger.info("Location:      {}", ds.getLocation());
    }

    /**
     * Log the data associated with the vision to the tail of the log file.
     **/
    private void logVisionData() {
        logger.info("vision data:");
    }

    /**
     * This function is called periodically during Disabled mode.
     */
    @Override
    public void disabledPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled commands, running already-scheduled commands, removing
        // finished or interrupted commands, and running subsystem periodic()
        // methods. This must be called from the robot's periodic block in order
        // for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This autonomous runs the autonomous command selected by your
     * {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit() {
        logger.info("initializing");
        autonomousComplete = true;
        autonomousFirstRun = false;

        // Update the preferences
        for (IModule m : modules) {
            m.updatePreferences();
        }
        for (ISensor s : sensors) {
            s.updatePreferences();
        }
        for (ISubsystem s : subsystems) {
            s.updatePreferences();
        }

        // autoCommand = autoChooser.getSelected();
        autoCommand = m_robotContainer.getAutonomousCommand();
        autoCommand.schedule();

        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
         * switch(autoSelected) { case "My Auto": autonomousCommand = new
         * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
         * ExampleCommand(); break; }
         */

        // schedule the autonomous command (example)
        if (autoCommand != null) {
            // FIXME - New method for new command implementation?
            // autoCommand.start();
        }

        logger.info("initialized");
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        if (!autonomousFirstRun) {
            autonomousFirstRun = true;
            logger.info("first run of periodic");
        }

        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled commands, running already-scheduled commands, removing
        // finished or interrupted commands, and running subsystem periodic()
        // methods. This must be called from the robot's periodic block in order
        // for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot enters Teleop mode.
     */
    @Override
    public void teleopInit() {
        logger.info("initializing");
        teleopComplete = true;
        teleopFirstRun = false;

        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autoCommand != null) {
            autoCommand.cancel();
        }

        // Update the preferences
        for (IModule m : modules) {
            m.updatePreferences();
        }
        for (ISensor s : sensors) {
            s.updatePreferences();
        }
        for (ISubsystem s : subsystems) {
            s.updatePreferences();
        }

        logger.info("initialized");
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        if (!teleopFirstRun) {
            teleopFirstRun = true;
            logger.info("first run of periodic");
        }

        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled commands, running already-scheduled commands, removing
        // finished or interrupted commands, and running subsystem periodic()
        // methods. This must be called from the robot's periodic block in order
        // for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot enters Test mode.
     */
    @Override
    public void testInit() {
        logger.info("initializing");

        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();

        // Update the preferences
        for (IModule m : modules) {
            m.updatePreferences();
        }
        for (ISensor s : sensors) {
            s.updatePreferences();
        }
        for (ISubsystem s : subsystems) {
            s.updatePreferences();
        }

        logger.info("initialized");
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

}
