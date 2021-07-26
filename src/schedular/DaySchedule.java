package schedular;
import java.util.ArrayList;

public class DaySchedule implements Comparable<DaySchedule> {
	private String dayName;
	private ArrayList<Game> games = new ArrayList<Game>();
                public int compareTo(DaySchedule schedule){
                           return dayName.compareTo(schedule.dayName);
                }
	DaySchedule(){
	}

	public DaySchedule(String dayName) {
		super();
		this.dayName = dayName;
	}

	public DaySchedule(String dayName, ArrayList<Game> games) {
		super();
		this.dayName = dayName;
		this.games = games;
	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}
	
	public String toString(){
		return dayName;
	}
	
	

}
