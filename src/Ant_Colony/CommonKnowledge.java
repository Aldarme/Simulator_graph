package Ant_Colony;

import java.util.ArrayList;

/**
 * 
 * @author promet
 *
 */

public class CommonKnowledge {

	private int   nbrOfCities;
	private float maxMarker;
	private float minMarker;
	private float evaporation;
	private int   optimalLght;
	private ArrayList<ArrayList<Float>> pheromones;
	private ArrayList<ArrayList<Integer>> distances;
	
	public CommonKnowledge(float minMk, float maxMk, float evap, int ArraySize) {
	
		this.nbrOfCities 	= ArraySize;
		this.maxMarker		= maxMk;
		this.minMarker		=	minMk;
		this.evaporation	=	evap;
		this.pheromones 	= new ArrayList<ArrayList<Float>>(ArraySize);
		this.distances 		= new ArrayList<ArrayList<Integer>>(ArraySize);
	}
	
	public void nbrCtSet (int nbr) {
		this.nbrOfCities = nbr;
	}
	
	public void maxMkSet (float maxMk) {
		this.maxMarker = maxMk;
	}
	
	public void minMkSet (float minMk) {
		this.minMarker = minMk;
	}
	
	public void evapSet (float evap) {
		this.evaporation = evap;
	}
	
	public void optLgthSet (int optl) {
		this.optimalLght = optl;
	}
	
	public int nbrCtGet () {
		return this.nbrOfCities;
	}
	
	public float maxMkGet () {
		return this.maxMarker;
	}
	
	public float minMkGet () {
		return this.minMarker;
	}
	
	public float evapGet () {
		return this.evaporation;
	}
	
	public int optLgthGet () {
		return this.optimalLght;
	}
	
	public int CkSize() {
		return pheromones.size();
	}
	
	public void setPheromone(int vtxIn, int vtxOut, float value) {
		pheromones.get(vtxIn).set(vtxOut, value);
	}
	
	public void evaporate() {
		for(int i=0; i < nbrOfCities ; i++) {
			for(int j=0; j < nbrOfCities ; j++) {
				pheromones.get(i).set(j, pheromones.get(i).get(j) * (100-evaporation) /100 );
				if (pheromones.get(i).get(j) < minMarker) {
					pheromones.get(i).set(j, minMarker);
				}
				else if (pheromones.get(i).get(j) > maxMarker) {
					pheromones.get(i).set(j, maxMarker);
				}
				pheromones.get(j).set(i, pheromones.get(i).get(j));
			}
		}
	}
	
}
