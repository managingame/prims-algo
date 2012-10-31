package lt.refactory.primsAlgo.algorithm.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.service.algorithm.SteinersAlgorithm;
import lt.refactory.primsAlgo.service.algorithm.exceptions.AlgorithmException;

import org.junit.Test;

public class SteinersAlgorithmTest {

	public static SteinerObjectsProvider objectsProvider = new SteinerObjectsProvider();
	
	@Test
	public void testGetGraphLeave(){
		Graph<Edge> graph = objectsProvider.getTreeGraph();
		Edge leave = null;
		
		try {
			leave = SteinersAlgorithm.getGraphLeave(graph);
		} catch (AlgorithmException e) {
			fail("Algorithm exception thrown");
		}
		
		assertTrue(graph.getNearEdges(leave).size() == 1);
	}
	
	@Test
	public void testGetAngleBetweenTwoEdges(){

		try {
			
			Edge firstEdge = objectsProvider.getEdge(0, 0, 0, 5);
			Edge secondEdge = objectsProvider.getEdge(0, 0, 3, 0);
			BigDecimal angle = SteinersAlgorithm.getAgleBetweenTwoEdges(firstEdge, secondEdge);
			assertTrue(angle.compareTo(BigDecimal.valueOf(90)) == 0);
			
			firstEdge = objectsProvider.getEdge(0, 0, 0, 5);
			secondEdge = objectsProvider.getEdge(0, 5, 0, 8);
			angle = SteinersAlgorithm.getAgleBetweenTwoEdges(firstEdge, secondEdge);
			assertTrue(angle.compareTo(BigDecimal.valueOf(180)) == 0);
			
			firstEdge = objectsProvider.getEdge(0, 0, 0, 5);
			secondEdge = objectsProvider.getEdge(0, 0, 3, 3);
			angle = SteinersAlgorithm.getAgleBetweenTwoEdges(firstEdge, secondEdge);
			assertTrue(angle.compareTo(BigDecimal.valueOf(45)) == 0);
			
		} catch (AlgorithmException e) {
			fail("Algorithm exception thrown");
		}
	}
	
	@Test
	public void testGetEdgeLength() {
		Edge edge = objectsProvider.getEdge(0, 0, 0, 2);
		BigDecimal length = SteinersAlgorithm.getEdgeLength(edge);
		
		assertTrue(length.compareTo(BigDecimal.valueOf(2)) == 0);
	}
	
	@Test
	public void testGetTriangleWithWeights() {
		Edge firstEdge = objectsProvider.getEdge(1, 1, 2, 3);
		Edge secondEdge = objectsProvider.getEdge(1, 1, 3, 1);
		
		Edge thirdEdge = objectsProvider.getEdge(2, 3, 3, 1);
		WeightedEdge addedEdge = new WeightedEdge(thirdEdge, SteinersAlgorithm.getEdgeLength(thirdEdge));
		
		Graph<WeightedEdge> triangle;
		try {
			triangle = SteinersAlgorithm.getTriangleWithWeights(firstEdge, secondEdge);
			
			assertTrue(triangle.containsEdge(addedEdge));
		} catch (AlgorithmException e) {
			fail("Algorithm exception thrown");
		} catch (AddEdgeException e) {
			fail("Add edge exception thrown");
		}
	}
	
	@Test
	public void testGetEquilateralTriangle() {
		Graph<WeightedEdge> triangle = objectsProvider.getTriangle();
		
		try {
			//Graph<WeightedEdge> equilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(triangle);
			
			
		} catch (AddEdgeException e) {
			fail("Add edge exception thrown");
		}
	}
}
