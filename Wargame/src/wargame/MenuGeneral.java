package wargame;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class MenuGeneral extends JPanel implements IConfig{
	MenuGeneral(){
		this.setPreferredSize(new Dimension(LARGEUR_FENETRE,HAUTEUR_FENETRE));
		MenuAide aide = new MenuAide();
		GridLayout grille = new GridLayout(0,1,HAUTEUR_FENETRE/10,LARGEUR_FENETRE/10);
		grille.preferredLayoutSize(this);
		this.setLayout(grille);
		this.setBackground(new Color(88,85,90));
		JButton Start = new JButton("Start");
		Start.setBackground(new Color(127, 127, 127));
		Start.setPreferredSize(new Dimension(300,100));
		Start.setForeground(new Color(251, 243, 124));
		Start.setFont(new Font("Tahoma", Font.PLAIN, 30));
		JButton Aide = new JButton("Aide");
		Aide.setBackground(new Color(127, 127, 127));
		Aide.setPreferredSize(new Dimension(300,100));
		Aide.setForeground(new Color(251, 243, 124));
		Aide.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JButton Quitter = new JButton("Quitter");
		Quitter.setBackground(new Color(127, 127, 127));
		Quitter.setPreferredSize(new Dimension(300,100));
		Quitter.setForeground(new Color(251, 243, 124));
		Quitter.setFont(new Font("Tahoma", Font.PLAIN, 30));
		Quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		}
		);
		Aide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAide aide = new MenuAide();
				remove(Start);
				remove(Aide);
				remove(Quitter);
				add(aide);
				validate();
			}
		}
		);
		Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanneauJeu jeu = new PanneauJeu();
				PanneauMenu menu = new PanneauMenu();
				PanneauInfo info = new PanneauInfo();
				remove(Start);
				remove(Aide);
				remove(Quitter);
				JSplitPane contenu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				JSplitPane contenu2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
				contenu2.add(info);
				contenu2.add(menu);
				contenu.setDividerLocation(1000);
				contenu2.setDividerLocation(400);
				contenu.add(jeu);
				contenu.add(contenu2);
				add(contenu);
				validate();
			}
		}
		);
		add(Start);
		add(Aide);
		add(Quitter);
	}
}
