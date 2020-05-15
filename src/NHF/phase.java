package NHF;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class phase {
	public static character hero;
	public static enemy enemy;
	public static String eventContent;
	public static String eventType;
	public static ArrayList<String> eventsPhase_1;
	public static ArrayList<String> typesPhase_1;
	
	public void executePhase(int phase, int rounds) throws InterruptedException {
		
		main m = new main();
		m.selectPhase_1();
		
		
		for (int i=0; i<rounds; i++){ //Anzahl der Events, welche in Phase 1 getriggert werden sollen (MAX: 30)

			Random x = new Random();
			int eventID = x.nextInt(main.eventsPhase_1.size());
			
			//eventID = 0; //DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG
			
			eventContent = main.eventsPhase_1.get(eventID);
			eventType = main.typesPhase_1.get(eventID);
			
			
			switch(eventType) {
			  case "0":
				  executeFight(phase);
				  //Entscheidung
			    break;
			  case "1":
				  executeDecision(phase);
				  //Entscheidung
			    break;
			  case "2":
				  executeStory(phase);
				  //Story
				break;
			}
			main.eventsPhase_1.remove(eventID);
			main.typesPhase_1.remove(eventID);
		}
	}
	
	
	public void executeFight(int phase) throws InterruptedException {
		  Scanner userInput = new Scanner(System.in);
		  main m = new main();  
		  m.selectMonster(phase);
		  System.out.printf(eventContent);
		  System.out.println("");
		  Thread.sleep(10000);
		  System.out.println("- Ein "+main.enemy.name+" steht vor dir! -");
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
			  
			  int apNPC = main.enemy.ap;
			  int hpNPC = main.enemy.hp;
			  
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
				  main.enemy.hp -= attackPlayerDamage;
				  System.out.print("- Du hat den Gegner getroffen, ");
				  
				  
				  if(main.enemy.hp <= 0) {
					  attackTrue = 1;
					  System.out.print("er ist erledigt! -");
					  System.out.println("");
					  System.out.println("- Erhaltene Erfahungspunkte: "+main.enemy.xp+" -");
					  flowClass.hero.xp += main.enemy.xp;
				  } else {
					  System.out.print("er hat noch ["+main.enemy.hp+"] HP -");
					  System.out.println("");
				  }
			  }
			  else if(diffPlayer > diffNPC) {
				  Random attackY = new Random();
				  int attackNPC = attackY.nextInt(apNPC-1)+1;
				  if(blockPlayer > hpNPC) {
					  attackNPC /= 2;
				  }
				  flowClass.hero.hp -= attackNPC;
				  System.out.print("- Du wurdest getroffen, ");
				  System.out.print("du hast noch "+flowClass.hero.hp+" [HP] -");
				  System.out.println("");
				  
				  if(flowClass.hero.hp <= 0) {
					  System.out.print("leider bist du tot! -");
					  attackTrue = 1;
				  }
			  }
			  else {
				  System.out.println("- Der Angriff wird geblockt! -");
			  }
			  System.out.println("--------------------------------------------------");
		  }
		}	
	
	
	public void executeDecision(int phase) throws InterruptedException {
	  System.out.printf(eventContent);
	  System.out.println("");
	  System.out.println("+ Entscheidung treffen +");
	  System.out.println("");
	  System.out.println("--------------------------------------------------");
	}
	
	public void executeStory(int phase) throws InterruptedException {
	  System.out.printf(eventContent);
	  System.out.println("");
	  System.out.println("- Auswirkungen der Entscheidung: -");
	  System.out.println("");
	  System.out.println("--------------------------------------------------");
	}

}
