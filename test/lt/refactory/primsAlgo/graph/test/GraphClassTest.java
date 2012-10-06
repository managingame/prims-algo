/**
 * 
 */
package lt.refactory.primsAlgo.graph.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.graph.exception.RemoveNodeException;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Arminas
 * @Tester Egidijus
 */
public class GraphClassTest {
	static List<Node> nodeList ;
	static List<Edge> edgeList ;
	
	static{
		nodeList = TestObjectsFactory.nodesFactory();
		edgeList = TestObjectsFactory.edgeFactory();		
	}
	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#addNode(lt.refactory.primsAlgo.graph.Node)}.
	 * @throws AddNodeException 
	 */
	@Test
	public void testAddNode() throws AddNodeException {
		Graph<Edge> graph = new Graph<>();
		List<Node> correctGraphList = new ArrayList<>();
		
		// putting nodes into graph and into correctGraph 
		// If Both of them will have same amount of nodes , test
		// will be approved
		
		for(int i=0; i < nodeList.size(); i++){
			graph.addNode(nodeList.get(i));
			
			if(!correctGraphList.contains(nodeList.get(i))){
				correctGraphList.add(nodeList.get(i));
			}	
		}
		
		
		// nodeList listed below is this method object not static one.
		List<Node> nodeList = graph.getNodeList();           
		assertTrue(nodeList.size()==correctGraphList.size());
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(0)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(1)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(2)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(3)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(4)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(5)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(6)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(7)));
	    // Passed the test : method addNode works properly.
		 
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#removeNode(lt.refactory.primsAlgo.graph.Node)}.
	 * @throws AddNodeException 
	 * @throws RemoveNodeException 
	 */
	@Test
	
	public void testRemoveNode() throws AddNodeException, RemoveNodeException {
		Graph<Edge> graph = new Graph<>();
		List<Node> correctGraph = new ArrayList<>();
		
		for(int i=0; i < nodeList.size();i++){
			graph.addNode(nodeList.get(i));
			if (!correctGraph.contains(nodeList.get(i))){
				correctGraph.add(nodeList.get(i));
			}
			
		}
		
		graph.removeNode(nodeList.get(0));
		correctGraph.remove(0);
		

		assertFalse(graph.containsNode(nodeList.get(0)));
		assertFalse(correctGraph.contains(nodeList.get(0)));
	
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#addEdge(lt.refactory.primsAlgo.graph.Edge)}.
	 * @throws AddEdgeException 
	 * @throws AddNodeException 
	 */
	@Test(timeout=1000)
	public void testAddEdge() throws AddEdgeException ,AddNodeException {
		
		Graph<Edge> graph = new Graph<>();
		
		for (int h=0; h < nodeList.size(); h++ ){
			graph.addNode(nodeList.get(h));
		}
		
		for(int i=0; i< edgeList.size(); i++){
			graph.addEdge(edgeList.get(i));
		}
		
		List<Edge> edgeList = graph.getEdgeList();
		assertTrue(edgeList.size()==11);
		
		// Method works fine .
		
		
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#removeEdge(lt.refactory.primsAlgo.graph.Edge)}.
	 * @throws AddNodeException 
	 * @throws AddEdgeException 
	 * @throws RemoveNodeException 
	 */
	
	@Test
	public void testRemoveEdge() throws AddNodeException, AddEdgeException, RemoveNodeException {
			Graph<Edge> graph = new Graph<>();
		
		for (int h=0; h < nodeList.size(); h++ ){
			graph.addNode(nodeList.get(h));
		}
		
		for(int i=0; i< edgeList.size(); i++){
			graph.addEdge(edgeList.get(i));
		}
		graph.removeEdge(edgeList.get(0));
		graph.removeEdge(edgeList.get(2));
		
		// First i removes edge[0] and edge[2] from list ,
		// then tested if they exists in graph.
		// because this method was passed by test 
		// removing works fine 
		assertFalse(graph.containsEdge(edgeList.get(0)));
		assertFalse(graph.containsEdge(edgeList.get(2)));
		
		
		// Graph has 11 edges , if i remove 2 of them ,
		// size of graph should be 9 .
		assertTrue(graph.getEdgeListSize()==9);
		
		//Lets remove 1 node and see if the size of edges changed
		
		/* Method which throws an exception 
		 * graph.removeNode(nodeList.get(8));
		 */
		
		// Thrown RemoveNodeException : Node has edges .
		// Dear Arminas , please upgrade method
		// removeNode(Node node) .
		// ^.^ . ty
		
		
		
		
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#getNearEdges(lt.refactory.primsAlgo.graph.Node)}.
	 */
	@Test
	@Ignore("I dont fully understand this method" +
			"I think its same to testGetNearNodes()")
	public void testGetNearEdgesNode() {
		
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#getNearEdges(lt.refactory.primsAlgo.graph.Edge)}.
	 */
	@Ignore("Method isnt ready to be run")
	@Test
	public void testGetNearEdgesT() {
		fail("Not yet implemented");
		
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#getNearNodes(lt.refactory.primsAlgo.graph.Node)}.
	 * @throws AddNodeException 
	 * @throws AddEdgeException 
	 */
	@Test
	public void testGetNearNodes() throws AddNodeException, AddEdgeException {
		Graph<Edge> graph = new Graph<>();
		
		for (int h=0; h < nodeList.size(); h++ ){
			graph.addNode(nodeList.get(h));
		}
		
		for(int i=0; i< edgeList.size(); i++){
			graph.addEdge(edgeList.get(i));
		}
		// Assume this as true .  
		List<Node> nearNodeAList = graph.getNearNodes(nodeList.get(0));
		List<Node> nearNodeBList= graph.getNearNodes(nodeList.get(1));
		assertTrue(nearNodeAList.size()==4);
		assertTrue(nearNodeBList.size()==2);
		
		
		
		graph.removeEdge(new Edge(nodeList.get(0),nodeList.get(6)));
		graph.removeEdge(new Edge(nodeList.get(1),nodeList.get(6)));
		nearNodeAList = graph.getNearNodes(nodeList.get(0));
		nearNodeBList = graph.getNearNodes(nodeList.get(1));
		
		// Removed 1 edge for each checkNode i am testing 
		// size for those checkNodes decrease by 1
		assertTrue(nearNodeAList.size()==3);
		assertTrue(nearNodeBList.size()==1);
	}
	
	@Test
	public void testEdgeHashCodeAndEquals(){
		
		Edge edge1 = edgeList.get(3);
		Edge edge2 = edgeList.get(4);
		
		assertTrue(edge1.equals(edge2));
		assertTrue(edge1.hashCode() == edge2.hashCode());
	}


}
