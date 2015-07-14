package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Knight extends Piece{
	private ImageIcon knight;
	
	public Knight(int color, int row, int col) {
		super(color, row, col);
		String path = "resources/";
		switch (color) {
		case 1:
			path += "white-knight.png";
			break;
		case 2:
			path += "black-knight.png";
			break;
		case 3:
			path += "green-knight.png";
			break;
		case 4:
			path += "red-knight.png";
			break;
		case 5:
			path += "yellow-knight.png";
			break;
		}
		
		try {
			BufferedImage image = ImageIO.read(new File(path));
			knight = (new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ImageIcon getIcon() {
		return knight;
	}

	public void makeMove() {
		
	}

	public void possibleMoves(JButton[][] board, Piece[][] game) {
		int[] x = {2,1,-1,-2,-2,-1,1,2};
		int[] y = {1,2,2,1,-1,-2,-2,-1};
		for (int n = 0; n < 8; n++) {
			int i = row + x[n], j = col + y[n];
			if (i >= 0 && j >= 0 && i < 8 && j < 8) {
				if (game[i][j] == null || game[i][j].getColor() != color) {
					board[i][j].setBorderPainted(true);
				}
			}
		}
	}
}
