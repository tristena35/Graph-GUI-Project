import java.awt.Color;

public class Edges {
	
	//Two Vertices used in order to connect edges
	private Vertex firstPoint, secondPoint;
	
	//Responsible for managing color for edge
	private Color colorOfEdge;
	
	//Variable to keep track of edge
	private int NumberOfEdge;
	
	//Variable to keep track of weight of Edge
	private int weightOfEdge;
	
	//Number of current Edges that have been made
	private static int numOfEdges=0;
	
	
	public Edges() {
		
		//Default values for vertices
		firstPoint = null;
		secondPoint = null;
		
		//Default weight of edge
		weightOfEdge = 8000;
		
		//Default color of edge
		colorOfEdge = Color.RED;
		
	} //Constructor

	public Edges(Vertex firstPoint) {
		
		super();
		
		//Sets the point passed equal to the class vertex
		this.firstPoint = firstPoint;
		
		//Add to the amount of Edges
		NumberOfEdge = ++numOfEdges;
		
		//Default color of edge
		colorOfEdge = Color.RED;
	
	}//Constructor

	/*
	 * This constructor takes both the vertices necessary to create the edge
	 */
	public Edges(Vertex firstPoint,Vertex secondPoint) {
		
		super();
		
		//Initializing class vertices
		this.firstPoint = firstPoint;
		this.secondPoint = secondPoint;
		
		//Add 1 to the number of edges
		NumberOfEdge = numOfEdges ++;
	
		//Default color of edge
		colorOfEdge = Color.RED;
		
	}

	/*
	 * This Constructor takes both vertices as well as the weight
	 */
	public Edges(Vertex firstPoint, Vertex secondPoint, int weight) {
		
		this.firstPoint = firstPoint;
		this.secondPoint = secondPoint;
		
		//Initializing class variable for weight to weight passed
		weightOfEdge = weight;
		
		//Add 1 to the number of edges
		NumberOfEdge = numOfEdges ++;
		
	}//Constructor
	
	
	//Getter for the First Vertex
	public Vertex getFirstPoint() {
		
		return firstPoint;
	
	}
	
	//Getter for the Second Vertex
	public Vertex getSecondPoint() {
	
		return secondPoint;
	
	}
	
	//Getter for the Edge's weight
	public int getEdgeWeight() {
		
		return weightOfEdge;
	
	}

	//Getter for Edge's Color
	public Color getEdgeColor() {
		
		return colorOfEdge;
	
	}
	
	//Setter for Edge's Weight
	public void setEdgeWeight(int weight) {
		
		weightOfEdge = weight;
	
	}

	//Setter for Edge's Color
	public void setEdgeColor(Color color) {
		
		colorOfEdge = color;
	
	}
	
	//Override toString method to print Egde Information
	public String toString() {
		
		return "Head: " + firstPoint.getID() + " Tail: " + secondPoint.getID();
		
	}


}