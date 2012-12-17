package lt.refactory.primsAlgo.gui;

import java.awt.Color;
import java.awt.GridLayout;
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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SettingsDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7491690062482923663L;

	JPanel settingsPanel;
	JButton applyButton;
	
	private JButton colorPickingButton;
	
	private String encodedColorForBackground ;
	
	private String encodedColorForButtons;
	
	private GridLayout layout;

	private Properties primsProperties;

	private JTextField textFieldForXCordinate;

	private JTextField textFieldForYCordinate;

	private Color color;

	private JLabel dimensionXLabel;

	private JLabel dimensionYLabel;

	private JButton buttonColorPickingButton;
	
	private JCheckBox checkRepaint;
	
	private JCheckBox showTooltips;
	
	private JCheckBox showEdgesWeight;
	
	private JCheckBox showNodeCordinates;

	private NewAppFrame owner;
	
	public SettingsDialog() {
		super();
	}

	public SettingsDialog(NewAppFrame owner, Properties primsProperties) {
		super(owner);
		this.owner = owner;
		this.primsProperties = primsProperties;	
		initComponents();
		this.setVisible(true);
	}
	
	public SettingsDialog(JFrame owner) {
		super(owner);
		initComponents();
		add(settingsPanel);	
		this.setVisible(true);
	}

	private void initComponents() {
		setSize(600,300);
		setTitle(primsProperties.getProperty("Settings.Title"));
	    layout = new GridLayout(10, 5);
	        
		settingsPanel = new JPanel();
		settingsPanel.setLayout(layout);
		
		applyButton = new  JButton(primsProperties.getProperty("Settings.ApplyButton"));
		checkRepaint = new JCheckBox("Taip");
		showTooltips = new JCheckBox("Taip");
		showNodeCordinates = new JCheckBox("Taip");
		showEdgesWeight = new JCheckBox("Taip");
		
		
		
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();
		
		panel.setLayout(new GridLayout(1,5));
		
		
		JLabel dimensionText = new JLabel("Dimensija ", SwingConstants.LEFT);
		dimensionText.setBounds(50, 150, 10, 50);
				
		colorPickingButton = new JButton(primsProperties.getProperty("Settings.ButtonForBackground"));
		buttonColorPickingButton = new JButton(primsProperties.getProperty("Settings.ButtonForButtonsForeground"));
		

		dimensionXLabel = new JLabel("X: ",SwingConstants.RIGHT);
		dimensionYLabel = new JLabel("Y: ", SwingConstants.RIGHT);
		
		textFieldForXCordinate = new JTextField();
		textFieldForYCordinate = new JTextField();
		
		panel.add(dimensionText);
		panel.add(new JSeparator(SwingConstants.VERTICAL));
		panel.add(dimensionXLabel);	
		panel.add(textFieldForXCordinate);
		panel.add(dimensionYLabel);
		panel.add(textFieldForYCordinate);
		
		panel2.setLayout(new GridLayout(1, 2));
		panel2.add(new JLabel("Background spalva", SwingConstants.LEFT));	
		panel2.add(new JSeparator(SwingConstants.VERTICAL));
		panel2.add(new JLabel());
		panel2.add(colorPickingButton);
		
		panel3.setLayout(new GridLayout(1,3));
		panel3.add(new JLabel("Mygtukų teksto spalvos", SwingConstants.LEFT));
		panel3.add(new JSeparator(SwingConstants.VERTICAL));
		panel3.add(new JLabel());
		panel3.add(buttonColorPickingButton);
		
		panel4.setLayout(new GridLayout(1,4));
		panel4.add(new JLabel(primsProperties.getProperty("Settings.Label.Repaint"), SwingConstants.LEFT));
		panel4.add(new JSeparator(SwingConstants.VERTICAL));
		panel4.add(checkRepaint);
		
		panel5.setLayout(new GridLayout(1,4));
		panel5.add(new JLabel(primsProperties.getProperty("Settings.Label.ShowTooltip"), SwingConstants.LEFT));
		panel5.add(new JSeparator(SwingConstants.VERTICAL));
		panel5.add(showTooltips);
		
		panel6.setLayout(new GridLayout(1,4));
		panel6.add(new JLabel(primsProperties.getProperty("Settings.Label.ShowEdgeWeights"), SwingConstants.LEFT));
		panel6.add(new JSeparator(SwingConstants.VERTICAL));
		panel6.add(showEdgesWeight);
		
		panel7.setLayout(new GridLayout(1,4));
		panel7.add(new JLabel(primsProperties.getProperty("Settings.Label.ShowNodeCoordinates"), SwingConstants.LEFT));
		panel7.add(new JSeparator(SwingConstants.VERTICAL));
		panel7.add(showNodeCordinates);
		
		
		
		
		
		
		
		
		textFieldForXCordinate.setText(primsProperties.getProperty("DimensionX"));
		textFieldForYCordinate.setText(primsProperties.getProperty("DimensionY"));
		
		settingsPanel.add(panel);
		settingsPanel.add(panel2);
		settingsPanel.add(panel3);
		settingsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		settingsPanel.add(panel4);
		settingsPanel.add(panel5);
		settingsPanel.add(panel6);
		settingsPanel.add(panel7);
		
		settingsPanel.add(applyButton);
		add(settingsPanel);
		
		settingsPanel.setVisible(true);
			
		applyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				primsProperties.setProperty("DimensionX", textFieldForXCordinate.getText());
				primsProperties.setProperty("DimensionY", textFieldForYCordinate.getText());
				
				
				
				if (checkRepaint.isSelected()){
					primsProperties.setProperty("Settings.Repaint.Value","True");
				}else{
					primsProperties.setProperty("Settings.Repaint.Value","False");
				}
				
				if (showEdgesWeight.isSelected()){
					primsProperties.setProperty("Settings.ShowEdgeWeights.Value","True");
				}else{
					primsProperties.setProperty("Settings.ShowEdgeWeights.Value","False");
				}
				
				if (showNodeCordinates.isSelected()){
					primsProperties.setProperty("Settings.ShowNodeCoordinates.Value","True");
				}else{
					primsProperties.setProperty("Settings.ShowNodeCoordinates.Value","False");
				}
				
				if (showTooltips.isSelected()){
					primsProperties.setProperty("Settings.ShowTooltip.Value","True");
				}else{
					primsProperties.setProperty("Settings.ShowTooltip.Value","False");
				}
				
				System.out.println(primsProperties.getProperty("Settings.ShowEdgeWeights.Value"));
				
				if (encodedColorForBackground != null){
					settingsPanel.setBackground(color);					
					primsProperties.setProperty("BackgroundColor", encodedColorForBackground);
					primsProperties.setProperty("ButtonColor", encodedColorForButtons);
				}
				try{
					URL ur = this.getClass().getResource("PrimsProperties.xml");
					OutputStream outp = new FileOutputStream(new File(ur.toURI()));
					primsProperties.storeToXML(outp, "Prims properties", "UTF-8");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
				
				owner.changeProperties(primsProperties);
				
			}
		});
		
		colorPickingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				color = JColorChooser.showDialog(null, "Pasirinkite spalvą", Color.black);
				
				String rgb = Integer.toHexString(color.getRGB());
				encodedColorForBackground = rgb;			
			}
		});
		
		buttonColorPickingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(null, "Pasirinkite spalvą", Color.black);
				
				String rgb = Integer.toHexString(color.getRGB());
				encodedColorForButtons = rgb;			
			}
		});
			
	}

	public SettingsDialog(Window owner) {
		super(owner);
		
	}
	
}
