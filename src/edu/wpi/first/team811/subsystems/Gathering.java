/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.Relay.Value;

/**
 * Controls the conveyer belt
 *
 * Progress - 100% Status - Reviewed, Ready for robot
 *
 * @author David
 */
public class Gathering extends SubSystem {

    boolean ballCounted = false;
    boolean[] states = new boolean[4];
    

    public Gathering(Team811Robot teamrobot) {
        super(teamrobot);
    }

    public String ID() {
        return "Gathering";
    }

    public void logic(Object param) {
        
        if(d.joy2.getRawAxis(6) > 0 || d.joy1.getRawAxis(3) < 0) {
            c.gathererOn = true;
        }
        if(d.joy2.getRawAxis(6) < 0 || d.joy1.getRawAxis(3) > 0) {
            c.gathererOn = false;
        }
        
        if(d.joy1.getRawButton(1)) {
            //d.conveyorBelt.set(Value.kForward);//Start Conveyer Belt
            d.conveyorBelt.set(1);
        } else if(c.gathererOn) {
            //d.conveyorBelt.set(Value.kReverse);//Start Conveyer Belt
            d.conveyorBelt.set(-1);
        } else {
            //d.conveyorBelt.set(Value.kOff);//Stop Conveyer Belt
            d.conveyorBelt.set(0);
        }
    }
}