package stack;

import stack.io.Console;

public class Main {

	public static Instruction[] INSTRUCTIONS = new Instruction[] {
			new Instruction("Push", stack -> {
				System.out.println("Input the word you wish to push:");
				stack.push(Console.readString());
			}),
			new Instruction("Pop", stack -> System.out.println(stack.isEmpty() ? "Cannot pop an empty stack!" : "Popping reveals \"" + stack.pop() + "\".")),
			new Instruction("Empty", stack -> System.out.println("The stack is " + (stack.isEmpty() ? "empty." : "not empty."))),
			new Instruction("Size", stack -> System.out.println("The stack contains " + stack.size() + "elements.")),
			new Instruction("Peek", stack -> System.out.println(stack.isEmpty() ? "Cannot peek an empty stack!" : "Peeking reveals \"" + stack.peek() + '\"')),
			new Instruction("Contains", stack -> {
				System.out.println("Input the word you wish to search for:");
				int i = stack.search(Console.readString());
				System.out.println(i == -1 ? "The word is not in the stack." : "The word is in the stack at index " + i + ".");
			}),
			new Instruction("Remove", stack -> {
				System.out.println("Input the word you wish to remove:");
				stack.remove(Console.readString());
			}),
			new Instruction("Show", stack -> System.out.println("The stack is: " + stack + "."))
	};

	public static void main(String[] args) {
		BasicStack<String> stack = new BasicStack<>();

		for(int i = 0; i < INSTRUCTIONS.length; i++) {
			Instruction instruction = INSTRUCTIONS[i];
			System.out.format("%d) %s \n", i + 1, instruction.name);
		}

		System.out.println("======================================================\n");

		while(true) {
			System.out.println("Please select an instruction: ");

			int choice = Console.queryInteger(s -> {
				System.out.println("[" + s.trim() + "] is not a valid integer.");
			}) - 1;

			if(choice >= 0 && choice < INSTRUCTIONS.length) {
				INSTRUCTIONS[choice].behaviour.apply(stack);
			} else if(choice != INSTRUCTIONS.length) {
				System.out.println((choice + 1) + " is not a valid input.");
			} else {
				break;
			}
		}
	}

	public static class Instruction {
		public final String name;
		public final StackConsumer behaviour;

		public Instruction(String name, StackConsumer behaviour) {
			this.name = name;
			this.behaviour = behaviour;
		}
	}

	@FunctionalInterface
	public interface StackConsumer {
		void apply(BasicStack<String> stack);
	}

}