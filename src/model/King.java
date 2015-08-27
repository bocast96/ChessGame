package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class King extends Piece{
	private ImageIcon king;
	
	public King(int color, int row, int col) {
		super("King", color, row, col);
		String path = "resources/";
		switch (color) {
		case 1:
			path += "white-king.png";
			break;
		case 2:
			path += "black-king.png";
			break;
		case 3:
			path += "green-king.png";
			break;
		case 4:
			path += "red-king.png";
			break;
		case 5:
			path += "yellow-king.png";
			break;
		}
		
		try {
			BufferedImage image = ImageIO.read(new File(path));
			king = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ImageIcon getIcon() {
		return king;
	}
	
	public void makeMove() {
		
	}
	
	@Override
	public Pair[] possibleMoves(JButton[][] board, Piece[][] game) {
		int[] x = {0,1,1,1,0,-1,-1,-1};
		int[] y = {1,1,0,-1,-1,-1,0,1};
		LinkedList<Pair> list = new LinkedList<Pair>();
		
		for (int n = 0; n < 8; n++) {
			int i = row + x[n], j = col + y[n];
			if (i >= 0 && i < 8 && j >= 0 && j < 8) {
				if (game[i][j] == null || game[i][j].color != color) {
					board[i][j].setBorderPainted(true);
					list.add(new Pair(i,j));
				}
			}
		}
		
		return list.toArray(new Pair[0]);
	}
}
