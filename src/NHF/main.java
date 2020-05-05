package NHF;
public class main {
	public static void main(String[] args) throws InterruptedException {

		flowClass game = new flowClass();
		game.startGame();
		
		System.out.println("Spiel initialisiert. Lade Spielwelt...");
		Thread.sleep(6000);
		
		System.out.println("Stärke des Charakters: "+flowClass.hero.strenght);
		
		
		
	/*
	enemy eins = new enemy("Goblin", 10, 2, 15);
	
	System.out.println("Gegner: "+eins.name);
	System.out.println("HP: "+eins.hp);
	System.out.println("AP: "+eins.ap);
	System.out.println("XP: "+eins.xp);
	*/
	}

}
