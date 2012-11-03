package lt.refactory.primsAlgo.gui;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;

public class GraphMouseListener implements MouseListener {
	private Graph<Edge> graph ;
	
	
	public final static int GAP = 15;
	
	BigDecimal x;
	BigDecimal y;
	GraphPanel panel;
	public GraphMouseListener(GraphPanel panel)
	{
		
		this.panel = panel;
		graph = new Graph<>();
		
	}
	
	
		
	

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		x = BigDecimal.valueOf(e.getXOnScreen()-3-panel.getLocationOnScreen().x);
		y = BigDecimal.valueOf(e.getYOnScreen()-3-panel.getLocationOnScreen().y);
		Node newNode = new Node(x,y);
		
		for (Node node : graph.getNodeList()) {
			if(isNear(node,newNode))
			{
				x = node.getPointX();
				y = node.getPointY();
				
			}
		}
		try {
			graph.addNode(new Node(x, y));
		} catch (AddNodeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			panel.AddPoint(x,y);
		} catch (AddNodeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	

	}

	private boolean isNear(Node node, Node newNode) {
		if (Math.abs(node.getPointX().intValue() - newNode.getPointX().intValue()) <= GAP 
			&& Math.abs(node.getPointY().intValue()-newNode.getPointY().intValue()) <= GAP ){
			return true;
		}
		return false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = BigDecimal.valueOf(e.getXOnScreen()-3-panel.getLocationOnScreen().x);
		y = BigDecimal.valueOf(e.getYOnScreen()-3-panel.getLocationOnScreen().y);
		Node newNode = new Node(x,y);
		
		for (Node node : graph.getNodeList()) {
			if(isNear(node,newNode))
			{
				x = node.getPointX();
				y = node.getPointY();
				
			}
		}
		try {
			graph.addNode(new Node(x, y));
		} catch (AddNodeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			panel.AddPoint(x,y);
		} catch (AddNodeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
