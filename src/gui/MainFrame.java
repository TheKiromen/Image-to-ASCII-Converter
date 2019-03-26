package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	//------------------COMPONENTS----------------//
	private Font myFont;
	private JButton fileSelect,convert;
	private JLabel chooseFile;

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
				
			}
		});
		add(convert,gc);
		
		}
	
	
	//--------------------CHOOSING FILE-------------------------//
	
	private class FileChooser implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			convert.setEnabled(true);
		}
	}
	
	
}
