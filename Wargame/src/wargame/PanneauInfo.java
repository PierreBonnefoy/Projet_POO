package wargame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class PanneauInfo extends JPanel{
	PanneauInfo() {
		GridLayout grille = new GridLayout(0,1);
		this.setLayout(grille);	
		this.setPreferredSize(new Dimension(500,100));
		this.setBackground(java.awt.Color.black);
		JLabel bouton1 = new JLabel("Bouton 1");
		bouton1.setForeground(Color.white);
		JLabel bouton2 = new JLabel("Bouton 2");
		bouton2.setForeground(Color.white);
		JLabel bouton3 = new JLabel("Bouton 3");
		bouton3.setForeground(Color.white);
		JLabel bouton4 = new JLabel("Bouton 4");
		bouton4.setForeground(Color.white);
		add(bouton1);
		add(bouton2);
		add(bouton3);
		add(bouton4);
	}	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
