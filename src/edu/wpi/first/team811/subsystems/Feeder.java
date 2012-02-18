///NEEDS SASWAT APPROVAL!
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

/**
 *
 * @author Elisha
 */
public class Feeder extends SubSystem {

    public Feeder(Team811Robot teamrobot) {
        super(teamrobot);
    }

    public String ID() {
        return "Feeder";
    }

    public void logic(Object param) {
        
        if(d.joy2.getRawAxis(c.feeder) < .5) {
            c.feederOn = true;
        } 
        if(d.joy2.getRawAxis(c.feeder) > -.5) {
            c.feederOn = false;
        }
        
        if(c.feederOn) {
            d.feeder.set(Relay.Value.kReverse);
        } else {
             d.feeder.set(Relay.Value.kOff);
        }
        
        
//        if (d.joy1.getRawButton(c.feederStart)) {
//            d.feeder.set(Value.kReverse);
//        } 
//        if (d.joy1.getRawButton(c.feederStop)){
//            d.feeder.set(Value.kOff);
//        }
    }
}
