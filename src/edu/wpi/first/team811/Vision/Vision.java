/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811.Vision;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
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
        //if (cameraTable == null) {
            //cameraTable = SmartDashboard;
        //}

        //cameraTable.beginTransaction();
        //int tcount = SmartDashboard.getInt("count", 0);
        double txOffset = SmartDashboard.getDouble("xOffset", 0);
        double theight = SmartDashboard.getDouble("height", 0);
        double twidth = SmartDashboard.getDouble("width", 0);
        //int tdashID = SmartDashboard.getInt("dashID", dashID);
        //cameraTable.endTransaction();

        nData = true;//TODO remove and re-enable the safe delievery check
        
        /*if (tdashID != dashID) {
            count = 0;
            dashID = tdashID;
        }

        if (tcount > count) {
            count = tcount;
            nData = true;
        } else {
            nData = false;
            return;
        }*/
        System.out.println(txOffset);
        
        xOffset = txOffset;
        height = theight;
        width = twidth;
    }
    //private Parallelogram getParallelogram() throws NetworkTableKeyNotDefined {
    //return (Parallelogram)SmartDashboard.getData("camera").getTable();
    //}
}