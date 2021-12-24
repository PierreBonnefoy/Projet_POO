package wargame;

public class Element implements IConfig{
	/*-- Liste des variables --*/
	/*Element désigne la case du plateau*/
	private int occupe; /*un entier entre 0 et 3 qui indique l'occupation de la case. Si occupe = 3 : la case est pleine et rien ne peut aller dessus. Lorsqu'une unité va sur une case, il augmente occupe de sa Taille.*/
	private int nature; /*le type d'élément de terrain de la case (montagne, forêt, pleine,fossé)*/
	private Personnage[] personnage ; /*la liste des personnages qui sont sur la case (il y en as 3 maximum)*/
	private int[] etat; /*l'etat d'exploration de la case pour chacune des equipes*/
	
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