package quizzer;

import quizzer.collection.RandomList;
import quizzer.io.Console;

import java.util.Random;

public class Quizzer {

	private RandomList<QuestionType> questions = new RandomList<>(new QuestionType[] {
			new QuestionType.Builder().setSymbol("+").setEquation((a, b) -> a + b).setRand(r -> r.nextInt(100) + 1).build(),
			new QuestionType.Builder().setSymbol("-").setEquation((a, b) -> a - b).setRand(r -> r.nextInt(100) + 1).build(),
			new QuestionType.Builder().setSymbol("*").setEquation((a, b) -> a * b).setRand(r -> r.nextInt(12) + 1).build(),
			new QuestionType.Builder().setSymbol("/").setEquation((a, b) -> a / b).setRand(r -> r.nextInt(12) + 1).build()
	});

	private Config config;

	public Quizzer(Config config) {
		this.config = config;
	}

	public static void main(String[] args) {
		Config config = new Config.Builder().setQuestionCount(5).setChances(3).setScore(c -> c * c - 4 * c + 5).build();
		Quizzer quizzer = new Quizzer(config);
		quizzer.quiz();
	}

	private void quiz() {
		Random rand = new Random();
		int totalScore = 0;

		System.out.println("Welcome to the quizzer!");

		for(int i = 0; i < this.config.getQuestionCount(); i++) {
			QuestionType question = this.questions.getRandomElement(rand);
			int num1 = question.nextInt(rand);
			int num2 = question.nextInt(rand);
			int answer = question.getAnswer(num1, num2);

			for(int chance = 0; chance < this.config.getChances(); chance++) {
				System.out.print(num1 + " " + question.getSymbol() + " " + num2 + " = ");

				int userAnswer = Console.queryInteger(s -> {
					System.out.println("[" + s + "] is an invalid input!");
					System.out.print(num1 + " " + question.getSymbol() + " " + num2 + " = ");
				});

				if(userAnswer == answer) {
					int score = this.config.getScore(chance);
					System.out.println(" > Correct answer! You get " + score + (score == 1 ? " point." : " points."));
					totalScore += score;
					break;
				}

				if(chance == this.config.getChances() - 1) {
					System.out.println(" > Ur bad. The right answer is " + answer + ".");
				} else {
					System.out.println(" > Wrong answer, try again.");
				}
			}
		}

		System.out.println("\nGreat job! Your total score is " + totalScore + " / " + this.config.getMaxScore() + ".");
	}

}
