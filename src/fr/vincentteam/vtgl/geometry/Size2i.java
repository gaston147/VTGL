package fr.vincentteam.vtgl.geometry;

public class Size2i {
	public int width, height;
	
	public Size2i(int width, int height) {
		set(width, height);
	}
	
	public void set(int width, int height) {
		this.width = width;
		this.height = height;
	}
}
