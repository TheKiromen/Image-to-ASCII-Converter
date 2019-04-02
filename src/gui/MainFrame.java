package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.Converter;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	//------------------COMPONENTS----------------//
	private Font myFont;
	private JButton fileSelect,convert;
	private File file;
	private BufferedImage img;
	private JFileChooser chooser;
	private FileNameExtensionFilter filter;
	private int returnVal;
	private JTextArea chooseFile;

	
	//--------------FILE VALIDATION-------------//
	private Pattern pattern;
	private Matcher matcher;
	
	
	//---------------CONVERTING------------------//
	Converter conv;
	
	

	public MainFrame(String s){
		super(s);
		setSize(400,350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		//------------------------LAYOUT---------------------//
		
		GridBagLayout gb = new GridBagLayout();
		setContentPane(new JPanel(gb));
		GridBagConstraints gc = new GridBagConstraints();
		myFont = new Font("TimesRoman", Font.BOLD, 30);
		
		gc.weightx=1;
		gc.weighty=1;
		gc.fill=GridBagConstraints.NONE;
		gc.insets=new Insets(10,20,10,20);
		
		
		gc.gridx=0;
		gc.gridy=0;
		chooseFile=new JTextArea("Choose file:");
		chooseFile.setFont(myFont);
		chooseFile.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		chooseFile.setBackground(UIManager.getColor("JFrame.background"));
		chooseFile.setEditable(false);
		chooseFile.setFocusable(false);
		add(chooseFile,gc);
		
		gc.gridy=1;
		fileSelect = new JButton("Open");
		fileSelect.setFont(myFont);
		fileSelect.setPreferredSize(new Dimension(150,80));
		fileSelect.addActionListener(new FileChooser());
		add(fileSelect,gc);
		
		gc.gridy=2;
		gc.insets=new Insets(10,20,30,20);
		convert = new JButton("Convert");
		convert.setFont(myFont);
		convert.setPreferredSize(new Dimension(150,80));
		convert.setEnabled(false);
		
		
		
		//-----------------CONVERSION-------------------//
		convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseFile.setForeground(Color.BLACK);
				chooseFile.setText("Converting...");
				
				
				//Honestly I dont know why but without this line it just cut out part of text
				chooseFile.setSize(chooseFile.getPreferredSize());
				//And without this line it doesnt even refresh TextArea
				chooseFile.paintImmediately(chooseFile.getVisibleRect());

				
				getObjectInstance().setEnabled(false);
				conv.convert(img);
				getObjectInstance().setEnabled(true);
				chooseFile.setText("Conversion succesfull!");
				chooseFile.setForeground(Color.GREEN);
			}
		});
		add(convert,gc);
		//----------------------------------------------//
		
		
		}
	
	
	//Additional validation if user baypasses the extension filter
	private boolean fileValidation(File img) {
		pattern=Pattern.compile("(\\.png$)|(\\.jpg$)");
		matcher=pattern.matcher(img.getPath());
		return matcher.find();
	}
	
	
	public void setConverter(Converter c) {
		this.conv=c;
	}
	
	private MainFrame getObjectInstance() {
		return this;
	}

	//--------------------CHOOSING FILE-------------------------//
	
	private class FileChooser implements ActionListener{
		ImageFrame img_frame;
		
		public void actionPerformed(ActionEvent e) {
			chooser = new JFileChooser(System.getProperty("user.home")+"/Desktop");
			filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
			chooser.setFileFilter(filter);
			returnVal = chooser.showOpenDialog(null);
			if(returnVal==0) {
				file = chooser.getSelectedFile();
				if(fileValidation(file)) {
					try {
						img=ImageIO.read(file);
						convert.setEnabled(true);
						chooseFile.setText("File chosen");
						chooseFile.setForeground(Color.GREEN);
						if(img_frame!=null) {
							img_frame.dispose();
						}
						img_frame=new ImageFrame(img);
						img_frame.setVisible(true);
						getObjectInstance().toFront();
						
					} catch (IOException e1) {
						convert.setEnabled(false);
						chooseFile.setText("Failed to convert");
						chooseFile.setForeground(Color.RED);
					}
				}else {
					convert.setEnabled(false);
					chooseFile.setText("Wrong file");
					chooseFile.setForeground(Color.RED);
				}
			}
		}
	}
	
	
}
