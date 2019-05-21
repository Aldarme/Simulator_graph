package Ant_Colony;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import Graph.*;
import sun.security.provider.certpath.AdjacencyList;

//	Enumeration for the current state of an ant
enum state{ HANDLE, SEARCHING_PATH, RETURNING, KILLED};

/**
 * 
 * @author promet
 *
 */
public class Ant extends Thread{

	private Vertex 						vtxStart;				//Ant starting city
	private Vertex 						vtxEnd;					//City to reach
	private Vector<String> 		tabuList;				//Collection containing all the Vertex visited by an Ant
	private ArrayList<Edge> 	Edge_tabuList;	//Collection containing all the Edge visited by an Ant
	private Vector<String> 		ToVisit;				//Collection containing all the remaining Vertex of the graph to visit
	private state 						antState;				//Current state of the ant
	private int 							distTravelled;	//Total distance travel from start to current position
	private ArrayList<Vertex> testedVtxList;	//Allow to store testedVtx to do not test two times the same vertex
	
//	It's important to remember that a ant must have a reference on Two points:
//	 - A reference to the common Ant knowledge
//	 - A Reference to the Graph adjacency matrix 	
	
	public Ant(Vertex vtxStt, Vertex vtxEd) {
		this.vtxStart 		= vtxStt;
		this.vtxEnd 			= vtxEd;
		this.antState			= state.HANDLE;
		this.tabuList.setSize(CommonKnowledge.matGraph.size());
		this.ToVisit.setSize(CommonKnowledge.matGraph.size());
	}
	
	/**
	 * 	Function execute when thread is started
	 */
	public void run() {
		this.antState = antState.SEARCHING_PATH;	//update state to corresponding to searching action
		while(!toReach()) {												//the ant is searching a path
		}
		this.distTravelled += CommonKnowledge.matGraph.edgeValue(this.vtxStart, this.vtxEnd);
		this.antState = antState.RETURNING;
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
	 * @return int
	 */
	public int getDist() {
		return this.distTravelled;
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
		Hashtable<Float, Edge> myHashTable	=	new Hashtable<Float, Edge>();
		ArrayList<Float> PhLg_tab 					= new ArrayList<Float>();
		ArrayList<Float> DorigoProb 				= new ArrayList<Float>();
		float neighborSum 									= 0.0f;
		Edge rtEdge 												= null;
		
		
//	Calculate PhLg ratio for all neighbor of current Vecter.
		for (Edge myEdge : neighbor) {
			float tmp = CommonKnowledge.getPheromones(CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxIn()),
									CommonKnowledge.matGraph.getVtxNum(myEdge.getVtxOut())
																								)/ (1/(float)myEdge.getLgh());
			myHashTable.put(tmp, myEdge);
			PhLg_tab.add(tmp);
			neighborSum += tmp;
		}
		
//	Calculate Dorigo Probability for all neighbor of current Vecter.
		for (Float PhLgRatio : PhLg_tab) {
			DorigoProb.add(PhLgRatio / neighborSum);
		}
		
//	Add Max. possible value at the end of the array to have a stop condition
		DorigoProb.add(neighborSum);
		
//	Sort DorigoProb Arraylist
		Collections.sort(DorigoProb);
		
//	Generate a Random number
		Random rand = new Random(); 
		int nombreAleatoire = rand.nextInt((int)neighborSum - 0 + 1) + 0;
		
//	Use generated random number to define the next Vertex to reach
		for (Float PhLgRatio : DorigoProb) {
			if((float)nombreAleatoire < PhLgRatio) {
				rtEdge = myHashTable.get(PhLgRatio);
				Edge_tabuList.add(rtEdge);
				break;
			}
		}
		return rtEdge;
	}

	
}
