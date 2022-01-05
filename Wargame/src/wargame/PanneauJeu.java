package wargame;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.lang.Thread;


public class PanneauJeu extends JPanel implements IConfig {
	public static final int taille_fenetre=800;
	public int[] nb_vivant = new int[NBJOUEUR];
	public int indicePerso =1;
	public int indiceJoueur=NBJOUEUR-1;
	public Carte jeu=new Carte(30,4,2,2);
	boolean jouable=true;
	public Position posbuffer=new Position(0,0);
	Personnage perso=new Personnage(ECLAIREUR,JOUEUR,15,10);
	
	
	/*creation des personnages */
	public Personnage[][] equipe;
	public Personnage abomination;
	public Personnage mutilateur;
	public Personnage demoniste;
	public Personnage demon;
	public Personnage diablotin;
	public Personnage guerrier;
	public Personnage succube;
	public Personnage incube;
	public Personnage cerbere;
	public Personnage oeil;
	public Personnage golem;
	public Personnage hydre;
	public Personnage drake;
	public Personnage minotaure;
	public Personnage grenouille;
	public Personnage armure;
	public Personnage lezard;
	public Personnage vampyrion;
	public Personnage predator;
	public Personnage worm;
	JLabel infoLabel = new JLabel (perso.toString());
	
	public PanneauJeu(){
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		infoLabel.setForeground(Color.white);
		nb_vivant[JOUEUR] = NBPERSONNAGE;
		nb_vivant[IA] = NBPERSONNAGE;
		
		this.setPreferredSize(new Dimension(LARGEUR_FENETRE,HAUTEUR_FENETRE));
		this.setBackground(Color.black);
		/*initialisation du tableau d'equipe*/
		
		add(infoLabel);
		
		equipe = new Personnage[NBPERSONNAGE][NBJOUEUR];
		abomination = new Personnage(GARDIEN,IA,1,1);
		mutilateur = new Personnage(BETE,IA,1,2);
		demoniste = new Personnage(INCENDIAIRE,IA,1,3);
		demon = new Personnage(DESTRUCTEUR,IA,1,4);
		diablotin = new Personnage(ENCHANTEUR,IA,1,5);
		guerrier = new Personnage(COMBATTANT,IA,1,6);
		succube = new Personnage(ARTILLEUR,IA,1,7);
		incube = new Personnage(DANCELAME,IA,1,8);
		cerbere = new Personnage(CHASSEUR,IA,1,9);
		oeil = new Personnage(ECLAIREUR,IA,1,10);

		golem = new Personnage(GARDIEN,JOUEUR,15,1);
		hydre = new Personnage(BETE,JOUEUR,15,2);
		drake = new Personnage(INCENDIAIRE,JOUEUR,15,3);
		minotaure = new Personnage(DESTRUCTEUR,JOUEUR,15,4);
		grenouille = new Personnage(ENCHANTEUR,JOUEUR,15,5);
		armure = new Personnage(COMBATTANT,JOUEUR,15,6);
		lezard = new Personnage(ARTILLEUR,JOUEUR,15,7);
		vampyrion = new Personnage(DANCELAME,JOUEUR,15,8);
		predator = new Personnage(CHASSEUR,JOUEUR,15,9);
		worm = new Personnage(ECLAIREUR,JOUEUR,15,10);
		
		equipe[0][IA] = abomination;
		equipe[1][IA] = mutilateur;
		equipe[2][IA] = demoniste;
		equipe[3][IA] = demon;
		equipe[4][IA] = diablotin;
		equipe[5][IA] = guerrier;
		equipe[6][IA] = succube;
		equipe[7][IA] = incube;
		equipe[8][IA] = cerbere;
		equipe[9][IA] = oeil;
		
		equipe[0][JOUEUR] = golem;
		equipe[1][JOUEUR] = hydre;
		equipe[2][JOUEUR] = drake;
		equipe[3][JOUEUR] = minotaure;
		equipe[4][JOUEUR] = grenouille;
		equipe[5][JOUEUR] = armure;
		equipe[6][JOUEUR] = lezard;
		equipe[7][JOUEUR] = vampyrion;
		equipe[8][JOUEUR] = predator;
		equipe[9][JOUEUR] = worm;
		
		for(int i=0;i<NBPERSONNAGE;i++) {
			for(int j=0;j<NBJOUEUR;j++) {
				jeu.carte[equipe[i][j].getPos().getX()][equipe[i][j].getPos().getY()].rajoutPersonnage(equipe[i][j]);
			}
		}
		clickPosition();
		repaint();	
		tourDeJeux();
	}
	
	public void tourDeJeux() {
		/*boucle en parcourant le tableau des Personnages, et joue chacun des personnages a tour de rôle*/
		indiceJoueur+=1;
		if(indiceJoueur==NBJOUEUR) {
			indiceJoueur=0;
			indicePerso-=1;
			if(indicePerso==0) {
				indicePerso=NBPERSONNAGE-1;
			}
		}
		
		jouable=true;
		//int boucle = 0;
		//System.out.println("entree dans tour de jeux ");
		/*on parcours équipe et on joue chacun des personnages*/
		/*while(nb_vivant[JOUEUR]>0 || nb_vivant[IA]>0){
			System.out.println("oui ");
			;*/
			/*
			for(indicePerso=NBPERSONNAGE-1;indicePerso >= 0;indicePerso--){
				for(indiceJoueur=0;indiceJoueur <= NBJOUEUR-1;indiceJoueur++) {
					if(equipe[indicePerso][indiceJoueur].getEtat()!=MORT) {
						System.out.println("ici on joue "+equipe[indicePerso][indiceJoueur].nomPersonnage()+" "+indicePerso+" "+indiceJoueur);
						nb_vivant[indiceJoueur] -= jouerPersoJoueur(jeu,equipe[indicePerso][indiceJoueur]);
						nb_vivant[JOUEUR]-= 1;////////////
						nb_vivant[IA]--;////////////
					}
				}
			}*/
			/*System.out.println("fin d'une session de jeux : boucle = "+boucle+" | joueur = "+nb_vivant[JOUEUR]+" "+nb_vivant[IA]);
			repaint();	
		}*/
		if(nb_vivant[JOUEUR]>0 || nb_vivant[IA]>0) {
			//pas de soucis
		}
		else {
			System.exit(0);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		jeu.toutDessiner(g,30);
	}
	
	
	public Position ppV(int x1,int x2,int y1,int y2,int mx,int my,int haut) {
		Position pos=new Position(0,0),posMouse=new Position(mx,my);
		int dec1,dec2;
		double min=1000,tmp;
		if(haut==1) {
			dec1=1;
			dec2=0;
		}
		else {
			dec1=0;
			dec2=1;
		}
		tmp=posMouse.distance(new Position((dec1*14)+x1,y1));
		if(min>tmp) {
			min=tmp;
			pos=(new Position((dec1*14)+x1,y1));
		}
		tmp=posMouse.distance(new Position((dec1*14)+x2,y1));
		if(min>tmp) {
			min=tmp;
			pos=(new Position((dec1*14)+x2,y1));
		}
		tmp=posMouse.distance(new Position((dec2*14)+x1,y2));
		if(min>tmp) {
			min=tmp;
			pos=(new Position((dec2*14)+x1,y2));
		}
		tmp=posMouse.distance(new Position((dec2*14)+x2,y2));
		if(min>tmp) {
			min=tmp;
			pos=(new Position((dec2*14)+x2,y2));
		}
		return pos;
	}
 	
	public Position clickPosition(){
		posbuffer = null;
		
		//while(posbuffer == null) {
			System.out.println("null");
			addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					/*----Recuperation de la case dans posbuffer---*/
					Position pos2=null;
					Position positionCase=null;
					

					remove(infoLabel);
					Personnage perso=equipe[indicePerso][indiceJoueur];
					infoLabel.setText(perso.toString());
					add(infoLabel);
					validate();
					int y1,y2,x1,x2,pair;
					/*---Recupere les y qui entoure---*/
					y1=14+24*((e.getY()-14)/24);
					y2=14+24*(1+(e.getY()-14)/24);
					
					/*---Verifie si c'est la premiere ou la 2eme lignes qui est decal�---*/
					pair=Math.floorMod(y1/24,2);
					/*---Recupere les x qui l'entoure---*/
					x1=28*((e.getX())/28);
					x2=28*(1+(e.getX())/28);
					/*---Verifie parmi les 4 voisins, lequel est le plus proche---*/
					pos2=ppV(x1,x2,y1,y2,e.getX(),e.getY(),pair);
					//System.out.println("proche x = "+pos2.getX()+"proche y = "+pos2.getY());
					//System.out.println("case = ["+pos2.getX()/28+","+(pos2.getY()/24)+"]");
					posbuffer = new Position(pos2.getX()/28,pos2.getY()/24);
					positionCase=posbuffer;
					
					
					
					/*----Mouvement ou attaque du perso----*/
					if(jouable==true) {
						System.out.println("joueur n� " + indiceJoueur + "joue avec le perso numero : "+indicePerso);
						System.out.println("je suis a la position : "  + perso.getPos().getX()+ " " + perso.getPos().getY());
						int xd=perso.getPos().getX(),yd=perso.getPos().getY();
						
						if(positionCase.getX()>0 && positionCase.getX()<LARGEUR_CARTE-1 && positionCase.getY()>0 && positionCase.getY()<HAUTEUR_CARTE-1) {
							int xa = positionCase.getX();
							int ya = positionCase.getY();
							/*si le joueur a cliqué sur la case où se trouve le perso, il se repose, ou passe son tour*/
							if(xa==xd && ya==yd) {
								if(perso.getAttaque()==1 && perso.getPm() == perso.getVitesse()) {
									perso.repos();
								}
								else {
									perso.passeTour();
								}
							}
							/*le joueur a cliqu� sur une case vide a cote du perso et perso.pm!=0*/
							else if(perso.getPm() !=0 ) {
								jeu.Deplacement(new Position(xd,yd), positionCase);
								infoLabel.setText(perso.toString());
								add(infoLabel);
								validate();
								repaint();
							}
						}
						if(perso.getAttaque()==0 || perso.getPm() == 0) {
							jouable=false;
							tourDeJeux();
						}
					}
			}});

		//}
		System.out.println("on recupere click :");
		return posbuffer;
	}
	
}

