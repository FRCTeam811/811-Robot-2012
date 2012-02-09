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
 * @author elisha
 */
public class Feeder extends SubSystem {

    public Feeder(Team811Robot teamrobot) {
        super(teamrobot);
    }

    public String ID() {
        return "Feeder";
    }

    public void logic(Object param) {
        if (c.feederOn == 1){
          d.feeder.set(Value.kForward);
        }
        else {
          d.feeder.set(Value.kOff);
    }

       }
    }
