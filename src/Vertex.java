import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Vertex {
	
	//X and Y to track where vertex is
	private int x, y, ID;
	
	//Keeps track of the number of Vertices created
	private static int NumberOfVertices = 0;
	
	//Circular Shape for Vertices
	private Shape circleVertex;
	
	//Color will change depending on vertice's state
	private Color currentColor;
	
	
	public Vertex() {
		
		//Sets coordinates to a default value of zero
		x = 0;
		y = 0;
		
		//Sets Dimensions of a vertex
		circleVertex = new Ellipse2D.Double(x, y, 8, 8);
		
		//Starting state should be black
		currentColor = Color.BLACK;
		
	}// Constructor
	
	/*
	 * Constructor that takes coordinates, and draws vertex accordingly
	 */
	public Vertex(int x, int y) {
	
		super();
		
		//Uses the number of Vertices as a key
		ID = NumberOfVertices ++;
		
		//Sets x and y coordinates from the coordinates passed in when clicked
		this.x = x;
		this.y = y;
		
		//Sets Dimensions of a vertex
		circleVertex = new Ellipse2D.Double((x - 5), (y - 5), 8, 8);
		
		//Starting state should be black
		currentColor = Color.BLACK;
	
	} //Constructor
	
	
	//Returns current x-coordinate
	public int getX() {
		
		return x;
	
	}
	
	//Sets current x location of Vertex
	public void setX(int x) {
		
		this.x = x;
		
	}
	
	//Returns current y-coordinate
	public int getY() {
		
		return y;
	
	}
	
	//Sets current y location of Vertex
	public void setY(int y) {
		
		this.y = y;
		
	}
	
	//Returns current vertex ID
	public int getID() {
	
		return ID;
	
	}
	
	//Returns Shape of Vertex
	public Shape getVertexShape() {
	
		return circleVertex;
	
	}
	
	//Sets Shape of the vertex
	public void setVertexShape(Shape s) {
			
		circleVertex = s;
			
	}
	
	//Returns current color of Vertex
	public Color getVertexColor() {
	
		return currentColor;
	
	}
	
	//Sets color of a Vertex, called when Vertex is clicked
	public void setVertexColor(Color currentColor) {
		
		this.currentColor = currentColor;
		
	}
	
	/*
	 * toString() method for a Vertex
	 */
	public String toString() {
	
		return "Vertex: " + ID;
	
	}

}