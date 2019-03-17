
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.common.io.LittleEndianDataInputStream;

public class Model {
	public Vertex[] vertices;
	public Edge[] edges;
	
	public Model(String fileName) throws IOException{
		
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(new FileInputStream(fileName));
		dis.skipBytes(80);
		int triangle_count = dis.readInt();
		Vertex[] vertices_raw = new Vertex[triangle_count*3];
		for(int i = 0; i < triangle_count; i++) {
			dis.skipBytes(12);
			for(int j = 0; j < 3; j++)
				vertices_raw[i*3+j] = new Vertex(dis.readFloat(),dis.readFloat(),dis.readFloat());
			dis.skipBytes(2);
		}
		dis.close();
		
		Vertex[] vertices_unsorted = new Vertex[triangle_count*3];
		for(int i = 0; i < vertices_raw.length; i++)
			vertices_unsorted[i] = vertices_raw[i];
		
		Arrays.sort(vertices_raw);
		ArrayList<Vertex> vertex_list = new ArrayList<>();
		
		vertex_list.add(vertices_raw[0]);
		for(int i = 1; i < vertices_raw.length;i++)
			if(!vertices_raw[i].equals(vertices_raw[i-1]))
				vertex_list.add(vertices_raw[i]);
		
		vertices = vertex_list.toArray(new Vertex[vertex_list.size()]);
		
		Edge[] edges_raw = new Edge[triangle_count*3];
		for(int i = 0; i < triangle_count; i++) {
			int v1 = Arrays.binarySearch(vertices, vertices_unsorted[3*i]);
			int v2 = Arrays.binarySearch(vertices, vertices_unsorted[3*i+1]);
			int v3 = Arrays.binarySearch(vertices, vertices_unsorted[3*i+2]);
			edges_raw[3*i] = new Edge(v1,v2,vertices);
			edges_raw[3*i+1] = new Edge(v1,v3,vertices);
			edges_raw[3*i+2] = new Edge(v2,v3,vertices);
		}
		
		Arrays.sort(edges_raw);
		ArrayList<Edge> edge_list = new ArrayList<>();
		
		edge_list.add(edges_raw[0]);
		for(int i = 1; i < edges_raw.length;i++)
			if(!edges_raw[i].equals(edges_raw[i-1]))
				edge_list.add(edges_raw[i]);
		
		edges = edge_list.toArray(new Edge[edge_list.size()]);
	}
	
	public int closestPoint(Vertex v) {
		int index = 0;
		for(int i = 1; i < vertices.length; i++) {
			if(vertices[i].distanceTo(v)<vertices[index].distanceTo(v)) {
				index = i;
			}
		}
		return index;
	}
	
	public void saveEdges(String filename, int start, int end) throws IOException {
		PrintWriter pw = new PrintWriter(filename);
		pw.println(start+","+end);
		for(int i = 0; i < edges.length; i++)
			pw.println(edges[i]);
		pw.close();
	}
	
	public void saveVertices(String filename) throws IOException {
		PrintWriter pw = new PrintWriter(filename);
		for(int i = 0; i < vertices.length; i++)
			pw.println(vertices[i]);
		pw.close();
	}
}
