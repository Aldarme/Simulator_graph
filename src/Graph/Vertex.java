package Graph;

import java.util.Objects;

/**
 * 
 * @author promet
 *	Class Vector
 *	This Class represent a Vector of a Graph
 *	and provide all the methods to manipulate it.
 */
public class Vertex {

	private String name;
	private boolean marker;

	public Vertex(String nm, boolean mk) {
		this.name 	= nm;
		this.marker = mk;
  }
	
	public Vertex(String str) {
		this.name = str;
		System.out.println("Vertex: "+str+" was created");
	}

	/**
	 * Set name of the Vertex
	 * @param String nm
	 */
	public void setName(String nm) {
		this.name = nm;
	}

	/**
	 * Set marker of the Vertex
	 * @param boolean mk
	 */
	public void setMarker(boolean mk) {
		this.marker = mk;
	}

	/**
	 * Get name of the Vertex
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get marker of the Vertex
	 * @return boolean marker
	 */
	public boolean getMarker() {
		return this.marker;
	}

	/**
	 * Overriding of equals function, by comparing String object name
	 * @return boolean
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
	 * @return boolean
	 */
	@Override
	public int hashCode() {
		return this.name != null ? Objects.hash(name) : 0;
	}

}
