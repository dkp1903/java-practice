package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import schedular.DayAlreadyScheduledException;
import schedular.GameAlreadyExistsException;
import schedular.InvalidGameException;
import schedular.PlayerAlreadyExistsException;
import schedular.Schedular;

public class TestAddGame {
	static Schedular sch = new Schedular();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sch.addGame("Khokho",6);
		sch.addGame("Kabaddi", 7);
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
	public void testAddNewGame() throws GameAlreadyExistsException {
		
		try {
			int nog = sch.getNumberGames();
			sch.addGame("cricket", 11);
			
			assertEquals("Bug in add game it should have added new game ",nog+1, sch.getNumberGames() );
			
			
		} catch (GameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	@Test
	public void testAddSameGame() throws GameAlreadyExistsException {
		
		try {
			int nog = sch.getNumberGames();
			sch.addGame("VOlleyBall", 7);
			sch.addGame("VOlleyBall", 7);
			assertEquals(nog+1, sch.getNumberGames() );
			
			
		} catch (GameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//	throw e;
		}
		
	}
	@Test
	public void testUniqueByNameAddGame() throws GameAlreadyExistsException {
		
		try {
			int nog= sch.getNumberGames();
			sch.addGame("Kabaddi", 8);
			assertEquals("Same Game got should not be added with different nop hashCode is not overridden based on name",nog, sch.getNumberGames() );
			
			
		} catch (GameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw e;
		}
		
	}
	@Ignore
public void testUniqueNotByNOPAddGame() throws GameAlreadyExistsException {
		
		try {
			int nog= sch.getNumberGames();
			sch.addGame("Tennis", 11);
			sch.addGame("Tennis", 11);
			assertEquals("Game was supposed to be added but not added : hashCode is not overridden based on name ",nog+1, sch.getNumberGames() );
			
			
		} catch (GameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw e;
		}
		
	}
	@Test
	public void testAddNewPlayerWithValidGames() {
		try {
			sch.addPlayer("vivek", new String[]{"Khokho","Kabaddi"});
		} catch (PlayerAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidGameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, sch.getNumberofPlayers());
	}
	@Test
	public void testAddNewPlayerWithInValidGames() throws Exception{
		int nop = sch.getNumberofPlayers();
		try {
			sch.addPlayer("Yogesh", new String[]{"VitiDandu","Kabaddi"});
		} catch (PlayerAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidGameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//	throw e;
		}
		assertEquals(nop, sch.getNumberofPlayers());
	}
	
	public void testAddPlayerUniquenessWithSameGame() {
		int nop = sch.getNumberofPlayers();
		try {
			sch.addPlayer("Yogesh", new String[]{"Khokho","Kabaddi"});
			nop=sch.getNumberofPlayers();
			sch.addPlayer("Yogesh", new String[]{"Kabaddi","Kabaddi"});
			assertEquals(nop, sch.getNumberofPlayers());
		} catch (PlayerAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertEquals(nop, sch.getNumberofPlayers());
		} catch (InvalidGameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertEquals(nop, sch.getNumberofPlayers());
		//	throw e;
		}
		
	}
	

	public void testAddPlayerUniquenessWithDifferentGames() {
		int nop = sch.getNumberofPlayers();
		try {
			try {
				sch.addGame("Tennis", 2);
			} catch (GameAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sch.addPlayer("Vivek", new String[]{"Khokho","Kabaddi"});
			nop=sch.getNumberofPlayers();
			sch.addPlayer("Vivek", new String[]{"VOlleyBall","Tennis"});
			assertEquals(nop, sch.getNumberofPlayers());
		} catch (PlayerAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertEquals(nop, sch.getNumberofPlayers());
		} catch (InvalidGameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertEquals(nop, sch.getNumberofPlayers());
		//	throw e;
		}
		
	}
	@Test
	public void testAddDaySchedule(){
		int nop = sch.getNumberofDays();
		int nog = sch.getNumberGames();
	
		System.out.println("DEBUG "+sch.getGames());
		try {
			try {
				sch.addGame("Tennis",6);
			} catch (GameAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	sch.addGame("Kabaddi", 7);
			sch.addSchedule("Monday", new String[]{"Tennis","Khokho"});
		//	assertEquals(nop+1,sch.getNumberofDays());
			sch.addSchedule("Tuesday", new String[]{"Tennis","Khokho"});
			//assertEquals(nop+2,sch.getNumberofDays());
			sch.addSchedule("Tuesday", new String[]{"Tennis","Khokho"});
			assertEquals(nop+3,sch.getNumberofDays());
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
	@Test
	public void testAddDayScheduleForExistingDay(){
		
		int nop = sch.getNumberofDays();
		try {
			sch.addSchedule("Wednesday", new String[]{"Tennis","Khokho"});
			
			sch.addSchedule("Wednesday", new String[]{"Tennis","Kabaddi"});
			assertEquals(nop+1,sch.getNumberofDays());
			assertEquals(3,sch.getDayNameScheduleMap().get("Wednesday").getGames().size());
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
