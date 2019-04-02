package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ImageFrame extends JFrame {

	private Dimension imageSize,screenSize;
	private int x,y,width,height;
	
	public ImageFrame(BufferedImage image) {
		super("Image");
		
		imageSize=new Dimension(image.getWidth(),image.getHeight());
		screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		
		//---------------Setting x an width-----------------------//
		
		if(imageSize.getWidth()>screenSize.getWidth()) {
			width=(int)screenSize.getWidth()/2;
		}else {
			width=(int)imageSize.getWidth();
		}
		
		x=0;
		
		
		
		//----------------Setting y and height---------------------//
		
		if(imageSize.getHeight()>screenSize.getHeight()) {
			height=(int)screenSize.getHeight();
		}else {
			height=(int)imageSize.getHeight();
		}
		
		y=(int)(screenSize.getHeight()-height)/2;
				
		
		
		
		setSize(width,height);
		setLocation(x, y);
		
		
	}
	
}
