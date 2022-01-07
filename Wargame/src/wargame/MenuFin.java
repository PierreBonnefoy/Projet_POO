package wargame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MenuFin extends JPanel implements IConfig{
	/**
	 * Constructeur du panneau d'affichage de victoire
	 * @param victoire est ce que le joueur a gagné
	 */
	MenuFin(boolean victoire){
		String c=null;
		if(victoire) {
			c="Victoire";
		}
		else {
			c="Defaite";
		}
		JLabel chaine = new JLabel(c,SwingConstants.CENTER);
		this.setPreferredSize(new Dimension(LARGEUR_FENETRE,HAUTEUR_FENETRE));
		this.setLayout(new BorderLayout());
		JButton retour = new JButton ("retour");
		this.setBackground(new Color(127, 127, 127));
		chaine.setMaximumSize(new Dimension(LARGEUR_FENETRE,HAUTEUR_FENETRE));
		chaine.setForeground(new Color(251, 243, 124));
		chaine.setFont(new Font("Tahoma", Font.PLAIN, 50));
		retour.setBackground(new Color(100, 100, 100));
		retour.setForeground(new Color(251, 243, 124));
		retour.setFont(new Font("Tahoma", Font.PLAIN, 30));
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGeneral menu = new MenuGeneral();
				remove(chaine);
				remove(retour);
				add(menu);
				validate();
			}
		}
		);
		add(chaine);
		add(retour,BorderLayout.SOUTH);
	}
}
