package model.components;

import java.util.Random;

import javax.swing.ImageIcon;

import config.General;
import model.IModel;

public class Brick extends General implements IModel {

	//Image Path
	String[] Image__Brick = {
		"src/assets/images/Brick__red.png",
		"src/assets/images/Brick__blue.png",
		"src/assets/images/Brick__yellow.png",
		
	};
	String Brick__red = "src/assets/images/Brick__red.png";
	String Brick__blue = "src/assets/images/Brick__blue.png";
	String Brick__yellow = "src/assets/images/Brick__yellow.png";
	String Brick__green = "src/assets/images/Brick__green.png";


	private boolean destroyed;
	
	public Brick(int _x, int _y) {
		initial(_x, _y);
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	@Override
	public void initial() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initial(int x, int y) {
		this.setX(x);
		this.setY(y);
		this.destroyed = false;
		loadImage();
		getImageDimensions();
	}

	@Override
	public void loadImage() {
		int index = randomNumber();
		ImageIcon imageIcon = new ImageIcon(Image__Brick[index]);
		image = imageIcon.getImage();
	}

	public int randomNumber() {
		Random number = new Random();
		return number.nextInt(3);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean collision() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bounce() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
