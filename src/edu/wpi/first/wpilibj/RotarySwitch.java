/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.wpilibj;

/**
 *
 * @author Saswat
 */
public class RotarySwitch {

    DigitalInput di1;
    DigitalInput di2;
    DigitalInput di3;
    DigitalInput di4;

    public RotarySwitch(DigitalInput di1, DigitalInput di2, DigitalInput di3, DigitalInput di4) {
        this.di1 = di1;
        this.di2 = di2;
        this.di3 = di3;
        this.di4 = di4;
    }
    
    public int get() {
        boolean i1 = di1.get();
        boolean i2 = di2.get();
        boolean i3 = di3.get();
        boolean i4 = di4.get();
        //i4 i3 i2 i1
        if(i1 && i2 && i3 && i4) {//0000
            return 0;
        }
        if(i1 && i2 && i3 && !i4) {//0001
            return 1;
        }
        if(i1 && i2 && !i3 && i4) {//0010
            return 2;
        }
        if(i1 && i2 && !i3 && !i4) {//0011
            return 3;
        }
        if(i1 && !i2 && i3 && i4) {//0100
            return 4;
        }
        if(i1 && !i2 && i3 && !i4) {//0101
            return 5;
        }
        if(i1 && !i2 && !i3 && !i4) {//0111
            return 6;
        }
        if(!i1 && i2 && i3 && i4) {//1000
            return 7;
        }
        if(!i1 && i2 && i3 && !i4) {//1001
            return 8;
        }
        if(!i1 && i2 && !i3 && i4) {//1010
            return 9;
        }
        //Theoretical
        if(!i1 && i2 && !i3 && !i4) {//1011
            return 10;
        }
        if(!i1 && !i2 && i3 && i4) {//1100
            return 11;
        }
        if(!i1 && !i2 && i3 && !i4) {//1101
            return 12;
        }
        if(!i1 && !i2 && !i3 && !i4) {//1111
            return 13;
        }
        
        return -1;
    }
}
