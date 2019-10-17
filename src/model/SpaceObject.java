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
    public double zLocation;
    public double xOffset;
    public double yOffset;
    public double zOffset;

    public ArrayList<Point> forces = new ArrayList<>();
    
    public double gravConstant = 6.674e-11;
    public double earthMass = 5.972e24;
    public double mass;

    public double xVel0;
    public double yVel0;
    public double xLoc0;
    public double yLoc0;

    public double radius;
    public double gravAccelScalar;

    public double xVel;
    public double yVel;
    public double zVel;
    //public double xAccel = 2;
    //public double yAccel = 2;
    //public double accelConstant = 1;

    public double potentialEnergy;
    public double kineticEnergy;

    public double normX;
    public double normY;
    public double normZ;

    public int originX;
    public int originY;
    public int originZ = 0;
    
    public Image spaceObjectImage;
    public int frameCount;

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

   /* public double normZ(double z, double radius){
        if (radius != 0.0) {
            return z/radius;
        }
        else {
            return 0;
        }
    }*/

    public double xVel(double normX, double gravAccelScalar){
        return this.xVel + normX*gravAccelScalar;
    }

    public double yVel(double normY, double gravAccelScalar){
        return this.yVel + normY*gravAccelScalar;
    }

  /*  public double zVel(double normZ, double gravAccelScalar){
        return this.zVel + normZ*gravAccelScalar;
    }*/

    public double xPos(double x, double xVel) {
        return x + xVel;
    }

    public double yPos(double y, double yVel) {
        return y + yVel;
    }

   /* public double zPos(double z, double zVel) {
        return z + zVel;
    }*/

    /*public double angular(double x, double y){
        return Math.atan2(x, y);
    }*/

    public double radius(double x, double y/*, double z*/) {
        return Math.sqrt(x*x + y*y/* + z*z*/);
    }

    /*public double theta(double t) {
        return theta + angVel*t + 1/2*angAccel*t*t;
    }

    public double angVel(double t) {
        return angVel + theta*angAccel;
    }*/
    
    public double gravAccelScalar(double radius) {
        if (radius != 0.0) {
            return -gravConstant*earthMass*mass/(radius*radius);
        }
        else {
            return 0;
        }
    }

    public double unifCircXVel() {
        if (radius != 0.0) {
            return Math.sqrt(gravAccelScalar/radius)*(-normY);
        }
        else {
            return 0;
        }
    }

    public double unifCircYVel() {
        if (radius != 0.0) {
            return Math.sqrt(gravAccelScalar/radius)*(normX);
        }
        else {
            return 0;
        }
    }

    public double potentialEnergy() {
        if (radius != 0.0) {
            return gravConstant*(mass)*(radius);
        }
        else {
            return 0;
        }
    }

    public double kineticEnergy() {
        return mass*(xVel*xVel + yVel*yVel/* + zVel*zVel*/)/2;
    }

    public void move(double t) {
        System.out.println("time t:" + t);
        //this.xAccel = this.xAccel(t);
        //this.yAccel = this.yAccel(t);
        //this.angAccel = this.angAccel(t);
        this.radius = radius(this.xOffset, this.yOffset);
        System.out.println("radius:" + radius);
        this.gravAccelScalar = gravAccelScalar(radius);
        System.out.println("gravAccelScalar:" + gravAccelScalar);
        this.normX = normX(this.xOffset, radius);
        System.out.println("normX:" + normX);
        this.normY = normY(this.yOffset, radius);
        System.out.println("normY:" + normY);
        //this.normZ = normZ(this.zOffset, radius);
        //System.out.println("normZ:" + normZ);
        this.xVel = xVel(normX, gravAccelScalar);
        System.out.println("xVel:" + xVel);
        this.yVel = yVel(normY, gravAccelScalar);
        System.out.println("yVel:" + yVel);
        //this.yVel = zVel(normZ, gravAccelScalar);
        //System.out.println("zVel:" + zVel);
        this.kineticEnergy = kineticEnergy();
        double potentialEnergy = potentialEnergy();
        System.out.println("kinetic:" + kineticEnergy);
        System.out.println("potential:" + potentialEnergy); 
        double totalEnergy = kineticEnergy + potentialEnergy;
        System.out.println("Total energy:" + totalEnergy);
        this.setX(this.xPos(this.xOffset, this.xVel));
        this.setY(this.yPos(this.yOffset, this.yVel));
        //this.setZ(this.zPos(this.zOffset, this.zVel));
        System.out.println("xLocation: " + xLocation + " yLocation: " + yLocation);
        System.out.println("xOffset: " + xOffset + " yOffset: " + yOffset);
    }
	
	public double getX() {
		return this.xLocation;
	}
	
	public double getY() {
		return this.yLocation;
    }

    public double getZ() {
		return this.zLocation;
    }

    public void setX(double x) {
        this.xLocation = (x/10e10 + originX);
        System.out.println("originX:" + this.originX);
        System.out.println("setting xLocation:" + this.xLocation);
        this.xOffset = x;
    }

    public void setY(double y) {
        this.yLocation = (originY + y/10e10);
        this.yOffset = y;
    }

    public void setZ(double z) {
        this.zLocation = (originZ + z);
        this.zOffset = z;
    }

    public int getFrameCount() {
        return this.frameCount;
    }

    public Image getImage() {
		return this.spaceObjectImage;
	}
}
	