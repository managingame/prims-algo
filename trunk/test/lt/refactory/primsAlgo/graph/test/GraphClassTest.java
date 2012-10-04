/**
 * 
 */
package lt.refactory.primsAlgo.graph.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.graph.exception.RemoveNodeException;

import org.junit.Test;

/**
 * @author Arminas
 * @Tester Egidijus
 */
public class GraphClassTest {
	static List<Node> nodeList ;
	static List<Edge> edgeList ;
	
	static{
		nodeList = TestObjectsFactory.nodesFactory();
		edgeList = TestObjectsFactory.edgeFactory();		
	}
	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#addNode(lt.refactory.primsAlgo.graph.Node)}.
	 * @throws AddNodeException 
	 */
	@Test
	public void testAddNode() throws AddNodeException {
		Graph<Edge> graph = new Graph<>();
		List<Node> correctGraphList = new ArrayList<>();
		
		// putting nodes into graph and into correctGraph 
		// If Both of them will have same amount of nodes , test
		// will be approved
		
		for(int i=0; i < nodeList.size(); i++){
			graph.addNode(nodeList.get(i));
			
			if(!correctGraphList.contains(nodeList.get(i))){
				correctGraphList.add(nodeList.get(i));
			}	
		}
		
		
		// nodeList listed below is this method object not static one.
		List<Node> nodeList = graph.getNodeList();           
		assertTrue(nodeList.size()==correctGraphList.size());
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(0)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(1)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(2)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(3)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(4)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(5)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(6)));
		assertTrue(graph.containsNode(GraphClassTest.nodeList.get(7)));
	    // Passed the test : method addNode works properly.
		 
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#removeNode(lt.refactory.primsAlgo.graph.Node)}.
	 * @throws AddNodeException 
	 * @throws RemoveNodeException 
	 */
	@Test
	
	public void testRemoveNode() throws AddNodeException, RemoveNodeException {
		Graph<Edge> graph = new Graph<>();
		List<Node> correctGraph = new ArrayList<>();
		
		for(int i=0; i < nodeList.size();i++){
			graph.addNode(nodeList.get(i));
			if (!correctGraph.contains(nodeList.get(i))){
				correctGraph.add(nodeList.get(i));
			}
			
		}
		
		graph.removeNode(nodeList.get(0));
		correctGraph.remove(0);
		

		assertFalse(graph.containsNode(nodeList.get(0)));
		assertFalse(correctGraph.contains(nodeList.get(0)));
	
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#addEdge(lt.refactory.primsAlgo.graph.Edge)}.
	 * @throws AddEdgeException 
	 * @throws AddNodeException 
	 */
	@Test
	public void testAddEdge() throws AddEdgeException, AddNodeException {
		
		Graph<Edge> graph = new Graph<>();
		List <Edge> correctGraph = new ArrayList<>();
		correctGraph.clear();
		for (int h=0; h < nodeList.size(); h++ ){
			graph.addNode(nodeList.get(h));
		}
		System.out.println(graph.getNodeListSize());
		System.out.println(edgeList.size()+"]\n");
		System.out.println(edgeList.toString());
		for(int i=0; i< edgeList.size(); i++){
			graph.addEdge(edgeList.get(i));
			
			System.out.println("\n"+i);
			if(correctGraph.contains(edgeList.get(i))){
				System.out.println("Grafas jau kontainina briauna"+edgeList.get(i).toString());
				
			}else{
				correctGraph.add(edgeList.get(i));
			}
			
		}
		
		List<Edge> edgeList = graph.getEdgeList();
		System.out.println(edgeList.size()+"vs"+correctGraph.size());
		assertTrue(edgeList.size()==correctGraph.size());
		
		// Error : addEdge method adds all edges no matter where they are
		
		
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#removeEdge(lt.refactory.primsAlgo.graph.Edge)}.
	 */
	@Test
	public void testRemoveEdge() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#getNearEdges(lt.refactory.primsAlgo.graph.Node)}.
	 */
	@Test
	public void testGetNearEdgesNode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#getNearEdges(lt.refactory.primsAlgo.graph.Edge)}.
	 */
	@Test
	public void testGetNearEdgesT() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link lt.refactory.primsAlgo.graph.Graph#getNearNodes(lt.refactory.primsAlgo.graph.Node)}.
	 * @throws AddNodeException 
	 * @throws AddEdgeException 
	 */
	@Test
	public void testGetNearNodes() throws AddNodeException, AddEdgeException {
		fail("Someday it will be implemented");
	}
	
	@Test
	public void testEdgeHashCodeAndEquals(){
		
		Edge edge1 = edgeList.get(3);
		Edge edge2 = edgeList.get(4);
		
		assertTrue(edge1.equals(edge2));
		assertTrue(edge1.hashCode() == edge2.hashCode());
	}
	@Test
	public void testEdgeHashCodeAndEquals(){
		Edge edge1 = edgeList.get(3);
		Edge edge2 = edgeList.get(4);
		
		assertTrue(edge1.equals(edge2));
		assertTrue(edge1.hashCode() == edge2.hashCode());
	}

}
