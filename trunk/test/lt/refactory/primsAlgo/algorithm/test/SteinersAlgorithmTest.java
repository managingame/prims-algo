package lt.refactory.primsAlgo.algorithm.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.service.algorithm.SteinersAlgorithm;
import lt.refactory.primsAlgo.service.algorithm.exceptions.AlgorithmException;
import lt.refactory.primsAlgo.service.algorithm.models.Circle;

import org.junit.Test;

public class SteinersAlgorithmTest {

	public static SteinerObjectsProvider objectsProvider = new SteinerObjectsProvider();
	
	@Test
	public void testGetGraphLeave(){
		Graph<Edge> graph = objectsProvider.getTreeGraph();
		Edge leave = null;
		
		leave = SteinersAlgorithm.getGraphLeave(graph);
		
		assertTrue(leave != null);
		assertTrue(graph.getNearNodes(leave.getStart()).size() == 1 || graph.getNearNodes(leave.getEnd()).size() == 1);
	}
	
	@Test
	public void testGetAngleBetweenTwoEdges(){

		try {
			
			Edge firstEdge = objectsProvider.getEdge(0, 0, 0, 5);
			Edge secondEdge = objectsProvider.getEdge(0, 0, 3, 0);
			BigDecimal angle = SteinersAlgorithm.getAngleBetweenTwoEdges(firstEdge, secondEdge);
			assertTrue(angle.compareTo(BigDecimal.valueOf(90)) == 0);
			
			firstEdge = objectsProvider.getEdge(0, 0, 0, 5);
			secondEdge = objectsProvider.getEdge(0, 5, 0, 8);
			angle = SteinersAlgorithm.getAngleBetweenTwoEdges(firstEdge, secondEdge);
			assertTrue(angle.compareTo(BigDecimal.valueOf(180)) == 0);
			
			firstEdge = objectsProvider.getEdge(0, 0, 0, 5);
			secondEdge = objectsProvider.getEdge(0, 0, 3, 3);
			angle = SteinersAlgorithm.getAngleBetweenTwoEdges(firstEdge, secondEdge);
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
		Graph<WeightedEdge> firstTriangle = objectsProvider.getTriangle(1);		// first line vertical
		Graph<WeightedEdge> secondTriangle = objectsProvider.getTriangle(2);	// first line diagonal
		Graph<WeightedEdge> thirdTriangle = objectsProvider.getTriangle(3);		// first line horizontal

		try {
			Graph<WeightedEdge> firstEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(firstTriangle);
			Graph<WeightedEdge> secondEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(secondTriangle);
			Graph<WeightedEdge> thirdEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(thirdTriangle);
			
			// check if weights match
			for (WeightedEdge edge  : firstEquilateralTriangle.getEdgeList()) {
				assertTrue(edge.getWeight().compareTo(firstTriangle.getEdgeList().get(0).getWeight()) == 0);
			}
			
			for (WeightedEdge edge  : secondEquilateralTriangle.getEdgeList()) {
				assertTrue(edge.getWeight().compareTo(secondTriangle.getEdgeList().get(0).getWeight()) == 0);
			}
			
			for (WeightedEdge edge  : thirdEquilateralTriangle.getEdgeList()) {
				assertTrue(edge.getWeight().compareTo(thirdTriangle.getEdgeList().get(0).getWeight()) == 0);
			}
			
		} catch (AddEdgeException e) {
			fail("Add edge exception thrown");
		}
	}
	
	@Test
	public void testGetCircumscribedCircle() {
		Graph<WeightedEdge> firstTriangle = objectsProvider.getTriangle(1);		// first line vertical
		Graph<WeightedEdge> secondTriangle = objectsProvider.getTriangle(2);	// first line diagonal
		Graph<WeightedEdge> thirdTriangle = objectsProvider.getTriangle(3);		// first line horizontal
		
		try {
			Graph<WeightedEdge> firstEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(firstTriangle);
			Graph<WeightedEdge> secondEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(secondTriangle);
			Graph<WeightedEdge> thirdEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(thirdTriangle);
			
			Circle firstCircle = SteinersAlgorithm.getCircumscribedCircle(firstEquilateralTriangle);
			Circle secondCircle = SteinersAlgorithm.getCircumscribedCircle(secondEquilateralTriangle);
			Circle thirdCircle = SteinersAlgorithm.getCircumscribedCircle(thirdEquilateralTriangle);
			
			// test if circles got any radius
			assertTrue(firstCircle.getRadius().compareTo(BigDecimal.ZERO) == 1);
			assertTrue(secondCircle.getRadius().compareTo(BigDecimal.ZERO) == 1);
			assertTrue(thirdCircle.getRadius().compareTo(BigDecimal.ZERO) == 1);
			
			// test what values should be aproximately
			assertTrue(firstCircle.getCenterPoint().getPointX().compareTo(BigDecimal.ZERO) == -1);
			assertTrue(firstCircle.getCenterPoint().getPointY().compareTo(BigDecimal.valueOf(2)) == 0);
			
			assertTrue(secondCircle.getCenterPoint().getPointX().compareTo(BigDecimal.ONE) == 1);
			assertTrue(secondCircle.getCenterPoint().getPointY().compareTo(BigDecimal.valueOf(2)) == 1);
			
			assertTrue(thirdCircle.getCenterPoint().getPointX().compareTo(BigDecimal.valueOf(1.5)) == 0);
			assertTrue(thirdCircle.getCenterPoint().getPointY().compareTo(BigDecimal.ZERO) == -1);
			
		} catch (AddEdgeException e) {
			fail("Add edge exception thrown");
		}
	}
	
	@Test
	public void testGetEdgeThroughTriangles() {
		Graph<WeightedEdge> firstTriangle = objectsProvider.getTriangle(1);		// first line vertical
		Graph<WeightedEdge> secondTriangle = objectsProvider.getTriangle(2);	// first line diagonal
		Graph<WeightedEdge> thirdTriangle = objectsProvider.getTriangle(3);		// first line horizontal
		
		try {
			Graph<WeightedEdge> firstEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(firstTriangle);
			Graph<WeightedEdge> secondEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(secondTriangle);
			Graph<WeightedEdge> thirdEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(thirdTriangle);
			
			WeightedEdge firstEdge = SteinersAlgorithm.getEdgeThroughTriangles(firstTriangle, firstEquilateralTriangle);
			WeightedEdge secondEdge = SteinersAlgorithm.getEdgeThroughTriangles(secondTriangle, secondEquilateralTriangle);
			WeightedEdge thirdEdge = SteinersAlgorithm.getEdgeThroughTriangles(thirdTriangle, thirdEquilateralTriangle);
			
			// check if edges through triangles consists nodes from triangles
			assertTrue(firstTriangle.containsNode(firstEdge.getStart()) || firstEquilateralTriangle.containsNode(firstEdge.getStart()));
			assertTrue(firstTriangle.containsNode(firstEdge.getEnd()) || firstEquilateralTriangle.containsNode(firstEdge.getEnd()));
			
			assertTrue(secondTriangle.containsNode(secondEdge.getStart()) || secondEquilateralTriangle.containsNode(secondEdge.getStart()));
			assertTrue(secondTriangle.containsNode(secondEdge.getEnd()) || secondEquilateralTriangle.containsNode(secondEdge.getEnd()));
			
			assertTrue(thirdTriangle.containsNode(thirdEdge.getStart()) || thirdEquilateralTriangle.containsNode(thirdEdge.getStart()));
			assertTrue(thirdTriangle.containsNode(thirdEdge.getEnd()) || thirdEquilateralTriangle.containsNode(thirdEdge.getEnd()));			
		} catch (AddEdgeException e) {
			fail("Add edge exception thrown");
		} catch (AlgorithmException e) {
			fail("Algorithm exception thrown");
		}
	}
	
	@Test
	public void testGetSteinersPoint() {
		try {
			
			Graph<WeightedEdge> firstTriangle = objectsProvider.getTriangle(1);		// first line vertical
			Graph<WeightedEdge> secondTriangle = objectsProvider.getTriangle(2);	// first line diagonal
			Graph<WeightedEdge> thirdTriangle = objectsProvider.getTriangle(3);		// first line horizontal
			
			Graph<WeightedEdge> firstEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(firstTriangle);
			Graph<WeightedEdge> secondEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(secondTriangle);
			Graph<WeightedEdge> thirdEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(thirdTriangle);
			
			Circle firstCircle = SteinersAlgorithm.getCircumscribedCircle(firstEquilateralTriangle);
			Circle secondCircle = SteinersAlgorithm.getCircumscribedCircle(secondEquilateralTriangle);
			Circle thirdCircle = SteinersAlgorithm.getCircumscribedCircle(thirdEquilateralTriangle);
			
			WeightedEdge firstEdge = SteinersAlgorithm.getEdgeThroughTriangles(firstTriangle, firstEquilateralTriangle);
			WeightedEdge secondEdge = SteinersAlgorithm.getEdgeThroughTriangles(secondTriangle, secondEquilateralTriangle);
			WeightedEdge thirdEdge = SteinersAlgorithm.getEdgeThroughTriangles(thirdTriangle, thirdEquilateralTriangle);
			
			Node firstPoint = SteinersAlgorithm.getSteinersPoint(firstEdge, firstCircle);
			Node secondPoint = SteinersAlgorithm.getSteinersPoint(secondEdge, secondCircle);
			Node thirdPoint = SteinersAlgorithm.getSteinersPoint(thirdEdge, thirdCircle);
			
			// test what values should be aproximately
			assertTrue(firstPoint.getPointX().compareTo(BigDecimal.ZERO) == 1 && firstPoint.getPointX().compareTo(BigDecimal.ONE) == -1);
			assertTrue(firstPoint.getPointY().compareTo(BigDecimal.ONE) == -1 && firstPoint.getPointY().compareTo(BigDecimal.ZERO) == 1);
			
			assertTrue(secondPoint.getPointX().compareTo(BigDecimal.ZERO) == 1 && secondPoint.getPointX().compareTo(BigDecimal.ONE) == -1);
			assertTrue(secondPoint.getPointY().compareTo(BigDecimal.ONE) == -1 && secondPoint.getPointY().compareTo(BigDecimal.ZERO) == 1);
			
			assertTrue(thirdPoint.getPointX().compareTo(BigDecimal.ZERO) == 1 && thirdPoint.getPointX().compareTo(BigDecimal.ONE) == -1);
			assertTrue(thirdPoint.getPointY().compareTo(BigDecimal.ONE) == -1 && thirdPoint.getPointY().compareTo(BigDecimal.ZERO) == 1);
			
		} catch (AddEdgeException e) {
			fail("Add edge exception thrown");
		} catch (AlgorithmException e) {
			fail("Algorithm exception thrown");
		}
	}
	
	@Test
	public void testChangeGraphEdges() {
		try {
			Graph<WeightedEdge> firstTriangle = objectsProvider.getTriangle(1);		// first line vertical
			WeightedEdge edge = firstTriangle.getEdgeList().get(0);
			WeightedEdge nearEdge = firstTriangle.getEdgeList().get(1);
			
			Graph<WeightedEdge> firstEquilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(firstTriangle);
			
			Circle firstCircle = SteinersAlgorithm.getCircumscribedCircle(firstEquilateralTriangle);
			
			WeightedEdge firstEdge = SteinersAlgorithm.getEdgeThroughTriangles(firstTriangle, firstEquilateralTriangle);
			
			Node firstPoint = SteinersAlgorithm.getSteinersPoint(firstEdge, firstCircle);
			
			Graph<WeightedEdge> changedGraph = SteinersAlgorithm.changeGraphEdges(firstTriangle, edge, nearEdge, firstPoint);
			
			// must contain Steiners point and there should be four edges and four nodes total
			assertTrue(changedGraph.containsNode(firstPoint));
			assertTrue(changedGraph.getEdgeListSize() == 4);
			assertTrue(changedGraph.getNodeListSize() == 4);
			
		} catch (AddEdgeException e) {
			fail("Add edge exception thrown");
		} catch (AlgorithmException e) {
			fail("Algorithm exception thrown");
		}
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
