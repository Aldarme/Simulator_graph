package Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import Graph.Edge;

/**
 * 
 * @author PierreROMET
 *
 *	Class MatrixGraph
 *	Data structure & method 
 *	to deploy	& manipulate an undirected graph
 *
 */

public class MatrixGraph extends Graph {

	private Vector<Vector<Edge>> adjMatrix;
	private Numerotation numtation;	

	/**
	 * Constructor
	 * Instantiate the data structure of a given size
	 * @param integer
	 */
	public MatrixGraph(int n) {
		this.adjMatrix = new Vector<Vector<Edge>>(n);
		this.numtation = new Numerotation(n);
		this.adjMatrix.setSize(n);
		System.out.println("adj. matrix created, with a size of: "+this.adjMatrix.size());
	}

	/**
	 * Return the size of the adjacency matrix
	 * @return integer
	 */
	public int size() {
		return this.adjMatrix.size();
	}

	/**
	 * Insert vertex into the adjacency matrix
	 * @param Vertex
	 */
	public void vertexAdd(Vertex vtx) {
		if(numtation.elementAdd(vtx)) {
			Vector<Edge> tmpVtx = new Vector<Edge>(size());
			tmpVtx.setSize(size());
			adjMatrix.set(numtation.getNum(vtx), tmpVtx);
		}
	}
	
	/**
	 * Get integer representation of a given vertex
	 * @param vtx
	 * @return
	 */
	public int getVtxNum(Vertex vtx) {
		return numtation.getNum(vtx);
	}

	/**
	 * Check if a given edge occur by its vertices, into the adjacency matrix
	 * @param Vertex
	 * @param Vertex
	 * @return boolean
	 */
	public boolean edgeOccur(Vertex vtxIn, Vertex vtxOut) {
		return adjMatrix.get(numtation.getNum(vtxIn)).get(numtation.getNum(vtxOut)) != null;
	}

	/**
	 * Check if a given edge occur by integer representation of vertices, into the adjacency matrix
	 * @param int i
	 * @param int j
	 * @return boolean
	 */
	public boolean edgeOccur(int i, int j) {
		return adjMatrix.get(i).get(j) != null;
	}

	/**
	 * Add new edge thank to vertices, into the adjacency matrix
	 * @param Vertex
	 * @param Vertex
	 * @param int
	 */
	public void egeAdd(Vertex vtxIn, Vertex vtxOut, int val) {
		vertexAdd(vtxIn);
		vertexAdd(vtxOut);
		adjMatrix.get(numtation.getNum(vtxIn)).set(numtation.getNum(vtxOut), new Edge(val, vtxIn, vtxOut));
	}
	
	/**
	 * Add new edge, connecting existing vertex in the adjacency matrix
	 * @param Vertex
	 * @param Vertex
	 * @param integer
	 */
	public void edgeAdd(Edge edg) {
		adjMatrix.get(numtation.getNum(edg.getVtxIn())).set(numtation.getNum(edg.getVtxOut()), edg);
	}

	/**
	 * Return the value of an edge between starting & ending vertices
	 * @param Vertex
	 * @param Vertex
	 * @return integer
	 */
	public int edgeValue(Vertex vtxIn, Vertex vtxOut) {
		return adjMatrix.get(numtation.getNum(vtxIn)).get(numtation.getNum(vtxOut)).getLgh();
	}

	/**
	 * Return the value between starting & ending vertices
	 * @param integer
	 * @param integer
	 * @return integer
	 */
	public int edgeValue(int i, int j) {
		return adjMatrix.get(i).get(j).getLgh();
	}

	/**
	 * Delete edge between starting & ending vertices
	 * @param Vertex
	 * @param Vertex
	 */
	public void edgeDelete(Vertex vtxIn, Vertex vtxOut) {
		adjMatrix.get(numtation.getNum(vtxIn)).remove(numtation.getNum(vtxOut));
	}

	/**
	 * Modify the value of the edge between starting & ending vertices
	 * @param Vertex
	 * @param Vertex
	 * @param integer
	 */
	public void numModify(Vertex vtxIn, Vertex vtxOut, int val) {
		adjMatrix.get(numtation.getNum(vtxIn)).get(numtation.getNum(vtxOut)).setLength(val);
	}

	/**
	 * Return an ArrayList of edge, representing the given vertex neighbor
	 * @param Vertex
	 * @return LinkedList<Edge>
	 */
	public ArrayList<Edge> getNeighboor(Vertex vtx) {
		ArrayList<Edge> tmpngb = new ArrayList<Edge>();
		
		for(int j=0; j < size(); j++) {
			if(edgeOccur(numtation.getNum(vtx), j)) {
				tmpngb.add(adjMatrix.get(numtation.getNum(vtx)).get(j));
			}
		}
		return tmpngb;
	}

	/**
	 * Return a collection of the all vertices
	 * @return Collection<Vertex>
	 */
	public Collection<Vertex> vertices() {
		return numtation.vertices();
	}

	/**
	 * Return the current adjacency matrix
	 * @return Vector<Vector<Edge>>
	 */
	public Vector<Vector<Edge>> replica() {
		return adjMatrix;
	}
	
	/**
	 * Debug
	 * Display into console, the adjacency matrix
	 */
	public void adjMatDisplay() {
		for(int i=0; i < size(); i++) {
			for(int j=0; j < size(); j++) {
				if(adjMatrix.get(i).get(j) != null) {
					System.out.println("vtx("+i+"), vtx("+j+") :");
					System.out.println("	vtxIn name: "+adjMatrix.get(i).get(j).getVtxIn().getName());
					System.out.println("	vtxOut name: "+adjMatrix.get(i).get(j).getVtxOut().getName());
					System.out.println("	edge cost: "+adjMatrix.get(i).get(j).getLgh());	
				}
			}
		}
		
//		System.out.println("dp: "+adjMatrix.get(numtation.getNum(edg.getVtxIn())).get(numtation.getNum(edg.getVtxOut())).getVtxIn().getName());
//		System.out.println("dp: "+adjMatrix.get(numtation.getNum(edg.getVtxIn())).get(numtation.getNum(edg.getVtxOut())).getVtxOut().getName());
//		System.out.println("dp: "+adjMatrix.get(numtation.getNum(edg.getVtxIn())).get(numtation.getNum(edg.getVtxOut())).getLgh());
//		System.out.println("dp: "+adjMatrix.get(numtation.getNum(edg.getVtxIn())).get(numtation.getNum(edg.getVtxOut())).getClass());

	}

}
