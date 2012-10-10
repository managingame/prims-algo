package lt.refactory.primsAlgo.service.algorithm.models;

import java.math.BigDecimal;

import lt.refactory.primsAlgo.graph.Node;

public class Circle {
	private Node centerPoint;
	private BigDecimal radius;
	
	public Circle(Node centerPoint, BigDecimal radius) {
		super();
		this.centerPoint = centerPoint;
		this.radius = radius;
	}

	public Node getCenterPoint() {
		return centerPoint;
	}

	public void setCenterPoint(Node centerPoint) {
		this.centerPoint = centerPoint;
	}

	public BigDecimal getRadius() {
		return radius;
	}

	public void setRadius(BigDecimal radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Circle [centerPoint=" + centerPoint + ", radius=" + radius
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((centerPoint == null) ? 0 : centerPoint.hashCode());
		result = prime * result + ((radius == null) ? 0 : radius.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circle other = (Circle) obj;
		if (centerPoint == null) {
			if (other.centerPoint != null)
				return false;
		} else if (!centerPoint.equals(other.centerPoint))
			return false;
		if (radius == null) {
			if (other.radius != null)
				return false;
		} else if (!radius.equals(other.radius))
			return false;
		return true;
	}
}
