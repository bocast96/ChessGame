package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author bocast2919
 *
 */
public class Player {
	Piece[] team;
	Piece[][] game;
	JButton[][] board;
	JButton[] moves;
	
	public Player(Piece[] team, Piece[][] game, JButton[][] board) {
		super();
		this.team = team;
		this.board = board;
		this.game = game;
	}
	
	private void action(Piece piece) {
		moves = piece.possibleMoves(board, game);
	}
	
	private void clearBoard() {
		for (JButton[] row : board) {
			for (JButton b : row) {
				b.setBorderPainted(false);
			}
		}
	}
	
	public void teamAction() {
		for (final Piece piece : team) {
			board[piece.getRow()][piece.getCol()].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearBoard();
					action(piece);
				}
			});
		}
	}
}
