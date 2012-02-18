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
//    public int armDown = 5;///<<<Mapped Var>>><<<Button>>>  arm is at bottom limit switch Used By: BridgeArm
//    public int armUp = 6;///<<<Mapped Var>>><<<Button>>>  arm is at top limit switch Used By: BridgeArm
//    public int ballCount = 0;///ball counter variable Used by: Gathering
//    public int feederOn = 1;///This will switch on and off the feeder. By default it is off. Used By: Feeder
//    public int feederStart = 9;///<<<Mapped Var>>><<<Button>>> TEMPORARY feeder turns on Used By: Feeder
//    public int feederStop = 10;///<<<Mapped Var>>><<<Button>>>  TEMPORARY feeder turns off Used By: Feeder
//    public int gathererInput = 6;///<<<Mapped Var>>><<<Axis>>> tells hows many balls are in the gatherer Used By: Gathering
//    public double maxDriveSpeed = 1.0;///Drive Train Speed Scale  Drive Train is at max speed Used By: DriveTrain
//    public int fastDriveTrain = 9;///<<<Mapped Var>>><<<Button>>>Used to set DriveTrain at full speed Used By: DriveTrain
//    public int slowDriveTrain = 10;///<<<Mapped Var>>><<<Button>>> Used to set DriveTrain at slow speed Used By: DriveTrain
//    public double robotDriveExpire = 5;///Time (seconds?) before the Robot Drive Device complains about the output not being updated Used By:Drive Train
//    public int shooterFullpower = 1;///<<<Mapped Var>>><<<Button>>> shoots the ball with full power Used By: Shooter Debug
//    public int shooter75power = 2;///<<<Mapped Var>>><<<Button>>> shoots the ball with 75% power Used By: Shooter Debug
//    public int shooter60power = 3;///<<<Mapped Var>>><<<Button>>> shoots the ball with 60% power Used By: Shooter Debug
//    public int shooter50power = 4;///<<<Mapped Var>>><<<Button>>> shoots the ball with 50% power Used By: Shooter Debug
//    public int shooterResetPower = 8;///<<<Mapped Var>>><<<Button>>> resets the shooter Used By: Shooter Debug
//    public int shooterTrack = 1;///<<<Mapped Var>>><<<Button>>> aligns the turret to the rectangle Used By: Shooter
//    public int shooterTrackShoot = 2;///<<<Mapped Var>>><<<Button>>> shoots a ball Used By: Shooter
//    public double shooterWheelPIDkP = 0.1;///P constant for shooter PID Used By: Shooter
//    public double shooterWheelPIDkI = 0.1;///I constant for shooter PID Used By: Shooter
//    public double shooterWheelPIDkD = 0.1;///D constant for shooter PID Used By: Shooter
//    public double turretPIDkP = 0.1;///P constant for turret PID Used By: Shooter
//    public double turretPIDkI = 0.1;///I constant for turret PID Used By: Shooter
//    public double turretPIDkD = 0.1;///D constant for turret PID Used By: Shooter
//    public boolean stickyGatherer = false;///Allow gatherer to be on without D-Pad pressed Used By: Gathering
//    public int visionErrorMargin = 5;///Margin of error allowed for vision
//    public double[] heights = {0};
//    public double[] speeds =  {0};
    ///Variables End
    
    ///Driver Controls
    //Joy1
    public int motorFullSpeed = 10;///<<<Mapped Var>>><<<Axis>>> x1
    public int motorSpeed2 = 9;///<<<Mapped Var>>><<<Axis>>> x1
    public int bridgeArmDown = 5;///<<<Mapped Var>>><<<Button>>> x1 arm is at bottom limit switch Used By: BridgeArm
    public int bridgeArmUp = 6;///<<<Mapped Var>>><<<Button>>> x1 arm is at top limit switch Used By: BridgeArm
    public int gather = 3;///<<<Mapped Var>>><<<Axis>>> x1
    public int gathererStop = 1;///<<<Mapped Var>>><<<Button>>> x1
    //Joy2
    public int shoot = 3;///<<<Mapped Var>>><<<Axis>>> x2
    public int maunalMode = 8;///<<<Mapped Var>>><<<Button>>> x2
    public int autoMode = 7;///<<<Mapped Var>>><<<Button>>> x2
    public int turret = 7;///<<<Mapped Var>>><<<Axis>>> x2
    public int feeder = 1;///<<<Mapped Var>>><<<Axis>>> x2 right
    public int shooterPower1 = 1;///<<<Mapped Var>>><<<Button>>> x2 shoots the ball with full power Used By: Shooter Debug
    public int shooterPower2 = 2;///<<<Mapped Var>>><<<Button>>> x2 shoots the ball with 75% power Used By: Shooter Debug
    public int shooterPower3 = 3;///<<<Mapped Var>>><<<Button>>> x2 shoots the ball with 60% power Used By: Shooter Debug
    public int shooterPower4 = 4;///<<<Mapped Var>>><<<Button>>> x2 shoots the ball with 50% power Used By: Shooter Debug
    public int turretFullSpeed = 6;///<<<Mapped Var>>><<<Axis>>> x2
    public int turretSpeed2 = 5;///<<<Mapped Var>>><<<Axis>>> x2
    public int autoTurret = 9;///<<<Mapped Var>>><<<Button>>> x2
    public int gatherer = 6;///<<<Mapped Var>>><<<Axis>>>
    //others
    public int visionErrorMargin = 5;///Margin of error allowed for vision
    public double robotDriveExpire = 5;///Time (seconds?) before the Robot Drive Device complains about the output not being updated Used By:Drive Train
    public double[] heights1 =   {105, 65, 50};///greater
    public double[] heights2 =   {95 , 50, 40};///less
    public double[] speedsNew =  {.55, .85, .7};
    public double[] speedsMedium={.6 , .9 , 1};
    public double[] speedsOld =  {.6 , 1 ,  1};
    public double[] encspeeds =  {0,  0 };
    public boolean gathererOn = false;
    public boolean feederOn = false;
}
