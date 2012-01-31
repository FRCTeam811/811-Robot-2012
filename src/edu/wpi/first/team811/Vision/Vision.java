/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811.Vision;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.networktables.NetworkListener;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Saswat
 */
public class Vision {

    AxisCamera ac;
    int screenX = 240;
    int screenY = 190;

    public Vision() {
        ac = AxisCamera.getInstance();

    }
    
    public int getXOffset() throws NetworkTableKeyNotDefined {
        Point center = getParallelogram().center;
        return center.x - screenX;
    }

    private Parallelogram getParallelogram() throws NetworkTableKeyNotDefined {
        return (Parallelogram)SmartDashboard.getData("camera").getTable().getValue("rect");
        
    }
}