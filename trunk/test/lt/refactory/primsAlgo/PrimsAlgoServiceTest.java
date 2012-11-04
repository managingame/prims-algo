package lt.refactory.primsAlgo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.test.TestObjectsFactory;
import lt.refactory.primsAlgo.service.PrimsAlgoService;
import lt.refactory.primsAlgo.service.algorithm.PrimsAlgorithm;

import org.junit.Test;

public class PrimsAlgoServiceTest {

	@Test
	public void test() {
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.add(TestObjectsFactory.enumNode.A.getNode());
		nodeList.add(TestObjectsFactory.enumNode.B.getNode());
		nodeList.add(TestObjectsFactory.enumNode.C.getNode());
		nodeList.add(TestObjectsFactory.enumNode.D.getNode());
		nodeList.add(TestObjectsFactory.enumNode.E.getNode());

		Graph<WeightedEdge> graph = Graph.<WeightedEdge>fullGraphFactory(nodeList);
		graph = PrimsAlgorithm.convertLengthToWeight(graph);
		
		graph = PrimsAlgoService.getResult(graph);
		
	}

}
