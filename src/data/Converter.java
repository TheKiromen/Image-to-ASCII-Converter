package data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Converter {
	
	public String[] convert(BufferedImage img) {
		String[] result;
		ArrayList<ArrayList<Color>> pixels= new ArrayList<ArrayList<Color>>();
		
		//Go through all pixels in image and put them into array
		for(int i=0;i<img.getHeight();i++) {
			pixels.add(new ArrayList<Color>());
			for(int j=0;j<img.getWidth();j++) {
				pixels.get(i).add(new Color(img.getRGB(j, i)));
			}
		}

		result=null;
		return result;
	}
	
	//ToGrayScale (returns array of calculated pixels)
	
	
	//PixelToAscii (given int retuns string witch char/whitespaces)
}
