/**
 * 
 */
package lt.refactory.primsAlgo.graph.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;

import org.junit.Test;

/**
 * @author Arminas
 *
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
		Graph<Edge> graph = new Graph<Edge>();
		
		// creating point 
		Node point_1 = new Node(bigNumber1, bigNumber2);
		
		// adding point to graph
		graph.addNode(point_1);
		
		assertTrue(graph.containsNode(point_1));
		
		
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#removeNode(lt.refactory.primsAlgo.graph.Node)}.
	 */
	@Test
	public void testRemoveNode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#addEdge(lt.refactory.primsAlgo.graph.Edge)}.
	 */
	@Test
	public void testAddEdge() {
		fail("Not yet implemented");
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
