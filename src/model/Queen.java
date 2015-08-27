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
	
	public Queen( int color, int row, int col) {
		super("Queen", color, row, col);
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

	public Pair[] possibleMoves(JButton[][] board, Piece[][] game) {
		LinkedList<Pair> list = new LinkedList<Pair>();
		
		for (int i = row + 1; i < 8; i++) {
			if (game[i][col] == null) {
				add(board, list, i, col);
			} else if (game[i][col].getColor() != color) {
				add(board,list,i,col);
				break;
			} else {
				break;
			}
		}
		for (int i = row -1; i >= 0; i--) {
			if (game[i][col] == null) {
				add(board, list, i, col);
			} else if (game[i][col].getColor() != color) {
				add(board,list,i,col);
				break;
			} else {
				break;
			}
		}
		for (int i = col + 1; i < 8; i++) {
			if (game[row][i] == null) {
				add(board, list, row, i);
			} else if (game[row][i].getColor() != color) {
				add(board,list,row,i);
				break;
			} else {
				break;
			}
		}
		for (int i = col -1; i >= 0; i--) {
			if (game[row][i] == null) {
				add(board, list, row, i);
			} else if (game[row][i].getColor() != color) {
				add(board,list,row,i);
				break;
			} else {
				break;
			}
		}
//<------------------ diagonal moves -------------------------------------------------->
		for (int i = row + 1, j = col + 1; i < 8 && j < 8; i++, j++) {
			if (game[i][j] == null) {
				add(board, list, i, j);
			} else if (game[i][j].getColor() != color) {
				add(board,list,i,j);
				break;
			} else {
				break;
			}
		}
		for (int i = row + 1, j = col - 1; i < 8 && j >= 0; i++, j--) {
			if (game[i][j] == null) {
				add(board, list, i, j);
			} else if (game[i][j].getColor() != color) {
				add(board,list,i,j);
				break;
			} else {
				break;
			}
		}
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (game[i][j] == null) {
				add(board, list, i, j);
			} else if (game[i][j].getColor() != color) {
				add(board,list,i,j);
				break;
			} else {
				break;
			}
		}
		for (int i = row - 1, j = col + 1; i >= 0 && j < 8; i--, j++) {
			if (game[i][j] == null) {
				add(board, list, i, j);
			} else if (game[i][j].getColor() != color) {
				add(board,list,i,j);
				break;
			} else {
				break;
			}
		}
		
		return list.toArray(new Pair[0]);
	}

	private void add(JButton[][] board, LinkedList<Pair> list, int i, int j) {
		board[i][j].setBorderPainted(true);
		list.add(new Pair(i,j));
	}
}
