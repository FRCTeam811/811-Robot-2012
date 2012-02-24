/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811.Vision;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Saswat
 */
public class Vision {

    AxisCamera ac;
    NetworkTable cameraTable = null;
    public double xOffset = 0;
    public double height = 0;
    public double width = 0;
    int count = 0;
    int dashID = 0;
    public boolean nData = false;//was data updated tracker var

    public Vision() {
        ac = AxisCamera.getInstance();
    }

    public void update() {
        
        double txOffset = SmartDashboard.getDouble("xOffset", 0);
        double theight = SmartDashboard.getDouble("height", 0);
        double twidth = SmartDashboard.getDouble("width", 0);

        nData = true;
        
        System.out.println(height);
        
        xOffset = txOffset;
        height = theight;
        width = twidth;
    }
}