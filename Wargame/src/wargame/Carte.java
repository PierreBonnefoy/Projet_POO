package wargame;

import java.awt.Graphics;

public class Carte implements ICarte{
	private Element[][] carte;
	private int taille;
	
	public Carte(int n) {
		this.taille=n;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(Math.floorMod(i,2)==1 & j==0) {
					carte[i][j]=new Element(Bord);
				}
				else {
					/*Création des riviers(entre 1 et 3(modifiables par la suite))*/
					for(int k=0;k<((int)Math.random()*3)+1;k++) {
						int positionDebutx=(int)Math.random()*n;
						int positionDebuty=(int)Math.random()*n;
						/*Taile de chaque rivière,entre 1 et taille/2*/
						for(int l=0;l<((int)Math.random()*(n/2))+1;l++) {
							carte[positionDebutx][positionDebuty]=new Element(Riviere);
							positionDebutx=((int)Math.random()*2)-1;
							positionDebuty=((int)Math.random()*2)-1;
						}
					}
					
					/*Création des montagne (entre 1 et 3 aussi a modifié)*/
					for(int k=0;k<((int)Math.random()*3)+1;k++) {
						int positionDebutx=(int)Math.random()*n;
						int positionDebuty=(int)Math.random()*n;
						/*Taile de chaque Montagne,entre 1 et taille/2*/
						for(int l=0;l<((int)Math.random()*(n/2))+1;l++) {
							carte[positionDebutx][positionDebuty]=new Element(Montagne);
							positionDebutx=((int)Math.random()*2)-1;
							positionDebuty=((int)Math.random()*2)-1;
						}
					}
					
					/*Création des Forets (entre 1 et 3)*/
					for(int k=0;k<((int)Math.random()*3)+1;k++) {
						int positionDebutx=(int)Math.random()*n;
						int positionDebuty=(int)Math.random()*n;
						/*Taile de chaque foret,entre 1 et taille/2*/
						for(int l=0;l<((int)Math.random()*(n/2))+1;l++) {
							carte[positionDebutx][positionDebuty]=new Element(Foret);
							positionDebutx=((int)Math.random()*2)-1;
							positionDebuty=((int)Math.random()*2)-1;
						}
					}
					
					/*Remplissage du reste avec plaine*/
					for(int k=0;k<n;k++) {
						for(int l=0;l<n;l++) {
							if(carte[k][l]==null) {
								carte[k][l]=new Element(Plaine);
							}
						}
					}
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
		/*-- Vérification qu'il y ait au moins une place libre --*/
		boolean test=false;
		/*-- haut gauche --*/
		if(this.carte[pos.getX()][pos.getY()+1].getOccupe()==0) {
			test=true;
		}
		/*-- haut droite --*/
		else if(this.carte[pos.getX()+1][pos.getY()+1].getOccupe()==0) {
			test=true;
		}
		/*-- gauche --*/
		else if(this.carte[pos.getX()-1][pos.getY()].getOccupe()==0) {
			test=true;
		}
		/*-- droite --*/
		else if(this.carte[pos.getX()+1][pos.getY()].getOccupe()==0) {
			test=true;
		}
		/*-- bas gauche --*/
		else if(this.carte[pos.getX()][pos.getY()-1].getOccupe()==0) {
			test=true;
		}
		/*-- haut droite --*/
		else if(this.carte[pos.getX()+1][pos.getY()-1].getOccupe()==0) {
			test=true;
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
	public boolean deplaceSoldat(Position pos, Soldat soldat) {
		
	}
	public boolean actionHeros(Position pos, Position pos2) {
		
	}
	public void jouerSoldats(PanneauJeu pj) {
		
	}
	public void mort(Personnage perso) {
		
	}*/
	
	
	public void toutDessiner(Graphics g) {
		
	}
}
