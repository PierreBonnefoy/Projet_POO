package wargame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class PanneauMenu extends JPanel{
	PanneauMenu() {
		GridLayout grille = new GridLayout(0,2);
		this.setLayout(grille);	
		this.setPreferredSize(new Dimension(500,100));
		this.setBackground(java.awt.Color.black);
		JButton bouton1 = new JButton("Bouton 1");
		JButton bouton2 = new JButton("Bouton 2");
		JButton bouton3 = new JButton("Bouton 3");
		JButton bouton4 = new JButton("Bouton 4");
		add(bouton1);
		add(bouton2);
		add(bouton3);
		add(bouton4);
	}	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
