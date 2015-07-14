package model;

import javax.swing.ImageIcon;

public abstract class Piece {
	int row, col, color;

	public Piece(int color, int row, int col) {
		this.row = row;
		this.col = col;
		this.color = color;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public abstract void makeMove();

	public abstract ImageIcon getIcon();
}
