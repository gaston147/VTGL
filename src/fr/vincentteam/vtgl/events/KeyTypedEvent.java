package fr.vincentteam.vtgl.events;


public class KeyTypedEvent implements VTGLEvent {
	private int code;
	
	public KeyTypedEvent(int code) {
		this.code = code;
	}
	
	public int getKeyCode() {
		return code;
	}
}
