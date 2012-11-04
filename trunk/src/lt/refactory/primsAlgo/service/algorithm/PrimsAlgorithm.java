package lt.refactory.primsAlgo.service.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.graph.exception.RemoveNodeException;

public class PrimsAlgorithm {

	
	public static <T extends WeightedEdge> Graph<T> solve (Graph<T> graph){
		List<T> result = new ArrayList<T>();
		List<T> sortedEdgeList = graph.getEdgeList();
		Collections.sort(sortedEdgeList);
		
		T lightestEdge = sortedEdgeList.get(0);
		result.add(lightestEdge);
		
		int nodesLeft = graph.getNodeListSize();
		nodesLeft -= 2;  //already connected two nodes with lightestEdge
		
		while (nodesLeft >0){
			List<T> nearEdgeList = graph.getNearEdges(lightestEdge);
			nearEdgeList.removeAll(result);
			Collections.sort(nearEdgeList);
			for (T nearEdge : nearEdgeList){
				//testing if this edge would create cycle
				Graph<T> tempGraph = new Graph<T>();
				try {
					tempGraph.addAllEdgesWithNodes(result);
					tempGraph.addEdgeWithNodes(nearEdge);
				} catch (AddEdgeException e) {
					e.printStackTrace();
				}
				if (!hasCycle(tempGraph)){
					result.add(nearEdge);
					nodesLeft--;
					lightestEdge = nearEdge;
					break;
				}
				
			}
		}
		Graph<T> resultGraph = new Graph<T>();
		try {
			resultGraph.addAllEdgesWithNodes(result);
		} catch (AddEdgeException e) {
			e.printStackTrace();
		}
		return resultGraph;
		
	}
	
	public static <T extends WeightedEdge> Graph<T> convertLengthToWeight(Graph<T> graph ){
		Graph<T> resultGraph = new Graph<T>();
		try {
			resultGraph.addAllNodes(graph.getNodeList());
		} catch (AddNodeException e) {
			e.printStackTrace();
		}
		
		for (Edge edge : graph.getEdgeList()){
			BigDecimal length = lengthBetweenNodes(edge.getStart(), edge.getEnd());
			@SuppressWarnings("unchecked")
			T edgeWithWeight = (T) new WeightedEdge(edge,length);
			
			try {
				resultGraph.addEdge(edgeWithWeight);
			} catch (AddEdgeException e) {
				e.printStackTrace();
			}
		}
		return resultGraph;
		
	}
	
	public static BigDecimal lengthBetweenNodes(Node a,Node b){
		BigDecimal x1 = a.getPointX();
		BigDecimal x2 = b.getPointX();
		
		//TODO: finish
		BigDecimal y1 = a.getPointY();
		BigDecimal y2 = b.getPointY();
		
		return 	x1.subtract(x2).pow(2).add(y1.subtract(y2).pow(2));


	}
	public static <T extends Edge> boolean hasCycle(Graph<T> graph){
		Stack<Node> result = new Stack<Node>();
		Node start = graph.getNodeList().get(0);
		
		Graph<T> graphToTest = PrimsAlgorithm.removeNodesWithoutEdges(graph);
		return depthFirstSearch(start,null,result,graphToTest);
	}
	
	private static boolean depthFirstSearch(Node start, Node previous,Stack<Node> result,
			Graph<?> graph) {
		// ensure we're not stuck in a cycle
		if (result.contains(start)) {
			return true;
		}
		result.push(start);
		
		List<Node> nearNodeList = graph.getNearNodes(start);
		nearNodeList.remove(previous); // remove node, that we came from

		// check if we've found the goal
		if (result.size() == graph.getNodeListSize()) {
			for (Node resultNode: result){
				if (nearNodeList.contains(resultNode)){
					return true;
				}
			}
			return false;
		}
				
		// expand each child node in order, returning if we find the goal
		for (Node node : nearNodeList) {
			if (depthFirstSearch(node,start, result, graph)) {
				return true;
			}
		}
		// No path was found
		result.pop();
		return false;
	}
	
	public static <T extends Edge> Graph<T> removeNodesWithoutEdges(Graph<T> graph){
		Graph<T> result = new Graph<T>(graph);
		List<Node> nodeList = result.getNodeList();
		for (Node node : nodeList){
			if (graph.getNearEdges(node).size() == 0){
				try {
					result.removeNode(node);
				} catch (RemoveNodeException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
		
	}
}
