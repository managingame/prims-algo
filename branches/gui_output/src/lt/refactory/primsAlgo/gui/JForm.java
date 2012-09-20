package lt.refactory.primsAlgo.gui;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.mock.GraphMock;

public class JForm {
	public static void main(String[] argv) {
		GraphMock<Edge> graphMock = new GraphMock<Edge>();
		for (Edge edge : graphMock.getEdgeList()) {
			System.out.println(edge);
		}
		
	}
}
