package schedular;

public class Game {
	private String gameName;
	private int noOfPlayers;
	
	Game(){
	}
	
                
	public Game(String gameName, int noOfPlayers) {
		this.gameName = gameName;
		this.noOfPlayers = noOfPlayers;
	}

	public String getGameName() {
		return gameName;
	}
	
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}
	
	
	public boolean equals(Object o){
		Game g = (Game)o;
		return gameName.equals(g.gameName);
	}
	
	public String toString(){
		return this.gameName;
	}
}
