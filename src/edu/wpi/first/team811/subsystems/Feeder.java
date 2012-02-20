/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.Relay;

/**
 * Controls the feeder on the robot 
 * 
 * Progress - 100%
 * Status - Reviewed, Ready for robot
 * 
 * @author Elisha
 */
public class Feeder extends SubSystem {

    boolean feederOn = false;
    
    public Feeder(Team811Robot teamrobot) {
        super(teamrobot);
    }

    public String ID() {
        return "Feeder";
    }

    public void logic(Object param) {
        
        if(d.joy2.getRawAxis(c.feeder) > .5) {
            feederOn = true;
        } else {// if(d.joy2.getRawAxis(c.feeder) < -.5) {
            feederOn = false;
        }
        
        if(feederOn) {
            d.feeder.set(Relay.Value.kReverse);
        } else {
             d.feeder.set(Relay.Value.kOff);
        }
    }
}
