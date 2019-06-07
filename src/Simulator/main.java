package Simulator;

import Graph.*;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import Ant_Colony.*;
import Decision_Making.Markov_Decision_Process;
//import Shortest_Path.*;

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
	
	//Variable to evaluate the time convergence of the algorithm
	private static double timeAdder			= 0;
	private static ArrayList<Integer> nbrIteration = new ArrayList<Integer>() {{
		add(1);
		add(1000);
		add(5000);
		add(10000);
		add(30000);
		add(50000);
		add(75000);
		add(95000);
		add(100000);
		add(100000000);
	}};
	
	/**
	 * Main function
	 */
	public static void main(String[] args) {
		
		iterate(9);		
	}
	
	/**
	 * Calculate the average time convergence of the algorithm,
	 * the number of iteration is set by the parameter 
	 * @param int
	 */
	public static void iterate(int iteration_p) {
		
		timeAdder = 0;
		for (int i = 0; i <= nbrIteration.get(iteration_p); i++) {
			System.out.println("iteration nbr: "+i+"\n");
			antEngine.AE_start();
			CommonKnowledge.pheroInit();
			timeAdder += CommonKnowledge.convTimeGet();
		}		
		System.out.println("Average time for algorithm convergence is: "+ timeAdder/(double)nbrIteration.get(iteration_p));
		
		try{
			
			File myFile = new File("D:\\Workspace\\Simulator_graph\\logs\\Average_time_for_convergence.txt");
			if(myFile.isFile())
			{ 
				FileWriter ffw = new FileWriter(myFile, true);
				ffw.write("******************************************");
				ffw.write("\r\n");
				ffw.write("\r\n");
				ffw.write("Average time for "+ nbrIteration.get(iteration_p) + " Iteration.");
				ffw.write("\r\n");
				ffw.write("The time for algorithm convergence is: "+ timeAdder/(double)nbrIteration.get(iteration_p) + " seconds");
				ffw.write("\r\n");
				ffw.write("\r\n");
				ffw.write("******************************************");
				ffw.write("\r\n");
				ffw.close();
			}
			else {
				myFile.createNewFile();
				FileWriter ffw = new FileWriter(myFile);
				ffw.write("******************************************");
				ffw.write("\r\n");
				ffw.write("\r\n");
				ffw.write("Average time for "+ nbrIteration.get(iteration_p) + " Iteration.");
				ffw.write("\r\n");
				ffw.write("The time for algorithm convergence is: "+ timeAdder/(double)nbrIteration.get(iteration_p) + " seconds");
				ffw.write("\r\n");
				ffw.write("\r\n");
				ffw.write("******************************************");
				ffw.write("\r\n");
				ffw.close();
			}			
			
			} catch (Exception e) {}		
	}

}
