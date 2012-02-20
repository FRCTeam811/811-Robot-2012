/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.Modes;

/**
 *
 * @author David Grossman last updated February 4, 2012
 */
import edu.wpi.first.team811.Mode;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.team811.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Kinect;
import edu.wpi.first.wpilibj.KinectStick;
import edu.wpi.first.wpilibj.Skeleton;
import edu.wpi.first.wpilibj.Timer;

public class Hybrid extends Mode {

    //<editor-fold defaultstate="collapsed" desc="init">
    public Hybrid(Team811Robot teamrobot) {
        super(teamrobot);
    }
    //</editor-fold>
    Kinect kinect;
    KinectStick kStick;
    Skeleton sk;
    /*
     * 1) Head to the Right
     * 2) Head to the Left
     * 3) Right leg out to the right 
     * 4) Left Leg out to the left 
     * 5) Right Leg Forward 
     * 6) Right Leg Back 
     * 7) Left Leg Forward 
     * 8) Left Leg Back
     */
    final static int HEADTORIGHT = 1;
    final static int HEADTOLEFT = 2;
    final static int RIGHTLEGTOTHERIGHT = 3;
    final static int LEFTLEGTOTHELEFT = 4;
    final static int RIGHTLEGFORWARD = 1;

    public void runOnce() {
    }

    public void init() {
        kinect = Kinect.getInstance();
        kStick = new KinectStick(1);
        sk = kinect.getSkeleton();
    }

    public void execute() {
        if (Timer.getFPGATimestamp() > 5) {
            d.rd1.tankDrive(kStick.getY(Hand.kLeft) * (0.90), kStick.getY(Hand.kRight) * (0.90));
        }
        
        if (kStick.getRawButton(RIGHTLEGTOTHERIGHT)) {
            d.bridgeArm.set(-1);
        }
        if (kStick.getRawButton(LEFTLEGTOTHELEFT)) {
            d.bridgeArm.set(1);
        }
    }

    public void highPriortiy() {
    }

    public void disable() {
    }

    public void pause() {
    }
    
    private void shoot() {
    }

}
