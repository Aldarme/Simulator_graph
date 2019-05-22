package Ant_Colony;

import java.util.ArrayList;

import Graph.Edge;
import Graph.MatrixGraph;

/**
 * 
 * @author promet
 *
 */

public class CommonKnowledge {

	private static int   nbrOfCities;						//total number of vertex to visit
	private static float evaporation;						//evaporation coefficient to update edge's strength phero.
	private static int   optimalPathLght;				//length of the optimal tour realize by an ant
	private static ArrayList<ArrayList<Float>> pheromones;	//adjacency pheromone matrix
	public 	static MatrixGraph matGraph;				//adjacency matrix
	
	public static void initPhero() {
		pheromones = new ArrayList<ArrayList<Float>>();
		
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
	
	public static void nbrCtSet (int nbr) {
		nbrOfCities = nbr;
	}
	
	public static void evapSet (float evap) {
		evaporation = evap;
	}
	
	public static void optLgthSet (int optl) {
		optimalPathLght = optl;
	}
	
	public static int nbrCtGet () {
		return nbrOfCities;
	}
	
	public static float evapGet () {
		return evaporation;
	}
	
	public static int optLgthGet () {
		return optimalPathLght;
	}
	
	public static int CkSize() {
		return pheromones.size();
	}
	
	public static float getPheromones(int i, int j){
		return pheromones.get(i).get(j);
	}
	
	/**
	 * Allow to set pheromone strength of an edge.
	 * @param vtxIn
	 * @param vtxOut
	 * @param value
	 */
	public static void setPheromone(int vtxIn, int vtxOut, float value) {
		pheromones.get(vtxIn).set(vtxOut, value);
	}
	
	/**
	 * Allow to evaporate (update) pheromones on the edges visited by an Ant
	 * to construct his tour.
	 */
	public static float Dorigo_evaporation(Edge edge_p, int tourLenght_p) {
		return ((1.0f-evaporation) * getPheromones(matGraph.getVtxNum(edge_p.getVtxIn()),CommonKnowledge.matGraph.getVtxNum(edge_p.getVtxOut())) + (1.0f / tourLenght_p));
	}
	
}
