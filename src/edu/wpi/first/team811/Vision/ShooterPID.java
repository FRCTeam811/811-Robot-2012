/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.Vision;

import edu.wpi.first.team811.Configuration;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author saswat
 */
public class ShooterPID extends PIDController {

    Jaguar out;
    //I have a feeling we will need to change the output ranges to 0 and 1
    //and switch the encoder ports
    //If it works, don't fix it, entender?
    //well it worked when i was setting the wheel speeds to negative numbers but on this bot, we're setting it to positive values
    
    public ShooterPID(final Encoder source, final Jaguar out) {
        super(Configuration.pidP, Configuration.pidI, Configuration.pidD, new PIDSource() {

            public double pidGet() {
                return source.getRate();
            }
        }, new PIDOutput() {

            public void pidWrite(double output) {
                out.set(output);
                System.out.println(source.getRate());
                System.out.println(output);
            }
        }, Configuration.pidPeriod);
        
        this.out = out;
        
        setOutputRange(-1, 0);
        setTolerance(Configuration.pidTolerance);
    }

    public synchronized void disable() {
        super.disable();
        
        out.pidWrite(get());
    }
}
