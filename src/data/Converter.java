package data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Converter {
	
	//2 chars at once to compensate char height issues
	private String[] chars= {"@@","##","&&","%%","$$","88","==","oo","**","++","~~","^^","--",".."};
	private double avg;
	private int fontSize;
	
	
	public ArrayList<String> convert(BufferedImage img) {
		ArrayList<String> result=new ArrayList<String>();
		String row;
		ArrayList<ArrayList<Color>> pixels= new ArrayList<ArrayList<Color>>();
		fontSize=6;
		
		//Go through all pixels in image and put them into array
		for(int i=0;i<img.getHeight();i++) {
			pixels.add(new ArrayList<Color>());
			for(int j=0;j<img.getWidth();j++) {
				pixels.get(i).add(new Color(img.getRGB(j, i)));
			}
		}
		
		
		toGrayScale(pixels);

		
		//Font size is 8 so we are taking 8x8 squares into consideration
		for(int rows=0;rows<pixels.size();rows+=fontSize) {
			row="";
			for(int cols=0;cols<pixels.get(rows).size();cols+=fontSize) {
				avg=0;
				
				//Calculating avg brightness of that 16x16 square
				for(int w=cols;w<cols+fontSize;w++) {
					for(int h=rows;h<rows+fontSize;h++) {
						avg+=pixels.get(rows).get(cols).getRed();
					}
				}
				avg/=(fontSize*fontSize);	
				
				
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
