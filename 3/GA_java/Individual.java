package de.vi_home.main;

public abstract class Individual {
	
	protected int fitness = -1;
	
	public Individual() {
		
	}
	
	public abstract void mutate(float odds);
	
	public int getFitness() {
		if(fitness != -1) {
			return fitness;
		}
		calculateFitness();
		return fitness;
	}
	public abstract void calculateFitness();
	
	public void resetFitness(){
		fitness = -1;
	}
	
	public abstract Individual[] crossoverWith(Individual other, float crossOdds);
	
	public abstract Individual clone();
	
	public abstract String toString();
	public abstract String agentToString();
	
	public abstract Individual createRandomIndividual();
}
