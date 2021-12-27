package wargame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanneauJeu extends JPanel{
	public static final int taille_fenetre=800;
	public Carte jeu=new Carte(70,8,5,5);;
	PanneauJeu(){
		this.setPreferredSize(new Dimension(taille_fenetre,taille_fenetre));
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		jeu.toutDessiner(g,70);
	}
}
