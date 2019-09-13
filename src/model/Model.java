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
	public final static double PI = 3.14159;

	public final static int WORLD_WIDTH = 1366;
	public final static int WORLD_HEIGHT = 768;

	public int frameWidth = 500;
	public int frameHeight = 300;

	public int originX;
	public int originY;

	public ArrayList<SpaceObject> spaceObjects = new ArrayList<>();
	
	//public Earth earth;
	//public Satellite satellite;
	public boolean started = false;
	public Simulation sim = new Simulation();

	
	public Model(int frameWidth, int frameHeight) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.originX = frameWidth/2 - 128;
		this.originY = frameHeight/2 - 128;
	}

	public void createSpaceObjects() {
		spaceObjects.clear();
		Earth earth = new Earth(originX, originY, frameWidth, frameHeight, 256, 256);
		Satellite satellite = new Satellite(originX, originY, 0, 0, 0, 20, 9, 0, frameWidth, frameHeight, 64, 64);

		spaceObjects.add(earth);
		spaceObjects.add(satellite);
		//System.out.println("added earth in model");
		//System.out.println(spaceObjects.size());
	}

	public void updateSimulation() {
		//System.out.println("in updateSimulation()");
		if (!started) {
			//System.out.println("creating space objects");
			createSpaceObjects();
			//System.out.println("starting simulation");
			sim.startSimulation();
			//System.out.println("simulation started");
			started = true;
		}
		else {
			//System.out.println("updating time in simulation");
			sim.updateTime();
			//System.out.println("moving space objects");
			for (SpaceObject a : spaceObjects) {
				if (a.moves) {
					a.move(sim.elapsedTime);
				}
			}
		}
	}

	public ArrayList<SpaceObject> getSpaceObjects() {
		//System.out.println(spaceObjects.size());
		return spaceObjects;
	}

}