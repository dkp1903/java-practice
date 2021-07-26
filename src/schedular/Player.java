package schedular;
import java.util.*;

public class Player implements Comparable<Player>{
	private String playerName;
	private ArrayList<Game> games = new ArrayList<Game>();
	
	public Player(String playerName) {
		super();
		playerName = playerName;
		games = new ArrayList<Game>();
	}

	public Player(String playerName, ArrayList<Game> games) {
		super();
		playerName = playerName;
		games = games;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}

	public String toString(){
		return playerName;
	}

	
	
//    public boolean equals(Object o){
//    	Player p = (Player)o;
//    	for(Game g : p.getGames()){
//    		return this.getGames().contains(g);
//    	}
//    	return false;
//    }
	@Override
	public int compareTo(Player p) {
		return playerName.compareTo(p.getPlayerName());
	}

	
}
