/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811;

import edu.wpi.first.team811.Vision.Vision;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 * DO NOT EDIT or the program will break
 *
 * @author Saswat
 */
public class Devices {
    
    ///Variables Start
    public AxisCamera ac;///<<<MANUALLY EDITED>>>
    public DigitalInput armTop;///
    public Victor bridgeArm;///
    public Victor conveyorBelt;///gatherer
    public Relay feeder;///Determines whether it goes forward or backwards.
    public Joystick joy1;///
    public Joystick joy2;///
    public Jaguar leftDriveJag;///
    public RobotDrive rd1;///<<<MANUALLY EDITED>>>
    public Jaguar rightDriveJag;///
    public DigitalInput switchP1;///
    public DigitalInput switchP2;///
    public Jaguar shooter;///This represents the 2 Jaguars that are wired together. They are always set to the same speed
    public Encoder shooterEncoder;///<<<MANUALLY EDITED>>>
    public Victor turret;///
    public DigitalInput turretLimit;///
    public Vision vision;///<<<MANUALLY EDITED>>>
    ///Variables End

    public Devices() {
        
        ///Variables Set Start
        ac = AxisCamera.getInstance();
        armTop = new DigitalInput(3);
        bridgeArm = new Victor(5);
        conveyorBelt = new Victor(6);
        feeder = new Relay(4);
        joy1 = new Joystick(1);
        joy2 = new Joystick(2);
        leftDriveJag = new Jaguar(1);
        rightDriveJag = new Jaguar(2);
        rd1 = new RobotDrive(leftDriveJag, rightDriveJag);
        switchP1 = new DigitalInput(5);
        switchP2 = new DigitalInput(6);
        shooter = new Jaguar(3);
        shooterEncoder = new Encoder(1, 13, 1, 14, true, EncodingType.k4X);
        turret = new Victor(4);
        turretLimit = new DigitalInput(4);
        vision = new Vision();
        ///Variables Set End
        ac.freshImage();
        shooterEncoder.start();
    }
}
