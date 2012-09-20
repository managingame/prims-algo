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
		this.weight = BigDecimal.valueOf(0);
	}
	public WeightedEdge(Edge edge,BigDecimal weight){
		super(edge);
		this.weight = weight;
	}
	
	public BigDecimal getWeight() {
		return weight;
	}
	@Override
	public String toString() {
		return "WeightedEdge [weight=" + weight + ", start=" + getStart()
				+ " end=" + getEnd() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeightedEdge other = (WeightedEdge) obj;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}	

}
