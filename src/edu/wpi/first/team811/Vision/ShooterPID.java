/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.Vision;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;

/**
 *
 * @author saswat
 */
public class ShooterPID extends PIDController {

    Jaguar out;
    
    public ShooterPID(Encoder source, Jaguar output) {
        super(.0002, .0002, .005, source, output, .1);
        
        out = output;
        
        setOutputRange(-1, 0);
        setTolerance(5);
    }

    public synchronized void disable() {
        super.disable();
        out.pidWrite(get());
    }
}
