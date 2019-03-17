
public class Vertex implements Comparable<Vertex>{
	
	public float x;
	public float y;
	public float z;
	
	public Vertex(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double distanceTo(Vertex v2) {
		return Math.sqrt((x-v2.x)*(x-v2.x)+(y-v2.y)*(y-v2.y)+(z-v2.z)*(z-v2.z));
	}
	
	public boolean equals(Vertex v2) {
		if(x==v2.x&&y==v2.y&&z==v2.z)
			return true;
		else
			return false;
	}
	
	public int compareTo(Vertex v2) {
		if(v2==null)
			throw new NullPointerException();
		else if(x!=v2.x) {
			if(x<v2.x)
				return -1;
			else
				return 1;
		}
		else if(y!=v2.y) {
			if(y<v2.y)
				return -1;
			else
				return 1;
		}
		else if(z!=v2.z) {
			if(z<v2.z)
				return -1;
			else
				return 1;
		}
		else
			return 0;
	}
	
	public String toString() {
		return x+","+y+","+z;
	}
}
