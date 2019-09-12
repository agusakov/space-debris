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
   
    public float xVel;
    public float yVel;
    public float xAccel;
    public float yAccel;

    public int originX;
    public int originY;
    
    public Image spaceObjectImage;
    public int frameCount;
    
    public void move() {
		xLocation += xVel;
		yLocation -= yVel;
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
	