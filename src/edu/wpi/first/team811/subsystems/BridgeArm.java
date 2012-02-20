package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;

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
            debug2dashboard("off");
        }
    }
    
    public void armUp() {//if armDown is pressed, arm moves down (If the bottom limit switch is false)
        d.bridgeArm.set(1.0);
//        if (d.armTop.get()) {//switch is inverted
//            d.bridgeArm.set(1.0);
//        } else{
//            d.bridgeArm.set(0);
//        }
    }
    
    public void armDown() {//if armUp is pressed, arm moves up
            d.bridgeArm.set(-1.0);
    }

    public void pause() {
        d.bridgeArm.set(0);
    }
}