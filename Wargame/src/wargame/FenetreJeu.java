package wargame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreJeu implements IConfig{
	/**
	 * 
	 * @param args liste des arguments a mettre pour le main(il n'y en a pas)
	 */
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
}
