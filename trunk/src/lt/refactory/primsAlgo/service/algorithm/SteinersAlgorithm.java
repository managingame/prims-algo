package lt.refactory.primsAlgo.service.algorithm;

import java.math.BigDecimal;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;

public class SteinersAlgorithm {

	public static WeightedEdge findLeave(Graph<WeightedEdge> graph) {
		throw new UnsupportedOperationException();
	}
	
	public static <T extends Edge> BigDecimal  AngleBetweenTwoEdges(T  edge1,T  edge2){
		//checking if edges have common point
		if (!edge1.getStart().equals(edge2.getStart())
			|| !edge1.getStart().equals(edge2.getEnd())
			|| !edge1.getEnd().equals(edge2.getStart())
			|| !edge1.getEnd().equals(edge2.getEnd())
			) {
			// This is baaad
			return null;
		}
		
		//Angle is calculateed with vector scalar multiplication rule:
		// cos(angle) = v1 * v2/ |v2| * |v2|
		
		//TODO: FINISH
		return null;
	}
	
	public static <T extends Edge> BigDecimal EdgeLength(T edge){
		Node node1 = edge.getStart();
		Node node2 = edge.getEnd();
		
		BigDecimal difference1 = node1.getPointX().pow(2).subtract(
				                 node2.getPointX().pow(2));
		BigDecimal difference2 = node1.getPointY().pow(2).subtract(
                node2.getPointY().pow(2));		
		double length = Math.sqrt(difference1.add(difference2).doubleValue());
		return BigDecimal.valueOf(length);
	}
	
	
}
