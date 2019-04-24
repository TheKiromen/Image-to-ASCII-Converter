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
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;
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
	private LookAndFeel oldStyle;
	private JMenuBar menuBar;
	private JMenu menu;
	private JCheckBoxMenuItem imgPrevItem;
	//Flag for fixing repainting bug
	private Boolean flag=false;
	private Boolean showImgPrev=true;

	
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
		
		
		
		
		//Menu
		menuBar = new JMenuBar();
		menu = new JMenu("Settings");
		imgPrevItem=new JCheckBoxMenuItem("Image preview");
		imgPrevItem.setSelected(true);
		menuBar.add(menu);
		menu.add(imgPrevItem);
		setJMenuBar(menuBar);
		
		//Setting flag
		imgPrevItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showImgPrev=imgPrevItem.isSelected();
			}
		});
		
		
		
		
		//Layout
		
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
		
	

		//----SETTING UP FILE CHOOSER----//
		oldStyle=UIManager.getLookAndFeel();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			chooser = new JFileChooser(System.getProperty("user.home")+"/Desktop");
			filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png","bmp","jpeg");
			chooser.setFileFilter(filter);
			UIManager.setLookAndFeel(oldStyle);
		} catch (Exception ex) {}
		
		
		
		
		//-----------------CONVERSION-------------------//
		convert.addActionListener(new ActionListener() {
			AsciiFrame output;
			
			public void actionPerformed(ActionEvent arg0) {
				chooseFile.setForeground(Color.BLACK);
				
				//After first conversion text doesnt repaint fully and lefts some old text in backgroud
				//Stupid solustion but "smart" ones aren't working so I'll leave it at that.
				//If you have any ideas how to make it more elegant and working feel free to contribute.
				if(flag) {
					chooseFile.setText("          Converting...          ");
				}else {
					flag=true;
					chooseFile.setText("Converting...");
				}
				
				
				//Makes TextArea more responsive 
				chooseFile.setSize(chooseFile.getPreferredSize());
				
				
				//Without this line TextArea doesnt repaint until end of the method
				chooseFile.paintImmediately(chooseFile.getVisibleRect());

				
				getObjectInstance().setEnabled(false);
				
				
				if(output!=null) {
					output.dispose();
				}
				output=new AsciiFrame(conv.convert(img));
				output.setVisible(true);
				
				
				getObjectInstance().setEnabled(true);
				getObjectInstance().toFront();
				
				
				chooseFile.setText("Conversion succesfull!");
				chooseFile.setForeground(Color.GREEN);
				
			}
		});
		add(convert,gc);
		//----------------------------------------------//
		
		
		}
	
	
	//Additional validation if user baypasses the extension filter
	private boolean fileValidation(File img) {
		pattern=Pattern.compile("(\\.PNG$)|(\\.JPG$)|(\\.BMP$)||(\\.JPEG$)");
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
						
						if(showImgPrev) {
							img_frame=new ImageFrame(img);
							img_frame.setVisible(true);
							getObjectInstance().toFront();
						}
						
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
