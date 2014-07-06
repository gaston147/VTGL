package fr.vincentteam.vtgl.events;


public class MouseClickEvent implements VTGLEvent {
	private int x, y;
	
	public MouseClickEvent(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
