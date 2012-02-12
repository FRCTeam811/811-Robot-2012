/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811;

import edu.wpi.first.team811.Vision.Vision;
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
    public DigitalInput gathererBottom;///limitswitch that counts the balls.
    public Jaguar leftDriveJag;///
    public Relay bridgeArm;///
    public DigitalInput armBottom;///
    public DigitalInput armTop;///
    public DigitalInput gathererTop;///
    public Jaguar rightDriveJag;///
    public Encoder shooterEncoder;///<<<MANUALLY EDITED>>>
    public Jaguar shooter;///This represents the 2 Jaguars that are wired together. They are always set to the same speed
    public Relay feeder;///Determines whether it goes forward or backwards.
    public Victor turret;///
    public Joystick joy1;///
    public Joystick joy2;///
    public Relay conveyorBelt;///gatherer
    public RobotDrive rd1;///<<<MANUALLY EDITED>>>
    public Vision vision;///<<<MANUALLY EDITED>>>
    ///Variables End

    public Devices() {
        
        ///Variables Set Start
        ac = AxisCamera.getInstance();
        gathererBottom = new DigitalInput(4);
        leftDriveJag = new Jaguar(1);
        bridgeArm = new Relay(5);
        armBottom = new DigitalInput(6);
        armTop = new DigitalInput(5);
        gathererTop = new DigitalInput(3);
        rightDriveJag = new Jaguar(2);
        shooterEncoder = new Encoder(1, 2);
        shooter = new Jaguar(3);
        feeder = new Relay(4);
        turret = new Victor(6);
        joy1 = new Joystick(1);
        joy2 = new Joystick(2);
        conveyorBelt = new Relay(5);
        rd1 = new RobotDrive(leftDriveJag, rightDriveJag);
        vision = new Vision();
        ///Variables Set End
    }
}
