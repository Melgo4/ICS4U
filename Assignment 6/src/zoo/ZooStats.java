package zoo;

import java.util.ArrayList;
import java.util.List;

public class ZooStats {

	private List<Animal> items = new ArrayList<>();

	public void enterItem(Animal item) {
		this.items.add(item);
	}

	public int numberOfItems() {
		return this.items.size();
	}

	public int totalCost() {
		return this.items.stream().mapToInt(Animal::getCost).sum();
	}

	public void clear() {
		this.items.clear();
	}

	public String toString() {
		StringBuilder str=new StringBuilder();
		//Append the header
		str.append("\n    " + Zoo.STORE_NAME +"\n");
		str.append("  ------------------------\n\n");
		str.append("Daily Accounts:\n");
		//Append the info of each item
		for(Animal item:items)
		{
			str.append(item);
			str.append("\n");
		}

		str.append("                                              ---------------\n");

		//Append the total cost
		str.append("Total cost");
		str.append(this.space(Zoo.cents2dollarsAndCents(totalCost()), "Total cost"));
		str.append(" " + Zoo.cents2dollarsAndCents(totalCost()));



		return str.toString();
	}

	//Static method used to calculate the number of spaces needed to keep the right alignment
	public static String space(String cost,String prefix)
	{
		String spaces="";
		// #spaces = MAX_ITEM_SIZE + COST_WIDTH - (length of cost) - (length of the name)
		int i=Zoo.MAX_ITEM_NAME_SIZE+Zoo.COST_WIDTH-cost.length()-prefix.length();
		//Add the appropriate number of spaces to a string
		for(int j=0;j<i;j++)
			spaces+=" ";
		return spaces;

	}


}
