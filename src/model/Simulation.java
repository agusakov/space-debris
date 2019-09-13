package model;

import java.awt.Color;
import java.io.Serializable;
import java.lang.*;

public class Simulation implements Serializable {

	public double elapsedTime;
	public long startTime;
	public long pauseStart;
	public long playTime;
	public long pausedDuration = 0;
	public long previouslyPaused = 0;

	/**
	* Increments time while game is being played.
	*/
	public void updateTime() {
		playTime = System.currentTimeMillis();
		elapsedTime = (playTime - startTime) * (0.001);
	}

	/**
	* Sets game start time and sets scores to zero.
	*/
	public void startSimulation() {
        //System.out.println(System.currentTimeMillis());
		startTime = System.currentTimeMillis();
	}

	/**
	* Resets duration game was paused and game playing time to zero.
	*/
	public void restart() {
		// playTime = 0;
		pausedDuration = 0;
		elapsedTime = 0;
	}

	/**
	* Sets time for when game was paused and keeps track of how long the game was previously paused.
	*/
	public void pauseGame() {
		pauseStart = System.currentTimeMillis();
		previouslyPaused = pausedDuration;
	}

	/**
	* Keeps track of how long the game has been paused.
	*/
	public void paused() {
		long now = System.currentTimeMillis();
		pausedDuration = previouslyPaused + now - pauseStart;
	}

	/**
	* Returns how long the game has been actively played.
	* @return playing time elapsed
	*/
	public double getElapsedTime() {
		return elapsedTime;
	}

}