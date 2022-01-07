package wargame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreJeu implements IConfig{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				JFrame frame = new JFrame("Base de jeu");
				MenuGeneral StartScreen = new MenuGeneral();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setPreferredSize(new Dimension(LARGEUR_FENETRE,HAUTEUR_FENETRE));
				frame.setContentPane(StartScreen);
				frame.pack();
				frame.setVisible(true);
	  }

	
	public void tourDeJeu() {
		/*---D'abord deplacement du joueur avec la souris et attaque si possible, personnage par personnage---*/
		
		
		/*---Tour de jeu des ennemies---*/
		
		
		/*---On verifie si pas fi, de partie en verifiant si il reste tout le persos---*/
	}
}
