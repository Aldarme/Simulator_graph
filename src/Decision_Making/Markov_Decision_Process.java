package Decision_Making;

import java.awt.Stroke;

import Ant_Colony.CommonKnowledge;

/**
 * 
 * @author PierreROMET
 * 
 * Class Markov_Decision_Process
 * The MDP (Markov decision process) is a
 * Passive reinforcement learning.
 *
 */

public class Markov_Decision_Process {
	
	private static int storedBestPath = Integer.MAX_VALUE;
	private static int reward 				= 1;
	
	public static boolean MDP() {
		
		if(reward == 0) {
			return false;
		}
		
		if(CommonKnowledge.optLgthGet() < storedBestPath ) {
			reward++;
			storedBestPath = CommonKnowledge.optLgthGet();
			return true;
		}
		
		if(CommonKnowledge.optLgthGet() == storedBestPath) {
			reward--;
			storedBestPath = CommonKnowledge.optLgthGet();
			return true;
		}
		
		return true;
	}
}
