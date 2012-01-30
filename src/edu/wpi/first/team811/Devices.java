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
    public Jaguar gatheringMotor;///
    public Jaguar jag1;///
    public Victor bridgeArm;///
    public DigitalInput armBottom;///
    public DigitalInput armTop;///
    public DigitalInput gathererTop;///
    public Jaguar jag2;///
    public Jaguar bridgeArmJag;///
    public Jaguar shooter2;
    public Jaguar shooter;///
    public Victor turret;
    public Joystick joy1;///
    public Joystick joy2;///
    public Jaguar conveyorBelt;///
    public RobotDrive rd1;///<<<MANUALLY EDITED>>>
    public Gyro gyro;///
    public Vision vision;///<<<MANUALLY EDITED>>>
    ///Variables End

    public Devices() {
        
        ///Variables Set Start
        ac = AxisCamera.getInstance();
        gatheringMotor = new Jaguar(5);
        jag1 = new Jaguar(1);
        jag2 = new Jaguar(2);
        bridgeArmJag = new Jaguar(3);
        bridgeArm = new Victor(4);
        armBottom = new DigitalInput(8);
        armTop = new DigitalInput(9);
        gathererBottom = new DigitalInput(1);
        gathererTop = new DigitalInput(2);
        shooter = new Jaguar(7);
        shooter2 = new Jaguar(8);
        turret = new Victor(9);
        joy1 = new Joystick(1);
        joy2 = new Joystick(2);
        conveyorBelt = new Jaguar(6);
        rd1 = new RobotDrive(jag1,jag2);
        gyro = new Gyro(1);
        vision = new Vision();
        ///Variables Set End
    }
}
