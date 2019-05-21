package Ant_Colony;

import java.util.ArrayList;
import java.util.Vector;

import Graph.*;

/**
 * 
 * @author promet
 *
 */
public class AntCo {

	private ArrayList<Ant> antArray;

	public AntCo(	Vertex Starting_Vertex,
								Vertex Ending_Vertex)
	{
		
//	Set all ants at the same depart Vertex and provide them the same end Vertex
		for(int i=0; i < CommonKnowledge.matGraph.size(); i++) {
			antArray.set(i, new Ant(Starting_Vertex, Ending_Vertex));
		}
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
	 * @return
	 */
	public void getBack() {
		
		for (Ant ant : antArray) {
			if(ant.getAntState() == state.RETURNING) {
				for (Edge edges : ant.getTabuEdge()) {
					edges.setLength((int)CommonKnowledge.Dorigo_evaporation(edges, ant.getDist()));
				}
			}
		}		
		System.out.println("All pheromone strength has been updated.");		
	}
	
	/**
	 * Local research function
	 */
	public void localSearch() {
		//TODO
	}

}
