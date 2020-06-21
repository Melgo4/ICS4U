package zoo;

public abstract class Animal {

	protected String name;
	protected int population;
	protected int unitCost;

	public Animal(String name, int population, int unitCost) {
		if(name.length() <= Zoo.MAX_ITEM_NAME_SIZE) {
			this.name = name;
		} else {
			this.name = name.substring(0, Zoo.MAX_ITEM_NAME_SIZE);
		}

		this.population = population;
		this.unitCost = unitCost;
	}

	public String getName() {
		return this.name;
	}

	public int getPopulation() {
		return this.population;
	}

	public int getCost() {
		return this.unitCost * this.getPopulation();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("The daily class ").append(this.getClass().getSimpleName()).append(" cost is ")
				.append(Zoo.cents2dollarsAndCents(this.getCost())).append(" / day\n");

		String prefix = this.getPopulation() + " " + this.getName();
		sb.append(prefix).append(" ");

		String cost = Zoo.cents2dollarsAndCents(this.getCost());
		sb.append(ZooStats.space(cost, prefix)).append(cost);

		return sb.toString();
	}
}

