package quizzer.function;

import java.util.function.BiFunction;

/**
 * Functional interface wrapper for {@code java.util.function.BiFunction} that takes in two integers and returns one.
 * */
@FunctionalInterface
public interface Equation extends BiFunction<Integer, Integer, Integer> {
}
