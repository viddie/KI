package de.vi_home.main;

public class Starter {

	public static void main(String[] args) {
		startSudoku();
	}
	
	public static void startSudoku(){
		int n = 9;
		int maxGenerations = 100000;
		int individualCount = 100;
		int tournamentSize = 5;
		float mutationOdds = 0.01f;
		float crossOdds = 0.6f;
		
		SudokuIndividual dummy = new SudokuIndividual(new int[n][n]);
		
		testIndividual(dummy);

		GeneticAlgorithm ga = new GeneticAlgorithm(dummy);
		SudokuIndividual si = (SudokuIndividual)ga.startSearch(maxGenerations, individualCount, tournamentSize, crossOdds, mutationOdds);
		System.out.println("Best solution:\n"+si);
	}
	
	public static void startNQueens(){
		int n = 8;
		int maxGenerations = 10000;
		int individualCount = 100;
		int tournamentSize = 2;
		float mutationOdds = 0.045f;
		float crossOdds = 0.6f;
		
		NQueensIndividual dummy = new NQueensIndividual(new int[n]);

		GeneticAlgorithm ga = new GeneticAlgorithm(dummy);
		ga.startSearch(maxGenerations, individualCount, tournamentSize, crossOdds, mutationOdds);
	}
	
	public static void performMultipleRuns(int repeatAlgorithm, Individual dummy) {
		int n = 8;
		int maxGenerations = 10000;
		int individualCount = 100;
		int tournamentSize = 2;
		float mutationOdds = 0.045f;
		float crossOdds = 0.6f;
		
		GeneticAlgorithm ga = new GeneticAlgorithm(dummy);
		
		
		System.out.println("Testing genetic algorithm with settings:");
		System.out.println("\tn = "+n);
		System.out.println("\tmaxGenerations = "+maxGenerations);
		System.out.println("\tindividualCount = "+individualCount);
		System.out.println("\ttournamentSize = "+tournamentSize);
		System.out.println("\tmutationOdds = "+mutationOdds);
		System.out.println("\tcrossOdds = "+crossOdds);
		System.out.println("\trepeatAlgorithm = "+repeatAlgorithm);
		
		
		float avgGenerationsToSolution = 0.0f;
		int genToSolutionCount = 0;
		
		float avgGenerationsToClose = 0.0f;
		int genToCloseCount = 0;
		
		float avgGenerationsToMedium = 0.0f;
		int genToMediumCount = 0;
		
		for(int i = 0; i < repeatAlgorithm; i++){
			ga.startSearch(maxGenerations, individualCount, tournamentSize, crossOdds, mutationOdds);
			System.out.println("Stopped after '"+ga.generation+"' generations.");
			boolean foundSolution = ga.solution != null;
			System.out.println("Found solution? -> "+(foundSolution ? "Yes" : "No"));
			
			float[][] stats = ga.stats;
			boolean statCloseFound = false;
			boolean statMediumFound = false;
			
			
			for(int generation = 0; generation < stats.length; generation++){
				if(stats[generation][0] == 0){
					avgGenerationsToSolution += generation;
					genToSolutionCount++;
					break;
					
				} else if(stats[generation][0] <= 1 && !statCloseFound){
					statCloseFound = true;
					
					avgGenerationsToClose += generation;
					genToCloseCount++;
					continue;
					
				} else if(stats[generation][0] <= 2 && !statMediumFound){
					statMediumFound = true;
					
					avgGenerationsToMedium += generation;
					genToMediumCount++;
					continue;
				}
			}
			
			if(!foundSolution){
				avgGenerationsToSolution += ga.generation;
				genToSolutionCount++;
			}
		}
		
		avgGenerationsToSolution /= genToSolutionCount;
		avgGenerationsToClose /= genToCloseCount;
		avgGenerationsToMedium /= genToMediumCount;
		
		System.out.println("Average count of generations until solution was found: "+avgGenerationsToSolution);
		System.out.println("Average count of generations until at or below 1 fitness was found: "+avgGenerationsToClose);
		System.out.println("Average count of generations until at or below 2 fitness was found: "+avgGenerationsToMedium);
		
	}
	
	public static void fullPrint(Individual a){
		a.resetFitness();
//		System.out.println("\n\nAgent: "+a.agentToString()+"\n");
		System.out.println(a);
		System.out.println("Fitness: "+a.getFitness());
	}
	
	public static void testIndividual(Individual dummy){
		Individual a = dummy.createRandomIndividual();
		fullPrint(a);
		System.out.println("\n\nNow mutating...");
		a.mutate(0.01f);
		fullPrint(a);
	}
	
}
