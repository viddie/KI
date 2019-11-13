package main;

public class HouseConfiguration {
	
	Color color;
	Nation nation;
	Animal animal;
	Cigarette cig;
	Drink drink;
	
	public HouseConfiguration(Color color, Nation nation, Animal animal, Cigarette cig, Drink drink){
		this.color = color;
		this.nation = nation;
		this.animal = animal;
		this.cig = cig;
		this.drink = drink;
	}
	
	public boolean hasDuplicateValues(HouseConfiguration other){
		if(this.color == other.color
				|| this.nation == other.nation
				|| this.animal == other.animal
				|| this.cig == other.cig
				|| this.drink == other.drink){
			return true;
		}
		
		return false;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HouseConfiguration [color=" + color + ", nation=" + nation + ", animal=" + animal + ", cig=" + cig
				+ ", drink=" + drink + "]";
	}
}
