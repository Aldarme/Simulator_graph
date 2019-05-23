package Graph;

import java.util.Objects;

/**
 * 
 * @author PierreROMET
 * 
 *	Class Vector
 *	This Class represent a vector of a graph
 *	and provide all the methods to manipulate it.
 *
 */
public class Vertex {

	private String name;
	private boolean marker;

	/**
	 * Constructor
	 * @param String  vertex name
	 * @param boolean	vertex Marking
	 */
	public Vertex(String nm, boolean mk) {
		this.name 	= nm;
		this.marker = mk;
  }
	
	/**
	 * Constructor
	 * @param String vertex name
	 */
	public Vertex(String str) {
		this.name = str;
		System.out.println("Vertex: "+str+" was created");
	}

	/**
	 * Set the name of the vertex
	 * @param String
	 */
	public void setName(String nm) {
		this.name = nm;
	}

	/**
	 * Set the marker of the vertex
	 * @param boolean
	 */
	public void setMarker(boolean mk) {
		this.marker = mk;
	}

	/**
	 * Get name of the vertex
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get marker of the vertex
	 * @return boolean
	 */
	public boolean getMarker() {
		return this.marker;
	}

	/**
	 * Overriding of equals function, by comparing String object name
	 * @return boolean Success representation
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if ( ! (obj instanceof Vertex)) {
			return false;
		}
		Vertex other = (Vertex)obj;
		return this.name != null ? this.name.equals(other.name) : this.name == other.name;
	}

	/**
	 * Overriding of hashCode function, by hashing by object name
	 * @return boolean Success representation
	 */
	@Override
	public int hashCode() {
		return this.name != null ? Objects.hash(name) : 0;
	}

}
