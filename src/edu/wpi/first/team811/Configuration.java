/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811;


/**
 * All the configuration variables used on the robot
 * 
 * @author Saswat
 */
public class Configuration {
    
    ///Driver Controls - Joy1
    public int motorFullSpeed = 10;///<<<Mapped Var>>><<<Axis>>> x1
    public int motorSpeed2 = 9;///<<<Mapped Var>>><<<Axis>>> x1
    public int bridgeArmDown = 6;///<<<Mapped Var>>><<<Button>>> x1 arm is at bottom limit switch Used By: BridgeArm
    public int bridgeArmUp = 5;///<<<Mapped Var>>><<<Button>>> x1 arm is at top limit switch Used By: BridgeArm
    public int gatherer = 3;///<<<Mapped Var>>><<<Axis>>> x1
    public int gathererReverse = 1;///<<<Mapped Var>>><<<Button>>> x1
    ///Driver Controls - Joy2
    public int shoot = 3;///<<<Mapped Var>>><<<Axis>>> x2
    public int maunalMode = 8;///<<<Mapped Var>>><<<Button>>> x2
    public int autoMode = 7;///<<<Mapped Var>>><<<Button>>> x2
    public int turret = 7;///<<<Mapped Var>>><<<Axis>>> x2
    public int feeder = 1;///<<<Mapped Var>>><<<Axis>>> x2 right
    public int shooterPower1 = 1;///<<<Mapped Var>>><<<Button>>> x2 shoots the ball with full power Used By: Shooter
    public int shooterPower2 = 2;///<<<Mapped Var>>><<<Button>>> x2 shoots the ball with 75% power Used By: Shooter
    public int shooterPower3 = 3;///<<<Mapped Var>>><<<Button>>> x2 shoots the ball with 60% power Used By: Shooter
    public int shooterPower4 = 4;///<<<Mapped Var>>><<<Button>>> x2 shoots the ball with 50% power Used By: Shooter
    public int turretFullSpeed = 6;///<<<Mapped Var>>><<<Axis>>> x2
    public int turretSpeed2 = 5;///<<<Mapped Var>>><<<Axis>>> x2
    public int autoTurret = 9;///<<<Mapped Var>>><<<Button>>> x2
    public int gatherer2 = 6;///<<<Mapped Var>>><<<Axis>>> x2
    ///Shooter data tables
    public double[] heights1 =  {150, 89 , 74, 63, 60, 56, 54, 45, 35};///greater
    public double[] heights2 =  {90 , 75 , 71, 60, 57, 55, 50, 36, 30};///less
    //public double[] speedsNew = {.55, .85, .7};///raw motor speeds - DO NOT USE
    //public double[] speedsMed = {.6 , .9 , 1 };///raw motor speeds - DO NOT USE
    //public double[] speedsOld = {.6 , 1  , 1 };///raw motor speeds - DO NOT USE
    //public double[] espeedNew = {34 , 36 , 37, 42, 43, 44, 46, 54, 56};///encoder speeds
    //public double[] espeedMed = {33 , 37 , 38, 43, 44, 45, 47, 55, 57};///encoder speeds
    //public double[] espeedOld = {33 , 37 , 38, 43, 44, 45, 47, 56, 58};///encoder speeds
    public double[] espeedNew = {34 , 36 , 37, 42, 43, 44, 48, 56, 58};///encoder speeds
    public double[] espeedMed = {33 , 37 , 38, 43, 44, 45, 49, 57, 59};///encoder speeds
    public double[] espeedOld = {33 , 37 , 38, 43, 44, 45, 49, 58, 60};///encoder speeds
    ///Shooter PID Constants
    public static double pidP = .0002;///
    public static double pidI = .0002;///
    public static double pidD = .0005;///
    public static double pidPeriod = .01;///
    public static double pidTolerance = 5;///%
    ///others
    public int visionErrorMargin = 2;///Margin of error allowed for vision
    public double robotDriveExpire = 5;///Time (seconds?) before the Robot Drive Device complains about the output not being updated Used By:Drive Train
    public boolean hybridOn = false;///
    public String SDautoMode = "Autonomous Mode";//SmartDashboard autonomous mode key
    public int autoDTMoveTime = 3600;//3500;
    public double shooterOffset = 3;///
}
