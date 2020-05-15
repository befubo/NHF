package NHF;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class main {	
	Connection con = null;
	public static character hero;
	public static enemy enemy;
	public static decision decision;
	public static story story;
	public static ArrayList<String> eventsPhase;
	public static ArrayList<String> typesPhase;
	
	public static void main(String[] args) throws InterruptedException {
		
		int debugger = 0; // DEBUG-MODE, 0=OFF, 1=ON
		
		flowClass game = new flowClass();
		
		if(debugger == 1) {
			game.debugHero();
		} else {
			game.startGame(); //Intro mit Titelbild und Begrüssung
			//Check for Savegame
			game.createChar(); //Charaktererstellung
			game.saveGame(1); //Spiel speichern
			Thread.sleep(4000);
			game.adventureIntro(); //Statischer Introtext für das Abenteuer
		}
		
		phase phase = new phase();
		phase.executePhase(1,3); //Erste Spielphase starten, Argumente: (int Phase, int Rundenzahl)
		
		game.changePhase1to2(); //Statischer Text zum Wechsel der Umgebung
		game.saveGame(2); //Spiel speichern
		
		phase.executePhase(2,3); //Zweite Spielphase starten, Argumente: (int Phase, int Rundenzahl)
		
		game.changePhase2to3(); //Statischer Text zum Wechsel der Umgebung
		game.saveGame(3); //Spiel speichern
		
		phase.executePhase(3,3); //Dritte Spielphase starten, Argumente: (int Phase, int Rundenzahl)

		//BOSSFIGHT
		System.out.println("ENDE");
		
		
		
		
		
	}
	
	public main() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			return;
		}
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:hsqldb/data/data; shutdown=true", "bossaben", "SWEng");
		} catch (SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	
	public void selectPhase(int phase) {
		String sqlPhase = "";
		switch(phase) {
		  case 1:
			  sqlPhase = "nhf_phase_1";
		    break;
		  case 2:
			  sqlPhase = "nhf_phase_2";
		    break;
		  case 3:
			  sqlPhase = "nhf_phase_3";
			break;
		}
		try {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM "+sqlPhase+"";
		ResultSet res = stmt.executeQuery(sql);
		
		eventsPhase = new ArrayList<String>();
		typesPhase = new ArrayList<String>();
		
		while(res.next())
		{
			String type = res.getString(2);
			String event = res.getString(3);
			typesPhase.add(type);
			eventsPhase.add(event);
		}
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void selectMonster(int phase) {
		String sqlPhase = "";
		switch(phase) {
		  case 1:
			  sqlPhase = "nhf_monster_1";
		    break;
		  case 2:
			  sqlPhase = "nhf_monster_2";
		    break;
		  case 3:
			  sqlPhase = "nhf_monster_3";
			break;
		}
		
		try {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM "+sqlPhase+"";
		ResultSet res = stmt.executeQuery(sql);
		res.next();
		
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM "+sqlPhase+"");
		rs.next();
	    int rowCount = rs.getInt(1);
		
		Random x = new Random();
		int monsterID = x.nextInt(rowCount)+1;
		
		ResultSet result = stmt.executeQuery("SELECT * FROM "+sqlPhase+" WHERE id = '"+monsterID+"'");
		result.next();
		String name = result.getString(2);
		int hp = result.getInt(3);
		int ap = result.getInt(4);
		int xp = result.getInt(5);
		
		enemy = new enemy(name,hp,ap,xp);
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void selectDecision(int phase, int eventID) {
		String sqlPhase = "";
		switch(phase) {
		  case 1:
			  sqlPhase = "nhf_decision_1";
		    break;
		  case 2:
			  sqlPhase = "nhf_decision_2";
		    break;
		  case 3:
			  sqlPhase = "nhf_decision_3";
			break;
		}
		
		try {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM "+sqlPhase+" WHERE id = '"+eventID+"'";
		ResultSet res = stmt.executeQuery(sql);
		res.next();
		
		String skill = res.getString(2);
		int value = res.getInt(3);
		String success = res.getString(4);
		String fail = res.getString(5);
		
		decision = new decision(skill,value,success,fail);
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void selectStory(int phase, int eventID) {
		String sqlPhase = "";
		switch(phase) {
		  case 1:
			  sqlPhase = "nhf_story_1";
		    break;
		  case 2:
			  sqlPhase = "nhf_story_2";
		    break;
		  case 3:
			  sqlPhase = "nhf_story_3";
			break;
		}
		
		try {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM "+sqlPhase+" WHERE id = '"+eventID+"'";
		ResultSet res = stmt.executeQuery(sql);
		res.next();
		
		String skill = res.getString(2);
		int value = res.getInt(3);
		
		story = new story(skill,value);
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
