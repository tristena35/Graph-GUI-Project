import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;


public class MainGraph {
	
	//This HashMap contains the Vertices as well as Edges
	private HashMap<Vertex, HashSet<Edges>> graphComponents;
	
	/*
	 * Variables to hold number of Vertices and Edges
	 */
	private static int totalVertices;
	private static int totalEdges;
	
	
	public MainGraph() {

		graphComponents = new HashMap<Vertex, HashSet<Edges>>();
		
		totalEdges = 0;
		totalVertices = 0;
	
	}//Constructor
	
	//Adds a vertex
	public void addVertex(Vertex individualVertex) {
		
		//If the graph does not yet have the specified vertex, add it
		if(!graphComponents.containsKey(individualVertex)) {
		
			graphComponents.put(individualVertex, new HashSet<Edges>());
			
			//Increment the number of vertices
			totalVertices ++;
		
		}
	
	}
	
	//Adds an Edge
	public void addEdge(Vertex firstVertex, Vertex secondVertex) {
		
		if(edgeAlreadyExists(firstVertex,secondVertex))
			return;
		
		/*
		 * Add the edges to the vertices
		 */
		graphComponents.get(firstVertex).add(new Edges(firstVertex, secondVertex));
		graphComponents.get(secondVertex).add(new Edges(secondVertex, firstVertex));
		
		//Finally, add 2 to the number of edges
		totalEdges = totalEdges + 2;
	
	}
	
	/*
	 * This functions simply returns true if the edge already exists
	 */
	public boolean edgeAlreadyExists(Vertex v1, Vertex v2) {
		
		Edges tempEdge = getEdge(v1,v2);
		
		//If Edge does exist
		if(tempEdge != null)
			return true;
		else //If edge does not exist
			return false;
	
	}

	//This function will return the clicked Vertex
	public Vertex getVertexClicked(Point vertexClickedPoint) {
		
		for(Vertex eachVertex: graphComponents.keySet()) {
			
			/*
			 * Compare the two vertices
			 */
			if(eachVertex.getVertexShape().contains(vertexClickedPoint)) {
				
				//Return vertice that has the same point
				return eachVertex;
			
			}
		
		}
	
		//If there is no point there, return null
		return null;
		
	}
	
	//This function retrieves a specific vertex wanted
	public Vertex getVertex(int uniqueVertexID) {
		
		for(Vertex eachVertex: graphComponents.keySet()) {
		
			/*
			 * If the vertice ID's are the same, then return that specific vertex
			 */
			if(eachVertex.getID() == uniqueVertexID) {
				return eachVertex;
			}
		
		}
		
		//If not, return null
		return null;
	
	}
	
	//This function retrieves a specific edge
	public Edges getEdge(Vertex startVertex, Vertex endVertex) {
		
		//Create a Hashset for edges
		HashSet<Edges> edges = graphComponents.get(startVertex);
		
		/*
		 * A loop to go through each edge on the graph
		 */
		for(Edges eachEdge: edges) {
		
			//If the edge matches, return it
			if(eachEdge.getSecondPoint().equals(endVertex))
				return eachEdge;
		
		}
		
		//If not, return null
		return null;
	
	}
	
	//This functions returns ALL the vertices in the graph
	public HashSet<Vertex> getAllVertices(){
		
		//Create a hashSet of vertices
		HashSet<Vertex> allVertices = new HashSet<Vertex>();
		
		//This for loop goes through all vertices and adds them to the HashSet
		for(Vertex individualVertex: graphComponents.keySet())
			allVertices.add(individualVertex);
		
		//Return the set of vertices
		return allVertices;
	
	}
	
	/*
	 * This function will return all adjacent edges of a specific vertex
	 */
	public HashSet<Edges> getVertexEdges(Vertex desiredVertex){
		
		return graphComponents.get(desiredVertex);
	
	}
	

}