/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.Vision;

/**
 *
 * @author saswat
 */
public class Parallelogram {

    public Point upperLeft;
    public Point lowerLeft;
    public Point upperRight;
    public Point lowerRight;
    
    public Point midLeft;
    public Point midRight;
    public Point midTop;
    public Point midBottom;
    
    public Point center;

    public Parallelogram() {
    }
    
    public Parallelogram(Point ul, Point ll, Point ur, Point lr) {
        upperLeft = ul;
        lowerLeft = ll;
        upperRight = ur;
        lowerRight = lr;
    }
    
    public String toString() {
        return "Parallelogram{Upper Left = (" + upperLeft + "), Lower Left = (" + lowerLeft + "), Upper Right = (" + upperRight + "), Lower Right = (" + lowerRight + ")}";
    }
}
