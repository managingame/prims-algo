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
public class Graph<T extends Edge> {
	Map<String,Node> nodeList;
	Map<String,T> edgeList;
	
	public List<Node> getNodeList() {
 		return new ArrayList<Node>(nodeList.values());
	}
	public List<T> getEdgeList() {
		return new ArrayList<T>(edgeList.values());
	}
	
	public void addNode(Node node) throws AddNodeException {
		throw new UnsupportedOperationException ();	
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
		throw new UnsupportedOperationException ();		
	}
	/**
	 * Adds new edge to graph. You can't add new edge when:
	 * <li>Edge start or end points does not exist in graph</li>
	 * <li>Edge has no name</li>
	 * @param edge
	 */
	public void addEdge(T edge) throws AddEdgeException {
		throw new UnsupportedOperationException ();	
	}
	
	public void removeEdge(T edge) {
		throw new UnsupportedOperationException ();	
	}
	
	
	public Node getNode(String name){
		return null;
	}
	
	public Edge getEdge(String name){
		return null;
	}
	
	public boolean containsEdge(T edge){
		return false;
	}
	
	public boolean containsNode(T node){
		return false;
	}
	
	
	

}
