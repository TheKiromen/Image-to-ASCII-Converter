package gui;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class ImageFrame extends JFrame {

	public ImageFrame(BufferedImage image) {
		super("Image");
		
		setSize(image.getWidth(),image.getHeight());
		
		
	}
	
}
