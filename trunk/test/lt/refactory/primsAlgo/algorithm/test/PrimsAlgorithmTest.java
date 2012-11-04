/**
 * 
 */
package lt.refactory.primsAlgo.algorithm.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.graph.test.TestObjectsFactory;
import lt.refactory.primsAlgo.service.algorithm.PrimsAlgorithm;

import org.junit.Test;

/**
 * @author Arminas
 *
 */
public class PrimsAlgorithmTest {

	@Test
	public void solveTest() {
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.add(TestObjectsFactory.enumNode.A.getNode());
		nodeList.add(TestObjectsFactory.enumNode.B.getNode());
		nodeList.add(TestObjectsFactory.enumNode.C.getNode());
		nodeList.add(TestObjectsFactory.enumNode.D.getNode());
		nodeList.add(TestObjectsFactory.enumNode.E.getNode());

		Graph<WeightedEdge> graph = Graph.<WeightedEdge>fullGraphFactory(nodeList);
		graph = PrimsAlgorithm.convertLengthToWeight(graph);
		
		graph = PrimsAlgorithm.solve(graph);

		assertTrue(true);
		
		
	}
	
	@Test
	public void hasCycleTest() {
		// create graph with test
		Graph<Edge> graph = new Graph<Edge>();
		Node nodeA = TestObjectsFactory.enumNode.A.getNode();
		Node nodeB = TestObjectsFactory.enumNode.B.getNode();
		Node nodeC = TestObjectsFactory.enumNode.C.getNode();
		
		try {
			graph.addEdge(nodeA, nodeB);
			graph.addEdge(nodeA, nodeC);
			graph.addEdge(nodeB,nodeC);
		} catch (AddEdgeException e) {
			fail("AddEdgeException thrown");
			e.printStackTrace();
		}
		
		assertTrue(PrimsAlgorithm.hasCycle(graph));
		
		graph.removeEdge(new Edge(nodeA,nodeB));
		
		assertFalse(PrimsAlgorithm.hasCycle(graph));		
	}
	
	@Test
	public void removeNodesWithoutEdgesTest(){
		Graph<Edge> baseGraph = new Graph<Edge>();
		Node nodeA = TestObjectsFactory.enumNode.A.getNode();
		Node nodeB = TestObjectsFactory.enumNode.B.getNode();
		Node nodeC = TestObjectsFactory.enumNode.C.getNode();
		
		try {
			baseGraph.addNode(nodeA);
			baseGraph.addNode(nodeB);
			baseGraph.addNode(nodeC);
		} catch (AddNodeException e) {
			fail("AddNodeException thrown");
			e.printStackTrace();
		}
		
		Graph<Edge> graph1 = new Graph<Edge>(baseGraph);
		graph1 = PrimsAlgorithm.removeNodesWithoutEdges(graph1);
		assertTrue("Total node: " + graph1.getNodeListSize(),graph1.getNodeListSize() == 0);
		
		Graph<Edge> graph2 = new Graph<Edge>(baseGraph);
		try {
			graph2.addEdge(nodeA,nodeB);
		} catch (AddEdgeException e) {
			fail("AddEdgeException thrown");
			e.printStackTrace();
		}
		graph2 = PrimsAlgorithm.removeNodesWithoutEdges(graph2);
		assertTrue("Total node: " + graph2.getNodeListSize(),graph2.getNodeListSize() == 2);
		

	}
}
