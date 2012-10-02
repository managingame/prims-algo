package lt.refactory.primsAlgo.service;

import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.service.algorithm.PrimsAlgorithm;
import lt.refactory.primsAlgo.service.algorithm.SteinersAlgorithm;

public class PrimsAlgoService {
	
	private PrimsAlgorithm primsAlgorithm;
	private SteinersAlgorithm steinersAlgorithm;
	
	public PrimsAlgoService(){
		primsAlgorithm = new PrimsAlgorithm();
		steinersAlgorithm = new SteinersAlgorithm();
	}
	
	public Graph<WeightedEdge> getSmallestTree(Graph<WeightedEdge> graph){
		throw new UnsupportedOperationException();
	}
	


}
