package schedular;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Schedular {
	private Set <Game> games = new HashSet<Game>();
	private Set <Player> players = new HashSet<Player>();
	private Set <DaySchedule> days = new HashSet<DaySchedule>();
	private Map<Game,TreeSet<DaySchedule>> gameDayMap = new HashMap<Game,TreeSet<DaySchedule>>();
	private Map<Game,TreeSet<Player>> gamePlayerMap ;
	private Map<String,DaySchedule> dayNameScheduleMap = new HashMap<String,DaySchedule>();

    
	public Map<String, DaySchedule> getDayNameScheduleMap() {
		return dayNameScheduleMap;
	}
	public void setDayNameScheduleMap(Map<String, DaySchedule> dayNameScheduleMap) {
		this.dayNameScheduleMap = dayNameScheduleMap;
	}
	public int getNumberGames(){
    	return games.size();
    }
    public int getNumberofPlayers(){
    	return players.size();
    }
    public int getNumberofDays(){
    	return days.size();
    }

	public String addGame(String name, int nop)throws GameAlreadyExistsException{
		Game g = new Game(name,nop);
		games.add(g);
		return null;
	}
	public String addSchedule(String dayName, String[] gameNames)throws PlayerAlreadyExistsException, InvalidGameException, DayAlreadyScheduledException{
		System.out.println("DEBUG : add schedule called");
		DaySchedule d = searchDay(dayName);
		if(d==null){
			//DaySchedule d = new DaySchedule(); This will throw nullPointer Exception
			d= new DaySchedule(dayName);
		}
		ArrayList<Game> games = d.getGames();
	    Set<Game> uniqueGames= new HashSet<Game>(games);
	    int originalnog = uniqueGames.size();
	    System.out.println("DEBUG "+uniqueGames);
		for(String game : gameNames){
			Game g = searchGame(game);
			if(g==null){
				System.out.println("GAME does not exist ?"+game+" "+this.games);
				System.exit(1);
				continue;
				
			}
			uniqueGames.add(g);
			System.out.println("DEBUG now "+uniqueGames);
			TreeSet<DaySchedule> daySchedules = gameDayMap.get(g);
			if(daySchedules==null){
				daySchedules = new TreeSet<DaySchedule>();
				
			}
		
		}
        if(originalnog==uniqueGames.size()){
			throw new DayAlreadyScheduledException("Day Already Scheduled");
        }
        games.clear();
        games.addAll(uniqueGames);
        d.setGames(games);
     
      
		return "SUCCESS";
	}
	private DaySchedule searchDay(String dayName) {
		for(DaySchedule day : this.days){
			if(day.getDayName().equals(dayName)){
				return day;
			}
		}
		return null;
	}
	public String addPlayer(String name, String[] gameNames)throws PlayerAlreadyExistsException, InvalidGameException{
		ArrayList<Game> games= new ArrayList<Game>();
		Player p = new Player(name,games);
		for(String game : gameNames){
			Game g = searchGame(game);
			if(g==null){
				players.add(p);
				throw new InvalidGameException(game+" does not exist ");
			}
			games.add(g);
			TreeSet<Player> players = gamePlayerMap.get(g);
			if(players==null){
				players=new TreeSet<Player>();
				
			}
		
		}
		
		//if(!players.add(p))
			//throw new PlayerAlreadyExistsException("Player Already Exists");
		return "SUCCESS";
	}

	public Set<Game> getGames() {
		return games;
	}
	public void setGames(Set<Game> games) {
		this.games = games;
	}
	private Game searchGame(String name) {
		for(Game g : this.games){
			if(g.getGameName().equals(name))
				return g;
		}
		return null;
	}
	public StringBuffer displayGamewiseSchedule(String gameName) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		Game g = searchGame(gameName);
		if(g==null)
		{
			sb.append(g+" is not part of the Schedule.");
			return sb;
		}
		if(gameDayMap.containsKey(g))
		{
			sb.append(g+" is played on ");
			sb.append("\n\t"+"Days:"+gameDayMap.get(g));
		}
		else{
			sb.append(g+" is not scheduled on any day...");
		}

		if(gamePlayerMap.containsKey(g)){
			sb.append("\n"+g+" is played by ");
			sb.append("\n\t"+"Players:"+gamePlayerMap.get(g));
		}
		else{
			sb.append("No player plays this game...");
		}
		return sb;
	}

	public StringBuffer displayPlayerwiseSchedule(String name){
		StringBuffer sb = new StringBuffer();
		
		if(!players.contains(new Player(name))){
			sb.append("No such Player exists ... ");
			return sb;
		}

		ArrayList<Player> players = new ArrayList<Player>(this.players);
        Player p = new Player(name,null);
		Collections.sort(players);
		int index = Collections.binarySearch(players,p);
		ArrayList<Game> games=players.get(index).getGames();
		Iterator<Game> it = games.iterator();

		sb.append(name+" plays ");
		while(it.hasNext()){
			Game g = it.next();
			sb.append("\n\t "+g+" on "+gameDayMap.get(g));
		}
		return sb;
	}

	public StringBuffer displayDaywiseSchedule(String name){
		StringBuffer sb = new StringBuffer();
		DaySchedule day = new DaySchedule(name);
		if(!days.contains(day)){
			sb.append("No such day exists in the schedule...");
			return sb;
		}

		ArrayList<DaySchedule> days= new ArrayList<DaySchedule>(this.days);
		
		Collections.sort(days);
		int index = Collections.binarySearch(days, day);
		ArrayList<Game>games =days.get(index).getGames();
		Iterator<Game> it = games.iterator();

		sb.append("On "+name);
		while(it.hasNext()){
			Game g = it.next();
			sb.append("\n\t"+g+" is played by "+gamePlayerMap.get(g));
		}
		return sb;
	}
}