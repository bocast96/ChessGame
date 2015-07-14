package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Bishop extends Piece{
	private ImageIcon bishop;
	private int row, col, color;
	
	public Bishop(int color, int row, int col) {
		super(color, row, col);
		String path = "resources/";
		switch (color) {
		case 1:
			path += "white-bishop.png";
			break;
		case 2:
			path += "black-bishop.png";
			break;
		case 3:
			path += "green-bishop.png";
			break;
		case 4:
			path += "red-bishop.png";
			break;
		case 5:
			path += "yellow-bishop.png";
			break;
		}
		
		try {
			BufferedImage image = ImageIO.read(new File(path));
			bishop = (new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getColor() {
		return color;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public ImageIcon getIcon() {
		return bishop;
	}

	public void makeMove() {
		
	}
	
	public void possibleMoves(JButton[][] board, Piece[][] game) {
		
		for (int i = row + 1, j = col + 1; i < 8 && j < 8; i++, j++) {
			if (game[i][j] == null || game[i][j].getColor() != color) {
				board[i][j].setBorderPainted(true);
			} else {
				break;
			}
		}
		for (int i = row + 1, j = col - 1; i < 8 && j >= 0; i++, j--) {
			if (game[i][j] == null || game[i][j].getColor() != color) {
				board[i][j].setBorderPainted(true);
			} else {
				break;
			}
		}
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (game[i][j] == null || game[i][j].getColor() != color) {
				board[i][j].setBorderPainted(true);
			} else {
				break;
			}
		}
		for (int i = row - 1, j = col + 1; i >= 0 && j < 8; i--, j++) {
			if (game[i][j] == null || game[i][j].getColor() != color) {
				board[i][j].setBorderPainted(true);
			} else {
				break;
			}
		}
	}

}
