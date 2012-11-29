package lt.refactory.primsAlgo.gui;

import lt.refactory.primsAlgo.graph.Node;

public class TooltipDisplayInfo {
	private Node node;
	private int x;
	private int y;
	
	public TooltipDisplayInfo(Node node, int x, int y) {
		super();
		this.node = node;
		this.x = x;
		this.y = y;
	}

	public Node getNode() {
		return node;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	

}
