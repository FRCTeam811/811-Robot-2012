/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811;

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
    public Jaguar jag1;///
    public Jaguar jag2;///
    public Joystick joy1;///
    public RobotDrive rd1;///<<<MANUALLY EDITED>>>
    public Gyro gyro;
    ///Variables End

    public Devices() {
        ///Variables Set Start
        ac = AxisCamera.getInstance();
        jag1 = new Jaguar(1);
        jag2 = new Jaguar(2);
        joy1 = new Joystick(1);
        rd1 = new RobotDrive(jag1,jag2);
        gyro = new Gyro(1);
        ///Variables Set End
    }
}
