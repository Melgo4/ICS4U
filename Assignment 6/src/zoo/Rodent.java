package zoo;

public class Rodent extends Mammal {

	public Rodent(String name, int population, int feedCost, int habitatCost) {
		super(name, population, feedCost + habitatCost);
	}

}
