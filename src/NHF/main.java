package NHF;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;
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
	public static save save;
	public static ArrayList<Integer> idsPhase;
	public static ArrayList<String> eventsPhase;
	public static ArrayList<String> typesPhase;
	
	public static void main(String[] args) throws InterruptedException {
		phase phase = new phase();
		flowClass game = new flowClass();
		
		game.startGame(); //Intro mit Titelbild und Begrüssung
		game.createChar(); //Charaktererstellung
		
		//game.debugHero();
				//m.gameSave(flowClass.hero.name,1); //Spiel speichern
				//m.updateChar(flowClass.hero.name,flowClass.hero.xp,flowClass.hero.hp,flowClass.hero.ap,flowClass.hero.type,flowClass.hero.ptype,flowClass.hero.strenght,flowClass.hero.condition,flowClass.hero.intelligence,flowClass.hero.charisma);
		Thread.sleep(4000);
		game.adventureIntro(); //Statischer Introtext für das Abenteuer
		Thread.sleep(4000);
		
		phase.executePhase(1,5); //Erste Spielphase starten, Argumente: (int Phase (1-3), int Rundenzahl (max 30))
		
		game.changePhase1to2(); //Statischer Text zum Wechsel der Umgebung
				//m.gameSave(flowClass.hero.name,2); //Spiel speichern
				//m.updateChar(flowClass.hero.name,flowClass.hero.xp,flowClass.hero.hp,flowClass.hero.ap,flowClass.hero.type,flowClass.hero.ptype,flowClass.hero.strenght,flowClass.hero.condition,flowClass.hero.intelligence,flowClass.hero.charisma);
		
		phase.executePhase(2,5); //Zweite Spielphase starten, Argumente: (int Phase (1-3), int Rundenzahl (max 30))
		
		game.changePhase2to3(); //Statischer Text zum Wechsel der Umgebung
				//m.gameSave(flowClass.hero.name,3); //Spiel speichern
				//m.updateChar(flowClass.hero.name,flowClass.hero.xp,flowClass.hero.hp,flowClass.hero.ap,flowClass.hero.type,flowClass.hero.ptype,flowClass.hero.strenght,flowClass.hero.condition,flowClass.hero.intelligence,flowClass.hero.charisma);
		
		phase.executePhase(3,5); //Dritte Spielphase starten, Argumente: (int Phase (1-3), int Rundenzahl (max 30))

		//BOSSFIGHT TBD
		
		  System.out.println(" ___                                               ");
		  System.out.println("-   ---___- ,,                  ,- _~,        |\\   ");
		  System.out.println("   (' ||    ||                 (' /| /         \\\\  ");
		  System.out.println("  ((  ||    ||/\\\\  _-_        ((  ||/= \\\\/\\\\  / \\\\");
		  System.out.println(" ((   ||    || || || \\\\       ((  ||   || || || || ");
		  System.out.println("  (( //     || || ||/          ( / |   || || || || ");
		  System.out.println("    -____-  \\\\ |/ \\\\,/          -____- \\\\ \\\\  \\\\/  ");
		  System.out.println("              _/                                   ");
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
		
		idsPhase = new ArrayList<Integer>();
		eventsPhase = new ArrayList<String>();
		typesPhase = new ArrayList<String>();
		
		while(res.next())
		{
			int id = res.getInt(1);
			String type = res.getString(2);
			String event = res.getString(3);
			idsPhase.add(id);
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
	
	public void selectDecision(int phase, int event) {
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
		String sql = "SELECT * FROM "+sqlPhase+" WHERE id = '"+event+"'";
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
	
	public void selectStory(int phase, int event) {
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
		String sql = "SELECT * FROM "+sqlPhase+" WHERE id = '"+event+"'";
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
	
	public void newChar(String n, int x, int h, int a, int t, String pt, int s, int c, int i, int ch) {
		try {
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO nhf_character VALUES (1, '"+n+"', '"+x+"', '"+h+"', '"+a+"', '"+t+"', '"+s+"', '"+c+"', '"+i+"', '"+ch+"', '"+pt+"')";
		ResultSet res = stmt.executeQuery(sql);
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadChar() {
		try {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM nhf_character WHERE id = 1";
		ResultSet res = stmt.executeQuery(sql);
		res.next();
		
		String userName = res.getString(2);
		int xp = res.getInt(3);
		int playerHP = res.getInt(4);
		int playerAP = res.getInt(5);
		int userTypeInt = res.getInt(6);
		int playerS = res.getInt(7);
		int playerK = res.getInt(8);
		int playerI = res.getInt(9);
		int playerC = res.getInt(10);
		String playerType = res.getString(11);
		
		hero = new character(userName,xp,playerHP,playerAP,userTypeInt,playerType,playerS,playerK,playerI,playerC);
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateChar(String n, int x, int h, int a, int t, String pt, int s, int c, int i, int ch) {
		try {
		Statement stmt = con.createStatement();
		String sql = "UPDATE nhf_character SET name = '"+n+"', xp = '"+x+"', hp = '"+h+"', ap = '"+a+"', type = '"+t+"', ptype = '"+pt+"', strenght = '"+s+"', condition = '"+c+"', intelligence = '"+i+"', charisma = '"+ch+"'  WHERE id = 1";
		ResultSet res = stmt.executeQuery(sql);
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteChar() {
		try {
		Statement stmt = con.createStatement();
		String sql = "DELETE FROM nhf_character WHERE id = 1";
		ResultSet res = stmt.executeQuery(sql);
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void gameSave(String n, int p) {
		System.out.println("");
		System.out.println("[[[Spiel gespeichert]]]");
		System.out.println("");
		try {
		Statement stmt = con.createStatement();
		String sql = "UPDATE nhf_savegame SET name = '"+n+"', phase = '"+p+"' WHERE id = 1";
		ResultSet res = stmt.executeQuery(sql);
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void checkSave() {
		
		try {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM nhf_savegame WHERE id = 1";
		ResultSet res = stmt.executeQuery(sql);
		res.next();
		
		String name = res.getString(2);
		int phase = res.getInt(3);
		
		save = new save(name,phase);
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
