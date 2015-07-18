package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Pawn extends Piece{
	private ImageIcon pawn;
	private boolean firstMove, playerPawn;
	
	public Pawn(int color, int row, int col, boolean playerPawn) {
		super(color, row, col);
		firstMove = true;
		this.playerPawn = playerPawn;
		String path = "resources/";
		switch (color) {
		case 1:
			path += "white-pawn.png";
			break;
		case 2:
			path += "black-pawn.png";
			break;
		case 3:
			path += "green-pawn.png";
			break;
		case 4:
			path += "red-pawn.png";
			break;
		case 5:
			path += "yellow-pawn.png";
			break;
		}
		
		try {
			BufferedImage image = ImageIO.read(new File(path));
			pawn = (new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ImageIcon getIcon() {
		return pawn;
	}

	public void makeMove() {
		
	}
	
	public JButton[] possibleMoves(JButton[][] board, Piece[][] game) {
		int x = playerPawn ? -1 : 1;
		int i = row + x, j1 = col - 1, j2 = col + 1;
		LinkedList<JButton> list = new LinkedList<JButton>();
		
		if (i >= 0 && i < 8) {
			if (game[i][col] == null || game[i][col].getColor() != color) {
				board[i][col].setBorderPainted(true);
				list.add(board[i][col]);
			}
		}
		
		if (j1 >= 0 && game[i][j1] != null && game[i][j1].getColor() != color) {
			board[i][j1].setBorderPainted(true);
			list.add(board[i][j1]);
		}
		
		if (j2 < 8 && game[i][j2] != null && game[i][j2].getColor() != color) {
			board[i][j2].setBorderPainted(true);
			list.add(board[i][j2]);
		}
		
		if (firstMove) {
			i += x;
			if (game[i][col] == null || game[i][col].getColor() != color) {
				board[i][col].setBorderPainted(true);
				list.add(board[i][col]);
			}
		}
		
		return list.toArray(new JButton[0]);
	}

}
