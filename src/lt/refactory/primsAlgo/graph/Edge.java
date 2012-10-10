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
	
	public boolean containsNode(Node node){
		if (start.equals(node) || end.equals(node)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Edge [start=" + start + ", end=" + end + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (start != null && end != null) {
			result = prime * start.hashCode() * end.hashCode();
		} else {
			result = prime * result + ((end == null) ? 0 : end.hashCode());
			result = prime * result + ((start == null) ? 0 : start.hashCode());
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Edge other = (Edge) obj;
		if (end == null) {
			if (other.end != null) {
				return false;
			}
		}
		if (start == null) {
			if (other.start != null)
				return false;
		}
		if (start.equals(other.start) && end.equals(other.end)
				|| start.equals(other.end) || end.equals(other.start)) {
			return true;
		} else {
			return false;
		}
	}

}
