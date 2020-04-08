package quizzer;

import java.util.function.Function;

public class Config {

	private int questionCount;
	private int chances;
	private Function<Integer, Integer> score;

	private Config(int questionCount, int chances, Function<Integer, Integer> scoreCalculator) {
		this.questionCount = questionCount;
		this.chances = chances;
		this.score = scoreCalculator;
	}

	public int getQuestionCount() {
		return this.questionCount;
	}

	public int getChances() {
		return this.chances;
	}

	public int getScore(int chance) {
		return this.score.apply(chance);
	}

	public int getMaxScore() {
		return this.getScore(0) * this.getQuestionCount();
	}

	public static class Builder {
		private int questionCount;
		private int chances;
		private Function<Integer, Integer> scoreCalculator;
		
		public Builder() {
			this.scoreCalculator = chance -> 0;
		}

		public Builder setQuestionCount(int questionCount) {
			this.questionCount = questionCount;
			return this;
		}

		public Builder setChances(int chances) {
			this.chances = chances;
			return this;
		}

		public Builder setScore(Function<Integer, Integer> scoreCalculator) {
			this.scoreCalculator = scoreCalculator;
			return this;
		}
		
		public Config build() {
			return new Config(this.questionCount, this.chances, this.scoreCalculator);
		}
	}
	
}
