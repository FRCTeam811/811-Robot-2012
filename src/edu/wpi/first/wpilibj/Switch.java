/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.wpilibj;

/**
 *
 * @author Saswat
 */
public class Switch {
    DigitalInput di1;
    DigitalInput di2;

    public Switch(DigitalInput di1, DigitalInput di2) {
        this.di1 = di1;
        this.di2 = di2;
    }
    
    public int get() {
        boolean i1 = di1.get();
        boolean i2 = di2.get();
        //if()
        return -1;
    }
}
