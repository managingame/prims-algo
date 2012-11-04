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
import java.awt.Graphics;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;

import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Graph;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;



/**
 *
 * @author Egidijus
 */
public class GraphPanel extends javax.swing.JFrame   {

	private Graph<Edge> graph ;
	
	int x , y = -1;
	
	private static final long serialVersionUID = 1L;
	
	
	
	// GUI Variables declaration - do not modify
    private javax.swing.JButton emptyButton;
    private javax.swing.JButton steinerButton;
    private JButton graphContructionButton;
    
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private GraphMouseListener graphMouseListener;
    private GraphMouseMotionListener graphMouseMotionListener;
    private buttonController buttonsController;
    // End of variables declaration
	
	/** Creates new form GraphPanel 
	 * @throws AddNodeException */
    public GraphPanel() throws AddNodeException {
  	
    	graph = new Graph<Edge>();
    	
        initComponents();
        
    }
    
    // Creates all Buttons and other gui components .also add listeners to them .
    private void initComponents() {
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new JPanel();
        emptyButton = new javax.swing.JButton();
        steinerButton = new javax.swing.JButton();
        graphContructionButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        graphMouseListener = new GraphMouseListener(this);
        graphMouseMotionListener = new GraphMouseMotionListener(this);
        buttonsController = new buttonController(this);
        
        jPanel1.setBackground(Color.WHITE);
        jPanel1.addMouseListener(graphMouseListener);
        jPanel1.addMouseMotionListener(graphMouseMotionListener);
        
			
			
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jProgressBar1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        emptyButton.setText("Dar nenuspresta");
        emptyButton.addActionListener(buttonsController);

        steinerButton.setText("Rasti Šteinerio tašką");
        steinerButton.addActionListener(buttonsController);
      
        
        graphContructionButton.setText("Konstruoti pilnaji grafa");
        graphContructionButton.addActionListener(buttonsController);

        
        jMenu1.setText("File");
        jMenu1.add(new JMenuItem("Skaityti iš failo"));
        jMenu1.add(new JMenuItem("Eksportuoti į failą"));
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Tools");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(steinerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emptyButton, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(graphContructionButton, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
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
                        .addComponent(emptyButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(steinerButton)
                        .addComponent(graphContructionButton))
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }
    
    
	protected void ActivateSteinerMethod() {
		// TODO Implement Method for activating Steiner algorithm
			
	}
	
	
	protected void BuildFullGraph()
	{
		List<Node> nodeList = graph.getNodeList();		
		graph =  Graph.fullGraphFactory(nodeList);
		
		repaint();
	}
	protected void Additional()
	{
		repaint();
        jProgressBar1.setBackground(Color.BLUE);
        jProgressBar1.setForeground(Color.BLACK);
        jProgressBar1.setStringPainted(true);
        jProgressBar1.setToolTipText("Mighty and powerfull status bar");
        jProgressBar1.setMinimum(0); 
        jProgressBar1.setMaximum(10); 
        jProgressBar1.setString("Finished");
        paintComponents(getGraphics());
	}
	
	@Override
    public void paint(Graphics g) {
    	super.paint(g);
    	jPanel1.setOpaque(true);
    	g.setColor(Color.BLACK);
    	
    	
	    	for (Node point : graph.getNodeList())
	    	{
	    		
	    		g.drawString(point.getPointX()+":"+point.getPointY(),point.getPointX().intValue()-4, point.getPointY().intValue()-6);
	    		g.fillOval(point.getPointX().intValue(), point.getPointY().intValue(), 10, 10);
	    		g.setColor(Color.RED);
	    		g.fillOval(point.getPointX().intValue()+3, point.getPointY().intValue()+3, 4, 4);
	    		g.setColor(Color.BLACK);
	    		
	    	}
	    	
	    	for (Edge edge : graph.getEdgeList())
	    	{
	    		g.drawLine(edge.getStart().getPointX().intValue(), edge.getStart().getPointY().intValue()+5, edge.getEnd().getPointX().intValue()+5, edge.getEnd().getPointY().intValue()+5);
	    		
	    	}
	    	
    }

	
  
    
    
	
	// Adding point to graph
    public void AddPoint(BigDecimal x,BigDecimal y) throws AddNodeException
    {   	
    	Node node = new Node(x, y);
		graph.addNode(node);
		repaint();
    }
    
  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception exception){
			exception.printStackTrace();
		}

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					new GraphPanel().setVisible(true);
				} catch (AddNodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
    
    
	
	
	
}
