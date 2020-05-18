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
	public static ArrayList<String> idsPhase;
	public static ArrayList<String> eventsPhase;
	public static ArrayList<String> typesPhase;
	
	public void executePhase(int phase, int rounds) throws InterruptedException {
		main m = new main();
		m.selectPhase(phase);
		
		
		for (int i=0; i<rounds; i++){
			int eventIndex = 0;
			
			if(main.idsPhase.size() == 1)
			{ eventIndex = 0;} else {
				Random x = new Random();
				eventIndex = x.nextInt(main.idsPhase.size()-1);
			}
			
			int eID = main.idsPhase.get(eventIndex);
			eventContent = main.eventsPhase.get(eventIndex);
			eventType = main.typesPhase.get(eventIndex);
			
					//Debug Block	
					//System.out.println(Arrays.toString(main.idsPhase.toArray()));
					//System.out.println("i: "+i+"/"+rounds+" Runden");
					//System.out.println("Event Index: "+eventIndex);
					//System.out.println("EventID: "+eID);
					//System.out.println("---------");
					
			
			switch(eventType) {
			  case "0":
				  executeFight(phase);
				  nextEvent();
			    break;
			  case "1":
				  executeDecision(phase,eID);
				  nextEvent();
			    break;
			  case "2":
				  executeStory(phase,eID);
				  nextEvent();
				break;
			}
			
			main.idsPhase.remove(eventIndex);
			main.eventsPhase.remove(eventIndex);
			main.typesPhase.remove(eventIndex);
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
		  //System.out.println("Deine Werte:");
		  //System.out.printf("[HP] "+flowClass.hero.hp+"%n[AP] "+flowClass.hero.ap+"%n[XP] "+flowClass.hero.xp+"%n");
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
					  flowClass game = new flowClass();
					  game.playerDead();
				  }
			  }
			  else {
				  System.out.println("- Der Angriff wird geblockt! -");
			  }
			  System.out.println("--------------------------------------------------");
		  }
	}
	
	public void executeDecision(int phase, int event) throws InterruptedException {
		System.out.printf(eventContent);
		System.out.println("");
		Thread.sleep(5000);
		Scanner userInputDecision = new Scanner(System.in);
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
	  	m.selectDecision(phase,event);
	  	int enoughSkill = 0;
		switch(main.decision.skill) {
		  case "s":
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
		
		if(enoughSkill == 1) {
			System.out.println(main.decision.success);
		} else {
			System.out.println(main.decision.fail);
			flowClass.hero.hp--;
			  if(flowClass.hero.hp <= 0) {
				  flowClass game = new flowClass();
				  game.playerDead();
			  }
		}
	  } else {
		  System.out.println("");
	  }
	  System.out.println("--------------------------------------------------");
	}
	
	public void executeStory(int phase, int event) throws InterruptedException {
	  System.out.printf(eventContent);
	  System.out.println("");
	  
	  main m = new main();  
	  m.selectStory(phase,event);
	  
		switch(main.story.skill) {
		  case "s":
			  flowClass.hero.strenght += main.story.value;
			  break;
		  case "c":
			  flowClass.hero.condition += main.story.value;
			  break;
		  case "i":
			  flowClass.hero.intelligence += main.story.value;
			  break;
		  case "ch":
			  flowClass.hero.charisma += main.story.value;
			  break;
		  case "hp":
			  flowClass.hero.hp += main.story.value;
			  break;	  
	  }
	System.out.println("--------------------------------------------------");
	Thread.sleep(3000);
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
		Thread.sleep(5000);
		}
		else if ("W".equalsIgnoreCase(nextEvent)) {{System.out.println("--------------------------------------------------");}
	}
  }
}