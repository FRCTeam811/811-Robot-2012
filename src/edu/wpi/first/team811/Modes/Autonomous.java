/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811.Modes;

import edu.wpi.first.team811.Mode;
import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.team811.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Saswat
 */
public class Autonomous extends Mode {

    //<editor-fold defaultstate="collapsed" desc="StartUp">
    public Autonomous(Team811Robot tr) {
        super(tr);
        SmartDashboard.putInt(c.SDautoMode, 0);
    }
    /*public Autonomous(Devices dev, Configuration config, Watchdog wd) {
        super(dev, config, wd);
    }*/
    //</editor-fold>
    
    /**
     * Runs when robot is started
     */
    public void runOnce() {
        shooter = new Shooter(tr);
    }
    
    /**
     * Runs once when autonomous is enabled
     */
    public void init() {
        int key = SmartDashboard.getInt(c.SDautoMode, 0);
        if(key == 0) shooter.execute(1, "Start");
        else if(key == 1) shooter.execute(2, "Start");
    }
    
    /**
     * Runs continuously while autonomous is enabled
     */
    public void execute() {
        
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
        //shooter.pause();
    }
    
    /**
     * Runs once autonomous is disabled
     */
    public void disable() {
        
    }
    
    Mode hybrid;
    SubSystem shooter;
}
