package lt.refactory.primsAlgo.graph.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;

public class GraphMock<T extends Edge> extends Graph<T> {
	List <T> edgeList;
	List<Node> nodeList;
	
	
	public GraphMock(){
		edgeList = new ArrayList<T>();
		nodeList = new ArrayList<Node>();
		Node node1 = new Node(BigDecimal.valueOf(100),BigDecimal.valueOf(100));
		Node node2 = new Node(BigDecimal.valueOf(500),BigDecimal.valueOf(50));
		Node node3 = new Node(BigDecimal.valueOf(300),BigDecimal.valueOf(300));
		Node node4 = new Node(BigDecimal.valueOf(100),BigDecimal.valueOf(10));
		
		nodeList.add(node1);
		nodeList.add(node2);
		nodeList.add(node3);
		nodeList.add(node4);
		
		@SuppressWarnings("unchecked")
		T edge1 = (T) new Edge(node1,node2); 
		@SuppressWarnings("unchecked")
		T edge2 = (T) new Edge(node1,node3); 
		
		edgeList.add(edge1);
		edgeList.add(edge2);

	}
	
	public List<T> getEdgeList() {
		return edgeList;
	}
	
	@Override
	public List<Node> getNodeList() {
		return nodeList;
	}
}
