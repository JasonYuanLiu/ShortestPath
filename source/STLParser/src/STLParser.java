
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class STLParser {

	public static void main(String[] args) throws IOException {
		
		Scanner scnr = new Scanner(new File("config.txt"));
		float[] start = parsePoint(scnr.nextLine());
		float[] end = parsePoint(scnr.nextLine());
		scnr.close();
		
		Model m = new Model("model.stl");
		int start_index = m.closestPoint(new Vertex(start[0],start[1],start[2]));
		int end_index = m.closestPoint(new Vertex(end[0],end[1],end[2]));
		
		m.saveVertices("vertices.csv");
		m.saveEdges("edges.csv",start_index,end_index);
	}
	
	public static float[] parsePoint(String point) {
		int comma_pos_1 = -1, comma_pos_2 = -1, semico_pos = -1;
		for(int i=0; i < point.length(); i++) {
			if(point.charAt(i)==',') {
				if(comma_pos_1==-1) 
					comma_pos_1 = i;
				else
					comma_pos_2 = i;
			}
			else if(point.charAt(i)==';') {
				semico_pos = i;
				break;
			}
		}
		float start_x = Float.parseFloat(point.substring(0,comma_pos_1));
		float start_y = Float.parseFloat(point.substring(comma_pos_1+1,comma_pos_2));
		float start_z = Float.parseFloat(point.substring(comma_pos_2+1,semico_pos));
		return new float[]{start_x,start_y,start_z};
	}
}
