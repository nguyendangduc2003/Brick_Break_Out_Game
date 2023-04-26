package model.components;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import config.General;
import model.IModel;
import utils.Constants;

public class Paddle extends General implements IModel {
	private int xDirection;
	
	
	public Paddle() {
		initial();
	}
	
	public int getxDirection() {
		return xDirection;
	}

	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}

	

	@Override
	public void initial() {
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
		ImageIcon imageIcon = new ImageIcon("src/assets/images/paddle.png"); //
		image = imageIcon.getImage();
	}


	@Override
	public void reset() {
		x = Constants.INITIAL_PADDLE_X;
		y = Constants.INITIAL_PADDLE_Y;
	}


	@Override
	public void move() {
		x = x + xDirection;
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
		if (x <= 0) {
			x = 0;
		}

		if (x >= Constants.SCREEN_WIDTH - this.getImageWidth()) {
			x = Constants.SCREEN_WIDTH - this.getImageWidth();
		}
		return false;
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();

		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			xDirection = -Constants.SPEED_PADDLE;
		}

		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			xDirection = Constants.SPEED_PADDLE;
		}
	}

	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();

		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			xDirection = 0;
		}

		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			xDirection = 0;
		}
	}
}
