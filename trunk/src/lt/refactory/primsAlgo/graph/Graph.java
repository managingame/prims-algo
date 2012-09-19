package lt.refactory.primsAlgo.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.graph.exception.RemoveNodeException;


/**
 * Class to hold graph information
 * @author arminas
 *
 */
public class Graph {
	Map<String,Node> nodeList;
	Map<String,Edge> edgeList;
	
	public List<Node> getNodeList() {
 		return new ArrayList<Node>(nodeList.values());
	}
	public List<Edge> getEdgeList() {
		return new ArrayList<Edge>(edgeList.values());
	}
	
	public void addNode(Node node) throws AddNodeException {

	}
	
	/**
	 * Removes node with given name from a graph.
	 * You can't remove node when:
	 * <li>Node has edges</li>
	 * @param name name of the node to remove
	 */
	public void removeNode(Node node) throws RemoveNodeException{
		throw new UnsupportedOperationException ();
	}
	
	/**
	 * Removes node with given name from a graph.
	 * You can't remove node when:
	 * <li>Node has edges</li>
	 * @param name name of the node to remove
	 */
	public void removeNode(String name) throws RemoveNodeException{
		
	}
	/**
	 * Adds new edge to graph. You can't add new edge when:
	 * <li>Edge start or end points does not exist in graph</li>
	 * <li>Edge has no name</li>
	 * @param edge
	 */
	public void addEdge(Edge edge) throws AddEdgeException {
		
	}
	
	public void removeEdge(Edge edge) {
		
	}
	
	public void removeEdge(String edgeName){
		
	}
	
	
	public Node getNode(String name){
		return null;
	}
	
	public Edge getEdge(String name){
		return null;
	}
	
	public boolean containsEdge(Edge edge){
		return false;
	}
	
	public boolean containsNode(Node node){
		return false;
	}
	
	
	

}
