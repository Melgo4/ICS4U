package zoo;

public class Snake extends Reptile {

	protected int habitatCost;

	public Snake(String name, int population, int feedCost, int habitatCost) {
		super(name, population, feedCost);
		this.habitatCost = habitatCost;
	}

	@Override
	public int getCost() {
		return super.getCost() + this.habitatCost;
	}

}
