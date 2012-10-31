package lt.refactory.primsAlgo.algorithm.test;

import java.math.BigDecimal;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.service.algorithm.SteinersAlgorithm;

public class SteinerObjectsProvider {
	
	public Edge getEdge(int firstX, int firstY, int secondX, int secondY) {
		return new Edge(new Node(new BigDecimal(firstX), new BigDecimal(firstY)), new Node(new BigDecimal(secondX), new BigDecimal(secondY)));
	}
	
	public Graph<Edge> getTreeGraph() {
		Graph<Edge> graph = new Graph<Edge>();
		
		try {
			
			graph.addEdgeWithNodes(getEdge(1,1,3,3));
			graph.addEdgeWithNodes(getEdge(3,3,3,5));
			graph.addEdgeWithNodes(getEdge(3,3,3,1));
			graph.addEdgeWithNodes(getEdge(3,3,5,3));
			graph.addEdgeWithNodes(getEdge(5,3,5,1));
			
		} catch (AddEdgeException ex) {
			ex.printStackTrace();
		}
		
		return graph;
	}
	
	public Graph<WeightedEdge> getTriangle() {
		Graph<WeightedEdge> graph = new Graph<WeightedEdge>();
		
		try {
			Edge edge = getEdge(0, 0, 0, 4);
			graph.addEdgeWithNodes(new WeightedEdge(edge, SteinersAlgorithm.getEdgeLength(edge)));
			
			edge = getEdge(0, 4, 3, 0);
			graph.addEdgeWithNodes(new WeightedEdge(edge, SteinersAlgorithm.getEdgeLength(edge)));
			
			edge = getEdge(0, 0, 3, 0);
			graph.addEdgeWithNodes(new WeightedEdge(edge, SteinersAlgorithm.getEdgeLength(edge)));
			
		} catch (AddEdgeException ex) {
			ex.printStackTrace();
		}
		
		return graph;
	}
}
