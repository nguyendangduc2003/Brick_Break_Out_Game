package config;

import java.awt.Image;
import java.awt.Rectangle;

public class General {
	protected int x; // position x
	protected int y; // position y
	
	
	private int imageWidth;
	private int imageHeight;
	
	protected Image image;
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
	}

	protected void getImageDimensions() {
		this.imageWidth = image.getWidth(null);
		this.imageHeight = image.getHeight(null);
	}
}
