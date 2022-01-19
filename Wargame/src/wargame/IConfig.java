package wargame;
import java.awt.Color;
public interface IConfig {
	int LARGEUR_CARTE = 30; int HAUTEUR_CARTE = 15; // en nombre de cases
	int LARGEUR_FENETRE = 1300 ; int HAUTEUR_FENETRE = 800;
	int NB_PIX_CASE = 20;
	int POSITION_X = 100; int POSITION_Y = 50; // Position de la fen�tre
	int NB_HEROS = 6; int NB_MONSTRES = 15; int NB_OBSTACLES = 20;
	Color COULEUR_VIDE = Color.white, COULEUR_INCONNU = Color.lightGray;
	Color COULEUR_TEXTE = Color.black, COULEUR_MONSTRES = Color.black;
	Color COULEUR_HEROS = Color.red, COULEUR_HEROS_DEJA_JOUE = Color.pink;
	Color COULEUR_EAU = Color.blue, COULEUR_FORET = Color.green, COULEUR_ROCHER = Color.gray;
		/*--- config pour la carte ---*/
	int PLAINE=0,FORET=1,RIVIERE=2,MONTAGNE=3,BORD=99;
	int MAXRIVIERE=26,MAXMONTAGNE=22,MAXFORET=32;
		/*--- config pour les personnages ---*/
	/*Personnage.id et Personnage.nemesis*/
	public int GARDIEN = 1, BETE = 2, INCENDIAIRE = 3, DESTRUCTEUR = 4, ENCHANTEUR = 5, COMBATTANT = 6;
	public int ARTILLEUR = 7, DANCELAME = 8, CHASSEUR = 9, ECLAIREUR = 10;
	/*Personnage.etat*/
	public int VIVANT = 1, MORT = 0;
	/*Personnage.joueur (et plus généralement le joueur dont c'est le tour*/
	public int JOUEUR = 0;
	public int IA = 1;

	/*-- config générales --*/
	public int NBJOUEUR=2;
	public int NBPERSONNAGE=10; 
	
		/*--- config pour les elements ---*/
	
	public int DECOUVERT = 1, VISIBLE = 2, NON_DECOUVERT = 0;
}