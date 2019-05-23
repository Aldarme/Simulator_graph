package Ant_Colony;

import java.util.ArrayList;
import Graph.Edge;
import Graph.MatrixGraph;

/**
 * 
 * @author PierreROMET
 * 
 * 	Class CommonKnowledge
 * 	Common knowledge of all ants of the colony
 *
 */

public class CommonKnowledge {

	private static int   nbrOfCities;						//total number of vertex to visit
	private static float evaporation;						//evaporation coefficient to update edge's strength phero.
	private static int   optimalPathLght;				//length of the optimal tour realize by an ant
	private static ArrayList<Edge> optimalPath;	//Edges that form the best path
	private static ArrayList<ArrayList<Float>> pheromones;	//adjacency pheromone matrix
	public 	static MatrixGraph matGraph;				//adjacency matrix
	
	/**
	 * Initialize data structures of the static class
	 *  optimalPath
	 *  pheromones
	 */
	public static void initPhero() {
		optimalPath	= new ArrayList<Edge>();
		pheromones 	= new ArrayList<ArrayList<Float>>();
		
		for (int i = 0; i < matGraph.size(); i++) {
			pheromones.add( new ArrayList<Float>(matGraph.size()) );
		}
		
		for (int i = 0; i < pheromones.size(); i++) {
			for (int j = 0; j < pheromones.size(); j++) {
				pheromones.get(i).add(0.0f);
			}
		}
//	Debug initialization of pheromones matrix
	System.out.println(""+pheromones+"\n"+pheromones.size());
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
	 * Get names of vertices of the optimal path of graph
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> optimalPathGet_string() {
		ArrayList<String> tmp = new ArrayList<String>();
		for (Edge edge : optimalPath) {
			tmp.add(edge.getVtxIn().getName());
		}
		return tmp;
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
		float tmp = ((1.0f-evaporation) * getPheromones(matGraph.getVtxNum(edge_p.getVtxIn()),matGraph.getVtxNum(edge_p.getVtxOut())));
		float tmp2 = tmp + (1.0f / tourLenght_p);
		return ( tmp2);
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
}
