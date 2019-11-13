package main;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.BacktrackingStrategy;
import aima.core.search.csp.CSP;
import aima.core.search.csp.CSPStateListener;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Domain;
import aima.core.search.csp.MinConflictsStrategy;
import aima.core.search.csp.Variable;

public class Starter {
	
	public static void main(String[] args) {
		List<Variable> vars = new ArrayList<>();
		vars.add(new Variable("House 1"));
		vars.add(new Variable("House 2"));
		vars.add(new Variable("House 3"));
		vars.add(new Variable("House 4"));
		vars.add(new Variable("House 5"));
		
		CSP csp = new CSP(vars);
		
		List<HouseConfiguration> domain = new ArrayList<>();
		
		for(Color color : Color.values()){
			for(Nation nation : Nation.values()){
				for(Animal animal : Animal.values()){
					for(Cigarette cig : Cigarette.values()){
						for(Drink drink : Drink.values()){
							domain.add(new HouseConfiguration(color, nation, animal, cig, drink));
						}
					}
				}
			}
		}
		
		csp.setDomain(vars.get(0), new Domain(domain));
		csp.setDomain(vars.get(1), new Domain(domain));
		csp.setDomain(vars.get(2), new Domain(domain));
		csp.setDomain(vars.get(3), new Domain(domain));
		csp.setDomain(vars.get(4), new Domain(domain));
		
		
		//No duplicate values
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				for(int i = 0; i < vars.size(); i++){
					for(int j = i+1; j < vars.size(); j++){
						HouseConfiguration hc1 = (HouseConfiguration) as.getAssignment(vars.get(i));
						HouseConfiguration hc2 = (HouseConfiguration) as.getAssignment(vars.get(j));
						
						if(hc1.hasDuplicateValues(hc2)){
							return false;
						}
					}
				}
				return true;
			}
		});
		
		
		//England & Red
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				for(Variable var : getScope()){
					HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
					if(hc.nation != Nation.England){
						continue;
					}
					
					if(hc.color == Color.Red){
						return true;
					}
				}
				return false;
			}
		});
		
		
		//Spain & Dog
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				for(Variable var : getScope()){
					HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
					if(hc.nation != Nation.Spain){
						continue;
					}

					if(hc.animal == Animal.Dog){
						return true;
					}
				}
				return false;
			}
		});
		
		
		//var0 -> Norway
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				List<Variable> scope = new ArrayList<>();
				scope.add(vars.get(0));
				return scope;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				HouseConfiguration hc = (HouseConfiguration) as.getAssignment(vars.get(0));
				return hc.nation == Nation.Norway;
			}
		});
		
		
		
		//Norway & Kools
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				for(Variable var : getScope()){
					HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
					if(hc.color != Color.Yellow){
						continue;
					}

					if(hc.cig == Cigarette.Kools){
						return true;
					}
				}
				return false;
			}
		});
		
		
		//Chesterfields next to fish
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				int indexChesterfields = -1;
				int indexFish = -1;
				for(Variable var : getScope()){
					HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
					if(hc.cig == Cigarette.Chesterfields){
						indexChesterfields = vars.indexOf(var);
					}
					if(hc.animal == Animal.Fish){
						indexFish = vars.indexOf(var);
					}
				}
				
				int distance = Math.abs(indexChesterfields - indexFish);
				
				return distance == 1;
			}
		});

		
		//Norway next to blue
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				int indexNorway = -1;
				int indexBlue = -1;
				for(Variable var : getScope()){
					HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
					if(hc.nation == Nation.Norway){
						indexNorway = vars.indexOf(var);
					}
					if(hc.color == Color.Blue){
						indexBlue = vars.indexOf(var);
					}
				}
				
				int distance = Math.abs(indexNorway - indexBlue);
				
				return distance == 1;
			}
		});
		
		
		
		//Winstons & Snail
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				for(Variable var : getScope()){
					HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
					if(hc.cig != Cigarette.Winston){
						continue;
					}

					if(hc.animal == Animal.Snail){
						return true;
					}
				}
				return false;
			}
		});
		
		
		
		//Lucky Strike & Orange juice
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				for(Variable var : getScope()){
					HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
					if(hc.cig != Cigarette.LuckyStrike){
						continue;
					}

					if(hc.drink == Drink.OrangeJuice){
						return true;
					}
				}
				return false;
			}
		});
		
		
		
		//Ukraine & Tea
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				for(Variable var : getScope()){
					HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
					if(hc.nation != Nation.Ukraine){
						continue;
					}

					if(hc.drink == Drink.Tea){
						return true;
					}
				}
				return false;
			}
		});

		
		
		
		//Japan & Parliament
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				for(Variable var : getScope()){
					HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
					if(hc.nation != Nation.Japan){
						continue;
					}

					if(hc.cig == Cigarette.Parliaments){
						return true;
					}
				}
				return false;
			}
		});

		
		
		
		//Horse next to Kools
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				int indexHorse = -1;
				int indexKools = -1;
				for(Variable var : getScope()){
					HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
					if(hc.animal == Animal.Horse){
						indexHorse = vars.indexOf(var);
					}
					if(hc.cig == Cigarette.Kools){
						indexKools = vars.indexOf(var);
					}
				}
				
				int distance = Math.abs(indexHorse - indexKools);
				
				return distance == 1;
			}
		});

		
		
		
		//Green & coffee
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				for(Variable var : getScope()){
					HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
					if(hc.color != Color.Green){
						continue;
					}

					if(hc.drink == Drink.Coffee){
						return true;
					}
				}
				return false;
			}
		});

		
		
		
		//Green to the right of elfenbeinfarben
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				return vars;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				int indexGreen = -1;
				int indexElfen = -1;
				for(Variable var : getScope()){
					HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
					if(hc.color == Color.Green){
						indexGreen = vars.indexOf(var);
					}
					if(hc.color == Color.Elfenbeinfarben){
						indexElfen = vars.indexOf(var);
					}
				}
				
				
				return indexElfen+1 == indexGreen;
			}
		});
		

		
		
		//var2 -> Milk
		csp.addConstraint(new Constraint(){
			@Override
			public List<Variable> getScope() {
				List<Variable> scope = new ArrayList<>();
				scope.add(vars.get(2));
				return scope;
			}

			@Override
			public boolean isSatisfiedWith(Assignment as) {
				HouseConfiguration hc = (HouseConfiguration) as.getAssignment(vars.get(2));
				return hc.drink == Drink.Milk;
			}
		});
		
		
		
		MinConflictsStrategy mcs = new MinConflictsStrategy(Integer.MAX_VALUE);
		mcs.addCSPStateListener(new CSPStateListener() {
			
			int at = 0;
			
			@Override
			public void stateChanged(Assignment arg0, CSP arg1) {
				if(at % 1000 == 0){
					System.out.println("At try: "+at);
				}
				at++;
			}
			
			@Override
			public void stateChanged(CSP arg0) {
				
			}
		});
		
		Assignment as = mcs.solve(csp);
		
		
		
		
		
		System.out.println("Finished:");
		int i = 0;
		for(Variable var : vars){
			HouseConfiguration hc = (HouseConfiguration) as.getAssignment(var);
			System.out.println("House "+(++i)+": "+hc);
		}
	}
}
