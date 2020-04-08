package quizzer;

import quizzer.function.Equation;
import quizzer.function.Randomizer;

import java.util.Random;

public class QuestionType {

	private String symbol;
	private Equation equation;
	private Randomizer randomizer;

	private QuestionType(String symbol, Equation equation, Randomizer randomizer) {
		this.symbol = symbol;
		this.equation = equation;
		this.randomizer = randomizer;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public int nextInt(Random rand) {
		return this.randomizer.nextInt(rand);
	}

	public int getAnswer(int a, int b) {
		return this.equation.apply(a, b);
	}

	public static class Builder {
		private String symbol;
		private Equation equation;
		private Randomizer randomizer;

		public Builder() {
			this.equation = (a, b) -> a;
			this.randomizer = rand -> 0;
		}

		public Builder setSymbol(String symbol) {
			this.symbol = symbol;
			return this;
		}

		public Builder setEquation(Equation equation) {
			this.equation = equation;
			return this;
		}

		public Builder setRand(Randomizer randomizer) {
			this.randomizer = randomizer;
			return this;
		}

		public QuestionType build() {
			return new QuestionType(this.symbol, this.equation, this.randomizer);
		}
	}

}
