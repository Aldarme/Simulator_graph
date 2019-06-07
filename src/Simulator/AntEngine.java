package Simulator;

import Ant_Colony.AntCo;
import Ant_Colony.CommonKnowledge;
import Decision_Making.Markov_Decision_Process;
import Graph.Edge;
import Graph.MatrixGraph;
import Graph.Vertex;

public class AntEngine {
	
	private Vertex vtx1;
	private Vertex vtx2;
	private Vertex vtx3;
	private Vertex vtx4;
	private Vertex vtx5;
	private Vertex vtx6;
	private Vertex vtx7;
	private Vertex vtx8;
	
	private Edge edgA;
	private Edge edgB;
	private Edge edgC;
	private Edge edgD;
	private Edge edgE;
	private Edge edgF;
	private Edge edgG;
	private Edge edgH;
	private Edge edgI;
	private Edge edgJ;
	private Edge edgK;
	private Edge edgL;
	
	
	public AntEngine(int MDP_p){
		/**
		 * TODO
		 * Pour une implementation dynamique
		 * - Utiliser un tab de Vertex au lieu de mono objet
		 * - Utiliser un tab de Edge au lieu de mono objet
		 * - Utiliser le tabVertex pour definir les arcs
		 * 
		 * MatGraph:
		 * 	boucle pour add "vertex" & "edge"
		 */
		
//		Creation of Vertices
		vtx1 = new Vertex("1");
		vtx2 = new Vertex("2");
		vtx3 = new Vertex("3");
		vtx4 = new Vertex("4");
		vtx5 = new Vertex("5");
		vtx6 = new Vertex("6");
		vtx7 = new Vertex("7");
		vtx8 = new Vertex("8");

//		Creation of Edges
		edgA = new Edge(8, vtx1, vtx2);
		edgB = new Edge(4, vtx1, vtx3);
		edgC = new Edge(1, vtx3, vtx2);
		edgD = new Edge(5, vtx2, vtx4);
		edgE = new Edge(2, vtx3, vtx5);
		edgF = new Edge(4, vtx5, vtx4);
		edgG = new Edge(3, vtx4, vtx7);
		edgH = new Edge(9, vtx8, vtx4);
		edgI = new Edge(7, vtx4, vtx6);
		edgJ = new Edge(3, vtx5, vtx6);
		edgK = new Edge(1, vtx7, vtx8);
		edgL = new Edge(2, vtx6, vtx8);

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
//		CommonKnowledge.matGraph.adjMatDisplay();	//Debug function
		
//		Initialization of Static classes
		CommonKnowledge.pheroInit();
		Markov_Decision_Process.rewardSet(MDP_p);
	}
	
	public void AE_start() {
//		Init time counter to evaluate algo time convergence
		long timeT1 = System. nanoTime();
		
		do {
			
//			Display the Ant Colony iteration number
			CommonKnowledge.iterationCnt();
//			System.out.println(CommonKnowledge.algoIterationGet()+"st tour \n");
			
//			Instantiation of the Ant Colony algorithm
			AntCo antColony = new AntCo(vtx1, vtx8);
//		antColony.antsDisplay();					//debug function
		
//		Initiate all ant thread
			antColony.initThreads();
		
//		Wait for the end of all threads
			antColony.endThreads();
		
//		Check all ants to find the best path
			antColony.Scoring();
		
//		Apply Local Search
			//TODO
		
//		All all ants to return at their starting point
			antColony.getBack();
		
//		Display adjacency pheromones matrix
//			CommonKnowledge.pheroMatDisplay();
			
		}while (Markov_Decision_Process.MDP());
		
		long timeT2 = System. nanoTime();
		
		CommonKnowledge.convTimeSet(CommonKnowledge.timeCvter(timeT2 - timeT1, 1_000_000_000));
		
		System.out.println("Algorithme convergence time: " + CommonKnowledge.convTimeGet() + " seconds\n");

	}

}
