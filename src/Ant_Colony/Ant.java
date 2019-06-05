package Ant_Colony;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import java.util.Random;
import java.util.Vector;

import Graph.*;

//	Enumeration for the current state of an ant
enum state{ HANDLE, SEARCHING_PATH, RETURNING, KILLED};

/**
 * 
 * @author PierreROMET
 * 
 * 	Class Ant
 *	This class represent an ant
 *	& all information & methods to manipulate it
 *
 */
public class Ant extends Thread{

	private int 				ID;
	private Vertex 			vtxStart;						//Ant starting city
	private Vertex 			vtxEnd;							//Next vertex to reach
	private Vertex			toReach;						//Final Vertex to reach to stop executing process
	private Vector<String> 	tabuList;				//Collection containing all the Vertex visited by an Ant
	private ArrayList<Edge> Edge_tabuList;	//Collection containing all the Edge visited by an Ant
	private Vector<String> 	ToVisit;				//Collection containing all the remaining Vertex of the graph to visit
	private state 			antState;						//Current state of the ant
	private int 				distTravelled;			//Total distance travel from start to current position
	private ArrayList<Vertex> testedVtxList;//Allow to store testedVtx to do not test two times the same vertex
	
//	It's important to remember that a ant must have a reference on Two points:
//	 - A reference to the common Ant knowledge
//	 - A Reference to the Graph adjacency matrix 	
	
	/**
	 * Constructor
	 * Instantiate Ant object with three vertices:
	 * Vertex starting & to reach, & ending point set a null for the instantiation
	 * & initialize data structures
	 * @param Vertex
	 * @param Vertex
	 * @param Vertex
	 * @param Integer
	 */
	public Ant(Vertex vtxStart_p, Vertex toReach_p, Vertex vtxEnd_p, int ID_p) {
		this.ID 				= ID_p;
		this.vtxStart 	= vtxStart_p;
		this.toReach		= toReach_p;
		this.vtxEnd 		= vtxEnd_p;
		this.antState		= state.HANDLE;
		this.tabuList		= new Vector<String>();
		this.ToVisit		= new Vector<String>();
		this.Edge_tabuList	= new ArrayList<Edge>();
		this.testedVtxList	= new ArrayList<Vertex>();
		ToVisit_init();
	}
	
	/**
	 * 	Executed function when the thread is started
	 */
	public void run() {
		this.antState = antState.SEARCHING_PATH;	//update state to corresponding to searching action
		while(toReach()) {												//the ant is searching a path
		}
	}	

	/**
	 * Assign the new Vertex to reach to the ant.
	 * Feed tabu list and to_visit list
	 * @return boolean
	 */
	public boolean toReach() {
		
//	return a heuristic destination, selected by DorigoProb
		this.vtxEnd = DorigoProb(vtxStart).getVtxOut();
		
//	test if the vtxEnd can be reach
		if(ToVisit.contains(this.vtxEnd.getName())) {
			tabuList.add(vtxStart.getName());
			ToVisit.remove(tabuList.lastElement());
			this.distTravelled += CommonKnowledge.matGraph.edgeValue(this.vtxStart, this.vtxEnd);
			//Kill the thread if the ant find the vertex to reach
			if(this.vtxEnd.getName() == this.toReach.getName()) {
				this.antState = antState.RETURNING;
				tabuList.add(vtxEnd.getName());
				ToVisit.remove(tabuList.lastElement());
				Thread.currentThread().interrupt();
				this.vtxStart = this.vtxEnd;
				System.out.println("Ant: "+this.ID+", Path: "+this.tabuList+", length: "+this.distTravelled+"\n");
				return false;
			}
			this.vtxStart = this.vtxEnd;							//update start&end vertex to find new destination from the vertex+1
			this.vtxEnd = null;			
			return true;
		}
		else {
			if(testedVtxList.size() < CommonKnowledge.matGraph.getNeighboor(this.vtxStart).size() //testedNeighborNbr < nbrOfNeighbor
					&& !testedVtxList.contains(this.vtxEnd)) {																				//& currentVtx was not previously test
				//no update of start&end vertex, a new vtxStart neighbor is tested.
				testedVtxList.add(this.vtxEnd);
				return true;
			}
			else {
				Thread.currentThread().interrupt();	//no possible vertex to join, thread is killed.
				this.antState = antState.KILLED;		//ant state is updated, as killed ant
				return false;
			}
		}
	}
	
	/**
	 * Return the selected Edge for the next ant move
	 * @return Edge
	 */
	public Edge DorigoProb(Vertex in_p) {
		
		ArrayList<Edge> neighbor 						= new ArrayList<Edge>();
		neighbor = CommonKnowledge.matGraph.getNeighboor(in_p);
		Hashtable<Float, Edge> myHashTable	= new Hashtable<Float, Edge>();
		ArrayList<Float> PhLg_tab 					= new ArrayList<Float>();
		ArrayList<Float> DorigoProb 				= new ArrayList<Float>();			//Dorigo Probability of each edge
		float neighborSum 									= 0.0f;
		Edge rtEdge 												= null;
		
		
//	Calculate PhLg ratio for all neighbor of current Vertex.
		for (Edge myEdge : neighbor) {			
			float tmp = CommonKnowledge.getPheromones(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
																											CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut())
																											) / (1.0f/(float)myEdge.getLgh());
			PhLg_tab.add(tmp);
			neighborSum += tmp;
		}
		
//	Calculate Dorigo Probability for all neighbor of current Vertex.
		for (int i = 0; i < neighbor.size(); i++) {
			if (neighborSum == 0) {
				DorigoProb.add(0.0f);
			}
			else {
				//transform PhLg as probability & store
				myHashTable.put(PhLg_tab.get(i)/ neighborSum, neighbor.get(i));
				DorigoProb.add(PhLg_tab.get(i)/ neighborSum);											
			}	
		}
		
		if (neighborSum == 0) { //All path have the same probability to be chosen
//		We randomly chose an edge, given that all edge have a null pheromone length
			Random rand = new Random(); 
			int aleaNbr = (rand.nextInt((int)neighborSum - 0 + neighbor.size()) + 0);
			rtEdge = neighbor.get(aleaNbr);
			Edge_tabuList.add(rtEdge);
		}
		else {
			
//		Sort DorigoProb Arraylist
			Collections.sort(DorigoProb);
			
//		Generate a Random number
			Random rand = new Random(); 
			float aleaNbr = (float) (rand.nextInt((int)neighborSum - 0 + Math.round(DorigoProb.get(DorigoProb.size()-1)) ) + 0) / 100.0f;
			
//		Use generated random number to define the next Vertex to reach
			for (Float PhLgRatio : DorigoProb) {
				if(aleaNbr <= PhLgRatio) {
					rtEdge = myHashTable.get(PhLgRatio);
					Edge_tabuList.add(rtEdge);
					break;
				}
			}
		}
		return rtEdge;
	}
	
	/**
	 * Initialize ToVisit tab, with the all node of the graph
	 */
	public void ToVisit_init() {
		for (Vertex vtx : CommonKnowledge.matGraph.vertices()) {
			this.ToVisit.add(vtx.getName());
		}
	}

	/**
	 * Get the ant current state
	 * @return state
	 */
	public state getAntState() {
		return this.antState;
	}
	
	/**
	 * Get an array of all visited Edges.
	 * @return ArrayList<Edge>
	 */
	public ArrayList<Edge> getTabuEdge() {
		return this.Edge_tabuList;
	}
	
	/**
	 * Get the total distance travel by ant
	 * @return integer
	 */
	public int getDist() {
		return this.distTravelled;
	}
	
	/**
	 * Get starting, ending & toReach vertices of the ant
	 */
	public String  getVertices()
	{
		return "vtxStart: "+this.vtxStart.getName()+" \n vtxEnd: "+" \n vtx to reach: "+this.toReach.getName();
	}
	
}
