package Graph;

import java.util.Vector;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;

import Graph.Vertex;

/**
 * 
 * @author promet
 *
 */

public class Numerotation {

	private int counter;
	private Hashtable<Vertex, Integer> HSI;	//Store an ordered Vector list
	private Vector<Vertex> VS;							//list of the all Vertex

	public Numerotation(int n) {
		this.counter = -1;
		this.HSI = new Hashtable<Vertex, Integer>(n);
		VS = new Vector<Vertex>(n);
		VS.setSize(n);
	}

	/**
	 * Return the size of the list of Vertex
	 * @return int size
	 */
	public int taille() {
		return VS.size();
	}

	/**
	 * Add a given Vertex into Ordered Vector list & list of Vertex
	 * @param Vertex vtx
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
	 * Return the position of a given Vertex
	 * @param Vertex vtx
	 * @return int position
	 */
	public int getNum(Vertex vtx) {
//		System.out.println("HSI position: "+HSI.get(vtx));
		return HSI.get(vtx);		
	}

	/**
	 * Return a Vertex at the given adresse
	 * @param int adresse
	 * @return
	 */
	public Vertex getElmtAt(int i) {
		return VS.elementAt(i);
	}

	/**
	 Return a collection of the all Vertices
	 * @return Vector<Vertex>
	 */
	public Vector<Vertex> vertices() {
		return VS;
	}

}
