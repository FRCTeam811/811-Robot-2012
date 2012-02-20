/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.team811.Vision.ShooterPID;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Eric
 */
public class Shooter extends SubSystem {

    boolean manualOn = false;
    boolean slowTurret = false;
    boolean turretStopPlanned = false;
    boolean turretTimerHit = false;
    double rectH = 0;
    double xOff = 0;
    double speed = 0.0;//for shooter
    int ballState = 0;//0-old, 1-medium, 2-new
    ShooterPID wheelPID;
    Timer timer = new Timer();
    DriverStation ds = DriverStation.getInstance();

    public Shooter(Team811Robot teamrobot) {
        super(teamrobot);
        SmartDashboard.putString("Ball State", "Old");
        
        wheelPID = new ShooterPID(d.shooterEncoder,d.shooter);
        
        d.shooterEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        d.shooterEncoder.setDistancePerPulse(.004);
        
        d.shooterEncoder.start();
    }

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
                //startShooter(getSpeed());
                autoStartShooter();
                new load().run();
                //timer.schedule(new load(), 2000);
                while(d.joy2.getRawAxis(c.shoot) == 1) ds.waitForData();
                new stopload().run();
                new shoot(0).run();
                //timer.schedule(new stopload(), 1000);
                //timer.schedule(new shoot(0), 1000);
            }
        }
        if (!manualOn) {
            checkBallState();
        }

    }

    public void logic1(Object param) {//autonomous
        if (param == "Start") {
            double initTime = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
            while (vision() && edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - initTime < 2) {// for 2 seconds adjust shooter
            }

            //Shoot 2 balls
            //startShooter(.8);
            autoStartShooter();
            new load().run();
            //timer.schedule(new load(), 2000);
            timer.schedule(new stopload(), 2000);
            timer.schedule(new shoot(0), 2000);

            //Move to the bridge
            timer.schedule(new moveDrive(.75, 0), 4000);
            timer.schedule(new moveDrive(0, 0), 6500);
            timer.schedule(new bridge(-1), 6500);
            timer.schedule(new bridge(0), 7500);
        }

    }

    public void logic2(Object param) {
        double initTime = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
        while (vision() && edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - initTime < 2) {// for 2 seconds adjust shooter
        }
        //startShooter(.8);
        autoStartShooter();
        //timer.schedule(new load(), 2000);
        new load().run();
        timer.schedule(new stopload(), 2000);
        timer.schedule(new shoot(0), 2000);
    }

    public void exclusiveRun(Object param) {//Set shooter speed with PID
        wheelPID.enable();
        wheelPID.setSetpoint(((Double)param).doubleValue());
        while (!wheelPID.onTarget() && d.joy2.getRawAxis(c.shoot) == 1) {
            ds.waitForData();
        }
        wheelPID.disable();
    }

    public void pause() {
        d.shooter.set(0);
        d.turret.set(0);
    }

    //<editor-fold defaultstate="collapsed" desc="Old Shooter Speed Set">
    /*public double getSpeed() {//Motor speed based
     * double mspeed = 1;
     * double speeds[];
     * 
     * switch (ballState) {
     * case 0:
     * speeds = c.speedsOld;
     * break;
     * case 1:
     * speeds = c.speedsMed;
     * break;
     * case 2:
     * speeds = c.speedsNew;
     * break;
     * default:
     * speeds = c.speedsMed;
     * }
     * 
     * for (int i = 0; i < speeds.length; i++) {
     * if (c.heights1[i] <= rectH && c.heights2[i] >= rectH) {
     * mspeed = speeds[i];
     * i = speeds.length;
     * }
     * }
     * return mspeed;
     * }*/
    //</editor-fold>
    
    public double getSpeed() {//Encoder based
        double mspeed = 80;
        double speeds[];

        switch (ballState) {
            case 0:
                speeds = c.espeedOld;
                break;
            case 1:
                speeds = c.espeedMed;
                break;
            case 2:
                speeds = c.espeedNew;
                break;
            default:
                speeds = c.espeedMed;
        }

        for (int i = 0; i < speeds.length; i++) {
            if (c.heights1[i] <= rectH && c.heights2[i] >= rectH) {
                mspeed = speeds[i];
                i = speeds.length;
            }
        }
        SmartDashboard.putDouble("Shooter At RAE:", mspeed);
        return mspeed;
    }
    
    public void autoStartShooter() {//Starts the shooter based on rectangle input
        tr.runExclusive(this, new Double(getSpeed()));
    }

    public void startShooter(double sSpeed) {
        sSpeed *= -1;
        d.shooter.set(sSpeed);
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

        if (limitSwitch && !turretTimerHit) {
            timer.schedule(new TimerTask() {

                public void run() {
                    turretTimerHit = true;
                }
            }, 2000);
        } else if (Math.abs(d.joy2.getRawAxis(4)) < .2) {
            d.turret.set(0);
        } else {
            d.turret.set(d.joy2.getRawAxis(4) * (slowTurret ? .5 : .8));
        }

        if (!limitSwitch) {
            turretTimerHit = false;
        }
    }

    public void manualShooting() {

        if (d.joy2.getRawButton(c.shooterPower4)) {
            speed = 1.0;
        } else if (d.joy2.getRawButton(c.shooterPower3)) {
            speed = 0.9;
        } else if (d.joy2.getRawButton(c.shooterPower2)) {
            speed = 0.7;
        } else if (d.joy2.getRawButton(c.shooterPower1)) {
            speed = 0.5;
        } else if (d.joy2.getRawButton(10)) {
            speed = 0;
        }

        debug2dashboard("running at " + speed);

        startShooter(speed);
    }

    public void checkBallState() {
        if (d.joy2.getRawButton(c.shooterPower1)) {
            ballState = 0;
            SmartDashboard.putString("Ball State", "Old");
        } else if (d.joy2.getRawButton(c.shooterPower2)) {
            SmartDashboard.putString("Ball State", "Medium");
            ballState = 1;
        } else if (d.joy2.getRawButton(c.shooterPower3)) {
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

    class moveDrive extends TimerTask {

        double forward, rotate;

        public moveDrive(double f, double r) {
            forward = f;
            rotate = r;
        }

        public void run() {
            d.rd1.arcadeDrive(forward, rotate);
        }
    }

    class shoot extends TimerTask {

        double speed;

        public shoot(double s) {
            speed = s;
        }

        public void run() {
            startShooter(speed);
        }
    }

    class load extends TimerTask {

        public void run() {
            d.conveyorBelt.set(-1);
            d.feeder.set(Relay.Value.kReverse);
        }
    }

    class stopload extends TimerTask {

        public void run() {
            d.conveyorBelt.set(0);
            d.feeder.set(Relay.Value.kOff);
        }
    }

    class bridge extends TimerTask {

        double speed;

        public bridge(double s) {
            speed = s;
        }

        public void run() {
            d.bridgeArm.set(speed);
        }
    }
}
