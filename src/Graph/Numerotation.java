package Graph;

import java.util.Vector;

import java.util.Hashtable;
import Graph.Vertex;

/**
 * 
 * @author PierreROMET
 *
 *	Class Numerotation
 *	Provide a data structure  & method to identify
 *  each vertex into the graph, as a number.
 *  
 */

public class Numerotation {

	private int counter;
	private Hashtable<Vertex, Integer> HSI;	//Store an ordered vector list
	private Vector<Vertex> VS;							//list of the all vertex

	/**
	 * Constructor
	 * Instantiate the data structure of a given size
	 * @param integer
	 */
	public Numerotation(int n) {
		this.counter = -1;
		this.HSI = new Hashtable<Vertex, Integer>(n);
		VS = new Vector<Vertex>(n);
		VS.setSize(n);
	}

	/**
	 * Return the size of the list of vertex
	 * @return integer
	 */
	public int taille() {
		return VS.size();
	}

	/**
	 * Add a given vertex into Ordered vector list & list of vertex
	 * @param Vertex
	 * @return boolean
	 */
	public boolean elementAdd(Vertex vtx) {
		if (!HSI.containsKey(vtx)){
			counter++;
			HSI.put(vtx, counter);
			VS.set(counter, vtx);
			return true;
		}
		return false;
	}

	/**
	 * Return the position of a given vertex
	 * @param Vertex
	 * @return integer
	 */
	public int getNum(Vertex vtx) {
//		System.out.println("HSI position: "+HSI.get(vtx));
		return HSI.get(vtx);		
	}

	/**
	 * Return a vertex at the given address
	 * @param integer
	 * @return
	 */
	public Vertex getElmtAt(int i) {
		return VS.elementAt(i);
	}

	/**
	 Return a collection of the all vertices
	 * @return Vector<Vertex>
	 */
	public Vector<Vertex> vertices() {
		return VS;
	}

}
