package fr.vincentteam.vtgl.fonts;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import fr.vincentteam.vtgl.bitmaps.Bitmap;
import fr.vincentteam.vtgl.geometry.Size2i;

public class BaseFont implements VTGLFont {
	private HashMap<Character, Bitmap> chars;
	private int lineHeight;
	private String charOrder = "abcdefghijklmnopqrstuvwxyz?!:/;., ";
	
	public BaseFont() {
		chars = load(getClass().getResourceAsStream("/res/fonts/base_font.png"), 6, 10, 10, 10);
	}
	
	public Bitmap getCharacter(char c) {
		return chars.get(c);
	}
	
	public int getLineHeight() {
		return lineHeight;
	}
	
	public int getWidth(String str) {
		str = str.toLowerCase();
		int x = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			Bitmap bmpC = getCharacter(c);
			if (bmpC != null) {
				x += getCharacter(c).getSize().width;
			}
		}
		return x;
	}
	
	public void drawString(Bitmap bitmap, int x, int y, String str) {
		str = str.toLowerCase();
		int j = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			Bitmap bmpC = getCharacter(c);
			if (bmpC != null) {
				bitmap.draw(x + j, y, 0, 0, bmpC.getSize().width, bmpC.getSize().height, bmpC);
				j += bmpC.getSize().width;
			}
		}
	}
	
	private HashMap<Character, Bitmap> load(InputStream stream, int charWidth, int charHeight, int collumns, int lines) {
		try {
			BufferedImage bi = ImageIO.read(stream);
			int[] pixels = bi.getRGB(0, 0, bi.getWidth(), bi.getHeight(), null, 0, bi.getWidth());
			if (charWidth * collumns > bi.getWidth() || charHeight * lines > bi.getHeight())
				return null;
			HashMap<Character, Bitmap> chars = new HashMap<Character, Bitmap>();
			for (int y = 0; y < lines; y++) {
				if (y * lines >= charOrder.length())
					break;
				for (int x = 0; x < collumns; x++) {
					if (x + y * lines >= charOrder.length())
						break;
					int[] px = new int[charWidth * charHeight];
					for (int j = 0; j < charHeight; j++) {
						for (int i = 0; i < charWidth; i++) {
							px[i + j * charWidth] = pixels[x * charWidth + i + (y * charHeight + j) * bi.getWidth()] & 0xffffff;
						}
					}
					chars.put(charOrder.charAt(x + y * lines), new Bitmap(new Size2i(charWidth, charHeight), px));
				}
			}
			return chars;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
