package data;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Converter {
	
	private String[] chars= {"@","#","&","%","$","8","=","o","*","+","~","^","-","."};
	private double avg;
	private int fontSize;
	
	
	public ArrayList<String> convert(BufferedImage img,Font f) {
		ArrayList<String> result=new ArrayList<String>();
		String row;
		ArrayList<ArrayList<Color>> pixels= new ArrayList<ArrayList<Color>>();
		fontSize=f.getSize();
		
		//Go through all pixels in image and put them into array
		for(int i=0;i<img.getHeight();i++) {
			pixels.add(new ArrayList<Color>());
			for(int j=0;j<img.getWidth();j++) {
				pixels.get(i).add(new Color(img.getRGB(j, i)));
			}
		}
		
		
		toGrayScale(pixels);

		
		for(int rows=0;rows<pixels.size();rows+=fontSize) {
			row="";
			for(int cols=0;cols<pixels.get(rows).size();cols+=fontSize/2) {
				avg=0;
				
				//Calculating avg brightness of area given by font size
				for(int w=cols;w<cols+fontSize;w++) {
					for(int h=rows;h<rows+(fontSize/2);h++) {
						avg+=pixels.get(rows).get(cols).getRed();
					}
				}
				avg/=(fontSize*(fontSize/2));	
				
				
				//Matching avg brightness with corresponding character
				row+=chars[(int)Math.floor((avg/255)*(chars.length-1))];
				
			}
			
			result.add(new String(row));
		}
		
		
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
}
