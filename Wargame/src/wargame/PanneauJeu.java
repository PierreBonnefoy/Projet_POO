package wargame;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.lang.Thread;


public class PanneauJeu extends JPanel implements IConfig {
	public static final int taille_fenetre=800;
	public int[] nb_vivant = new int[NBJOUEUR];
	public int indicePerso =NBPERSONNAGE-1;
	public int indiceJoueur=-1;
	public Carte jeu=new Carte(LARGEUR_CARTE,MAXRIVIERE,MAXMONTAGNE,MAXFORET);
	boolean jouable=true;
	public Position posbuffer=new Position(0,0);
	Personnage perso=new Personnage(ECLAIREUR,JOUEUR,15,10);
	boolean[] mort;
	//IA robot = new IA();
	
	/*creation des personnages */
	public Personnage[][] equipe;
	public Personnage abomination;
	public Personnage mutilateur;
	public Personnage demoniste;
	public Personnage demon;
	public Personnage diablotin;
	public Personnage guerrier;
	public Personnage succube;
	public Personnage incube;
	public Personnage cerbere;
	public Personnage oeil;
	public Personnage golem;
	public Personnage hydre;
	public Personnage drake;
	public Personnage minotaure;
	public Personnage grenouille;
	public Personnage armure;
	public Personnage lezard;
	public Personnage vampyrion;
	public Personnage predator;
	public Personnage worm;
	JLabel infoLabel = new JLabel ("<html>"+perso.toString()+"<html>");
	JLabel texteFin = new JLabel();
	JPanel boutonPanel = new JPanel();
	JPanel infoPanel = new JPanel();
	JPanel ecranFin = new JPanel();
	JSplitPane splitVert = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	Icon icon;
	/**
	 * Constructeur du panneau de jeuappeler par menu general
	 */
	public PanneauJeu(){
		ecranFin.setPreferredSize(new Dimension(LARGEUR_FENETRE,HAUTEUR_FENETRE));
		this.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
		infoLabel.setForeground(Color.white);
		nb_vivant[JOUEUR] = NBPERSONNAGE;
		nb_vivant[IA] = NBPERSONNAGE;
		
		this.setPreferredSize(new Dimension(LARGEUR_FENETRE,HAUTEUR_FENETRE));
		this.setBackground(Color.black);
		/*initialisation du tableau d'equipe*/
		
		
		mort = new boolean[NBJOUEUR];
		equipe = new Personnage[NBPERSONNAGE][NBJOUEUR];
		
		abomination = new Personnage(GARDIEN,IA,28,19);
		mutilateur = new Personnage(BETE,IA,28,20);
		demoniste = new Personnage(INCENDIAIRE,IA,28,21);
		demon = new Personnage(DESTRUCTEUR,IA,28,22);
		diablotin = new Personnage(ENCHANTEUR,IA,28,23);
		guerrier = new Personnage(COMBATTANT,IA,28,24);
		succube = new Personnage(ARTILLEUR,IA,28,25);
		incube = new Personnage(DANCELAME,IA,28,26);
		cerbere = new Personnage(CHASSEUR,IA,28,27);
		oeil = new Personnage(ECLAIREUR,IA,28,28);

		golem = new Personnage(GARDIEN,JOUEUR,1,10);
		hydre = new Personnage(BETE,JOUEUR,1,9);
		drake = new Personnage(INCENDIAIRE,JOUEUR,1,8);
		minotaure = new Personnage(DESTRUCTEUR,JOUEUR,1,7);
		grenouille = new Personnage(ENCHANTEUR,JOUEUR,1,6);
		armure = new Personnage(COMBATTANT,JOUEUR,1,5);
		lezard = new Personnage(ARTILLEUR,JOUEUR,1,4);
		vampyrion = new Personnage(DANCELAME,JOUEUR,1,3);
		predator = new Personnage(CHASSEUR,JOUEUR,1,2);
		worm = new Personnage(ECLAIREUR,JOUEUR,1,1);
		
		equipe[0][IA] = abomination;
		equipe[1][IA] = mutilateur;
		equipe[2][IA] = demoniste;
		equipe[3][IA] = demon;
		equipe[4][IA] = diablotin;
		equipe[5][IA] = guerrier;
		equipe[6][IA] = succube;
		equipe[7][IA] = incube;
		equipe[8][IA] = cerbere;
		equipe[9][IA] = oeil;
		
		equipe[0][JOUEUR] = golem;
		equipe[1][JOUEUR] = hydre;
		equipe[2][JOUEUR] = drake;
		equipe[3][JOUEUR] = minotaure;
		equipe[4][JOUEUR] = grenouille;
		equipe[5][JOUEUR] = armure;
		equipe[6][JOUEUR] = lezard;
		equipe[7][JOUEUR] = vampyrion;
		equipe[8][JOUEUR] = predator;
		equipe[9][JOUEUR] = worm;
		
		for(int i=0;i<NBPERSONNAGE;i++) {
			for(int j=0;j<NBJOUEUR;j++) {
				jeu.carte[equipe[i][j].getPos().getX()][equipe[i][j].getPos().getY()].rajoutPersonnage(equipe[i][j]);
			}
		}
		
		JButton Save = new JButton("Save");
		
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream file = new FileOutputStream("Wargame/save.ser");
					ObjectOutputStream save = new ObjectOutputStream(file);
					save.writeObject(nb_vivant);
					save.write(indicePerso);
					save.write(indiceJoueur);
					save.writeObject(jeu);
					save.writeObject(equipe);
					save.close();
					file.close();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		);
		
		JButton Load = new JButton("Load");
		
		Load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileInputStream file = new FileInputStream("Wargame/save.ser");
					ObjectInputStream load = new ObjectInputStream(file);
					nb_vivant = (int[]) load.readObject();
					indicePerso = (int) load.read();
					indiceJoueur = (int) load.read();
					jeu = (Carte) load.readObject();
					equipe = (Personnage[][]) load.readObject();
					repaint(); //reinitialiser l'affichage lors du chargement
					load.close();
					file.close();
				}catch(IOException ex) {
					ex.printStackTrace();
					return;
				}catch(ClassNotFoundException exe) {
					System.out.println("Une class n'a pas ete trouve !");
					exe.printStackTrace();
					return;
				}
			}
		}
		);
		JButton Quit = new JButton("Quitter");
		
		Quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGeneral menu = new MenuGeneral();
				removeAll();
				revalidate();
				add(menu);
				validate();
			}
		}
		);
		infoPanel.setBackground(Color.gray);
		icon =new ImageIcon("Wargame/image/m_"+perso.getNom()+".png");
		infoLabel.setIcon(icon);
		infoPanel.add(infoLabel);
		boutonPanel.setBackground(Color.gray);
		boutonPanel.add(Save);
		boutonPanel.add(Load);
		boutonPanel.add(Quit);
		splitVert.add(infoPanel);
		splitVert.add(boutonPanel);
		add(splitVert);
		clickPosition();
		//repaint();	//aucune utilit?? trouv??e
		tourDeJeux();
	}
	
	/**
	 * Procede au changement du personnage et verifie si la partie n'est pas finie
	 */
	public void tourDeJeux() {
		/*boucle en parcourant le tableau des Personnages, et joue chacun des personnages a tour de r??le*/
		indiceJoueur+=1;
		if(indiceJoueur==NBJOUEUR) {
			indiceJoueur=0;
			indicePerso-=1;
			//System.out.println("dans le if, indiceperso : "+indicePerso+" ; indicejoueur :"+indiceJoueur);
			//indicePerso-=1;
			if(indicePerso<0) {
				indicePerso=NBPERSONNAGE-1;
			}
		}
		
		/*on affiche une premi??re fois l'environnement autours de l'??quipe du joueur*/
		jeu.decouvrir(indiceJoueur,equipe);
        //repaint();  	//aucune utilit?? trouv??e 
        jouable=true;
		
		//System.out.println("test avant lacc??s au perso du tablo, indiceperso : "+indicePerso+" ; indicejoueur :"+indiceJoueur);
		
		perso=equipe[indicePerso][indiceJoueur];
		
		//System.out.println("test apres lacc??s au perso du tablo");
		
		
		if(nb_vivant[JOUEUR]==0 || nb_vivant[IA]==0) {
			if(nb_vivant[JOUEUR]==0) {
				//VICTOIRE IA
				MenuFin fin = new MenuFin(false);
				super.removeAll();
				add(fin);
				super.revalidate();
				super.repaint(); //mettre ?? jour pour afficher le menu de fin
			}
			else {
				//VICTOIRE JOUEUR
				MenuFin fin = new MenuFin(true);
				super.removeAll();
				add(fin);
				super.revalidate();
				super.repaint();//mettre ?? jour pour afficher le menu de fin
			}
		}
		if(perso.getEtat() != MORT) {
			
			if(indiceJoueur != JOUEUR) {
				//System.out.println("IIIIIIIIIIIAAAAAAAAAAAAAAAAA");
				perso.setAttaque(1);
				perso.setPm(perso.getVitesse());
				jouable = true;
				tour(jeu,equipe,indicePerso,mort,jouable,nb_vivant);
				jeu.decouvrir(JOUEUR,equipe);
				//repaint(); //aucune utilit?? trouv??e 
				tourDeJeux();
			}
			else {
				/*
				perso.setAttaque(1);
				perso.setPm(perso.getVitesse());
				jouable = true;
				robot.tour(jeu,equipe,indicePerso,mort);
				*/
				//System.out.println("JJJJJJJJJJJJJJOOOOOOOOOOEEEEEEUUUUUUUUUURRRRRRRR");
				jouable=true;
				perso.setAttaque(1);
				perso.setPm(perso.getVitesse());
				//tourDeJeux();
			}
		}else {
		/*	if(perso.getJoueur() == 0) {
				g.setColor(new Color((0),(250),(0),105));
			}
			else {
				g.setColor(new Color((250),(0),(0),135));
			}
		
			this.drawHexagon((debut)+(i*28)+17, j*24+5, g);*/
			tourDeJeux(); 
			}
		
	}
	
	/**
	 * Description d'un tour d'une IA
	 * @param jeu la carte courante
	 * @param equipe liste des personnages
	 * @param indicePerso numero du personnage a jouer
	 * @param mort resultat de l'attaque
	 * @param jouable si l'IA peut jouer
	 * @param nb_vivant nombre de personnages vivants dans chaque equipe
	 */
	public void tour(Carte jeu,Personnage[][] equipe,int indicePerso,boolean[] mort,boolean jouable,int nb_vivant[]) {
		Personnage courant = equipe[indicePerso][IA];
		Personnage cible=null;
		int x=0;
		if(courant.getPvActuel()<(courant.getPvMax()/2)) {
			courant.repos();
		}
		
		while(cible==null && x<NBPERSONNAGE) {
			if(equipe[x][JOUEUR].getEtat()!=MORT) {
				cible=equipe[x][JOUEUR];
			}
			x++;
		}
		for(x=0;x<NBPERSONNAGE;x++) {
			if(equipe[x][JOUEUR].getEtat()!=MORT) {
				if(jeu.distance(courant.getPos(), equipe[x][JOUEUR].getPos())<jeu.distance(courant.getPos(), cible.getPos())) {
					cible=equipe[x][JOUEUR];
				}
			}
		}
		
		//while(courant.getPm()>0) {--------------------------------------------------------------
		for(courant.getPm() ; courant.getPm() != 0 ; courant.setPm(courant.getPm() -1)) {
			
				/*--- On test si un perso ennemi est ?? pert??e ---*/
				if(jeu.distance(courant.getPos(),cible.getPos())<=courant.getPortee() && courant.getAttaque()>0) {
					/*--- Si oui on l'attaque ---*/
					if(cible.getJoueur() != IA) {
						System.out.println(courant.nomPersonnage()+" attaque "+cible.nomPersonnage());
						courant.setAttaque(courant.getAttaque()-1); //attaque
						mort[0] = courant.attaque(cible);
						
						if(mort[0]) {
							jeu.carte[cible.getPos().getX()][(cible.getPos().getY())].enleverPersonnage(cible.getId(), cible.getJoueur());
							nb_vivant[JOUEUR]-=1;
						}
						else {
						/*si le defenseur peut riposter, il riposte*/
							if(cible.getPortee() >= jeu.distance(courant.getPos(),cible.getPos()) && cible.getRiposte() > 0 && !cible.embrasement()) {
								System.out.println("portee riposte : "+cible.getPortee()+"\ndistance :"/*+jeu.distance(perso.getPos(),positionCase)*/);
								
								System.out.println(cible.nomPersonnage()+" riposte contre "+courant.nomPersonnage());
								mort[1] = cible.attaque(courant); //riposte
								cible.setRiposte(cible.getRiposte()-1);
								
								if(mort[1]) {
									System.out.println(cible.nomPersonnage()+" a tue en ripostant "+courant.nomPersonnage());
									jeu.carte[courant.getPos().getX()][courant.getPos().getY()].enleverPersonnage(courant.getId(), courant.getJoueur());
									nb_vivant[IA]-=1;
									courant.passeTour();
								}
							}
							else {
								System.out.println(cible.nomPersonnage()+" ne peut pas riposter contre "+courant.nomPersonnage());
							}
						}
					}
				}
				else {
					
					int j=0;
					Position tmp[] = jeu.PositionPossible(courant.getPos());
					
					Position best=null;
					while(best==null && j<6) {
						best=tmp[j];
						j++;
					}
					for(j=0; j<6;j++) {
						if(tmp[j]!=null) {
							if(jeu.distance(tmp[j], cible.getPos())<jeu.distance(best, cible.getPos())) {
								best=tmp[j];
							}
						}
					}
					if(best != null) {
						try {
			                //on fait attendre histoire de mettre une certaine animation
			                Thread.sleep(250);
			                repaint();
			                Thread.sleep(250);
			                System.out.println(courant.nomPersonnage()+" seleep");
			                
						}
						catch (Exception e) {
							// on prend l'exception
							System.out.println(e);
							System.out.println("erreur");
						}
						repaint();
						jeu.Deplacement(courant.getPos(), best);
						System.out.println(courant.nomPersonnage()+" se d??place");
						/*infoLabel.setText("<html>"+courant.toString()+"<html>");
						icon =new ImageIcon("./image/m_"+courant.getNom()+".png");
						infoLabel.setIcon(icon);
						infoPanel.add(infoLabel);
						validate();*/
						repaint();
					}
					else {
						courant.passeTour();
					}
				}
				
		}
		courant.passeTour();
		if(courant.getAttaque()==0 && courant.getPm() == 0) {
			courant.entrainement();
			courant.regeneration();
			courant.recupRiposte();
			jouable=false;
			
		}

	}
	
	
	/**
	 * Appele la fonction qui afffiche la carte
	 * @param g un Graphics
	 */
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		jeu.toutDessiner(g,JOUEUR);
	}
	/**
	 * Renvoie la position de la case la plus proche du clique entre les quatre l'entourant
	 * @param x1 x minimum
	 * @param x2 x maximum
	 * @param y1 y minimum
	 * @param y2 y maximum
	 * @param mx pixel x du clique de la souris
	 * @param my pixel y du clique de la souris
	 * @param haut indique si il y a ubn decalage
	 * @return la position de la case la plus proche
	 */
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
 	
	
	/**
	 * Cette fonction s'occupe de la gestion du mouvement et du clique de la souris
	 */
	public Position clickPosition(){
		posbuffer = null;
		
		//while(posbuffer == null) {
			//System.out.println("null");
			
			addMouseMotionListener(new MouseAdapter() {
				public void mouseMoved(MouseEvent e) {
					/*----Recuperation de la case dans posbuffer---*/
					Position pos2=null;
					Position positionCase=null;
					

					remove(infoLabel);
					Personnage perso=equipe[indicePerso][indiceJoueur];
					infoLabel.setText("<html>"+perso.toString()+"<html>");
					icon =new ImageIcon("Wargame/image/g_"+perso.getNom()+".png");
					infoLabel.setIcon(icon);
					infoPanel.add(infoLabel);
					validate();
					int y1,y2,x1,x2,pair;
					/*---Recupere les y qui entoure---*/
					y1=14+24*((e.getY()-14)/24);
					y2=14+24*(1+(e.getY()-14)/24);
					
					/*---Verifie si c'est la premiere ou la 2eme lignes qui est decal???---*/
					pair=Math.floorMod(y1/24,2);
					/*---Recupere les x qui l'entoure---*/
					x1=28*((e.getX())/28);
					x2=28*(1+(e.getX())/28);
					/*---Verifie parmi les 4 voisins, lequel est le plus proche---*/
					pos2=ppV(x1,x2,y1,y2,e.getX(),e.getY(),pair);
					//System.out.println("proche x = "+pos2.getX()+"proche y = "+pos2.getY());
					//System.out.println("case = ["+pos2.getX()/28+","+(pos2.getY()/24)+"]");
					posbuffer = new Position(pos2.getX()/28,pos2.getY()/24);
					if(pos2.getX()/28 <= jeu.taille-1 && pos2.getY()/24 <= jeu.taille-1 && pos2.getX()/28 > -1 && pos2.getY()/28 > -1 && jeu.carte[pos2.getX()/28][pos2.getY()/24].getEtat(JOUEUR)==VISIBLE ) {
						//System.out.println("position case : "+pos2.getX()/28+":"+pos2.getY()/24);
						//System.out.println("taille carte : "+jeu.taille);
						//System.out.println("case : nature="+jeu.carte[pos2.getX()/28][pos2.getY()/24].getNature()+" ; occupation="+jeu.carte[pos2.getX()/28][pos2.getY()/24].getOccupe());
						
						if(jeu.carte[pos2.getX()/28][pos2.getY()/24].getOccupe() != 0) {
							if(perso.nomPersonnage() != jeu.carte[pos2.getX()/28][pos2.getY()/24].getPersonnage(0).nomPersonnage()) {
								remove(infoLabel);
								Personnage cible=jeu.carte[pos2.getX()/28][pos2.getY()/24].getPersonnage(0);
								infoLabel.setText("<html>"+perso.toString()+cible.toString()+"<html>");
								icon =new ImageIcon("./image/m_"+perso.getNom()+".png");
								infoLabel.setIcon(icon);
								infoPanel.add(infoLabel);
								validate();
							}
						}
											
					}
			
			}});
			
			addMouseListener(new MouseAdapter() {	
				public void mouseReleased(MouseEvent e) {
					/*----Recuperation de la case dans posbuffer---*/
					Position pos2=null;
					Position positionCase=null;
					

					remove(infoLabel);
					Personnage perso=equipe[indicePerso][indiceJoueur];
					infoLabel.setText("<html>"+perso.toString()+"<html>");
					icon =new ImageIcon("./image/m_"+perso.getNom()+".png");
					infoLabel.setIcon(icon);
					infoPanel.add(infoLabel);
					validate();
					int y1,y2,x1,x2,pair;
					/*---Recupere les y qui entoure---*/
					y1=14+24*((e.getY()-14)/24);
					y2=14+24*(1+(e.getY()-14)/24);
					
					/*---Verifie si c'est la premiere ou la 2eme lignes qui est decal???---*/
					pair=Math.floorMod(y1/24,2);
					/*---Recupere les x qui l'entoure---*/
					x1=28*((e.getX())/28);
					x2=28*(1+(e.getX())/28);
					/*---Verifie parmi les 4 voisins, lequel est le plus proche---*/
					pos2=ppV(x1,x2,y1,y2,e.getX(),e.getY(),pair);
					//System.out.println("proche x = "+pos2.getX()+"proche y = "+pos2.getY());
					//System.out.println("case = ["+pos2.getX()/28+","+(pos2.getY()/24)+"]");
					posbuffer = new Position(pos2.getX()/28,pos2.getY()/24);
					positionCase=posbuffer;
					
					
					
					/*----Jeu du perso----*/
					if(jouable==true) {
						//System.out.println("joueur n??? " + indiceJoueur + "joue avec le perso : "+perso.nomPersonnage());
						//System.out.println("je suis a la position : "  + perso.getPos().getX()+ " " + perso.getPos().getY());
						int xd=perso.getPos().getX(),yd=perso.getPos().getY();
						mort[0] = false;
						mort[1] = false;
						
						if(positionCase.getX()>0 && positionCase.getX()<jeu.taille-1 && positionCase.getY()>0 && positionCase.getY()<jeu.taille-1) {
							int xa = positionCase.getX();
							int ya = positionCase.getY();
							//System.out.println("je pointe vers la position : "  + xa+ " " + ya);
							//System.out.println("distance de : "+jeu.distance(perso.getPos(),positionCase));
							/*si le joueur a cliqu?? sur la case o?? se trouve le perso qui joue, il se repose, ou passe son tour*/
							if(xa==xd && ya==yd) {
								if(perso.getAttaque()==1 && perso.getPm() == perso.getVitesse()) {
									perso.repos();
								}
								else {
									perso.passeTour();
								}
								infoLabel.setText("<html>"+perso.toString()+"<html>");
								icon =new ImageIcon("./image/m_"+perso.getNom()+".png");
								infoLabel.setIcon(icon);
								infoPanel.add(infoLabel);
								validate();
								repaint(); //affichage du label
							}
							/*le perso a clique sur une case ou se trouve un personnage, on verifie si elle est a portee d'attaque et sil peut attaquer*/
							else if(jeu.carte[xa][ya].getOccupe() != 0 && perso.getPortee() >= jeu.distance(perso.getPos(),positionCase)  && perso.getAttaque() > 0) {
								/*si le personnage est un ennemi*/
								Personnage cible = jeu.carte[positionCase.getX()][positionCase.getY()].getPersonnage(0);
								if(cible.getJoueur() != indiceJoueur) {
									System.out.println(perso.nomPersonnage()+" attaque "+cible.nomPersonnage());
									perso.setAttaque(perso.getAttaque()-1); //attaque
									mort[0] = perso.attaque(cible);
									
									if(mort[0]) {
										System.out.println(perso.nomPersonnage()+" a tue en attaquant "+cible.nomPersonnage());
										jeu.carte[xa][ya].enleverPersonnage(cible.getId(), cible.getJoueur());
										nb_vivant[IA]-=1;
									}
									else {
									/*si le defenseur peut riposter, il riposte*/
										if(cible.getPortee() >= jeu.distance(perso.getPos(),positionCase) 
												&& cible.getRiposte() > 0
												&& !perso.embrasement()) {
											//System.out.println("portee riposte : "+cible.getPortee()+"\ndistance :"+jeu.distance(perso.getPos(),positionCase));
											
											System.out.println(cible.nomPersonnage()+" riposte contre "+perso.nomPersonnage());
											mort[1] = cible.attaque(perso); //riposte
											cible.setRiposte(cible.getRiposte()-1);
											
											if(mort[1]) {
												System.out.println(perso.nomPersonnage()+" a tue en ripostant "+cible.nomPersonnage());
												jeu.carte[xd][yd].enleverPersonnage(perso.getId(), perso.getJoueur());
												nb_vivant[JOUEUR]-=1;
												tourDeJeux();
											}
										}
										else {
											System.out.println(cible.nomPersonnage()+" ne peut pas riposter contre "+perso.nomPersonnage());
										}
									}
									
								} /*le personnage est un alli??*/
								else {
									perso.encouragement(cible);
								}
								perso.setAttaque(perso.getAttaque()-1);
								infoLabel.setText("<html>"+perso.toString()+"<html>");
								icon =new ImageIcon("./image/m_"+perso.getNom()+".png");
								infoLabel.setIcon(icon);
								infoPanel.add(infoLabel);
								validate();
								jeu.decouvrir(indiceJoueur,equipe);
								//repaint(); aucune utilit?? trouv??e
								}
							
							/*le joueur a cliqu??? sur une case vide a cote du perso et perso.pm!=0*/
							else if(jeu.carte[xa][ya].getOccupe() == 0 && ((jeu.carte[xa][ya].getNature() != MONTAGNE && jeu.carte[xa][ya].getNature() != RIVIERE) || perso.chemin()) && perso.getPm() >0 && (jeu.distance(perso.getPos(),positionCase) == 1) ) {
								//System.out.println(perso.nomPersonnage()+" se deplace en "+positionCase.getX()+":"+positionCase.getY());
								jeu.Deplacement(new Position(xd,yd), positionCase);
								jeu.decouvrir(indiceJoueur,equipe);
								infoLabel.setText("<html>"+perso.toString()+"<html>");
								icon =new ImageIcon("./image/m_"+perso.getNom()+".png");
								infoLabel.setIcon(icon);
								infoPanel.add(infoLabel);
								validate();
								repaint(); //mise a jour apr??s d??placement du personnage 
							}
							else {
					    		//System.out.println(jeu.carte[xd][yd].getPersonnage(0).nomPersonnage()+" ne peut pas se d??placer ici !");
					    	}
						}
						if(perso.getAttaque()==0 && perso.getPm() == 0) {
							perso.entrainement();
							perso.regeneration();
							perso.recupRiposte();
							jouable=false;
							tourDeJeux();
						}
					}
			}});
			
		//}
		//System.out.println("on recupere click :");
		return posbuffer;
	}
	
}

