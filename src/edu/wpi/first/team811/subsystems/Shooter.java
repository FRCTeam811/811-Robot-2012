/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Eric
 */
public class Shooter extends SubSystem {

    //initializes the PID control for the wheel
    //TODO: need to set kP, kI, and kD values
    //TODO: need to set what to do with output
    //uses info from encoder
//    PIDController wheelPID = new PIDController(c.shooterWheelPIDkP, c.shooterWheelPIDkI, c.shooterWheelPIDkD, d.shooterEncoder,
//            new PIDOutput() {
//
//                public void pidWrite(double output) {
//                    d.shooter.set(output);
//                }
//            });
    public Shooter(Team811Robot teamrobot) {
        super(teamrobot);
        SmartDashboard.putString("Ball State", "Old");
//        d.shooterEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
//        d.shooterEncoder.setDistancePerPulse(.25);
//        d.shooterEncoder.start();
//        SmartDashboard.putBoolean("Encoder is on:", d.shooterEncoder.getStopped());
//        wheelPID.setTolerance(5.0);
//        wheelPID.disable();
//        wheelPID.setOutputRange(.5, 1);

        /*
         * d.shooterEncoder.setDistancePerPulse(1/360); //one rotation of the wheel = 1 unit (rpm) 
         * thePIDWheel.setTolerance(3); //TODO: tweak tolerance levels 
         * thePIDWheel.disable();
         * thePIDTurret.setSetpoint(0.00); 
         * thePIDTurret.setTolerance(3);//TODO: tweak tolerance levels 
         * thePIDTurret.enable();
         */
    }

    //used to set the wanted speed for the shooter using the PID controller
//    public void setShooterSpeed(double rpm) {
//        wheelPID.setSetpoint(rpm);
//        wheelPID.enable();
//    }
    //this depends on the tolerance values
//    public boolean shooterReady() {
//        return wheelPID.onTarget();
//    }
    public String ID() {
        return "Shooter";
    }

    public void logic(Object param) {
        if (turretStopPlanned) {
            return;
        }
        if(d.joy2.getRawButton(c.autoMode)) {
            manualOn = false;
        }
        if(d.joy2.getRawButton(c.maunalMode)) {
            manualOn = true;
        }
        SmartDashboard.putBoolean("Manual Mode", manualOn);
        if (d.joy2.getRawButton(c.autoTurret)) {
            vision();
            return;
        } else {
            d.turret.set(0);
            manualTurret();
        }
        if (d.joy2.getRawAxis(c.shoot) == 1) {
            if (manualOn) {
                manualShooting();
            } else {
                startShooter(getSpeed());
                timer.schedule(new shootAtTime(), 2000);
                timer.schedule(new stopShooter(), 3000);
            }
        }
        if(!manualOn) {
            checkBallState();
        }

//        if (vision() && rectH != 0) {
//            startShooter(getSpeed());
//            timer.schedule(new shootAtTime(), 2000);
//        }

    }

    public void logic1(Object param) {
        double initTime = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
        while (vision() && edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - initTime < 2) {
        }
        startShooter(1);
        timer.schedule(new shootAtTime(), 2000);
        timer.schedule(new shootAtTime(), 3000);
        timer.schedule(new stopShooter(), 4000);
    }

    public double getSpeed() {
        double mspeed = 1;
        double speeds[];

        switch (ballState) {
            case 0:
                speeds = c.speedsOld;
                break;
            case 1:
                speeds = c.speedsMedium;
                break;
            case 2:
                speeds = c.speedsNew;
                break;
            default:
                speeds = null;
        }

        for (int i = 0; i < speeds.length; i++) {
            if (c.heights1[i] <= rectH && c.heights2[i] >= rectH) {
                mspeed = speeds[i];
                i = speeds.length;
            }
        }
        return mspeed;
    }

    public void startShooter(double speed) {
        speed *= -1;
        d.shooter.set(speed);
        //d.shooter2.set(speed);
    }

    public boolean vision() {
        xOff = 0;

        d.vision.update();

        if (d.vision.nData) {
            xOff = d.vision.xOffset;
        }
        //TODO BUGBUG faulty logic. What happens when xOff is 8? - Fixed
        if (xOff < c.visionErrorMargin && xOff > -c.visionErrorMargin) {
            xOff = 0;// Allow margin of error
        }
        if (xOff == 0) {
            d.turret.set(0);
            rectH = d.vision.height;
            return true;
        }

        centerTurret(xOff);
        debug("" + xOff);

        return false;

    }

    public void manualTurret() {
        if (turretStopPlanned) {
            return;
        }

        if (d.joy2.getRawButton(c.turretFullSpeed)) {
            slowTurret = false;
        }
        if (d.joy2.getRawButton(c.turretSpeed2)) {
            slowTurret = true;
        }

        boolean limitSwitch = !d.turretLimit.get();
        SmartDashboard.putBoolean("Turret Limit Switch:", limitSwitch);
        
        if (!limitSwitch) {
            d.turret.set(d.joy2.getRawAxis(4) * (slowTurret ? .75 : 1));
        } else if (d.joy2.getRawAxis(4) < 0) {
            d.turret.set(d.joy2.getRawAxis(4) * (slowTurret ? .75 : 1));
        }
        
//        boolean limitSwitch = !d.turretLimit.get();
//        SmartDashboard.putBoolean("Turret Limit Switch:", limitSwitch);
//
//        if (limitSwitch != lastState) {
//            lastState = limitSwitch;
//            if (d.joy2.getRawAxis(4) > 0) {
//                tGoingPlus = true;
//            } else {
//                tGoingPlus = false;
//            }
//        }
//
//        if (!limitSwitch) {
//            d.turret.set(d.joy2.getRawAxis(4) * (slowTurret ? .75 : 1));
//        } else if (tGoingPlus && d.joy2.getRawAxis(3) < 0) {
//            d.turret.set(d.joy2.getRawAxis(4));
//        } else if (!tGoingPlus && d.joy2.getRawAxis(3) > 0) {
//            d.turret.set(d.joy2.getRawAxis(4));
//        } else {
//            d.turret.set(0);
//        }
    }

    public void manualShooting() {

        if (d.joy2.getRawButton(c.shooterPower4)) {
            //if (speed == 1.0) {
            //    speed = 0.0;
            //} else {
                speed = 1.0;
            //}
        }

        if (d.joy2.getRawButton(c.shooterPower3)) {
            //if (speed == 0.9) {
            //    speed = 0.0;
            //} else {
                speed = 0.9;
            //}
        }

        if (d.joy2.getRawButton(c.shooterPower2)) {
            //if (speed == 0.7) {
            //    speed = 0.0;
            //} else {
                speed = 0.7;
            //}
        }

        if (d.joy2.getRawButton(c.shooterPower1)) {
            //if (speed == 0.6) {
            //    speed = 0.0;
            //} else {
                speed = 0.6;
            //}
        }
        
        if(d.joy2.getRawButton(10)) {
            speed = 0;
        }

        debug2dashboard("running at " + speed);

        startShooter(speed);
    }

    public void checkBallState() {
        if (d.joy2.getRawButton(c.shooterPower1)) {
            ballState = 0;
            SmartDashboard.putString("Ball State", "Old");
        }
        if (d.joy2.getRawButton(c.shooterPower2)) {
            SmartDashboard.putString("Ball State", "Medium");
            ballState = 1;
        }
        if (d.joy2.getRawButton(c.shooterPower3)) {
            SmartDashboard.putString("Ball State", "New");
            ballState = 2;
        }
    }

    public void centerTurret(double xOffset) {
        /*
         * if (d.turretLimit.get()) { SmartDashboard.putBoolean("Turret Limit
         * Switch Hit", true); return; } else {
         * SmartDashboard.putBoolean("Turret Limit Switch Hit", false); }
         */
        int neg = 1;
        if (xOffset < 0) {
            neg = -1;
        }
        /*
         * if (Math.abs(xOffset) > 70) { d.turret.set(1 * neg); } else if
         * (Math.abs(xOffset) > 40) { d.turret.set(.9 * neg); } else if
         * (Math.abs(xOffset) > 5) { d.turret.set(.8 * neg); } else {
         * d.turret.set(0); }
         */
        if (turretStopPlanned) {
            return;
        }
        d.turret.set(.3 * neg);
        
        //long time = (long) (xOffset * 10);
        //timer.schedule(new stopTurret(), time);
        //turretStopPlanned = true;
    }
    double speed = 0.0;
    public double xOff = 0;
    double rectH = 0;
    boolean turretStopPlanned = false;
    Timer timer = new Timer();
    boolean manualOn = false;
    boolean slowTurret = false;
    boolean tGoingPlus = false;
    boolean lastState = false;
    int ballState = 0;//0-old, 1-medium, 2-new

    class shootAtTime extends TimerTask {

        public void run() {
            load();//load and shoot
            debug("shooting");
            try {
                this.wait(2000);//wait for ball to load
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            stop();
        }

        public void load() {
            //d.conveyorBelt.set(Relay.Value.kReverse);
            c.gathererOn = true;
            //d.feeder.set(Relay.Value.kReverse);
            c.feederOn = true;
        }

        public void stop() {
            //d.conveyorBelt.set(Relay.Value.kOff);
            c.gathererOn = false;
            //d.feeder.set(Relay.Value.kOff);
            c.feederOn = false;
        }
    }

    class stopShooter extends TimerTask {

        public void run() {
            startShooter(0);
        }
    }

    class stopTurret extends TimerTask {

        public void run() {
            d.turret.set(0);
            turretStopPlanned = false;
        }
    }
}
