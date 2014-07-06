package fr.vincentteam.vtgl.bitmaps;

import fr.vincentteam.vtgl.VTGLUtils;
import fr.vincentteam.vtgl.geometry.Size2i;

public class Screen extends Bitmap {
	public Screen(Size2i size) {
		super(size, VTGLUtils.fill(new int[size.width * size.height], 0xffffff));
	}
}
