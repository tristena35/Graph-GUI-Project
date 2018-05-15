import java.awt.Color; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

import javax.swing.JFrame;

public class RBListener implements ActionListener{

	//GraphGui Object
	private GraphGUI graphGui;
	
	
	public RBListener(GraphGUI graphGui) {
		
		this.graphGui = graphGui;
	
	}//Constructor

	
	@Override
	public void actionPerformed(ActionEvent e) {
				
		/*
		 * Check for which RadioButton was selected
		 */
		
		if(graphGui.addVertex.isSelected()) {
			
			graphGui.RightSideCanvas.setRadioButtonClicked("Add Vertex");
			graphGui.RightSideCanvas.setEnabledButton(true);
			
			/*
			 * Set every other Radio button enable to false
			 */
			graphGui.addEdge.setEnabled(false);
			graphGui.moveVertex.setEnabled(false);
			graphGui.shortestPath.setEnabled(false);
			graphGui.changeWeight.setEnabled(false);
	
		}else if(graphGui.addEdge.isSelected()) {
		
			graphGui.RightSideCanvas.setRadioButtonClicked("Add Edge");
			graphGui.RightSideCanvas.setEnabledButton(true);
			
			/*
			 * Set every other Radio button enable to false
			 */
			graphGui.addVertex.setEnabled(false);
			graphGui.moveVertex.setEnabled(false);
			graphGui.shortestPath.setEnabled(false);
			graphGui.changeWeight.setEnabled(false);
			
		}else if(graphGui.moveVertex.isSelected()) {
			
			graphGui.RightSideCanvas.setRadioButtonClicked("Move Vertex");
			graphGui.RightSideCanvas.setEnabledButton(true);
			
			/*
			 * Set every other Radio button enable to false
			 */
			graphGui.addEdge.setEnabled(false);
			graphGui.addVertex.setEnabled(false);
			graphGui.shortestPath.setEnabled(false);
			graphGui.changeWeight.setEnabled(false);
	
		}else if(graphGui.shortestPath.isSelected()) {
			
			graphGui.RightSideCanvas.setRadioButtonClicked("Shortest Path");
			graphGui.RightSideCanvas.setEnabledButton(true);
			
			/*
			 * Set every other Radio button enable to false
			 */
			graphGui.addEdge.setEnabled(false);
			graphGui.moveVertex.setEnabled(false);
			graphGui.addVertex.setEnabled(false);
			graphGui.changeWeight.setEnabled(false);
	
		}else if(graphGui.changeWeight.isSelected()) {
			
			graphGui.RightSideCanvas.setRadioButtonClicked("Change Weight");
			graphGui.RightSideCanvas.setEnabledButton(true);
			
			/*
			 * Set every other Radio button enable to false
			 */
			graphGui.addEdge.setEnabled(false);
			graphGui.moveVertex.setEnabled(false);
			graphGui.shortestPath.setEnabled(false);
			graphGui.addVertex.setEnabled(false);
			
		/*
		* If none of the above if statements are met, the else
		* resets the state of all the above RadioButtons, and 
		* repaints the canvas if necessary
		*/
		}else {
			
			//Disable right side of the GUI (Canvas)
			graphGui.RightSideCanvas.setEnabledButton(false);
			graphGui.RightSideCanvas.setRadioButtonClicked("");
			
			//Reset all JRadioButtons to enabled
			graphGui.addVertex.setEnabled(true);
			graphGui.addEdge.setEnabled(true);
			graphGui.moveVertex.setEnabled(true);
			graphGui.shortestPath.setEnabled(true);
			graphGui.changeWeight.setEnabled(true);
			
			/*
			 * Here, you go through all vertices, and check to see if the color is equal to green, and if so, paints it red
			 * to indicate it was selected
			 */
			for(Vertex vertex : MainCanvas.RightSideGraph.getAllVertices()) {
				
				if(vertex.getVertexColor().equals(Color.GREEN)) {
					
					vertex.setVertexColor(Color.BLACK);
				
				}
				
				//This set retrieves all edges connected to that vertex
				HashSet<Edges> allEdges = MainCanvas.RightSideGraph.getVertexEdges(vertex);
				
				/*
				 * This inner for loop goes through all the edges, and where it is equal to green, sets it equal to red
				 */
				for(Edges edges: allEdges) {
				
					if(edges.getEdgeColor().equals(Color.GREEN)) {
					
						edges.setEdgeColor(Color.RED);
					
					}
				
				}
			}
			
			//Repaint is called to keep track of Graph
			graphGui.RightSideCanvas.repaint();
			
		}
	}


}