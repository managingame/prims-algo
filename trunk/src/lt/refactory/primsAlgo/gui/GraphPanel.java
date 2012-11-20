/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GraphPanel.java
 *
 * Created on Oct 11, 2012, 4:59:12 PM
 */
package lt.refactory.primsAlgo.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;

import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.WeightedEdge;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;
import lt.refactory.primsAlgo.gui.customListeners.GraphMouseListener;
import lt.refactory.primsAlgo.service.FileController;
import lt.refactory.primsAlgo.service.PrimsController;



/**
 *
 * @author Egidijus
 */
public class GraphPanel extends javax.swing.JFrame   {
	private static final long serialVersionUID = 1L;



	protected static final String SPECIALSEPARATOR = ".";



	private Graph<WeightedEdge> graph;
	private PrimsController controller;
	private FileController fileController;
	int x , y = -1;
	
	// GUI Variables declaration - do not modify
    private javax.swing.JButton cleanButton;
    private javax.swing.JButton steinerButton;
    private JButton graphContructionButton;
    
    private JCheckBox solveOnClickCheckbox;
    
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    
    private javax.swing.JMenuItem openFromFile;
    private javax.swing.JMenuItem exportToFile;
    
    
    private javax.swing.JMenuBar jMenuBar1;
    private DrawingPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private GraphMouseListener graphMouseListener;
    
    private Properties properties = new Properties();
    // End of variables declaration
	
	/** Creates new form GraphPanel 
	  */
	public GraphPanel() {
		graph = new Graph<WeightedEdge>();
		controller = new PrimsController(graph);
		fileController = new FileController(controller);
		initComponents();
	}

    private void initComponents() {
    	try {     		 
    		InputStream propertiesFile = this.getClass().getResourceAsStream("GraphPanel.properties");
			properties.load(propertiesFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new DrawingPanel(controller);
        cleanButton = new javax.swing.JButton();
        steinerButton = new javax.swing.JButton();
        graphContructionButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        
        openFromFile = new JMenuItem("Skaityti iš failo");
        
        exportToFile = new JMenuItem("Išsaugoti faile");
        
        
        solveOnClickCheckbox = new JCheckBox();
        solveOnClickCheckbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (solveOnClickCheckbox.isSelected()){
					JOptionPane.showMessageDialog(null, "Bus blogai kai daug duomenu",properties.getProperty("title"),
							  JOptionPane.WARNING_MESSAGE);
				}
			}
		});

        graphMouseListener = new GraphMouseListener(this, controller, solveOnClickCheckbox);
        
        jPanel1.setBackground(Color.WHITE);
        jPanel1.addMouseListener(graphMouseListener);
        
			
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jProgressBar1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        cleanButton.setText("Valyti");
        cleanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.clear();
				jPanel1.repaint();
			}
		});

        steinerButton.setText("Trumpiausias grafas be tarpinių tašku");
        
        steinerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.solvePrimsAlgorithm(false);
				jPanel1.repaint();
			}
		});
        

        
        openFromFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
				fileChooser.setMultiSelectionEnabled(true);
				int returnVal = fileChooser.showOpenDialog(GraphPanel.this);
				
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File []files = fileChooser.getSelectedFiles();				
					try {
						fileController.readFromFile(files);
					} catch (IOException | NumberFormatException | AddNodeException | AddEdgeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();
				}
			
				
				
			}
		});
        
        exportToFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
				
				int returnVal = fileChooser.showDialog(GraphPanel.this, "Save");
				
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fileChooser.getSelectedFile();
					try {
						fileController.exportToFile(file);
						jProgressBar1.setBackground(Color.RED);
						jProgressBar1.setStringPainted(true);
						jProgressBar1.setValue(100);
								
					} catch (IOException e) {
						e.printStackTrace();
						
					}
				}
				
			}

			
		});
        
        
        
        
        
        graphContructionButton.setText("Kazkas");


        graphContructionButton.setText("Trumpiausias grafas su vienu tašku");

        
        graphContructionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.solvePrimsAlgorithm(true);
				jPanel1.repaint();
			}
		});
        
        jMenu1.setText("File");
        jMenu1.add(openFromFile);
        jMenu1.add(exportToFile);
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Tools");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);
        JLabel label = new JLabel();
        label.setText("Perskaiciuoti pridejus taska");

        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(steinerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cleanButton, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(graphContructionButton, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()

                    .addComponent(solveOnClickCheckbox, GroupLayout.DEFAULT_SIZE,0,Short.MAX_VALUE)
                    .addComponent(label, GroupLayout.DEFAULT_SIZE,0,Short.MAX_VALUE)
                    )
                    )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cleanButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(steinerButton)
                        .addComponent(graphContructionButton)
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        		.addComponent(solveOnClickCheckbox)
                                .addComponent(label)
                        )
                        )
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

	

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GraphPanel().setVisible(true);
			}
		});
	}

}
