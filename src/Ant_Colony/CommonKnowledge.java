package Ant_Colony;

import java.util.ArrayList;

import Graph.Edge;
import Graph.MatrixGraph;
import Graph.Vertex;

/**
 * 
 * @author PierreROMET
 * 
 * 	Class CommonKnowledge
 * 	Common knowledge of all ants of the colony
 *
 */

public class CommonKnowledge {

	private static Vertex vtxStart;
	private static Vertex vtxEnd;
	private static int   nbrOfCities;										//total number of vertex to visit
	private static float evaporation;										//evaporation coefficient to update edge's strength phero.
	private static int   optimalPathLght;								//length of the optimal tour realize by an ant
	private static ArrayList<Edge> 		optimalPath;			//Edges that form the best path
	private static ArrayList<Vertex> 	optimalPath_vtx;	//Vertices of the shortest path
	private static ArrayList<Vertex> 	optimalPath_vtx_tmp;	//short term copy of optimalPath_vtx
	private static ArrayList<ArrayList<Float>> pheromones;	//adjacency pheromone matrix
	public 	static MatrixGraph 	matGraph;								//adjacency matrix
	private static int					algoIteration;					//Ant Colony iteration number
	
	/**
	 * Initialize the environment of the Ant colony
	 *  optimalPath
	 *  pheromones
	 */
	public static void CommonKnowledgeInit(Vertex vtxStart_p, Vertex vtxEnd_p) {
		
		vtxStart						= vtxStart_p;
		vtxEnd 							= vtxEnd_p;
		optimalPath					= new ArrayList<Edge>();
		optimalPath_vtx 		= new ArrayList<Vertex>();
		optimalPath_vtx_tmp = new ArrayList<Vertex>();
		pheromones 					= new ArrayList<ArrayList<Float>>();
		optimalPathLght 		= Integer.MAX_VALUE; 										//Set optimal length at the maximum possible value
		
		for (int i = 0; i < matGraph.size(); i++) {
			pheromones.add( new ArrayList<Float>(matGraph.size()) );
		}
		
		for (int i = 0; i < pheromones.size(); i++) {
			for (int j = 0; j < pheromones.size(); j++) {
				pheromones.get(i).add(0.0f);
			}
		}
//	Debug initialization of pheromones matrix
//	System.out.println(""+pheromones+"\n"+pheromones.size());
	}
	
	/**
	 * Display adjacency pheromones matrix
	 */
	public static void pheroMatDisplay() {
		System.out.println("Adjacency pheromones matrix \n");
		for (ArrayList<Float> array : pheromones) {
			System.out.println(array+"\n");
		}
	}
	
	/**
	 * Return pheromone adjacency matrix
	 */
	public static Object[][] pheroMtx(){
		Object[][] array = new Object[pheromones.size()][];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = pheromones.get(i).toArray();
		}
		
		return array;
	}
	
	/**
	 * Set the optimal path of the graph
	 * @param ArrayList<Edge>
	 */
	public static void setOptiPath(ArrayList<Edge> edges_p) {
		optimalPath = edges_p;
	}
	
	/**
	 * Set the number of cities into the graph
	 * @param Integer
	 */
	public static void nbrCtSet(int nbr) {
		nbrOfCities = nbr;
	}
	
	/**
	 * Set the coefficient of evaporation
	 * @param float
	 */
	public static void evapSet(float evap) {
		evaporation = evap;
	}
	
	/**
	 * Set the optimal length of the graph
	 * @param integer
	 */
	public static void optLgthSet(int optl) {
		optimalPathLght = optl;
	}
	
	/**
	 * Get the number of cities into the graph
	 * @return integer
	 */
	public static int nbrCtGet() {
		return nbrOfCities;
	}
	
	/**
	 * Get the coefficient of evaporation
	 * @return float
	 */
	public static float evapGet() {
		return evaporation;
	}
	
	/**
	 * Get the optimal length of the graph
	 * @return integer
	 */
	public static int optLgthGet() {
		return optimalPathLght;
	}
	
	/**
	 * Get the optimal path of graph
	 * @return ArrayList<Edge>
	 */
	public static ArrayList<Edge> optimalPathGet() {
		return optimalPath;
	}
	
	/**
	 * Get the optimal path of graph
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getOptimalPath_toString() {
		ArrayList<String> tmp = new ArrayList<String>();
		for (Edge myEdge : optimalPath) {
			tmp.add(myEdge.getVtxIn().getName());
			tmp.add(myEdge.getVtxOut().getName());
		}
		return tmp;
	}
	
	/**
	 * Get the optimal path as a list of vertices
	 * @return ArrayList<Vertex>
	 */
	public static ArrayList<Vertex> optPathVtxGet(){
		return optimalPath_vtx;
	}
	
	/**
	 * Get the Get the optimal path as a list of String
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getOptPathVtx_toString(){
		
		ArrayList<String> tmp = new ArrayList<String>();
		for (Vertex myVertex : optimalPath_vtx_tmp) {
			tmp.add(myVertex.getName());
		}
		return tmp;
	}
	
	/**
	 * Get vertices of the optimal path
	 * @return ArrayList<Vertex>
	 */
	public static void optPathVerticesGet() {		
		for (Edge edge : optimalPath) {
			if (!optimalPath_vtx_tmp.contains(edge.getVtxIn())) {
				optimalPath_vtx_tmp.add(edge.getVtxIn());
			}
			if (!optimalPath_vtx_tmp.contains(edge.getVtxOut())) {
				optimalPath_vtx_tmp.add(edge.getVtxOut());
			}			
		}
	}
	
	/**
	 * Get names of vertices of the optimal path
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> optimalPathGet_string() {
		ArrayList<String> tmp = new ArrayList<String>();
		for (Vertex myVertex : optimalPath_vtx) {
			tmp.add(myVertex.getName());
		}
		return tmp;
	}
	
	/**
	 * Update the optimal path
	 * optimal path as a list of vertices
	 */
	public static void flush_optPath_vtx() {
		optimalPath_vtx.clear();
		optimalPath_vtx.addAll(optimalPath_vtx_tmp);
		optimalPath_vtx_tmp.clear();
	}
	
	/**
	 * Get the size of the pheromone matrix
	 * @return integer
	 */
	public static int CkSize() {
		return pheromones.size();
	}
	
	/**
	 * Get pheromone strength of an edge
	 * @param integer
	 * @param integer
	 * @return float
	 */
	public static float getPheromones(int i, int j){
		return pheromones.get(i).get(j);
	}
	
	/**
	 * Set pheromone strength of an edge.
	 * @param integer
	 * @param integer
	 * @param float
	 */
	public static void setPheromone(int vtxIn, int vtxOut, float value) {
		pheromones.get(vtxIn).set(vtxOut, value);
	}
	
	/**
	 * Evaporate (update) pheromones on the edges visited by an Ant
	 * to construct his tour.
	 * @param Edge
	 * @param integer
	 */
	public static float Dorigo_evaporation(Edge edge_p, int tourLenght_p) {
		float phero = getPheromones(matGraph.getVtxNum(edge_p.getVtxIn()),matGraph.getVtxNum(edge_p.getVtxOut()));
		return (((1.0f-evaporation) * phero) + (1.0f / tourLenght_p));
	}
	
	/**
	 * Increment Ant Colony iteration number
	 */
	public static void iterationCnt() {
		algoIteration++;
	}
	
	/**
	 * Display Ant Colony iteration number
	 * @return int
	 */
	public static int algoIterationGet() {
		return algoIteration;
	}
	
	/**
	 * Display shortest path
	 */
	public static void display_shortestPath() {
		System.out.println("Shortest path is:");
		for (Vertex myVtx : optimalPath_vtx) {
			System.out.println(myVtx.getName());
		}
		System.out.println("with a length of: " + optimalPathLght + "\n");
	}
	
	/**
	 * Get the starting point of the Ant Colony
	 * @return
	 */
	public static Vertex getVtxStart() {
		return vtxStart;
	}
	
	/**
	 * Get the ending point of the Ant Colony
	 * @return
	 */
	public static Vertex getVtxEnd() {
		return vtxEnd;
	}
	
	
}
