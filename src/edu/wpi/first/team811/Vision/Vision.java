/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package edu.wpi.first.team811.Vision;

import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 *
 * @author Saswat
 */
public class Vision {
    AxisCamera ac;
    public Vision() {
        ac = AxisCamera.getInstance();
        
    }
    
    private void processImage() {
        try {
            ac.getImage();
        } catch (Exception ex) {
        }
    }
    
}
