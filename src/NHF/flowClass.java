package NHF;
import java.util.Scanner;

public class flowClass {
	public static character hero;
	void startGame() throws InterruptedException {
		System.out.println("                                                                   _ ,                        ");
		System.out.println("                   |\\         ,,       ,,                        ,- -       ,, ,,             ");
		System.out.println("                    \\\\    _   ||       ||      _                _||_    _   || ||             ");
		System.out.println("\\\\/\\\\  /'\\\\ ,._-_  / \\\\  < \\, ||       ||/\\\\  < \\,  _-_,       ' ||    < \\, || ||  _-_  \\\\/\\\\ ");
		System.out.println("|| || || ||  ||   || ||  /-|| ||       || ||  /-|| ||_.          ||    /-|| || || || \\\\ || || ");
		System.out.println("|| || || ||  ||   || || (( || ||       || || (( ||  - ||         |,   (( || || || ||/   || || ");
		System.out.println("\\\\ \\\\ \\\\,/   \\\\,   \\\\/   \\/\\\\ \\\\       \\\\ |/  \\/\\\\ ,-_-        _-/     \\/\\\\ \\\\ \\\\ \\\\,/  \\\\ \\\\ ");
		System.out.println("                                         _/                                                   ");
		System.out.println("");
		
		Thread.sleep(2000);
		System.out.println("- Willkommen beim textbasierten Rollenspiel \"Nordal has fallen\" von Benjamin Bossart -");
		Thread.sleep(2000);
		System.out.println("+ Wie ist dein Name? +");
		Scanner userInput = new Scanner(System.in);
		String userName = userInput.nextLine();
		System.out.println("- Ein Hoch auf "+userName+", gesegnet sollst du sein! -");
		Thread.sleep(2000);
		System.out.println("- Möchtest du gerne eine kurze Einleitung in die Spielsteuerung erhalten? -");
		Thread.sleep(1000);
		System.out.println("+ Gebe dafür [J] für JA ein oder [N] für NEIN +");
		String tutorial = userInput.nextLine();
		int chooseTut = 0;
		while(chooseTut != 1) {
		
		if ("J".equalsIgnoreCase(tutorial)) {chooseTut = 1;
		
		System.out.println("- Dieses Rollenspiel funktioniert ausschliesslich über die Konsole -");
		Thread.sleep(2000);
		System.out.println("- Alle Sätze haben ein Zeichen am Anfang und am Ende. Diese bedeuten folgendes:");
		Thread.sleep(2000);
		System.out.println("[-] Zeigt an, dass dies eine Information vom Spiel an dich ist. Es ist keine Aktion möglich");
		System.out.println("[+] bedeutet, dass als nächstes eine Eingabe von dir in der Konsole erwartet wird");
		System.out.println("");
		Thread.sleep(8000);
		System.out.println("- Das Spiel gibt dir in den meisten Fällen vor, was du eingeben kannst -");
		Thread.sleep(1000);
		System.out.println("- Diese Möglichkeiten werden in [eckigen Klammern] dargestellt -");
		Thread.sleep(1000);
		System.out.println("- Es geht nun mit der Charaktererstellung weiter. Viel Spass! -");
		System.out.println("");
		Thread.sleep(6000);
	
		}
		else if ("N".equalsIgnoreCase(tutorial)) {chooseTut = 1;}
		else {System.out.println("+ Bitte gebe einen gültigen Buchstaben an +");}
		}
		
		//ABFRAGE, OB EIN SPEICHERSTAND VORHANDEN IST ODER NICHT
		
		if(true) {
			System.out.println("- Da du dich zum ersten Mal in ein Abenteuer stürzt, erstellen wir zuerst deinen Charakter -");
			Thread.sleep(4000);
			System.out.println("+ Was für ein Abenteurer möchtest du sein? +");
			Thread.sleep(1000);
			System.out.println("[R] Ritter");
			System.out.println("[D] Dieb");
			System.out.println("[M] Magier");
			int validType = 0;
			String userTypeName = "";
			int userTypeInt = 0;
			while(validType != 1) {
				String userType = userInput.nextLine();
				if ("R".equalsIgnoreCase(userType)) {validType = 1; userTypeName ="Ritter"; userTypeInt = 1;}
				else if ("D".equalsIgnoreCase(userType)) {validType = 1; userTypeName ="Dieb"; userTypeInt = 2;}
				else if ("M".equalsIgnoreCase(userType)) {validType = 1; userTypeName ="Magier"; userTypeInt = 3;}
				else {System.out.println("+ Bitte gebe einen gültigen Buchstaben an +");};
			}
			System.out.println("- Ah, ein neuer "+userTypeName+" in unseren Reihen! -");
			Thread.sleep(2000);
		    int playerS = 0;
		    int playerK = 0;
		    int playerI = 0;
		    int playerC = 0;
		    int playerAP = 0;
		    String playerType = "";
		    
			switch(userTypeInt) {
			  case 1:
				playerS = 8;
				playerK = 7;
				playerI = 5;
				playerC = 5;
				playerType = "Ritter";
				playerAP = 10;
			    break;
			  case 2:
				playerS = 5;
				playerK = 8;
				playerI = 6;
				playerC = 7;
				playerType = "Dieb";
				playerAP = 8;
			    break;
			  case 3:
				playerS = 5;
				playerK = 6;
				playerI = 8;
				playerC = 7;
				playerType = "Magier";
				playerAP = 8;
				break;
			}
			playerAP = playerS*playerK;
			
			System.out.println("- Folgende Attribute hast du erhalten: -");
			System.out.println("[S] Stärke: "+playerS);
			System.out.println("[K] Kondition: "+playerK);
			System.out.println("[I] Intelligenz: "+playerI);
			System.out.println("[C] Charisma: "+playerC);
			Thread.sleep(6000);
			int addPoints = 2;
			System.out.println("- Du kannst jetzt noch "+addPoints+" Punkte selber verteilen -");
			System.out.println("+ Nacheinander kannst du nachfolgend angeben, wie viele Punkt zu welchem Attribut hinzugefügt werden sollen +");
			
			System.out.println("[S] Stärke:");
			int addTrue = 0;
			while(addTrue != 1) {
				int addS = userInput.nextInt();
				if (addS <= addPoints) { addPoints -= addS; playerS +=addS;addTrue = 1;}
				else {System.out.println("+ Nicht genügend Punkte übrig +");};
			}
			addTrue = 0;
			System.out.println("[K] Kondition:");
			while(addTrue != 1) {
				int addK = userInput.nextInt();
				if (addK <= addPoints) { addPoints -= addK; playerK +=addK;addTrue = 1;}
				else {System.out.println("+ Nicht genügend Punkte übrig +");};
			}
			addTrue = 0;
			System.out.println("[I] Intelligenz:");
			while(addTrue != 1) {
				int addI = userInput.nextInt();
				if (addI <= addPoints) { addPoints -= addI; playerI +=addI;addTrue = 1;}
				else {System.out.println("+ Nicht genügend Punkte übrig +");};
			}
			addTrue = 0;
			System.out.println("[C] Charisma:");
			while(addTrue != 1) {
				int addC = userInput.nextInt();
				if (addC <= addPoints) { addPoints -= addC; playerC +=addC;addTrue = 1;}
				else {System.out.println("+ Nicht genügend Punkte übrig +");};
			}
			
			hero = new character(userName,0,30,playerAP,userTypeInt,playerType,playerS,playerK,playerI,playerC);
			
		}
		userInput.close();
	}
	
	void saveGame() throws InterruptedException {
		hero = new character("TEST",0,30,10,1,"Ritter",8,7,6,6); //DEBUG USER
		System.out.println("- Spiel gespeichert -");
		System.out.println("--------------------------------------------------");
		// Spielstand in DB speichern
	}
		
	
	
	
}


