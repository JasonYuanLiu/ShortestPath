
public class Edge implements Comparable<Edge>{
	public int v1;
	public int v2;
	public double length;
	
	public Edge(int v1, int v2, Vertex[] vertices) {
		this.v1 = v1;
		this.v2 = v2;
		length = vertices[v1].distanceTo(vertices[v2]);
	}
	
	public boolean equals(Edge e2) {
		return v1==e2.v1&&v2==e2.v2;
	}
	
	public int compareTo(Edge e2) {
		if(v1!=e2.v1) {
			if(v1<e2.v1)
				return -1;
			else
				return 1;
		}
		else if(v2!=e2.v2) {
			if(v2<e2.v2)
				return -1;
			else
				return 1;
		}
		else
			return 0;
	}
	
	public String toString() {
		return v1+","+v2+","+length;
	}
}
