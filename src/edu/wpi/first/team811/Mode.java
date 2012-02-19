/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811;

import edu.wpi.first.wpilibj.Watchdog;

/**
 *
 * @author Saswat
 */
public abstract class Mode {
    //<editor-fold defaultstate="collapsed" desc="StartUp">
    public Devices d;
    public Configuration c;
    public Watchdog watchdog;
    public boolean done = false;
    public Team811Robot tr = null;

    public Mode(Team811Robot teamrobot) {
        d = teamrobot.devices;
        c = teamrobot.config;
        watchdog = teamrobot.getWatchdog();
        tr = teamrobot;
        runOnce();
    }
    
    /*public Mode(Devices dev, Configuration config, Watchdog wd) {
        d = dev;
        c = config;
        watchdog = wd;
        runOnce();
    }*/
    //</editor-fold>
    
    /**
     * Runs when robot is started
     */
    public abstract void runOnce();
    
    /**
     * Runs once when mode is enabled
     */
    public abstract void init();
    
    /**
     * Runs continuously while mode is enabled
     */
    public abstract void execute();
    
    /**
     * Any very important code goes here
     */
    public abstract void highPriortiy();
    
    /**
     * Runs once mode is disabled
     */
    public abstract void disable();
}
