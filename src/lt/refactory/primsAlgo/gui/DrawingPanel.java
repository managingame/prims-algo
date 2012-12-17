package lt.refactory.primsAlgo.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Properties;

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
	private static final Font FONTFOREDGEWEIGHT = new Font("Arial", Font.BOLD, 12);
	private static int TOOLTIP_DISPLAY_OFFSET = 10;
	private static final long serialVersionUID = 1L;
	private PrimsController controller;
	private TooltipDisplayInfo tooltipDisplayInfo;
	private boolean showLoadingScreen = false;
	private Properties primsProperties;
	
	public void setTooltipDisplayInfo(TooltipDisplayInfo tooltipDisplayInfo) {
		this.tooltipDisplayInfo = tooltipDisplayInfo;
	}

	public DrawingPanel(PrimsController controller) {
		super();
		this.controller = controller;
	}

	public DrawingPanel(PrimsController controller, Properties primsProperties) {
		super();
		this.controller = controller;
		this.primsProperties = primsProperties;
	}

	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		// for antialising geometric shapes
		g2d.addRenderingHints(new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON));
		// for antialiasing text
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		drawMarks(g2d);		
		redrawEdges(g, g2d);
		redrawNodes(g2d);
		if (tooltipDisplayInfo != null) {
			showToolTip(g2d,tooltipDisplayInfo);
		}
		if (showLoadingScreen){
			showLoadingScreen(g2d);
		}
		showLoadingScreen = false;
	}
	
	public void redrawEdges(Graphics g,Graphics2D g2d){
		g.setColor(Color.BLACK);
		int middlePointX;
		int middlePointY;
		
		
		for (Edge edge : controller.getGraph().getEdgeList()) {
			
			middlePointX = GuiMathTool.MiddlePoint(edge).getPointX().intValue();
			middlePointY = GuiMathTool.MiddlePoint(edge).getPointY().intValue();
			
			int x1 = edge.getStart().getPointX().intValue();
			int y1 = edge.getStart().getPointY().intValue();
			int x2 = edge.getEnd().getPointX().intValue();
			int y2 = edge.getEnd().getPointY().intValue();
			g.drawLine(x1, y1, x2, y2);
			
			if (primsProperties.getProperty("Settings.ShowEdgeWeights.Value").equals("True")){
				g2d = (Graphics2D) g.create();
				g2d.setFont(FONTFOREDGEWEIGHT);
				g2d.rotate(Math.toRadians(GuiMathTool.Degrees(edge)),middlePointX, middlePointY);
				g2d.drawString(GuiMathTool.Weight(edge)+"",middlePointX , middlePointY);
				g2d.dispose();
			}
		}
	}
	
	public void redrawNodes(Graphics g){
		for (Node point : controller.getGraph().getNodeList()) {
			g.setColor(Color.BLACK);

			int x = point.getPointX().intValue();
			int y = point.getPointY().intValue();
			
			//String xCoord = String.format("%.0f",point.getPointX().doubleValue());
			//String yCoord = String.format("%.0f",point.getPointY().doubleValue());
			//g.drawString(xCoord + ":" + yCoord, x - 4, y - 6);
			//g.fillOval(x, y, 10, 10);
			g.setColor(Color.BLACK);
			fillCircle(g, x, y, 6);
			g.setColor(Color.RED);
			
			if (point.getNodeType() == NodeType.STEINER) {
				g.setColor(Color.GREEN);
			}
			fillCircle(g, x, y, 4);
		}
	}
	
	private void showToolTip(Graphics2D g2d,TooltipDisplayInfo info) {
		g2d.setColor(Color.WHITE);
		final int TOOLTIP_HEIGHT = 50;
		final int TOOLTIP_WIDTH = 100;		
		
		int x = info.getX() + TOOLTIP_DISPLAY_OFFSET;
		int y = info.getY() + TOOLTIP_DISPLAY_OFFSET;
		
		
		if (info.getX() + TOOLTIP_DISPLAY_OFFSET + TOOLTIP_WIDTH > this.getWidth()){
			x = info.getX() + TOOLTIP_DISPLAY_OFFSET - TOOLTIP_WIDTH;
		}
		
		if (info.getY() + TOOLTIP_DISPLAY_OFFSET + TOOLTIP_HEIGHT > this.getHeight()){
			y = info.getY() + TOOLTIP_DISPLAY_OFFSET - TOOLTIP_HEIGHT;
		}
		
		String infoToDisplay = String.format("X=%.0f Y=%.0f ", 
											info.getNode().getPointX(), 
											info.getNode().getPointY());
		
		g2d.fillRect(x, y, 100, 50);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(x, y, 100, 50);
	
		g2d.drawString(infoToDisplay, x + 20, y + 20);
	}
	
	private void showLoadingScreen(Graphics2D g2d){
		g2d.setColor(Color.BLACK);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                6 * 0.1f));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void drawCircle(Graphics cg, int xCenter, int yCenter, int r) {
		cg.drawOval(xCenter - r, yCenter - r, 2 * r, 2 * r);
	}
	
	public void fillCircle(Graphics cg, int xCenter, int yCenter, int r) {
		cg.fillOval(xCenter - r, yCenter - r, 2 * r, 2 * r);
	}
    
    private void drawMarks(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        
        for (int x = 20; x < getWidth(); x += 20) {
            for (int y = 20; y < getHeight(); y += 20) {
                g2.drawLine(0, y, getWidth(), y);
                g2.drawLine(x, 0, x, getHeight());
            }
        }
    }

	public void setShowLoadingScreen(boolean showLoadingScreen) {
		this.showLoadingScreen = showLoadingScreen;
	}

	public void changedProperties(Properties primsProperties2) {
		this.primsProperties = primsProperties2;
		System.out.println(primsProperties.getProperty("Settings.ShowEdgeWeights.Value"));
		
	}
}
