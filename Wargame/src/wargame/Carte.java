package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Carte implements ICarte, IConfig, java.io.Serializable {
	public Element[][] carte;
	public int taille;

	public Position voisineAlea(int positionx, int positiony, int n) {
		int alea;
		alea = (int) (Math.random() * 5);
		switch (alea) {
		case 0:
			/*-- Case haut gauche --*/
			if (positiony > 0 && positionx > 0) {
				if (Math.floorMod(positiony, 2) == 0) {
					positiony -= 1;
					positionx -= 1;
				} else {
					positiony -= 1;
				}
			}
			break;
		case 1:
			/*-- Case haut droite --*/
			if (positionx < n - 1 && positiony > 0) {
				if (Math.floorMod(positiony, 2) == 0) {
					positiony -= 1;
				} else {
					positionx += 1;
					positiony -= 1;
				}
			}
			break;
		case 2:
			/*-- Case gauche --*/
			if (positionx > 0) {
				positionx -= 1;
			}
			break;
		case 3:
			/*-- Case droite --*/
			if (positionx < n - 1) {
				positionx += 1;
			}
			break;
		case 4:
			/*-- Case bas gauche --*/
			if (positiony < n - 1 && positionx > 0) {
				if (Math.floorMod(positiony, 2) == 0) {
					positiony += 1;
					positionx -= 1;

				} else {
					positiony += 1;
				}
			}
			break;
		case 5:
			/*-- Case bas droite --*/
			if (positiony < n - 1 && positionx < n - 1) {
				if (Math.floorMod(positiony, 2) == 0) {
					positiony += 1;
				} else {
					positionx += 1;
					positiony += 1;
				}
			}
			break;
		default:
			System.out.println("Erreur dans la recherche de riviere !");
		}
		return new Position(positionx, positiony);
	}

	public Carte(int n) {
		int tmp1, tmp2;
		carte = new Element[n][n];
		this.taille = n;
		/* Remplissage avec la plaine */
		for (int k = 0; k < n; k++) {
			for (int l = 0; l < n; l++) {
				if (carte[k][l] == null) {
					carte[k][l] = new Element(PLAINE);
				}
			}
		}
		/* Nombre de riviere */
		tmp1 = (int) (Math.random() * (MAXRIVIERE - 1)) + 1;
		for (int k = 0; k < tmp1; k++) {
			int positionx = (int) (Math.random() * (n - 1)) + 1;
			;
			int positiony = (int) (Math.random() * (n - 1)) + 1;
			tmp2 = (int) (Math.random() * ((n - positionx) - 1)) + 1;
			for (int m = 0; m <= tmp2; m++) {
				carte[positionx][positiony] = new Element(RIVIERE);
				positionx = voisineAlea(positionx, positiony, n).getX();
				positiony = voisineAlea(positionx, positiony, n).getX();
			}
		}

		/* Creation des montagnes */
		tmp1 = (int) (Math.random() * (MAXMONTAGNE - 1)) + 1;
		for (int k = 0; k < tmp1; k++) {
			int positionx = (int) (Math.random() * (n - 1)) + 1;
			;
			int positiony = (int) (Math.random() * (n - 1)) + 1;
			tmp2 = (int) (Math.random() * ((n - positionx) - 1)) + 1;
			for (int m = 0; m <= tmp2; m++) {
				carte[positionx][positiony] = new Element(MONTAGNE);
				positionx = voisineAlea(positionx, positiony, n).getX();
				positiony = voisineAlea(positionx, positiony, n).getX();
			}
		}

		/* Creation des forets */
		tmp1 = (int) (Math.random() * (MAXFORET - 1)) + 1;
		for (int k = 0; k < tmp1; k++) {
			int positionx = (int) (Math.random() * (n - 1)) + 1;
			;
			int positiony = (int) (Math.random() * (n - 1)) + 1;
			tmp2 = (int) (Math.random() * ((n - positionx) - 1)) + 1;
			for (int m = 0; m <= tmp2; m++) {
				carte[positionx][positiony] = new Element(FORET);
				positionx = voisineAlea(positionx, positiony, n).getX();
				positiony = voisineAlea(positionx, positiony, n).getX();
			}
		}
		for (int i = 0; i < n; i++) {
			carte[0][i] = new Element(BORD);
			carte[i][0] = new Element(BORD);
			carte[i][LARGEUR_CARTE - 1] = new Element(BORD);
			carte[LARGEUR_CARTE - 1][i] = new Element(BORD);
		}
		/*
		 * carte[6][5].rajoutPersonnage(new Personnage(1,1,5,5));
		 * carte[6][6].rajoutPersonnage(new Personnage(2,1,6,6));
		 * carte[6][7].rajoutPersonnage(new Personnage(3,1,5,6));
		 * carte[6][8].rajoutPersonnage(new Personnage(4,1,6,5));
		 * carte[6][9].rajoutPersonnage(new Personnage(5,1,7,7));
		 * carte[6][10].rajoutPersonnage(new Personnage(6,1,7,6));
		 * carte[6][11].rajoutPersonnage(new Personnage(7,1,6,7));
		 * carte[6][12].rajoutPersonnage(new Personnage(8,1,7,5));
		 * carte[6][13].rajoutPersonnage(new Personnage(9,1,5,7));
		 * carte[6][14].rajoutPersonnage(new Personnage(10,1,8,8));
		 */
	}

	public void drawHexagon(int posx, int posy, Graphics g, int rouge, int vert, int bleu, int transparent) {
		g.setColor(new Color((rouge), (vert), (bleu), transparent));

		int xPoints[] = new int[6], yPoints[] = new int[6];
		xPoints[0] = posx;
		yPoints[0] = posy;
		xPoints[1] = posx + 12;
		yPoints[1] = posy + 8;
		xPoints[2] = posx + 12;
		yPoints[2] = posy + 16;
		xPoints[3] = posx;
		yPoints[3] = posy + 24;
		xPoints[4] = posx - 12;
		yPoints[4] = posy + 16;
		xPoints[5] = posx - 12;
		yPoints[5] = posy + 8;
		g.fillPolygon(xPoints, yPoints, 6);
	}

	public Carte(int n, int nbRiviere, int nbMontagne, int nbForet) {
		int tmp1, tmp2;
		Position tempo;
		carte = new Element[n][n];
		this.taille = n;

		/* Remplissage avec la plaine */
		for (int k = 0; k < n; k++) {
			for (int l = 0; l < n; l++) {
				if (carte[k][l] == null) {
					carte[k][l] = new Element(PLAINE);
				}
			}
		}

		/* Nombre de riviere */
		for (int k = 0; k < nbRiviere; k++) {
			int positionx = (int) (Math.random() * (n - 1)) + 1;
			;
			int positiony = (int) (Math.random() * (n - 1)) + 1;
			tmp2 = (int) (Math.random() * ((n - positionx) - 1)) + 1;
			for (int m = 0; m < tmp2; m++) {
				carte[positionx][positiony] = new Element(RIVIERE);
				tempo = voisineAlea(positionx, positiony, n);
				positionx = tempo.getX();
				positiony = tempo.getY();
			}
		}

		/* Creation des montagnes */
		for (int k = 0; k < nbMontagne; k++) {
			int positionx = (int) (Math.random() * (n - 1)) + 1;
			;
			int positiony = (int) (Math.random() * (n - 1)) + 1;
			tmp2 = (int) (Math.random() * ((n - positionx) - 1)) + 1;
			for (int m = 0; m <= tmp2; m++) {
				carte[positionx][positiony] = new Element(MONTAGNE);
				tempo = voisineAlea(positionx, positiony, n);
				positionx = tempo.getX();
				positiony = tempo.getY();
			}
		}

		/* Creation des forets */
		for (int k = 0; k < nbForet; k++) {
			int positionx = (int) (Math.random() * (n - 1)) + 1;
			;
			int positiony = (int) (Math.random() * (n - 1)) + 1;
			tmp2 = (int) (Math.random() * ((n - positionx) - 1)) + 1;
			for (int m = 0; m <= tmp2; m++) {
				carte[positionx][positiony] = new Element(FORET);
				tempo = voisineAlea(positionx, positiony, n);
				positionx = tempo.getX();
				positiony = tempo.getY();
			}
		}
		for (int i = 0; i < n; i++) {
			carte[0][i] = new Element(BORD);
			carte[i][0] = new Element(BORD);
			carte[i][LARGEUR_CARTE - 1] = new Element(BORD);
			carte[LARGEUR_CARTE - 1][i] = new Element(BORD);
		}
		for (int i = 1; i < n - 1; i++) {
			carte[1][i] = new Element(PLAINE);
			carte[i][1] = new Element(PLAINE);
			carte[i][LARGEUR_CARTE - 2] = new Element(PLAINE);
			carte[LARGEUR_CARTE - 2][i] = new Element(PLAINE);
		}

		/*
		 * carte[6][5].rajoutPersonnage(new Personnage(1,1,5,5));
		 * carte[6][6].rajoutPersonnage(new Personnage(2,1,6,6));
		 * carte[6][7].rajoutPersonnage(new Personnage(3,1,5,6));
		 * carte[6][8].rajoutPersonnage(new Personnage(4,1,6,5));
		 * carte[6][9].rajoutPersonnage(new Personnage(5,1,7,7));
		 * carte[6][10].rajoutPersonnage(new Personnage(6,1,7,6));
		 * carte[6][11].rajoutPersonnage(new Personnage(7,1,6,7));
		 * carte[6][12].rajoutPersonnage(new Personnage(8,1,7,5));
		 * carte[6][13].rajoutPersonnage(new Personnage(9,1,5,7));
		 * carte[6][14].rajoutPersonnage(new Personnage(10,1,8,8));
		 */
	}

	public Element getElement(Position pos) {
		/*-- Rtourne l'element � la position --*/
		System.out.println(distance(new Position(1, 1), pos));
		return this.carte[pos.getX()][pos.getY()];
	}

	public Position trouvePositionVide() {
		/*-- Cherche une position, sur le plateau, libre aleatoirement --*/
		int aleaX, aleaY;
		do {
			aleaX = (int) (Math.random() * this.taille - 1);
			aleaY = (int) (Math.random() * this.taille - 1);
		} while (this.carte[aleaX][aleaY].getOccupe() != 0);
		return (new Position(aleaX, aleaY));
	}

	public Position trouvePositionVide(Position pos) {
		/*-- Vérification qu'il y ait au moins une place libre --*/
		boolean test = false;

		/*-- Case haut gauche --*/
		if (pos.getY() > 0 && pos.getY() > 0) {
			if (Math.floorMod(pos.getY(), 2) == 0) {
				if (this.carte[pos.getX() - 1][pos.getY() - 1].getOccupe() == 0) {
					test = true;
				}
			} else {
				if (this.carte[pos.getX()][pos.getY() - 1].getOccupe() == 0) {
					test = true;
				}
			}
		}
		/*-- Case haut droite --*/
		if (pos.getX() < this.taille - 1 && pos.getY() > 0) {
			if (Math.floorMod(pos.getY(), 2) == 0) {
				if (this.carte[pos.getX()][pos.getY() - 1].getOccupe() == 0) {
					test = true;
				}
			} else {
				if (this.carte[pos.getX() + 1][pos.getY() - 1].getOccupe() == 0) {
					test = true;
				}
			}
		}
		/*-- Case gauche --*/
		if (pos.getX() > 0) {
			if (this.carte[pos.getX() - 1][pos.getY()].getOccupe() == 0) {
				test = true;
			}
		}
		/*-- Case droite --*/
		if (pos.getX() < this.taille - 1) {
			if (this.carte[pos.getX() + 1][pos.getY()].getOccupe() == 0) {
				test = true;
			}
		}
		/*-- Case bas gauche --*/
		if (pos.getY() < this.taille - 1 && pos.getX() > 0) {
			if (Math.floorMod(pos.getY(), 2) == 0) {
				if (this.carte[pos.getX() - 1][pos.getY() + 1].getOccupe() == 0) {
					test = true;
				}
			} else {
				if (this.carte[pos.getX()][pos.getY() + 1].getOccupe() == 0) {
					test = true;
				}
			}
		}
		/*-- Case bas droite --*/
		if (pos.getY() < this.taille - 1 && pos.getX() < this.taille - 1) {
			if (Math.floorMod(pos.getY(), 2) == 0) {
				if (this.carte[pos.getX()][pos.getY() + 1].getOccupe() == 0) {
					test = true;
				}
			} else {
				if (this.carte[pos.getX() + 1][pos.getY() + 1].getOccupe() == 0) {
					test = true;
				}
			}
		}
		/*-------------------------------------------*/

		if (!test) {
			/*-- retour si aucune case n'est libre --*/
			return null;
		}

		/*-- Tirage d'une des 6 position possible --*/
		Position tmp;
		do {
			tmp = this.voisineAlea(pos.getX(), pos.getY(), this.taille);

		} while ((this.carte[tmp.getX()][tmp.getY()].getOccupe()) != 0);
		return (tmp);

	}

	public Position[] PositionPossible(Position pos) {
		Position[] tmp = new Position[6];
		for (int i = 0; i < 6; i++) {
			tmp[i] = new Position(1, 1);
		}

		if (pos.getY() > 0 && pos.getY() > 0) {
			if (Math.floorMod(pos.getY(), 2) == 0) {
				if (this.carte[pos.getX() - 1][pos.getY() - 1].getOccupe() == 0
						&& this.carte[pos.getX() - 1][pos.getY() - 1].getNature() != BORD
						&& ((this.carte[pos.getX() - 1][pos.getY() - 1].getNature() != MONTAGNE
								&& this.carte[pos.getX() - 1][pos.getY() - 1].getNature() != RIVIERE)
								|| this.carte[pos.getX()][pos.getY()].getPersonnage(0).chemin())) {
					tmp[0].setX(pos.getX() - 1);
					tmp[0].setY(pos.getY() - 1);
				} else {
					tmp[0] = null;
				}
			} else {
				if (this.carte[pos.getX()][pos.getY() - 1].getOccupe() == 0
						&& this.carte[pos.getX()][pos.getY() - 1].getNature() != BORD
						&& ((this.carte[pos.getX()][pos.getY() - 1].getNature() != MONTAGNE
								&& this.carte[pos.getX()][pos.getY() - 1].getNature() != RIVIERE)
								|| this.carte[pos.getX()][pos.getY()].getPersonnage(0).chemin())) {
					tmp[0].setX(pos.getX());
					tmp[0].setY(pos.getY() - 1);
				} else {
					tmp[0] = null;
				}
			}
		}
		/*-- Case haut droite --*/
		if (pos.getX() < this.taille - 1 && pos.getY() > 0) {
			if (Math.floorMod(pos.getY(), 2) == 0) {
				if (this.carte[pos.getX()][pos.getY() - 1].getOccupe() == 0
						&& this.carte[pos.getX()][pos.getY() - 1].getNature() != BORD
						&& ((this.carte[pos.getX()][pos.getY() - 1].getNature() != MONTAGNE
								&& this.carte[pos.getX()][pos.getY() - 1].getNature() != RIVIERE)
								|| this.carte[pos.getX()][pos.getY()].getPersonnage(0).chemin())) {
					tmp[1].setX(pos.getX());
					tmp[1].setY(pos.getY() - 1);
				} else {
					tmp[1] = null;
				}
			} else {
				if (this.carte[pos.getX() + 1][pos.getY() - 1].getOccupe() == 0
						&& this.carte[pos.getX() + 1][pos.getY() - 1].getNature() != BORD
						&& ((this.carte[pos.getX() + 1][pos.getY() - 1].getNature() != MONTAGNE
								&& this.carte[pos.getX() + 1][pos.getY() - 1].getNature() != RIVIERE)
								|| this.carte[pos.getX()][pos.getY()].getPersonnage(0).chemin())) {
					tmp[1].setX(pos.getX() + 1);
					tmp[1].setY(pos.getY() - 1);
				} else {
					tmp[1] = null;
				}

			}
		}
		/*-- Case gauche --*/
		if (pos.getX() > 0) {
			if (this.carte[pos.getX() - 1][pos.getY()].getOccupe() == 0
					&& this.carte[pos.getX() - 1][pos.getY()].getNature() != BORD
					&& ((this.carte[pos.getX() - 1][pos.getY()].getNature() != MONTAGNE
							&& this.carte[pos.getX() - 1][pos.getY()].getNature() != RIVIERE)
							|| this.carte[pos.getX()][pos.getY()].getPersonnage(0).chemin())) {
				tmp[2].setX(pos.getX() - 1);
				tmp[2].setY(pos.getY());
			} else {
				tmp[2] = null;
			}
		}
		/*-- Case droite --*/
		if (pos.getX() < this.taille - 1) {
			if (this.carte[pos.getX() + 1][pos.getY()].getOccupe() == 0
					&& this.carte[pos.getX() + 1][pos.getY()].getNature() != BORD
					&& ((this.carte[pos.getX() + 1][pos.getY()].getNature() != MONTAGNE
							&& this.carte[pos.getX() + 1][pos.getY()].getNature() != RIVIERE)
							|| this.carte[pos.getX()][pos.getY()].getPersonnage(0).chemin())) {
				tmp[3].setX(pos.getX() + 1);
				tmp[3].setY(pos.getY());
			} else {
				tmp[3] = null;
			}
		}
		/*-- Case bas gauche --*/
		if (pos.getY() < this.taille - 1 && pos.getX() > 0) {
			if (Math.floorMod(pos.getY(), 2) == 0) {
				if (this.carte[pos.getX() - 1][pos.getY() + 1].getOccupe() == 0
						&& this.carte[pos.getX() - 1][pos.getY() + 1].getNature() != BORD
						&& ((this.carte[pos.getX() - 1][pos.getY() + 1].getNature() != MONTAGNE
								&& this.carte[pos.getX() - 1][pos.getY() + 1].getNature() != RIVIERE)
								|| this.carte[pos.getX()][pos.getY()].getPersonnage(0).chemin())) {
					tmp[4].setX(pos.getX() - 1);
					tmp[4].setY(pos.getY() + 1);
				} else {
					tmp[4] = null;
				}
			} else {
				if (this.carte[pos.getX()][pos.getY() + 1].getOccupe() == 0
						&& this.carte[pos.getX()][pos.getY() + 1].getNature() != BORD
						&& ((this.carte[pos.getX()][pos.getY() + 1].getNature() != MONTAGNE
								&& this.carte[pos.getX()][pos.getY() + 1].getNature() != RIVIERE)
								|| this.carte[pos.getX()][pos.getY()].getPersonnage(0).chemin())) {
					tmp[4].setX(pos.getX());
					tmp[4].setY(pos.getY() + 1);
				} else {
					tmp[4] = null;
				}
			}
		}
		/*-- Case bas droite --*/
		if (pos.getY() < this.taille - 1 && pos.getX() < this.taille - 1) {
			if (Math.floorMod(pos.getY(), 2) == 0) {
				if (this.carte[pos.getX()][pos.getY() + 1].getOccupe() == 0
						&& this.carte[pos.getX()][pos.getY() + 1].getNature() != BORD
						&& ((this.carte[pos.getX()][pos.getY() + 1].getNature() != MONTAGNE
								&& this.carte[pos.getX()][pos.getY() + 1].getNature() != RIVIERE)
								|| this.carte[pos.getX()][pos.getY()].getPersonnage(0).chemin())) {
					tmp[5].setX(pos.getX());
					tmp[5].setY(pos.getY() + 1);
				} else {
					tmp[5] = null;
				}
			} else {
				if (this.carte[pos.getX() + 1][pos.getY() + 1].getOccupe() == 0
						&& this.carte[pos.getX() + 1][pos.getY() + 1].getNature() != BORD
						&& ((this.carte[pos.getX() + 1][pos.getY() + 1].getNature() != MONTAGNE
								&& this.carte[pos.getX() + 1][pos.getY() + 1].getNature() != RIVIERE)
								|| this.carte[pos.getX()][pos.getY()].getPersonnage(0).chemin())) {
					tmp[5].setX(pos.getX() + 1);
					tmp[5].setY(pos.getY() + 1);
				} else {
					tmp[5] = null;
				}
			}
		}
		return tmp;

	}

	/*
	 * public Heros trouveHeros() { // Trouve al�atoirement un h�ros sur la carte }
	 * public Heros trouveHeros(Position pos) { // Trouve un h�ros choisi
	 * al�atoirement } public boolean deplaceSoldat(Position pos, Soldat soldat) {
	 * 
	 * } public boolean actionHeros(Position pos, Position pos2) {
	 * 
	 * } public void jouerSoldats(PanneauJeu pj) {
	 * 
	 * } public void mort(Personnage perso) {
	 * 
	 * }
	 */
	/*
	 * public void toutDessiner(Graphics g,int size) { int debut; for(int
	 * i=0;i<size;i++) { for(int j=0;j<size;j++) { if(Math.floorMod(j,2)==0) { debut
	 * =0; } else { debut =12; } if(carte[i][j].getOccupe()>0) {
	 * g.setColor(java.awt.Color.red); } else { switch(carte[i][j].getNature()) {
	 * case Bord: g.setColor(java.awt.Color.white); break; case Riviere:
	 * g.setColor(java.awt.Color.blue); break; case Montagne:
	 * g.setColor(java.awt.Color.gray); break; case Foret:
	 * g.setColor(java.awt.Color.yellow); break; case Plaine:
	 * g.setColor(java.awt.Color.green); break; } }
	 * drawHexagon(debut+(i*26),j*18,g); } } }
	 */

	public void affichePerso(Graphics g, int debut, int i, int j, Personnage perso) {
		/* affiche le personnage à l'aide des infos passés en paramètre */
		/* on veut savoir son id et son joueur pour connaître l'image à afficher */
		File file;
		BufferedImage img;

		switch (perso.getId()) {
		case GARDIEN:
			if (perso.getJoueur() == 0) {
				try {
					file = new File("Wargame/image/m_golem.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				try {
					file = new File("Wargame/image/m_abomination.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case BETE:
			if (perso.getJoueur() == 0) {
				try {
					file = new File("Wargame/image/m_hydre.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				try {
					file = new File("Wargame/image/m_mutilateur.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case INCENDIAIRE:
			if (perso.getJoueur() == 0) {
				try {
					file = new File("Wargame/image/m_drake.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				try {
					file = new File("Wargame/image/m_demoniste.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case DESTRUCTEUR:
			if (perso.getJoueur() == 0) {
				try {
					file = new File("Wargame/image/m_minotaure.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				try {
					file = new File("Wargame/image/m_princeDemon.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case ENCHANTEUR:
			if (perso.getJoueur() == 0) {
				try {
					file = new File("Wargame/image/m_mageGrenouille.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				try {
					file = new File("Wargame/image/m_diablotin.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case COMBATTANT:
			if (perso.getJoueur() == 0) {
				try {
					file = new File("Wargame/image/m_armureAnimee.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				try {
					file = new File("Wargame/image/m_guerrierChaos.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case ARTILLEUR:
			if (perso.getJoueur() == 0) {
				try {
					file = new File("Wargame/image/m_saurus.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				try {
					file = new File("Wargame/image/m_succube.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case DANCELAME:
			if (perso.getJoueur() == 0) {
				try {
					file = new File("Wargame/image/m_vampyrion.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				try {
					file = new File("Wargame/image/m_incube.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case CHASSEUR:
			if (perso.getJoueur() == 0) {
				try {
					file = new File("Wargame/image/m_predator.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				try {
					file = new File("Wargame/image/m_chienEnfer.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case ECLAIREUR:
			if (perso.getJoueur() == 0) {
				try {
					file = new File("Wargame/image/m_worm.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				try {
					file = new File("Wargame/image/m_oeilDiable.png");
					img = ImageIO.read(file);
					g.drawImage(img, debut + (i * 28), j * 24, null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		}
		/* affichage des barres de vie */if (perso.getJoueur() == 0) {
			g.setColor(new Color((0), (250), (0), 175));
		} else {
			g.setColor(new Color((250), (0), (0), 205));
		}
		g.fillRect(debut + (i * 28) + 16 - (perso.getPvActuel() / 8), j * 24 + 24, (perso.getPvActuel() / 4), 2);
	}

	public int distance(Position p1, Position p2) {
		if (p1.getX() == p2.getX()) {
			/* Retourne la difference de y si les x sont egaux */
			return Math.abs(p1.getY() - p2.getY());
		} else if (p1.getY() == p2.getY()) {
			/* Retourne la difference de x si les y sont egaux */
			return Math.abs(p1.getX() - p2.getX());
		} else {
			/* dy est egale a la difference de y */
			double dy = Math.abs(p1.getY() - p2.getY());
			if (Math.floorMod(p1.getY(), 2) == 0) {
				/* Si le y de p1 est pair */
				if (p2.getX() > p1.getX()) {
					/* Si le x de p2 est > a p1 */
					/* decalage possible == entier superieur de la (hauteur-1)/2 */
					int decalage = (int) (Math.ceil((dy - 1) / 2));
					if (p2.getX() - p1.getX() <= decalage) {
						/*
						 * si la difference de x est inferieur au decalage on retourne la difference de
						 * y
						 */
						return (int) dy;
					} else {
						/* sinon on retourne la hauteur + la difference de x moins le decalage */
						return (int) dy + p2.getX() - p1.getX() - decalage;
					}
				} else {
					int decalage = (int) (Math.ceil(dy / 2));
					if (p1.getX() - p2.getX() <= decalage) {
						return (int) dy;
					} else {
						return (int) dy + p1.getX() - p2.getX() - decalage;
					}
				}
			} else {
				/* Si le y de p1 est impair */
				if (p2.getX() > p1.getX()) {
					/* si le x de p2 */
					int decalage = (int) (Math.ceil(dy / 2));
					if (p2.getX() - p1.getX() <= decalage) {
						return (int) dy;
					} else {
						return (int) dy + p2.getX() - p1.getX() - decalage;
					}
				} else {
					int decalage = (int) (Math.ceil((dy - 1) / 2));
					if (p1.getX() - p2.getX() <= decalage) {
						return (int) dy;
					} else {
						return (int) dy + p1.getX() - p2.getX() - decalage;
					}
				}
			}
		}
	}

	public void toutDessiner(Graphics g, int equipe) {
		int debut;

		for (int i = 0; i < LARGEUR_CARTE; i++) {
			for (int j = 0; j < LARGEUR_CARTE; j++) {
				if (Math.floorMod(j, 2) == 0) {
					debut = -14;
				} else {
					debut = 0;
				}
				if (carte[i][j].getEtat(equipe) == VISIBLE) {
					switch (carte[i][j].getNature()) {
					case BORD:
						g.setColor(java.awt.Color.white);
						break;
					case RIVIERE:
						try {
							File file = new File("Wargame/image/m_eau_1.png");
							BufferedImage img = ImageIO.read(file);
							g.drawImage(img, debut + (i * 28), j * 24, null);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case MONTAGNE:
						try {
							File file = new File("Wargame/image/m_montagne_1.png");
							BufferedImage img = ImageIO.read(file);
							g.drawImage(img, debut + (i * 28), j * 24, null);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case FORET:
						try {
							File file = new File("Wargame/image/m_foret_1.png");
							BufferedImage img = ImageIO.read(file);
							g.drawImage(img, debut + (i * 28), j * 24, null);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case PLAINE:
						try {
							File file = new File("Wargame/image/m_plaine_1.png");
							BufferedImage img = ImageIO.read(file);
							g.drawImage(img, debut + (i * 28), j * 24, null);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					}
				}
				if (this.carte[i][j].getEtat(equipe) == DECOUVERT) {
					switch (carte[i][j].getNature()) {
					case BORD:
						g.setColor(java.awt.Color.white);
						break;
					case RIVIERE:
						try {
							File file = new File("Wargame/image/m_eau_2.png");
							BufferedImage img = ImageIO.read(file);
							g.drawImage(img, debut + (i * 28), j * 24, null);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case MONTAGNE:
						try {
							File file = new File("Wargame/image/m_montagne_2.png");
							BufferedImage img = ImageIO.read(file);
							g.drawImage(img, debut + (i * 28), j * 24, null);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case FORET:
						try {
							File file = new File("Wargame/image/m_foret_2.png");
							BufferedImage img = ImageIO.read(file);
							g.drawImage(img, debut + (i * 28), j * 24, null);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case PLAINE:
						try {
							File file = new File("Wargame/image/m_plaine_2.png");
							BufferedImage img = ImageIO.read(file);
							g.drawImage(img, debut + (i * 28), j * 24, null);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					}
				}
				/* on affiche ensuite les personnages */
				if (carte[i][j].getOccupe() > 0) {
					/* on affiche tout les personnages prÃ©sent sur cette case */
					for (int y = 0; carte[i][j].getPersonnage(y) != null; y++) {
						if (carte[i][j].getEtat(equipe) == VISIBLE) {
							affichePerso(g, debut, i, j, carte[i][j].getPersonnage(y));
						}
					}
				}
			}
		}
	}

	public void Deplacement(Position posD, Position posA) {
		/*--- D�placement du personnage ---*/
		this.carte[posA.getX()][posA.getY()].rajoutPersonnage(this.carte[posD.getX()][posD.getY()].getPersonnage(0));
		this.carte[posD.getX()][posD.getY()].enleverPersonnage(
				this.carte[posD.getX()][posD.getY()].getPersonnage(0).getId(),
				this.carte[posD.getX()][posD.getY()].getPersonnage(0).getJoueur());
		this.carte[posA.getX()][posA.getY()].getPersonnage(0).setPos(posA);
		int i = this.carte[posA.getX()][posA.getY()].getPersonnage(0).getPm() - 1;
		this.carte[posA.getX()][posA.getY()].getPersonnage(0).setPm(i);
	}

	public void decouvrir(int numequipe, Personnage[][] equipe) {
		int i, j, k;
		Personnage perso = null;
		for (i = 0; i < LARGEUR_CARTE; i++) {
			for (j = 0; j < LARGEUR_CARTE; j++) {
				if (this.carte[i][j].getEtat(numequipe) == VISIBLE) {
					for (k = 0; k < NBPERSONNAGE; k++) {
						perso = equipe[k][numequipe];
						if (distance(perso.getPos(), new Position(i, j)) > perso.getVision()) {
							this.carte[i][j].setEtat(numequipe, DECOUVERT);
						}
					}
				}
				if (this.carte[i][j].getEtat(numequipe) == NON_DECOUVERT
						|| this.carte[i][j].getEtat(numequipe) == DECOUVERT) {
					for (k = 0; k < NBPERSONNAGE; k++) {
						perso = equipe[k][numequipe];
						if (distance(perso.getPos(), new Position(i, j)) <= perso.getVision()) {
							this.carte[i][j].setEtat(numequipe, VISIBLE);
						}
					}
				}
			}
		}
	}

}
