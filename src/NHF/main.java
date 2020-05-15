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
	public static enemy enemyPhase_1;
	public static ArrayList<String> eventsPhase_1 = new ArrayList<String>();
	public static ArrayList<String> typesPhase_1 = new ArrayList<String>();
	
	public static void main(String[] args) throws InterruptedException {
		flowClass game = new flowClass();
		//game.startGame();
		game.saveGame();
		//Thread.sleep(4000);
		
		if(false) {
		//Statischer Starttext, gibt die Rahmenhandlung vor
		System.out.println("- Willkommen in Nordal, dem letzten Reich der Menschen. -");
		Thread.sleep(2000);
		System.out.println("- Vor einiger Zeit ist die Armee der Orks in Nordal eingefallen. -");
		Thread.sleep(2000);
		System.out.println("- Sie plündern, brandschatzen und töten alles, was ihnen in die Quere kommt. -");
		Thread.sleep(4000);
		System.out.println("- Es geht das Gerücht umher, dass ein dunkler Magier für das Auftauchen der Orks verantwortlich ist. -");
		Thread.sleep(2000);
		System.out.println("- Nordal ist dein Zuhause und du hast es dir zur Aufgabe gemacht, deine Heimat zu retten. -");
		Thread.sleep(2000);
		System.out.println("- Deine Reise soll dich zur Schwarzhöhle bringen - den Gerüchten nach befindet sich dort der dunkle Magier. -");
		Thread.sleep(2000);
		System.out.println("- Doch du weisst, die Strassen sind gefährlich und überall lauern Gefahren auf dem Weg... -");
		Thread.sleep(2000);
		System.out.println("- Du bist "+flowClass.hero.name+", ein unerschrockener "+flowClass.hero.ptype+". -");
		Thread.sleep(1000);
		System.out.println("- Dies ist deine Geschichte... -");
		System.out.println("--------------------------------------------------");
		Thread.sleep(6000);
		System.out.println("- Nachdem du das nötigste an Ausrüstung und Verpflegung eingepackt hast, machst du dich auf den Weg. -");
		Thread.sleep(1000);
		System.out.println("- Dein Weg führt dich auf die Strasse in Richtung Norden. -");
		};
		
		
		main m = new main();
		m.selectPhase_1();
		Scanner userInput = new Scanner(System.in);
		
		for (int i=0; i<3; i++){ //Anzahl der Events, welche in Phase 1 getriggert werden sollen (MAX: 30)

			Random x = new Random();
			int eventID = x.nextInt(eventsPhase_1.size());
			
			eventID = 0; //DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG
			
			String eventContent = eventsPhase_1.get(eventID);
			String eventType = typesPhase_1.get(eventID);
			
			
			switch(eventType) {
			  case "0":
				  m.selectMonster_1();
				  System.out.printf(eventContent);
				  System.out.println("");
				  System.out.println("- Ein "+enemyPhase_1.name+" steht vor dir! -");
				  System.out.println("--------------------------------------------------");
				  Thread.sleep(3000);
				  System.out.println("+++ Kampf beginnt! +++");
				  Thread.sleep(2000);
				  System.out.println("");
				  System.out.println("Deine Werte:");
				  System.out.printf("[HP] "+flowClass.hero.hp+"%n[AP] "+flowClass.hero.ap+"%n[XP] "+flowClass.hero.xp+"%n");
				  System.out.println("- Du würfelst und versuchst näher an eine zufällige Zahl zu kommen als der Gegner -");
				  
				  int attackTrue = 0;
				  while(attackTrue != 1) {
					  System.out.println("+ Gebe eine Zahl zwischen [0] und [20] ein +");
					  
					  
					  int diceTrue = 0;
					  int dicePlayer = 0;
					  while(diceTrue != 1) {
					  dicePlayer = userInput.nextInt();
						if (dicePlayer >= 0 && dicePlayer <= 20) { diceTrue = 1;}
						else {System.out.println("+ Falsche Zahl eingeben! +");};
					  }
					  Thread.sleep(1000);
					 
					  Random diceX = new Random();
					  int diceNPC = diceX.nextInt(19)+1;
					  //System.out.println("- NPC würfelt "+diceNPC+" -");
					  
					  Random combatX = new Random();
					  int diceSystem = combatX.nextInt(19)+1;
					  //System.out.println("- Die Zahl ist: "+diceSystem+" -");
					  
					  int diffPlayer = Math.abs(dicePlayer - diceSystem);
					  int diffNPC = Math.abs(diceNPC - diceSystem);
					  
					  //System.out.println("- Unterschied Spieler: "+diffPlayer+" -");
					  //System.out.println("- Unterschied NPC: "+diffNPC+" -");
					  
					  int apNPC = enemyPhase_1.ap;
					  int hpNPC = enemyPhase_1.hp;
					  
					  int apPlayer = flowClass.hero.ap;
					  int hpPlayer = flowClass.hero.hp;
					  	int sPlayer = flowClass.hero.strenght;
					  	int cPlayer = flowClass.hero.condition;
					  int blockPlayer = sPlayer + cPlayer;
					  double xpBonus = flowClass.hero.xp / 100;
					  
					  if(diffPlayer < diffNPC) {
						  Random attackX = new Random();
						  int attackPlayer = attackX.nextInt(apPlayer-1)+1;
						  
						  double playerDamage = (xpBonus + 1) * attackPlayer;
						  int attackPlayerDamage = (int) Math.round(playerDamage);
						  enemyPhase_1.hp -= attackPlayerDamage;
						  System.out.println("- Du hat den Gegner getroffen! -");
						  
						  
						  if(enemyPhase_1.hp <= 0) {
							  attackTrue = 1;
							  System.out.println("- Du hat den Gegner erledigt! -");
							  System.out.println("- Erhaltene Erfahungspunkte: "+enemyPhase_1.xp+" -");
							  flowClass.hero.xp += enemyPhase_1.xp;
						  } else {
							  System.out.println("- Er hat noch ["+enemyPhase_1.hp+"] -");
						  }
					  }
					  else if(diffPlayer > diffNPC) {
						  Random attackY = new Random();
						  int attackNPC = attackY.nextInt(apNPC-1)+1;
						  if(blockPlayer > hpNPC) {
							  attackNPC /= 2;
						  }
						  flowClass.hero.hp -= attackNPC;
						  System.out.println("- Du wurdest getroffen! -");
						  System.out.println("[HP] "+flowClass.hero.hp);
						  
						  if(flowClass.hero.hp <= 0) {
							  attackTrue = 1;
						  }
					  }
					  else {
						  System.out.println("- Der Angriff wird geblockt! -");
					  }
					  System.out.println("--------------------------------------------------");
				  }
				  				
			    break;
			  case "1":
				  //System.out.println("Eventtyp: Entscheidung");
			    break;
			  case "2":
				  //System.out.println("Eventtyp: Story");
				break;
			}
			eventsPhase_1.remove(eventID);
			typesPhase_1.remove(eventID);
		}
		userInput.close();
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public main() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			return;
		}
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:hsqldb\\data\\data; shutdown=false", "bossaben", "SWEng");
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
	
	
	public void selectMonster_1() {
		try {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM nhf_monster_1";
		ResultSet res = stmt.executeQuery(sql);
		res.next();
		
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM nhf_monster_1");
		rs.next();
	    int rowCount = rs.getInt(1);
		
	    //System.out.println("Anzahl Monster: "+rowCount);
		Random x = new Random();
		int monsterID = x.nextInt(rowCount)+1;
		//System.out.println("MonsterID: "+monsterID);
		
		ResultSet result = stmt.executeQuery("SELECT * FROM nhf_monster_1 WHERE id = '"+monsterID+"'");
		result.next();
		String name = result.getString(2);
		int hp = result.getInt(3);
		int ap = result.getInt(4);
		int xp = result.getInt(5);
		
		//System.out.println("Werte: "+name+", "+hp+", "+ap+", "+xp);
		
		enemyPhase_1 = new enemy(name,hp,ap,xp);
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void selectAll() {
		try {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM nhf_monster";
		ResultSet res = stmt.executeQuery(sql);
		
		while(res.next())
		{
			String id = res.getString(1);
			String firstname = res.getString(2);
			String lastname = res.getString(3);
			System.out.println(id+", "+firstname+" "+lastname);
		}
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	
	/*
	public void updateChar() {
		try {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM nhf_character";
		ResultSet res = stmt.executeQuery(sql);
		
		res.close();
		stmt.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	*/
}
