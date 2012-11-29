package lt.refactory.primsAlgo.gui.customListeners;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.service.PrimsController;

public class GraphMouseListener extends MouseAdapter {
	public final static int GAP = 15;
	
	
	private JPanel panel;
	private PrimsController controller;
	private JCheckBox solveOnClick;
	public GraphMouseListener(JPanel panel,PrimsController controller,JCheckBox solveOnClick) {

		this.panel = panel;
		this.controller = controller;
		this.solveOnClick = solveOnClick;
		

	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("movedc");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		BigDecimal x = BigDecimal.valueOf(e.getX());
		BigDecimal y = BigDecimal.valueOf(e.getY());
		Node newNode = new Node(x,y);
		
		for (Node node : controller.getGraph().getNodeList()) {
			if (isNear(node, newNode)) {
				return;
			}
		}
		
		try {
			controller.addNode(newNode);
			panel.repaint();
		} catch (AddNodeException e1) {
			e1.printStackTrace();
		}
		
		if (solveOnClick != null && solveOnClick.isSelected()) {
			controller.solvePrimsAlgorithm(true);
		}

	}
	
	


	private boolean isNear(Node node, Node newNode) {
		if (Math.abs(node.getPointX().intValue() - newNode.getPointX().intValue()) <= GAP 
			&& Math.abs(node.getPointY().intValue()-newNode.getPointY().intValue()) <= GAP ){
			return true;
		}
		return false;
	}
	
	

	

}
