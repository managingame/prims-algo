package lt.refactory.primsAlgo.service.algorithm.models;

import java.math.BigDecimal;

public class LinearFunctionParameters {

	private BigDecimal k;
	private BigDecimal b;
	private BigDecimal dx;
	private BigDecimal dy;

	public BigDecimal getK() {
		return k;
	}

	public void setK(BigDecimal k) {
		this.k = k;
	}

	public BigDecimal getB() {
		return b;
	}

	public void setB(BigDecimal b) {
		this.b = b;
	}

	public BigDecimal getDx() {
		return dx;
	}

	public void setDx(BigDecimal dx) {
		this.dx = dx;
	}

	public BigDecimal getDy() {
		return dy;
	}

	public void setDy(BigDecimal dy) {
		this.dy = dy;
	}
}
