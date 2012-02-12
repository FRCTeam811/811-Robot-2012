/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;

/**
 *
 * @author Eric
 */
public class ShooterDebug extends SubSystem {

    double speed = 0.0;
    
    public ShooterDebug(Team811Robot teamrobot) {
        super(teamrobot);
    }

    public String ID() {
        return "Shooter";
    }

    public void logic(Object param) {
        
        //ButtonA is Full Power 
        if (d.joy1.getRawButton(c.shooterFullpower)) speed = 1.0;
        //ButtonB is 75% power 
        if (d.joy1.getRawButton(c.shooter75power)) speed = 0.9;
        //ButtonX is  60% 
        if (d.joy1.getRawButton(c.shooter60power)) speed = 0.8;
        //Power ButtonY is 50% power
        if (d.joy1.getRawButton(c.shooter50power)) speed = 0.6;
        //Start button to reset power 
        if (d.joy1.getRawButton(c.shooterResetPower)) speed = 0.0;
        
        debug2dashboard("running at " + speed);
        
        //d.turret.set(1);
        //d.conveyorBelt.set(Relay.Value.kOn);
        //d.feeder.set(Relay.Value.kOn);
        d.turret.set(d.joy1.getRawAxis(3));
        
        shoot(speed);
        
    }
    
    public void shoot(double speed) {
        speed *= -1;//Apparently the shooter is backwards, this fixes it
        d.shooter.set(speed);
    }
}
