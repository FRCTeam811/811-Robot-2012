/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.team811.SubSystem;
import edu.wpi.first.team811.Team811Robot;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author Saswat
 */
public class DriveTrain extends SubSystem {
    


    public PIDController turnController;

    public DriveTrain(Team811Robot teamrobot) {
        super(teamrobot);
        turnController = new PIDController(0.05, 0.0001, 4.0, d.gyro, new PIDOutput() {
            public void pidWrite(double output) {
                if (!turnController.onTarget()) {
                    d.rd1.arcadeDrive(0.0, -output);
                }
            }
        }, .02);
        turnController.setInputRange(-360, 360);
        turnController.setTolerance(5);
        turnController.disable();

    }

    public String ID() {
        return "DriveTrain";
    }

    public void logic(Object param) {
        d.rd1.arcadeDrive(d.joy1.getY(GenericHID.Hand.kLeft), d.joy1.getX(GenericHID.Hand.kRight));
    }


    public void move(double degrees) {
        turnController.setSetpoint(d.gyro.pidGet() + degrees);
        turnController.enable();



    }
}
