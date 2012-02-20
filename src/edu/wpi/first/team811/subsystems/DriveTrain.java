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
        
//        d.rd1.arcadeDrive(d.joy1.getRawAxis(2), d.joy1.getRawAxis(4));
        
        if(d.joy1.getRawButton(c.motorFullSpeed)) slowMode = false;
        if(d.joy1.getRawButton(c.motorSpeed2)) slowMode = true;
        
        d.rd1.arcadeDrive(d.joy1.getRawAxis(2) * (slowMode ? .7 : 1), d.joy1.getRawAxis(4) * (slowMode ? .7 : 1));
            
    }
    boolean slowMode = false;
}
