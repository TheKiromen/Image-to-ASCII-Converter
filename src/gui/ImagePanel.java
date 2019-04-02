package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	
	private BufferedImage image;
	
	public ImagePanel(BufferedImage img) {
		super();
		image=img;
		this.setPreferredSize(new Dimension((int)img.getWidth(),(int)img.getHeight()));
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);           
    }
}
