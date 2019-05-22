package Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import Graph.Edge;

/**
 * 
 * @author promet
 *
 */

public class MatrixGraph extends Graph {

	private Vector<Vector<Edge>> adjMatrix;
	private Numerotation numtation;	

	public MatrixGraph(int n) {
		this.adjMatrix = new Vector<Vector<Edge>>(n);
		this.numtation = new Numerotation(n);
		this.adjMatrix.setSize(n);
		System.out.println("adj. matrix created, with a size of: "+this.adjMatrix.size());
	}

	/**
	 * Return the size of the adjacency matrix
	 * @return int size
	 */
	public int size() {
		return this.adjMatrix.size();
	}

	/**
	 * Insert Vertex into the adjacency matrix
	 * @param Vertex
	 */
	public void vertexAdd(Vertex vtx) {
		if(numtation.elementAdd(vtx)) {
			Vector<Edge> tmpVtx = new Vector<Edge>(size());
			tmpVtx.setSize(size());
			adjMatrix.set(numtation.getNum(vtx), tmpVtx);
		}
	}
	
	public int getVtxNum(Vertex vtx) {
		return numtation.getNum(vtx);
	}

	/**
	 * Check if edge occur in the adjacency matrix
	 * @param Vertex In
	 * @param Vertex Out
	 * @return boolean
	 */
	public boolean edgeOccur(Vertex vtxIn, Vertex vtxOut) {
		return adjMatrix.get(numtation.getNum(vtxIn)).get(numtation.getNum(vtxOut)) != null;
	}

	/**
	 * Check if edge occur in the adjacency matrix
	 * @param int i
	 * @param int j
	 * @return boolean
	 */
	public boolean edgeOccur(int i, int j) {
		return adjMatrix.get(i).get(j) != null;
	}

	/**
	 * Add new edge, adding new Vertex in the adjacency matrix
	 * @param Vertex In
	 * @param Vertex Out
	 * @param int value
	 */
	public void egeAdd(Vertex vtxIn, Vertex vtxOut, int val) {
		vertexAdd(vtxIn);
		vertexAdd(vtxOut);
		adjMatrix.get(numtation.getNum(vtxIn)).set(numtation.getNum(vtxOut), new Edge(val, vtxIn, vtxOut));
	}
	
	/**
	 * Add new edge, connecting existing vertex in the adjacency matrix
	 * @param Vertex In
	 * @param Vertex Out
	 * @param int value
	 */
	public void edgeAdd(Edge edg) {
		adjMatrix.get(numtation.getNum(edg.getVtxIn())).set(numtation.getNum(edg.getVtxOut()), edg);
	}

	/**
	 * Return the value of an edge between vtxIn & vtxOut
	 * @param Vertex In
	 * @param Vertex Out
	 * @return int value
	 */
	public int edgeValue(Vertex vtxIn, Vertex vtxOut) {
		return adjMatrix.get(numtation.getNum(vtxIn)).get(numtation.getNum(vtxOut)).getLgh();
	}

	/**
	 * Return the value between i & j
	 * @param int i
	 * @param int j
	 * @return int value
	 */
	public int edgeValue(int i, int j) {
		return adjMatrix.get(i).get(j).getLgh();
	}

	/**
	 * Delete edge between vtxIn & vtxOut
	 * @param Vertex In
	 * @param Vertex Out
	 */
	public void edgeDelete(Vertex vtxIn, Vertex vtxOut) {
		adjMatrix.get(numtation.getNum(vtxIn)).remove(numtation.getNum(vtxOut));
	}

	/**
	 * Modify the value of the edge between vtxIn & VtxOut
	 * @param Vertex In
	 * @param Vertex Out
	 * @param int
	 */
	public void numModify(Vertex vtxIn, Vertex vtxOut, int val) {
		adjMatrix.get(numtation.getNum(vtxIn)).get(numtation.getNum(vtxOut)).setLength(val);
	}

	/**
	 * Return an ArrayList of Edge, representing the given vertex neighbor
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
	 * Return a collection of the all Vertices
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
