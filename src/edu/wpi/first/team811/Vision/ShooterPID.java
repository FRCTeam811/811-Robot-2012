/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.Vision;

import edu.wpi.first.team811.Configuration;
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
        super(Configuration.pidP, Configuration.pidI, Configuration.pidD, source, output, Configuration.pidPeriod);
        
        out = output;
        
        setOutputRange(-1, 0);
        setTolerance(Configuration.pidTolerance);
    }

    public synchronized void disable() {
        super.disable();
        out.pidWrite(get());
    }
}
