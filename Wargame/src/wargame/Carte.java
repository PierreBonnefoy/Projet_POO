package wargame;

import java.awt.Graphics;

public class Carte implements ICarte{
	private Element[][] carte;
	private int taille;
	
	public Carte(int n) {
		this.taille=n;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(Math.floorMod(j,2)==1 & i==0) {
					this.carte[i][j].setNature(Bord);
				}
				else {
					
				}
			}
		}
	}
	
	public Element getElement(Position pos) {
		/*-- Rtourne l'element à la position --*/
		return this.carte[pos.getX()][pos.getY()];
	}
	
	public Position trouvePositionVide() {
		/*-- Cherche une position, sur le plateau, libre aleatoirement --*/
		int aleaX,aleaY;
		do {
		aleaX=(int)(Math.random()*this.taille);
		aleaY=(int)(Math.random()*this.taille);
		}while(this.carte[aleaX][aleaY].getOccupe()!=0);
		return(new Position(aleaX,aleaY));
	}
	
	public Position trouvePositionVide(Position pos) {
		/*-- Vérification qu'il y ai au moins une place libre --*/
		boolean test=true;
		/*-- haut gauche --*/
		if(this.carte[pos.getX()][pos.getY()+1].getOccupe()!=0) {
			test=false;
		}
		/*-- haut droite --*/
		else if(this.carte[pos.getX()+1][pos.getY()+1].getOccupe()!=0) {
			test=false;
		}
		/*-- gauche --*/
		else if(this.carte[pos.getX()-1][pos.getY()].getOccupe()!=0) {
			test=false;
		}
		/*-- droite --*/
		else if(this.carte[pos.getX()+1][pos.getY()].getOccupe()!=0) {
			test=false;
		}
		/*-- bas gauche --*/
		else if(this.carte[pos.getX()][pos.getY()-1].getOccupe()!=0) {
			test=false;
		}
		/*-- haut droite --*/
		else if(this.carte[pos.getX()+1][pos.getY()-1].getOccupe()!=0) {
			test=false;
		}
		
		if(!test) {
			/*-- retour si aucune case n'est libre --*/
			return null;
		}
		
		/*-- Tirage d'une des 6 position possible --*/
		int alea,tmpX=0,tmpY=0;
		do{
			alea=(int)(Math.random()*6);
			switch(alea) {
				case 0 : 
					/*-- Case haut gauche --*/
					tmpX=(pos.getX()); 
					tmpY=(pos.getY()+1);
					break;
				case 1 : 
					/*-- Case haut droite --*/
					tmpX=(pos.getX()+1); 
					tmpY=(pos.getY()+1);
				case 2 : 
					/*-- Case gauche --*/
					tmpX=(pos.getX()-1); 
					tmpY=(pos.getY());
				case 3 : 
					/*-- Case droite --*/
					tmpX=(pos.getX()+1); 
					tmpY=(pos.getY());
				case 4 : 
					/*-- Case bas gauche --*/
					tmpX=(pos.getX()); 
					tmpY=(pos.getY()-1);
				case 5 : 
					/*-- Case bas gauche --*/
					tmpX=(pos.getX()+1); 
					tmpY=(pos.getY()-1);
				default :
					System.out.println("Erreur dans la recherche de case !");
			}
		}while((this.carte[tmpX][tmpY].getOccupe())!=0);
		return(new Position(tmpX,tmpY));
		
	}
	
	/*
	public Heros trouveHeros() {
		// Trouve aléatoirement un héros sur la carte
	}
	public Heros trouveHeros(Position pos) {
		// Trouve un héros choisi aléatoirement
	}
	*/

	public boolean deplaceSoldat(Position pos, Soldat soldat) {
		
	}
	public void mort(Soldat perso) {
		
	}
	public boolean actionHeros(Position pos, Position pos2) {
		
	}
	public void jouerSoldats(PanneauJeu pj) {
		
	}
	public void toutDessiner(Graphics g) {
		
	}
}
