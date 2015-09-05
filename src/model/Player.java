package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author bocast2919
 *
 */
public class Player {
	ArrayList<Piece> team;
	Piece[][] game;
	Piece selected;
	JButton[][] board;
	Pair[] moves;
	ImageIcon icon;
	Game g;

	public Player(ArrayList<Piece> playerTeam, Piece[][] game, JButton[][] board, Game g) {
		this.team = playerTeam;
		this.game = game;
		this.board = board;
		this.g = g;
	}

	public void clearBoard() {
		for (JButton[] row : board) {
			for (JButton b : row) {
				b.setBorderPainted(false);
			}
		}
	}
	
	private void clearMoves(Pair[] list) {
		if (list != null) {
			for (Pair pair : list) {
				JButton b = board[pair.getRow()][pair.getCol()];
				for (ActionListener al : b.getActionListeners()) {
					b.removeActionListener(al);
				}
			}
		}
	}
	
	private void clearMoves() {
		for (Piece piece : team) {
			JButton b = board[piece.getRow()][piece.getCol()];
			for (ActionListener al : b.getActionListeners()) {
				b.removeActionListener(al);
			}
		}
	}
	
	private void action(Piece piece) {
		clearMoves(moves);
		moves = piece.possibleMoves(board, game);
		icon = piece.getIcon();
		selected = piece;
	}
	
	public void teamAction() {
		for (final Piece piece : team) {
			board[piece.getRow()][piece.getCol()].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearBoard();
					action(piece);
					makeMove();
				}
			});
		}
	}
	
	private void movesAction(Pair pair) {
		int row = pair.getRow(), col = pair.getCol();
		clearMoves(moves);
		clearMoves();
		board[selected.getRow()][selected.getCol()].setIcon(null);
		game[selected.getRow()][selected.getCol()] = null;

		if (game[row][col] != null) {
			g.battle(selected, game[row][col]);
		}
		selected.setRow(row); selected.setCol(col);                                                   
		game[row][col] = selected;
		board[row][col].setIcon(selected.getIcon());
		if (selected instanceof Pawn) {
			((Pawn) selected).setFirstMove(false);
		}
		g.setAITurn(true);
	}
	
	public void makeMove() {
		for (final Pair pair : moves) {
			final JButton b = board[pair.getRow()][pair.getCol()];
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					movesAction(pair);
					clearBoard();
				}
			});
		}
	}
	
	public void turn() {
		teamAction();
	}
}
