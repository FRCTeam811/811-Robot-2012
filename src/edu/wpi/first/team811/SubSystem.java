/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 * @author Saswat
 */
public abstract class SubSystem {

    public Devices d;
    public Configuration c;
    private long waitend = 0;
    public Team811Robot tr = null;

    /**
     * Sets the global Devices, Configuration and Robot variables
     *
     * @param teamrobot the robot file (try "tr")
     */
    public SubSystem(Team811Robot teamrobot) {
        d = teamrobot.devices;
        c = teamrobot.config;
        tr = teamrobot;
    }
    
    /*
     * Sets the global Devices and Configuration variables
     *
     * @param dev the main Devices file
     * @param config the main Configuration file
     */
    /*public SubSystem(Devices dev, Configuration config) {
        d = dev;
        c = config;
    }*/

    /**
     * Identification
     *
     * @return id of class
     */
    public abstract String ID();

    /**
     * Code to execute
     */
    public abstract void logic(Object param);

    /**
     * Custom print line statement
     *
     * @param text String to print
     */
    public void debug(String text) {
        System.out.println("Class " + ID() + ": " + text);
    }
    
    /**
     * Displays a log message to the SmartDashboard with the class file's ID
     *
     * @param text String to print
     */
    public void debug2dashboard(String text) {
        SmartDashboard.putString(ID(), text);
    }

    /**
     * Pauses the SubSystem for the number of milliseconds specified
     *
     * @param millis time in milliseconds to wait
     */
    public void wait(int millis) {
        waitend = System.currentTimeMillis() + millis;
    }

    /**
     * Checks if the SubSystem is currently paused
     *
     * @return true if the SubSystem is paused
     */
    public boolean isWaiting() {
        if (System.currentTimeMillis() < waitend) {
            return true;
        }
        return false;
    }

    /**
     * Main function executed in Mode file
     *
     * @param param any Object needed for the logic statement, usually is null
     */
    public void execute(Object param) {
        if (!isWaiting()) {
            c = tr.config;
            logic(param);
        }
    }

    /**
     * Calling function for extra logic functions
     *
     * @param number which logic function to call
     * @param param any Object needed for the logic statement, usually is null
     */
    public void execute(int number, Object param) {
        if (!isWaiting()) {
            c = tr.config;
            switch (number) {
                case 1:
                    logic1(param);
                    break;
                case 2:
                    logic2(param);
                    break;
                case 3:
                    logic3(param);
                    break;
            }
        }
    }
    
    

    /**
     * OPTIONAL extra logic method
     *
     * @param param any Object needed for the logic statement, usually is null
     */
    public void logic1(Object param) {
        
    }

    /**
     * OPTIONAL extra logic method
     *
     * @param param any Object needed for the logic statement, usually is null
     */
    public void logic2(Object param) {
    }

    /**
     * OPTIONAL extra logic method
     *
     * @param param any Object needed for the logic statement, usually is null
     */
    public void logic3(Object param) {
    }
}
