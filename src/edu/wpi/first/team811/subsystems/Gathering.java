/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;

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
    boolean gathererOn = false;

    public Gathering(Team811Robot teamrobot) {
        super(teamrobot);
    }

    public String ID() {
        return "Gathering";
    }

// MRG - Please change the joy stick numbers below to use the values in the Configuration.java
    public void logic(Object param) {
        
        if(d.joy2.getRawAxis(c.gatherer2) > 0 || d.joy1.getRawAxis(c.gatherer) < 0) {
            gathererOn = true;
        }
        if(d.joy2.getRawAxis(c.gatherer2) < 0 || d.joy1.getRawAxis(c.gatherer) > 0) {
            gathererOn = false;
        }
        
        if(d.joy1.getRawButton(c.gathererReverse)) {
            d.conveyorBelt.set(1);//Reverse Conveyer Belt
        } else if(gathererOn) {
            d.conveyorBelt.set(-1);//Start Conveyer Belt
        } else {
            d.conveyorBelt.set(0);//Stop Conveyer Belt
        }
    }

    public void pause() {
        d.conveyorBelt.set(0);
    }
}