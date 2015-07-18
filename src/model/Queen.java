package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Queen extends Piece{
	private ImageIcon queen;
	
	public Queen(int color, int row, int col) {
		super(color, row, col);	
		String path = "resources/";
		switch (color) {
		case 1:
			path += "white-queen.png";
			break;
		case 2:
			path += "black-queen.png";
			break;
		case 3:
			path += "green-queen.png";
			break;
		case 4:
			path += "red-queen.png";
			break;
		case 5:
			path += "yellow-queen.png";
			break;
		}
		
		try {
			BufferedImage image = ImageIO.read(new File(path));
			queen = (new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ImageIcon getIcon() {
		return queen;
	}

	public void makeMove() {
		
	}

	public JButton[] possibleMoves(JButton[][] board, Piece[][] game) {
		LinkedList<JButton> list = new LinkedList<JButton>();
		
		for (int i = row + 1; i < 8; i++) {
			if (game[i][col] == null || game[i][col].color != color) {
				board[i][col].setBorderPainted(true);
				list.add(board[i][col]);
			} else {
				break;
			}
		}
		for (int i = row -1; i >= 0; i--) {
			if (game[i][col] == null || game[i][col].color != color) {
				board[i][col].setBorderPainted(true);
				list.add(board[i][col]);
			} else {
				break;
			}
		}
		for (int i = col + 1; i < 8; i++) {
			if (game[row][i] == null || game[row][i].color != color) {
				board[row][i].setBorderPainted(true);
				list.add(board[row][i]);
			} else {
				break;
			}
		}
		for (int i = col -1; i >= 0; i--) {
			if (game[row][i] == null || game[row][i].color != color) {
				board[row][i].setBorderPainted(true);
				list.add(board[row][i]);
			} else {
				break;
			}
		}
		for (int i = row + 1, j = col + 1; i < 8 && j < 8; i++, j++) {
			if (game[i][j] == null || game[i][j].color != color) {
				board[i][j].setBorderPainted(true);
				list.add(board[i][j]);
			} else {
				break;
			}
		}
		for (int i = row + 1, j = col - 1; i < 8 && j >= 0; i++, j--) {
			if (game[i][j] == null || game[i][j].color != color) {
				board[i][j].setBorderPainted(true);
				list.add(board[i][j]);
			} else {
				break;
			}
		}
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (game[i][j] == null || game[i][j].color != color) {
				board[i][j].setBorderPainted(true);
				list.add(board[i][j]);
			} else {
				break;
			}
		}
		for (int i = row - 1, j = col + 1; i >= 0 && j < 8; i--, j++) {
			if (game[i][j] == null || game[i][j].color != color) {
				board[i][j].setBorderPainted(true);
				list.add(board[i][j]);
			} else {
				break;
			}
		}
		
		return list.toArray(new JButton[0]);
	}
}
