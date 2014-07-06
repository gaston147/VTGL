package fr.vincentteam.vtgl;

public class VTGLUtils {
	public static int[] fill(int[] t, int v) {
		for (int i = 0; i < t.length; i++)
			t[i] = v;
		return t;
	}
	
	public static void sleep(long miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void sync(int fps) {
		try {
			Thread.sleep(1000 / fps);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
