package wargame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MenuAide extends JPanel implements IConfig{
	MenuAide(){
		this.setPreferredSize(new Dimension(HAUTEUR_FENETRE,LARGEUR_FENETRE));
		GridLayout grille = new GridLayout(0,1,HAUTEUR_FENETRE/1000,LARGEUR_FENETRE/1000);
		grille.preferredLayoutSize(this);
		this.setLayout(grille);
		JLabel Explication = new JLabel("<html><h1>QUEST FOR UNDERGROUND : </h1>\r\n" + 
				"\r\n" + 
				"Les habitants des Donjon, monstres en tout genre, creusent la terre pour agrandir leur demeure et découvre l'Underground : un nouveau monde souterrain. En creusant trop profondément, ils créent malheureusement un passage vers l'Enfer : le royaume des démons. <br>\r\n" + 
				"Ayant enfin un accès vers l'extérieur, les démons rassemblent leurs armées et sortent en masse de leur royaume inhospitalier. <br>\r\n" + 
				"<br>\r\n" + 
				"Le Jouer va incarner une équipe de monstre du Donjon qui doit repousser l’invasion des Démons. \r\n" + 
				"\r\n" + 
				"<h2>Rêgles du jeux</h2>\r\n" + 
				"Le Joueur joue une équipe de 10 Monstres qui jouent chacun selon un ordre d’Initiative : celui qui à l’Initiative la plus élevée joue en premier. <br>\r\n" + 
				"Les Ennemis jouent une équipe semblable, et les créatures de chaque équipe jouent à tour de rôle.<br>\r\n" + 
				"Lors de son tour de jeux, un Personnage agit selon l’endroit où l’on clique avec la souris : \r\n" + 
				"<ul><li>Se déplacer (cliquez sur une case vide accessible)</li>\r\n" + 
				"<li>Attaquer (cliquez sur une case occupée (par un ennemi)</li><li>Passer son tour (cliquez sur le personnage</li></ul>"
				+ "</html>");
		JButton retour = new JButton ("retour");
		this.setBackground(new Color(127, 127, 127));
		Explication.setForeground(new Color(251, 243, 124));
		Explication.setFont(new Font("Tahoma", Font.PLAIN, 20));
		retour.setBackground(new Color(100, 100, 100));
		retour.setForeground(new Color(251, 243, 124));
		retour.setFont(new Font("Tahoma", Font.PLAIN, 30));
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGeneral menu = new MenuGeneral();
				remove(Explication);
				remove(retour);
				add(menu);
				validate();
			}
		}
		);
		add(Explication);
		add(retour);
	}
}
