package zoo;

public class Amphibian extends Animal {

	public Amphibian(String name, int population, int cost) {
		super(name, population, cost);
	}

	@Override
	public int getCost() {
		return this.unitCost;
	}

}
