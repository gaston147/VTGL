package fr.vincentteam.vtgl.bitmaps;

import fr.vincentteam.vtgl.VTGL;
import fr.vincentteam.vtgl.VTGLUtils;
import fr.vincentteam.vtgl.fonts.VTGLFont;
import fr.vincentteam.vtgl.geometry.Size2i;

public class Bitmap {
	private int[] pixels;
	private Size2i size;
	private VTGLFont font;
	
	public Bitmap(Size2i size, int[] pixels, VTGLFont font) {
		this.size = size;
		this.pixels = pixels;
		this.font = font;
	}
	
	public Bitmap(Size2i size, int[] pixels) {
		this(size, pixels, VTGL.getDefaultFont());
	}
	
	public Bitmap(Size2i size) {
		this(size, VTGLUtils.fill(new int[size.width * size.height], -1), VTGL.getDefaultFont());
	}
	
	public Size2i getSize() {
		return size;
	}
	
	public void fillRect(int x, int y, int width, int height, int color) {
		for (int j = y; j < y + height; j++) {
			for (int i = x; i < x + width; i++) {
				if (i >= 0 && j >= 0 && i < size.width && j < size.height) {
					pixels[i + j * size.width] = color;
				}
			}
		}
	}
	
	public int getPixel(int x, int y) {
		if (x >= 0 && y >= 0 && x < size.width && y < size.height)
			return pixels[x + y * size.height];
		return -1;
	}
	
	public void setPixel(int x, int y, int color) {
		if (x >= 0 && y >= 0 && x < size.width && y < size.height)
			pixels[x + y * size.height] = color;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	public Bitmap getBitmap(int x, int y, int width, int height) {
		int[] pixels = new int[width * height];
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (x + i >= 0 && y + j >= 0 && x + i < size.width && y + j < size.height) {
					pixels[i + j * width] = this.pixels[x + i + (y + j) * size.height];
				}
			}
		}
		return new Bitmap(new Size2i(width, height), pixels);
	}
	
	public void draw(int x, int y, int imgX, int imgY, int width, int height, Bitmap bitmap) {
		int[] pixels = bitmap.getPixels();
		Size2i bitmapSize = bitmap.getSize();
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (x + i >= 0 && y + j >= 0 && x + i < size.width && y + j < size.height && imgX + i < bitmapSize.width && imgY + j < bitmapSize.height) {
					int color = pixels[imgX + i + (imgY + j) * bitmap.getSize().width];
					if (color != -1)
						this.pixels[x + i + (y + j) * size.width] = color;
				}
			}
		}
	}
	
	public void drawString(int x, int y, String str) {
		font.drawString(this, x, y, str);
	}

	public VTGLFont getFont() {
		return font;
	}
}
