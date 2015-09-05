package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by bocast2919 on 9/4/2015.
 */
public class AIControl {
    private ArrayList<Piece> team;
    private Player player;
    private JButton[][] board;
    private Piece[][] game;
    private Game g;

    public AIControl(ArrayList<Piece> team, Player player, JButton[][] board, Piece[][] game, Game g) {
        this.team = team;
        this.player = player;
        this.board = board;
        this.game = game;
        this.g = g;
    }

    private void randomMove() {
        Random random = new Random();
        boolean turnTaken = true;

        while (turnTaken) {
            int i = random.nextInt(team.size());
            Pair[] moves = team.get(i).possibleMoves(board, game);
            player.clearBoard();
            if (moves.length > 0) {
                int j = random.nextInt(moves.length);
                System.out.println("row: "+ moves[j].getRow() + " col: " + moves[j].getCol());
                makeMove(team.get(i), moves[j]);
                turnTaken = false;
            }
        }
    }

    private void makeMove(Piece piece, Pair pair) {
        int row = piece.getRow(); int col = piece.getCol();
        board[row][col].setIcon(null);
        game[row][col] = null;

        row = pair.getRow();
        col = pair.getCol();
        if (game[row][col] != null) {
            g.battle(piece, game[row][col]);
        }
        board[row][col].setIcon(piece.getIcon());
        game[row][col] = piece;
        piece.setRow(row);
        piece.setCol(col);
        g.setPlayerTurn(true);
    }

    public void turn() {
        randomMove();
    }
}
