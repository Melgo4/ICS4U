package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicTacToe extends JFrame {

	private int centerX, centerY;
	private Player player = Player._1;
	private Player winner = Player.NULL;
	private GameBoard board = new GameBoard();

	public TicTacToe() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width >>> 1, screenSize.height >>> 1);
		this.centerX = screenSize.width >>> 2;
		this.centerY = screenSize.height >>> 2;
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.initialize();
		this.setLayout(null);
		this.setVisible(true);
	}

	private void initialize() {
		int buttonSize = 60;

		JLabel statusText = new JLabel();
		statusText.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
		statusText.setBounds(this.centerX - buttonSize - buttonSize / 2, this.centerY + 20, 200, 200);
		this.add(statusText);

		for(int y = 0; y < GameBoard.SIZE; y++) {
			for(int x = 0; x < GameBoard.SIZE; x++) {
				JButton button = new JButton(" ");
				int px = this.centerX - buttonSize / 2 + (x - 1) * buttonSize;
				int py = this.centerY - buttonSize / 2 + (y - 1) * buttonSize;
				button.setBounds(px, py, buttonSize, buttonSize);
				button.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));

				final int finalX = x;
				final int finalY = y;

				button.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {

					}

					@Override
					public void mousePressed(MouseEvent e) {
						if(winner == Player.NULL && board.set(finalX, finalY, player)) {
							player = player.other();

							winner = board.checkWinner();

							if(winner != Player.NULL) {
								statusText.setText(winner + " wins!");
							}
						}
					}

					@Override
					public void mouseReleased(MouseEvent e) {

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						if(winner == Player.NULL && board.get(finalX, finalY) == Player.NULL) {
							button.setText(player.text);
							button.setForeground(Color.LIGHT_GRAY);
						}
					}

					@Override
					public void mouseExited(MouseEvent e) {
						button.setText(board.get(finalX, finalY).text);
						button.setForeground(Color.BLACK);
					}
				});

				this.add(button);
			}
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
	}

}
