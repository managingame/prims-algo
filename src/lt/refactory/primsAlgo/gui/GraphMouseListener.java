package lt.refactory.primsAlgo.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class GraphMouseListener implements MouseListener {
	private List<ArrayList<Integer>> pointList ;
	private List<ArrayList<Integer>> edgeList;
	private List<Integer> edge;
	private List<Integer> point;
	private List<Integer> point2;
	
	int x;
	int y;
	GraphPanel panel;
	private long startedwhen;
	private int startedWhereX;
	private int startedWhereY;
	
	private long endedwhen;
	private int endedWhereX;
	private int  endedWhereY;
	
	public GraphMouseListener(GraphPanel panel)
	{
		
		this.panel = panel;
		pointList = new ArrayList<>();
		edgeList = new ArrayList<>();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		endedwhen = e.getWhen();
		endedWhereX = e.getXOnScreen()-3;
		endedWhereY = e.getYOnScreen()-3;
		point = new ArrayList<>();
		point.add(startedWhereX);
		point.add(startedWhereY);
		point2 = new ArrayList<>();
		point2.add(endedWhereX);
		point2.add(endedWhereY);
		
		if (((endedwhen-startedwhen)%600)>200 ||((endedwhen-startedwhen)/600) > 0 )
		{
			if (!pointList.contains(point))
			{
				panel.AddPoint(point);
			}
			if (!pointList.contains(point2))
			{
				panel.AddPoint(point2);
			}
			
			panel.AddEdge(startedWhereX, startedWhereY, endedWhereX, endedWhereY);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		startedwhen = e.getWhen();
		startedWhereX = e.getXOnScreen()-3;
		startedWhereY = e.getYOnScreen()-3;
		
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
		x = e.getXOnScreen()-3;
		y = e.getYOnScreen()-3;
		List<Integer> point = new ArrayList<>();
		point.add(x);
		point.add(y);
		pointList.add((ArrayList<Integer>) point);
		panel.AddPoint(x, y);
		
		
		
	}


}
