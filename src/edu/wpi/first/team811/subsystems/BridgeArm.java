//NEEDS REVIEW-this task: set up bridge arm(OI-button down till limit, OI-button release up to limit)

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author SM
 */
public class BridgeArm extends SubSystem {

    public BridgeArm(Team811Robot teamrobot) {
        super(teamrobot);
    }

    public String ID() {
        return "BridgeArm";
    }

    public void logic(Object param) {
        if (d.joy1.getRawButton(c.armDown)) {
            armDown();
        } else if (d.joy1.getRawButton(c.armUp)) {
            armUp();
        } else {
            d.bridgeArm.set(Relay.Value.kOff);
        }
    }
    //if armDown is pressed, arm moves down(If the bottom limit switch is false)
    //if armUp is pressed, arm moves up(If the top limit switch is false)

    public void armDown() {
        if (d.armBottom.get() == false) {
            d.bridgeArm.set(Relay.Value.kReverse);
        } else{
            d.bridgeArm.set(Relay.Value.kOff);
        }
    }

    public void armUp() {
        if (d.armTop.get() == false) {
            d.bridgeArm.set(Relay.Value.kForward);
        } else{
            d.bridgeArm.set(Relay.Value.kOff);
        }
    }
}