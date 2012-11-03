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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
    /**
	 * 
	 */
	int x , y = -1;

	private JButton jButton3;
	private static final long serialVersionUID = 1L;
	/** Creates new form GraphPanel 
	 * @throws AddNodeException */
    public GraphPanel() throws AddNodeException {
    	
    	graph = new Graph<Edge>();
    	
    	
        initComponents();
        jProgressBar1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }
    

    private void initComponents() {
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        graphMouseListener = new GraphMouseListener(this);
        graphMouseMotionListener = new GraphMouseMotionListener(this);
        jPanel1.setBackground(Color.WHITE);
        jPanel1.addMouseListener(graphMouseListener);
        jPanel1.addMouseMotionListener(graphMouseMotionListener);
			
			
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton1ActionPerformed(evt);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        });

        jButton2.setText("Rasti Šteinerio tašką ");
        jButton3.setText("Konstruoti pilnaji grafa");
        jButton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Node> nodeList = graph.getNodeList();
				graph = Graph.fullGraphFactory(nodeList);		
				repaint();
					
			}
		});

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
                    .addComponent(jButton2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(jButton3, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
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
                        .addComponent(jButton1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }
    
    @SuppressWarnings("unused")
	@Override
    public void paint(Graphics g) {
    	super.paint(g);
    	jPanel1.setOpaque(true);
    	
    	
    	g.setColor(Color.BLACK);
    	int i=0;
    	
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws InterruptedException {
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
    public void AddPoint(BigDecimal x,BigDecimal y) throws AddNodeException
    {
    	
    	Node node = new Node(x, y);
		graph.addNode(node);
		
		
		repaint();
    }
    
   /* public void AddPoint(List<Integer> point) {
		
		pointList.add((ArrayList<Integer>) point);
	}*/
    
   /* public void AddEdge(int x1 , int y1 , int x2 ,int y2){
    	List<Integer> edge = new ArrayList<>();
    	edge.add(x1);
    	edge.add(y1);
    	edge.add(x2);
    	edge.add(y2);
    	edgeList.add((ArrayList<Integer>) edge);
    	repaint();
    }*/
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
    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private GraphMouseListener graphMouseListener;
    private GraphMouseMotionListener graphMouseMotionListener;
    // End of variables declaration
    
	
	
	
}
