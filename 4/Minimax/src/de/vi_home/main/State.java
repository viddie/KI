package de.vi_home.main;

import java.util.ArrayList;

public class State {
	
	private int[][] field;
	
	private ArrayList<Move> leftMoves = new ArrayList<Move>();
	private ArrayList<Move> path = new ArrayList<Move>();
	
	public State() {
		field = new int[3][3];
		
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				leftMoves.add(new Move(x, y));
			}
		}
	}
	
	public ArrayList<Move> getLeftMoves(){
		ArrayList<Move> toRet = new ArrayList<>();
		for(Move move : leftMoves) {
			toRet.add(move);
		}
		return toRet;
	}
	
	public void doMove(Move move, int val) {
		leftMoves.remove(move);
		path.add(move);
		field[move.x][move.y] = val;
	}
	
	public void undoMove(Move move) {
		leftMoves.add(move);
		path.remove(move);
		field[move.x][move.y] = 0;
	}
	
	public boolean isTerminal() {
		return leftMoves.isEmpty() || utility() != 0;
	}
	
	public int utility() {
		for(int iter = 0; iter < 3; iter++) {
			if(field[iter][0] == field[iter][1] && field[iter][0] == field[iter][2]) {
				return field[iter][0];
			}
			if(field[0][iter] == field[1][iter] && field[0][iter] == field[2][iter]) {
				return field[0][iter];
			}
		}
		
		if(field[0][0] == field[1][1] && field[0][0] == field[2][2]) {
			return field[0][0];
		}
		if(field[2][0] == field[1][1] && field[2][0] == field[0][2]) {
			return field[2][0];
		}
		
		return 0;
	}
	
	public String charAt(int x, int y) {
		if(field[x][y] == -1) {
			return "O";
		} else if(field[x][y] == 1) {
			return "X";
		} else {
			return " ";
		}
	}
	
	public void printField() {
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				System.out.print(" "+charAt(x, y)+" ");
				if(y != 2) {
					System.out.print("|");
				}
			}
			System.out.println();
			if(x != 2) {
				System.out.println("-----------");
			}
		}
		System.out.println();
	}
	
	public void printField(String prefix) {
		System.out.println(prefix);
		printField();
	}
	
	public void printPath() {
		System.out.println("Final path for ("+utility()+"):");
		for(Move move : path) {
			System.out.print("("+move.x+"|"+move.y+") ");
		}
		System.out.println();
	}
}
