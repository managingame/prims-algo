package lt.refactory.primsAlgo.service;

import java.math.BigDecimal;

import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.service.algorithm.PrimsAlgorithm;

public class PrimsController {
	
	private Graph<WeightedEdge> graph;
	
	public PrimsController(Graph<WeightedEdge> graph) {
		super();
		this.graph = graph;
	}

	public void clear(){
		graph.clear();
	}
	
	public void addNode(Node node) throws AddNodeException{
		graph.addNode(node);
	}
	
	public void solvePrimsAlgorithm() {
		if (graph.getNodeListSize() < 2){
			return;
		}
		Node nodeToRemove = null;
		for (Node node : graph.getNodeList()){
			if (node.getNodeType() == Node.NodeType.STEINER){
				nodeToRemove = node;
			}
		}
		if (nodeToRemove != null){
		graph.removeNodeWithEdges(nodeToRemove);
		}
		try {
			graph.addNode(new Node(BigDecimal.valueOf(350),
					BigDecimal.valueOf(350),
					"STEINERIO",Node.NodeType.STEINER));
		} catch (AddNodeException e) {
			e.printStackTrace();
		}
		graph = Graph.<WeightedEdge>fullGraphFactory(graph.getNodeList());
		graph = PrimsAlgorithm.convertLengthToWeight(graph);
		graph = PrimsAlgoService.getResult(graph);
	}

	public Graph<WeightedEdge> getGraph() {
		return graph;
	}

	public void setGraph(Graph<WeightedEdge> graph) {
		this.graph = graph;
	}		
}
