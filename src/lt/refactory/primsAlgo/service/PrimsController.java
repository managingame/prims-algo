package lt.refactory.primsAlgo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.enums.NodeType;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.service.algorithm.PrimsAlgorithm;
import lt.refactory.primsAlgo.service.algorithm.SteinersAlgorithm;
import lt.refactory.primsAlgo.service.algorithm.exceptions.AlgorithmException;
import lt.refactory.primsAlgo.service.algorithm.models.Circle;

public class PrimsController {
	
	private Graph<WeightedEdge> graph;
	
	public PrimsController(Graph<WeightedEdge> graph) {
		super();
		this.graph = graph;
	}

	public void clear(){
		graph.clear();
	}
	
	public void addNode(Node node) throws AddNodeException{
		graph.addNode(node);
	}
	
	public void solvePrimsAlgorithm(boolean withOnePoint) {
		if (graph.getNodeListSize() < 2){
			return;
		}
		
		List<Node> nodesToRemove = new ArrayList<Node>();
		for (Node node : graph.getNodeList()){
			if (node.getNodeType() == NodeType.STEINER){
				nodesToRemove.add(node);
			}
		}
		
		for (Node node : nodesToRemove) {
			graph.removeNodeWithEdges(node);
		}
		
		graph = Graph.<WeightedEdge>fullGraphFactory(graph.getNodeList());
		graph = PrimsAlgorithm.convertLengthToWeight(graph);
		
		if (withOnePoint) {
			graph = getSmallestTreeWithOnePoint(graph);
		} else {
			graph = PrimsAlgorithm.solve(graph);
		}
	}

	public Graph<WeightedEdge> getGraph() {
		return graph;
	}

	public void setGraph(Graph<WeightedEdge> graph) {
		this.graph = graph;
	}	
	
	public Graph<WeightedEdge> getSmallestTreeWithOnePoint(Graph<WeightedEdge> graph) {
		// will be used for graph size difference calculations
		Graph<WeightedEdge> smallestTreeFull = PrimsAlgorithm.solve(graph);
		// will be used for steiners algorithm - edges will be removed
		Graph<WeightedEdge> smallestTree = new Graph<WeightedEdge>(smallestTreeFull);
		
		// result graph
		Graph<WeightedEdge> smallestTreeWithSteinersPoint = new Graph<WeightedEdge>(smallestTreeFull);
		BigDecimal biggestDifference = BigDecimal.ZERO;
		try {
			
			while (smallestTree.getEdgeListSize() > 1) {
				WeightedEdge leave = SteinersAlgorithm.getGraphLeave(smallestTree);
				
				// if no more leaves are in graph return result
				if (leave == null) {
					return smallestTreeWithSteinersPoint;
				}
				
				WeightedEdge nearEdge = smallestTree.getNearEdges(leave).get(0);	// leave always have one near edge
				BigDecimal angleBetweenEdges = SteinersAlgorithm.getAngleBetweenTwoEdges(leave, nearEdge);
				
				if (angleBetweenEdges.compareTo(BigDecimal.valueOf(120)) == -1) {
					// Torichelli procedure for steiners point:
					Graph<WeightedEdge> triangle = SteinersAlgorithm.getTriangleWithWeights(leave, nearEdge);
					Graph<WeightedEdge> equilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(triangle);
					Circle circumscribedCircle = SteinersAlgorithm.getCircumscribedCircle(equilateralTriangle);
					WeightedEdge edgeThroughTriangles = SteinersAlgorithm.getEdgeThroughTriangles(triangle, equilateralTriangle);
					Node steinersPoint = SteinersAlgorithm.getSteinersPoint(edgeThroughTriangles, circumscribedCircle);
					
					// Calculate how much graph is shorter than initial graph
					Graph<WeightedEdge> changedGraph = SteinersAlgorithm.changeGraphEdges(smallestTreeFull, leave, nearEdge, steinersPoint);
					BigDecimal lengthDifference = SteinersAlgorithm.calculateGraphLengthsDifference(smallestTreeFull, changedGraph);
					
					if (lengthDifference.compareTo(biggestDifference) == 1) {
						smallestTreeWithSteinersPoint = new Graph<WeightedEdge>(changedGraph);
						biggestDifference = lengthDifference;
					}
				}
				smallestTree.removeEdge(leave);
			}
		} catch (AlgorithmException e) {
			e.printStackTrace();
		} catch (AddEdgeException e) {
			e.printStackTrace();
		}
		
		return smallestTreeWithSteinersPoint;
	}
}
