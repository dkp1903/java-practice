package schedular;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class SchedularClient {

	/**
	 * @param args
	 */
	Schedular sch = new Schedular();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SchedularClient().showMenu();
	}
	private void showMenu() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("1. Add Games ");
			System.out.println("2. Add Players");
			System.out.println("3. Add Day Schedule");
			System.out.println("4. Display Gamewise Schedule");
			System.out.println("5. Display Playerwise Schedule");
			System.out.println("6. Display Daywise Schedule");
			System.out.println("7. Exit");

			int ch = UserInput.getInt();
			switch(ch)
			{
			case 1:
				acceptGameDetails();
				break;
			case 2:
				acceptPlayerDetails();
				break;
			case 3:
				acceptDayDetails();
				break;
			case 4:
				displayGameSchedule();
				break;
			case 5:
				displayPlayerschedule();
				break;
			case 6 :
				displayDaySchedule();
				break;
			case 7:
				System.exit(1);
			default:
				System.out.println("Invalid choice....");
			
			}

		}
	}
	private void displayDaySchedule() {
		// TODO Auto-generated method stub
		System.out.println("Enter name of the day :: ");
		String name = UserInput.getString();
		System.out.println(sch.displayDaywiseSchedule(name));
	}
	private void displayPlayerschedule() {
		// TODO Auto-generated method stub
		System.out.println("Enter the name of the player :: ");
		String name = UserInput.getString();
		System.out.println(sch.displayPlayerwiseSchedule(name));
	}
	private void displayGameSchedule() {
		// TODO Auto-generated method stub
		System.out.println("Enter the name of the Game :: ");
		String name = UserInput.getString();
		
		System.out.println(sch.displayGamewiseSchedule(name));
	}

	private void acceptDayDetails() {
		// TODO Auto-generated method stub
		System.out.println("How many days you want to add? ");
		int nod = UserInput.getInt();
		
		for(int i=0; i<nod; i++){
			System.out.println("Enter name of the day :: ");
			String dayName= UserInput.getString();
			System.out.println("How many games are to be scheduled on this day ? ");
			int nog = UserInput.getInt();
			String[] games = new String[nog];
			for(int j=0;j<nog;j++){
				System.out.println("Enter the game :: ");
				String gameName=UserInput.getString();
				
				games[j] = gameName;
			}
			try {
				sch.addSchedule(dayName, games);
				System.out.println("Successfully added Schedule");
			} catch (PlayerAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidGameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DayAlreadyScheduledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

	private void acceptPlayerDetails() {
		// TODO Auto-generated method stub
		System.out.println("How Many Players you want to add? ");
		int nop = UserInput.getInt();
		
		for(int i=0;i<nop;i++){
			System.out.println("Enter name of the player  :: ");
			String name = UserInput.getString();
			System.out.println("How many games this player plays? ");
			int nog = UserInput.getInt();
			String[] games = new String[nog];
			for(i=0;i<nog;i++)
			{
				System.out.println("enter Game name :: ");
				String gameName = UserInput.getString();
				games[i] = gameName;
				
			}
			try {
				sch.addPlayer(name, games);
				System.out.println("Player Added succssfully");
			} catch (PlayerAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidGameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

	private void acceptGameDetails() {
		// TODO Auto-generated method stub
		System.out.println("How many Games you want to add? ");
		int nog = UserInput.getInt();
		
		for(int i = 0; i<nog; i++){
			System.out.println("Enter game name :: ");
			String gameName = UserInput.getString();
			System.out.println("How many players are required to play this game? ");
			int nop = UserInput.getInt();
		
			try {
				sch.addGame(gameName, nop);
				System.out.println("Game added succssfully");
			} catch (GameAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
