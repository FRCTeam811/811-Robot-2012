/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;

/**
 *
 * @author saswat
 */
public class VisionSS extends SubSystem {

    public VisionSS(Team811Robot teamrobot) {
        super(teamrobot);
    }
    
    public String ID() {
        return "VisionSS";
    }
    
    public void logic(Object param) {
        d.ac.freshImage();
        //d.rd1.arcadeDrive((0.75) * , 0.75 * , true);
        d.rightDriveJag.set(d.joy1.getThrottle());
        d.leftDriveJag.set(d.joy1.getY());
        //d.turret.set(d.joy1.getRawAxis(3));
    }

    public void pause() {
        d.rightDriveJag.set(0);
        d.leftDriveJag.set(0);
    }
}
