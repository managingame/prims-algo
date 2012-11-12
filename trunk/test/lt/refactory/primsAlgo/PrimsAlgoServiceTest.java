package lt.refactory.primsAlgo;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.service.PrimsAlgoService;
import lt.refactory.primsAlgo.service.algorithm.SteinersAlgorithm;

import org.junit.Test;

public class PrimsAlgoServiceTest {

	@Test
	public void firstTest() {
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.add(new Node(BigDecimal.valueOf(1), BigDecimal.valueOf(1)));
		nodeList.add(new Node(BigDecimal.valueOf(1), BigDecimal.valueOf(4)));
		nodeList.add(new Node(BigDecimal.valueOf(5), BigDecimal.valueOf(1)));
		
		Graph<Edge> graph = Graph.<Edge>fullGraphFactory(nodeList);
		Graph<WeightedEdge> weightedGraph = SteinersAlgorithm.getWeightedGraph(graph);

		weightedGraph = PrimsAlgoService.getSmallestTreeWithOnePoint(weightedGraph);
		
		Node steinersPoint = weightedGraph.getSteinersPoint();
		
		assertTrue(steinersPoint != null);
		assertTrue(steinersPoint.getPointX().compareTo(BigDecimal.valueOf(1.6)) == 1);
		assertTrue(steinersPoint.getPointX().compareTo(BigDecimal.valueOf(1.8)) == -1);
		
		assertTrue(steinersPoint.getPointY().compareTo(BigDecimal.valueOf(1.7)) == 1);
		assertTrue(steinersPoint.getPointY().compareTo(BigDecimal.valueOf(1.8)) == -1);
	}
	
	@Test
	public void secondTest() {
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.add(new Node(BigDecimal.valueOf(2), BigDecimal.valueOf(2)));
		nodeList.add(new Node(BigDecimal.valueOf(1), BigDecimal.valueOf(4)));
		nodeList.add(new Node(BigDecimal.valueOf(6), BigDecimal.valueOf(3)));
		
		Graph<Edge> graph = Graph.<Edge>fullGraphFactory(nodeList);
		Graph<WeightedEdge> weightedGraph = SteinersAlgorithm.getWeightedGraph(graph);

		weightedGraph = PrimsAlgoService.getSmallestTreeWithOnePoint(weightedGraph);
		
		Node steinersPoint = weightedGraph.getSteinersPoint();
		
		assertTrue(steinersPoint != null);
		assertTrue(steinersPoint.getPointX().compareTo(BigDecimal.valueOf(2.1)) == 1);
		assertTrue(steinersPoint.getPointX().compareTo(BigDecimal.valueOf(2.3)) == -1);
		
		assertTrue(steinersPoint.getPointY().compareTo(BigDecimal.valueOf(2.4)) == 1);
		assertTrue(steinersPoint.getPointY().compareTo(BigDecimal.valueOf(2.5)) == -1);
	}
	
	@Test
	public void thirdTest() {
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.add(new Node(BigDecimal.valueOf(2), BigDecimal.valueOf(2)));
		nodeList.add(new Node(BigDecimal.valueOf(1), BigDecimal.valueOf(4)));
		nodeList.add(new Node(BigDecimal.valueOf(6), BigDecimal.valueOf(3)));
		nodeList.add(new Node(BigDecimal.valueOf(7), BigDecimal.valueOf(3)));
		nodeList.add(new Node(BigDecimal.valueOf(7), BigDecimal.valueOf(4)));
		nodeList.add(new Node(BigDecimal.valueOf(8), BigDecimal.valueOf(9)));
		
		Graph<Edge> graph = Graph.<Edge>fullGraphFactory(nodeList);
		Graph<WeightedEdge> weightedGraph = SteinersAlgorithm.getWeightedGraph(graph);

		weightedGraph = PrimsAlgoService.getSmallestTreeWithOnePoint(weightedGraph);
		
		Node steinersPoint = weightedGraph.getSteinersPoint();
		
		assertTrue(steinersPoint != null);
		assertTrue(steinersPoint.getPointX().compareTo(BigDecimal.valueOf(2.1)) == 1);
		assertTrue(steinersPoint.getPointX().compareTo(BigDecimal.valueOf(2.3)) == -1);
		
		assertTrue(steinersPoint.getPointY().compareTo(BigDecimal.valueOf(2.4)) == 1);
		assertTrue(steinersPoint.getPointY().compareTo(BigDecimal.valueOf(2.5)) == -1);
	}
	
	@Test
	public void testLengths() {
		Edge firstEdge = new Edge(new Node(BigDecimal.valueOf(2), BigDecimal.valueOf(2)), 
				new Node(BigDecimal.valueOf(6), BigDecimal.valueOf(6)));
		
		Edge secondEdge = new Edge(new Node(BigDecimal.valueOf(2), BigDecimal.valueOf(2)), 
				new Node(BigDecimal.valueOf(7), BigDecimal.valueOf(4)));
		
		BigDecimal firstWeight = SteinersAlgorithm.getEdgeLength(firstEdge);
		BigDecimal secondWeight = SteinersAlgorithm.getEdgeLength(secondEdge);
		
		assertTrue(firstWeight.compareTo(secondWeight) == 1);
	}
}
