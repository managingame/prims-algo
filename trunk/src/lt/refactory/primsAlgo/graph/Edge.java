package lt.refactory.primsAlgo.graph;
/**
 * Class to hold info about graph's edge.
 * @author arminas
 *
 */
public abstract class Edge {
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

}
