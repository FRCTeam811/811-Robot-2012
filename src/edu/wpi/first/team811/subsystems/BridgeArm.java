package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.Relay;

/**
 * Controls the bridge arm on the robot 
 * 
 * Progress - 100%
 * Status - Reviewed, Ready for robot
 * 
 * @author SM
 * 
 */
public class BridgeArm extends SubSystem {

    public BridgeArm(Team811Robot teamrobot) {
        super(teamrobot);
    }

    public String ID() {
        return "BridgeArm";
    }

    public void logic(Object param) {
        if (d.joy1.getRawButton(c.bridgeArmDown)) {
            armDown();
            debug2dashboard("down");
        } else if (d.joy1.getRawButton(c.bridgeArmUp)) {
            armUp();
            debug2dashboard("up");
        } else {
            d.bridgeArm.set(0);
            //d.bridgeArm.set(Relay.Value.kOff);
            debug2dashboard("off");
        }
    }
    
    public void armDown() {//if armDown is pressed, arm moves down (If the bottom limit switch is false)
        //if (d.armBottom.get()) {//TODO uncomment me
            d.bridgeArm.set(1.0);
        //} else{
        //    d.bridgeArm.set(0);
        //}
    }

    public void armUp() {//if armUp is pressed, arm moves up(If the top limit switch is false)
        //if (d.armTop.get() == false) {
            d.bridgeArm.set(-1.0);
        //d.bridgeArm.set(Relay.Value.kForward);
        //} else{
        //    d.bridgeArm.set(0);
        //}
    }
}