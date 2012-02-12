/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.Relay.Value;

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

    public Gathering(Team811Robot teamrobot) {
        super(teamrobot);
    }

    public String ID() {
        return "Gathering";
    }

    public void logic(Object param) {

        if ((c.stickyGatherer || Math.abs(d.joy1.getRawAxis(c.gathererInput)) > .1) && c.ballCount < 3) {//See if sticky gatherer is enabled, D-Pad is pressed and capacity is not reached
            d.conveyorBelt.set(Value.kReverse);//Start Conveyer Belt
        } else {
            d.conveyorBelt.set(Value.kOff);//Stop Conveyer Belt
        }

        boolean cState = d.gathererBottom.get();//get ball counter limit switch
        states = add2Array(states, cState);//add latest limit switch value

        if (ballCounted) {
           if (!states[0] && !states[1] && !states[2] && !states[3]) {
                ballCounted = false;//check if the ball is out
            }
        } else {
            if (states[0] && states[1] && states[2] && states[3]) {//check if the ball is in
                ballCounted = true; 
                c.ballCount++;
            }
        }
    }

    public boolean[] add2Array(boolean[] array, boolean data) {

        array[3] = array[2];
        array[2] = array[1];
        array[1] = array[0];
        array[0] = data;

        return array;
    }
}