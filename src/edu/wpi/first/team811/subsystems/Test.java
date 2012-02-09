///NEEDS SASWAT APPROVAL!
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Relay;
/**
 *
 * @author elisha
 */
public class Test extends SubSystem {

    public Test(Team811Robot teamrobot) {
        super(teamrobot);
    }

    public String ID() {
        return "Test";
    }
int currentTest =0;
{
   if(d.joy1.getRawButton(2)){
currentTest ++;
   }
   switch(currentTest){
    case 0:
        debug2dashboard("Testing Jag 1");
        d.leftDriveJag.set(d.joy1.getRawAxis(5));
        break;
    case 1: 
        break;
    default:
}
}


    
    public void logic(Object param) {
        debug2dashboard("one");
        if(d.joy1.getRawButton(2))///Button 2= button B
        d.joy1.getRawAxis(5);
        if (c.feederOn == 1){
          d.feeder.set(Relay.Value.kForward);
        }
        else {
          d.feeder.set(Relay.Value.kOff);
    }

       }
    }
