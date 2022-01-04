package wargame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanneauJeu extends JPanel{
	public static final int taille_fenetre=800;
	public Carte jeu=new Carte(30,4,2,2);
	PanneauJeu(){
		this.setPreferredSize(new Dimension(taille_fenetre,taille_fenetre));
		this.setBackground(Color.black);
		clickPosition();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		jeu.toutDessiner(g,30);
	}
	
	public Position ppV(int x1,int x2,int y1,int y2,int mx,int my,int haut) {
		Position pos=new Position(0,0),posMouse=new Position(mx,my);
		int dec1,dec2;
		double min=1000,tmp;
		if(haut==1) {
			dec1=1;
			dec2=0;
		}
		else {
			dec1=0;
			dec2=1;
		}
		tmp=posMouse.distance(new Position((dec1*14)+x1,y1));
		if(min>tmp) {
			min=tmp;
			pos=(new Position((dec1*14)+x1,y1));
		}
		tmp=posMouse.distance(new Position((dec1*14)+x2,y1));
		if(min>tmp) {
			min=tmp;
			pos=(new Position((dec1*14)+x2,y1));
		}
		tmp=posMouse.distance(new Position((dec2*14)+x1,y2));
		if(min>tmp) {
			min=tmp;
			pos=(new Position((dec2*14)+x1,y2));
		}
		tmp=posMouse.distance(new Position((dec2*14)+x2,y2));
		if(min>tmp) {
			min=tmp;
			pos=(new Position((dec2*14)+x2,y2));
		}
		return pos;
	}
 	
	public Position clickPosition(){
		Position pos=new Position(0,0);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Position pos2=new Position(0,0);
				int y1,y2,x1,x2,pair;
				
				/*---Recupere les y qui entoure---*/
				y1=14+24*((e.getY()-14)/24);
				y2=14+24*(1+(e.getY()-14)/24);
				
				/*---Verifie si c'est la premiere ou la 2eme lignes qui est decal�---*/
				pair=Math.floorMod(y1/24,2);
				/*---Recupere les x qui l'entoure---*/
				x1=28*((e.getX())/28);
				x2=28*(1+(e.getX())/28);
				/*---Verifie parmi les 4 voisins, lequel est le plus proche---*/
				pos2=ppV(x1,x2,y1,y2,e.getX(),e.getY(),pair);
				System.out.println("proche x = "+pos2.getX()+"proche y = "+pos2.getY());
				System.out.println("case = ["+pos2.getX()/28+","+(pos2.getY()/24)+"]");
				jeu.getElement(new Position(pos2.getX()/28,pos2.getY()/24)).rajoutPersonnage(new Personnage(2,2,10,10));
				repaint();
			}
		});
		return pos;
	}
}
