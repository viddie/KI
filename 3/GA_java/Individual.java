package de.vi_home.main;

public abstract class Individual {
	
	protected int[] agent;
	protected int n;
	protected int fitness = -1;
	
	public Individual(int[] agent) {
		this.agent = agent;
		n = agent.length;
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
	public String agentToString() {
		String toRet = "[ ";
		
		for(int i = 0; i < n; i++){
			toRet += agent[i]+", ";
		}
		
		toRet = toRet.substring(0, toRet.length() - 2) + " ]";
		return toRet;
	}
	
	public abstract Individual createRandomIndividual();
}
