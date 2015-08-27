package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tower extends Piece{
	private ImageIcon tower;
	
	public Tower(int color, int row, int col) {
		super("Tower", color, row, col);
		String path = "resources/";
		switch (color) {
		case 1:
			path += "white-tower.png";
			break;
		case 2:
			path += "black-tower.png";
			break;
		case 3:
			path += "green-tower.png";
			break;
		case 4:
			path += "red-tower.png";
			break;
		case 5:
			path += "yellow-tower.png";
			break;
		}
		
		try {
			BufferedImage image = ImageIO.read(new File(path));
			tower = (new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ImageIcon getIcon() {
		return tower;
	}

	public void makeMove() {
		
	}
	
	public Pair[] possibleMoves(JButton[][] board, Piece[][] game) {
		LinkedList<Pair> list = new LinkedList<Pair>();

		// moving down on the  board
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

		// moving north on the board
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
		
		return list.toArray(new Pair[0]);
	}

	private void add(JButton[][] board, LinkedList<Pair> list, int i, int j) {
		board[i][j].setBorderPainted(true);
		list.add(new Pair(i,j));
	}
}
