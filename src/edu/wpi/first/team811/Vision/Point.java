/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.Vision;

/**
 *
 * @author saswat
 */
public class Point {
    public int x;
    public int y;
    
    public Point() {
        this(0, 0);
    }
    public Point(Point p) {
        this(p.x, p.y);
    }
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public Point getLocation() {
        return new Point(x, y);
    }
    public void setLocation(Point p) {
        setLocation(p.x, p.y);
    }
    public void setLocation(int x, int y) {
        move(x, y);
    }
    public void setLocation(double x, double y) {
        this.x = (int) Math.floor(x+0.5);
        this.y = (int) Math.floor(y+0.5);
    }
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point pt = (Point)obj;
            return (x == pt.x) && (y == pt.y);
        }
        return super.equals(obj);
    }
    public String toString() {
        return getClass().getName() + "[x=" + x + ",y=" + y + "]";
    }
}
