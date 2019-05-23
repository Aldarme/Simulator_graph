package Graph;

import java.util.Collection;

/**
 * 
 * @author PierreROMET
 * 
 * 	Class Graph
 *	Abstract interface for the creation of a graph
 *
 */

public abstract class Graph {

	/**
	 * Abstract function
	 * Return the size of the graph
	 * @return int size
	 */
	public abstract int size();

	/**
	 * Abstract function
	 * Return a copy of the graph
	 * @return Graph
	 */
//	public abstract Graph replica();

	/**
	 * Abstract function
	 * Add Vertex into the Graph
	 * @param Vertex vtx
	 */
	public abstract void vertexAdd(Vertex vtx);

	/**
	 * Abstract function
	 * Add edge into the Graph
	 * @param Vertex vtxIn
	 * @param Vertex vtxOut
	 * @param int val
	 */
	public abstract void edgeAdd(Edge edg);

	/**
	 * Abstract function
	 * Check if edge occur into the graph
	 * @param Vertex vtxIn
	 * @param Vertex vtxOut
	 * @return boolean
	 */
	public abstract boolean edgeOccur(Vertex vtxIn, Vertex vtxOut);

	/**
	 * Abstract function
	 * Return the value of a given edge between Vertex vtxIn & vtxOut
	 * @param vtxIn
	 * @param vtxOut
	 * @return integer
	 */
	public abstract int edgeValue(Vertex vtxIn, Vertex vtxOut);

	/**
	 * Abstract function
	 * Delete edge between Vertex vtxIn & vtxOut
	 * @param vtxIn
	 * @param vtxOut
	 */
	public abstract void edgeDelete(Vertex vtxIn, Vertex vtxOut);

	/**
	 * Abstract function
	 * Return all vertices as a collection
	 * @return Collection<Vertex>
	 */
	public abstract Collection<Vertex> vertices();

}
