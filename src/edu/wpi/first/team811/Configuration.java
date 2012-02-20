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
    
    ///Driver Controls
    //Joy1
    public int motorFullSpeed = 10;///<<<Mapped Var>>><<<Axis>>> x1
    public int motorSpeed2 = 9;///<<<Mapped Var>>><<<Axis>>> x1
    public int bridgeArmDown = 6;///<<<Mapped Var>>><<<Button>>> x1 arm is at bottom limit switch Used By: BridgeArm
    public int bridgeArmUp = 5;///<<<Mapped Var>>><<<Button>>> x1 arm is at top limit switch Used By: BridgeArm
    public int gatherer = 3;///<<<Mapped Var>>><<<Axis>>> x1
    public int gathererReverse = 1;///<<<Mapped Var>>><<<Button>>> x1
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
    public int gatherer2 = 6;///<<<Mapped Var>>><<<Axis>>> x2
    //others
    public int visionErrorMargin = 5;///Margin of error allowed for vision
    public double robotDriveExpire = 5;///Time (seconds?) before the Robot Drive Device complains about the output not being updated Used By:Drive Train
    public double[] heights1 =  {105, 65 , 50};///greater
    public double[] heights2 =  {95 , 50 , 40};///less
    public double[] speedsNew = {.55, .85, .7};
    public double[] speedsMed = {.6 , .9 , 1};
    public double[] speedsOld = {.6 , 1 ,  1};
    public double[] espeedNew = {0,  0 };
    public double[] espeedMed = {0,  0 };
    public double[] espeedOld = {0,  0 };
    public boolean hybridOn = false;
}
