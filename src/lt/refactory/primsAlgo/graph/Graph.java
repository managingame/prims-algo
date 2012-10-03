package lt.refactory.primsAlgo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
		this.nodeList = new HashSet<Node>();
		this.edgeList = new HashSet<T>();
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
	 */
	public void addEdge(T edge) throws AddEdgeException {
		if (!nodeList.contains(edge.getStart()) || !nodeList.contains(edge.getEnd())){
			throw new AddEdgeException("Nodes at start or end of the edge does not exist");
		}
		edgeList.add(edge);
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
		return new ArrayList<T>(result);
		
	}
	
	public List<Node> getNearNodes(Node node){
		Set<Node> result = new HashSet<Node>();
		for (T nearEdge: getNearEdges(node)){
			result.add(nearEdge.getStart());
			result.add(nearEdge.getEnd());
		}
		
		//remove the node itself
		result.remove(node);
		
		return new ArrayList<Node>(result);
	}
	
	public int getNodeListSize(){
		return nodeList.size();
	}
	
	public int getEdgeListSize(){
		return edgeList.size();
	}
	
	
	

}
