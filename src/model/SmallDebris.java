package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.Serializable;


public class SmallDebris extends SpaceObject implements Serializable {
    public boolean inDust;
    public double dragAccelScalar;
    public double surfaceArea;
    public double dustDensity;
    public double crossSecArea;
    public double dragCoeff;

	/*private static ArrayList<String> characteristicsCorrect = new ArrayList<String>( Arrays.asList("gray","black eyes","fur","4 feet"));
	private static ArrayList<String> characteristicsIncorrect = new ArrayList<String>(Arrays.asList("gills","1 leg","hard shell","wings"));*/

	/**
	* Constructor sets information about individual animals and screen.
	* @param x initial x-position
	* @param y initial y-position
	* //param d initial direction
	* @param frameWidth screen width
	* @param frameHeight screen height
	* @param xBoundary x-position where main environment ends on screen
	* @param yBoundary y-position where secondary environments collide on screen
    */
    
    /*
    - tungsten dust, force that comes in at a certain radius
        * treat space object as sphere
        * treat dust as fluid
        * assume opposing directions
        * surface area - treat as circle
        * 30km thick ? 
        * decelerates with space object
        * < .01 m diam
        * 5.23599e-7 m^3 volume of space debris
        * ~1.5 g/m^3
    */

    /*
    - Figure out max time step by looking at cons. of energy
    - Figure out realistic value of ro (cause significant deceleration - dependent on max time step)
    */
    
	public SmallDebris(int originX, int originY, int xOffset, int yOffset, int xVel, int yVel, int frameWidth, int frameHeight, double timeStep) {
		this.name = "Earth";
		//this.xLoc0 = xOffset + originX;
		//this.yLoc0 = yOffset + originY;
		this.originX = originX;
        this.originY = originY;
        this.mass = 7.854e-7;
        this.dragAccelScalar = 0; 
        this.timeStep = timeStep;
        
        this.inDust = false;
        this.dustDensity = 500;//19300000; //g cc
        this.crossSecArea = 7.853982e-5;
        this.dragCoeff = 0.2;
		
		//System.out.println("initial x:" + (xOffset + originX));
		this.xLocation = xOffset/10e4 + originX;
		//System.out.println("initial x:" + this.xLocation);
		this.yLocation = yOffset/10e4 + originY;
		//System.out.println("initial y:" + this.yLocation);
        
        this.xOffset = xOffset;
		this.yOffset = yOffset;
		//this.zOffset = zOffset;
		this.xVel = xVel;//this.unifCircXVel();
		this.yVel = yVel;//this.unifCircYVel();
		//this.zVel = zVel;
		//this.xAccel = this.xAccel(0);
		//this.yAccel = this.yAccel(0);
		//this.accelConstant = Math.sqrt(xAccel*xAccel + yAccel*yAccel);
        
		//this.dir = d;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.imgWidth = 16;
		this.imgHeight = 16;
		this.moves = true;
		this.spaceObjectImage = Image.SMALLDEBRIS;
		this.frameCount = 1;
		//System.out.println("initial x:" + this.xLocation);
		//System.out.println("initial y:" s+ this.yLocation);
    }

    public double xVel(double normX, double normY, double gravAccelScalar, double dragAccelScalar) {
        return this.xVel + (-this.xVel*this.xVel*dragAccelScalar + normX*gravAccelScalar)*timeStep;
    }  
    
    public double yVel(double normY, double normX, double gravAccelScalar, double dragAccelScalar) {
        return this.yVel + (-this.yVel*this.yVel*dragAccelScalar + normY*gravAccelScalar)*timeStep;
    }  

    public boolean checkDrag() {
        if ((radius < 7130000)&&(radius > 7100000)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public double dragAccelScalar() {
        if (this.inDust == true) {
            return (1/2*dustDensity*dragCoeff*crossSecArea)/mass;
        }
        else {
            return 0;
        }
    }

    @Override
    public void move(double t) {
        System.out.println("time t:" + t);
        this.radius = radius(this.xOffset, this.yOffset);
        System.out.println("radius:" + radius);
        this.gravAccelScalar = gravAccelScalar(radius);
        this.inDust = this.checkDrag();
        this.dragAccelScalar = dragAccelScalar();
        this.normX = normX(this.xOffset, radius);
        this.normY = normY(this.yOffset, radius);
        this.xVel = xVel(normX, normY, gravAccelScalar, this.dragAccelScalar);
        this.yVel = yVel(normY, normX, gravAccelScalar, this.dragAccelScalar);
        this.kineticEnergy = kineticEnergy();
        double potentialEnergy = potentialEnergy();
        //System.out.println("kinetic:" + kineticEnergy);
        //System.out.println("potential:" + potentialEnergy); 
        double totalEnergy = kineticEnergy + potentialEnergy;
        System.out.println("Total energy: " + totalEnergy + " time: " + t);
        this.setX(this.xPos(this.xOffset, this.xVel));
        this.setY(this.yPos(this.yOffset, this.yVel));
        //System.out.println("x: " + xOffset);
        //System.out.println("y: " + yOffset);
        //System.out.println("theta: " + this.theta(xOffset, yOffset));
    }

}