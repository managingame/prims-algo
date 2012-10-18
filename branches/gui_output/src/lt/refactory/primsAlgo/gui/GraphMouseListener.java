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
	public final static int TVORA = 5;
	
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
		System.out.println("Realise");
		endedwhen = e.getWhen();
		endedWhereX = e.getXOnScreen()-3-panel.getLocationOnScreen().x;
		endedWhereY = e.getYOnScreen()-3-panel.getLocationOnScreen().y;
		point = new ArrayList<>();
		point2 = new ArrayList<>();
		point.add(startedWhereX);
		point.add(startedWhereY);
		
		
		if (((endedwhen-startedwhen)%600)>200 ||((endedwhen-startedwhen)/600) > 0 )
		{
		
			for (ArrayList<Integer> point : pointList) {
				System.out.println("cia turetu kazka spausdinti"+point.toString());
				if (isNear(point,endedWhereX,endedWhereY)){
					endedWhereX = point.get(0);
					endedWhereY = point.get(1);
					break;
				}
			}
			
			
			point2.add(endedWhereX);
			point2.add(endedWhereY);
			panel.AddPoint(point);
			panel.AddPoint(point2);
			panel.AddEdge(startedWhereX, startedWhereY, endedWhereX, endedWhereY);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Kviestas press");
		startedwhen = e.getWhen();
		startedWhereX = e.getXOnScreen()-3-panel.getLocationOnScreen().x;
		startedWhereY = e.getYOnScreen()-3-panel.getLocationOnScreen().y;
		for (ArrayList<Integer> point : pointList) {
			if (isNear(point,startedWhereX,startedWhereY))
			{
				startedWhereX = point.get(0);
				startedWhereY = point.get(1);
				break;
			}
		}
		
	}
	
	private boolean isNear(ArrayList<Integer> point3, int startedWhereX2,
			int startedWhereY2) {
		int diferenceXAxis = Math.abs(point3.get(0)-startedWhereX2);
		int diferenceYAxis = Math.abs(point3.get(1)-startedWhereY2);
		System.out.println("Skirtumas:"+diferenceXAxis+"x asi"+diferenceYAxis);
			if (diferenceXAxis <=5+TVORA && diferenceYAxis <=5+TVORA){
				return true;
			}else{
				return false;
			}
			
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
		System.out.println("Kviestas click");
		x = e.getXOnScreen()-3-panel.getLocationOnScreen().x;
		y = e.getYOnScreen()-3-panel.getLocationOnScreen().y;
		List<Integer> point = new ArrayList<>();
		
		for (ArrayList<Integer> testPoint : pointList) {
			if(isNear(testPoint, x, y))
			{
				x = testPoint.get(0);
				y = testPoint.get(1);
				
			}
		}
		point.add(x);
		point.add(y);
		pointList.add((ArrayList<Integer>) point);
		
		panel.AddPoint(x, y);
		
		
		
	}


}
