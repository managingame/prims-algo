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
			int x1 = edge.getStart().getPointX().intValue();
			int y1 = edge.getStart().getPointY().intValue();
			int x2 = edge.getEnd().getPointX().intValue();
			int y2 = edge.getEnd().getPointY().intValue();
			g.drawLine(x1, y1, x2, y2);
			
			
			g2d = (Graphics2D) g.create();
			g2d.rotate(Math.toRadians(GuiMathTool.Degrees(edge)),middlePointX, middlePointY);
			g2d.setFont(FONTFOREDGEWEIGHT);
		
			g2d.drawString(GuiMathTool.Weight(edge)+"",middlePointX , middlePointY);
			g2d.dispose();
		}

		for (Node point : controller.getGraph().getNodeList()) {
			g.setColor(Color.BLACK);

			int x = point.getPointX().intValue();
			int y = point.getPointY().intValue();
			g.drawString(point.getPointX() + ":" + point.getPointY(), x - 4,
					y - 6);
			//g.fillOval(x, y, 10, 10);
			g.setColor(Color.BLACK);
			fillCircle(g, x, y, 6);
			g.setColor(Color.RED);
			fillCircle(g, x, y, 4);
			
			if (point.getNodeType() == NodeType.STEINER) {
				g.setColor(Color.GREEN);
			}
			//g.fillOval(x + 3, y + 3, 5, 5);


		}
	}

	public void drawCircle(Graphics cg, int xCenter, int yCenter, int r) {

		cg.drawOval(xCenter - r, yCenter - r, 2 * r, 2 * r);
	}
	
	public void fillCircle(Graphics cg, int xCenter, int yCenter, int r) {

		cg.fillOval(xCenter - r, yCenter - r, 2 * r, 2 * r);
	}
}
