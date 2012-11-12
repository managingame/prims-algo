package lt.refactory.primsAlgo.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.service.PrimsController;

public class DrawingPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrimsController controller;
	
	public DrawingPanel(PrimsController controller) {
		super();
		this.controller = controller;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		
		for (Edge edge : controller.getGraph().getEdgeList()) {
			g.drawLine(edge.getStart().getPointX().intValue(), edge.getStart()
					.getPointY().intValue() + 5, edge.getEnd().getPointX()
					.intValue() + 5, edge.getEnd().getPointY().intValue() + 5);
		}

		for (Node point : controller.getGraph().getNodeList()) {

			g.drawString(point.getPointX() + ":" + point.getPointY(), point
					.getPointX().intValue() - 4,
					point.getPointY().intValue() - 6);
			g.fillOval(point.getPointX().intValue(), point.getPointY()
					.intValue(), 10, 10);
			g.setColor(Color.RED);
			if (point.getNodeType() == Node.NodeType.STEINER){
				g.setColor(Color.GREEN);
			}
			g.fillOval(point.getPointX().intValue() + 3, point.getPointY()
					.intValue() + 3, 5, 5);
			g.setColor(Color.BLACK);
		}
	}
}
