package lt.refactory.primsAlgo.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.JOptionPane;


import lt.refactory.primsAlgo.graph.Edge;
import lt.refactory.primsAlgo.graph.Node;
import lt.refactory.primsAlgo.graph.enums.NodeType;
import lt.refactory.primsAlgo.graph.exception.AddEdgeException;
import lt.refactory.primsAlgo.graph.exception.AddNodeException;

public class FileController {

	
	
	private static final String SPECIALSEPARATOR = ".";
	private static final String DELIMITER = ";";
	private PrimsController controller;
	
	public FileController(PrimsController controller){
		this.controller = controller;
	}
	
	

	
	public void exportToFile(File file) throws IOException {
		
		File fileForNodes = new File(file.toString()+SPECIALSEPARATOR+"Nodes"+".csv");
		File fileForEdges = new File(file.toString()+SPECIALSEPARATOR+"Edges"+".csv");
		
		
		// Writing nodes to FileName.nodes.csv
		if (!fileForNodes.exists()){
			fileForNodes.createNewFile();
			
			FileOutputStream fileOutStream = new FileOutputStream(fileForNodes);
			DataOutputStream dataStream = new DataOutputStream(fileOutStream);
							
			for (Node node : controller.getGraph().getNodeList()) {
				if (node.getNodeType() == NodeType.STEINER)
					dataStream.writeBytes(node.getPointX()+DELIMITER+node.getPointY()+DELIMITER+"STEINER"
										 +System.getProperty("line.separator"));
				else
					dataStream.writeBytes(node.getPointX()+DELIMITER+node.getPointY()+DELIMITER+"NORMAL"
										 +System.getProperty("line.separator"));
				
			}
			
			dataStream.close();
		}else{
			JOptionPane.showMessageDialog(null, "Failas nurodytu vardu :"+file.getName()+" jau yra",
						"Klaida",
					  JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// Writing edges to file FileName.edges.csv
		if (!fileForEdges.exists()){
			fileForEdges.createNewFile();
			
			FileOutputStream fileOutStream = new FileOutputStream(fileForEdges);
			DataOutputStream dataStream = new DataOutputStream(fileOutStream);
			
					
			for (Edge edge : controller.getGraph().getEdgeList()) {
				dataStream.writeBytes(edge.getStart().getPointX()+ DELIMITER
									 +edge.getStart().getPointY()+ DELIMITER
									 +edge.getEnd().getPointX()+ DELIMITER
									 +edge.getEnd().getPointY()+DELIMITER
								+System.getProperty("line.separator"));
			}	
			
			dataStream.close();
		}else{
			return ;
		}
	
	}




	public void readFromFile(File[] files) throws IOException, NumberFormatException, AddNodeException, AddEdgeException {
		int firstFileFirstDotPos;
		
		int secondFileFirstDotPos;
			
		String dataLine;
		
		
		if (files.length > 2 || files.length == 0){
			
			JOptionPane.showMessageDialog(null, "Pasirinktas netinkamas kiekis failų \n Reikėtų pasirinkti 2 failus"+
			"\n Jūs pasirinkote: "+files.length,"Klaida",
					  JOptionPane.WARNING_MESSAGE);
			return ;		
		}
		if (files.length == 2){
			firstFileFirstDotPos = files[0].getName().indexOf(SPECIALSEPARATOR);
			
			secondFileFirstDotPos = files[1].getName().indexOf(SPECIALSEPARATOR);
				
			String filePrefixFirst = files[0].getName().substring(0, firstFileFirstDotPos);
			String filePrefixSecond = files[1].getName().substring(0, secondFileFirstDotPos);

			String []wholeData;
			
			if (filePrefixFirst.equalsIgnoreCase(filePrefixSecond)){
				
				FileReader []fileReaders = {new FileReader(files[0]),new FileReader(files[1])};
				BufferedReader []bufferReaders = {new BufferedReader(fileReaders[0]),new BufferedReader(fileReaders[1])};
				
				for (int i = 0; i < bufferReaders.length; i++){
					
					while ((dataLine = bufferReaders[i].readLine()) != null){
						wholeData = dataLine.split(DELIMITER);
						
						// If dataLine consists of 3 members its node file else its edge file
						if (wholeData.length == 3){
							
						
							if (wholeData[2].equalsIgnoreCase("STEINER"))
								controller.addNode(new Node(BigDecimal.valueOf(Double.parseDouble(wholeData[0])),
										   BigDecimal.valueOf(Double.parseDouble(wholeData[1])),
										   "STEINER"));
							else
								controller.addNode(new Node(BigDecimal.valueOf(Double.parseDouble(wholeData[0])),
										   BigDecimal.valueOf(Double.parseDouble(wholeData[1])),
										   "NORMAL"));
									
						}else{
							
							// - TODO Adding edges to graph (cant be done due fact controller
							// doesn't have method addEdge(Edge)
						}
						
					}
					
					bufferReaders[i].close();
					fileReaders[i].close();
				}
				
				
			}else{
				
				JOptionPane.showMessageDialog(null, "Nesuderinti failų pavadinimai "+
						"\n Jūs pasirinkote: "+files[0].getName()+" ir "+ files[1].getName(),"Klaida",
								  JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
		}
		
	
		
		
		
		
		
	}
	
}
