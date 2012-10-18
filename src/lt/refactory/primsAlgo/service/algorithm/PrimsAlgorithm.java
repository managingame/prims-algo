package lt.refactory.primsAlgo.service.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;

public class PrimsAlgorithm {

	
	public <T extends WeightedEdge> Graph<T> solve (Graph<T> graph){
		List<T> result = new ArrayList<T>();
		List<T> sortedEdgeList = graph.getEdgeList();
		Collections.sort(sortedEdgeList);
		
		T lightestEdge = sortedEdgeList.get(0);
		result.add(lightestEdge);
		
		int nodesLeft = graph.getNodeListSize();
		nodesLeft -= 2;  //already connected two nodes with lightestEdge
		
		while (nodesLeft >0){
			List<T> nearEdgeList = graph.getNearEdges(lightestEdge);
			Collections.sort(nearEdgeList);
			for (T nearEdge : nearEdgeList){
				//testing if this edge would create cycle
				Graph<T> tempGraph = new Graph<T>();
				try {
					tempGraph.addAllEdgesWithNodes(result);
					tempGraph.addEdge(nearEdge);
				} catch (AddEdgeException e) {
					e.printStackTrace();
				}
				if (!hasCycle(tempGraph)){
					result.add(nearEdge);
					nodesLeft--;
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
	public static boolean hasCycle(Graph<? extends Edge> graph){
		Stack<Node> result = new Stack<Node>();
		Node start = graph.getNodeList().get(0);
		return depthFirstSearch(start,result,graph);
	}
	
	private static boolean depthFirstSearch(Node start, Stack<Node> result,
			Graph<?> graph) {
		// ensure we're not stuck in a cycle
		if (result.contains(start)) {
			return true;
		}
		result.push(start);

		// check if we've found the goal
		if (result.size() == graph.getNodeListSize()) {
			return false;
		}
		// expand each child node in order, returning if we find the goal
		for (Node node : graph.getNearNodes(start)) {
			if (depthFirstSearch(node, result, graph)) {
				return true;
			}
		}
		// No path was found
		result.pop();
		return false;
	}
}
