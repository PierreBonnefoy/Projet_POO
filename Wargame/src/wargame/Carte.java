package wargame;
import java.awt.Graphics;

public class Carte implements ICarte{
	private Element[][] carte;
	private int taille;
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
			int l =(int)(Math.random() * (n - 1)) + 1;;
			int positiony =(int)(Math.random() * (n - 1)) + 1;
			tmp2=(int)(Math.random() * ((n-l) - 1)) + 1;
			for(int m=0;m<=tmp2;m++) {
				carte[l][positiony]=new Element(Riviere);
				int alea;
				alea=(int)(Math.random()*5);
				switch(alea) {
					case 0 : 
						/*-- Case haut gauche --*/
						if(positiony<n-1)
						{
							positiony+=1;
						}
						break;
					case 1 : 
						/*-- Case haut droite --*/
						if(l<n-1 && positiony<n-1) {
							l+=1;
							positiony+=1;
						}
						break;
					case 2 : 
						/*-- Case gauche --*/
						if(l>0)
						{
							l-=1;
						}
						break;
					case 3 : 
						/*-- Case droite --*/
						if(l<n-1)
						{
							l+=1;
						}	
						break;
					case 4 : 
						/*-- Case bas gauche --*/
						if(positiony>0)
						{
							positiony-=1;
						}
						break;
					case 5 : 
						/*-- Case bas gauche --*/
						if(l<n-1 && positiony>0) {
							l+=1;
							positiony-=1;
						}
						break;
					default :
						System.out.println("Erreur dans la recherche de riviere !");
				}
			}
		}
		
		
		/*Creation des montagnes*/
		tmp1=(int) (Math.random() * (MaxMontagne - 1)) + 1;
		for(int k=0;k<tmp1;k++) {
			int l =(int)(Math.random() * (n - 1)) + 1;;
			int positiony =(int)(Math.random() * (n - 1)) + 1;
			tmp2=(int)(Math.random() * ((n-l) - 1)) + 1;
			for(int m=0;m<=tmp2;m++) {
				carte[l][positiony]=new Element(Montagne);
				int alea;
				alea=(int)(Math.random()*5);
				switch(alea) {
					case 0 : 
						/*-- Case haut gauche --*/
						if(positiony<n-1)
						{
							positiony+=1;
						}
						break;
					case 1 : 
						/*-- Case haut droite --*/
						if(l<n-1 && positiony<n-1) {
							l+=1;
							positiony+=1;
						}
						break;
					case 2 : 
						/*-- Case gauche --*/
						if(l>0)
						{
							l-=1;
						}
						break;
					case 3 : 
						/*-- Case droite --*/
						if(l<n-1)
						{
							l+=1;
						}	
						break;
					case 4 : 
						/*-- Case bas gauche --*/
						if(positiony>0)
						{
							positiony-=1;
						}
						break;
					case 5 : 
						/*-- Case bas gauche --*/
						if(l<n-1 && positiony>0) {
							l+=1;
							positiony-=1;
						}
						break;
					default :
						System.out.println("Erreur dans la recherche de montagne !");
				}
			}
		}
		
		/*Creation des forets*/
		tmp1=(int) (Math.random() * (MaxForet - 1)) + 1;
		for(int k=0;k<tmp1;k++) {
			int l =(int)(Math.random() * (n - 1)) + 1;;
			int positiony =(int)(Math.random() * (n - 1)) + 1;
			tmp2=(int)(Math.random() * ((n-l) - 1)) + 1;
			for(int m=0;m<=tmp2;m++) {
				carte[l][positiony]=new Element(Foret);
				int alea;
				alea=(int)(Math.random()*5);
				switch(alea) {
					case 0 : 
						/*-- Case haut gauche --*/
						if(positiony<n-1)
						{
							positiony+=1;
						}
						break;
					case 1 : 
						/*-- Case haut droite --*/
						if(l<n-1 && positiony<n-1) {
							l+=1;
							positiony+=1;
						}
						break;
					case 2 : 
						/*-- Case gauche --*/
						if(l>0)
						{
							l-=1;
						}
						break;
					case 3 : 
						/*-- Case droite --*/
						if(l<n-1)
						{
							l+=1;
						}	
						break;
					case 4 : 
						/*-- Case bas gauche --*/
						if(positiony>0)
						{
							positiony-=1;
						}
						break;
					case 5 : 
						/*-- Case bas gauche --*/
						if(l<n-1 && positiony>0) {
							l+=1;
							positiony-=1;
						}
						break;
					default :
						System.out.println("Erreur dans la recherche de foret !");
				}
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
		xPoints[1]=posx+6;
		yPoints[1]=posy+4;
		xPoints[2]=posx+6;
		yPoints[2]=posy+8;
		xPoints[3]=posx;
		yPoints[3]=posy+12;
		xPoints[4]=posx-6;
		yPoints[4]=posy+4;
		xPoints[5]=posx-6;
		yPoints[5]=posy+8;
		g.fillPolygon(xPoints,yPoints,6);
	}
	
	public Carte(int n,int nbRiviere,int nbMontagne,int nbForet) {
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
		for(int k=0;k<nbRiviere;k++) {
			int l =(int)(Math.random() * (n - 1)) + 1;;
			int positiony =(int)(Math.random() * (n - 1)) + 1;
			tmp2=(int)(Math.random() * ((n-l) - 1)) + 1;
			for(int m=0;m<=tmp2;m++) {
				carte[l][positiony]=new Element(Riviere);
				int alea;
				alea=(int)(Math.random()*5);
				switch(alea) {
					case 0 : 
						/*-- Case haut gauche --*/
						if(positiony<n-1)
						{
							positiony+=1;
						}
						break;
					case 1 : 
						/*-- Case haut droite --*/
						if(l<n-1 && positiony<n-1) {
							l+=1;
							positiony+=1;
						}
						break;
					case 2 : 
						/*-- Case gauche --*/
						if(l>0)
						{
							l-=1;
						}
						break;
					case 3 : 
						/*-- Case droite --*/
						if(l<n-1)
						{
							l+=1;
						}	
						break;
					case 4 : 
						/*-- Case bas gauche --*/
						if(positiony>0)
						{
							positiony-=1;
						}
						break;
					case 5 : 
						/*-- Case bas gauche --*/
						if(l<n-1 && positiony>0) {
							l+=1;
							positiony-=1;
						}
						break;
					default :
						System.out.println("Erreur dans la recherche de riviere !");
				}
			}
		}
		
		
		/*Creation des montagnes*/
		for(int k=0;k<nbMontagne;k++) {
			int l =(int)(Math.random() * (n - 1)) + 1;;
			int positiony =(int)(Math.random() * (n - 1)) + 1;
			tmp2=(int)(Math.random() * ((n-l) - 1)) + 1;
			for(int m=0;m<=tmp2;m++) {
				carte[l][positiony]=new Element(Montagne);
				int alea;
				alea=(int)(Math.random()*5);
				switch(alea) {
					case 0 : 
						/*-- Case haut gauche --*/
						if(positiony<n-1)
						{
							positiony+=1;
						}
						break;
					case 1 : 
						/*-- Case haut droite --*/
						if(l<n-1 && positiony<n-1) {
							l+=1;
							positiony+=1;
						}
						break;
					case 2 : 
						/*-- Case gauche --*/
						if(l>0)
						{
							l-=1;
						}
						break;
					case 3 : 
						/*-- Case droite --*/
						if(l<n-1)
						{
							l+=1;
						}	
						break;
					case 4 : 
						/*-- Case bas gauche --*/
						if(positiony>0)
						{
							positiony-=1;
						}
						break;
					case 5 : 
						/*-- Case bas gauche --*/
						if(l<n-1 && positiony>0) {
							l+=1;
							positiony-=1;
						}
						break;
					default :
						System.out.println("Erreur dans la recherche de montagne !");
				}
			}
		}
		
		/*Creation des forets*/
		for(int k=0;k<nbForet;k++) {
			int l =(int)(Math.random() * (n - 1)) + 1;;
			int positiony =(int)(Math.random() * (n - 1)) + 1;
			tmp2=(int)(Math.random() * ((n-l) - 1)) + 1;
			for(int m=0;m<=tmp2;m++) {
				carte[l][positiony]=new Element(Foret);
				int alea;
				alea=(int)(Math.random()*5);
				switch(alea) {
					case 0 : 
						/*-- Case haut gauche --*/
						if(positiony<n-1)
						{
							positiony+=1;
						}
						break;
					case 1 : 
						/*-- Case haut droite --*/
						if(l<n-1 && positiony<n-1) {
							l+=1;
							positiony+=1;
						}
						break;
					case 2 : 
						/*-- Case gauche --*/
						if(l>0)
						{
							l-=1;
						}
						break;
					case 3 : 
						/*-- Case droite --*/
						if(l<n-1)
						{
							l+=1;
						}	
						break;
					case 4 : 
						/*-- Case bas gauche --*/
						if(positiony>0)
						{
							positiony-=1;
						}
						break;
					case 5 : 
						/*-- Case bas gauche --*/
						if(l<n-1 && positiony>0) {
							l+=1;
							positiony-=1;
						}
						break;
					default :
						System.out.println("Erreur dans la recherche de foret !");
				}
			}
		}
		for(int i=0;i<n;i+=2) {
			carte[0][i]=new Element(Bord);
		}
		carte[10][10].rajoutPersonnage(new Personnage(2,2,10,10));
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
		int debut;
		for(int i=0;i<size;i++) {
			if(Math.floorMod(i,2)==0) {
				debut =12;
			}
			else {
				debut =6;
			}
			for(int j=0;j<size;j++) {
				if(carte[i][j].getOccupe()>0) {
					g.setColor(java.awt.Color.red);
				}
				else {
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
				}
				drawHexagon(i*8,debut+(j*12),g);
			}
		}
	}
}
