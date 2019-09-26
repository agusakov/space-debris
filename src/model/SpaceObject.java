package model;
/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Iterator;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Comparator;
import java.io.Serializable;

public class SpaceObject implements Serializable {
    public final static double PI = 3.14159;
    public String name;
    public int frameWidth;
	public int frameHeight;
	public int imgWidth;
	public int imgHeight;
    public boolean moves;
    
    public double xLocation;
    public double yLocation;
    public double xOffset;
    public double yOffset;

    public ArrayList<Point> forces = new ArrayList<>();
    
    public double mass;

    public double xVel0;
    public double yVel0;
    public double xLoc0;
    public double yLoc0;

    public double xVel;
    public double yVel;
    public double xAccel;
    public double yAccel;
    public double accelConstant = 1;

    public int originX;
    public int originY;
    
    public Image spaceObjectImage;
    public int frameCount;
    
    public double xPos(double t){
        return 1/2*xAccel*t*t + xVel*t + xOffset;
    }

    public double yPos(double t){
        return 1/2*yAccel*t*t + yVel*t + yOffset;
    }

    public double xVel(double t) {
        return xAccel*t + xVel;
    }

    public double yVel(double t) {
        return yAccel*t + yVel;
    }

    public double accel(double t) {
        return accelConstant/(xOffset*xOffset + yOffset*yOffset);
    }

    public double xAccel(double t) {
        return -0.001*xOffset/(xOffset*xOffset + yOffset*yOffset);
    }

    public double yAccel(double t) {
        return -0.001*yOffset/(xOffset*xOffset + yOffset*yOffset);
    }

    public void move(double t) {
        System.out.println("time t:" + t);
        this.xAccel = this.xAccel(t);
        this.yAccel = this.yAccel(t);
        System.out.println("xAccel:" + this.xAccel);
        this.xVel = this.xVel(t);
        this.yVel = this.yVel(t);
        System.out.println("xVel:" + this.xVel);
        this.setX((int) this.xPos(t));
        this.setY((int) this.yPos(t));
        System.out.println("xLocation: " + xLocation + " yLocation: " + yLocation);
        System.out.println("xOffset: " + xOffset + " yOffset: " + yOffset);
    }
	
	public double getX() {
		return this.xLocation;
	}
	
	public double getY() {
		return this.yLocation;
    }

    public void setX(int x) {
        this.xLocation = x + originX;
        System.out.println("originX:" + this.originX);
        System.out.println("setting xLocation:" + this.xLocation);
        this.xOffset = x;
    }

    public void setY(int y) {
        this.yLocation = originY + y;
        this.yOffset = y;
    }

    public int getFrameCount() {
        return this.frameCount;
    }

    public Image getImage() {
		return this.spaceObjectImage;
	}
}
	