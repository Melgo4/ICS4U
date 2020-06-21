package zoo;

public class Zoo {

	public final static String STORE_NAME = "Toronto Zoo";
	public final static int MAX_ITEM_NAME_SIZE = 50;
	public final static int COST_WIDTH = 6;

	public static String cents2dollarsAndCents(int cents) {
		String s = "";

		if (cents < 0) {
			s += "-";
			cents *= -1;
		}

		int dollars = cents/100;
		cents = cents % 100;

		if (dollars > 0)
			s += dollars;

		s +=".";

		if (cents < 10)
			s += "0";

		s += cents;
		return s;
	}


}

