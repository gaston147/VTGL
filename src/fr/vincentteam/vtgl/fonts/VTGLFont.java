package fr.vincentteam.vtgl.fonts;

import fr.vincentteam.vtgl.bitmaps.Bitmap;

public interface VTGLFont {
	public Bitmap getCharacter(char c);
	
	public int getLineHeight();
	
	public int getWidth(String str);
	
	public void drawString(Bitmap bitmap, int x, int y, String str);
}
