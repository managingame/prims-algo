/**
 * 
 */
package lt.refactory.primsAlgo.graph.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.graph.exception.RemoveNodeException;

import org.junit.Test;

/**
 * @author Arminas
 * @Tester Egidijus
 */
public class GraphClassTest {

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#addNode(lt.refactory.primsAlgo.graph.Node)}.
	 * @throws AddNodeException 
	 */
	@Test
	public void testAddNode() throws AddNodeException {
		BigDecimal bigNumber1 = new BigDecimal(1.0);
		BigDecimal bigNumber2 = new BigDecimal(2.0);
		BigDecimal bigNumber3 = new BigDecimal(3.0);
		BigDecimal bigNumber4 = new BigDecimal(3.0);
		BigDecimal bigNumber5 = new BigDecimal(-4.0);
		BigDecimal bigNumber6 = new BigDecimal(4.0);
		
		
		//Adding multiplier for fun mathematics 
		BigDecimal multiplier = new BigDecimal(1.11111);
		//Big decimal is much more better than double or float.
		
		Graph<Edge> graph = new Graph<Edge>();
		
		// creating points
		// Tried to multiply number with 1.11111 in order to create diference numbers .
		// Didn't worked
		Node point_1 = new Node((bigNumber1.add(bigNumber3).multiply(multiplier)), bigNumber2);
		Node point_11 = new Node((bigNumber1.add(bigNumber3).multiply(multiplier)), bigNumber2);
		
		// two objects with same value but different reference .. didn't worked either
		Node point_2 = new Node(bigNumber3, bigNumber3);
		Node point_3 = new Node(bigNumber4, bigNumber4);
		
		// Adding 2 points : 1 is |-4;-4| and second is |4;4| 
		// in addition both of them are equal and set  
		Node point_4 = new Node(bigNumber5.abs(), bigNumber5.abs());
		Node point_5 = new Node(bigNumber6, bigNumber6);
		
		// adding points to graph
		graph.addNode(point_1);
		graph.addNode(point_2);
		
		// different obj with same value
		graph.addNode(point_3);
		graph.addNode(point_11);
		
		//  
		graph.addNode(point_5);
		graph.addNode(point_4);
		
		
		List<Node> nodeList = graph.getNodeList();
		
		assertTrue(nodeList.contains(point_1));
		
		// both of them are working fine which means they are taking values instead of references
		// I so hate C++ for that ...
		assertTrue(nodeList.contains(point_5));
		assertTrue(nodeList.contains(point_4));
		
		 
		assertTrue(nodeList.size() == 3); 
		
		// Conclusions : addNode method works fine for now.
		
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#removeNode(lt.refactory.primsAlgo.graph.Node)}.
	 * @throws AddNodeException 
	 * @throws RemoveNodeException 
	 */
	@Test
	public void testRemoveNode() throws AddNodeException, RemoveNodeException {
		BigDecimal bigNumber1 = new BigDecimal(1.0);
		BigDecimal bigNumber2 = new BigDecimal(2.0);
		BigDecimal bigNumber3 = new BigDecimal(3.0);
		Graph<Edge> graph = new Graph<Edge>();
		
		Node firstPoint  = new Node(bigNumber1, bigNumber3);
		Node secondPoint = new Node(bigNumber2, bigNumber1);
		Node thirdPoint = new Node(bigNumber1, bigNumber3);
		
		graph.addNode(firstPoint);
		graph.addNode(secondPoint);
		graph.addNode(thirdPoint);
		
		graph.removeNode(secondPoint);
		graph.removeNode(firstPoint);
		List<Node> pointsList = graph.getNodeList();
		assertTrue(pointsList.size()==0);
		
		
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#addEdge(lt.refactory.primsAlgo.graph.Edge)}.
	 */
	@Test
	public void testAddEdge() {
		
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
	 */
	@Test
	public void testGetNearNodes() {
		fail("Not yet implemented");
	}

}
