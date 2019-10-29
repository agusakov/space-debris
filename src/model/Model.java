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

	public int earthOriginX;
	public int earthOriginY;

	public ArrayList<SpaceObject> spaceObjects = new ArrayList<>();
	
	//public Earth earth;
	//public Satellite satellite;
	public boolean started = false;
	public Simulation sim = new Simulation();

	
	public Model(int frameWidth, int frameHeight) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.earthOriginX = frameWidth/2 - 32;
		this.earthOriginY = frameHeight/2 - 32;
	}

	public void createSpaceObjects() {
		spaceObjects.clear();
		Earth earth = new Earth(this.earthOriginX, this.earthOriginY, frameWidth, frameHeight, 64, 64);
		Satellite satellite = new Satellite(this.earthOriginX + 24, this.earthOriginY + 24, -60000000, 60000000, 7800, 7800, frameWidth, frameHeight, 32, 32, 100);

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