package Simulator;

import Graph.*;

import java.util.ArrayList;

import Ant_Colony.*;
//import Shortest_Path.*;

/**
 * 
 * @author promet
 * @version 0.0.1
 *
 */
public class main {

	public static void main(String[] args) {
		
//		Creation of Vertices
		Vertex vtx1 = new Vertex("1");
		Vertex vtx2 = new Vertex("2");
		Vertex vtx3 = new Vertex("3");
		Vertex vtx4 = new Vertex("4");
		Vertex vtx5 = new Vertex("5");
		Vertex vtx6 = new Vertex("6");
		Vertex vtx7 = new Vertex("7");
		Vertex vtx8 = new Vertex("8");

//		Creation of Edges
		Edge edgA = new Edge(8, vtx1, vtx2);
		Edge edgB = new Edge(4, vtx1, vtx3);
		Edge edgC = new Edge(1, vtx3, vtx2);
		Edge edgD = new Edge(5, vtx2, vtx4);
		Edge edgE = new Edge(2, vtx3, vtx5);
		Edge edgF = new Edge(4, vtx5, vtx4);
		Edge edgG = new Edge(3, vtx4, vtx7);
		Edge edgH = new Edge(9, vtx8, vtx4);
		Edge edgI = new Edge(7, vtx4, vtx6);
		Edge edgJ = new Edge(3, vtx5, vtx6);
		Edge edgK = new Edge(1, vtx7, vtx8);
		Edge edgL = new Edge(2, vtx6, vtx8);

//		Instantiation of the adjacency matrix
		CommonKnowledge.matGraph = new MatrixGraph(8);
		
//		Insertion of Vertex into adjacency matrix
		CommonKnowledge.matGraph.vertexAdd(vtx1);
		CommonKnowledge.matGraph.vertexAdd(vtx2);
		CommonKnowledge.matGraph.vertexAdd(vtx3);
		CommonKnowledge.matGraph.vertexAdd(vtx4);
		CommonKnowledge.matGraph.vertexAdd(vtx5);
		CommonKnowledge.matGraph.vertexAdd(vtx6);
		CommonKnowledge.matGraph.vertexAdd(vtx7);
		CommonKnowledge.matGraph.vertexAdd(vtx8);

//		Insertion of the edges into adjacency matrix
		CommonKnowledge.matGraph.edgeAdd(edgA);
		CommonKnowledge.matGraph.edgeAdd(edgB);
		CommonKnowledge.matGraph.edgeAdd(edgC);
		CommonKnowledge.matGraph.edgeAdd(edgD);
		CommonKnowledge.matGraph.edgeAdd(edgE);
		CommonKnowledge.matGraph.edgeAdd(edgF);
		CommonKnowledge.matGraph.edgeAdd(edgG);
		CommonKnowledge.matGraph.edgeAdd(edgH);
		CommonKnowledge.matGraph.edgeAdd(edgI);
		CommonKnowledge.matGraph.edgeAdd(edgJ);
		CommonKnowledge.matGraph.edgeAdd(edgK);
		CommonKnowledge.matGraph.edgeAdd(edgL);

//		Display adjacency matrix to control it
		CommonKnowledge.matGraph.adjMatDisplay();	//Debug function
		
//		Initialization of the adjacency pheromones matrix
		CommonKnowledge.initPhero();

//		Instantiation of the Ant Colony algorithm
		AntCo antColony = new AntCo(vtx1, vtx8);
//		antColony.antsDisplay();					//debug function
		
////		Initiate all ant thread
		antColony.initThreads();
		
////		Wait for the end of all threads
//		antColony.endThreads();
		
////		Check all ants to find the best path
//		antColony.Scoring();
		
////		Apply Local Search
//		//TODO
		
////		All all ants to return at their starting point
//		antColony.getBack(); 
	}

}
