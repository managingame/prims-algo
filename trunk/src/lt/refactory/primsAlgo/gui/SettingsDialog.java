package lt.refactory.primsAlgo.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class SettingsDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7491690062482923663L;

	JPanel settingsPanel;
	JButton applyButton;

	private String encodedColorForBackground ;
	
	private String encodedColorForButtons;
	
	private GridLayout layout;

	private Properties primsProperties;

	private JTextField textF;

	private JTextField textF2;
	
	public SettingsDialog() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	public SettingsDialog(JFrame owner, Properties primsProperties) {
		super(owner);
		System.out.println("kviestas pagrindinis");
		this.primsProperties = primsProperties;
		setSize(600,300);
		setTitle("Nustatymai");
	    layout = new GridLayout(10,4);
		layout.addLayoutComponent("something", new JButton("kazkas"));
		initComponents();
		add(settingsPanel);
		show(true);
	}
	
	@SuppressWarnings("deprecation")
	public SettingsDialog(JFrame owner) {
		super(owner);
		System.out.println("kviestas pagrindinis2");
		setSize(600,300);
		setTitle("Nustatymai");
	    layout = new GridLayout(10,4);
		layout.addLayoutComponent("something", new JButton("kazkas"));
		initComponents();
		add(settingsPanel);
		
		show(true);
		
		// TODO Auto-generated constructor stub
	}

	private void initComponents() {
		settingsPanel = new JPanel();
		settingsPanel.setLayout(layout);
		applyButton = new  JButton("Apply changes");
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,2));
		
		
		panel.setLayout(new GridLayout(1,2));
		panel.add(new JLabel("Dimensija",SwingConstants.LEFT));
		panel.add(new JSeparator(SwingConstants.VERTICAL));
		//label.add(new JSeparator(SwingConstants.VERTICAL));
		ButtonGroup buttonuGroupas = new ButtonGroup();
		AbstractButton colorPickingButton = new JButton("spalva...");
		
		
		colorPickingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Color color = JColorChooser.showDialog(null, "Choose color", Color.black);
				String rgb = Integer.toHexString(color.getRGB());
				
				encodedColorForBackground = rgb;
				System.out.println(rgb);			
				
			}
		});
		
		JLabel dimensijaX = new JLabel("X: ",SwingConstants.RIGHT);
		textF = new JTextField();
		textF2 = new JTextField();
		
		JLabel dimensijaY = new JLabel("Y: ", SwingConstants.RIGHT);
		textF.setText(primsProperties.getProperty("DimensionX"));
		textF2.setText(primsProperties.getProperty("DimensionY"));
		panel.add(dimensijaX);
		panel.add(textF);
		panel.add(dimensijaY);
		panel.add(textF2);
		panel2.add(new JLabel("Background spalva", SwingConstants.RIGHT));
		panel2.add(new JSeparator(SwingConstants.VERTICAL));
		
		
		panel2.add(colorPickingButton);
		
		
		
		settingsPanel.add(panel);
		settingsPanel.add(panel2);
		settingsPanel.add(applyButton);
		
		
		
		
			
				
				
				settingsPanel.setVisible(true);
			
		applyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				primsProperties.setProperty("DimensionX", textF.getText());
				primsProperties.setProperty("DimensionY", textF2.getText());
				if (encodedColorForBackground != null)
					primsProperties.setProperty("BackgroundColor", encodedColorForBackground);
				try{
					URL ur = this.getClass().getResource("PrimsProperties.xml");
					OutputStream outp = new FileOutputStream(new File(ur.toURI()));
					primsProperties.storeToXML(outp, "Prims properties", "UTF-8");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		//add(settingsPanel);
		
	}


	public SettingsDialog(Window owner) {
		super(owner);
		
	}


	

	
}
