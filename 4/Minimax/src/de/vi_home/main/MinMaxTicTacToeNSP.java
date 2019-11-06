package de.vi_home.main;

import java.util.Scanner;

public class MinMaxTicTacToeNSP {
	
	// ???
	
	public static int calls = 0;
	
	public MinMaxTicTacToeNSP() {
		Scanner scan = new Scanner(System.in);
		State state = new State();
		
		
		state.printField();
		
		int turn = 1;
		while(!state.isTerminal()) {
			Move maxMove = miniMax(state);
			System.out.println("Turn "+(turn++)+": Player X places in "+maxMove);
			state.doMove(maxMove, 1);
			state.printField();
			
			if(state.isTerminal()) {
				break;
			}
			
			
//			String turnsLeft = "";
//			for(Move m : state.getLeftMoves()) {
//				turnsLeft += m.getMoveID()+", ";
//			}
//			turnsLeft = turnsLeft.substring(0, turnsLeft.length()-2);
//			
//			
//			System.out.println("Feld eingeben ("+turnsLeft+"):");
//			System.out.print("> ");
//			
//			int choice = scan.nextInt();
//			
//			Move selectedMove = null;
//			for(Move m : state.getLeftMoves()) {
//				if(m.getMoveID() == choice) {
//					selectedMove = m;
//				}
//			}
			
			Move selectedMove = maxiMin(state);
			System.out.println("Turn "+(turn++)+": Player O places in "+selectedMove);
			state.doMove(selectedMove, -1);
			state.printField();
		}
		

		scan.close();
		
		System.out.println("Total calls: "+calls);
	}
	
	public Move miniMax(State state) {
		int value = Integer.MIN_VALUE;
		Move move = null;
		for(Move successor : state.getLeftMoves()) {
			state.doMove(successor, 1);
			int val = minValue(state);
			if(val >= value) {
				value = val;
				move = successor;
			}
			state.undoMove(successor);
		}
		
		return move;
	}
	
	public Move maxiMin(State state) {
		int value = Integer.MAX_VALUE;
		Move move = null;
		for(Move successor : state.getLeftMoves()) {
			state.doMove(successor, -1);
			int val = maxValue(state);
			if(val <= value) {
				value = val;
				move = successor;
			}
			state.undoMove(successor);
		}
		
		return move;
	}
	
	public int maxValue(State state) {
		calls++;
		
		if(state.isTerminal()) {
			return state.utility();
		}
		
		if(state.getLeftMoves().size() == 6) {
			state.printField("MAX with 6 turns left");
		}
		
		
		int value = Integer.MIN_VALUE;
		
		for(Move move : state.getLeftMoves()) {
			state.doMove(move, 1);
			value = Math.max(value, minValue(state));
			state.undoMove(move);
		}
		
		return value;
	}
	
	public int minValue(State state) {
		calls++;
		
		if(state.isTerminal()) {
			return state.utility();
		}
		
		int value = Integer.MAX_VALUE;
		
		for(Move move : state.getLeftMoves()) {
			state.doMove(move, -1);
			value = Math.min(value, maxValue(state));
			state.undoMove(move);
		}
		
		return value;
	}
}
