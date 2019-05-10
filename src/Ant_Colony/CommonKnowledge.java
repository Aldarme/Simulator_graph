package Ant_Colony;

import java.util.ArrayList;
import java.util.Vector;

import Graph.Edge;

/**
 * 
 * @author promet
 *
 */

public class CommonKnowledge {

	private static int   nbrOfCities;												//total number of vertex to visit
	private static float evaporation;												//evaporation coefficient to update edge's strength phero.
	private static int   optimalPathLght;										//length of the optimal tour realize by an ant
	private static ArrayList<ArrayList<Float>> pheromones;	//adjacency pheromone matrix
	private static Vector<Vector<Edge>> adjMtx_replica;			//replica of the current adjacency matrix
	
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
	 * Allow to set pheromones strength of an edge.
	 * @param vtxIn
	 * @param vtxOut
	 * @param value
	 */
	public static void setPheromone(int vtxIn, int vtxOut, float value) {
		pheromones.get(vtxIn).set(vtxOut, value);
	}
	
	/**
	 * Return a distance specify between two Vertex
	 * @return
	 */
	public static Integer getAdjMtx_replica(int vtxIn, int vtxOut) {
		return adjMtx_replica.get(vtxIn).get(vtxOut).getLgh();
	}
	
	/**
	 * Realize a replica of the current adjacency matrix
	 * @param adjMtx_p
	 */
	public static void setAdjMtx_replica(Vector<Vector<Edge>> adjMtx_p) {
		for(int i=0; i < adjMtx_replica.size(); i++) {
			adjMtx_replica.set(i, adjMtx_p.get(i));
		}
	}
	
	public static int AdjMtxSize() {
		return adjMtx_replica.size();
	}
	
	/**
	 * Allow to evaporate (update) pheromones on the edges visited by an Ant
	 * to construct his tour.
	 */
	public void evaporate() {
//	on dépose les phéromones sur les arcs qui ont étés visités par la fourmis pour la création de son toure.
		
	}
	
}
