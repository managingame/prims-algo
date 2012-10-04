/**
 * 
 */
package lt.refactory.primsAlgo.graph.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.graph.exception.RemoveNodeException;

import org.junit.Test;
import org.w3c.dom.NodeList;

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
	@Test
	public void testAddEdge() throws AddEdgeException, AddNodeException {
		
		Graph<Edge> graph = new Graph<>();
		List <Edge> correctGraph = new ArrayList<>();
		System.out.println(edgeList.toString());
		for(int i=0; i< edgeList.size(); i++){
			graph.addEdge(edgeList.get(i));
			
			
			if(!correctGraph.contains(edgeList.get(i))){
				correctGraph.add(edgeList.get(i));
			}
			
		}
		
		List<Edge> edgeList = graph.getEdgeList();
		assertTrue(edgeList.size()==correctGraph.size());
		
		// Ok we got first error 
		// fifthEdge(starts at [2;3]ends at[2;1])and sixthEdge(starts at[2;1]ends at[2;3])
		// are different edges and both were put into graph  
		
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#removeEdge(lt.refactory.primsAlgo.graph.Edge)}.
	 */
	@Test
	public void testRemoveEdge() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#getNearEdges(lt.refactory.primsAlgo.graph.Node)}.
	 */
	@Test
	public void testGetNearEdgesNode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#getNearEdges(lt.refactory.primsAlgo.graph.Edge)}.
	 */
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
		BigDecimal bigNumber1 = new BigDecimal(1.0);
		BigDecimal bigNumber2 = new BigDecimal(2.0);
		BigDecimal bigNumber3 = new BigDecimal(3.0);
		BigDecimal bigNumber4 = new BigDecimal(3.0);
		BigDecimal bigNumber5 = new BigDecimal(-4.0);
		BigDecimal bigNumber6 = new BigDecimal(4.0);
		
		
		Node point_1 = new Node(bigNumber6, bigNumber1);
		Node point_2 = new Node(bigNumber5, bigNumber6);
		Node point_3 = new Node(bigNumber2, bigNumber3);
		Node point_4 = new Node(bigNumber2, bigNumber1);
		Node point_5 = new Node(bigNumber2, bigNumber1);
		
		Graph<Edge> graph = new Graph<Edge>();
		Edge firstEdge = new Edge(point_1, point_3);
		Edge thirdEdge = new Edge(point_2, point_3);
		Edge secondEdge = new Edge(point_1, point_4);	
		Edge fourthEdge = new Edge(point_3,point_4);
		
		graph.addNode(point_5);
		graph.addNode(point_4);
		graph.addNode(point_3);
		graph.addNode(point_2);
		graph.addNode(point_1);

		graph.addEdge(firstEdge);
		graph.addEdge(secondEdge);
		graph.addEdge(thirdEdge);
		graph.addEdge(fourthEdge);
		
		// Searching for nearNodes for point which goes as argument in the method
		List<Node> nearNodesList = graph.getNearNodes(point_4);
		
		// Second error appears here from point which was connected with 2 nodes ,
		// amount of near Nodes is 3 .should be 2  if he include himself then it is ok.
		assertTrue(nearNodesList.size()==2);
	}

}
