/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.Relay;
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
         * d.shooterEncoder.setDistancePerPulse(1/360); //one rotation of the
         * wheel = 1 unit (rpm) thePIDWheel.setTolerance(3); //TODO: tweak
         * tolerance levels thePIDWheel.disable();
         * thePIDTurret.setSetpoint(0.00); thePIDTurret.setTolerance(3);//TODO:
         * tweak tolerance levels thePIDTurret.enable();
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
        if (d.joy2.getRawButton(c.autoMode)) {
            manualOn = false;
        }
        if (d.joy2.getRawButton(c.maunalMode)) {
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
        if (!manualOn) {
            checkBallState();
        }

    }

    public void logic1(Object param) {//autonomous
        if (param == "Start") {
            double initTime = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
            while (vision() && edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - initTime < 2) {
            }
            startShooter(.8);
            timer.schedule(new autoShootAtTime(), 2000);
            timer.schedule(new autoShootAtTime(), 3000);
            timer.schedule(new stopShooter(), 4000);
            //timer.schedule(new goBack(), 4000);//TODO uncomment
            timer.schedule(new stopDrive(), 5000);
            //timer.schedule(new gotoBridge(), 4000);
            //timer.schedule(new pushBridge(), 6500);
            //timer.schedule(new stopDrive(), 6500);
        }

    }

    public double getSpeed() {
        double mspeed = 1;
        double speeds[];

        switch (ballState) {
            case 0:
                speeds = c.speedsOld;
                break;
            case 1:
                speeds = c.speedsMed;
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
    }

    public boolean vision() {
        xOff = 0;

        d.vision.update();

        if (d.vision.nData) {
            xOff = d.vision.xOffset;
        }

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

        /*
         * if (!limitSwitch) { if (Math.abs(d.joy2.getRawAxis(4)) < .1) {
         * d.turret.set(0); } else { d.turret.set(d.joy2.getRawAxis(4) *
         * (slowTurret ? .5 : 1)); } } else if (d.joy2.getRawAxis(4) < 0) { if
         * (Math.abs(d.joy2.getRawAxis(4)) < .1) { d.turret.set(0); } else {
         * d.turret.set(d.joy2.getRawAxis(4) * (slowTurret ? .5 : 1)); } } else
         * { d.turret.set(0);
        }
         */
        if (Math.abs(d.joy2.getRawAxis(4)) < .2) {
            d.turret.set(0);
        } else {
            d.turret.set(d.joy2.getRawAxis(4) * (slowTurret ? .5 : .8));
        }
    }

    public void manualShooting() {

        if (d.joy2.getRawButton(c.shooterPower4)) {
            speed = 1.0;
        }

        if (d.joy2.getRawButton(c.shooterPower3)) {
            speed = 0.9;
        }

        if (d.joy2.getRawButton(c.shooterPower2)) {
            speed = 0.7;
        }

        if (d.joy2.getRawButton(c.shooterPower1)) {
            speed = 0.5;
        }

        if (d.joy2.getRawButton(10)) {
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
        int neg = 1;
        if (xOffset < 0) {
            neg = -1;
        }

        if (turretStopPlanned) {
            return;
        }
        d.turret.set(Math.max(Math.min(.3 * (xOffset / 100), 1), .1));
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
            c.gathererOn = true;
            c.feederOn = true;
        }

        public void stop() {
            c.gathererOn = false;
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

    class autoShootAtTime extends TimerTask {

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
            d.conveyorBelt.set(-1);
            d.feeder.set(Relay.Value.kReverse);
        }

        public void stop() {
            //d.conveyorBelt.set(Relay.Value.kOff);
            d.conveyorBelt.set(0);
            d.feeder.set(Relay.Value.kOff);
        }
    }

    class gotoBridge extends TimerTask {

        public void run() {
            //d.leftDriveJag.set(-.75);
            //d.rightDriveJag.set(-.75);
            d.rd1.arcadeDrive(0.75, 0);
            long sTime = System.currentTimeMillis();
            
            try {
                this.wait(1500);//wait for ball to load
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            //d.leftDriveJag.set(0);
            //d.rightDriveJag.set(0);
            d.rd1.arcadeDrive(0, 0);
        }
    }

    class pushBridge extends TimerTask {

        public void run() {
            d.bridgeArm.set(-1);
        }
    }
    
    class goBack extends TimerTask {

        public void run() {
            d.rd1.arcadeDrive(0.75, 0);
        }
    }
    class stopDrive extends TimerTask {

        public void run() {
            d.rd1.arcadeDrive(0, 0);
        }
    }
}
