package minesweeper;

import minesweeper.collection.DisplayScore;
import minesweeper.io.Console;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BooleanSupplier;

public class GameInterface {

	private DisplayScore<GameEntry> gameEntries = new DisplayScore<>();

	private Instruction[] instructions = {
			new Instruction("Load high score file.", this::loadFile),
			new Instruction("Add entry.", this::addEntry),
			new Instruction("Remove entry.", this::removeEntry),
			new Instruction("Display alphabetically sorted leader boards.", this::printNameLeaderBoards),
			new Instruction("Display top 5 per game level.", this::printLevelLeaderBoards)
	};

	public static void main(String[] args) {
		new GameInterface().start();
	}

	private void start() {
		while(true) {
			System.out.format("Please pick an instruction between 1 and %d.\n", this.instructions.length);

			for(int i = 0; i < this.instructions.length; i++) {
				Instruction instructions = this.instructions[i];
				System.out.format("%d) %s\n", i + 1, instructions.message);
			}

			int id = Console.queryInteger(s -> System.err.format("Please input a valid number.\n")) - 1;

			if(id < 0 || id >= this.instructions.length) {
				System.err.format("The number is out of bounds, please input a valid one.\n");
				continue;
			}

			while(!this.instructions[id].run()) {
				System.out.println();
			}

			System.out.println();
		}
	}

	private boolean loadFile() {
		System.out.format("Enter the name of the file to read from.\n");
		String fileName = Console.readString();

		File scoresFile = new File(fileName);

		try {
			BufferedReader reader = new BufferedReader(new FileReader(scoresFile));
			this.gameEntries.clear();

			while(reader.ready()) {
				GameEntry gameEntry = new GameEntry();
				gameEntry.deserialize(reader.readLine());
				this.gameEntries.add(gameEntry);
			}

			System.out.format("File %s was successfully loaded!\n", scoresFile.getAbsolutePath());
		} catch(FileNotFoundException e) {
			System.err.format("File at %s doesn't exist.\n", scoresFile.getAbsolutePath());
			return false;
		} catch(IOException e) {
			System.err.format("Unexpected error occurred while reading file at %s.\n", scoresFile.getAbsolutePath());
			return false;
		}

		return true;
	}

	private boolean addEntry() {
		try {
			System.out.format("Enter an entry in the form <$NAME, $SCORE, $LEVEL>: ");
			String rawEntry = Console.readString();
			GameEntry gameEntry = new GameEntry();
			gameEntry.deserialize(rawEntry);
			this.gameEntries.add(gameEntry);
		} catch(InvalidParameterException e) {
			System.err.format("Please enter a valid entry...\n");
			return false;
		}

		return true;
	}

	private boolean removeEntry() {
		try {
			System.out.format("Enter the name: ");
			String name = Console.readString();
			System.out.format("Enter the level: ");
			GameLevel level = GameLevel.valueOf(Console.readString().toUpperCase());
			this.gameEntries.remove(new GameEntry(name, 0, level));
		} catch(Exception e) {
			System.err.format("Please enter a valid value.\n");
			return false;
		}

		return true;
	}

	private boolean printNameLeaderBoards() {
		/*
		* this.gameEntries.stream().sorted(Comparator.comparing(GameEntry::getName)).forEach(System.out::println);
		* return true;
		* */
		GameEntry[] topEntries = this.gameEntries.toArray(new GameEntry[0]);
		Comparator<GameEntry> comparator = Comparator.comparing(GameEntry::getName);

		//Selection sort.
		for(int i = 0; i < topEntries.length; i++) {
			GameEntry minElement = topEntries[i];
			int min = i;

			for(int j = i; j < topEntries.length; j++) {
				GameEntry currentElement = topEntries[j];

				if(comparator.compare(currentElement, minElement) < 0) {
					minElement = topEntries[j];
					min = j;
				}
			}

			topEntries[min] = topEntries[i];
			topEntries[i] = minElement;
		}

		Arrays.stream(topEntries).forEach(System.out::println);
		return true;
	}

	private boolean printLevelLeaderBoards() {
		for(GameLevel level: GameLevel.values()) {
			System.out.format("\nLevel %s: \n", level);
			this.gameEntries.stream().filter(e -> e.getLevel().equals(level)).limit(5).forEach(System.out::println);
		}

		return true;
	}

	public static class Instruction {
		public final String message;
		public final BooleanSupplier function;

		public Instruction(String message, BooleanSupplier function) {
			this.message = message;
			this.function = function;
		}

		public boolean run() {
			return this.function.getAsBoolean();
		}
	}

}
