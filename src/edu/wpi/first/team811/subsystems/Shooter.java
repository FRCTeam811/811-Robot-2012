/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.Configuration;
import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.*;
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
    PIDController wheelPID;
    Timer timer = new Timer();
    DriverStation ds = DriverStation.getInstance();

    public Shooter(Team811Robot teamrobot) {
        super(teamrobot);
        SmartDashboard.putString("Ball State", "Old");

        d.shooterEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        d.shooterEncoder.setDistancePerPulse(.004);

        wheelPID = new PIDController(Configuration.pidP, Configuration.pidI, Configuration.pidD, new PIDSource() {

            public double pidGet() {
                return d.shooterEncoder.getRate();
            }
        }, new PIDOutput() {

            public void pidWrite(double output) {
                d.shooter.set(output);
                System.out.println(d.shooterEncoder.getRate());
                System.out.println(output);
            }
        }, Configuration.pidPeriod);//ShooterPID(d.shooterEncoder, d.shooter);

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
                vision();
                timer.schedule(new load(), 2500);
                autoStartShooter();
                //timer.schedule(new load(), 2000);
                while (d.joy2.getRawAxis(c.shoot) == 1) {
                    ds.waitForData();
                    tr.getWatchdog().feed();
                }
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
                tr.getWatchdog().feed();
            }

            //Shoot 2 balls
            //startShooter(.8);
            wheelPID.enable();
            System.out.println("Starting PID");
            wheelPID.setSetpoint(getSpeed());
            while (!wheelPID.onTarget() && edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - initTime < 6) {
                ds.waitForData();
                tr.getWatchdog().feed();
            }
            System.out.println("setpoint: " + wheelPID.getSetpoint());
            double get = wheelPID.get();
            wheelPID.disable();
            d.shooter.set(get);


            new load().run();
            //timer.schedule(new load(), 2000);
            timer.schedule(new stopload(), 3000);
            timer.schedule(new shoot(0), 3000);

            //Move to the bridge
            timer.schedule(new moveDrive(.6, 0), 3000);
            timer.schedule(new moveDrive(0, 0), 3000+c.autoDTMoveTime);
            timer.schedule(new bridge(-1), 3000+c.autoDTMoveTime);
            timer.schedule(new bridge(0), 4500+c.autoDTMoveTime);
        }

    }

    public void logic2(Object param) {
        double initTime = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
        while (vision() && edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - initTime < 2) {// for 2 seconds adjust shooter
            tr.getWatchdog().feed();
        }

        //Shoot 2 balls
        //startShooter(.8);
        wheelPID.enable();
        System.out.println("Starting PID");
        wheelPID.setSetpoint(getSpeed());
        while (!wheelPID.onTarget() && edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - initTime < 6) {
            ds.waitForData();
            tr.getWatchdog().feed();
        }
        System.out.println("setpoint: " + wheelPID.getSetpoint());
        double get = wheelPID.get();
        wheelPID.disable();
        d.shooter.set(get);


        new load().run();
        //timer.schedule(new load(), 2000);
        timer.schedule(new stopload(), 3000);
        timer.schedule(new shoot(0), 3000);
    }

    public void exclusiveRun(Object param) {//Set shooter speed with PID
        wheelPID.enable();
        System.out.println("Starting PID");
        wheelPID.setSetpoint(((Double) param).doubleValue());
        while (!wheelPID.onTarget() && d.joy2.getRawAxis(c.shoot) == 1) {
            ds.waitForData();
            tr.getWatchdog().feed();
        }
        System.out.println("setpoint: " + wheelPID.getSetpoint());
        double get = wheelPID.get();
        wheelPID.disable();
        d.shooter.set(get);
    }

    public void pause() {
        d.shooter.set(0);
        d.turret.set(0);
    }

    //<editor-fold defaultstate="collapsed" desc="Old Shooter Speed Set">
    /*
     * public double getSpeed() {//Motor speed based double mspeed = 1; double
     * speeds[];
     *
     * switch (ballState) { case 0: speeds = c.speedsOld; break; case 1: speeds
     * = c.speedsMed; break; case 2: speeds = c.speedsNew; break; default:
     * speeds = c.speedsMed; }
     *
     * for (int i = 0; i < speeds.length; i++) { if (c.heights1[i] <= rectH &&
     * c.heights2[i] >= rectH) { mspeed = speeds[i]; i = speeds.length; } }
     * return mspeed;
     * }
     */
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
            if (c.heights1[i] >= rectH && c.heights2[i] <= rectH) {
                mspeed = speeds[i];
                i = speeds.length;
            }
        }
        SmartDashboard.putDouble("Shooter At RPS:", mspeed);
        return mspeed * -1;
    }

    public void autoStartShooter() {//Starts the shooter based on rectangle input
        System.out.println("Speed: " + getSpeed() + ", Height: " + rectH);
        tr.runExclusive(this, new Double(getSpeed()));
    }

    public void startShooter(double sSpeed) {
        sSpeed *= -1;
        d.shooter.set(sSpeed);
    }

    public boolean vision() {
        xOff = 0;

        d.vision.update();

        rectH = d.vision.height;

        if (d.vision.nData) {
            xOff = d.vision.xOffset;
        }

        if (xOff < c.visionErrorMargin && xOff > -c.visionErrorMargin) {
            xOff = 0;// Allow margin of error
        }
        if (xOff == 0) {
            d.turret.set(0);
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
        System.out.println("xOff: " + xOffset);
        int neg = 1;
        if (xOffset < 0) {
            neg = -1;
        }

        if (turretStopPlanned) {
            return;
        }
        //d.turret.set(Math.max(Math.min(.3 * (xOffset / 100), 1), -1));
        if (d.turretLimit.get()) {
            d.turret.set(0);
        } else if (Math.abs(xOffset) < 20) {
            d.turret.set(.7 * (xOffset / 100));
        } else if (Math.abs(xOffset) < 50 && Math.abs(xOffset) > 20) {
            d.turret.set(.5 * (xOffset / 100));
        } else if (Math.abs(xOffset) > 50) {
            d.turret.set(.3 * (xOffset / 100));
        } else {
            d.turret.set(.1 * (xOffset / 100));
        }

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
