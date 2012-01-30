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
public class Shooter extends SubSystem {

    public Shooter(Team811Robot teamrobot) {
        super(teamrobot);
    }

    public String ID() {
        return "Shooter";
    }

    public void logic(Object param) {

        //ButtonA is Full Power 
        if (d.joy1.getRawButton(c.shooterFullpower)) {
            speed = 1.0;
        }
        //ButtonB is 75% power 
        if (d.joy1.getRawButton(c.shooter75power)) {
            speed = 0.75;
        }
        //ButtonX is  60% 
        if (d.joy1.getRawButton(c.shooter60power)) {
        speed = 0.60;
        }
        //Power ButtonY is 50% power
        if (d.joy1.getRawButton(c.shooter50power)) {
            speed = 0.5;
        }
        //Start button to reset power
        if (d.joy1.getRawButton(c.shooterResetPower)) {
            speed = 0.0;
        }
        // Start Button Stops Shooter
        //if (d.joy1.getRawAxis() != 0.0) {
        //    speed = d.joy1.getRawAxis(3);
        //}
        //if (d.joy1.getRawButton(c.Shoot)) {
        //    shooter(speed);
        //}

        //debug("speed: " + speed);
        d.shooter.set(speed);
        d.shooter2.set(speed);
    }
    double speed = 0.0;

    public void shooter(double speed) {
        d.shooter.set(speed);
    }

    /**
     * written by David Grossman this is a routine to shoot assuming that -we
     * are shooting one ball at a time -the feeder can load a ball without
     * running the gatherer -we are using user input to start and stop the
     * shooter When the trigger is pulled, the shooter will begin When the
     * trigger is released, the shooting sequence will stop To shoot multiple
     * balls, the driver will have to pull the trigger multiple times
     *
     *
    private void shoot() {
        if (isShooting()) {
            if (isBallAtTop()) {
                if (turretAligned() && shooterReady()) {
                    startFeeder();
                }
            }
        } else {
            stopFeeder();
        }
    }*/

    private boolean isShooting() {
        if (d.joy2.getRawButton(c.shootButton)) {
            return true;
        } else {
            return false;
        }
    }
}
