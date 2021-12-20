package wargame;

public class Element {
	/*-- Liste des variables --*/
	/*Element désigne la case du plateau*/
	private int occupe; /*un entier entre 0 et 3 qui indique l'occupation de la case. Si occupe = 3 : la case est pleine et rien ne peut aller dessus. Lorsqu'une unité va sur une case, il augmente occupe de sa Taille.*/
	private int nature; /*la liste des ID des personnages qui sont sur la case (il y en as 3 maximum)*/
	private int[] ids ; /*le type d'élément de terrain de la case (montagne, forêt, pleine,fossé)*/
	
	public Element(int n) {
		this.occupe = 0;
		this.nature = n;
		this.ids = new int[3];
		for(int i = 0 ; i < 3 ; i++) {
			this.ids[i] = 0;
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