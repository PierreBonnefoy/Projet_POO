package wargame;

import java.awt.Graphics;

public class Carte implements ICarte{
	public Element getElement(Position pos) {
		
	}
	public Position trouvePositionVide() {
		// Trouve al�atoirement une position vide sur la carte
	}
	public Position trouvePositionVide(Position pos) {
		// Trouve une position vide choisie
	}
								// al�atoirement parmi les 8 positions adjacentes de pos
	public Heros trouveHeros() {
		// Trouve al�atoirement un h�ros sur la carte
	}
	public Heros trouveHeros(Position pos) {
		// Trouve un h�ros choisi al�atoirement
	}
									 // parmi les 8 positions adjacentes de pos
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
