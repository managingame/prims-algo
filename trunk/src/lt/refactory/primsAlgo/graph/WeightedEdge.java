package lt.refactory.primsAlgo.graph;

import java.math.BigDecimal;

/**
 * Edge class with aditional information to save edge weight.
 * @author arminas
 *
 */
public class WeightedEdge extends Edge{
	private BigDecimal weight;
	
	public WeightedEdge(Edge edge){
		super(edge);
		weight = BigDecimal.valueOf(0);
	}
	
	public BigDecimal getWeight() {
		return weight;
	}

}
