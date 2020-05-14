package NHF;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class main {	
	Connection con = null;
	public static character hero;
	
	public static void main(String[] args) throws InterruptedException {
		/*
		System.out.println("Select DB");
		main m = new main();
		m.selectAll();
		System.out.println("Sleep");
		Thread.sleep(15000);
		System.out.println("Exit");
		Thread.sleep(2000);
		*/
		
		
		flowClass game = new flowClass();
		game.startGame();
		
		game.saveGame();
		
		Thread.sleep(4000);
		
		//System.out.println("Stärke des Charakters: "+flowClass.hero.strenght);
		
		
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
		System.out.println("~ Nachdem du das nötigste an Ausrüstung und Verpflegung eingpackt hast, machst du dich auf den Weg. ~");
		Thread.sleep(1000);
		System.out.println("~ Dein Weg führt dich auf die Strasse in Richtung Norden. ~");
		
		main m = new main();
		m.selectPhase_1();
		
		// IDs in Array schreiben
		// zufällige ID ziehen
		// Entsprechendes Event darstellen
		// ID aus Array entfernen
		
		// Abfrage: Kampf, Entscheidung oder Story
		// WENN Kampf: Methode Kampf aufrufen
		// WENN Entscheidung: Methode Entscheidung aufrufen (gelinkt mit ID des Events)
		// WENN Story: Nichts tun
		
		
		
		
		
		
		
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
	public void selectPhase_1() {
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
}
