import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JOptionPane;

public class BListener implements ActionListener{

	//Object for GraphGUI
	private GraphGUI graphGui;
	
	
	public BListener(GraphGUI graphGui) {
		
		this.graphGui = graphGui;
	
	} //Constructor
	
	
	@Override
	public void actionPerformed(ActionEvent buttonClicked) {
		// TODO Auto-generated method stub
		
		//Gets the button clicked on
		String nameOfClickedButton = buttonClicked.getActionCommand();
		
		
		/*
		 * 'Add All Edges': This button creates a complete graph creating edges for each vertex
		 */
		if(nameOfClickedButton.equals("Add All Edges")) {
			
			//This HashSet retrieves all vertices on the graph
			HashSet<Vertex> allVerticesOnGraph = MainCanvas.RightSideGraph.getAllVertices();
			
			/*
			 * This nested for loop goes through all vertices, and checks each one against each other
			 */
			for(Vertex vertice: allVerticesOnGraph) {
				
				for(Vertex vertex: allVerticesOnGraph) {
					
					//If they equal, then ignore it because we don't want to connect a vertex to itself
					if(vertice.equals(vertex)) {
						
						continue;
					
					}
					//If they don't equal, then draw an edge
					else {
					
						MainCanvas.RightSideGraph.addEdge(vertice,vertex);
						
					}
					
				}

			}
			
			//Afterwards, repaint the canvas
			graphGui.RightSideCanvas.repaint();
		
		}
	
		/*
		 * 'Random Weights': This button assigns each edge a random weight
		 */
		if(nameOfClickedButton.equals("Random Weights")) {
			
			/*
			 * This nested for loop goes through each of the edges and vertices
			 */
			for(Vertex vertex: MainCanvas.RightSideGraph.getAllVertices()) {
				
				for(Edges edges: MainCanvas.RightSideGraph.getVertexEdges(vertex)) {
					
					//This variable computes a random value between 1 and 10
					Random rand = new Random();
					int newRandomWeight = rand.nextInt(10 - 1 + 1) + 1;
					
					//Here we set the specific edge's weight to random value computed
					edges.setEdgeWeight(newRandomWeight);
					
					try {
						
						//Here we continue to set the random weight to edges
						Edges tempEdge = MainCanvas.RightSideGraph.getEdge(edges.getSecondPoint(), vertex);	
						tempEdge.setEdgeWeight(newRandomWeight);
						
					}catch(NullPointerException e) {
						
						System.out.println("Error Occured setting weight of edge: " + e);
						
					}
					
				}
			}
			
			//Canvas is repainted
			graphGui.RightSideCanvas.repaint();
			
		}
		
		/*
		 * 'Minimal Tree Spanning': This button computes and displays the minimal spanning tree
		 */
		if(nameOfClickedButton.equals("Minimal Tree Spanning")) {
			
			//Stores the Minimal Spanning Tree
			HashMap <Vertex, HashSet<Edges>> minimalSpanningTree = new HashMap<Vertex, HashSet<Edges>>();
			
			//A vertex to start at
			Vertex startingVertex = MainCanvas.RightSideGraph.getVertex(1);
			
			//Here we use the startingVertex as the key
			minimalSpanningTree.put(startingVertex, new HashSet<Edges>());
			
			/*
			 * This loop is responsible for adding to the minimal spanning tree, until all 
			 * vertices have been added
			 */
			while(!MainCanvas.RightSideGraph.getAllVertices().equals(minimalSpanningTree.keySet())) {
				
				//This vertex will be the one connected to the Edge below
				Vertex connectedVertex = new Vertex();
				
				//This Edge is to track the smallest weighted Edge
				Edges lowestEdge = new Edges();
				
				/*
				 * This nested for loop is responsible for going through all the vertices in the graph
				 * that are connected to all edges in the graph
				 */
				for(Vertex vertex : minimalSpanningTree.keySet()) {
					
					for(Edges edge: MainCanvas.RightSideGraph.getVertexEdges(vertex)) {
						
						try {
							/*
							 * Skips the edge if the adjacent edge is in the tree
							 * or if the vertices have been added to the tree already
							 */
							if(minimalSpanningTree.containsKey(vertex)) {
								
								/*
								 * If the spanning tree already contains the vertex or adjacent edge
								 * it will skip to the next.
								 */
								if(minimalSpanningTree.get(vertex).contains(edge))
									continue;
								
								//Creates temporary edge to test for the tree
								Edges adjacentEdge = new Edges(edge.getSecondPoint(), edge.getFirstPoint());
								
								if(minimalSpanningTree.get(vertex).contains(adjacentEdge))
									continue;
								
								//Creates two temporary vertices
								Vertex firstVertex = edge.getFirstPoint();
								Vertex secondVertex = edge.getSecondPoint();
								
								//If the Vertices are already connected inside the minimal spanning tree
								if(MainCanvas.RightSideGraph.getVertex(firstVertex.getID()).getVertexColor().equals(Color.GREEN) 
										&& MainCanvas.RightSideGraph.getVertex(secondVertex.getID()).getVertexColor().equals(Color.GREEN)) 
									continue;
								
							}

						}catch(NullPointerException ex) {
							
							/*
							 * This message will be called if the user did not add weights to the graph, therefore calculating
							 * the minimal spanning tree is impossible
							 */
							System.out.println("All edges must contain a weight");
							
							continue;
							
						}
						
						//Here, we check if the current edge has a lower weight than the stored one.
						if(edge.getEdgeWeight() < lowestEdge.getEdgeWeight()) {
							
							//If so, we change our temporary variables
							lowestEdge = edge;
							connectedVertex = vertex;

						}
					}
				}

				//Here we add the edge to a vertex that is connected, and has been added to the tree
				if(minimalSpanningTree.containsKey(connectedVertex)) {

					//Creates an Edge Object for temporary edge that is adjacent
					Edges closeEdge = new Edges(lowestEdge.getSecondPoint(),connectedVertex);
					
					minimalSpanningTree.get(connectedVertex).add(lowestEdge);
					
					//Here, we add the edge that has been is lowest to the minimal spanning tree
					if(!minimalSpanningTree.containsKey(lowestEdge.getSecondPoint())) {
					
						minimalSpanningTree.put(lowestEdge.getSecondPoint(), new HashSet<Edges>());
						minimalSpanningTree.get(lowestEdge.getSecondPoint()).add(closeEdge);
					
					}else {
						
						minimalSpanningTree.get(lowestEdge.getSecondPoint()).add(closeEdge);
					
					}
					
					/*
					 * Here we set the colors green of all the edges and vertices that are part of
					 * the minimal spanning tree
					 */
					MainCanvas.RightSideGraph.getEdge(connectedVertex, lowestEdge.getSecondPoint()).setEdgeColor(Color.GREEN);
					MainCanvas.RightSideGraph.getVertex(connectedVertex.getID()).setVertexColor(Color.GREEN);
					MainCanvas.RightSideGraph.getEdge(lowestEdge.getSecondPoint(),connectedVertex).setEdgeColor(Color.GREEN);
					MainCanvas.RightSideGraph.getVertex(lowestEdge.getSecondPoint().getID()).setVertexColor(Color.GREEN);
					
					/*
					 * After we are done, we reset the values of the lowest weighted edge, and the connected vertex back to null,
					 * so that it is ready to be computed again.
					 */
					
					lowestEdge = null;
					connectedVertex = null;
					
				}
				
				//If we have not yet added the vertex, we now add it to the minimal spanning tree
				else {
					
					//Here we add the vertice along with the edge
					minimalSpanningTree.put(connectedVertex, new HashSet<Edges>());
					
					//We create a temporary edge to keep track of connected edge to the vertex
					Edges tempEdge = new Edges(lowestEdge.getSecondPoint(),connectedVertex);
					
					//Here we add the lowest weighted edge to the minimal spanning tree
					minimalSpanningTree.get(connectedVertex).add(lowestEdge);
					
					if(!minimalSpanningTree.containsKey(lowestEdge.getSecondPoint())) {
						
						minimalSpanningTree.put(lowestEdge.getSecondPoint(), new HashSet<Edges>());
						minimalSpanningTree.get(lowestEdge.getSecondPoint()).add(tempEdge);
						
					}else {
						
						minimalSpanningTree.get(lowestEdge.getSecondPoint()).add(tempEdge);
					
					}
					
					/*
					 * Here, we get the edges that were added to the tree, and set the colors of them to be green, indicating
					 * that they are part of the tree
					 */
					MainCanvas.RightSideGraph.getEdge(connectedVertex, lowestEdge.getSecondPoint()).setEdgeColor(Color.GREEN);
					MainCanvas.RightSideGraph.getEdge(lowestEdge.getSecondPoint(), connectedVertex).setEdgeColor(Color.GREEN);
					
					/*
					 * Since we have finished, we set the values back equal to null,
					 * leaving it ready to start again
					 */
					
					connectedVertex = null;
					lowestEdge = null;
					
				}
			}
			
			//Canvas is repainted, displaying final minimal spanning tree
			graphGui.RightSideCanvas.repaint();
				
		}
		
		/*
		 * 'Help': This button shows a pop up to explain all features of this program
		 */
		if(nameOfClickedButton.equals("Help")) {
			
			new Help();
		
		}
		
	}

}