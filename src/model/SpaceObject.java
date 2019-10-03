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

    public double radius;
    public double gravForceScalar;

    public double xVel;
    public double yVel;
    //public double xAccel = 2;
    //public double yAccel = 2;
    //public double accelConstant = 1;

    public double potentialEnergy;
    public double kineticEnergy;

    public double normX;
    public double normY;

    public int originX;
    public int originY;
    
    public Image spaceObjectImage;
    public int frameCount;

    /*public double xComponent(double angle, double mag) {
        return mag*Math.cos(angle);
    }

    public double yComponent(double angle, double mag) {
        return mag*Math.sin(angle);
    }*/

    public double normX(double x, double radius) {
        if (radius != 0.0) {
            return x/radius;
        }
        else {
            return 0;
        }
    }

    public double normY(double y, double radius){
        if (radius != 0.0) {
            return y/radius;
        }
        else {
            return 0;
        }
    }

    public double xVel(double normX, double gravForceScalar){
        return this.xVel + normX*gravForceScalar;
    }

    public double yVel(double normY, double gravForceScalar){
        return this.yVel + normY*gravForceScalar;
    }

    public double xPos(double x, double xVel) {
        return x + xVel;
    }

    public double yPos(double y, double yVel) {
        return y + yVel;
    }

    /*public double angular(double x, double y){
        return Math.atan2(x, y);
    }*/

    public double radius(double x, double y) {
        return Math.sqrt(x*x + y*y);
    }

    /*public double theta(double t) {
        return theta + angVel*t + 1/2*angAccel*t*t;
    }

    public double angVel(double t) {
        return angVel + theta*angAccel;
    }*/
    
    public double gravForceScalar(double radius) {
        if (radius != 0.0) {
            return -10000/(radius*radius);
        }
        else {
            return 0;
        }
    }

    public double unifCircXVel() {
        if (radius != 0.0) {
            return Math.sqrt(gravForceScalar/radius)*(-normY);
        }
        else {
            return 0;
        }
    }

    public double unifCircYVel() {
        if (radius != 0.0) {
            return Math.sqrt(gravForceScalar/radius)*(normX);
        }
        else {
            return 0;
        }
    }

    public double potentialEnergy() {
        if (radius != 0.0) {
            return 1000/(radius);
        }
        else {
            return 0;
        }
    }

    public double kineticEnergy() {
        return (xVel*xVel + yVel*yVel)*1/2;
    }

    public void move(double t) {
        System.out.println("time t:" + t);
        //this.xAccel = this.xAccel(t);
        //this.yAccel = this.yAccel(t);
        //this.angAccel = this.angAccel(t);
        this.radius = radius(this.xOffset, this.yOffset);
        System.out.println("radius:" + radius);
        this.gravForceScalar = gravForceScalar(radius);
        System.out.println("gravForceScalar:" + gravForceScalar);
        this.normX = normX(this.xOffset, radius);
        System.out.println("normX:" + normX);
        this.normY = normY(this.yOffset, radius);
        System.out.println("normY:" + normY);
        this.xVel = xVel(normX, gravForceScalar);
        System.out.println("xVel:" + xVel);
        this.yVel = yVel(normY, gravForceScalar);
        System.out.println("yVel:" + yVel);
        this.setX(this.xPos(this.xOffset, this.xVel));
        this.setY(this.yPos(this.yOffset, this.yVel));
        System.out.println("xLocation: " + xLocation + " yLocation: " + yLocation);
        System.out.println("xOffset: " + xOffset + " yOffset: " + yOffset);
        this.kineticEnergy = kineticEnergy();
        double potentialEnergy = potentialEnergy();
        double totalEnergy = kineticEnergy - potentialEnergy;
        System.out.println("Total energy:" + totalEnergy);
    }
	
	public double getX() {
		return this.xLocation;
	}
	
	public double getY() {
		return this.yLocation;
    }

    public void setX(double x) {
        this.xLocation = x + originX;
        System.out.println("originX:" + this.originX);
        System.out.println("setting xLocation:" + this.xLocation);
        this.xOffset = x;
    }

    public void setY(double y) {
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
	