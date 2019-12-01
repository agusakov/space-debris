package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.Serializable;


public class Satellite extends SpaceObject implements Serializable {

	/*private static ArrayList<String> characteristicsCorrect = new ArrayList<String>( Arrays.asList("gray","black eyes","fur","4 feet"));
	private static ArrayList<String> characteristicsIncorrect = new ArrayList<String>(Arrays.asList("gills","1 leg","hard shell","wings"));*/

	/**
	* Constructor sets information about individual animals and screen.
	* @param x initial x-position
	* @param y initial y-position
	* //param d initial direction
	* @param frameWidth screen width
	* @param frameHeight screen height
	* @param imgWidth width of animal image
	* @param imgHeight height of animal image
	* @param xBoundary x-position where main environment ends on screen
	* @param yBoundary y-position where secondary environments collide on screen
	*/
	public Satellite(int originX, int originY, int xOffset, int yOffset, int xVel, int yVel, int frameWidth, int frameHeight, int imgWidth, int imgHeight, double mass, double timeStep) {
		this.name = "Earth";
		//this.xLoc0 = xOffset + originX;
		//this.yLoc0 = yOffset + originY;
		this.originX = originX;
		this.originY = originY;
		this.mass = mass;
		
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
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.moves = true;
		this.spaceObjectImage = Image.SATELLITE;
		this.frameCount = 1;
		this.timeStep = timeStep;
		System.out.println("initial x:" + this.xLocation);
		System.out.println("initial y:" + this.yLocation);
	}

}