package lt.refactory.primsAlgo.service;

import java.math.BigDecimal;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.graph.mock.GraphMock;
import lt.refactory.primsAlgo.service.algorithm.PrimsAlgorithm;
import lt.refactory.primsAlgo.service.algorithm.SteinersAlgorithm;
import lt.refactory.primsAlgo.service.algorithm.exceptions.AlgorithmException;
import lt.refactory.primsAlgo.service.algorithm.models.Circle;

public class PrimsAlgoService {

	public Graph<WeightedEdge> getSmallestTreeWithOnePoint(Graph<WeightedEdge> graph) {
		
		// will be used for graph size difference calculations
		Graph<WeightedEdge> smallestTreeFull = PrimsAlgorithm.solve(graph);
		// will be used for steiners algorithm - edges will be removed
		Graph<WeightedEdge> smallestTree = new Graph<WeightedEdge>(smallestTreeFull);
		
		// result graph
		Graph<WeightedEdge> smallestTreeWithSteinersPoint = null;
		BigDecimal biggestDifference = BigDecimal.ZERO;
		try {
			
			while (smallestTree.getEdgeListSize() > 1) {
				WeightedEdge leave = SteinersAlgorithm.getGraphLeave(smallestTree);
				WeightedEdge nearEdge = smallestTree.getNearEdges(leave).get(0);	// leave always have one near edge
				BigDecimal angleBetweenEdges = SteinersAlgorithm.getAgleBetweenTwoEdges(leave, nearEdge);
				
				if (angleBetweenEdges.compareTo(BigDecimal.valueOf(120)) == -1) {
					// Torichelli procedure for steiners point:
					Graph<WeightedEdge> triangle = SteinersAlgorithm.getTriangleWithWeights(leave, nearEdge);
					Graph<WeightedEdge> equilateralTriangle = SteinersAlgorithm.getEquilateralTriangle(triangle);
					Circle circumscribedCircle = SteinersAlgorithm.getCircumscribedCircle(equilateralTriangle);
					WeightedEdge edgeThroughTriangles = SteinersAlgorithm.getEdgeThroughTriangles(triangle, equilateralTriangle);
					Node steinersPoint = SteinersAlgorithm.getSteinersPoint(edgeThroughTriangles, circumscribedCircle);
					
					// Calculate how much graph is shorter than initial graph
					Graph<WeightedEdge> newTree = SteinersAlgorithm.changeGraphEdges(smallestTreeFull, leave, nearEdge, steinersPoint);
					BigDecimal lengthDifference = SteinersAlgorithm.calculateGraphLengthsDifference(smallestTreeFull, newTree);
					
					if (lengthDifference.compareTo(biggestDifference) == 1) {
						smallestTreeWithSteinersPoint = new Graph<WeightedEdge>(newTree);
						biggestDifference = lengthDifference;	// TODO : make sure if value, not value
					}
				} else {
					smallestTree.removeEdge(leave);
				}
			}
		} catch (AlgorithmException e) {
			e.printStackTrace();
		} catch (AddEdgeException e) {
			e.printStackTrace();
		}
		
		return smallestTreeWithSteinersPoint;
	}
}
