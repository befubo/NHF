package NHF;
public class character {
		
	String name;
	int xp;
	int hp;
	int ap;
	int type;
	String ptype;
		
	int	strenght;
	int	condition;
	int	intelligence;
	int	charisma;
	
	character(String n, int x, int h, int a, int t, String pt, int s, int c, int i, int ch){
		name=n;
		xp=x;
		hp=h;
		ap=a;
		type=t;
		ptype=pt;
		strenght=s;
		condition=c;
		intelligence=i;
		charisma=ch;
	}
}
