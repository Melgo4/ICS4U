package quizzer.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class Console {

	public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public static String readString() {
		try {
			return in.readLine();
		} catch(IOException e) {
			return "";
		}
	}

	public static char readCharacter() {
		String s = readString();
		if(s.length() == 1)return s.charAt(0);
		throw new IllegalArgumentException();
	}

	public static int readInteger() {
		return Integer.parseInt(readString());
	}

	public static float readFloat() {
		return Float.parseFloat(readString());
	}

	public static float readLong() {
		return Long.parseLong(readString());
	}

	public static double readDouble() {
		return Double.parseDouble(readString());
	}

	public static char queryCharacter(ErrorEvent... onError) {
		while(true) {
			String line = readString();
			if(line.length() == 1)return line.charAt(0);

			for(ErrorEvent o: onError) {
				if(o != null)o.accept(line);
			}
		}
	}

	public static int queryInteger(ErrorEvent... onError) {
		while(true) {
			String line = readString();

			try {
				return Integer.parseInt(line);
			} catch(NumberFormatException e) {
				for(ErrorEvent o: onError) {
					if(o != null)o.accept(line);
				}
			}
		}
	}

	public static float queryFloat(ErrorEvent... onError) {
		while(true) {
			String line = readString();

			try {
				return Float.parseFloat(line);
			} catch(NumberFormatException e) {
				for(ErrorEvent o: onError) {
					if(o != null)o.accept(line);
				}
			}
		}
	}

	public static long queryLong(ErrorEvent... onError) {
		while(true) {
			String line = readString();

			try {
				return Long.parseLong(line);
			} catch(NumberFormatException e) {
				for(ErrorEvent o: onError) {
					if(o != null)o.accept(line);
				}
			}
		}
	}

	public static double queryDouble(ErrorEvent... onError) {
		while(true) {
			String line = readString();

			try {
				return Double.parseDouble(line);
			} catch(NumberFormatException e) {
				for(ErrorEvent o: onError) {
					if(o != null)o.accept(line);
				}
			}
		}
	}

	@FunctionalInterface
	public interface ErrorEvent extends Consumer<String> {
	}

}
