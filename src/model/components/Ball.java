package model.components;

import javax.swing.ImageIcon;

import config.General;
import model.IModel;
import utils.Constants;

public class Ball extends General implements IModel {

	
	private int xDirection;
	private int yDirection;
	
	
	public int getxDirection() {
		return xDirection;
	}

	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}

	public int getyDirection() {
		return yDirection;
	}

	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}
	
	
	
	public Ball() {
		initial();
	}
	
	@Override
	public void initial() {
		this.setxDirection(-Constants.SPEED_BALL);
		this.setyDirection(-Constants.SPEED_BALL);
		loadImage();
		getImageDimensions();
		reset();
	}

	@Override
	public void initial(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadImage() {
		ImageIcon imageIcon = new ImageIcon("src/assets/images/ball__2.png"); // PATH URL
		image = imageIcon.getImage();
	}

	@Override
	public void reset() {
		setX(Constants.INITIAL_BALL_X);
		setY(Constants.INITIAL_BALL_Y);
	}

	@Override
	public void move() {
		x = x + xDirection;
		y = y + yDirection;
		bounce();
		
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
		if (x == 0) {
			setxDirection(Constants.SPEED_BALL);
		}

		if (x == Constants.SCREEN_WIDTH - getImageWidth()) {
			setxDirection(-Constants.SPEED_BALL);
		}

		if (y == 0) {
			setyDirection(Constants.SPEED_BALL);
		}

		return false;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
