package Simulator;

import Graph.*;
import Ant_Colony.*;
import Shortest_Path.*;

public class main {

	public static void main(String[] args) {
		
		//Creation of Vertices
		Vertex vtx1 = new Vertex ("1");
		Vertex vtx2 = new Vertex ("2");
		Vertex vtx3 = new Vertex ("3");
		Vertex vtx4 = new Vertex ("4");
		Vertex vtx5 = new Vertex ("5");
		Vertex vtx6 = new Vertex ("6");
		Vertex vtx7 = new Vertex ("7");
		Vertex vtx8 = new Vertex ("8");
		
		//Creation of Edges
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
		
		//Creation of the adjacency matrix, but it's empty
		MatrixGraph adjMtx = new MatrixGraph(8);
		adjMtx.vertexAdd(vtx1);
		adjMtx.vertexAdd(vtx2);
		adjMtx.vertexAdd(vtx3);
		adjMtx.vertexAdd(vtx4);
		adjMtx.vertexAdd(vtx5);
		adjMtx.vertexAdd(vtx6);
		adjMtx.vertexAdd(vtx7);
		adjMtx.vertexAdd(vtx8);
		
		//Insertion of the edges
		adjMtx.edgeAdd(edgA);
		adjMtx.edgeAdd(edgB);
		adjMtx.edgeAdd(edgC);
		adjMtx.edgeAdd(edgD);
		adjMtx.edgeAdd(edgE);
		adjMtx.edgeAdd(edgF);
		adjMtx.edgeAdd(edgG);
		adjMtx.edgeAdd(edgH);
		adjMtx.edgeAdd(edgI);
		adjMtx.edgeAdd(edgJ);
		adjMtx.edgeAdd(edgK);
		adjMtx.edgeAdd(edgL);
		
		//Display adjacency matrix to control it
		adjMtx.adjMatDisplay();
		
		
		
		
	}

}
