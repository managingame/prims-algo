package lt.refactory.primsAlgo.gui.customComponents;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageButton extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIcon normalImage;
	private ImageIcon hoverImage;
	
	public ImageButton(String title,String imageFile,String hoverFile){
		super();

		this.normalImage = new ImageIcon(getClass().getResource(imageFile));
				
		this.hoverImage = new ImageIcon(getClass().getResource(hoverFile));
		this.setText(title);
		this.setIcon(normalImage);
		this.addMouseListener(onHoverListener);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}
	
	
	private MouseListener onHoverListener = new MouseAdapter() {
		public void mouseEntered(java.awt.event.MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			label.setIcon(hoverImage);
		};

		public void mouseExited(java.awt.event.MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			label.setIcon(normalImage);
		};
	};

}
