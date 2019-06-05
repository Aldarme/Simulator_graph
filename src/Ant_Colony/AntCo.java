package Ant_Colony;

import java.util.ArrayList;

import Graph.*;

/**
 * 
 * @author PierreROMET
 * 
 * 	Class AntCo
 *	This class provide methods to use "Ant Colony" algorithm
 */
public class AntCo {

	private ArrayList<Ant> antArray;

	/**
	 * Constructor
	 * Given a starting vertex & vertex to reach for all ants
	 * @param Vertex
	 * @param Vertex
	 */
	public AntCo(	Vertex Starting_Vertex,
								Vertex Ending_Vertex)
	{
		antArray = new ArrayList<Ant>();
		
//	Set all ants at the same depart Vertex and provide them the same end Vertex
		for(int i=0; i < CommonKnowledge.matGraph.size(); i++) {
			antArray.add(new Ant(Starting_Vertex, Ending_Vertex, null, i));
		}
	}
	
	/**
	 * Save the best traveled path
	 */
	public void Scoring() {
		for (Ant ant : antArray) {
			if(ant.getDist() < CommonKnowledge.optLgthGet()) {
				CommonKnowledge.optLgthSet(ant.getDist());				//store best path length
				CommonKnowledge.setOptiPath(ant.getTabuEdge());		//store best path
			}
		}
		System.out.println("Optimal path: "+CommonKnowledge.optimalPathGet_string());	//Debug
		System.out.println("Optimal length: "+CommonKnowledge.optLgthGet());					//Debug
	}
	
	/**
	 * Initiate all ants thread
	 */
	public void initThreads() {
		for (Ant ant : antArray) {
			ant.start();
		}		
	}
	
	/**
	 * Wait for the end of all threads
	 */
	public void endThreads() {
		for (Ant ant : antArray) {
			try {
				ant.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("thread interruption Occur");
			}
		}
	}	
	
	/**
	 * Allow to getBack ants to its starting vertex
	 */
	public void getBack() {
		
		for (Ant ant : antArray) {
			if(ant.getAntState() == state.RETURNING) {
				for (Edge edges : ant.getTabuEdge()) {
					CommonKnowledge.setPheromone( CommonKnowledge.matGraph.getVtxNum(edges.getVtxIn()),
																				CommonKnowledge.matGraph.getVtxNum(edges.getVtxOut()), 
																				CommonKnowledge.Dorigo_evaporation(edges, ant.getDist())
																			);
				}
			}
		}		
		System.out.println("All pheromone strength has been updated.");		//Debug
	}
	
	/**
	 * Local research function
	 */
	public void localSearch() {
		//TODO
	}
	
	/**
	 * Debug
	 * Display all ants inside the antArray
	 */
	public void antsDisplay() {
		for (Ant ant : antArray) {
			System.out.println("ant: "+ant.getVertices()+"\n");
		}
	}
	
}
