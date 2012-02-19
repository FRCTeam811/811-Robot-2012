/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.team811;


import edu.wpi.first.team811.Modes.Autonomous;
import edu.wpi.first.team811.Modes.OperatorControl;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team811Robot extends IterativeRobot {
    
    public Devices devices;
    public Configuration config;
    private Mode op;
    private Mode auto;

    public void robotInit() {
        devices = new Devices();
        config = new Configuration();
        op = new OperatorControl(this);
        auto = new Autonomous(this);
        getWatchdog().setEnabled(true);
    }

    public void autonomousInit() {
        auto.done = false;
        auto.init();
    }

    public void autonomousPeriodic() {
        if (!auto.done) auto.execute();
    }

    public void autonomousContinuous() {
        auto.highPriortiy();
    }

    public void disabledInit() {
        auto.disable();
        op.disable();
    }

    public void disabledPeriodic() {
    }

    public void disabledContinuous() {
    }
    
    public void teleopInit() {
        op.done = false;
        op.init();
    }

    public void teleopPeriodic() {
        if (!op.done) op.execute();
    }

    public void teleopContinuous() {
        op.highPriortiy();
    }
}
