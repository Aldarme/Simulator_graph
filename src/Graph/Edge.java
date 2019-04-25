package Graph;

import java.util.Objects;

/**
 * 
 * @author promet
 *
 */

public class Edge {

	private int length;
	private Vertex vtxIn;
	private Vertex vtxOut;
	
	public Edge(int lgh, Vertex vtxIn, Vertex vtxOut) {
		this.length = lgh;
		this.vtxIn  = vtxIn;
		this.vtxOut = vtxOut;
		System.out.println("Edge: "+vtxIn.getName()+" to "+vtxOut.getName()+", with a cost of: "+length);
	}
	
	public void Edge(Edge edg) {
		this.length = edg.length;
		this.vtxIn  = edg.vtxIn;
		this.vtxOut = edg.vtxOut;
	}

	/**
	 * Set edge parameter with a given object
	 * @param Object obj
	 */
	public void setEdge(Object obj) {
		Edge edg 		= (Edge) obj;
		this.length = edg.length;
		this.vtxIn  = edg.vtxIn;
		this.vtxOut = edg.vtxOut;
	}

	/**
	 * Set length of a given edge
	 * @param int lgh
	 */
	public void setLength(int lgh) {
		this.length = lgh;
	}

	/**
	 * Set input Vertex of the edge
	 * @param Vertex in
	 */
	public void setvtxIn(Vertex in) {
		this.vtxIn = in;
	}

	/**
	 * Set output Vertex of the edge
	 * @param Vertex out
	 */
	public void setvtxOut(Vertex out) {
		this.vtxOut = out;
	}

	/**
	 * Return the length of the edge
	 * @return
	 */
	public int getLgh() {
		return this.length;
	}

	/**
	 * Return input Vertex of the edge
	 * @return Vertex
	 */
	public Vertex getVtxIn() {
		return this.vtxIn;
	}

	/**
	 * Return output Vertex of the edge
	 * @return Vertex
	 */
	public Vertex getVtxOut() {
		return this.vtxOut;
	}

	/**
	 * Convert Vertices of edge into String Object
	 * @return String
	 */
	public String toString() {
		return ""+this.vtxIn+", "+this.vtxOut+"with cost size of: "+this.length;
	}

	/**
	 * Overriding of equals function, by comparing vtxIn, vtxOut, length
	 * @return boolean
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
	 * @return boolean
	 */
	@Override
	public int hashCode() {
		String str = ""+this;
		return this != null ? Objects.hash(str) : 0;
	}

}
