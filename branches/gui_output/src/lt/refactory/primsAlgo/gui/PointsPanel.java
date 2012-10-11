package lt.refactory.primsAlgo.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PointsPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x , y = -1;
	JPanel panel ;
	public PointsPanel()
	{
		panel = new JPanel(null);
		
		panel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println("Paspaude");
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	@Override
    public void paint(Graphics g) {
    	super.paint(g);
    	System.out.println("taip");
    	panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    	
    	if (this.x!=-1 && this.y!=-1)
    	{
    		System.out.println(x+":"+y);
    		g.drawOval(x,y,100,100);
    		g.drawLine(5, 5 ,5, 5);
    	}
    }
}