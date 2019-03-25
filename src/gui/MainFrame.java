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

	public MainFrame(String s){
		super(s);
		setSize(400,250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//------------------------LAYOUT---------------------//
		
		GridBagLayout gb = new GridBagLayout();
		setContentPane(new JPanel(gb));
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx=1;
		gc.weighty=1;
		gc.fill=GridBagConstraints.NONE;
		gc.insets=new Insets(10,20,10,20);
		
		
		gc.gridx=0;
		gc.gridy=0;
		JLabel chooseFile = new JLabel("Choose file:");
		chooseFile.setFont(new Font("TimesRoman", Font.BOLD, 30));
		add(chooseFile, gc);
		
		gc.gridy=1;
		gc.insets=new Insets(10,20,30,20);
		JButton fileSelect = new JButton("Open");
		fileSelect.setFont(new Font("TimesRoman", Font.BOLD, 30));
		fileSelect.setPreferredSize(new Dimension(150,80));
		fileSelect.addActionListener(new FileChooser());
		add(fileSelect,gc);
	}
	
	
	//--------------------CHOOSING FILE-------------------------//
	
	private class FileChooser implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Hello World!");
		}
		
	}
}
