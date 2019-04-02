package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ImageFrame extends JFrame {

	private Dimension imageSize,screenSize;
	private int x,y,width,height;
	private JScrollPane scroll;
	
	public ImageFrame(BufferedImage image) {
		super("Image");
		
		imageSize=new Dimension(image.getWidth(),image.getHeight());
		screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		
		//---------------Setting x an width-----------------------//
		
		if(imageSize.getWidth()>screenSize.getWidth()/2) {
			width=(int)screenSize.getWidth()/2;
		}else {
			width=(int)imageSize.getWidth();
		}
		
		x=0;
		
		
		
		//----------------Setting y and height---------------------//
		
		if(imageSize.getHeight()>screenSize.getHeight()-100) {
			height=(int)screenSize.getHeight()-100;
		}else {
			height=(int)imageSize.getHeight();
		}
		
		y=(int)(screenSize.getHeight()-height)/2;
		
		
		
		setPreferredSize(new Dimension(width,height));
		setSize(width,height);
		setLocation(x, y);
		
		
		
		
		//---------------Setting scrolls and image loading---------------//
		scroll=new JScrollPane(new ImagePanel(image));
		scroll.setAutoscrolls(true);
		scroll.getVerticalScrollBar().setUnitIncrement(25);
		scroll.getHorizontalScrollBar().setUnitIncrement(25);
		add(scroll);
	}
	
}
