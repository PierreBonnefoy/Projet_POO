package wargame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanneauJeu extends JPanel{
	public Carte jeu=new Carte(10);;
	PanneauJeu(){
		this.setPreferredSize(new Dimension(500,500));
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		jeu.toutDessiner(g,10);
	}
}
