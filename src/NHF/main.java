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
	public static ArrayList<String> eventsPhase_1 = new ArrayList<String>();
	public static ArrayList<String> typesPhase_1 = new ArrayList<String>();
	
	public static void main(String[] args) throws InterruptedException {
		
		int debugger = 1;
		
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
		phase.executePhase(1,2); //Erste Spielphase starten, Argumente: (int Phase, int Rundenzahl)
		
		game.changePhase1to2(); //Statischer Text zum Wechsel der Umgebung
		game.saveGame(2); //Spiel speichern
		
		//phase.executePhase(2,2);
		//game.changePhase2to3();
		//phase.executePhase(3,2);
		
		
		
		
		
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
	
	public void selectPhase_1() {
		try {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM nhf_phase_1";
		ResultSet res = stmt.executeQuery(sql);
		
		//ArrayList<String> eventsPhase_1 = new ArrayList<String>();
		while(res.next())
		{
			String type = res.getString(2);
			String event = res.getString(3);
			//System.out.println(id);
			typesPhase_1.add(type);
			eventsPhase_1.add(event);
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
		
	    //System.out.println("Anzahl Monster: "+rowCount);
		Random x = new Random();
		int monsterID = x.nextInt(rowCount)+1;
		//System.out.println("MonsterID: "+monsterID);
		
		ResultSet result = stmt.executeQuery("SELECT * FROM "+sqlPhase+" WHERE id = '"+monsterID+"'");
		result.next();
		String name = result.getString(2);
		int hp = result.getInt(3);
		int ap = result.getInt(4);
		int xp = result.getInt(5);
		
		//System.out.println("Werte: "+name+", "+hp+", "+ap+", "+xp);
		
		enemy = new enemy(name,hp,ap,xp);
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
