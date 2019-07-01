package Decision_Making;

import java.util.ArrayList;

import Ant_Colony.CommonKnowledge;
import Graph.Edge;
import Graph.Vertex;

/**
 * 
 * @author PierreROMET
 * 
 * 	Class MarkovianInfluence
 * 	Algorithm to promote the emergence of new path,
 * 	into a dynamic generated cluster,
 * 	in the case of the initiate shortest path is broken.
 *
 *	More information:
 *	By default, this algorithm exploit by default an edge cut approach
 *	Edge Cut: All edge of the shortest path are disclaim
 */

public class MarkovianInfluence {
	
	private static int connectionDegree = 0;								//Define the connection degree to bring about dynamic clustering
	private static ArrayList<ArrayList<Vertex>> degreeMtx;	//Vertices lists organized by connection degree
	private static ArrayList<Float> mkInfluence;						//over time, this marker should be determined by graph density, by example
	
	
	/**
	 * Markovian Influence Engine
	 */
	public static void ApplyMkInfluence(int connDegree_p) {
		connectionDegree = connDegree_p;
		degreeMtx = new ArrayList<ArrayList<Vertex>>();
		mkInfluence = new ArrayList<Float>();
		initMtxInf();
		degreeMtx.set(0, CommonKnowledge.optPathVtxGet());		//set the 0 degree connection, with the shortest path
		setMkInfl();
		killShortPath();
		markovianInfl();
	}
	
	/**
	 * Setter for mkInfluence marker
	 * Range value [0 ; connectionDegree]
	 */
	private static void setMkInfl() {
		ArrayList<Float> tmp = new ArrayList<Float>();
		for (int i = connectionDegree; i >= 0 ; i--) {
			tmp.add((float)i);
		}
		mkInfluence.clear();
		mkInfluence.addAll(tmp);
		
		System.out.println(tmp);
		System.out.println(mkInfluence);
	}
	
	/**
	 * Make the shortest path seems impracticable for our ants
	 */
	private static void killShortPath() {
		for (Edge crtEdge : CommonKnowledge.optimalPathGet() ) {
			CommonKnowledge.setPheromone(
					CommonKnowledge.matGraph.getVtxNum(crtEdge.getVtxIn()),
					CommonKnowledge.matGraph.getVtxNum(crtEdge.getVtxOut()),
					Float.MAX_VALUE);
		}
	}
	
	/**
	 * For each vertex of the shortest path,
	 * determined the connection degree
	 * & modify the phero matrix with mkInfluence marker
	 */
	private static void markovianInfl() { //toDebug
		ArrayList<Vertex> alreadyVisited = new ArrayList<Vertex>();
		alreadyVisited.addAll(degreeMtx.get(0));
		
		for (int i = 0; i <= connectionDegree-1; i++) {
			for (Vertex myVertex : degreeMtx.get(i)) {
				for (Edge myEdge : CommonKnowledge.matGraph.getNeighboor(myVertex)) {
					
					if (!degreeMtx.get(0).contains(myEdge.getVtxIn())
							&& myEdge.getVtxOut() == CommonKnowledge.getVtxEnd()) {	//to Modify with env. knowledge vtxEnd
						System.out.println("*****************************************");
						System.out.println("THE END");
						
						System.out.println(myEdge.getVtxIn().getName());
						System.out.println(myEdge.getVtxOut().getName());
						
						System.out.println(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()));
						System.out.println(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()));
						
						System.out.println(
							CommonKnowledge.getPheromones(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
									CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()))
							);
						/****/
						//update the phero matrix with markov influence							
						if (CommonKnowledge.getPheromones(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
																								CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut())
																						 ) == 0.0f) {
									CommonKnowledge.setPheromone(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()), 
																							 	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()), 
																							 	mkInfluence.get(i)
																							);
						}
						else {
							CommonKnowledge.setPheromone(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()), 
									 												 	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()),
									 												 	CommonKnowledge.getPheromones(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
									 														 													 		CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut())
									 														 													 ) * mkInfluence.get(i)
																					);
						}
						System.out.println(
								CommonKnowledge.getPheromones(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
										CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()))
								);
						System.out.println("*****************************************");
					}
					
					if (i == 0) {
						if(!degreeMtx.get(0).contains(myEdge.getVtxOut())) {
							
							/**Debug**/
							System.out.println("*****************************************");
							System.out.println(myEdge.getVtxIn().getName());
							System.out.println(myEdge.getVtxOut().getName());
							
							System.out.println(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()));
							System.out.println(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()));
							
							System.out.println(
								CommonKnowledge.getPheromones(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
										CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()))
								);
							/****/
							
							//update degree connection matrix
							if (!degreeMtx.get(i+1).contains(myEdge.getVtxOut())) {
								degreeMtx.get(i+1).add(myEdge.getVtxOut());
							}
							
							//update the phero matrix with markov influence							
							if (CommonKnowledge.getPheromones(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
																									CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut())
																							 ) == 0.0f) {
										CommonKnowledge.setPheromone(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()), 
																								 	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()), 
																								 	mkInfluence.get(i)
																								);
							}
							else {
								CommonKnowledge.setPheromone(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()), 
										 												 	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()),
										 												 	CommonKnowledge.getPheromones(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
										 														 													 		CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut())
										 														 													 ) * mkInfluence.get(i)
																						);
							}
							System.out.println(
									CommonKnowledge.getPheromones(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
											CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()))
									);
							System.out.println("*****************************************");
						}						
					}
					else {
						
						if(degreeMtx.get(i).contains(myEdge.getVtxOut())) {
							
							/**Debug**/
							System.out.println("*****************************************");
							System.out.println(myEdge.getVtxIn().getName());
							System.out.println(myEdge.getVtxOut().getName());
							
							System.out.println(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()));
							System.out.println(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()));
							
							System.out.println(
								CommonKnowledge.getPheromones(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
										CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()))
								);
							/****/
							
							if (CommonKnowledge.getPheromones(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
																									CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut())
																							 ) == 0.0f) {
										CommonKnowledge.setPheromone( CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()), 
																									CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()), 
																									 mkInfluence.get(i-1)
																								);
							}
							else {
								CommonKnowledge.setPheromone(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()), 
																 							CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()),
																 							CommonKnowledge.getPheromones(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
																									 													 	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut())
																									 												 ) * mkInfluence.get(i-1)
																						);
							}
							
							System.out.println(
									CommonKnowledge.getPheromones(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
											CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()))
									);
							System.out.println("*****************************************");	
						}
						
						alreadyVisited.addAll(degreeMtx.get(i));
						
						if (!alreadyVisited.contains(myEdge.getVtxOut())) {
							
							/**Debug**/
							System.out.println("*****************************************");
							System.out.println(myEdge.getVtxIn().getName());
							System.out.println(myEdge.getVtxOut().getName());
							
							System.out.println(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()));
							System.out.println(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()));
							
							System.out.println(
								CommonKnowledge.getPheromones(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
										CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()))
								);
							/****/
							
							//update degree connection matrix
							if (!degreeMtx.get(i+1).contains(myEdge.getVtxOut())) {
								degreeMtx.get(i+1).add(myEdge.getVtxOut());
							}
							
							if (CommonKnowledge.getPheromones(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
																									CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut())
																							 ) == 0.0f) {
										CommonKnowledge.setPheromone( CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()), 
																									CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()), 
																									mkInfluence.get(i)
																								);
							}
							else {
								CommonKnowledge.setPheromone(	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()), 
																 							CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()),
																 							CommonKnowledge.getPheromones( 	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
																									 													 	CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut())
																									 												 ) * mkInfluence.get(i)
																						);
							}
							
							System.out.println(
									CommonKnowledge.getPheromones(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
											CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut()))
									);
							System.out.println("*****************************************");
						}
					}
				}
			}
		}
	}
	
	private static void initMtxInf() {
		for (int i = 0; i < connectionDegree; i++) {
			degreeMtx.add(new ArrayList<Vertex>(connectionDegree));
		}
	}
	
}
