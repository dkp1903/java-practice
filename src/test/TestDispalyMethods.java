package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import schedular.Schedular;

public class TestDispalyMethods {
	static Schedular sch = new Schedular();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sch.addGame("Cricket", 11);
		sch.addGame("Tennis", 2);
		sch.addGame("Khokho", 6);
		sch.addPlayer("Shreeram", new String[]{"Tennis","Khokho"});
		sch.addPlayer("Shreekrishna", new String[]{"Tennis","Cricket"});
		sch.addSchedule("Monday", new String[]{"Cricket"});
		sch.addSchedule("Tuesday", new String[]{"Khokho","Tennis"});
		sch.addSchedule("Wednesday", new String[]{"Tennis"});
		sch.addSchedule("Thursday", new String[]{"Cricket","Khokho"});
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDisplayGamewiseScheduleCheckifDaysAreCorrect() {
		String report = sch.displayGamewiseSchedule("Khokho").toString();
		System.out.println("DEBUG : report gamewise for Khokho "+report);
		int firstIndexDay = report.indexOf("[");
		assertFalse("Khokho Not Shown as scheduled eventhough it is ",firstIndexDay==-1);
		int lastIndexDay  = report.indexOf("]");
		String days = report.substring(firstIndexDay+1, lastIndexDay);
		String[] dayList = days.split(",");
		assertEquals("Khokho is only Played on 2 days ",2,dayList.length);
		assertEquals(true,dayList[0].trim().equals("Tuesday")? dayList[1].trim().equals("Thursday"):(dayList[0].trim().equals("Thursday")&& dayList[1].trim().equals("Tuesday")));
	}
	@Test
	public void testDisplayGamewiseScheduleCheckifPlayersAreCorrect() {
		
		String[] playerList = getPlayerListForAGame("Khokho");
	//	assertEquals("Khokho is only Played by 1 Player ",1,playerList.length);
		assertEquals("Shreeram",playerList[0]);
		String[] playerListTennis = getPlayerListForAGame("Tennis");
		assertEquals("Tennis is  Played by 2 Players ",2,playerListTennis.length);
		assertTrue(playerListTennis[0].trim().equals("Shreeram")? playerListTennis[1].trim().equals("Shreekrishna"):(playerListTennis[0].trim().equals("Shreekrishna")&& playerListTennis[1].trim().equals("Shreeram")));
		
		
	}
	private String[] getPlayerListForAGame(String game){
		String report = sch.displayGamewiseSchedule(game).toString();
		System.out.println("DEBUG : report gamewise for "+game+"  "+report);
		int firstIndexPlayer = report.indexOf("Players:");
		assertFalse(" No One is playing this game "+game,firstIndexPlayer==-1);
		int lastIndexPlayer = report.lastIndexOf("]");
		String players = report.substring(firstIndexPlayer+9, lastIndexPlayer);
		String[] playerList = players.split(",");
		for(String playerName : playerList){
			System.out.println("Name "+playerName);
		}
		
		return playerList;
	}

	@Test
	public void testDisplayPlayerwiseSchedulecheckIfDaysAreCorrect() {
		String report = sch.displayPlayerwiseSchedule("Shreeram").toString();
		boolean dayResult = (report.indexOf("Tuesday")!=-1) && (report.indexOf("Wednesday") !=-1)&&(report.indexOf("Thursday")!=-1)&& (report.indexOf("Monday")==-1); 
		assertEquals("days are not correct",true,dayResult );
	
		
	}
	@Test
	public void testDisplayPlayerwiseSchedulecheckIfGamesAreCorrect() {
		String report = sch.displayPlayerwiseSchedule("Shreeram").toString();
		System.out.println("DEBUG SHREERAM's REPORT "+report);
		boolean gameResult = (report.indexOf("Tennis")!=-1) && (report.indexOf("Khokho") !=-1)&&(report.indexOf("Cricket")==-1);
		assertEquals("Games are not correct",true,gameResult );
		
	}

	@Test
	public void testDisplayDaywiseSchedulecheckIfPlayersAreCorrect() {
		String report = sch.displayDaywiseSchedule("Monday").toString();
		boolean playerResult = report.indexOf("Shreekrishna") != -1 && report.indexOf("Shreeram")==-1;
		assertTrue("Players displayed are not correct ", playerResult);
	}
	@Test
	public void testDisplayDaywiseSchedulecheckIfGamesAreCorrect() {
		String report = sch.displayDaywiseSchedule("Monday").toString();
		boolean gameResult = report.indexOf("Cricket") != -1 && report.indexOf("Khokho")==-1 && report.indexOf("Tennis")==-1;
		assertTrue("Games displayed are not correct ", gameResult);
	}

}
