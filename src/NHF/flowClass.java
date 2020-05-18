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

		System.out.println("- Möchtest du gerne eine kurze Einleitung in die Spielsteuerung erhalten? -");
		Thread.sleep(1000);

		Scanner userInputGame = new Scanner(System.in);
		System.out.println("+ Gebe dafür [J] für JA ein oder [N] für NEIN +");
		while (!userInputGame.hasNext("[JNjn]")) {
		    System.out.println("+ Bitte gebe einen gültigen Buchstaben an +");
		    userInputGame.next();
		}
		String tutorial = userInputGame.next();
		
		if ("J".equalsIgnoreCase(tutorial)) {
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
		System.out.println("");
		Thread.sleep(1000);
		System.out.println("- Es kann vorkommen, dass du Entscheidungen treffen musst. Diese sind mit [J] oder [N] zu beantworten -");
		Thread.sleep(1000);
		System.out.println("- Wenn du dich dazu entscheidest, eine Aktion auszuführen wird geprüft, ob der benötigte Skill");
		System.out.println("hoch genug ist. Der geprüfte Skill wird im text in [eckigen Klammern] dargestellt. -");
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
			System.out.println("- Da du dich zum ersten Mal in ein Abenteuer stürzt, erstellen wir zuerst deinen Charakter -");
			Thread.sleep(4000);
			System.out.println("+ Was für ein Abenteurer möchtest du sein? +");
			Thread.sleep(1000);
			System.out.println("[R] Ritter (Sehr stark, nicht besonders schlau)");
			System.out.println("[D] Dieb (Nicht besonders stark, durchschnittlich schlau, charismatisch");
			System.out.println("[M] Magier (Sehr klug, körperlich eher schwach");

			while (!userInputChar.hasNext("[RrDdMm]")) {
			    System.out.println("+ Bitte gebe einen gültigen Buchstaben an +");
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
			System.out.println("[S] Stärke: "+playerS);
			System.out.println("[K] Kondition: "+playerK);
			System.out.println("[I] Intelligenz: "+playerI);
			System.out.println("[C] Charisma: "+playerC);
			Thread.sleep(2000);
			hero = new character(userName,0,30,playerAP,userTypeInt,playerType,playerS,playerK,playerI,playerC);
			System.out.println("");
			
	}
	
	void adventureIntro() throws InterruptedException {
		//Statischer Starttext, gibt die Rahmenhandlung vor
		System.out.println("- Willkommen in Nordal, dem letzten Reich der Menschen. -");
		Thread.sleep(3500);
		System.out.println("- Vor einiger Zeit ist die Armee der Orks in Nordal eingefallen. -");
		Thread.sleep(3500);
		System.out.println("- Sie plündern, brandschatzen und töten alles, was ihnen in die Quere kommt. -");
		Thread.sleep(3500);
		System.out.println("- Es geht das Gerücht umher, dass ein dunkler Magier für das Auftauchen der Orks verantwortlich ist. -");
		Thread.sleep(3500);
		System.out.println("- Nordal ist dein Zuhause und du hast es dir zur Aufgabe gemacht, deine Heimat zu retten. -");
		Thread.sleep(3500);
		System.out.println("- Deine Reise soll dich zur Schwarzhöhle bringen - den Gerüchten nach befindet sich dort der dunkle Magier. -");
		Thread.sleep(3500);
		System.out.println("- Doch du weisst, die Strassen sind gefährlich und überall lauern Gefahren auf dem Weg... -");
		Thread.sleep(3500);
		System.out.println("- Du bist "+hero.name+", ein unerschrockener "+hero.ptype+". -");
		Thread.sleep(2000);
		System.out.println("- Dies ist deine Geschichte... -");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		Thread.sleep(4000);
		System.out.println("- Nachdem du das nötigste an Ausrüstung und Verpflegung eingepackt hast, machst du dich auf den Weg. -");
		Thread.sleep(2000);
		System.out.println("- Dein Weg führt dich auf die Strasse in Richtung Norden. -");
	}
	
	void changePhase1to2() throws InterruptedException {
		System.out.println("- Endlich hast du die Kreuzung erreicht, an welcher du in den Wald abbiegen musst. -");
		Thread.sleep(3500);
		System.out.println("- Du verlässt die Strasse in östliche Richtung und begibst dich auf einen schmalen Pfad in den Wald. -");
		Thread.sleep(3500);
		System.out.println("- Kaum bist du unter das Blätterdach eingetreten, spürst du wie sich die Luft um dich ändert. -");
		Thread.sleep(3500);
		System.out.println("- Alles ist stickiger und feuchter. Überall raschelt das Unterholz. -");
		Thread.sleep(3500);
		System.out.println("- Du wünschst dir schon fast wieder die übersichtliche Strasse zurück, aber nun gibt es kein Zurück mehr -");
		Thread.sleep(3500);
	}
	
	void changePhase2to3() throws InterruptedException {
		System.out.println("- Du erblickst vor dir eine grosse Felsformation, links und rechts rauschen Wasserfälle herab. -");
		Thread.sleep(3500);
		System.out.println("- Du tritts näher und erkennst am Fusse der Formation einen Höleneingang. -");
		Thread.sleep(3500);
		System.out.println("- Bei genaueren Untersuchung des Eingangs erkennst du das Zeichen des dunklen Magiers auf dem Felsen. -");
		Thread.sleep(3500);
		System.out.println("- Das muss der Ort sein! -");
		Thread.sleep(3500);
		System.out.println("- Du atmest einmal tief ein und kletterst anschliessend entschlossen in die Höhle. -");
		Thread.sleep(3500);
	}
	
	void playerDead() throws InterruptedException {
		  System.out.println("- Leider bist du tot! -");
		  Thread.sleep(1500);
		  System.out.println("");
		  System.out.println("- Vielen Dank fürs Spielen, probier es doch gleich nochmals :) -");
		  Thread.sleep(3500);
		  System.out.print("");
		  
		  System.out.println("    __ ,                                 __                      ");
		  System.out.println("  ,-| ~                                ,-||-,                    ");
		  System.out.println(" ('||/__,   _                         ('|||  )  ;                ");
		  System.out.println("(( |||  |  < \\, \\\\/\\\\/\\\\  _-_        (( |||--)) \\\\/\\  _-_  ,._-_ ");
		  System.out.println("(( |||==|  /-|| || || || || \\\\       (( |||--)) || | || \\\\  ||   ");
		  System.out.println(" ( / |  , (( || || || || ||/          ( / |  )  || | ||/    ||   ");
		  System.out.println("  -____/   \\/\\\\ \\\\ \\\\ \\\\ \\\\,/          -____-   \\\\/  \\\\,/   \\\\,  ");
		  
		  Thread.sleep(5000);
		  System.out.println("");
		  System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		  System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		  Thread.sleep(1500);
		  System.out.println("");
		  main m = new main();
		  main.main(null);
	}
	
		
	void debugHero() throws InterruptedException {
		hero = new character("TEST",0,5,10,1,"Ritter",5,4,4,4);
	}
	
	
	
	
}