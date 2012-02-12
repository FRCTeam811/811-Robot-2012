/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811;

/**
 *
 * @author Saswat
 */
public class Configuration {
    ///Variables Start
    public int armDown = 5;///<<<Mapped Var>>><<<Button>>>  arm is at bottom limit switch Used By: BridgeArm
    public int armUp = 6;///<<<Mapped Var>>><<<Button>>>  arm is at top limit switch Used By: BridgeArm
    public int ballCount = 0;///ball counter variable Used by: Gathering
    public int feederOn = 1;///This will switch on and off the feeder. By default it is off. Used By: Feeder
    public int gathererInput = 6;///<<<Mapped Var>>><<<Axis>>> tells hows many balls are in the gatherer Used By: Gathering
    public double maxDriveSpeed = 1.0;///Drive Train Speed Scale  Drive Train is at max speed Used By: DriveTrain
    public double robotDriveExpire = 5;///Time (seconds?) before the Robot Drive Device complains about the output not being updated Used By:Drive Train
    public int shooterFullpower = 1;///<<<Mapped Var>>><<<Button>>> shoots the ball with full power Used By: Shooter 
    public int shooter75power = 2;///<<<Mapped Var>>><<<Button>>> shoots the ball with 75% power Used By: Shooter
    public int shooter60power = 3;///<<<Mapped Var>>><<<Button>>> shoots the ball with 60% power Used By: Shooter
    public int shooter50power = 4;///<<<Mapped Var>>><<<Button>>> shoots the ball with 50% power Used By: Shooter
    public int shooterResetPower = 8;///<<<Mapped Var>>><<<Button>>> resets the shooter Used By: Shooter
    public int shooterStoppower = 3;///<<<Mapped Var>>><<<Axis>>> doesn't shoot the ball, turns off Used By: Shooter
    public int shooterTrack = 7;///<<<Mapped Var>>><<<Button>>> tells the shooter where to shoot Used By: Shooter
    public int feederStart = 9;///<<<Mapped Var>>><<<Button>>> TEMPORARY feeder turns on Used By: Feeder
    public int feederStop = 10;///<<<Mapped Var>>><<<Button>>>  TEMPORARY feeder turns off Used By: Feeder
    public double shooterWheelPIDkP = 1.0;///P constant for shooter PID Used By: Shooter
    public double shooterWheelPIDkI = 1.0;///I constant for shooter PID Used By: Shooter
    public double shooterWheelPIDkD = 1.0;///D constant for shooter PID Used By: Shooter
    public double turretPIDkP = 1.0;///P constant for turret PID Used By: Shooter
    public double turretPIDkI = 1.0;///I constant for turret PID Used By: Shooter
    public double turretPIDkD = 1.0;///D constant for turret PID Used By: Shooter
    public boolean stickyGatherer = false;//Allow gatherer to be on without D-Pad pressed Used By: Gathering
    ///Variables End
    
}
