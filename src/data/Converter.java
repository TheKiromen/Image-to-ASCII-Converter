package data;

import gui.MainFrame;

public class Converter {

	public static void main(String[] args) {
		Converter c = new Converter();
		c.setup();
	}
	
	private void setup() {
		MainFrame window = new MainFrame("Img -> ASCII");
		window.setVisible(true);
	}

}
