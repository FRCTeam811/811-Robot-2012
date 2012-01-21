/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.team811;


import edu.wpi.first.team811.Modes.Autonomous;
import edu.wpi.first.team811.Modes.OperatorControl;
import edu.wpi.first.wpilibj.SimpleRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team811Robot extends SimpleRobot {
    
    public Devices devices;
    public Configuration config;
    private Mode op;
    private Mode auto;

    protected void robotInit() {
        devices = new Devices();
        config = new Configuration();
        op = new OperatorControl(this);
        auto = new Autonomous(this);
    }
    
    public void autonomous() {
        auto.done = false;
        auto.init();
        while (this.isAutonomous()) {
            if (!auto.done) auto.execute();
        }
        if(isDisabled()) auto.disable();
    }

    public void operatorControl() {
        op.done = false;
        op.init();
        while (this.isOperatorControl()) {
            if (!op.done) op.execute();
        }
        if(isDisabled()) op.disable();
    }
    
    /*public void startCompetition() {
        devices = new Devices();
        config = new Configuration();
        op = new OperatorControl(this);
        auto = new Autonomous(this);
        
        while (true) {
            if (isOperatorControl()) {
                op.done = false;
                op.init();
                while (this.isOperatorControl()) {
                    if (!op.done) op.execute();
                }
                if(isDisabled()) op.disable();
            } else if (isAutonomous()) {
                auto.done = false;
                auto.init();
                while (this.isAutonomous()) {
                    if (!auto.done) auto.execute();
                }
                if(isDisabled()) auto.disable();
            } else if (isDisabled()) {
                while(isDisabled()) {}
            }
        }
    }*/
}
