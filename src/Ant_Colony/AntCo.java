package Ant_Colony;

import java.util.ArrayList;

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
		antArray = new ArrayList<Ant>();
//		Set optimal length at the maximum possible value
		CommonKnowledge.optLgthSet(Integer.MAX_VALUE);
		
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
		System.out.println("Optimal path: "+CommonKnowledge.optimalPathGet_string());
		System.out.println("Optimal length: "+CommonKnowledge.optLgthGet());
	}
	
	/**
	 * Initiate all ants thread
	 */
	public void initThreads() {
		for (Ant ant : antArray) {
			ant.start();
		}
//		antArray.get(0).start();
//		antArray.get(1).start();
//		antArray.get(2).start();
//		antArray.get(3).start();
//		antArray.get(4).start();
//		antArray.get(5).start();
//		antArray.get(6).start();
//		antArray.get(7).start();
		
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
					CommonKnowledge.setPheromone( CommonKnowledge.matGraph.getVtxNum(edges.getVtxIn()),
																				CommonKnowledge.matGraph.getVtxNum(edges.getVtxOut()), 
																				CommonKnowledge.Dorigo_evaporation(edges, ant.getDist())
																			);
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
