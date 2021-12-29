package wargame;
import java.awt.Graphics;

public class Carte implements ICarte{
	private Element[][] carte;
	private int taille;
	
	public Position voisineAlea(int positionx,int positiony,int n) {
	int alea;
	alea=(int)(Math.random()*5);
	switch(alea) {
		case 0 : 
			/*-- Case haut gauche --*/
			if(positiony>0 && positionx>0)
			{
				if(Math.floorMod(positiony, 2)==0) {
					positiony-=1;
					positionx-=1;
				}
				else {
					positiony-=1;
				}
			}
			break;
		case 1 : 
			/*-- Case haut droite --*/
			if(positionx<n-1 && positiony>0) {
				if(Math.floorMod(positiony, 2)==0) {
					positiony-=1;
				}
				else {
					positionx+=1;
					positiony-=1;
				}
			}
			break;
		case 2 : 
			/*-- Case gauche --*/
			if(positionx>0)
			{
				positionx-=1;
			}
			break;
		case 3 : 
			/*-- Case droite --*/
			if(positionx<n-1)
			{
				positionx+=1;
			}	
			break;
		case 4 : 
			/*-- Case bas gauche --*/
			if(positiony<n-1 && positionx>0)
			{
				if(Math.floorMod(positiony, 2)==0) {
					positiony+=1;
					positionx-=1;

				}
				else {
					positiony+=1;
				}
			}
			break;
		case 5 : 
			/*-- Case bas droite --*/
			if(positiony<n-1 && positionx<n-1) {
				if(Math.floorMod(positiony, 2)==0) {
					positiony+=1;
				}
				else {
					positionx+=1;
					positiony+=1;
				}
			}
			break;
		default :
			System.out.println("Erreur dans la recherche de riviere !");
	}
	return new Position(positionx,positiony);
}
	
	public Carte(int n) {
		int tmp1,tmp2;
		carte=new Element[n][n];
		this.taille=n;
		/*Remplissage avec la plaine*/
		for (int k = 0; k < n; k++) {
			for (int l = 0; l < n; l++) {
				if (carte[k][l] == null) {
					carte[k][l] = new Element(Plaine);
				}
			}
		}
		/*Nombre de riviere*/
		tmp1=(int) (Math.random() * (MaxRiviere - 1)) + 1;
		for(int k=0;k<tmp1;k++) {
			int positionx =(int)(Math.random() * (n - 1)) + 1;;
			int positiony =(int)(Math.random() * (n - 1)) + 1;
			tmp2=(int)(Math.random() * ((n-positionx) - 1)) + 1;
			for(int m=0;m<=tmp2;m++) {
				carte[positionx][positiony]=new Element(Riviere);
				positionx=voisineAlea(positionx,positiony,n).getX();
				positiony=voisineAlea(positionx,positiony,n).getX();
			}
		}
		
		
		/*Creation des montagnes*/
		tmp1=(int) (Math.random() * (MaxMontagne - 1)) + 1;
		for(int k=0;k<tmp1;k++) {
			int positionx =(int)(Math.random() * (n - 1)) + 1;;
			int positiony =(int)(Math.random() * (n - 1)) + 1;
			tmp2=(int)(Math.random() * ((n-positionx) - 1)) + 1;
			for(int m=0;m<=tmp2;m++) {
				carte[positionx][positiony]=new Element(Montagne);
				positionx=voisineAlea(positionx,positiony,n).getX();
				positiony=voisineAlea(positionx,positiony,n).getX();
			}
		}
		
		/*Creation des forets*/
		tmp1=(int) (Math.random() * (MaxForet - 1)) + 1;
		for(int k=0;k<tmp1;k++) {
			int positionx =(int)(Math.random() * (n - 1)) + 1;;
			int positiony =(int)(Math.random() * (n - 1)) + 1;
			tmp2=(int)(Math.random() * ((n-positionx) - 1)) + 1;
			for(int m=0;m<=tmp2;m++) {
				carte[positionx][positiony]=new Element(Foret);
				positionx=voisineAlea(positionx,positiony,n).getX();
				positiony=voisineAlea(positionx,positiony,n).getX();
			}
		}
		for(int i=0;i<n;i+=2) {
			carte[0][i]=new Element(Bord);
		}
		carte[10][10].rajoutPersonnage(new Personnage(2,2,10,10));
	}

	public void drawHexagon(int posx,int posy,Graphics g) {
		int xPoints[]=new int[6],yPoints[]=new int[6];
		xPoints[0]=posx;
		yPoints[0]=posy;
		xPoints[1]=posx+12;
		yPoints[1]=posy+8;
		xPoints[2]=posx+12;
		yPoints[2]=posy+16;
		xPoints[3]=posx;
		yPoints[3]=posy+24;
		xPoints[4]=posx-12;
		yPoints[4]=posy+16;
		xPoints[5]=posx-12;
		yPoints[5]=posy+8;
		g.fillPolygon(xPoints,yPoints,6);
	}
	
	public Carte(int n,int nbRiviere,int nbMontagne,int nbForet) {
		int tmp1,tmp2;
		Position tempo;
		carte=new Element[n][n];
		this.taille=n;
		
		/*Remplissage avec la plaine*/
		for (int k = 0; k < n; k++) {
			for (int l = 0; l < n; l++) {
				if (carte[k][l] == null) {
					carte[k][l] = new Element(Plaine);
				}
			}
		}
		
		/*Nombre de riviere*/
		for(int k=0;k<nbRiviere;k++) {
			int positionx =(int)(Math.random() * (n - 1)) + 1;;
			int positiony =(int)(Math.random() * (n - 1)) + 1;
			tmp2=(int)(Math.random() * ((n-positionx) - 1)) + 1;
			for(int m=0;m<tmp2;m++) {
				carte[positionx][positiony]=new Element(Riviere);
				tempo=voisineAlea(positionx,positiony,n);
				positionx=tempo.getX();
				positiony=tempo.getY();
			}
		}
		
		
		/*Creation des montagnes*/
		for(int k=0;k<nbMontagne;k++) {
			int positionx =(int)(Math.random() * (n - 1)) + 1;;
			int positiony =(int)(Math.random() * (n - 1)) + 1;
			tmp2=(int)(Math.random() * ((n-positionx) - 1)) + 1;
			for(int m=0;m<=tmp2;m++) {
				carte[positionx][positiony]=new Element(Montagne);
				tempo=voisineAlea(positionx,positiony,n);
				positionx=tempo.getX();
				positiony=tempo.getY();
			}
		}
		
		/*Creation des forets*/
		for(int k=0;k<nbForet;k++) {
			int positionx =(int)(Math.random() * (n - 1)) + 1;;
			int positiony =(int)(Math.random() * (n - 1)) + 1;
			tmp2=(int)(Math.random() * ((n-positionx) - 1)) + 1;
			for(int m=0;m<=tmp2;m++) {
				carte[positionx][positiony]=new Element(Foret);
				tempo=voisineAlea(positionx,positiony,n);
				positionx=tempo.getX();
				positiony=tempo.getY();
			}
		}
		for(int i=0;i<n;i+=2) {
			carte[0][i]=new Element(Bord);
		}
		carte[10][10].rajoutPersonnage(new Personnage(2,2,10,10));
	}
	
	public Element getElement(Position pos) {
		/*-- Rtourne l'element � la position --*/
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
		/*-- V�rification qu'il y ait au moins une place libre --*/
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
		// Trouve al�atoirement un h�ros sur la carte
	}
	public Heros trouveHeros(Position pos) {
		// Trouve un h�ros choisi al�atoirement
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
		int debut;
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(Math.floorMod(j,2)==0) {
					debut =0;
				}
				else {
					debut =12;
				}
				if(carte[i][j].getOccupe()>0) {
					g.setColor(java.awt.Color.red);
				}
				else {
					switch(carte[i][j].getNature()) {
					case Bord:
						g.setColor(java.awt.Color.white);
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
				}
				drawHexagon(debut+(i*26),j*18,g);
			}
		}
	}
}
