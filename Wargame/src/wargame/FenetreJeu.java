package wargame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreJeu {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				JFrame frame = new JFrame("Base de jeu");
				PanneauJeu jeu= new PanneauJeu();
				PanneauMenu menu = new PanneauMenu();
				PanneauInfo info = new PanneauInfo();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setPreferredSize(new Dimension(1400,PanneauJeu.taille_fenetre));
				JSplitPane contenu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				JSplitPane contenu2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
				contenu2.add(info);
				contenu2.add(menu);
				contenu.setDividerLocation(1000);
				contenu2.setDividerLocation(400);
				contenu.add(jeu);
				contenu.add(contenu2);
				frame.getContentPane().add(contenu);
				frame.pack();
				frame.setVisible(true);
	  }

	
	public void tourDeJeu() {
		/*---D'abord deplacement du joueur avec la souris et attaque si possible, personnage par personnage---*/
		
		
		/*---Tour de jeu des ennemies---*/
		
		
		/*---On verifie si pas fi, de partie en verifiant si il reste tout le persos---*/
	}
}
