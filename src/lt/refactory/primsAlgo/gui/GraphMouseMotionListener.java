package lt.refactory.primsAlgo.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GraphMouseMotionListener implements MouseMotionListener {

	JPanel panel;
	int checkedPointX;
	int checkedPointY;
	List<ArrayList<Integer>> pointList = new ArrayList<>();
	
	public GraphMouseMotionListener(GraphPanel panel) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
			
	}
	public void AddPoint(ArrayList<Integer> point){
		pointList.add(point);
	}

}
