package lt.refactory.primsAlgo.graph;
/**
 * Class to hold info about graph's edge.
 * @author arminas
 *
 */
public class Edge {
	private Node start;
	private Node end;
	

	public Edge(Edge edge) {
		super();
		this.start = edge.getStart();
		this.end = edge.getEnd();
	}	
	
	public Edge(Node start, Node end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Node getStart() {
		return start;
	}

	public Node getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return "Edge [start=" + start + ", end=" + end + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
	
	

}
