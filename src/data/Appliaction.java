package data;

import java.io.File;

import gui.MainFrame;

public class Appliaction {
	
	public static void main(String[] args) {
		Appliaction c = new Appliaction();
		c.setup();
	}
	
	private void setup() {
		MainFrame window = new MainFrame("Img -> ASCII");
		Converter conv = new Converter();
		window.setConverter(conv);
		window.setVisible(true);
	}
	
}
