package lt.refactory.primsAlgo.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class GraphMouseListener implements MouseListener {

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
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		endedwhen = e.getWhen();
		endedWhereX = e.getXOnScreen();
		endedWhereY = e.getYOnScreen();
		if (((endedwhen-startedwhen)%600)>200 ||((endedwhen-startedwhen)/600) > 0 )
		{
			
			panel.AddEdge(startedWhereX, startedWhereY, endedWhereX, endedWhereY);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		startedwhen = e.getWhen();
		startedWhereX = e.getXOnScreen();
		startedWhereY = e.getYOnScreen();
		
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
		panel.AddPoint(x, y);
		
		
		
	}


}
