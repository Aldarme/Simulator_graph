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

	public AntCo(	String Starting_Vertex,
								String Ending_Vertex,
								Vector<Vector<Edge>> adjcencyMatrix)
	{
		
//	Provide a replica of the current adjacency matrix
		CommonKnowledge.setAdjMtx_replica(adjcencyMatrix);
		
//	Set all ants at the same depart Vertex and provide them the same end Vertex
		for(int i=0; i < CommonKnowledge.AdjMtxSize(); i++) {
			antArray.set(i, new Ant(Starting_Vertex, Ending_Vertex));
		}
	}

	public void run() {
		
	}

}
