package zoo;

public class Invertebrate extends Animal {

	public Invertebrate(String name, int population, int cost) {
		super(name, population, cost);
	}

	@Override
	public int getCost() {
		return this.unitCost;
	}

}
