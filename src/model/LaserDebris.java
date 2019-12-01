package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.Serializable;


public class LaserDebris extends SpaceObject implements Serializable {
    public boolean pulse;
    public double laserAccelScalar;
    public boolean calculating;
    public double apogee;
    public double perigee;
    public double eccentricity;
    public double pulseStartTime;

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
	public LaserDebris(int originX, int originY, int xOffset, int yOffset, int xVel, int yVel, int frameWidth, int frameHeight, double mass, double timeStep) {
		this.name = "Earth";
		//this.xLoc0 = xOffset + originX;
		//this.yLoc0 = yOffset + originY;
		this.originX = originX;
        this.originY = originY;
        this.mass = mass;
        this.apogee = 3.578934291741344E7;
        this.perigee = 2.8353341836060237E7;

        this.timeStep = timeStep;
		
		//System.out.println("initial x:" + (xOffset + originX));
		this.xLocation = xOffset/10e4 + originX;
		//System.out.println("initial x:" + this.xLocation);
		this.yLocation = yOffset/10e4 + originY;
		//System.out.println("initial y:" + this.yLocation);
        
        this.pulse = false;
        this.calculating = true;
        this.laserAccelScalar = 0;

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
		this.spaceObjectImage = Image.LASERDEBRIS;
		this.frameCount = 1;
		System.out.println("initial x:" + this.xLocation);
		System.out.println("initial y:" + this.yLocation);
    }

    public double xVel(double normX, double laserAccel, double gravAccelScalar) {
        return this.xVel + normX*(gravAccelScalar - laserAccel)*timeStep;
    }  
    
    public double yVel(double normY, double laserAccel, double gravAccelScalar) {
        return this.yVel + normY*(gravAccelScalar - laserAccel)*timeStep;
    }  
    
    public double laserAccelScalar() {
        System.out.println("pulse: " + this.pulse);
        if (this.pulse == true) {
            return gravAccelScalar;
        }
        else {
            return 0;
        }
    }

    /*public boolean pulse() {

    }*/

   public void findApogeeAndPerigee(double radius, double curApogee, double curPerigee) {
        if (radius > curApogee) {
            this.apogee = radius;
        }
        if (radius < curPerigee) {
            this.perigee = radius;
        }
    }

    public double findEccentricity() {
        return (apogee - perigee)/(apogee + perigee);
    }

    @Override
    public void move(double t) {
        System.out.println("time t:" + t);
        this.radius = radius(this.xOffset, this.yOffset);
        System.out.println("radius:" + radius);
        this.gravAccelScalar = gravAccelScalar(radius);
        //this.pulse = this.checkDrag();
        this.laserAccelScalar = laserAccelScalar();
        this.normX = normX(this.xOffset, radius);
        this.normY = normY(this.yOffset, radius);
        this.xVel = xVel(normX, laserAccelScalar, gravAccelScalar);
        this.yVel = yVel(normY, laserAccelScalar, gravAccelScalar);
        this.kineticEnergy = kineticEnergy();
        System.out.println("kinetic:" + kineticEnergy);
        System.out.println("potential:" + potentialEnergy); 
        this.setX(this.xPos(this.xOffset, this.xVel));
        this.setY(this.yPos(this.yOffset, this.yVel));
        System.out.println("x: " + xOffset);
        System.out.println("y: " + yOffset);
        System.out.println("theta: " + this.theta(xOffset, yOffset));
       /* if (t < 60) {
            this.findApogeeAndPerigee(this.radius, this.apogee, this.perigee);
        }
        else {*/
            this.eccentricity = this.findEccentricity();
            System.out.println("eccentricity: "+ this.eccentricity);
            if ((this.radius >= apogee - 10000)&&(!this.pulse)) {
                this.pulseStartTime = t;
                this.pulse = true;
            }
            else {
                this.pulse = false;
            }
            System.out.println("pulse start time: " + this.pulseStartTime);
       // }
        System.out.println("apogee: " + this.apogee);
        System.out.println("perigee: "+ this.perigee);
    }

}