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

 /*
- laser broom, force that comes in at a certain time
    * accelerates & exaggerates eccentricity
    * probably gonna need more than one hit
- harpoon, later
*/

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Iterator;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Comparator;
import java.io.Serializable;
import java.lang.Math;

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
    public double zOffset; //don't even worry about it

    public ArrayList<Point> forces = new ArrayList<>();
    
    public double gravConstant = 6.674e-11; //m^3 kg^-1 s^-2
    public double earthMass = 5.972e24; //kg
    public double mass; //kg
    
    public double xVel0;
    public double yVel0;
    public double xLoc0;
    public double yLoc0;

    public double radius;
    public double gravAccelScalar;

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

    public double xVel(double normX, double gravAccelScalar){
        return this.xVel + normX*gravAccelScalar*100;
    }

    public double yVel(double normY, double gravAccelScalar){
        return this.yVel + normY*gravAccelScalar*100;
    }

    public double xPos(double x, double xVel) {
        return x + xVel*100;
    }

    public double yPos(double y, double yVel) {
        return y + yVel*100;
    }

    public double radius(double x, double y/*, double z*/) {
        return Math.sqrt(x*x + y*y/* + z*z*/);
    }
    
    public double gravAccelScalar(double radius) {
        if (radius != 0.0) {
            return -gravConstant*earthMass/(radius*radius); //see if mass needs to be here
        }
        else {
            return 0;
        }
    }

    public double theta(double x, double y) {
        if (x != 0) {
            return Math.atan2(x, y);
        }
        else {
            if (y < 0) {
                return PI/2;
            }
            else {
                return -PI/2;
            }
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
            return gravConstant*(mass)*earthMass/(radius);
        }
        else {
            return 0;
        }
    }

    public double kineticEnergy() {
        return mass*(xVel*xVel + yVel*yVel)/2;
    }

    public void move(double t) {
        System.out.println("time t:" + t);
        //this.xAccel = this.xAccel(t);
        //this.yAccel = this.yAccel(t);
        //this.angAccel = this.angAccel(t);
        this.radius = radius(this.xOffset, this.yOffset);
        System.out.println("radius:" + radius);
        this.gravAccelScalar = gravAccelScalar(radius);
        //System.out.println("gravAccelScalar:" + gravAccelScalar);
        this.normX = normX(this.xOffset, radius);
        //System.out.println("normX:" + normX);
        this.normY = normY(this.yOffset, radius);
        //System.out.println("normY:" + normY);
        //this.normZ = normZ(this.zOffset, radius);
        //System.out.println("normZ:" + normZ);
        this.xVel = xVel(normX, gravAccelScalar);
        //System.out.println("xVel:" + xVel);
        this.yVel = yVel(normY, gravAccelScalar);
        //System.out.println("yVel:" + yVel);
        //this.yVel = zVel(normZ, gravAccelScalar);
        //System.out.println("zVel:" + zVel);
        this.kineticEnergy = kineticEnergy();
        //double potentialEnergy = potentialEnergy();
        System.out.println("kinetic:" + kineticEnergy);
        System.out.println("potential:" + potentialEnergy); 
        //double totalEnergy = kineticEnergy + potentialEnergy;
        //System.out.println("Total energy:" + totalEnergy);
        this.setX(this.xPos(this.xOffset, this.xVel));
        this.setY(this.yPos(this.yOffset, this.yVel));
        //this.setZ(this.zPos(this.zOffset, this.zVel));
        System.out.println("x: " + xOffset);
        System.out.println("y: " + yOffset);
        System.out.println("theta: " + this.theta(xOffset, yOffset));
    }
	
	public double getX() {
		return this.xLocation;
	}
	
	public double getY() {
		return this.yLocation;
    }

    public void setX(double x) {
        this.xLocation = (x/10e4 + originX);
        System.out.println("originX:" + this.originX);
        System.out.println("setting xLocation:" + this.xLocation);
        this.xOffset = x;
    }

    public void setY(double y) {
        this.yLocation = (originY + y/10e4);
        this.yOffset = y;
    }

    public int getFrameCount() {
        return this.frameCount;
    }

    public Image getImage() {
		return this.spaceObjectImage;
	}
}
	