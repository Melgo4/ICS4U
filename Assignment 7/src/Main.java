import collection.LinkedList;

public class Main {

	public static void main(String[] args) {
		LinkedList<PersonsScore> score = new LinkedList<>();//Initialize Scores object

		//Add objects
		score.add(new PersonsScore("Mike", 1105));
		score.add(new PersonsScore("Paul", 720));
		score.add(new PersonsScore("Rose", 590));
		score.add(new PersonsScore("Jack", 510));
		score.add(new PersonsScore("Anna", 660));
		score.add(new PersonsScore("Rob", 750));

		//Output number of entries
		System.out.println(score.size() + " Entries");

		//Output top 5 entries
		System.out.println(score);

		score.add(new PersonsScore("Jill", 720));

		System.out.println(score);

		score.remove("Paul");

		System.out.println(score);

		score.add(new PersonsScore("Phil", 870));
		score.add(new PersonsScore("Don", 470));
		score.add(new PersonsScore("Donna", 630));
		score.add(new PersonsScore("Erin", 420));
		score.add(new PersonsScore("George", 500));

		//Capture invalid arguments using try...catch block
		//try {
		//	score.add(new PersonsScore(6, "Tom"));
		//} catch(Exception e) {
		//	System.out.println(e.getMessage()+"\n");
		//}

		System.out.println(score.size() + " Entries");
		System.out.println(score);
	}

}
