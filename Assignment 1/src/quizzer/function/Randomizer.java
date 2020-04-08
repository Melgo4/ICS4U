package quizzer.function;

import java.util.Random;

/**
 * Functional interface for getting a random number given a random context.
 * */
@FunctionalInterface
public interface Randomizer {

	int nextInt(Random rand);

}
