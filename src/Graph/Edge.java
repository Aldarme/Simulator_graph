package Graph;

import java.util.Objects;

/**
 * 
 * @author PierreROMET
 *
 *	Class Edge
 *	This Class represent a Edge of a Graph
 *	and provide all the methods to manipulate it.
 *
 */

public class Edge {

	private int length;
	private Vertex vtxIn;
	private Vertex vtxOut;
	
	/**
	 * Constructor
	 * @param int 		Length of the edge
	 * @param Vertex	Starting vertex of the edge
	 * @param Vertex	Ending vertex of the edge
	 */
	public Edge(int lgh, Vertex vtxIn, Vertex vtxOut) {
		this.length = lgh;
		this.vtxIn  = vtxIn;
		this.vtxOut = vtxOut;
		System.out.println("Edge: "+vtxIn.getName()+" to "+vtxOut.getName()+", with a cost of: "+length);
	}
	
	/**
	 * Constructor
	 * @param Edge Instantiate edge with data of an other one
	 */
	public void Edge(Edge edg) {
		this.length = edg.length;
		this.vtxIn  = edg.vtxIn;
		this.vtxOut = edg.vtxOut;
	}

	/**
	 * Set edge with data of generic data structure
	 * @param Object 
	 */
	public void setEdge(Object obj) {
		Edge edg 		= (Edge) obj;
		this.length = edg.length;
		this.vtxIn  = edg.vtxIn;
		this.vtxOut = edg.vtxOut;
	}

	/**
	 * Set length of a given edge
	 * @param integer 
	 */
	public void setLength(int lgh) {
		this.length = lgh;
	}

	/**
	 * Set starting vertex of the edge
	 * @param Vertex
	 */
	public void setvtxIn(Vertex in) {
		this.vtxIn = in;
	}

	/**
	 * Set ending vertex of the edge
	 * @param Vertex
	 */
	public void setvtxOut(Vertex out) {
		this.vtxOut = out;
	}

	/**
	 * Return the length of the edge
	 * @return integer
	 */
	public int getLgh() {
		return this.length;
	}

	/**
	 * Return input vertex of the edge
	 * @return Vertex
	 */
	public Vertex getVtxIn() {
		return this.vtxIn;
	}

	/**
	 * Return output vertex of the edge
	 * @return Vertex
	 */
	public Vertex getVtxOut() {
		return this.vtxOut;
	}

	/**
	 * Convert vertices of edge into String Object
	 * @return String
	 */
	public String toString() {
		return ""+this.vtxIn+", "+this.vtxOut+"with cost size of: "+this.length;
	}

	/**
	 * Overriding of equals function, by comparing vtxIn, vtxOut, length
	 * @return boolean Success representation
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (! (obj instanceof Vertex)) {
			return false;
		}
		Edge myEdge = (Edge) obj;
		return this.vtxIn != null
						&& this.vtxOut != null ? this.vtxIn.equals(myEdge.vtxIn)
																			&& this.vtxOut.equals(myEdge.vtxOut)
																			&& this.length == myEdge.length
							: false;
		}

	/**
	 * Overriding of hashCode function, by hashing by object reference
	 * @return boolean Success representation
	 */
	@Override
	public int hashCode() {
		String str = ""+this;
		return this != null ? Objects.hash(str) : 0;
	}

}
