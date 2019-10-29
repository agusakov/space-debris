package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.Serializable;


public class TungstenDust extends SpaceObject implements Serializable {

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
	public TungstenDust(int originX, int originY, int xOffset, int yOffset, int frameWidth, int frameHeight, int imgWidth, int imgHeight) {
		this.name = "TungstenDust";
		//this.xLocation = x + originX;
		//this.yLocation = y + originY;
		this.xLocation = originX + xOffset;
		this.yLocation = originY + yOffset;
		this.xVel = 0;
		this.yVel = 0;
		//this.dir = d;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.moves = false;
		this.spaceObjectImage = Image.TUNGSTENDUST;
		this.frameCount = 2;
	}

}