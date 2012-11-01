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
	
	public Graph<WeightedEdge> getTriangle(int which) {
		Graph<WeightedEdge> graph = new Graph<WeightedEdge>();
		
		try {
			Edge firstEdge = null;
			Edge secondEdge = null;
			Edge thirdEdge = null;
			
			switch(which) {
			case 1:
				// first one vertical line
				firstEdge = getEdge(0, 0, 0, 4);
				secondEdge = getEdge(0, 4, 3, 0);
				thirdEdge = getEdge(0, 0, 3, 0);
				break;
			case 2:
				// first one diagonal line
				firstEdge = getEdge(0, 4, 3, 0);
				secondEdge = getEdge(0, 0, 0, 4);
				thirdEdge = getEdge(0, 0, 3, 0);
				break;
			case 3:
				// first one horizontal line
				firstEdge = getEdge(0, 0, 3, 0);
				secondEdge = getEdge(0, 4, 3, 0);
				thirdEdge = getEdge(0, 0, 0, 4);
				break;
			}
			
			graph.addEdgeWithNodes(new WeightedEdge(firstEdge, SteinersAlgorithm.getEdgeLength(firstEdge)));
			graph.addEdgeWithNodes(new WeightedEdge(secondEdge, SteinersAlgorithm.getEdgeLength(secondEdge)));
			graph.addEdgeWithNodes(new WeightedEdge(thirdEdge, SteinersAlgorithm.getEdgeLength(thirdEdge)));
			
		} catch (AddEdgeException ex) {
			ex.printStackTrace();
		}
		
		return graph;
	}
}
