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
	public Satellite(int originX, int originY, int x, int y, int xVel, int yVel, double xAccel, double yAccel, int frameWidth, int frameHeight, int imgWidth, int imgHeight) {
		this.name = "Earth";
		this.xLoc0 = x + originX;
		this.yLoc0 = - y + originY;
		
		this.xLocation = x + originX;
        this.yLocation = - y + originY;
        
        this.xOffset = x;
        this.yOffset = - y;
		this.xVel0 = xVel;
		this.yVel0 = yVel;
		this.xAccel = xAccel;
		this.yAccel = yAccel;
		//this.accelConstant = Math.sqrt(xAccel*xAccel + yAccel*yAccel);
        
		//this.dir = d;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.moves = true;
		this.spaceObjectImage = Image.SATELLITE;
		this.frameCount = 1;
	}

}