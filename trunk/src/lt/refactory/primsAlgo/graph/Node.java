package lt.refactory.primsAlgo.graph;

import java.math.BigDecimal;

/**
 * Point class for saving point coordinates in x and y coordinate system.
 * @author arminas
 *
 */
enum NodeType{
	NORMAL,STEINER
}
public class Node {
	private final BigDecimal pointX;
	private final BigDecimal pointY;
	private final String name;
	private final NodeType nodeType;
	
	
	
	public Node(BigDecimal pointX, BigDecimal pointY, String name) {
		super();
		this.pointX = pointX;
		this.pointY = pointY;
		this.name = name;
		this.nodeType = NodeType.NORMAL;
	}
	
	public Node(BigDecimal pointX, BigDecimal pointY) {
		super();
		this.pointX = pointX;
		this.pointY = pointY;
		this.name = "";
		this.nodeType = NodeType.NORMAL;
	}
	
	public BigDecimal getPointX() {
		return pointX;
	}
	public BigDecimal getPointY() {
		return pointY;
	}
	public String getName() {
		return name;
	}
	public NodeType getNodeType() {
		return nodeType;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pointX == null) ? 0 : pointX.hashCode());
		result = prime * result + ((pointY == null) ? 0 : pointY.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pointX == null) {
			if (other.pointX != null)
				return false;
		} else if (!pointX.equals(other.pointX))
			return false;
		if (pointY == null) {
			if (other.pointY != null)
				return false;
		} else if (!pointY.equals(other.pointY))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [pointX=" + pointX + ", pointY=" + pointY + ", name="
				+ name + "]";
	}

	
}
