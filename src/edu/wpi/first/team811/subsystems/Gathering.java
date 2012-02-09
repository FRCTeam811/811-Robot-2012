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
 * @author David
 */
public class Gathering extends SubSystem {
    //
    boolean lastState = false;
    
    final static double gathererOn = 1.0;
    final static double gathererOff = -1.0;

    public Gathering(Team811Robot teamrobot) {
        super(teamrobot);
    }

    public String ID() {
        return "Gathering";
    }

    static boolean[] get = new boolean[10];
    static int index = 0;
    
    public void logic(Object param) {
        
        if (d.joy1.getRawAxis(c.gathererInput) == gathererOn) {
            if (reachedCapacity()) {
                stopConveyorBelt();
            }
            else {
                startConveyorBelt();
            }
            
        }
        else if (d.joy1.getRawAxis(c.gathererInput) == gathererOff){
            stopConveyorBelt();
        }
        
        
        
        //gets the current state of the limitswitch
        get[index] = d.gathererBottom.get();
        index++;
        
        if (index==10) {
            int numberOfTrues = 0;
            index = 0;
            for (int i = 0; i < get.length; i++) {
                if (get[i]) {
                    numberOfTrues++;
                }
            }
            boolean containsABall = (numberOfTrues>=9);
            
            if (containsABall != lastState) {//looks at boolean lastState 
                if (containsABall) {
                    c.ballCount++;
                    //see if "get" is not equal to lastState boolean
                    // if not then add 1 to ballcount 
                }
                lastState = containsABall;
            }
        
        }
        
    }
    
    public boolean reachedCapacity() {
        if (c.ballCount < 3){
            return false; 
        }
        else 
            return true;
    }
    public void ballCountMinusOne() {
        c.ballCount--;
    }
    public void startConveyorBelt() {
        d.conveyorBelt.set(Value.kOn) ;
    }
    public void stopConveyorBelt() {
        d.conveyorBelt.set(Value.kOff);
    }
    public void moveConveyorBelt() {
        if (c.ballCount < 3) {
            startConveyorBelt();
        } 
        else stopConveyorBelt();
    }
    public boolean isBallAtTop(){
        if (d.gathererTop.get())
            return true;
        else 
            return false;
    }
}
    
   
        
    
    
            
                
    /*
     * move it up and down limit for getting balls stop at three
     *
     */