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

		System.out.println("- M�chtest du gerne eine kurze Einleitung in die Spielsteuerung erhalten? -");
		Thread.sleep(1000);

		Scanner userInputGame = new Scanner(System.in);
		System.out.println("+ Gebe daf�r [J] f�r JA ein oder [N] f�r NEIN +");
		while (!userInputGame.hasNext("[JNjn]")) {
		    System.out.println("+ Bitte gebe einen g�ltigen Buchstaben an +");
		    userInputGame.next();
		}
		String tutorial = userInputGame.next();
		
		if ("J".equalsIgnoreCase(tutorial)) {
		System.out.println("- Dieses Rollenspiel funktioniert ausschliesslich �ber die Konsole -");
		Thread.sleep(2000);
		System.out.println("- Alle S�tze haben ein Zeichen am Anfang und am Ende. Diese bedeuten folgendes:");
		Thread.sleep(2000);
		System.out.println("[-] Zeigt an, dass dies eine Information vom Spiel an dich ist. Es ist keine Aktion m�glich");
		System.out.println("[+] bedeutet, dass als n�chstes eine Eingabe von dir in der Konsole erwartet wird");
		System.out.println("");
		Thread.sleep(8000);
		System.out.println("- Das Spiel gibt dir in den meisten F�llen vor, was du eingeben kannst -");
		Thread.sleep(1000);
		System.out.println("- Diese M�glichkeiten werden in [eckigen Klammern] dargestellt -");
		System.out.println("");
		Thread.sleep(6000);
		}
		else if ("N".equalsIgnoreCase(tutorial)) {
			
		}
	}
		
	void createChar() throws InterruptedException {	
			System.out.println("+ Wie ist dein Name? +");
			Scanner userInputChar = new Scanner(System.in);
			String userName = userInputChar.nextLine();
			System.out.println("- Ein Hoch auf "+userName+", gesegnet sollst du sein! -");
			Thread.sleep(2000);
			System.out.println("- Da du dich zum ersten Mal in ein Abenteuer st�rzt, erstellen wir zuerst deinen Charakter -");
			Thread.sleep(4000);
			System.out.println("+ Was f�r ein Abenteurer m�chtest du sein? +");
			Thread.sleep(1000);
			System.out.println("[R] Ritter");
			System.out.println("[D] Dieb");
			System.out.println("[M] Magier");

			while (!userInputChar.hasNext("[RrDdMm]")) {
			    System.out.println("+ Bitte gebe einen g�ltigen Buchstaben an +");
			    userInputChar.next();
			}
			String userType = userInputChar.next();
			String userTypeName = "";
			int userTypeInt = 0;
			
			if ("R".equalsIgnoreCase(userType)) {userTypeName ="Ritter"; userTypeInt = 1;}
			else if ("D".equalsIgnoreCase(userType)) {userTypeName ="Dieb"; userTypeInt = 2;}
			else if ("M".equalsIgnoreCase(userType)) {userTypeName ="Magier"; userTypeInt = 3;}
			
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
			
			System.out.println("- Folgende Attribute hast du erhalten: -");
			System.out.println("[S] St�rke: "+playerS);
			System.out.println("[K] Kondition: "+playerK);
			System.out.println("[I] Intelligenz: "+playerI);
			System.out.println("[C] Charisma: "+playerC);
			Thread.sleep(2000);
			hero = new character(userName,0,1,playerAP,userTypeInt,playerType,playerS,playerK,playerI,playerC);
			
	}
	
	void adventureIntro() throws InterruptedException {
		//Statischer Starttext, gibt die Rahmenhandlung vor
		System.out.println("- Willkommen in Nordal, dem letzten Reich der Menschen. -");
		Thread.sleep(3500);
		System.out.println("- Vor einiger Zeit ist die Armee der Orks in Nordal eingefallen. -");
		Thread.sleep(3500);
		System.out.println("- Sie pl�ndern, brandschatzen und t�ten alles, was ihnen in die Quere kommt. -");
		Thread.sleep(3500);
		System.out.println("- Es geht das Ger�cht umher, dass ein dunkler Magier f�r das Auftauchen der Orks verantwortlich ist. -");
		Thread.sleep(3500);
		System.out.println("- Nordal ist dein Zuhause und du hast es dir zur Aufgabe gemacht, deine Heimat zu retten. -");
		Thread.sleep(3500);
		System.out.println("- Deine Reise soll dich zur Schwarzh�hle bringen - den Ger�chten nach befindet sich dort der dunkle Magier. -");
		Thread.sleep(3500);
		System.out.println("- Doch du weisst, die Strassen sind gef�hrlich und �berall lauern Gefahren auf dem Weg... -");
		Thread.sleep(3500);
		System.out.println("- Du bist "+hero.name+", ein unerschrockener "+hero.ptype+". -");
		Thread.sleep(2000);
		System.out.println("- Dies ist deine Geschichte... -");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		Thread.sleep(4000);
		System.out.println("- Nachdem du das n�tigste an Ausr�stung und Verpflegung eingepackt hast, machst du dich auf den Weg. -");
		Thread.sleep(1000);
		System.out.println("- Dein Weg f�hrt dich auf die Strasse in Richtung Norden. -");
	}
	
	void changePhase1to2() throws InterruptedException {
		System.out.println("- Endlich hast du die Kreuzung erreicht, an welcher du in den Wald abbiegen musst. -");
		Thread.sleep(3500);
		System.out.println("- Du verl�sst die Strasse in �stliche Richtung und begibst dich auf einen schmalen Pfad in den Wald. -");
		Thread.sleep(3500);
		System.out.println("- Kaum bist du unter das Bl�tterdach eingetreten, sp�rst du wie sich die Luft um dich �ndert. -");
		Thread.sleep(3500);
		System.out.println("- Alles ist stickiger und feuchter. �berall raschelt das Unterholz. -");
		Thread.sleep(3500);
		System.out.println("- Du w�nschst dir schon fast wieder die �bersichtliche Strasse zur�ck, aber nun gibt es kein Zur�ck mehr -");
		Thread.sleep(3500);
	}
	
	void changePhase2to3() throws InterruptedException {
		System.out.println("- Vom Wald in die H�hle -");
		Thread.sleep(3500);
	}
	
		
	void debugHero() throws InterruptedException {
		hero = new character("TEST",0,5,10,1,"Ritter",5,4,4,4);
	}
}