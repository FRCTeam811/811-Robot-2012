/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811.Modes;

import edu.wpi.first.team811.Mode;
import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.team811.subsystems.*;
import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Watchdog;

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
        bridgeArm = new BridgeArm(tr);
        drivetrain = new DriveTrain(tr);
        gatherer = new Gathering(tr);
        feeder = new Feeder(tr);
        shooter = new Shooter(tr);
        
        w = tr.getWatchdog();
    }
    
    /**  
     * Runs once when operator control is enabled
     */
    public void init() {
        //d.shooterEncoder.start();
        shooter.enabled();
    }
    
    /**
     * Runs continuously while operator control is enabled
     */
    public void execute() {
        ds.waitForData();
        w.feed();
        bridgeArm.execute(null);
        drivetrain.execute(null);
        feeder.execute(null);
        gatherer.execute(null);
        shooter.execute(null);
    }

    /**
     * Any very important code goes here
     */
    public void highPriortiy() {
        
    }
    
    /**
     * Pauses all moving parts to yield to exclusive mode
     */
    public void pause() {
        bridgeArm.pause();
        drivetrain.pause();
        feeder.pause();
        gatherer.pause();
        shooter.pause();
    }
    
    /**
     * Runs once operator control is disabled
     */
    public void disable() {
        shooter.disabled();
        //d.shooterEncoder.stop();
    }
    
    SubSystem bridgeArm;
    SubSystem drivetrain;
    SubSystem feeder;
    SubSystem gatherer;
    SubSystem shooter;
    
    DriverStation ds = DriverStation.getInstance();
    Watchdog w;
}
