/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;

/**
 *
 * @author Eric
 */
public class Shooter extends SubSystem {
    //initializes the PID control for the wheel
    //TODO: need to set kP, kI, and kD values
    //TODO: need to set what to do with output
    //uses info from encoder
    /*PIDController thePIDWheel = new PIDController(0.00, 0.00, 0.00, d.shooterEncoder, new PIDOutput() {
        public void pidWrite(double output) {
            d.shooter.set(output);
        }
    });*/
    
    //initializes the PID control for the turret
    //TODO: need to set kP, kI, and kD values
    //TODO: need to set what to do with output
    //uses info from camera. It is always running but will only run when there is new data
    PIDController thePIDTurret = new PIDController(0.00, 0.00, 0.00, new PIDSource() {

        public double pidGet() {
            if(xOff != -1)
                return xOff;
            else
                return 0;
        }
    }, new PIDOutput() {

        public void pidWrite(double output) {
            d.turret.set(output);
        }
    });

    public Shooter(Team811Robot teamrobot) {
        super(teamrobot);
        /*d.shooterEncoder.setDistancePerPulse(1/360); //one rotation of the wheel = 1 unit (rpm)
        thePIDWheel.setTolerance(3); //TODO: tweak tolerance levels
        thePIDWheel.disable();
        thePIDTurret.setSetpoint(0.00);
        thePIDTurret.setTolerance(3);//TODO: tweak tolerance levels
        thePIDTurret.enable();*/
    }
    
    //used to set the wanted speed for the shooter using the PID controller
    /*public void setShooterSpeed(double rpm){
        thePIDWheel.setSetpoint(rpm);
        thePIDWheel.enable();
    }*/
    
    //this depends on the tolerance values
    /*public boolean shooterReady(){
        if (thePIDWheel.onTarget())
            return true;
        else
            return false;
    }*/

    public String ID() {
        return "Shooter";
    }
    
    public double xOff;

    public void logic(Object param) {
        //debug("exec");

        //ButtonA is Full Power 
        if (d.joy1.getRawButton(c.shooterFullpower)) speed = 1.0;
        //ButtonB is 75% power 
        if (d.joy1.getRawButton(c.shooter75power)) speed = 0.75;
        //ButtonX is  60% 
        if (d.joy1.getRawButton(c.shooter60power)) speed = 0.60;
        //Power ButtonY is 50% power
        if (d.joy1.getRawButton(c.shooter50power)) speed = 0.5;
        //Start button to reset power 
        if (d.joy1.getRawButton(c.shooterResetPower)) speed = 0.0;
        
        d.turret.set(d.joy1.getRawAxis(3));
        
        shoot(speed);
        
        xOff = 0;
        
        if(true) return;
        
        if(d.joy1.getRawButton(c.shooterTrack)) {
            
            d.vision.update();
            
            if(d.vision.nData) xOff = d.vision.xOffset;
            if(xOff == 0) {
                //debug("not moving turret");
                return;
            }
            debug(""+xOff);
        }
    }
    
    
    
    public void shoot(double speed) {
        speed *= -1;
        d.shooter.set(speed);
        d.shooter2.set(speed);
    }
    
    double speed = 0.0;

    /*public void shooter(double speed) {
        d.shooter.set(speed);
    }

    *
     * written by David Grossman this is a routine to shoot assuming that -we
     * are shooting one ball at a time -the feeder can load a ball without
     * running the gatherer -we are using user input to start and stop the
     * shooter When the trigger is pulled, the shooter will begin When the
     * trigger is released, the shooting sequence will stop To shoot multiple
     * balls, the driver will have to pull the trigger multiple times
     *
     *
    private void shoot() {
        if (isShooting()) {
            if (isBallAtTop()) {
                if (turretAligned() && shooterReady()) {
                    startFeeder();
                }
            }
        } else {
            stopFeeder();
        }
    }

    private boolean isShooting() {
        if (d.joy2.getRawButton(c.shootButton)) {
            return true;
        } else {
            return false;
        }
    }*/
}
