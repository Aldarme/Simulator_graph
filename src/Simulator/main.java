package Simulator;

import java.io.File;
import java.io.FileWriter;
import View.PheromoneMatrix;


import Ant_Colony.*;
import Decision_Making.MarkovianInfluence;


/**
 * 
 * @author PierreROMET
 * @version 0.1.0
 * 
 * 	Class main 
 * 	This class represent the algorithm engine of "Ant Colony"
 *
 */
public class main {
	
	private static int localMinDeepSearch = 10;
	private static AntEngine antEngine = new AntEngine(localMinDeepSearch);
	
	/**
	 * Main function
	 */
	public static void main(String[] args) {
		
		/*************************************************************
		 * 
		 * Code lines to test Ant_Co & its dynamic adaptation
		 * 
		 *************************************************************/
		antEngine.AE_start();
		CommonKnowledge.display_shortestPath();

		MarkovianInfluence.ApplyMkInfluence(3);
		
		antEngine.AE_start();
//		CommonKnowledge.display_shortestPath();
		
	}
	
	/**
	 * Write algo logs into a file
	 * @param /TODO
	 */
	public static void iterate(/*TODO*/) {

//		try{
//			
//			File myFile = new File("D:\\Workspace\\Simulator_graph\\logs\\Average_time_for_convergence.txt");
//			if(myFile.isFile())
//			{ 
//				FileWriter ffw = new FileWriter(myFile, true);
//				ffw.write("******************************************");
//				ffw.write("\r\n");
//				ffw.write("\r\n");
//				ffw.write("Average time for "+ nbrIteration.get(iteration_p) + " Iteration.");
//				ffw.write("\r\n");
//				ffw.write("The time for algorithm convergence is: "+ timeAdder/(double)nbrIteration.get(iteration_p) + " seconds");
//				ffw.write("\r\n");
//				ffw.write("\r\n");
//				ffw.write("******************************************");
//				ffw.write("\r\n");
//				ffw.close();
//			}
//			else {
//				myFile.createNewFile();
//				FileWriter ffw = new FileWriter(myFile);
//				ffw.write("******************************************");
//				ffw.write("\r\n");
//				ffw.write("\r\n");
//				ffw.write("Average time for "+ nbrIteration.get(iteration_p) + " Iteration.");
//				ffw.write("\r\n");
//				ffw.write("The time for algorithm convergence is: "+ timeAdder/(double)nbrIteration.get(iteration_p) + " seconds");
//				ffw.write("\r\n");
//				ffw.write("\r\n");
//				ffw.write("******************************************");
//				ffw.write("\r\n");
//				ffw.close();
//			}			
//			
//			} catch (Exception e) {}		
	}

}
