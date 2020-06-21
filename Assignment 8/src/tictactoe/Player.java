package tictactoe;

public enum Player {

	NULL(" "), _1("O"), _2("X");

	public final String text;

	Player(String text) {
		this.text = text;
	}

	public Player other() {
		if(this == NULL) {
			throw new UnsupportedOperationException();
		}

		return this == _1 ? _2 : _1;
	}

	@Override
	public String toString() {
		if(this == NULL) {
			return "NULL";
		}

		return this == _1 ? "Player 1" : "Player 2";
	}

}
