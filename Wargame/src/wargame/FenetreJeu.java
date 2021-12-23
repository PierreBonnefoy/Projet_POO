package wargame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreJeu {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				JFrame frame = new JFrame("Base de jeu");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setBackground(Color.white);
				frame.setLocation(100,100);
				frame.setResizable(false);
				frame.setPreferredSize(new Dimension(PanneauJeu.taille_fenetre,PanneauJeu.taille_fenetre));
				PanneauJeu panneau = new PanneauJeu();
				frame.setContentPane(panneau);
				frame.pack();
				frame.setVisible(true);
	  }
}
