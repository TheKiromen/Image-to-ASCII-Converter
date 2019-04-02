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
		
		
		toGrayScale(pixels);
		
		result=null;
		return result;
	}

	
	
	private void toGrayScale(ArrayList<ArrayList<Color>> pixels) {
		int avg;
		
		for(ArrayList<Color> row : pixels) {
			for(Color pixel : row) {
				avg=(pixel.getRed()+pixel.getBlue()+pixel.getGreen())/3;
				pixel=new Color(avg,avg,avg);
			}
		}
	}
	
	
	//PixelToAscii (given int retuns string witch char/whitespaces)
}
