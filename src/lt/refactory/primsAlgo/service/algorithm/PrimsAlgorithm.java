package lt.refactory.primsAlgo.service.algorithm;

import java.util.Stack;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;

public class PrimsAlgorithm {

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
