package controller;

import model.*;
import view.*;
import java.awt.Point;
import java.awt.Dimension;
import java.io.*;

public class Controller {

	private Model model;
	private View view;
	
	public Controller(){
		//System.out.println("creating controller");
		view = new View();
		//System.out.println("created view");
		model = new Model(view.getFrameWidth(), view.getFrameHeight());
		//System.out.println("created model");
	}
	
        //run the simulation
	public void start(){
		//System.out.println("in start");
		for(int i = 0; i < 5000; i++)
		{
			model.updateSimulation();
			//increment the x and y coordinates, alter direction if necessary
			//model.updateLocationAndDirection();
			//update the view
			view.clearSpaceObjects();

			for(SpaceObject a : model.getSpaceObjects()){
				view.addSpaceObject(a.getImage(),
					new Point((int)a.getX(), (int)a.getY()),
					a.getFrameCount(),
					new Dimension(a.imgWidth, a.imgHeight));
					/*a.getDirection(),*/
					//new Dimension(a.imgWidth, a.imgHeight));
			}
			//System.out.println(view.spaceObjectImages.size());
			//System.out.println("on tick " + i);

			view.repaint();
			view.update();
		}
	}

	/*public void loadSimulation(){
		try{
			FileInputStream fileIn = new FileInputStream("./saved/SavedGame.eg");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			model = (Model) in.readObject();
			in.close();
			fileIn.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}*/
}
