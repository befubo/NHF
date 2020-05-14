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
		System.out.println("~ Willkommen in Nordal, dem letzten Reich der Menschen. ~");
		Thread.sleep(2000);
		System.out.println("~ Vor einiger Zeit ist die Armee der Orks in Nordal eingefallen. ~");
		Thread.sleep(2000);
		System.out.println("~ Sie plündern, brandschatzen und töten alles, was ihnen in die Quere kommt. ~");
		Thread.sleep(4000);
		System.out.println("~ Es geht das Gerücht umher, dass ein dunkler Magier für das Auftauchen der Orks verantwortlich ist. ~");
		Thread.sleep(2000);
		System.out.println("~ Nordal ist dein Zuhause und du hast es dir zur Aufgabe gemacht, deine Heimat zu retten. ~");
		Thread.sleep(2000);
		System.out.println("~ Deine Reise soll dich zur Schwarzhöhle bringen - den Gerüchten nach befindet sich dort der dunkle Magier. ~");
		Thread.sleep(2000);
		System.out.println("~ Doch du weisst, die Strassen sind gefährlich und überall lauern Gefahren auf dem Weg... ~");
		Thread.sleep(2000);
		System.out.println("~ Du bist "+flowClass.hero.name+", ein unerschrockener "+flowClass.hero.ptype+". ~");
		Thread.sleep(1000);
		System.out.println("~ Dies ist deine Geschichte... ~");
		System.out.println("--------------------------------------------------");
		Thread.sleep(6000);
		System.out.println("~ Nachdem du das nötigste an Ausrüstung und Verpflegung eingepackt hast, machst du dich auf den Weg. ~");
		Thread.sleep(1000);
		System.out.println("~ Dein Weg führt dich auf die Strasse in Richtung Norden. ~");
		};
		
		
		main m = new main();
		m.selectPhase_1();
		
		for (int i=0; i<1; i++){ //Anzahl der Events, welche in Phase 1 getriggert werden sollen (MAX: 30)

			Random x = new Random();
			int eventID = x.nextInt(eventsPhase_1.size());
			String eventContent = eventsPhase_1.get(eventID);
			String eventType = typesPhase_1.get(eventID);
			
			eventType = "0"; //Immer ein Kampfevent Triggern -> DEBUG!!!!!!
			
			switch(eventType) {
			  case "0":
				  m.selectMonster_1();
				  System.out.println("~ "+eventContent+" ~");
				  System.out.println("~ Ein "+enemyPhase_1.name+" steht vor dir! ~");
				  
				
			    break;
			  case "1":
				  //System.out.println("Eventtyp: Entscheidung");
				
				
				
			    break;
			  case "2":
				  //System.out.println("Eventtyp: Story");
				
				
				
				break;
			}
			
			
			System.out.println("--------------------------------------------------");
			
			eventsPhase_1.remove(eventID);
			typesPhase_1.remove(eventID);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public main() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			return;
		}
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:hsqldb\\data\\data; shutdown=true", "bossaben", "SWEng");
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
