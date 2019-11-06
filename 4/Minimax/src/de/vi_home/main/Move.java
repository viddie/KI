package de.vi_home.main;

public class Move{
	
	int x;
	int y;
	
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Move)) {
			return false;
		}
		
		Move move = (Move) o;
		return x == move.x && y == move.y;
	}
	
	@Override
	public String toString() {
		return "("+x+"|"+y+")";
	}
	
	public int getMoveID() {
		return x*3 + y;
	}
}
