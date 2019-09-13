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

public class SpaceObject implements Serializable{
    public String name;
    public int frameWidth;
	public int frameHeight;
	public int imgWidth;
	public int imgHeight;
    public boolean moves;
    
    public int xLocation;
    public int yLocation;
    public int xOffset;
    public int yOffset;
   
    public double xVel0;
    public double yVel0;
    public double xLoc0;
    public double yLoc0;

    public double xVel = 0;
    public double yVel = 0;
    public double xAccel;
    public double yAccel;
    //public double accelConstant;

    public int originX;
    public int originY;
    
    public Image spaceObjectImage;
    public int frameCount;
    
    public double xPos(double t){
        return -1/2*Math.cos(t)*t*t - Math.sin(t)*t + xLoc0;
    }

    public double yPos(double t){
        return -1/2*Math.sin(t)*t*t + Math.cos(t)*t + yLoc0;
    }

    public double xVel(double t) {
        return Math.sin(t)*t + xVel0;
    }

    public double yVel(double t) {
        return -Math.cos(t)*t + yVel0;
    }

    /*public double xAccel(double t) {
        return accelConstant * yVel/(Math.sqrt(xVel*xVel + yVel + yVel));
    }

    public double yAccel(double t) {
        return accelConstant * xVel/(Math.sqrt(xVel*xVel + yVel + yVel));
    }*/

    public void move(double t) {
        xVel = this.xVel(t);
        yVel = this.yVel(t);
        /*xAccel = this.xAccel(t);
        yAccel = this.yAccel(t);*/
        this.setX((int) this.xPos(t) + originX);
        this.setY((int) this.yPos(t) + originY);
        System.out.println("x: " + xLocation + " y: " + yLocation);
    }
    
    public double xTheta(double t) {
        return Math.cos(t);
    }

    public double yTheta(double t) {
        return Math.sin(t);
    }
	
	public int getX() {
		return this.xLocation + this.originX;
	}
	
	public int getY() {
		return this.yLocation + this.originY;
    }

    public void setX(int x) {
        this.xLocation = x + originX;
    }

    public void setY(int y) {
        this.yLocation = y + originY;
    }

    public int getFrameCount() {
        return this.frameCount;
    }

    public Image getImage() {
		return this.spaceObjectImage;
	}
}
	