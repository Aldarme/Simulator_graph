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
	//the init. value of reward parameter, conditions the deep search of local minimum algo.  
	private static int reward;
	
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
	
	public static void rewardSet(int reward_p) {
		reward = reward_p;
	}
}
