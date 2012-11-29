/*
 * DesktopApplication5View.java
 */

package lt.refactory.primsAlgo.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;

import lt.refactory.primsAlgo.graph.Graph;
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


    public NewAppFrame() {
        super();
		graph = new Graph<WeightedEdge>();
		controller = new PrimsController(graph);
		fileController = new FileController(controller);
        initComponents();
        progressBar.setVisible(false);

        setSize(new Dimension(700, 500));
        setTitle("Primo algoritmas su Ðteinerio taðku");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
    	String imgFolder = System.getProperty("user.dir");
    	imgFolder += File.separator + "bin" + File.separator + "img" + File.separator;
    	
        mainPanel = new JPanel();
        controlPanel = new JPanel();
        solveLabel = new ImageButton("Spresti",imgFolder +"exchange.png",imgFolder + "exchange_hover.png");
        importLabel = new ImageButton("Importuoti", imgFolder +"above.png", imgFolder +"above_hover.png");
        settingsLabel = new ImageButton("Nustatymai", imgFolder +"pen.png", imgFolder +"pen_hover.png");
        exportLabel = new ImageButton("Eksportuoti", imgFolder +"below.png", imgFolder +"below_hover.png");
        clearLabel = new ImageButton("Valyti", imgFolder +"close.png", imgFolder +"close_hover.png");
        graphBackgroundPanel = new JPanel();
        graphDrawPanel = new DrawingPanel(controller);
        statusPanel = new JPanel();
        progressBar = new JProgressBar();

        

        controlPanel.setBorder(BorderFactory.createTitledBorder(""));

        solveLabel.setText("Spresti"); 

        solveLabel.addMouseListener(onSolveLabelClick);        
        clearLabel.addMouseListener(onClearLabelClick);
        importLabel.addMouseListener(onImportLabelClick);
        exportLabel.addMouseListener(onExportLabelClick);

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
        graphDrawPanel.addMouseMotionListener(mouseMotion);
        
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
/*					jProgressBar1.setBackground(Color.RED);
					jProgressBar1.setStringPainted(true);
					jProgressBar1.setValue(100);*/
							
				} catch (IOException e1) {
					e1.printStackTrace();
					
				}
			}
		};
	};
	
	
	private MouseMotionListener mouseMotion = new MouseMotionAdapter() {
		public void mouseMoved(java.awt.event.MouseEvent e) {
			//System.out.println("Moved:" + e.getPoint().x + ":" + e.getPoint().y) ;
		};
	};
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
