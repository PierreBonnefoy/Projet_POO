package wargame;
import java.awt.Graphics;

public class Carte implements ICarte{
	private Element[][] carte;
	private int taille;
	
	public Carte(int n) {
		int tmp1,tmp2;
		carte=new Element[n][n];
		this.taille=n;
		for(int i=0;i<n;i+=2) {
			carte[0][i]=new Element(Bord);
		}
		
		/*Remplissage avec la plaine*/
		for (int k = 0; k < n; k++) {
			for (int l = 0; l < n; l++) {
				if (carte[k][l] == null) {
					carte[k][l] = new Element(Plaine);
				}
			}
		}
		
		/*Nombre de riviere*/
		tmp1=((int)Math.random()*2)+1;
		for(int k=0;k<tmp1;k++) {
			int l =(int) Math.random()*(n-1);
			int positiony =(int) Math.random()*(n-1);
			tmp2=(int)(Math.random()*((n-1) - l)) + l;
			/*Taille de la riviere(droite pour l'instant*/
			for(l=l;l<tmp2;l++) {
				carte[l][positiony]=new Element(Riviere);
			}
		}
		
		System.out.println("Fin de la création de la carte");
	}
	
	public Element getElement(Position pos) {
		/*-- Rtourne l'element à la position --*/
		return this.carte[pos.getX()][pos.getY()];
	}
	
	public Position trouvePositionVide() {
		/*-- Cherche une position, sur le plateau, libre aleatoirement --*/
		int aleaX,aleaY;
		do {
		aleaX=(int)(Math.random()*this.taille-1);
		aleaY=(int)(Math.random()*this.taille-1);
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
			alea=(int)(Math.random()*5);
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
	
	
	public void toutDessiner(Graphics g,int size) {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				switch(carte[i][j].getNature()) {
				case Bord:
					g.setColor(java.awt.Color.black);
					break;
				case Riviere:
					g.setColor(java.awt.Color.blue);
					break;
				case Montagne:
					g.setColor(java.awt.Color.gray);
					break;
				case Foret:
					g.setColor(java.awt.Color.yellow);
					break;
				case Plaine:
					g.setColor(java.awt.Color.green);
					break;
				}
				g.fillRect(i*10,j*10,10,10);
			}
		}
	}
}
