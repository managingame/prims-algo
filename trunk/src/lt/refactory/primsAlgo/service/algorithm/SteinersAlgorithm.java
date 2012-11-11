package lt.refactory.primsAlgo.service.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.enums.NodeType;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.service.algorithm.exceptions.AlgorithmException;
import lt.refactory.primsAlgo.service.algorithm.models.Circle;
import lt.refactory.primsAlgo.service.algorithm.models.LinearFunctionParameters;

/**
 * Class for Steiner's algorithm implementation
 * @authors Osvaldas, Arminas
 *
 */
public class SteinersAlgorithm {
	
	public static final int DIVISION_PRECISION = 3;
	public static final int ROUNDING_PRECISION = 3;
	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	
	public static <T extends Edge> T getGraphLeave(Graph<T> graph){
		for (T edge : graph.getEdgeList()) {
			List<Node> firstNearNodes = graph.getNearNodes(edge.getStart());
			List<Node> secondNearNodes = graph.getNearNodes(edge.getEnd());

			if (firstNearNodes.size() == 1 || secondNearNodes.size() == 1) {
				return edge;
			}
		}
		return null;
	}
	
	public static <T extends Edge> BigDecimal getAngleBetweenTwoEdges(T edge1, T edge2) throws AlgorithmException {
		//checking if edges have common point
		if (SteinersAlgorithm.getCommonPoint(edge1, edge2) != null) {
			// find first vector value
			BigDecimal pointX = edge1.getEnd().getPointX().subtract(edge1.getStart().getPointX());
			BigDecimal pointY = edge1.getEnd().getPointY().subtract(edge1.getStart().getPointY());
			Node firstVector = new Node(pointX, pointY);
			
			// find second vector value
			pointX = edge2.getEnd().getPointX().subtract(edge2.getStart().getPointX());
			pointY = edge2.getEnd().getPointY().subtract(edge2.getStart().getPointY());
			Node secondVector = new Node(pointX, pointY);
			
			// |a| * |b|
			BigDecimal scalarMultiplication = SteinersAlgorithm.getVectorLength(firstVector).multiply(SteinersAlgorithm.getVectorLength(secondVector));
			
			// a x b
			BigDecimal vectorMultiplication = 
					firstVector.getPointX().multiply(secondVector.getPointX())
					.add(firstVector.getPointY().multiply(secondVector.getPointY()));

			double result = Math.acos(vectorMultiplication.divide(scalarMultiplication, DIVISION_PRECISION, ROUNDING_MODE).doubleValue());
			
			if (result == 0) {
				return BigDecimal.valueOf(180);
			}
			
			return BigDecimal.valueOf(Math.toDegrees(result)).round(new MathContext(ROUNDING_PRECISION, ROUNDING_MODE));
		}
		throw new AlgorithmException("Cannot compute angle of edges which doesn't have a common point");
	}
	
	public static <T extends Edge> Node getCommonPoint(T firstEdge, T secondEdge) {
		if (firstEdge.getStart().equals(secondEdge.getStart())) {
			return firstEdge.getStart();
		}
		if (firstEdge.getStart().equals(secondEdge.getEnd())) {
			return firstEdge.getStart();
		}
		if (firstEdge.getEnd().equals(secondEdge.getStart())) {
			return firstEdge.getEnd();
		}
		if (firstEdge.getEnd().equals(secondEdge.getEnd())) {
			return firstEdge.getEnd();
		}
		return null;
	}
	
	public static <T extends Edge> BigDecimal getEdgeLength(T edge) {
		Node node1 = edge.getStart();
		Node node2 = edge.getEnd();
		
		BigDecimal difference1 = node1.getPointX().subtract(node2.getPointX()).pow(2);
		BigDecimal difference2 = node1.getPointY().subtract(node2.getPointY()).pow(2);		
		double length = Math.sqrt(Math.abs(difference1.add(difference2).doubleValue()));
		
		return BigDecimal.valueOf(length);
	}
	
	public static BigDecimal getVectorLength(Node vector) {
		BigDecimal result = vector.getPointX().pow(2).add(vector.getPointY().pow(2));
		
		return BigDecimal.valueOf(Math.sqrt(result.doubleValue()));
	}
	
	public static Graph<WeightedEdge> getTriangleWithWeights(Edge firstEdge, Edge secondEdge) throws AlgorithmException, AddEdgeException {
		
		Node commonPoint = SteinersAlgorithm.getCommonPoint(firstEdge, secondEdge);
		if (commonPoint != null) {
			Node firstNode = null;
			Node secondNode = null;
			
			if (!firstEdge.getStart().equals(commonPoint)) {
				firstNode = firstEdge.getStart();
			}
			if (!firstEdge.getEnd().equals(commonPoint)) {
				firstNode = firstEdge.getEnd();
			}
			
			if (!secondEdge.getStart().equals(commonPoint)) {
				secondNode = secondEdge.getStart();
			}
			if (!secondEdge.getEnd().equals(commonPoint)) {
				secondNode = secondEdge.getEnd();
			}
			Edge thirdEdge = new Edge(firstNode, secondNode);
			
			Graph<WeightedEdge> triangle = new Graph<WeightedEdge>();
			triangle.addEdgeWithNodes(new WeightedEdge(firstEdge, SteinersAlgorithm.getEdgeLength(firstEdge)));
			triangle.addEdgeWithNodes(new WeightedEdge(secondEdge, SteinersAlgorithm.getEdgeLength(secondEdge)));
			triangle.addEdgeWithNodes(new WeightedEdge(thirdEdge, SteinersAlgorithm.getEdgeLength(thirdEdge)));
			
			return triangle;
		}
		throw new AlgorithmException("Edges must have a common point to form a triangle");
	}
	
	public static Graph<WeightedEdge> getEquilateralTriangle(Graph<WeightedEdge> triangle) throws AddEdgeException {
		
		// any edge is suitable for equilateral triangle construction - let's take first
		WeightedEdge edge = triangle.getEdgeList().get(0);
		
		LinearFunctionParameters linearParameters = SteinersAlgorithm.getLinearFunctionParameters(edge.getStart(), edge.getEnd());

		// find node which does not belong to edge - another node will have one
		Node oppositeSideNode;
		if (edge.containsNode(triangle.getEdgeList().get(1).getStart())) {
			oppositeSideNode = triangle.getEdgeList().get(1).getEnd();
		} else {
			oppositeSideNode = triangle.getEdgeList().get(1).getStart();
		}
		
		int oppositeSide;
		// special case when we got a vertical line
		if (linearParameters.getDx().compareTo(BigDecimal.ZERO) == 0) {
			oppositeSide = oppositeSideNode.getPointX().compareTo(edge.getStart().getPointX());
		} else {
			// is (k * x + b - y) > 0
			oppositeSide = linearParameters.getK()
					.multiply(oppositeSideNode.getPointX())
					.add(linearParameters.getB())
					.subtract(oppositeSideNode.getPointY())
					.compareTo(BigDecimal.ZERO);
		}
		
		BigDecimal Cos60 = BigDecimal.valueOf(Math.cos(Math.toRadians(60))).round(new MathContext(ROUNDING_PRECISION, ROUNDING_MODE));
		BigDecimal Sin60 = BigDecimal.valueOf(Math.sin(Math.toRadians(60))).round(new MathContext(ROUNDING_PRECISION, ROUNDING_MODE));
		
		// try to turn point clockwise to find coordinates of the third triangle's point
		BigDecimal turnedX = Cos60.multiply(linearParameters.getDx()).subtract(Sin60.multiply(linearParameters.getDy())).add(edge.getStart().getPointX());
		BigDecimal turnedY = Sin60.multiply(linearParameters.getDx()).add(Cos60.multiply(linearParameters.getDy())).add(edge.getStart().getPointY());
		
		// check if points are in different sides
		// special case when line is vertical
		int thirdNodeSide;
		if (linearParameters.getDx().compareTo(BigDecimal.ZERO) == 0) {
			thirdNodeSide = turnedX.compareTo(edge.getStart().getPointX());
		} else {
			thirdNodeSide = linearParameters.getK()
					.multiply(turnedX)
					.add(linearParameters.getB())
					.subtract(turnedY)
					.compareTo(BigDecimal.ZERO);
		}
		
		// if points are in the same side, need to turn to opposite side
		if (oppositeSide == thirdNodeSide) {
			turnedX = Cos60.multiply(linearParameters.getDx()).add(Sin60.multiply(linearParameters.getDy())).add(edge.getStart().getPointX());
			turnedY = Sin60.negate().multiply(linearParameters.getDx()).add(Cos60.multiply(linearParameters.getDy())).add(edge.getStart().getPointY());
		}
		
		Node newNode = new Node(turnedX, turnedY);
		
		Graph<WeightedEdge> equilateralTriangle = new Graph<WeightedEdge>();
		equilateralTriangle.addEdgeWithNodes(edge);
		equilateralTriangle.addEdgeWithNodes(new WeightedEdge(new Edge(edge.getStart(), newNode), edge.getWeight()));
		equilateralTriangle.addEdgeWithNodes(new WeightedEdge(new Edge(edge.getEnd(), newNode), edge.getWeight()));
		
		return equilateralTriangle;
	}
	
	public static Circle getCircumscribedCircle(Graph<WeightedEdge> triangle) {

		// circle radius equation r = sqrt(3) / 3 * a
		BigDecimal radius = BigDecimal.valueOf((Math.sqrt(3) / 3) * triangle.getEdgeList().get(0).getWeight().doubleValue());
		
		// we need two edges for center point finding
		WeightedEdge firstEdge = triangle.getEdgeList().get(0);
		WeightedEdge secondEdge = triangle.getEdgeList().get(1);
		
		// find middle-edge edges
		WeightedEdge firstMiddleEdge = getMiddleEdge(firstEdge, secondEdge);
		WeightedEdge secondMiddleEdge = getMiddleEdge(secondEdge, firstEdge);
		
		LinearFunctionParameters firstEdgeParameters = getLinearFunctionParameters(firstMiddleEdge.getStart(), firstMiddleEdge.getEnd());
		LinearFunctionParameters secondEdgeParameters = getLinearFunctionParameters(secondMiddleEdge.getStart(), secondMiddleEdge.getEnd());
		
		BigDecimal centerX = null;
		BigDecimal centerY = null;
		
		// special case when one of the edges is vertical
		if (firstEdgeParameters.getDx().compareTo(BigDecimal.ZERO) == 0) {
			
			centerX = firstMiddleEdge.getStart().getPointX();
			centerY = secondEdgeParameters.getK()
					.multiply(centerX)
					.add(secondEdgeParameters.getB());
			
		} else if (secondEdgeParameters.getDx().compareTo(BigDecimal.ZERO) == 0 ) {
			
			centerX = secondMiddleEdge.getStart().getPointX();
			centerY = firstEdgeParameters.getK()
					.multiply(centerX)
					.add(firstEdgeParameters.getB());
		} else {
			// x = (b2 - b1) / ( k1 - k2 )
			centerX = secondEdgeParameters.getB()
					.subtract(firstEdgeParameters.getB())
					.divide(firstEdgeParameters.getK()
					.subtract(secondEdgeParameters.getK()), new MathContext(DIVISION_PRECISION, ROUNDING_MODE));
			
			// y = k * x - b
			centerY = firstEdgeParameters.getK()
					.multiply(centerX)
					.add(firstEdgeParameters.getB());
		}
		
		return new Circle(new Node(centerX, centerY), radius);
	}
	
	public static WeightedEdge getMiddleEdge(Edge mainEdge, Edge nearEdge) {
		BigDecimal midX = mainEdge.getEnd().getPointX().add(mainEdge.getStart().getPointX()).divide(BigDecimal.valueOf(2));
		BigDecimal midY = mainEdge.getEnd().getPointY().add(mainEdge.getStart().getPointY()).divide(BigDecimal.valueOf(2));
		Node anotherPoint = mainEdge.containsNode(nearEdge.getStart()) ? nearEdge.getEnd() : nearEdge.getStart();
		Edge midEdge = new Edge(new Node(midX, midY), anotherPoint);
		
		return new WeightedEdge(midEdge, getEdgeLength(midEdge));
	}
	
	public static LinearFunctionParameters getLinearFunctionParameters(Node startNode, Node endNode) {
		
		// find edge linear function y = k * x + b
		LinearFunctionParameters result = new LinearFunctionParameters();
		
		// dy = y2 - y1
		result.setDy(endNode.getPointY().subtract(startNode.getPointY()));
		// dx = x2 - x1
		result.setDx(endNode.getPointX().subtract(startNode.getPointX()));
		
		// if line is not vertical (vertical line cannot be expressed with linear function)
		if (result.getDx().compareTo(BigDecimal.ZERO) != 0) {
			// k = dy / dx
			result.setK(result.getDy().divide(result.getDx(), DIVISION_PRECISION, RoundingMode.FLOOR));
			// b = y1 - k * x1
			result.setB(startNode.getPointY().subtract(startNode.getPointX().multiply(result.getK())));
		}
		
		return result;
	}
	
	public static WeightedEdge getEdgeThroughTriangles(Graph<WeightedEdge> triangle, Graph<WeightedEdge> equilateral) throws AlgorithmException {

		Graph<WeightedEdge> combinedGraph = new Graph<WeightedEdge>();
		try { 
			
			for (WeightedEdge edge : triangle.getEdgeList()) {
				combinedGraph.addEdgeWithNodes(edge);
			}
			for (WeightedEdge edge : equilateral.getEdgeList()) {
				if (!combinedGraph.containsEdge(edge)) {
					combinedGraph.addEdgeWithNodes(edge);
				}
			}
			
		} catch (AddEdgeException e) {
			throw new AlgorithmException("Failed to make combined graph");
		}
		
		List<Node> resultNodes = new ArrayList<Node>();
		List<Node> nearNodes;
		
		for (Node node : combinedGraph.getNodeList()) {
			nearNodes = combinedGraph.getNearNodes(node);
			if (nearNodes.size() == 2) {
				resultNodes.add(node);
			}
		}
		
		if (resultNodes.size() != 2) {
			throw new AlgorithmException("Found more or less edges than needed");
		}
		
		Edge resultEdge = new Edge(resultNodes.get(0), resultNodes.get(1));
		BigDecimal weight = getEdgeLength(resultEdge);
		
		return new WeightedEdge(resultEdge, weight);
	}
	
	public static Node getSteinersPoint(WeightedEdge edge, Circle circle) {
		
		LinearFunctionParameters edgeParameters = getLinearFunctionParameters(edge.getStart(), edge.getEnd());
		
		// find quadratic function parameters:
		// a = k * k + 1
		BigDecimal a = edgeParameters.getK().pow(2).add(BigDecimal.ONE); 
		// b = 2 * (k * (z - b) - a) // here z = b from edgeParameters
		BigDecimal b = edgeParameters.getK()
				.multiply(edgeParameters.getB().subtract(circle.getCenterPoint().getPointY()))
				.subtract(circle.getCenterPoint().getPointX())
				.multiply(BigDecimal.valueOf(2));
		// c = a * a - r * r + (z - b)(z - b)
		BigDecimal c = circle.getCenterPoint().getPointX().pow(2)
				.subtract(circle.getRadius().pow(2))
				.add(
						edgeParameters.getB()
						.subtract(circle.getCenterPoint().getPointY())
						.pow(2));
		
		// discriminant D = b * b - 4 * a * c
		BigDecimal D = b.multiply(b)
				.subtract(BigDecimal.valueOf(4).multiply(a).multiply(c));
		
		// find result points
		BigDecimal x1 = b.negate()
				.subtract(BigDecimal.valueOf(Math.sqrt(D.doubleValue())))
				.divide(a.multiply(BigDecimal.valueOf(2)), DIVISION_PRECISION, ROUNDING_MODE);
		
		BigDecimal x2 = b.negate()
				.add(BigDecimal.valueOf(Math.sqrt(D.doubleValue())))
				.divide(a.multiply(BigDecimal.valueOf(2)), DIVISION_PRECISION, ROUNDING_MODE);
				
		BigDecimal y1 = edgeParameters.getK()
				.multiply(x1)
				.add(edgeParameters.getB());
		
		BigDecimal y2 = edgeParameters.getK()
				.multiply(x2)
				.add(edgeParameters.getB());
		
		Node firstPoint = new Node(x1, y1, NodeType.STEINER);
		Node secondPoint = new Node(x2, y2, NodeType.STEINER);
		
		BigDecimal startComparer = firstPoint.getPointX().subtract(edge.getStart().getPointX()).abs();
		BigDecimal endComparer = firstPoint.getPointX().subtract(edge.getEnd().getPointX()).abs();
		BigDecimal firstComparer = startComparer.compareTo(endComparer) == 1 ? endComparer : startComparer;
		
		startComparer = secondPoint.getPointX().subtract(edge.getStart().getPointX()).abs();
		endComparer = secondPoint.getPointX().subtract(edge.getEnd().getPointX()).abs();
		BigDecimal secondComparer = startComparer.compareTo(endComparer) == 1 ? endComparer : startComparer;
		
		if (firstComparer.compareTo(secondComparer) == 1) {
			return firstPoint;
		} else {
			return secondPoint;
		}
	}
	
	public static Graph<WeightedEdge> changeGraphEdges(Graph<WeightedEdge> graphToChange, WeightedEdge edge, WeightedEdge nearEdge, Node steinerPoint){
		Node commonPoint = getCommonPoint(edge, nearEdge);
		Graph<WeightedEdge> currentGraph = new Graph<WeightedEdge>(graphToChange);
		
		// remove old edges
		currentGraph.removeEdge(edge);
		currentGraph.removeEdge(nearEdge);
		
		// create new adges
		Edge firstNewEdge = new Edge(commonPoint, steinerPoint);
		Edge secondNewEdge;
		Edge thirdNewEdge;
		
		if (edge.getStart().equals(commonPoint)) {
			secondNewEdge = new Edge(edge.getEnd(), steinerPoint);
		} else {
			secondNewEdge = new Edge(edge.getStart(), steinerPoint);
		}
		
		if (nearEdge.getStart().equals(commonPoint)) {
			thirdNewEdge = new Edge(nearEdge.getEnd(), steinerPoint);
		} else {
			thirdNewEdge = new Edge(nearEdge.getStart(), steinerPoint);
		}
		
		WeightedEdge firstWeightedEdge = new WeightedEdge(firstNewEdge, getEdgeLength(firstNewEdge));
		WeightedEdge secondWeightedEdge = new WeightedEdge(secondNewEdge, getEdgeLength(secondNewEdge));
		WeightedEdge thirdWeightedEdge = new WeightedEdge(thirdNewEdge, getEdgeLength(thirdNewEdge));
		
		// add new edges
		try {
			currentGraph.addEdgeWithNodes(firstWeightedEdge);
			currentGraph.addEdgeWithNodes(secondWeightedEdge);
			currentGraph.addEdgeWithNodes(thirdWeightedEdge);
		} catch (AddEdgeException ex) {
			ex.printStackTrace();
		}
		
		return currentGraph;
	}
	
	public static Graph<WeightedEdge> getWeightedGraph(Graph<Edge> graph) {
		Graph<WeightedEdge> resultGraph = new Graph<WeightedEdge>();
		
		for (Edge edge : graph.getEdgeList()) {
			try {
				resultGraph.addEdgeWithNodes(new WeightedEdge(edge, getEdgeLength(edge)));
			} catch (AddEdgeException ex) {
				ex.printStackTrace();
			}
		}
		
		return resultGraph;
	}
	
	public static BigDecimal getGraphLength(Graph<WeightedEdge> graph) {
		BigDecimal result = BigDecimal.ZERO;
		result.setScale(ROUNDING_PRECISION);
		
		for (WeightedEdge edge : graph.getEdgeList()) {
			result = result.add(edge.getWeight(), new MathContext(ROUNDING_PRECISION, ROUNDING_MODE));
		}
		return result;
	}
	
	public static BigDecimal calculateGraphLengthsDifference(Graph<WeightedEdge> firstGraph, Graph<WeightedEdge> shorterGraph) {
		BigDecimal firstLength = getGraphLength(firstGraph);
		BigDecimal secondLength = getGraphLength(shorterGraph);
		
		return firstLength.subtract(secondLength);
	}
}
