package model;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Game {
	private JButton[][] board;
	private Piece[][] game;
	private int playerColor, AIColor;
	private Random rand;
	private ArrayList<Piece> playerTeam, AITeam;
	private boolean playerTurn;
	private boolean AITurn;
	Player player;
	AIControl aiControl;

	
	public Game(JButton[][] board) {
		this.board = board;
		game = new Piece[8][8];
		rand = new Random();
	}

	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}

	public void setAITurn(boolean AITurn) {this.AITurn = AITurn;}

	public void setGame() {
		chooseColor();
		populateBoard();
		setTeams();
		player = new Player(playerTeam, game, board, this);
		aiControl = new AIControl(AITeam,player,board,game,this);
	}
	
	private void chooseColor() {
		String[] options = {"White", "Black", "Green", "Red", "Yellow"};
		playerColor = JOptionPane.showOptionDialog(null, "Choose a Color!", "Lets get Started!", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		playerColor++;
		do {
			AIColor = rand.nextInt(5) + 1;
		} while (playerColor == AIColor);
	}
	
	private void populateBoard() {
		int color = playerColor, i = 7;
		
		for (int temp = 1 ; temp < 3 ; temp++) {
			game[i][0] = new Tower(color,i,0);
			board[i][0].setIcon(game[i][0].getIcon());
			
			game[i][1] = new Knight(color,i,1);
			board[i][1].setIcon(game[i][1].getIcon());
			
			game[i][2] = new Bishop(color,i,2);
			board[i][2].setIcon(game[i][2].getIcon());
			
			game[i][3] = new Queen(color,i,3);
			board[i][3].setIcon(game[i][3].getIcon());
			
			game[i][4] = new King(color,i,4);
			board[i][4].setIcon(game[i][4].getIcon());
			
			game[i][5] = new Bishop(color,i,5);
			board[i][5].setIcon(game[i][5].getIcon());
			
			game[i][6] = new Knight(color,i,6);
			board[i][6].setIcon(game[i][6].getIcon());
			
			game[i][7] = new Tower(color,i,7);
			board[i][7].setIcon(game[i][7].getIcon());
			
			color = AIColor;
			i = 0;
		}
		i = 6;
		color = playerColor;
		boolean player = true;
		for (int t = 1; t < 3; t++) {
			for (int j = 0; j < 8; j++) {
				game[i][j] = new Pawn(color,i,j, player);
				board[i][j].setIcon(game[i][j].getIcon());
			}
			i = 1;
			color = AIColor;
			player = false;
		}
	}
	
	private void setTeams() {
		playerTeam = new ArrayList<>();
		AITeam = new ArrayList<>();
		
		for (int j = 0; j < 8; j++) {
			AITeam.add(game[0][j]);
			AITeam.add(game[1][j]);
			playerTeam.add(game[6][j]);
			playerTeam.add(game[7][j]);
		}
	}
	
	public void playerMove() {
		playerTurn = false;
		System.out.println("Taking a turn ------------------------------------------------------------------");
		player.turn();
		do {
			if (AITurn) {
				System.out.println("Taking AI turn ===============================================================");
				aiControl.turn();
				AITurn = false;
			}
			if (playerTurn) {
				System.out.println("Taking a turn ------------------------------------------------------------------");
				player.turn();
				playerTurn = false;
			}
		} while (true);

	}

	public void printGame() {
		for (Piece[] row : game) {
			for (Piece piece : row) {
				if (piece == null) {
					System.out.print(" NULL ");
				} else {
					System.out.print(piece.toString() + " ");
				}
			}
			System.out.println();
		}
	}

	public void battle(Piece win, Piece lose) {
		if (playerTeam.contains(win)) {
			AITeam.remove(lose);
		} else {
			playerTeam.remove(lose);
		}
	}
}
