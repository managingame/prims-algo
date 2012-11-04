package lt.refactory.primsAlgo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.graph.exception.RemoveNodeException;

/**
 * Class to hold graph information
 * @author arminas
 *
 */
public class Graph<T extends Edge> {
	Set<Node> nodeList;
	Set<T> edgeList;
	
	public Graph(){
		this.nodeList = new LinkedHashSet<Node>();
		this.edgeList = new LinkedHashSet<T>();
	}
	
	public Graph(Graph<T> graph){
		this.nodeList = new LinkedHashSet<Node>(graph.nodeList);
		this.edgeList = new LinkedHashSet<T>(graph.edgeList);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Edge> Graph<T> fullGraphFactory(List<Node> nodeList) {
		Graph<T> result = new Graph<T>();

		try {
			result.addAllNodes(nodeList);

			for (int i = 0; i < nodeList.size(); i++) {
				for (int j = i+1; j < nodeList.size(); j++) {
					Node firstNode = nodeList.get(i);
					Node secondNode = nodeList.get(j);
					result.addEdge((T) new Edge(firstNode, secondNode));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public List<Node> getNodeList() {
 		return new ArrayList<Node>(nodeList);
	}
	public List<T> getEdgeList() {
		return new ArrayList<T>(edgeList);
	}
	
	public void addNode(Node node) throws AddNodeException {
		nodeList.add(node);
	}
	
	/**
	 * Removes node from a graph.
	 * You can't remove node when:
	 * <li>Node has edges</li>
	 * @param name name of the node to remove
	 */
	public void removeNode(Node node) throws RemoveNodeException {
		for (T edge : edgeList) {
			if (edge.getStart().equals(node) || edge.getEnd().equals(node)) {
				throw new RemoveNodeException("Node has edges");
			}
		}
		nodeList.remove(node);
	}
		
	
	/**
	 * Adds new edge to graph. You can't add new edge when:
	 * <li>Edge start or end points does not exist in graph</li>
	 * @param edge
	 * @throws AddEdgeException 
	 */
	public void addEdge(T edge) throws AddEdgeException {
		if (!nodeList.contains(edge.getStart()) || !nodeList.contains(edge.getEnd())){
			throw new AddEdgeException("Nodes at start or end of the edge does not exist");
		}
		edgeList.add(edge);
	}
	
	/**
	 * Adds new edge to graph.
	 * @param edge
	 * @throws AddEdgeException 
	 */
	@SuppressWarnings("unchecked")
	public void addEdge(Node nodeA,Node nodeB) throws AddEdgeException {
		nodeList.add(nodeA);
		nodeList.add(nodeB);
		edgeList.add((T) new Edge(nodeA,nodeB));
	}
	
	/**
	 * Adds new edge to graph. If start and end points does not exist in graph
	 * there are added automatically
	 * @param edge edge to add
	 * @throws AddEdgeException when AddEdge() conditions are not met
	 * @see {@link Graph#addEdge(Edge)}
	 */
	public void addEdgeWithNodes(T edge) throws AddEdgeException {
		Node nodeStart = edge.getStart();
		Node nodeEnd = edge.getEnd();
		nodeList.add(nodeStart);
		nodeList.add(nodeEnd);
		this.addEdge(edge);
	}
	
	public void addAllNodes(List<Node> nodeList) throws AddNodeException {
		for (Node node : nodeList) {
			this.addNode(node);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addAll(Graph<T> graph) throws AddEdgeException{
		for (Edge edge : graph.getEdgeList()){
			this.addEdgeWithNodes((T) edge);
		}
	}
	
	public void addAllEdges(List<T> edgeList) throws AddEdgeException{
		for (T edge: edgeList){
			this.addEdge(edge);
		}
	}
	
	public void addAllEdgesWithNodes(List<T> edgeList) throws AddEdgeException{
		for (T edge: edgeList){
			this.addEdgeWithNodes(edge);
		}
	}
	
	public void removeEdge(T edge) {
		edgeList.remove(edge);
	}
	
	
	public boolean containsEdge(T edge){
		return edgeList.contains(edge);
	}
	
	public boolean containsNode(Node node){
		return nodeList.contains(node);
	}
	
	public List<T> getNearEdges(Node node) {
		if (!nodeList.contains(node)) {
			return Collections.emptyList();
		}
		Set<T> resultList = new HashSet<T>();
		for (T edge : edgeList) {
			if (edge.getStart().equals(node) || edge.getEnd().equals(node)) {
				resultList.add(edge);
			}
		}
		return new ArrayList<T>(resultList);
	}
	
	public List<T> getNearEdges(T edge){
		List<T> firstNodeNearEdgeList = getNearEdges(edge.getStart());
		List<T> secondNodeNearEdgeList = getNearEdges(edge.getEnd());

		Set<T> result = new HashSet<T>();
		result.addAll(firstNodeNearEdgeList);
		result.addAll(secondNodeNearEdgeList);
		// remove the edge itself
		result.remove(edge);
		return new ArrayList<T>(result);
		
	}
	
	public List<Node> getNearNodes(Node node) {
		Set<Node> result = new HashSet<Node>();
		for (T nearEdge : getNearEdges(node)) {
			result.add(nearEdge.getStart());
			result.add(nearEdge.getEnd());
		}
		// remove the node itself
		result.remove(node);

		return new ArrayList<Node>(result);
	}

	public int getNodeListSize(){
		return nodeList.size();
	}
	
	public int getEdgeListSize(){
		return edgeList.size();
	}
	
	/**
	 * Removes all edges and all nodes from graph
	 */
	public void clear() {
		nodeList.clear();
		edgeList.clear();
	}

}
