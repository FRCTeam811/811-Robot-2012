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
    boolean turretTimerHit = false;
    boolean ballStateIncrementHit = false;
    double rectH = 0;
    double xOff = 0;
    double speed = 0.0;//for shooter
    int ballState = 0;//0-old, 1-medium, 2-new
    PIDController wheelPID = null;
    Timer timer = null;
    DriverStation ds = DriverStation.getInstance();

    public Shooter(Team811Robot teamrobot) {
        super(teamrobot);
        reset();
    }

    public final void reset() {
        SmartDashboard.putString("Ball State", "Old");
        SmartDashboard.putBoolean("PID on target", false);
        SmartDashboard.putBoolean("Manual Mode", false);
        SmartDashboard.putDouble("Shooter At RPS:", 0.0);
        SmartDashboard.putBoolean("Turret Limit Switch:", false);
        
        manualOn = false;
        slowTurret = false;
        turretTimerHit = false;
        ballStateIncrementHit = false;
        rectH = 0;
        xOff = 0;
        speed = 0.0;
        ballState = 0;
        
        d.shooterEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        d.shooterEncoder.setDistancePerPulse(.004);

        if(wheelPID != null) wheelPID.free();
        
        wheelPID = new PIDController(Configuration.pidP, Configuration.pidI, Configuration.pidD, new PIDSource() {

            public double pidGet() {
                return d.shooterEncoder.getRate();
            }
        }, new PIDOutput() {

            public void pidWrite(double output) {
                d.shooter.set(output);
                debug(d.shooterEncoder.getRate()+"");
                debug(output+"");
            }
        }, Configuration.pidPeriod);
        
        wheelPID.setOutputRange(-1, 0);
        wheelPID.setInputRange(-80, 0);
        wheelPID.setTolerance(1.0);
        
    }

    public void enabled() {
        reset();
    }

    public void disabled() {
        wheelPID.free();
        wheelPID = null;
    }
    
    public String ID() {
        return "Shooter";
    }

    public void logic(Object param) {

        if (d.joy2.getRawButton(c.autoMode)) {
            manualOn = false;
            SmartDashboard.putBoolean("Manual Mode", manualOn);
        } else if (d.joy2.getRawButton(c.maunalMode)) {
            manualOn = true;
            SmartDashboard.putBoolean("Manual Mode", manualOn);
        }
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

                vision();

                autoStartShooter();

//                while (d.joy2.getRawAxis(c.shoot) == 1) {
//                    ds.waitForData();
//                    tr.getWatchdog().feed();
//                }
                new stopload().run();
                new shoot(0).run();
            }
        }
        if (!manualOn) {
            checkBallState();
        }

    }

    public void logic1(Object param) {//autonomous
        if (param == "Start") {
            reset();
            timer.cancel();
            timer = new Timer();

            double initTime = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
            while (vision() && edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - initTime < 2) {// for 2 seconds adjust shooter
                tr.getWatchdog().feed();
            }

            //Shoot 2 balls
            wheelPID.enable();
            debug("Starting PID");
            wheelPID.setSetpoint(getSpeed());
            while (edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - initTime < 6) {
                ds.waitForData();
                tr.getWatchdog().feed();
            }
            debug("setpoint: " + wheelPID.getSetpoint());
            double get = wheelPID.get();
            wheelPID.disable();
            d.shooter.set(get);

            new load().run();
            timer.schedule(new stopload(), 3000);
            timer.schedule(new shoot(0), 3000);

            long timeOff = 3000;

            if (SmartDashboard.getInt(c.SDautoMode, 0) == 0) {
                //Move to the bridge
                timer.schedule(new moveDrive(.6, 0), 3000);
                timer.schedule(new moveDrive(0, 0), 3000 + c.autoDTMoveTime);
                timer.schedule(new bridge(-1), 3000 + c.autoDTMoveTime);
                timer.schedule(new bridge(0), 4500 + c.autoDTMoveTime);
                timeOff = 4500 + c.autoDTMoveTime;
            }

            timer.schedule(new TimerTask() {//Free all the resources since autonomous is(should be) used only once

                public void run() {
                    wheelPID.free();
                    wheelPID = null;
                    timer.cancel();
                }
            }, timeOff);
        }
    }

//    public void logic2(Object param) {//autonomous 2
//        timer = new Timer();
//
//        double initTime = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
//        while (vision() && edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - initTime < 2) {// for 2 seconds adjust shooter
//            tr.getWatchdog().feed();
//        }
//
//        //Shoot 2 balls
//        wheelPID.enable();
//        debug("Starting PID");
//        wheelPID.setSetpoint(getSpeed());
//        while (edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - initTime < 6) {
//            ds.waitForData();
//            tr.getWatchdog().feed();
//        }
//        debug("setpoint: " + wheelPID.getSetpoint());
//        double get = wheelPID.get();
//        wheelPID.disable();
//        d.shooter.set(get);
//
//
//        new load().run();
//        timer.schedule(new stopload(), 3000);
//        timer.schedule(new shoot(0), 3000);
//
//        timer.schedule(new TimerTask() {//Free all the resources since autonomous is used only once
//
//            public void run() {
//                wheelPID.free();
//                timer.cancel();
//            }
//        }, 3000);
//    }
    public void exclusiveRun(Object param) {//Set shooter speed with PID
        wheelPID.enable();
        System.out.println("Starting PID");
        wheelPID.setSetpoint(((Double) param).doubleValue());
        boolean onTargetLastState = false;

        while (d.joy2.getRawAxis(c.shoot) == 1) {
            ds.waitForData();
            tr.getWatchdog().feed();
            if (d.joy2.getRawAxis(c.feeder) > .5) {
                d.feeder.set(Relay.Value.kReverse);
                d.conveyorBelt.set(-1);
            } else {
                d.feeder.set(Relay.Value.kOff);
                d.conveyorBelt.set(0);
            }
            if (wheelPID.onTarget() != onTargetLastState) {
                onTargetLastState = !onTargetLastState;
                SmartDashboard.putBoolean("PID on target", onTargetLastState);
            }
        }

        debug("setpoint: " + wheelPID.getSetpoint());
        wheelPID.disable();
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
     * return mspeed; }
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
        debug("Speed: " + getSpeed() + ", Height: " + rectH);
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

//        if (d.joy2.getRawButton(c.shooterPower4)) {
//            speed = 1.0;
//        } else if (d.joy2.getRawButton(c.shooterPower3)) {
//            speed = 0.9;
//        } else if (d.joy2.getRawButton(c.shooterPower2)) {
//            speed = 0.7;
//        } else if (d.joy2.getRawButton(c.shooterPower1)) {
//            speed = 0.5;
//        } else
        if (d.joy2.getRawButton(10)) {
            speed = 0;
        } else if (d.joy2.getRawButton(c.shooterPower4)) {
            speed = Math.abs(d.joy2.getRawAxis(3) - 1);//Left trigger should be pressed all the way during this
        }
        debug2dashboard("running at " + speed);

        startShooter(speed);
    }

    public void checkBallState() {
        if (d.joy2.getRawButton(c.shooterPower1)) {
            if (!ballStateIncrementHit) {
                ballStateIncrementHit = true;
                switch (ballState) {
                    case 0:
                        ballState = 1;
                        SmartDashboard.putString("Ball State", "Medium");
                        break;
                    case 1:
                        ballState = 2;
                        SmartDashboard.putString("Ball State", "New");
                        break;
                    case 2:
                        ballState = 0;
                        SmartDashboard.putString("Ball State", "Old");
                        break;
                    default:
                        SmartDashboard.putString("Ball State", "Error");
                }
            }
        } else {
            ballStateIncrementHit = false;
        }
//        if (d.joy2.getRawButton(c.shooterPower1)) {
//            ballState = 0;
//            SmartDashboard.putString("Ball State", "Old");
//        } else if (d.joy2.getRawButton(c.shooterPower2)) {
//            SmartDashboard.putString("Ball State", "Medium");
//            ballState = 1;
//        } else if (d.joy2.getRawButton(c.shooterPower3)) {
//            SmartDashboard.putString("Ball State", "New");
//            ballState = 2;
//        }
    }

    public void centerTurret(double xOffset) {
        debug("xOff: " + xOffset);

        if (d.turretLimit.get()) {
            d.turret.set(0);
        } else if (Math.abs(xOffset) < 20) {
            d.turret.set(.7 * (xOffset / 100));
        } else if (Math.abs(xOffset) <= 50 && Math.abs(xOffset) >= 20) {
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
