package lt.refactory.primsAlgo.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.enums.NodeType;
import lt.refactory.primsAlgo.gui.MathTool.GuiMathTool;
import lt.refactory.primsAlgo.service.PrimsController;

public class DrawingPanel extends JPanel {
	/**
	 * 
	 */
	private static final Font FONTFOREDGEWEIGHT = new Font("Arial", Font.BOLD, 14);
	private static final long serialVersionUID = 1L;
	private PrimsController controller;
	
	public DrawingPanel(PrimsController controller) {
		super();
		this.controller = controller;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d ;
		g.setColor(Color.BLACK);
		
		int middlePointX;
		int middlePointY;
		
		for (Edge edge : controller.getGraph().getEdgeList()) {
			
			middlePointX = GuiMathTool.MiddlePoint(edge).getPointX().intValue();
			middlePointY = GuiMathTool.MiddlePoint(edge).getPointY().intValue();
			
			
			g.drawLine(edge.getStart().getPointX().intValue(), edge.getStart()
					.getPointY().intValue() + 5, edge.getEnd().getPointX()
					.intValue() + 5, edge.getEnd().getPointY().intValue() + 5);
			
			
			g2d = (Graphics2D) g.create();
			g2d.rotate(Math.toRadians(GuiMathTool.Degrees(edge)),middlePointX, middlePointY);
			g2d.setFont(FONTFOREDGEWEIGHT);
		
			g2d.drawString(GuiMathTool.Weight(edge)+"",middlePointX , middlePointY);
			g2d.dispose();
		}

		for (Node point : controller.getGraph().getNodeList()) {

			g.drawString(point.getPointX() + ":" + point.getPointY(), point
					.getPointX().intValue() - 4,
					point.getPointY().intValue() - 6);
			g.fillOval(point.getPointX().intValue(), point.getPointY()
					.intValue(), 10, 10);
			g.setColor(Color.RED);
			if (point.getNodeType() == NodeType.STEINER){
				g.setColor(Color.GREEN);
			}
			g.fillOval(point.getPointX().intValue() + 3, point.getPointY()
					.intValue() + 3, 5, 5);
			g.setColor(Color.BLACK);
		}
	}
}
