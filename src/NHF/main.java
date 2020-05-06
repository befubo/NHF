package NHF;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class main {	
	Connection con = null;
	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Select DB");
		main m = new main();
		m.selectAll();
		System.out.println("Sleep");
		Thread.sleep(15000);
		System.out.println("Exit");
		Thread.sleep(2000);
		
		
		/*
		flowClass game = new flowClass();
		game.startGame();
		
		System.out.println("Spiel initialisiert. Lade Spielwelt...");
		Thread.sleep(6000);
		
		System.out.println("Stärke des Charakters: "+flowClass.hero.strenght);
		*/
	}
	
	public main() {
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error");
			return;
		}
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:hsqldb\\data\\data; shutdown=true", "bossaben", "SWEng");
		} catch (SQLException e) {
			System.out.println("Error");
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
			System.out.println("Error");
			e.printStackTrace();
		}
	}
}
