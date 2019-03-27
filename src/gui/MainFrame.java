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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.Converter;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	//------------------COMPONENTS----------------//
	private Font myFont;
	private JButton fileSelect,convert;
	private JLabel chooseFile;
	private File file;
	private BufferedImage img;
	private JFileChooser chooser;
	private FileNameExtensionFilter filter;
	private int returnVal;
	
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
		chooseFile = new JLabel("Choose file:");
		chooseFile.setFont(myFont);
		add(chooseFile, gc);
		
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
		convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conv.convert(img);
			}
		});
		add(convert,gc);
		
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

	//--------------------CHOOSING FILE-------------------------//
	
	private class FileChooser implements ActionListener{
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
