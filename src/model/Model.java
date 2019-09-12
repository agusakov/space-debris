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

import java.util.ArrayList;
import java.awt.Point;
import java.util.Iterator;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Comparator;
import java.io.Serializable;

public class Model implements Serializable{
	public final static int WORLD_WIDTH = 1366;
	public final static int WORLD_HEIGHT = 768;

	public int frameWidth = 500;
	public int frameHeight = 300;

	public int originX;
	public int originY;

	public ArrayList<SpaceObject> spaceObjects = new ArrayList<>();
	
	public Earth earth;

	
	public Model(int frameWidth, int frameHeight) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.originX = frameWidth/2 - 128;
		this.originY = frameHeight/2 - 128;
	}

	public void createSpaceObjects() {
		spaceObjects.clear();
		Earth earth = new Earth(originX, originY, frameWidth, frameHeight, 256, 256);
		Satellite satellite = new Satellite(originX, originY, 0, 9, 9, 0, 200, -200, frameWidth, frameHeight, 64, 64);

		spaceObjects.add(earth);
		spaceObjects.add(satellite);
		//System.out.println("added earth in model");
		//System.out.println(spaceObjects.size());
	}

	public void updateSimulation() {
		createSpaceObjects();
	}

	public ArrayList<SpaceObject> getSpaceObjects() {
		//System.out.println(spaceObjects.size());
		return spaceObjects;
	}

}