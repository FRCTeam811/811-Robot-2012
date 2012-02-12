///NEEDS SASWAT APPROVAL!
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
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
        
        ///TODO revert to feederOn
        if (d.joy1.getRawButton(c.feederStart)) {
            d.feeder.set(Value.kReverse);
        } 
        if (d.joy1.getRawButton(c.feederStop)){
            d.feeder.set(Value.kOff);
        }
    }
}
