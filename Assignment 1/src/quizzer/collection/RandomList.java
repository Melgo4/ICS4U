package quizzer.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomList<T> extends ArrayList<T> {

	public RandomList() {
		this(null);
	}

	public RandomList(T[] arr) {
		if(arr == null || arr.length == 0)return;
		this.addAll(Arrays.asList(arr));
	}

	public T getRandomElement(Random rand) {
		int size = this.size();
		if(size == 0)return null;
		return this.get(rand.nextInt(size));
	}

}
