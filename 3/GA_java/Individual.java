package de.vi_home.main;

import java.util.Random;

public class Individual {
	
	private int[] agent;
	private int n;
	private int fitness = -1;
	
	public Individual(int[] pAgent){
		agent = pAgent;
		n = agent.length;
	}
	
	public void mutate(float odds){
		Random r = new Random();
		
		for(int i = 0; i < n; i++){
			if(r.nextFloat() > odds){ //Dont mutate if odds arent met
				continue;
			}
			
			int value = r.nextInt(n);
			
			agent[i] = value;
		}
	}
	
	public int calculateFitness(){
		if(fitness != -1){
			return fitness;
		}
		
		int overlaps = 0;
		
		for(int col = 0; col < n-1; col++){
			int valueAt = agent[col];
			for(int searchCol = col+1; searchCol < n; searchCol++){
				int compareAgainst = agent[searchCol];
				if(valueAt == compareAgainst){
					overlaps++;
					continue;
				}
				
				int diff = searchCol - col;
				if(valueAt + diff == compareAgainst || valueAt - diff == compareAgainst){
					overlaps++;
					continue;
				}
			}
		}
		
		fitness = overlaps;
		return overlaps;
	}
	
	public void resetFitnessCalculation(){
		fitness = -1;
	}
	
	public Individual[] crossoverWith(Individual other, float crossOdds){
		Random r = new Random();
		
		Individual thisChild = this.clone();
		Individual otherChild = other.clone();
		Individual[] children = new Individual[] {thisChild, otherChild};
		
		if(r.nextFloat() > crossOdds){
			return children;
		}
	
		
		int cutAt = r.nextInt(n-1) + 1;
		for(int col = cutAt; col < n; col++){
			int temp = thisChild.agent[col];
			thisChild.agent[col] = otherChild.agent[col];
			otherChild.agent[col] = temp;
		}
		
		return children;
	}
	
	public Individual clone(){
		int[] agentCopy = new int[n];
		for(int i = 0; i < n; i++){
			agentCopy[i] = agent[i];
		}
		return new Individual(agentCopy);
	}
	
	@Override
	public String toString(){
		String[] lines = new String[n];
		
		for(int i = 0; i < n; i++){
			lines[i] = "";
		}
		
		for(int col = 0; col < n; col++){
			for(int row = 0; row < n; row++){
				if(agent[col] == row){
					lines[row] += "X ";
				} else {
					lines[row] += "O ";
				}
			}
		}
		
		String toRet = "";
		for(int i = 0; i < n; i++){
			toRet += lines[i] + "\n";
		}
		
		return toRet;
	}
	
	public String agentToString(){
		String toRet = "[ ";
		
		for(int i = 0; i < n; i++){
			toRet += agent[i]+", ";
		}
		
		toRet = toRet.substring(0, toRet.length() - 2) + " ]";
		return toRet;
	}
	
	
	public static Individual createRandomIndividual(int n){
		int[] nums = new int[n];
		Random r = new Random();
		for(int i = 0; i < n; i++){
			nums[i] = r.nextInt(n);
		}
		
		return new Individual(nums);
	}
}
