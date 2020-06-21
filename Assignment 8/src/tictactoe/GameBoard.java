package tictactoe;

public class GameBoard {

	public static final int SIZE = 3;
	private int[][] board = new int[SIZE][SIZE];

	public GameBoard() {

	}

	public Player get(int x, int y) {
		return Player.values()[this.board[y][x]];
	}

	public boolean set(int x, int y, Player player) {
		if(this.board[y][x] != Player.NULL.ordinal()) {
			return false;
		}

		this.board[y][x] = player.ordinal();
		return true;
	}

	public Player checkWinner() {
		Player p;

		for(int y = 0; y < SIZE; y++) {
			p = this.checkHorizontal(y);
			if(p != Player.NULL)return p;
		}

		for(int x = 0; x < SIZE; x++) {
			p = this.checkVertical(x);
			if(p != Player.NULL)return p;
		}

		p = this.checkDiagonal(0, 0, 1, 1);
		if(p != Player.NULL)return p;

		p = this.checkDiagonal(0, SIZE - 1, 1, -1);
		if(p != Player.NULL)return p;

		return Player.NULL;
	}

	private Player checkHorizontal(int y) {
		Player p = this.get(0, y);
		if(p == Player.NULL)return Player.NULL;

		for(int x = 1; x < SIZE; x++) {
			if(this.get(x, y) != p) {
				return Player.NULL;
			}
		}

		return p;
	}

	private Player checkVertical(int x) {
		Player p = this.get(x, 0);
		if(p == Player.NULL)return Player.NULL;

		for(int y = 1; y < SIZE; y++) {
			if(this.get(x, y) != p) {
				return Player.NULL;
			}
		}

		return p;
	}

	private Player checkDiagonal(int x, int y, int xIncrement, int yIncrement) {
		Player p = this.get(x, y);
		x += xIncrement;
		y += yIncrement;
		if(p == Player.NULL)return Player.NULL;

		for(; x < SIZE && x >= 0 && y < SIZE && y >= 0; x += xIncrement, y += yIncrement) {
			if(this.get(x, y) != p) {
				return Player.NULL;
			}
		}

		return p;
	}

}
