package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;

/**
 * Controls Drive Train
 *
 * Progress - 100%
 * Status - Reviewed, Ready for robot
 *
 * @author Saswat
 */
public class DriveTrain extends SubSystem {

    public DriveTrain(Team811Robot teamrobot) {
        super(teamrobot);
        d.rd1.setExpiration(c.robotDriveExpire);
    }

    public String ID() {
        return "DriveTrain";
    }

    public void logic(Object param) {
        
        if(d.joy1.getRawButton(c.motorFullSpeed)) slowMode = false;
        if(d.joy1.getRawButton(c.motorSpeed2)) slowMode = true;
        
        d.rd1.arcadeDrive(d.joy1.getRawAxis(2) * (slowMode ? .8 : 1), d.joy1.getRawAxis(4) * (slowMode ? .8 : 1));
        //d.rd1.arcadeDrive(.5, 0);
        
        /*By request of driver, there would be a slow speed and fast speed for drivetrain for positioning
        if (d.joy1.getRawButton(c.slowDriveTrain)) {
            c.maxDriveSpeed = .6;
        }
        else if(d.joy1.getRawButton(c.fastDriveTrain)){
            c.maxDriveSpeed = 1.0;
        }*/
            
    }
    boolean slowMode = false;
}
