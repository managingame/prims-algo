package lt.refactory.primsAlgo.graph.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Node;

enum Points{
	AA,AB,AC,AD,AE,AF,AG,
	BA,BB,BC,BD,BE,BF,BG,
	CA,CB,CC,CD,CE,CF,CG,
}

 public class TestObjectsFactory {
	 static final int TESTOBJECTS = 6;
	 static BigDecimal []pointsToXArray = new BigDecimal[TESTOBJECTS];
	 static BigDecimal []pointsToYArray = new BigDecimal[TESTOBJECTS];
	 
	 static{
		 pointsToXArray[0] = BigDecimal.valueOf(1.0);
		 pointsToXArray[1] = BigDecimal.valueOf(2.0);
		 pointsToXArray[2] = BigDecimal.valueOf(-3.0);
		 pointsToXArray[3] = BigDecimal.valueOf(4.0);
		 pointsToXArray[4] = BigDecimal.valueOf(0.5);
		 pointsToXArray[5] = BigDecimal.valueOf(-6.0);
		 
		 pointsToYArray[0] = BigDecimal.valueOf(0.4);
		 pointsToYArray[1] = BigDecimal.valueOf(1.1);
		 pointsToYArray[2] = BigDecimal.valueOf(-3.5);
		 pointsToYArray[3] = BigDecimal.valueOf(2.7);
		 pointsToYArray[4] = BigDecimal.valueOf(-0.0);
		 pointsToYArray[5] = BigDecimal.valueOf(1.2);		 
	 }
	 
	 static List<Node> nodesFactory()
	 {
		 List<Node> nodeList = new ArrayList<Node>();
			 nodeList.add(new Node(pointsToXArray[0],pointsToYArray[0])); // 0[ 1.0; 0.4];
			 nodeList.add(new Node(pointsToXArray[0],pointsToYArray[1])); // 1[ 1.0; 1.1];
			 nodeList.add(new Node(pointsToXArray[2],pointsToYArray[3])); // 2[-3.0; 2.7];
			 nodeList.add(new Node(pointsToXArray[5],pointsToYArray[4])); // 3[-6.0;-0.0];
			 nodeList.add(new Node(pointsToXArray[3],pointsToYArray[5])); // 4[ 4.0; 1.2];
			 nodeList.add(new Node(pointsToXArray[1],pointsToYArray[5])); // 5[ 2.0; 1.2];
			 nodeList.add(new Node(pointsToXArray[5],pointsToYArray[1])); // 6[-6.0; 1.1];
			 nodeList.add(new Node(pointsToXArray[5],pointsToYArray[1])); // 7[-6.0; 1.1];
			 nodeList.add(new Node(pointsToXArray[4],pointsToYArray[2])); // 8[ 0.5;-3.5];
		return nodeList;
		 
	 }
	 static List<Edge> edgeFactory(){
		List<Node> nodeList = TestObjectsFactory.nodesFactory();
		List<Edge> edgeList = new ArrayList<>();
		edgeList.add(new Edge(nodeList.get(0),nodeList.get(6)));
		edgeList.add(new Edge(nodeList.get(6),nodeList.get(0)));
		edgeList.add(new Edge(nodeList.get(0),nodeList.get(5)));
	    edgeList.add(new Edge(nodeList.get(5),nodeList.get(1)));
		edgeList.add(new Edge(nodeList.get(1),nodeList.get(5)));
		edgeList.add(new Edge(nodeList.get(1),nodeList.get(6)));
		edgeList.add(new Edge(nodeList.get(5),nodeList.get(6)));
		edgeList.add(new Edge(nodeList.get(4),nodeList.get(5)));
		edgeList.add(new Edge(nodeList.get(2),nodeList.get(6)));
		edgeList.add(new Edge(nodeList.get(2),nodeList.get(3)));
		edgeList.add(new Edge(nodeList.get(0),nodeList.get(8)));
		System.out.println(edgeList.toString());
		return edgeList;
		 
	 }
}
