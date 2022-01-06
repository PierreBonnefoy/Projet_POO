package wargame;

public class Element implements IConfig,java.io.Serializable{
	/*-- Liste des variables --*/
	/*Element désigne la case du plateau*/
	private int occupe; /*un entier entre 0 et 3 qui indique l'occupation de la case. Si occupe = 3 : la case est pleine et rien ne peut aller dessus. Lorsqu'une unité va sur une case, il augmente occupe de sa Taille.*/
	private int nature; /*le type d'élément de terrain de la case (montagne, forêt, pleine,fossé)*/
	private Personnage[] personnage ; /*la liste des personnages qui sont sur la case (il y en as 3 maximum)*/
	private int[] etat; /*l'etat d'exploration de la case pour chacune des equipes*/
	
	public Element() {
		int n;
		n=(int)(Math.random()*3);
		this.occupe = 0;
		this.nature = n;
		this.personnage = new Personnage[3];
		this.etat = new int[NBJOUEUR];
		for(int i = 0 ; i < 2 ; i++) {
			this.etat[i] = NON_DECOUVERT;
		}
		for(int i = 0 ; i < 3 ; i++) {
			this.personnage[i] = null;
		}
	}
	
	public Element(int n) {
		this.occupe = 0;
		this.nature = n;
		this.personnage = new Personnage[3];
		this.etat = new int[2];
		for(int i = 0 ; i < 2 ; i++) {
			this.etat[i] = NON_DECOUVERT;
		}
		for(int i = 0 ; i < 3 ; i++) {
			this.personnage[i] = null;
		}
	}
	
	public void rajoutPersonnage(Personnage perso) {
		int i;
		if(this.occupe+perso.getTaille()<=3) {
			this.occupe+=perso.getTaille();
			for(i=0;this.personnage[i]!=null;i++) {
			}
			this.personnage[i]=perso;
		}
		else {
			System.out.println("Pas assez de place pour venir");
		}
	}
	
	public Personnage getPersonnage(int i) {
		/*renvoi le personnage à l'indice i s'il existe dans l'element, ou null sinon*/
		if(this.personnage[i]!=null) {
			return this.personnage[i];
		}
		return null;
	}
	
	public void enleverPersonnage(int id, int joueur) {
		/*---On enlève le personnage possédant la bonne id et le bon joueur---*/
		int i;
		for(i = 0;this.personnage[i].getId()!=id && this.personnage[i].getJoueur()!=joueur;i++) {
		}
		this.occupe-=this.personnage[i].getTaille();
		this.personnage[i]=null;
	}
	
	public int getOccupe() {
		return this.occupe;
	}
	
	public void setOccupe(int n) {
		this.occupe = n;
	}
	/*le reste est à faire*/
	
	/*-- Ajoute pour la map --*/
	
	public int getNature() {
		return this.nature;
	}
	public void setNature(int n) {
		this.nature=n;
	}
	
}