package de.vi_home.main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class GeneticAlgorithm {
	
	private int n;
	public int generation;
	private Comparator<Individual> fitnessComparator = new Comparator<Individual>(){
		@Override
		public int compare(Individual o1, Individual o2) {
			return o1.getFitness() - o2.getFitness();
		}
	};
	
	public Individual solution;
	public float[][] stats;
	private Individual dummy;
	
	public GeneticAlgorithm(int pN, Individual dummy){
		n = pN;
		this.dummy = dummy;
	}
	
	public void startSearch(int maxGenerations, int individualCount, int tournamentSize, float crossOdds, float mutationOdds){
		
		
		Individual[] individualPool = new Individual[individualCount];
		Random r = new Random();
		
		
		
		for(int i = 0; i < individualCount; i++){
			individualPool[i] = dummy.createRandomIndividual();
		}
		
		stats = new float[maxGenerations][];
		solution = null;
		
		for(int i = 0; i < maxGenerations; i++){
			stats[i] = new float[] {-1, -1};
		}
		
		for(generation = 0; generation < maxGenerations; generation++){
			Arrays.sort(individualPool, fitnessComparator);
			
			doProgress(individualPool);
			
			if(individualPool[0].getFitness() == 0){
				foundOptimum(individualPool[0]);
				solution = individualPool[0];
				break;
			}
			
			Individual[] newPool = new Individual[individualCount];
			
			for(int i = 0; i < individualCount / 2; i++){
				newPool[i] = tournamentSelect(individualPool, tournamentSize);
			}
			
			for(int i = individualCount / 2; i < individualCount; i += 2){
				Individual parent1 = newPool[r.nextInt(individualCount / 2)];
				Individual parent2 = newPool[r.nextInt(individualCount / 2)];
				
				Individual[] children = parent1.crossoverWith(parent2, crossOdds);
				newPool[i] = children[0];
				
				if(i != individualCount-1){
					newPool[i+1] = children[1];
				}
			}
			
			for(int i = 0; i < individualCount; i++){
				newPool[i].mutate(mutationOdds);
			}
			
			System.out.println("Generation done.");
			
			individualPool = newPool;
		}
		
		System.out.println("Search stopped.");
	}
	
	public Individual tournamentSelect(Individual[] pool, int tournamentSize){
		Individual[] tournament = new Individual[tournamentSize];
		Random r = new Random();
		
		for(int i = 0; i < tournamentSize; i++){
			tournament[i] = pool[r.nextInt(pool.length)];
		}
		
		Arrays.sort(tournament, fitnessComparator);
		return tournament[0];
	}
	
	public void doProgress(Individual[] pool){
		System.out.println("Current generation: "+generation);
		int bestFitness = pool[0].getFitness();
		System.out.println("Best fitness: "+bestFitness);
		System.out.println("Best solution:\n"+pool[0]+"\n\n");
		
		int totalFitness = 0;
		for(int i = 0; i < pool.length; i++){
			totalFitness += pool[i].getFitness();
		}
		float avgFitness = (float)totalFitness / pool.length;
		
		System.out.println("Average fitness: "+avgFitness);
		
		stats[generation][0] = bestFitness;
		stats[generation][1] = avgFitness;
	}
	
	public void foundOptimum(Individual solution){
		System.out.println("\n\n==========\nFound a solution.");
		System.out.println("Solution fitness: "+solution.getFitness());
		System.out.println("Solution:\n"+solution);
	}
	
}
