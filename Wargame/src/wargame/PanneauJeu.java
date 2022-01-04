package wargame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanneauJeu extends JPanel implements IConfig {
	public static final int taille_fenetre=800;
	public Carte jeu=new Carte(30,4,2,2);
	public Position posbuffer=new Position(0,0);
	
	/*creation des personnages */
	public Personnage[][] equipe = new Personnage[NBPERSONNAGE][NBJOUEUR];
	public Personnage abomination = new Personnage(0,1,1,1);
	public Personnage mutilateur = new Personnage(0,2,1,2);
	public Personnage incendiaire = new Personnage(0,3,1,3);
	public Personnage destructeur = new Personnage(0,4,1,4);
	public Personnage diablotin = new Personnage(0,5,1,5);
	public Personnage guerrier = new Personnage(0,6,1,6);
	public Personnage succube = new Personnage(0,7,1,7);
	public Personnage incube = new Personnage(0,8,1,8);
	public Personnage cerbere = new Personnage(0,9,1,9);
	public Personnage oeil = new Personnage(0,10,1,10);
	
	public PanneauJeu(){
		this.setPreferredSize(new Dimension(taille_fenetre,taille_fenetre));
		this.setBackground(Color.black);
		clickPosition();
		/*initialisation du tableau d'equipe*/
		equipe[0][0] = abomination;
		equipe[1][0] = mutilateur;
		equipe[2][0] = incendiaire;
		equipe[3][0] = destructeur;
		equipe[4][0] = diablotin;
		equipe[5][0] = guerrier;
		equipe[6][0] = succube;
		equipe[7][0] = incube;
		equipe[8][0] = cerbere;
		equipe[9][0] = oeil;
	}
	
	public void tourdejeux() {
		/*boucle en parcourant le tableau des Personnages, et joue chacun des personnages a tour de rôle*/
		int indicePerso;
		int indiceJoueur; 
		int termine = 0;
		int[] nb_vivant = new int[2];
		nb_vivant[JOUEUR] = NBPERSONNAGE;
		nb_vivant[IA] = NBPERSONNAGE;
		
		/*on parcours équipe et on joue chacun des personnages*/
		while(nb_vivant[JOUEUR]!=0 || nb_vivant[IA]!=0){
			for(indicePerso=NBPERSONNAGE-1;indicePerso >= 1;indicePerso--){
				for(indiceJoueur=0;indiceJoueur <= NBJOUEUR-1;indiceJoueur++) {
					if(equipe[indicePerso][indiceJoueur].getEtat()!=MORT) {
						nb_vivant[indiceJoueur] += jouerPersoJoueur(jeu,equipe[indicePerso][indiceJoueur]);
					}
				}
			}
		}
		
	}
	
	public int jouerPersoJoueur(Carte jeu, Personnage perso) {
		int i;
		int resultat = 0;
		int x=0;
		int y=0;
		Position positionDepart = perso.getPos();
		Position positionCase = new Position(0,0); /////////////////
		
		perso.setAttaque(1);
		perso.setPm(perso.getVitesse());
		
		
		while(perso.getAttaque()!=0 && perso.getPm() != 0) {
			/*on se place en attente d'un click sur la carte : selon la position, l'effet sera différent*/
			positionCase = clickPosition();
			
			/*le joueur a cliqué sur une case vide a cote du perso et perso.pm!=0*/
			if(perso.getPm() !=0) {
				jeu.Deplacement(positionDepart, positionCase);
			}
		}
		
		return resultat;
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
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Position pos2=new Position(0,0);
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
				System.out.println("proche x = "+pos2.getX()+"proche y = "+pos2.getY());
				System.out.println("case = ["+pos2.getX()/28+","+(pos2.getY()/24)+"]");
				posbuffer = new Position(pos2.getX()/28,pos2.getY()/24);
			}
		});
		return posbuffer;
	}
}
