/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811.Modes;

import edu.wpi.first.team811.Mode;
import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.team811.subsystems.*;

/**
 *
 * @author Saswat
 */
public class OperatorControl extends Mode {

    //<editor-fold defaultstate="collapsed" desc="StartUp">
    public OperatorControl(Team811Robot tr) {
        super(tr);
    }
    
    /*public OperatorControl(Devices dev, Configuration config, Watchdog wd) {
        super(dev, config, wd);
    }*/
    //</editor-fold>
    
    /**
     * Runs when robot is started
     */
    public void runOnce() {
        
        /*  shooter
         * feeder wheel
         * gatherer
         */
        bridgeArm = new BridgeArm(tr);
        drivetrain = new DriveTrain(tr);
        gatherer = new Gathering(tr);
        feeder = new Feeder(tr);//TODO disable debug feeder
        shooter = new ShooterDebug(tr);//TODO stop using Shooter Debug
    }
    
    /**  
     * Runs once when operator control is enabled
     */
    public void init() {
        
    }
    
    /**
     * Runs continuously while operator control is enabled
     */
    public void execute() {
        bridgeArm.execute(null);
        drivetrain.execute(null);
        feeder.execute(null);
        gatherer.execute(null);
        shooter.execute(null);
    }
    
    /**
     * Runs once operator control is disabled
     */
    public void disable() {
        
    }
    
    SubSystem bridgeArm;
    SubSystem drivetrain;
    SubSystem feeder;
    SubSystem gatherer;
    SubSystem shooter;
}
