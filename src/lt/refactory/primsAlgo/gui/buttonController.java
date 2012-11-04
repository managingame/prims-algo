package lt.refactory.primsAlgo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buttonController implements ActionListener {
	GraphPanel panel;
	 
	
		public buttonController(GraphPanel panel)
		{
			this.panel = panel;
		}
		 @Override
		 // Identifying button name and giving the order to panel 
		 public void actionPerformed(ActionEvent e) {
			String commandName = e.getActionCommand();
			switch(commandName){
			case "Rasti Šteinerio tašką" :		panel.ActivateSteinerMethod(); break;
			case "Konstruoti pilnaji grafa" :	panel.BuildFullGraph(); break;
			case "Dar nenuspresta" :			panel.Additional(); break;
			default : 							System.out.println("Mygtukas nebuvo atpažintas : "+commandName);break;
			}
			
		}

}
