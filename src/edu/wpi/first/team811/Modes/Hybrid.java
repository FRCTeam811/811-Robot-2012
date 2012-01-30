/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.Modes;

/**
 *
 * @author David
 */
import edu.wpi.first.team811.Mode;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.Kinect;
import edu.wpi.first.wpilibj.KinectStick;
import edu.wpi.first.wpilibj.Skeleton;

public class Hybrid extends Mode{

    //<editor-fold defaultstate="collapsed" desc="init">
    public Hybrid(Team811Robot teamrobot) {
        super(teamrobot);
    }
    //</editor-fold>
    
    Kinect kinect;
    KinectStick kStick;
    Skeleton sk;

    public void runOnce() {
        
        
    }

    public void init() {
        kinect = Kinect.getInstance();
        kStick = new KinectStick(1);
        sk = kinect.getSkeleton();
    }

    public void execute() {
        while(true){
          // kStick.
        }
    }

    public void disable() {
    }
    
}
