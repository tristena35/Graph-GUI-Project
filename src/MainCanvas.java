import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainCanvas extends JPanel implements MouseListener{

	//Instance Variables of a GraphCanvas
    private GraphGUI graphGui;
    
    //This Boolean Variable determines which buttons should be enabled and disabled
    private static boolean enableRadioButton; 
    
    //This variable keeps track of which Radio button was clicked On
    private static String currentRButtonClicked;
    
    //This MainGraph Object will allow us to access the right side of the GUI
    protected static MainGraph RightSideGraph;
    
    //Two Objects for Vertices
    private static Vertex firstVertex;
    private static Vertex secondVertex;

    
    public MainCanvas(GraphGUI graphGui){
    	
    	//Sets the Gui passed in to the class Gui object
        this.graphGui = graphGui;
       
        //Here we reset the RadioButtonClicked to be nothing
        currentRButtonClicked="";
        
        //Here, we set radioButton to false
        enableRadioButton = false;
        
        //Assign MainGraph class Object to a new MainGraph
        RightSideGraph = new MainGraph();
        
        //Here, we add the mouse listener to listen for mouse clicks
        this.addMouseListener(this);
        
    }//Constructor
    

    //Setter for enabling buttons
    public void setEnabledButton(boolean buttonEnabled) {
    	
    	enableRadioButton = buttonEnabled;
    
    }

    //Sets the Radio button that was clicked
    public void setRadioButtonClicked(String rButton) {
    	
    	currentRButtonClicked = rButton;
    
    }
    
    /*
     * (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     * This function is essential for when the mouse is clicked
     */
    @Override
    public void mouseClicked(MouseEvent coordinateClicked){
    	
    	//When the mouse is clicked, it will get the specific x and y on the canvas
        int x = coordinateClicked.getX();
        int y = coordinateClicked.getY();
        
    	//If the 'Add Vertex' button is clicked on
        if(currentRButtonClicked.equals("Add Vertex")) {
        	
        	//Creates a new vertex
        	Vertex newVertex = new Vertex(x, y);
        	
        	//Add the vertex to the Graph
        	RightSideGraph.addVertex(newVertex);
        	
        	//Here you paint onto the canvas
        	this.paintComponent(this.getGraphics());
        
        }
        
        //If 'Add Edge' button was clicked
        if(currentRButtonClicked.equals("Add Edge")) {
        	
        	//if the first vertex is empty
        	if(firstVertex == null) {
        	  
        		firstVertex = RightSideGraph.getVertexClicked(coordinateClicked.getPoint());
        	  
        		try{
        			
        			//Try and set color to green if it is not null
        			firstVertex.setVertexColor(Color.GREEN);
        		
        		//If vertex is null, catch it here
        		}catch (NullPointerException nullVertex) {
        		
        			//First you have to repaint the canvas
        			this.repaint();
        			
        			return;
        		
        		}
        	   
        		//If there is not catch, repaint canvas regardless
        		this.repaint();
           
        	}//If the vertex color of the first vertex is Green
        	else if(firstVertex.getVertexColor().equals(Color.GREEN)) {
        		
        		secondVertex = RightSideGraph.getVertexClicked(coordinateClicked.getPoint());
        		
        		try {
        		
        			// Create the edge        			
        			RightSideGraph.addEdge(firstVertex, secondVertex);
        		
        		}catch (NullPointerException nullVertex) {
        		
        			System.out.println("There was an error: " + nullVertex);
        			
        			//Reset the Vertex color to black
        			firstVertex.setVertexColor(Color.BLACK);
             	   
        			//Reset Vertices to empty, so new edge can be added
             	   	firstVertex = null;
             	   	secondVertex = null;
             	   	            	   	
             	   	//Repaint the canvas
             	   	this.repaint(); 
             	   	
             	   	return;
        		
        		}
        		
        		firstVertex.setVertexColor(Color.BLACK);
        		
        		//If no error was thrown, still repaint canvas
         	   	this.repaint();
         	   	
         	   	//Reset vertices to be null
         	   	firstVertex = null;
         	   	secondVertex = null;       	   	
         	   	
          	   
        	}
        }
        
        //If the 'Move Vertex' button is clicked on
        if(currentRButtonClicked.equals("Move Vertex")) {
        	
            if(firstVertex == null) {
            	
            	try {
            	
            		//This Vertex gets the point clicked
            		firstVertex = RightSideGraph.getVertexClicked(coordinateClicked.getPoint());
                    
            		//If the vertex was empty, throw exception
            		if(firstVertex == null) 
                    	throw new NullPointerException();
            	
            	}catch (NullPointerException nullVertex) {
            	
            		System.out.println("There is no vertex at that point: " + nullVertex);
            		
            		return;
            	
            	}
            	
            	//Set the color of it green once it is clicked
            	firstVertex.setVertexColor(Color.GREEN);
  			
            	//Repaint the canvas
            	this.repaint();	
            
            }else if(firstVertex.getVertexColor().equals(Color.GREEN)) {
            
            	//New point as to where vertex should move
            	Point currentP = coordinateClicked.getPoint();
            	
            	//With the new point, set the x and y coordinates of the current vertex
            	firstVertex.setX(currentP.x);
            	firstVertex.setY(currentP.y);
            	
            	//Set it back to black
            	firstVertex.setVertexColor(Color.BLACK);
            	
            	//Make the firstVertex empty again
            	firstVertex = null;
            	
            	//Repaint
            	this.repaint();
            
            }
            	
        }
        
        //Change Weight
        if(currentRButtonClicked.equals("Change Weight")) {
        	
        	//Always check so you know you are on a vertex
            if(firstVertex == null) {
            	
            	try {
            		
            		//Gets vertex clicked
            		firstVertex = RightSideGraph.getVertexClicked(coordinateClicked.getPoint());
            	   
            		//If the vertex was empty, then through the NullPointerException
            		if(firstVertex == null)
            			throw new NullPointerException();
             	   
            		//Set color equal to green to show it is selected
            		firstVertex.setVertexColor(Color.GREEN);
             	   
            		//Repaint canvas
            		this.repaint();
     			   
            	}catch (NullPointerException nullVertex) {
            		
            		return;
            
            	}
            //This checks if the color of the first is equal to green, indicating the connected vertice	
            }else if(firstVertex.getVertexColor().equals(Color.GREEN)) {
            	
            	//This will get the weight of the edge in between vertices
            	int weightOfCurrentEdge;
            	
            	try{
            	
            		//Do this same check for the second vertex
            		secondVertex = RightSideGraph.getVertexClicked(coordinateClicked.getPoint());
            		
              	   	if(secondVertex == null)
              	   		throw new NullPointerException();
              	   	
              	   	//If the second vertex is not equal to null, then get the weight from the entered value in graph gui
              	   	weightOfCurrentEdge = Integer.parseInt(graphGui.weightInputText.getText());
            	
            	}catch (NumberFormatException invalidNumberEntry) {
            	
            		//Set vertex back to zero
            		firstVertex.setVertexColor(Color.BLACK);
            		
            		//Resets vertices to null
              	   	firstVertex = null;
              	   	secondVertex = null;
              	   	
              	   	//Repaints the canvas
              	   	this.repaint();
              	   	
              	   	return;
            	
            	}catch(NullPointerException nullVertex) {
            		
            		//Set vertex back to zero
            		firstVertex.setVertexColor(Color.BLACK);
            		
            		//Resets vertices to null
              	   	firstVertex = null;
              	   	secondVertex = null;
              	   	
              	   	//Repaints the canvas
              	   	this.repaint();
              	   	
              	   	return;
            	
            	}
            	
            	/*
            	 * Create two temporary edges in which you will add weight to
            	 */
         	   	Edges egdePointOne = RightSideGraph.getEdge(firstVertex, secondVertex);
         	   	Edges edgePointTwo = RightSideGraph.getEdge(secondVertex, firstVertex);
          	   
         	   	//Here we set the weight of the edge
         	   	egdePointOne.setEdgeWeight(weightOfCurrentEdge);
         	   	edgePointTwo.setEdgeWeight(weightOfCurrentEdge);
          	   
         	   	//Repaint Canvas
         	   	this.repaint();
          	   
         	   	//Reset Vertices
         	   	firstVertex = null;
         	   	secondVertex = null;
         	   	
         	   	//Reset color of vertex to Black after weight is added
         	   	firstVertex.setVertexColor(Color.BLACK);
            
            }
        	
        }
   
    }
    
    /*
     * Used to set Vertices back to null
     */
    public void setVertexEmpty(Vertex vertex1, Vertex vertex2) {
	   		
	   		vertex1 = null;
	   		vertex2 = null;
	   		
	}

	public void paintComponent(Graphics g){ 
        super.paintComponent(g);
        
        //This HashSet will get all the vertices in the graph
        HashSet<Vertex> vertices = RightSideGraph.getAllVertices();
      
        //Object for Graphics2D (Vertices)
        Graphics2D graphics = (Graphics2D) g;
        
        //Here we paint the canvas of all the vertices
        for(Vertex eachVertex : vertices) {
        	
        	//Here you set the shape dimensions and coordinates for the Vertex
        	Shape vertexCircle = new Ellipse2D.Double((eachVertex.getX() - 5), (eachVertex.getY() - 5), 8, 8);
        	
        	//Here we set the shape to the vertex
        	eachVertex.setVertexShape(vertexCircle);
        	
        	/*
        	 * Here we use the Graphics2D functions to create our vertex
        	 */
        	graphics.setColor(eachVertex.getVertexColor());
            graphics.fill(vertexCircle);
        
        }

        //Here we paint the canvas of all the vertices
        for(Vertex eachVertice: vertices) {
        	
        	//Create a HashSet to get the current edges for each vertice
        	HashSet<Edges> edges = RightSideGraph.getVertexEdges(eachVertice);
        	
        	for(Edges eachEdge : edges) { 
        	
        		//Here we get two temporary points for each edge we need to draw
        		Point start = new Point(eachVertice.getX(), eachVertice.getY());
        		Point end = new Point(eachEdge.getSecondPoint().getX(), eachEdge.getSecondPoint().getY());
        		
        		//Here you set the color of each edge depending on the state
        		graphics.setColor(eachEdge.getEdgeColor());
        		//Here we set the size of the Stroke of the edge to 2
        		graphics.setStroke(new BasicStroke(2));
        		//Using the coordinates, we draw the line
        		graphics.drawLine(start.x, start.y, end.x, end.y);
             	
        		//If the edge has a weight
        		if(eachEdge.getEdgeWeight() != 0) {
             	
        			graphics.setFont(new Font("Arial", Font.ITALIC, 16));
        			
        			String weightOnEdge = "" + eachEdge.getEdgeWeight();
        			int x = ((start.x + end.x) / 2) + 30;
        			int y = ((start.y + end.y) / 2);
        			
        			//Use the above values to draw the weighted Edge
             		graphics.drawString(weightOnEdge, x, y);
             	
        		}
        
        	}
        
        }
        
    }
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}