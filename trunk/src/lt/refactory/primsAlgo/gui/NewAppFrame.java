/*
 * DesktopApplication5View.java
 */

package lt.refactory.primsAlgo.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.gui.customComponents.ImageButton;
import lt.refactory.primsAlgo.gui.customListeners.GraphMouseListener;
import lt.refactory.primsAlgo.service.FileController;
import lt.refactory.primsAlgo.service.PrimsController;

/**
 * The application's main frame.
 */
public class NewAppFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3828293560112255576L;
	private Graph<WeightedEdge> graph;
	private PrimsController controller;
	private FileController fileController;
    private GraphMouseListener graphMouseListener;
    private Properties primsProperties;


    public NewAppFrame() {
        super();
        
        try {
			loadPropertiesFromXml();
		} catch (FileNotFoundException | URISyntaxException e) {
			e.printStackTrace();
		}
        
		graph = new Graph<WeightedEdge>();
		controller = new PrimsController(graph);
		fileController = new FileController(controller);
		
		
		
        initComponents();
        progressBar.setVisible(false);
        if (primsProperties.containsKey("DimensionX") && primsProperties.containsKey("DimensionY")){
        setSize(new Dimension(Integer.parseInt(primsProperties.getProperty("DimensionX")),
        				      Integer.parseInt(primsProperties.getProperty("DimensionY"))));
        }else{
        	setSize(new Dimension(700, 500));
        }   
        setTitle(primsProperties.getProperty("title"));
        setBackground(Color.getColor("blue"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadPropertiesFromXml() throws FileNotFoundException, URISyntaxException {	    	
    	primsProperties = new Properties();
	
		InputStream stream = this.getClass().getResourceAsStream("PrimsProperties.xml");	
		try {
			primsProperties.loadFromXML(stream);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	private void initComponents() {
    	String imgFolder = "/img/";
    	
        mainPanel = new JPanel();
        controlPanel = new JPanel();
        solveLabel = new ImageButton(primsProperties.getProperty("Solve Button"),imgFolder + primsProperties.getProperty("Solve Button image")
        									, imgFolder + primsProperties.getProperty("Solve Button image hover"));
        importLabel = new ImageButton(primsProperties.getProperty("Import Button"), imgFolder + primsProperties.getProperty("Import Button image")
        									, imgFolder + primsProperties.getProperty("Import Button image hover"));
        settingsLabel = new ImageButton(primsProperties.getProperty("Settings Button"), imgFolder + primsProperties.getProperty("Settings Button image")
											, imgFolder + primsProperties.getProperty("Settings Button image hover"));
        exportLabel = new ImageButton(primsProperties.getProperty("Export Button"), imgFolder + primsProperties.getProperty("Export Button image")
											, imgFolder + primsProperties.getProperty("Export Button image hover"));
        clearLabel = new ImageButton(primsProperties.getProperty("Clean Button"), imgFolder + primsProperties.getProperty("Clean Button image")
				, imgFolder + primsProperties.getProperty("Clean Button image hover"));
        graphBackgroundPanel = new JPanel();
        graphDrawPanel = new DrawingPanel(controller,primsProperties);
        statusPanel = new JPanel();
        progressBar = new JProgressBar();

        

        controlPanel.setBorder(BorderFactory.createTitledBorder(""));

        solveLabel.setText("Spresti"); 

        solveLabel.addMouseListener(onSolveLabelClick);        
        clearLabel.addMouseListener(onClearLabelClick);
        importLabel.addMouseListener(onImportLabelClick);
        exportLabel.addMouseListener(onExportLabelClick);
        settingsLabel.addMouseListener(onSettingsLabelClick);

        GroupLayout controlPanelLayout = new GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGroup(controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(importLabel)
                            .addComponent(solveLabel)
                            .addComponent(exportLabel)))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(clearLabel))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(settingsLabel)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(solveLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(importLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingsLabel)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        graphBackgroundPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE)); 
        //graphBackgroundPanel.setName("graphBackgroundPanel"); 

        graphDrawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        //graphDrawPanel.setName("graphDrawPanel"); 
        graphDrawPanel.setBackground(Color.WHITE);

		graphMouseListener = new GraphMouseListener(graphDrawPanel, controller, null);
        graphDrawPanel.addMouseListener(graphMouseListener);
        graphDrawPanel.addMouseMotionListener(mouseMotionListener);
        
        GroupLayout graphBackgroundPanelLayout = new GroupLayout(graphBackgroundPanel);
        graphBackgroundPanel.setLayout(graphBackgroundPanelLayout);
        graphBackgroundPanelLayout.setHorizontalGroup(
            graphBackgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, graphBackgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(graphDrawPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        graphBackgroundPanelLayout.setVerticalGroup(
            graphBackgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(graphBackgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(graphDrawPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphBackgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(graphBackgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

 
    
     String colorString = primsProperties.getProperty("BackgroundColor");
     colorString = colorString.substring(2, colorString.length());
     Color colorForBackgrounds = Color.decode("0x"+colorString);
     
     String buttonColorString = primsProperties.getProperty("ButtonColor");
     System.out.println(buttonColorString);
     buttonColorString = buttonColorString.substring(2, buttonColorString.length());
     Color colorForButtons = Color.decode("0x"+buttonColorString);
      
     
     importLabel.setForeground(colorForButtons);
     clearLabel.setForeground(colorForButtons);
     exportLabel.setForeground(colorForButtons);
     solveLabel.setForeground(colorForButtons);
     settingsLabel.setForeground(colorForButtons);
     
  
      setBackground(colorForBackgrounds);
      controlPanel.setBackground(colorForBackgrounds);
      graphBackgroundPanel.setBackground(colorForBackgrounds);
      mainPanel.setBackground(colorForBackgrounds);
      
        mainPanel.add(statusPanel);
        setContentPane(mainPanel);
        //setComponent(mainPanel);
        //setStatusBar(statusPanel);
    }

    private JLabel clearLabel;
    private JPanel controlPanel;
    private JLabel exportLabel;
    private JPanel graphBackgroundPanel;
    private DrawingPanel graphDrawPanel;
    private JLabel importLabel;
    private JPanel mainPanel;
    private JProgressBar progressBar;
    private JLabel settingsLabel;
    private JLabel solveLabel;
    private JPanel statusPanel;


    
    private MouseListener onSolveLabelClick = new MouseAdapter() {
    	public void mouseClicked(java.awt.event.MouseEvent e) {
    		graphDrawPanel.setShowLoadingScreen(false);
    		graphDrawPanel.repaint();
    		controller.solvePrimsAlgorithm(true);
    		
			graphDrawPanel.repaint();
    	};
    	
	};
	
	
    private MouseListener onClearLabelClick = new MouseAdapter() {
    	public void mouseClicked(java.awt.event.MouseEvent e) {
    		controller.clear();
			graphDrawPanel.repaint();
    	};
    	
	};
	
	private MouseListener onImportLabelClick = new MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			JFileChooser fileChooser = new JFileChooser(
					System.getProperty("user.dir"));
			fileChooser.setMultiSelectionEnabled(true);
			int returnVal = fileChooser.showOpenDialog(NewAppFrame.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File[] files = fileChooser.getSelectedFiles();
				try {
					fileController.readFromFile(files);
				} catch (IOException | NumberFormatException | AddNodeException
						| AddEdgeException e1) {
					e1.printStackTrace();
				}
				repaint();
			}
		};
	};
	
	
	MouseListener onExportLabelClick = new MouseAdapter(){
		public void mouseClicked(java.awt.event.MouseEvent e) {
			JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
			
			int returnVal = fileChooser.showDialog(NewAppFrame.this, "Save");
			
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				try {
					fileController.exportToFile(file);
							
				} catch (IOException e1) {
					e1.printStackTrace();
					
				}
			}
		};
	};
	
	MouseListener onSettingsLabelClick = new MouseAdapter(){
		public void mouseClicked(java.awt.event.MouseEvent e) {			
			showSettingsDialog();
		};
	};
	
	MouseMotionListener mouseMotionListener = new MouseAdapter() {
		public final static int GAP = 15;

		private boolean isNear(Node node, Node newNode) {
			if (Math.abs(node.getPointX().intValue() - newNode.getPointX().intValue()) <= GAP 
				&& Math.abs(node.getPointY().intValue()-newNode.getPointY().intValue()) <= GAP ){
				return true;
			}
			return false;
		}
		
		public void mouseMoved(java.awt.event.MouseEvent e) {
			BigDecimal x = BigDecimal.valueOf(e.getX());
			BigDecimal y = BigDecimal.valueOf(e.getY());
			
			Node newNode = new Node(x, y);
			Node nearNode = null;
			
			TooltipDisplayInfo tooltipDisplayInfo = null;

			for (Node node : controller.getGraph().getNodeList()) {
				if (isNear(node, newNode)){
					nearNode = node;
				}
			}
			if (nearNode != null){
				tooltipDisplayInfo = new TooltipDisplayInfo(nearNode, e.getX(), e.getY());
				graphDrawPanel.setTooltipDisplayInfo(tooltipDisplayInfo);
			} else {
				graphDrawPanel.setTooltipDisplayInfo(null);
			}
			graphDrawPanel.repaint();

		};
	};
	
	private JDialog settingsDialog;
	
    public void showSettingsDialog() {
    
        if (settingsDialog == null) {

            settingsDialog = new SettingsDialog(this,primsProperties);
            settingsDialog.setLocationRelativeTo(this);
        }else{
        	settingsDialog.setVisible(true);
        }
        //this.showSettingsDialog();
      
    }

	
	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NewAppFrame().setVisible(true);
			}
		});
	}
}

/*
 statusPanel.setName("statusPanel"); 

        statusPanelSeparator.setName("statusPanelSeparator"); 

        statusMessageLabel.setName("statusMessageLabel"); 

        statusAnimationLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); 


        GroupLayout statusPanelLayout = new GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );
 

 */
