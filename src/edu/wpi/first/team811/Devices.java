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
    public Encoder shooterEncoder;///
    public Jaguar shooter;///This represents the 2 Jaguars that are wired together. They are always set to the same speed
    public Jaguar shooter2;
    public Relay feeder;///Determines whether it goes forward or backwards.
    public Victor turret;///
    public Joystick joy1;///
    public Joystick joy2;///
    public Relay conveyorBelt;///
    public RobotDrive rd1;///<<<MANUALLY EDITED>>>
    public Gyro gyro;///
    public Vision vision;///<<<MANUALLY EDITED>>>
    ///Variables End

    public Devices() {
        
        ///Variables Set Start
        ac = AxisCamera.getInstance();
        leftDriveJag = new Jaguar(1);
        rightDriveJag = new Jaguar(2);
        bridgeArm = new Relay(2);
        armBottom = new DigitalInput(6);
        armTop = new DigitalInput(5);
        gathererBottom = new DigitalInput(4);
        gathererTop = new DigitalInput(3);
        shooterEncoder = new Encoder(1, 2);
        shooter = new Jaguar(3);
        shooter2 = new Jaguar(10);
        feeder = new Relay(3);
        turret = new Victor(4);
        joy1 = new Joystick(1);
        joy2 = new Joystick(2);
        conveyorBelt = new Relay(1);
        rd1 = new RobotDrive(leftDriveJag,rightDriveJag);
        gyro = new Gyro(1);
        vision = new Vision();
        ///Variables Set End
    }
}
