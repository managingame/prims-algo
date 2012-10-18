package lt.refactory.primsAlgo.graph.test;
// TODO - 1) Move this shit to utils subpackage
// TODO - 2) Create points using enum
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Node;


	public class TestObjectsFactory {
		 
		
		public enum enumNode{
				A(new Node(pointsToXArray[0],pointsToXArray[0])),
				B(new Node(pointsToXArray[1],pointsToXArray[1])),
				C(new Node(pointsToXArray[2],pointsToXArray[2])),
				D(new Node(pointsToXArray[3],pointsToXArray[3])),
				E(new Node(pointsToXArray[4],pointsToXArray[4])),
				F(new Node(pointsToXArray[5],pointsToXArray[5])),
				G(new Node(pointsToXArray[0],pointsToXArray[0])),
				H(new Node(pointsToXArray[0],pointsToXArray[0])),
				I(new Node(pointsToXArray[0],pointsToXArray[0])),
				J(new Node(pointsToXArray[0],pointsToXArray[0]));
				
				private final Node node;
				private enumNode(Node node){
					this.node  = node;
				}
				public Node getNode(){
					return node;
				}
				
				public Edge getEdge(enumNode otherNode ){
					return new Edge(this.getNode(),otherNode.getNode());
				}
				
			 }
			 
		
		 static final int TESTOBJECTS = 6;
		 static BigDecimal []pointsToXArray = new BigDecimal[TESTOBJECTS];
		 static BigDecimal []pointsToYArray = new BigDecimal[TESTOBJECTS];
		 
		 
		 
		 // Read about static import ! 
		 static{
			 Node test = enumNode.A.getNode();
			 Edge test2 = enumNode.A.getEdge(enumNode.B);
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
				 nodeList.add(new Node(pointsToYArray[0],pointsToYArray[0])); // 0[ 1.0; 0.4];
				 nodeList.add(new Node(pointsToXArray[0],pointsToYArray[1])); // 1[ 1.0; 1.1];
				 nodeList.add(new Node(pointsToXArray[2],pointsToYArray[3])); // 2[-3.0; 2.7];
				 nodeList.add(new Node(pointsToXArray[5],pointsToYArray[4])); // 3[-6.0;-0.0];
				 nodeList.add(new Node(pointsToXArray[3],pointsToYArray[5])); // 4[ 4.0; 1.2];
				 nodeList.add(new Node(pointsToXArray[1],pointsToYArray[5])); // 5[ 2.0; 1.2];
				 nodeList.add(new Node(pointsToXArray[5],pointsToYArray[1])); // 6[-6.0; 1.1];
				 nodeList.add(new Node(pointsToXArray[5],pointsToYArray[1])); // 7[-6.0; 1.1];
				 nodeList.add(new Node(pointsToXArray[4],pointsToYArray[2])); // 8[ 0.5;-3.5];
				 nodeList.add(new Node(pointsToXArray[2],pointsToYArray[4])); // 9[-3.0;-0.0];
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
			edgeList.add(new Edge(nodeList.get(0),nodeList.get(7)));
			edgeList.add(new Edge(nodeList.get(8),nodeList.get(9)));
			edgeList.add(new Edge(nodeList.get(0),nodeList.get(9)));
			edgeList.add(new Edge(nodeList.get(9),nodeList.get(0)));
			
			
			return edgeList;
			 
		 }
	 }
 
