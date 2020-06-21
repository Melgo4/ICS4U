package zoo;

public class TestZooStats {

	public static void main(String[] args) {
		ZooStats checkout = new ZooStats();

		checkout.enterItem(new Mammal("Panda Bear",3, 399));
		checkout.enterItem(new Reptile("Alligator",5, 1500));
		checkout.enterItem(new Amphibian("Wood Frog",45, 50));
		checkout.enterItem(new Bird("Great Horned Owl", 4, 1098));
		checkout.enterItem(new Fish("Discus", 200, 150));
		checkout.enterItem(new Invertebrate("Southern Black Widow", 30, 67));

		System.out.println("\nNumber of items: " + checkout.numberOfItems());
		System.out.println("\nTotal cost: " + checkout.totalCost() + "\n\n");
		System.out.println(checkout);

		checkout.clear();

		checkout.enterItem(new Rodent("White Footed Mouse", 24, 98, 145));
		checkout.enterItem(new Amphibian("Salamander", 10, 600));
		checkout.enterItem(new Invertebrate("European Hornet", 200, 23));
		checkout.enterItem(new Snake("Cobra", 4, 235, 90));
		checkout.enterItem(new Mammal("Elephant", 6, 5040));
		checkout.enterItem(new Bird("Flamingo",12, 340));

		System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
		System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
		System.out.println(checkout);
	}

}
