package chessBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import model.Game;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Pawn;

public class ChessBoardGUI{
	JPanel chessBoard;
	JButton squares[][];
	
	public ChessBoardGUI() {
		chessBoard = new JPanel(new GridLayout(8,8));
		squares = new JButton[8][8];
		makeSquares();
	}
	
	public void makeSquares() {
		LineBorder border = new LineBorder(Color.yellow, 7);
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				JButton b = new JButton();
				if (i%2 == 0) {
					b.setBackground( j % 2 == 0 ? Color.darkGray : Color.white);
				} else {
					b.setBackground( j % 2 == 0 ? Color.white : Color.darkGray);
				}
				chessBoard.add(b);
				squares[i][j] = b;
				b.setBorder(border);
				b.setBorderPainted(false);
			}
		}
	}
	
	public JButton[][] getSquares() {
		return squares;
	}
	
	public JPanel getChessBoard() {
		return chessBoard;
	}
	
	public static void createAndDisplay() {
		JFrame board = new JFrame();
		ChessBoardGUI GUI = new ChessBoardGUI();
		board.add(GUI.getChessBoard());
		board.setSize(800, 800);
		board.setResizable(false);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int upperLeftCornerX = (screenSize.width - board.getWidth()) / 2;
	    int upperLeftCornerY = (screenSize.height - board.getHeight()) / 2;
	    board.setLocation(upperLeftCornerX, upperLeftCornerY);
		board.setVisible(true);
		Game game = new Game(GUI.getSquares());
		game.chooseColor();
		game.populateBoard();
		
	}

	public static void main(String[] args) {
				createAndDisplay();
	}

}
