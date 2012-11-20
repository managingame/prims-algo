package lt.refactory.primsAlgo.gui.MathTool;

import java.math.BigDecimal;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Node;

public class GuiMathTool {
	
	public static double Degrees(Node start ,Node end){
		int multiplier;
	
		double statinioPrieKampoIlgis;
		double statinioPriesKampaIlgis;
		
		if (start.getPointX().compareTo(end.getPointX()) >= 1 ){	
			if (start.getPointY().compareTo(end.getPointY()) > 1){
				multiplier = 1;
				statinioPrieKampoIlgis = start.getPointX().subtract(end.getPointX()).doubleValue();
				statinioPriesKampaIlgis = start.getPointY().subtract(end.getPointY()).doubleValue();
			}else{
				multiplier = -1;
				statinioPrieKampoIlgis = start.getPointX().subtract(end.getPointX()).doubleValue();
				statinioPriesKampaIlgis = end.getPointY().subtract(start.getPointY()).doubleValue();
			}
		}else{
			if (start.getPointY().compareTo(end.getPointY()) > 1){
				multiplier = -1;
				statinioPrieKampoIlgis = end.getPointX().subtract(start.getPointX()).doubleValue();
				statinioPriesKampaIlgis = start.getPointY().subtract(end.getPointY()).doubleValue();
			}else{
				multiplier = 1;
				statinioPrieKampoIlgis = end.getPointX().subtract(start.getPointX()).doubleValue();
				statinioPriesKampaIlgis = end.getPointY().subtract(start.getPointY()).doubleValue();
			}
		}
		double degree = multiplier * Math.toDegrees(Math.atan(statinioPriesKampaIlgis/statinioPrieKampoIlgis));

		return degree;		
	}
	public static double Degrees(Edge edge){
		return GuiMathTool.Degrees(edge.getStart(), edge.getEnd());
	}
	public static Node MiddlePoint(Edge edge){
		return GuiMathTool.MiddlePoint(edge.getStart(),edge.getEnd());
	}
	
	public static Node MiddlePoint(Node start, Node end) {
		double maximumX = Math.max(start.getPointX().doubleValue(),end.getPointX().doubleValue());
		double minimumX = Math.min(start.getPointX().doubleValue(),end.getPointX().doubleValue());
		
		double maximumY = Math.max(start.getPointY().doubleValue(),end.getPointY().doubleValue());
		double minimumY = Math.min(start.getPointY().doubleValue(),end.getPointY().doubleValue());
		
		
		BigDecimal middleX = BigDecimal.valueOf(minimumX + ((maximumX-minimumX)/2));
		BigDecimal middleY = BigDecimal.valueOf(minimumY + ((maximumY-minimumY)/2));
		
		return new Node(middleX, middleY);
	}
	public static double Weight(Edge edge){
		return Weight(edge.getStart(),edge.getEnd());
	}
	public static double Weight(Node start, Node end) {
		double maximumX = Math.max(start.getPointX().doubleValue(),end.getPointX().doubleValue());
		double minimumX = Math.min(start.getPointX().doubleValue(),end.getPointX().doubleValue());
		
		double maximumY = Math.max(start.getPointY().doubleValue(),end.getPointY().doubleValue());
		double minimumY = Math.min(start.getPointY().doubleValue(),end.getPointY().doubleValue());
		
		double weight = Math.sqrt(Math.pow((maximumX-minimumX), 2)+Math.pow((maximumY-minimumY), 2));
		return Math.round(weight);
	}
}
