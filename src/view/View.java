package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Point;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Image;
import controller.*;
/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
public class View extends JPanel{
	
	public JFrame frame = new JFrame();
	JPanel panelEnvironment = new JPanel();

	int worldWidth = 1366;
	int worldHeight = 768;

	float worldScale;
	//int worldScale = 1;

	HashMap<Image,ArrayList<BufferedImage>> images = new HashMap<>();

	ArrayList<Image> spaceObjectImages = new ArrayList<>();
	ArrayList<Point> spaceObjectLocations = new ArrayList<>();
	ArrayList<Integer> spaceObjectFrameCt = new ArrayList<>();
	//ArrayList<Direction> spaceObjectDirection = new ArrayList<>();
	ArrayList<Dimension> spaceObjectDimension = new ArrayList<>();
	
    int picNum = 0;
        	
	public View() {
		//System.out.println("in view creation");
		loadImages();
		//System.out.println("loaded images");
		this.frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(worldWidth, worldHeight);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setBackground(Color.black);
    	
		//this.makeFrame();
		//System.out.println("made frame");
    	
	}
	
	void makeFrame() {
		this.frame.getContentPane().add(this);
		this.frame.setBackground(Color.black);
		//System.out.println("set backgroundColor");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//System.out.println("set default close operation");
		this.frame.setSize(this.getFrameWidth() , this.getFrameHeight());
		//System.out.println("set frame size");
		this.frame.setVisible(true);
		//System.out.println("made frame visible");
		this.update();
		//System.out.println("updated");
	}
	
	public View getView() {
		return this;
	}
	
    public void update() {
		//System.out.println(spaceObjectImages.size());
		//System.out.println("in update");
    	try {
			//System.out.println("sleep");
    		Thread.sleep(100);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
	
    public void paint(Graphics g) {
		//System.out.println("painting");
		float widthRatio = (float) this.getWidth()/worldWidth;
		float heightRatio = (float) this.getHeight()/worldHeight;

		if(widthRatio > heightRatio){
			this.worldScale = (float) this.getHeight()/worldHeight;
		}
		else{
			this.worldScale = (float) this.getWidth()/worldWidth;
		}
		panelEnvironment.setBackground(Color.black);
		//g.drawImage(images.get(Image.TITLESCREEN).get(0), 0, 0, this.getWidth(), this.getHeight(), this);
    	//picNum = (picNum + 1) % frameCount;
		displaySpaceObjects(g);
    }
    
	
    private BufferedImage createImage(String name){
    	BufferedImage bufferedImage;
    	try {
			//System.out.println("getting bufferedImage: images/" + name);
			bufferedImage = ImageIO.read(new File("images/" + name));
			//System.out.println("got bufferedImage" + bufferedImage.toString());
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
	}

	void displaySpaceObjects(Graphics g) {
		//System.out.println("size of spaceObjects: " + spaceObjectImages.size());
		//if (!paused) {
		picNum++;
		//}

		for (int i = 0; i < spaceObjectImages.size(); i++) {
			//System.println("getting" + spaceObjectImages.get(i));
			drawImage(g,
				spaceObjectImages.get(i),
				picNum%(spaceObjectFrameCt.get(i)),
				spaceObjectLocations.get(i).x,
				spaceObjectLocations.get(i).y,
				spaceObjectDimension.get(i).width,
				spaceObjectDimension.get(i).height);
		}
		
	}

	void drawImage(Graphics g, Image i, int index, int x, int y, int width, int height){
		g.drawImage(images.get(i).get(index), (int) (x*worldScale), (int) (y*worldScale), (int) (width*worldScale), (int) (height*worldScale), this);
	}
	
	void loadImages() {
		for(Image e : Image.values()){
			images.put(e, new ArrayList<BufferedImage>());
			//System.out.println(e);
			for(String  s: e.getFileNames()){
				//System.out.println(s);
				images.get(e).add(createImage(s));
			}
			//System.out.println(images.get(Image.EARTH));
		}
	}
	
	public int getFrameWidth() {
		return this.getWidth();
	}
	
	public int getFrameHeight() {
		return this.getHeight();
	}

	public void addSpaceObject(Image i, Point spaceP, int frameCount, Dimension dim){
		//System.out.println("adding EARTH in view");
		spaceObjectImages.add(i);
		//System.out.println(i + " added in view");
		spaceObjectLocations.add(spaceP);
		spaceObjectFrameCt.add(frameCount);
		spaceObjectDimension.add(dim);
	}

	public void clearSpaceObjects(){
		spaceObjectImages.clear();
		spaceObjectLocations.clear();
		spaceObjectFrameCt.clear();
	}

	public static void main(String[] args) {
		//System.out.println("in main");
		Controller cntrl1 = new Controller();
		//System.out.println("new controller created");
		cntrl1.start();
	}
}