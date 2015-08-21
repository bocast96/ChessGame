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
		
		for (int i = row + 1; i < 8; i++) {
			if (game[i][col] == null  || game[i][col].getColor() != color) {
				board[i][col].setBorderPainted(true);
				list.add(new Pair(i,col));
			} else {
				break;
			}
		}
		for (int i = row -1; i >= 0; i--) {
			if (game[i][col] == null || game[i][col].getColor() != color) {
				board[i][col].setBorderPainted(true);
				list.add(new Pair(i,col));
			} else {
				break;
			}
		}
		for (int i = col + 1; i < 8; i++) {
			if (game[row][i] == null || game[row][i].getColor() != color) {
				board[row][i].setBorderPainted(true);
				list.add(new Pair(row,i));
			} else {
				break;
			}
		}
		for (int i = col -1; i >= 0; i--) {
			if (game[row][i] == null || game[row][i].getColor() != color) {
				board[row][i].setBorderPainted(true);
				list.add(new Pair(row,i));
			} else {
				break;
			}
		}
		
		return list.toArray(new Pair[0]);
	}

}
