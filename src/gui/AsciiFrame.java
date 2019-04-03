package gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AsciiFrame extends JFrame {

	private Dimension screenSize;
	private int x,y,width,height;
	private JScrollPane scroll;
	private JTextArea output;
	
	public AsciiFrame(ArrayList<String> text) {
		super("ASCII");
		
		screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		
		//---------------Setting x an width-----------------------//
		
		width=(int)screenSize.getWidth()/2;
		x=width;
		
		
		
		//----------------Setting y and height---------------------//
		
		height=(int)screenSize.getHeight()-100;
		y=(int)(screenSize.getHeight()-height)/2;
		
		
		
		setPreferredSize(new Dimension(width,height));
		setSize(width,height);
		setLocation(x, y);
		
		
		
		
		//---------------Setting scrolls and JTextArea---------------//
		output=new JTextArea();
		output.setEditable(false);
		output.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		output.setFont(new Font(Font.MONOSPACED,Font.BOLD,6));
		for(String row : text) {
			output.append(row+"\n");
		}
		
		scroll=new JScrollPane(output);
		scroll.setAutoscrolls(true);
		scroll.getVerticalScrollBar().setUnitIncrement(25);
		scroll.getHorizontalScrollBar().setUnitIncrement(25);
		add(scroll);
	}
	
}
