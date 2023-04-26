package model;

public interface IModel {
	public void initial();

	public void initial(int x, int y);

	public void loadImage();

	public void reset();

	public void move();

	public void update();

	public void repaint();

	public boolean collision(); // Va chạm

	public boolean bounce(); // Nảy

	public void destroy();
}



