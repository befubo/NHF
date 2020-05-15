package NHF;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class phase {
	public static character hero;
	public static enemy enemy;
	public static decision decision;
	public static String eventContent;
	public static String eventType;
	public static int eventID;
	public static ArrayList<String> eventsPhase;
	public static ArrayList<String> typesPhase;
	
	public void executePhase(int phase, int rounds) throws InterruptedException {
		main m = new main();
		m.selectPhase(phase);
		
		
		for (int i=0; i<rounds; i++){ //Anzahl der Events, welche in Phase 1 getriggert werden sollen (MAX: 30)

			Random x = new Random();
			eventID = x.nextInt(main.eventsPhase.size()-1);
			
			//eventID = 19; //DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG
			
			eventContent = main.eventsPhase.get(eventID);
			eventType = main.typesPhase.get(eventID);
			
			
			switch(eventType) {
			  case "0":
				  executeFight(phase);
				  nextEvent();
			    break;
			  case "1":
				  executeDecision(phase);
				  nextEvent();
			    break;
			  case "2":
				  executeStory(phase);
				  nextEvent();
				break;
			}
			main.eventsPhase.remove(eventID);
			main.typesPhase.remove(eventID);
		}
	}
	
	public void executeFight(int phase) throws InterruptedException {
		  Scanner userInputFight = new Scanner(System.in);
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
			  dicePlayer = userInputFight.nextInt();
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
					  System.exit(0);
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
		
		Scanner userInputDecision = new Scanner(System.in);
		System.out.println("+ Gebe dafür [J] für JA ein oder [N] für NEIN +");
		while (!userInputDecision.hasNext("[JNjn]")) {
		    System.out.println("+ Bitte gebe einen gültigen Buchstaben an +");
		    userInputDecision.next();
		}
		String decPlay = userInputDecision.next();
		
		int dec = 0;
		if ("J".equalsIgnoreCase(decPlay)) {dec = 1;}
		else if ("N".equalsIgnoreCase(decPlay)) {dec = 0;}
	  
		if(dec == 1) {
		main m = new main();  
	  	m.selectDecision(phase,eventID);
	  	int enoughSkill = 0;
	  	int playerValue = 0;
		switch(main.decision.skill) {
		  case "s":
			  playerValue = flowClass.hero.strenght;
			  if(main.decision.value > flowClass.hero.strenght) {
				  Random randX = new Random();
				  if(randX.nextInt(20) > main.decision.value) {
					  enoughSkill = 1; 
					  flowClass.hero.strenght++;
				  }
			  } else {
				  enoughSkill = 1;
				  flowClass.hero.strenght++;
			  }
		    break;
		  case "c":
			  playerValue = flowClass.hero.condition;
			  if(main.decision.value > flowClass.hero.condition) {
				  Random randX = new Random();
				  if(randX.nextInt(20) > main.decision.value) {
					  enoughSkill = 1; 
					  flowClass.hero.condition++;
				  }
			  } else {
				  enoughSkill = 1;
				  flowClass.hero.condition++;
			  }
		    break;
		  case "i":
			  playerValue = flowClass.hero.intelligence;
			  if(main.decision.value > flowClass.hero.intelligence) {
				  Random randX = new Random();
				  if(randX.nextInt(20) > main.decision.value) {
					  enoughSkill = 1; 
					  flowClass.hero.intelligence++;
				  }
			  } else {
				  enoughSkill = 1;
				  flowClass.hero.intelligence++;
			  }
			break;
		  case "ch":
			  playerValue = flowClass.hero.charisma;
			  if(main.decision.value > flowClass.hero.charisma) {
				  Random randX = new Random();
				  if(randX.nextInt(20) > main.decision.value) {
					  enoughSkill = 1; 
					  flowClass.hero.charisma++;
				  }
			  } else {
				  enoughSkill = 1;
				  flowClass.hero.charisma++;
			  }
			break;
		}
		
		System.out.println("Benötigter Skill: "+main.decision.skill);
		System.out.println("Benötigter Wert: "+main.decision.value);
		System.out.println("Spieler Wert: "+playerValue);
		
		if(enoughSkill == 1) {
			System.out.println(main.decision.success);
		} else {
			System.out.println(main.decision.fail);
			flowClass.hero.hp--;
		}
	  } else {
		  System.out.println("");
	  }
	  System.out.println("--------------------------------------------------");
	}
	
	public void executeStory(int phase) throws InterruptedException {
	  System.out.printf(eventContent);
	  System.out.println("");
	  System.out.println("- Auswirkungen der Entscheidung: -");
	  System.out.println("");
	  
	  main m = new main();  
	  m.selectStory(phase,eventID);
	  
		switch(main.story.skill) {
		  case "s":
			  flowClass.hero.strenght += main.story.value;
			  System.out.println("Anpassung Stärke: "+main.story.value);
			  break;
		  case "c":
			  flowClass.hero.condition += main.story.value;
			  System.out.println("Anpassung Kondition: "+main.story.value);
			  break;
		  case "i":
			  flowClass.hero.intelligence += main.story.value;
			  System.out.println("Anpassung Intelligenz: "+main.story.value);
			  break;
		  case "ch":
			  flowClass.hero.charisma += main.story.value;
			  System.out.println("Anpassung Charisma: "+main.story.value);
			  break;
		  case "hp":
			  flowClass.hero.hp += main.story.value;
			  System.out.println("Anpassung HP: "+main.story.value);
			  break;	  
	  }
	System.out.println("--------------------------------------------------");
	}
	
	public void nextEvent() throws InterruptedException {
		
		Scanner userInputEvent = new Scanner(System.in);
		System.out.println("+ [W]eiterfahren / [C]harakterstatistik +");
		while (!userInputEvent.hasNext("[WwCc]")) {
		    System.out.println("+ Bitte gebe einen gültigen Buchstaben an +");
		    userInputEvent.next();
		}
		String nextEvent = userInputEvent.next();

		if ("C".equalsIgnoreCase(nextEvent)) {
		System.out.println("[HP] Lebenspunkte: "+flowClass.hero.hp);
		System.out.println("[XP] Erfahrungspunkte: "+flowClass.hero.xp);
		System.out.println("[S] Stärke: "+flowClass.hero.strenght);
		System.out.println("[K] Kondition: "+flowClass.hero.condition);
		System.out.println("[I] Intelligenz: "+flowClass.hero.intelligence);
		System.out.println("[C] Charisma: "+flowClass.hero.charisma);
		System.out.println("");
		}
		else if ("W".equalsIgnoreCase(nextEvent)) {{System.out.println("--------------------------------------------------");}
	}
  }
}
