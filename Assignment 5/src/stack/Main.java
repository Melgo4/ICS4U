package stack;

public class Main {

	public static void main(String[] args) {
		BasicStack<Integer> stuff = new BasicStack<>();

		for(int i = 0; i < 100; i++) {
			stuff.push(i);
		}

		stuff.forEach(integer -> System.out.println(stuff.search(integer)));
	}

}
