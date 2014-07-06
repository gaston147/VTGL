package fr.vincentteam.vtgl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class VTGLComponent extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private VTGL vtgl;
	private BufferedImage bi;
	
	public VTGLComponent(VTGL vtgl) {
		this.vtgl = vtgl;
		bi = new BufferedImage(vtgl.getSize().width, vtgl.getSize().height, BufferedImage.TYPE_INT_RGB);
	}
	
	public void paintComponent(Graphics g) {
		bi.setRGB(0, 0, vtgl.getSize().width, vtgl.getSize().height, vtgl.getScreen().getPixels(), 0, vtgl.getSize().width);
		g.drawImage(bi, 0, 0, vtgl.getSize().width, vtgl.getSize().height, this);
	}
}