package wargame;

public class Personnage implements IConfig{
	/*Cette classe est TEMPORAIRE : on decidera plus tard de si on la conserve sous ce nom ou si on met les fonctions dans une autre classe.*/
	
	private int pvMax;  //les PV totaux du personnage
	private int pvActuel; //les PV actuels du personnage, ne peut pas dépasser pvMax
	private int degat; //les dégats lorsque le personnage attaque
	private int attaque; //1 si le personnage n'a pas encore attaqué, 0 sinon
	private int protection; //la valeur de Protection (entre 0 et 100)
	private int blindage; //la valeur de Blindage
	private int taille; //la place que prend un monstre sur une case (entre 1 et 3)
	private int id; //l'ID du monstre (spécifique à lui) ainsi que son ordre de tour de jeux.
	private int joueur; //les monstres de chaque camps partagent la même ID, Joueur permet de savoir à quel camp appartient le monstre
	private int portee; //la portée d'attaque du monstre, au minimum de 1
	private int vision; //le champ de vision du monstre
	private int vitesse; //la vitesse de déplacement maximale du monstre
	private int pm; //(Point de Marche)la vitesse restante du monstre, ne peut dépasser vitesse
	private int riposte; //le nombre de riposte restante du monstre (soit 1, soit 0, sauf cas particulier)
	private int nemesis ; //l'ID du nemesis du monstre
	private Position position; //position x et y du personnage
	private int exp; //l'expérience actuelle du personnage
	private int niveau; //le niveau du personnage (commence à 0)
	private int etat; //vivant ou mort

	/*-- Constructeur --*/
	public Personnage(int id, int joueur,int x,int y) {
		switch(id) {
		case GARDIEN:
			this.position=new Position(x,y);
			this.pvMax = 65;
			this.pvActuel = 65;
			this.degat = 10;
			this.attaque = 1;
			this.protection = 80;
			this.blindage = 8;
			this.taille = 3;
			this.id = id;
			this.joueur = joueur;
			this.portee = 1;
			this.vision = 2;
			this.vitesse = 3;
			this.pm = 3;
			this.riposte = 3;
			this.nemesis = DANCELAME;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case BETE:
			this.position=new Position(x,y);
			this.pvMax = 65;
			this.pvActuel = 65;
			this.degat = 10;
			this.attaque = 1;
			this.protection = 40;
			this.blindage = 5;
			this.taille = 3;
			this.id = id;
			this.joueur = joueur;
			this.portee = 1;
			this.vision = 4;
			this.vitesse = 5;
			this.pm = 5;
			this.riposte = 1;
			this.nemesis = CHASSEUR;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case INCENDIAIRE:
			this.position=new Position(x,y);
			this.pvMax = 45;
			this.pvActuel = 45;
			this.degat = 10;
			this.attaque = 1;
			this.protection = 60;
			this.blindage = 4;
			this.taille = 2;
			this.id = id;
			this.joueur = joueur;
			this.portee = 3;
			this.vision = 4;
			this.vitesse = 3;
			this.pm = 3;
			this.riposte = 1;
			this.nemesis = COMBATTANT;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case DESTRUCTEUR:
			this.position=new Position(x,y);
			this.pvMax = 50;
			this.pvActuel = 50;
			this.degat = 15;
			this.attaque = 1;
			this.protection = 60;
			this.blindage = 4;
			this.taille = 2;
			this.id = id;
			this.joueur = joueur;
			this.portee = 1;
			this.vision = 3;
			this.vitesse = 4;
			this.pm = 4;
			this.riposte = 1;
			this.nemesis = GARDIEN;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case ENCHANTEUR:
			this.position=new Position(x,y);
			this.pvMax = 45;
			this.pvActuel = 45;
			this.degat = 8;
			this.attaque = 1;
			this.protection = 40;
			this.blindage = 3;
			this.taille = 1;
			this.id = id;
			this.joueur = joueur;
			this.portee = 3;
			this.vision = 4;
			this.vitesse = 4;
			this.pm = 4;
			this.riposte = 1;
			this.nemesis = DESTRUCTEUR;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case COMBATTANT:
			this.position=new Position(x,y);
			this.pvMax = 55;
			this.pvActuel = 55;
			this.degat = 12;
			this.attaque = 1;
			this.protection = 60;
			this.blindage = 6;
			this.taille = 2;
			this.id = id;
			this.joueur = joueur;
			this.portee = 1;
			this.vision = 4;
			this.vitesse = 4;
			this.pm = 4;
			this.riposte = 3;
			this.nemesis = BETE;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case ARTILLEUR:
			this.position=new Position(x,y);
			this.pvMax = 50;
			this.pvActuel = 50;
			this.degat = 8;
			this.attaque = 1;
			this.protection = 40;
			this.blindage = 4;
			this.taille = 2;
			this.id = id;
			this.joueur = joueur;
			this.portee = 6;
			this.vision = 4;
			this.vitesse = 4;
			this.pm = 4;
			this.riposte = 1;
			this.nemesis = ECLAIREUR;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case DANCELAME:
			this.position=new Position(x,y);
			this.pvMax = 50;
			this.pvActuel = 50;
			this.degat = 12;
			this.attaque = 1;
			this.protection = 40;
			this.blindage = 3;
			this.taille = 2;
			this.id = id;
			this.joueur = joueur;
			this.portee = 2;
			this.vision = 3;
			this.vitesse = 5;
			this.pm = 5;
			this.riposte = 1;
			this.nemesis = INCENDIAIRE;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case CHASSEUR:
			this.position=new Position(x,y);
			this.pvMax = 45;
			this.pvActuel = 45;
			this.degat = 15;
			this.attaque = 1;
			this.protection = 40;
			this.blindage = 4;
			this.taille = 2;
			this.id = id;
			this.joueur = joueur;
			this.portee = 1;
			this.vision = 4;
			this.vitesse = 7;
			this.pm = 7;
			this.riposte = 1;
			this.nemesis = ARTILLEUR;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case ECLAIREUR:
			this.position=new Position(x,y);
			this.pvMax = 40;
			this.pvActuel = 40;
			this.degat = 8;
			this.attaque = 1;
			this.protection = 40;
			this.blindage = 3;
			this.taille = 1;
			this.id = id;
			this.joueur = joueur;
			this.portee = 3;
			this.vision = 6;
			this.vitesse = 6;
			this.pm = 6;
			this.riposte = 1;
			this.nemesis = ENCHANTEUR;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;	
		}
	}
	
	/*-- accesseurs et mutateurs --*/
	public int getEtat(){
		return this.etat;
	}
	public void setEtat(int n){
		this.etat = n;
	}
	public int getNiveau(){
		return this.niveau;
	}
	public void setNiveau(int n){
		this.niveau = n;
	}
	public void setPos(Position n){
		this.position.setX(n.getX());
		this.position.setY(n.getY());
	}
	public Position getPos(){
		return this.position;
	}
	public int getExp(){
		return this.exp;
	}
	public void setExp(int n){
		this.exp = n;
	}
	public int getPvMax(){
		return this.pvMax;
	}
	public void setPvMax(int n){
		this.pvMax = n;
	}
	public int getPvActuel() {
		return this.pvActuel;
	}
	public void setPvActuel(int n) {
		this.pvActuel = n;
	}
	public int getDegat(){
		return this.degat;
	}
	public void setDegat(int n){
		this.degat = n;
	}
	public int getAttaque(){
		return this.attaque;
	}
	public void setAttaque(int n){
		this.attaque = n;
	}
	public int getProtection(){
		return this.protection;
	}
	public int getBlindage(){
		return this.blindage;
	}
	public void setBlindage(int n){
		this.blindage = n;
	}
	public int getTaille(){
		return this.taille;
	}
	public int getId(){
		return this.id;
	}
	public int getJoueur(){
		return this.joueur;
	}
	public int getPortee(){
		return this.portee;
	}
	public void setPortee(int n){
		this.portee = n;
	}
	public int getVision(){
		return this.vision;
	}
	public void setVision(int n){
		this.vision = n;
	}
	public int getVitesse(){
		return this.vitesse;
	}
	public void setVitesse(int n){
		this.vitesse = n;
	}
	public int getPm(){
		return this.pm;
	}
	public void setPm(int n){
		this.pm = n;
	}
	public int getRiposte(){
		return this.riposte;
	}
	public void setRiposte(int n){
		this.riposte = n;
	}
	public int getNemesis(){
		return this.nemesis;
	}
	
	/*-- méthode spéciale des Personnages --*/
	
	/*Si le personnage est le destructeur, augmente de 1 ses degats*/
	public void rage(){
		if(this.id == DESTRUCTEUR){
			this.setDegat(this.degat + 1);
		}
	}
	
	/*Si le personnage est un chasseur/bête de guerre et que ses PV sont supérieur à ceux passé en paramètres, 
	 * il augmente de 1 sa vitesse, mais réduit de 1 ses dégâts si ces PV sont inférieurs à 50% de ses PV de départ*/
	public void course(int pvavant){
		if(this.id == BETE || this.id == CHASSEUR){
			if((pvavant > (this.pvMax/2)) && (this.pvActuel < (this.pvMax/2))){
			/*on sais ici que this viens de perdre plus de 50% de ses PV max : on active la capacité Course*/
				this.degat -= 1;
				this.vitesse += 1;
			}
		}	
	}
	
	/*Si le personnage this est une bête de guerre, il ralentie sa cible D*/
	public void meurtrissure(Personnage D){
		if(this.id == BETE){
			if(D.vitesse > 3){
				D.setVitesse(D.vitesse-1);
			}
		}
	}
	
	/*Si le personnage this est le Combattant, il gagne de l'experience
	Cette fonction est appelée à la fin de chaque tour de personnage.*/
	public void entrainement(){
		if(this.id == COMBATTANT){
			this.gainExp(5);
		}
	}
	
	/*Si le personnage est un éclaireur, renvoi vrai
	 cette méthode est appelée lorsqu'un monstre tente de se déplacer sur un terrain (element) dont la nature est différente de 0*/
	public boolean chemin(){
		if(this.id == ECLAIREUR){
			return true;
		}
		return false;
	}
	
	/*Si l'attaquant est un assassin (chasseur ou artilleur) et si la cible D à 50%- de PV, 
	 renvois des dégâts supplémentaires (5), 0 sinon */
	public int assassin(Personnage D){
		if(this.id == CHASSEUR || this.id == ARTILLEUR){
			if(D.pvActuel <= D.pvMax/2){
				return 5;
			}
		}
		return 0;
	}
	
	/*si le Personnage this est le Gardien, il se soigne.*/
	public void regeneration(){
		if(this.id == GARDIEN){
			this.soin(2+(int)(Math.random()*6));
		}
	}
	/*Si le personnage this est le Gardien/Combattant, renvoi vrai */ 
	public boolean replique(){
		if(this.id == GARDIEN || this.id == COMBATTANT){
			return true;
		}
		return false;
	}
	
	/*si le personnage this est l'artilleur, alors elle gagne de l'experience selon la distance à laquelle elle tire sur sa cible D*/
	public void precision(Personnage D){
		if(this.id == 7){
			int bonus = 2*D.taille;
			this.gainExp(bonus);
		}
	}
	
	/*si le personnage est le Destructeur ou l'Incendiaire, renvois vrais.*/
	public boolean perceArmure(){
		if(this.id == INCENDIAIRE || this.id == DESTRUCTEUR){
			return true;
		}
		return false;
	}
	
	/*Renvoi vrai si le personnage est l'icendiaire*/
	public boolean embrasement(){
		if(this.id == INCENDIAIRE){
			return true;
		}
		return false;
	}
	
	/*Renvoi 0 si le personnage parvient a esquiver l'attaque ou degat sinon*/
	public int esquive(int degat) {
		int a = 1 + (int)(Math.random()*10);
		if(a <= 2) {
			return 0;
		}
		return degat;
	}
	
	/*verifie si le personnage est le dance-lame, et le soigne d'une partie de la valeur passée en parametre*/
	public void drain(int degat) {
		if(this.id == DANCELAME) {
			int soin = degat/4;
			if(soin != 0) {
				this.soin(soin);
			}
		}
	}
	
		/*-- toutes les autres méthodes --*/
	
	/*Si le personnage this est le Gardien/Combattant, il regagne 3 de riposte, sinon, il en regagne seulement 1. */
	public void recupRiposte(){
		if(this.replique()){
			this.riposte = 3;
		}
		else{
			this.riposte = 1;
		}
	}
	
	/*indique si le personnage D peut riposter contre le personnage this en fonction de son nombre de riposte restant et de sa portée d'attaque. 
	Rappel : le personne ne peut pas riposter si l'attaquant est un incendiaire*/
	/*public boolean peutRiposter(Personnage D){
		if((D.distance(this) > D.portee) || (D.riposte == 0) || this.embrasement()){
			return false;
		}
		return true;
	}*/
	
	/*le personnage this riposte s'il le peut : inflige une contre-attaque au personnage A*/
	/*public void Riposte(Personnage A){
		if(this.peutRiposter(A)) {
			this.setRiposte(this.getRiposte()-1);
			this.attaque(A);
		}
	}*/
	/*Le personnage this porte une attaque sur le personnage D*/
	public void attaque(Personnage D){
		/*mise en place des deg*/
		int degat = this.degat + 1 + (int)(Math.random()*6) + this.assassin(D); /* -this.distance(D);*/
	
		if(this.estNemesis(D)){
			/*Le défenseur est le némésis de l'attaquant : bonus de dégât*/
			degat += 5;
		}
		/*if(!this.peutRiposter(D)) {
			degat = D.esquive(degat);
		}*/

		/*les  degats vont etre reduit par l'armure de la cible D, si l'A est un incendiaire (ID = 3) ou un destructeur (ID= 4), alors cette partie est sautée*/
		if(!this.perceArmure()){
			if(1+(int)(Math.random()*100) <= D.protection){
				degat -= D.blindage; 
				if(degat < 0){
					degat = 0;
				}
			} 
		}
		/*l'attaquant inflige donc ses degats à la cible et gagne l'experience correspondante
	On enregistre les pvactuels du D pour les utiliser dans la D.course()*/
		int pvactuel =  D.pvActuel;
		D.setPvActuel(D.pvActuel-degat);
	
		/*on appelle les fonctions de personnage :
		 * celle qui augmente des degats si le destructeur est attaqué, 
		 * celle qui augmente la vitesse sur le Chasseur/Bête de guerre, 
		 * celle qui ralentie la cible si l'attaquant est une bête de guerre, 
		 * celle qui soigne un allie proche si l'attaquant est un enchanteur
		 * celle qui soigne le dancelame d'une partie des degats infliges*/
		D.rage();
		D.course(pvactuel);
		this.meurtrissure(D); 
		this.drain(degat);
		this.precision(D);
		/*this.vigueur();*/
	
		/*S'il l'attaquant tue la cible, il recupere un bonus d'exp (augmenté si la cible est le nemesis*/
		if(D.deces()){
			degat += 50;
			if(this.estNemesis(D)){
				degat += 50;
			}
		}
		this.gainExp(degat);
	}
	
	/*Le personnage passe son tour pour se reposer et se soigner*/
	public void repos(){
		this.setAttaque(0);
		this.setPm(0);
		this.soin(6+(int)(Math.random()*6));

		/*si c'est l'enchanteur, il donne de l'EXP aux alliés proches et se soigne un peu plus*/
		/*this.encouragement(); */
	}
	
	/*Le personnage A se soigne d'un montant egal a valeur*/
	public void soin(int valeur){
		this.setPvActuel(this.pvActuel + valeur);
		if(this.pvActuel > this.pvMax){
			valeur -= this.pvActuel - this.pvMax;
			this.setPvActuel(this.pvMax);
		}
		this.gainExp(valeur);
	}
	
	/*Le personnage gagne de l'experience, on verifie alors s'il passe un niveau et on applique ses bonus. */
	public void gainExp(int valeur){
		this.setExp(this.exp + valeur);
		this.gainNiveau();	
	}
	
	/*Si le personnage depasse 100 d'exp, il gagne un niveau*/
	public void gainNiveau(){
		if(this.exp >= 100){
			this.setNiveau(this.niveau + 1);
			this.setExp(this.exp -100);

			this.setDegat(this.degat +1);
			this.setPvMax(this.pvMax + 5);
			this.soin(5);
			this.setBlindage(this.blindage +1);
			/*l'artilleur gagne aussi un bonus de portée*/
			if(this.id == ARTILLEUR){
				this.setPortee(this.portee +1);
			}
		}
	}
	
	/*Le Personnage actuel est le nemesis du personnage D*/
	public boolean estNemesis(Personnage D){
		if(this.nemesis == D.id){
			return true;
		}
		return false;
	}
	
	/*deces test si le personnage a perdu tout ses PV, le tue si c'est le cas, en renvoyant vrai*/
	public boolean deces() {
		if(this.pvActuel <= 0) {
			this.setEtat(MORT);
			//IL reste probablement des choses à faire ici...
			return true;
		}
		return false;
	}
	/*--- Methodes d'affichage et auxiliaires ---*/
	
	private String nomPersonnage() {
		//renvois une chaine de la forme suivante : nomPersonnage (classePersonnage-campPersonnage)
		String chaine ="";
		switch(this.id) {
		case GARDIEN:
			if(this.joueur == 0) {
				chaine = "GOLEM (gardien-donjon)\n";
			}
			else {
				chaine = "ABOMINATION (gardien-enfer)\n";
			}
			break;
		case BETE:
			if(this.joueur == 0) {
				chaine = "HYDRE (bête de guerre-donjon)\n";
			}
			else {
				chaine = "MUTILATEUR (bête de guerre-enfer)\n";
			}
			break;
		case INCENDIAIRE:
			if(this.joueur == 0) {
				chaine = "DRAKE (incendiaire-donjon)\n";
			}
			else {
				chaine = "DEMONISTE (incendiaire-enfer)\n";
			}
			break;
		case DESTRUCTEUR:
			if(this.joueur == 0) {
				chaine = "MINOTAURE (destructeur-donjon)\n";
			}
			else {
				chaine = "PRINCE DEMON (destructeur-enfer)\n";
			}
			break;
		case ENCHANTEUR:
			if(this.joueur == 0) {
				chaine = "MAGE GRENOUILLE (enchanteur-donjon)\n";
			}
			else {
				chaine = "DIABLOTIN (enchanteur-enfer)\n";
			}
			break;
		case COMBATTANT:
			if(this.joueur == 0) {
				chaine = "ARMURE ANIMEE (combattant-donjon)\n";
			}
			else {
				chaine = "GUERRIER DU CHAOS (combattant-enfer)\n";
			}
			break;
		case ARTILLEUR:
			if(this.joueur == 0) {
				chaine = "HOMME-LEZARD (artilleur-donjon)\n";
			}
			else {
				chaine = "SUCCUBE (artilleur-enfer)\n";
			}
			break;
		case DANCELAME:
			if(this.joueur == 0) {
				chaine = "VAMPYRION (dancelame-donjon)\n";
			}
			else {
				chaine = "INCUBE (dancelame-enfer)\n";
			}
			break;
		case CHASSEUR:
			if(this.joueur == 0) {
				chaine = "PREDATOR (chasseur-donjon)\n";
			}
			else {
				chaine = "CHIEN DES ENFERS (chasseur-enfer)\n";
			}
			break;
		case ECLAIREUR:
			if(this.joueur == 0) {
				chaine = "WORM (eclaireur-donjon)\n";
			}
			else {
				chaine ="OEIL DU DIABLE (eclaireur-enfer)\n";
			}
			break;
		}
		return chaine;
	}
	
	private String nomNemesis() {
		//renvois une chaine contenant le nom du Nemesis du Personnage this
		String chaine ="";
		switch(this.nemesis) {
		case GARDIEN:
			if(this.joueur == 0) {
				chaine = "ABOMINATION\n";
			}
			else {
				chaine = "GOLEM\n";
			}
			break;
		case BETE:
			if(this.joueur == 0) {
				chaine = "MUTILATEUR\n";
			}
			else {
				chaine = "HYDRE\n";
			}
			break;
		case INCENDIAIRE:
			if(this.joueur == 0) {
				chaine = "DEMONISTE\n";
			}
			else {
				chaine = "DRAKE\n";
			}
			break;
		case DESTRUCTEUR:
			if(this.joueur == 0) {
				chaine = "PRINCE DEMON\n";
			}
			else {
				chaine = "MINOTAURE\n";
			}
			break;
		case ENCHANTEUR:
			if(this.joueur == 0) {
				chaine = "DIABLOTIN\n";
			}
			else {
				chaine = "MAGE GRENOUILLE\n";
			}
			break;
		case COMBATTANT:
			if(this.joueur == 0) {
				chaine = "GUERRIER DU CHAOS\n";
			}
			else {
				chaine = "ARMURE ANIMEE\n";
			}
			break;
		case ARTILLEUR:
			if(this.joueur == 0) {
				chaine = "SUCCUBE\n";
			}
			else {
				chaine = "HOMME-LEZARD\n";
			}
			break;
		case DANCELAME:
			if(this.joueur == 0) {
				chaine = "INCUBE\n";
			}
			else {
				chaine = "VAMPYRION\n";
			}
			break;
		case CHASSEUR:
			if(this.joueur == 0) {
				chaine = "CHIEN DES ENFERS\n";
			}
			else {
				chaine = "PREDATOR\n";
			}
			break;
		case ECLAIREUR:
			if(this.joueur == 0) {
				chaine = "OEIL DU DIABLE\n";
			}
			else {
				chaine ="WORM\n";
			}
			break;
		}
		return chaine;
	}
	
	private String capacite() {
		//renvois une chaine contenant le nom de la description des capacites du Personnage this
		String chaine ="";
		switch(this.id) {
		case GARDIEN:
			chaine = "	Réplique : possède 3 ripostes au lieu d'une seule.\n"
					+ "	Régénération : au début de chaque tour, se soigne de 1+1D6.";
			break;
		case BETE:
			chaine = "	Course : Augmente de 1 sa vitesse après avoir perdu 50% de ses PV max (cumulable).\n"
					+ "	Meurtrissure : Ralentie de 1 les ennemis attaqué (jusqu'à une vitesse de 3).";
			break;
		case INCENDIAIRE:
			chaine = "	Perce-armure : Les attaques ignorent la défense ennemie.\n"
					+ "	Embrasement : les attaques ne peuvent pas générer de riposte ennemie.";
			break;
		case DESTRUCTEUR:
			chaine = "	Perce-armure : Les attaques ignorent la défense ennemie.\n"
					+ "	Rage : augmente de 1 ses dégâts à chaque attaque subie (cumulable).";
			break;
		case ENCHANTEUR:
			chaine = "	Vigueur : Les attaques soignent un Personnage allié à 2 de distance de 5 PV.\n"
					+ "	Encouragement : Se reposer confère 5 d'Expérience aux alliés à 2 de distance.";
			break;
		case COMBATTANT:
			chaine = "	Réplique : possède 3 ripostes au lieu d'une seule.\n"
					+ "	Entrainement : au début de chaque tour, gagne 5 d'Expérience.";
			break;
		case ARTILLEUR:
			chaine = "	Assassin : Inflige 5 dégât supplémentaire aux ennemis avec moins de 50% de leurs PV max.\n"
					+ "	Précision : gagne de l'Expérience bonus selon la taille des cibles attaquées. Augmente sa portée en gagnant un niveau.";
			break;
		case DANCELAME:
			chaine = "	Drain : se soigne de 25% des dégâts infligés.\n"
					+ "	Esquive : Lorsqu'il ne riposte pas, il possède 20% de chance d'esquiver l'attaque subie.";
			break;
		case CHASSEUR:
			chaine = "	Course : Augmente de 1 sa vitesse après avoir perdu 50% de ses PV max (cumulable).\n"
					+ "	Assassin : Inflige 5 dégât supplémentaire aux ennemis avec moins de 50% de leurs PV max..";
			break;
		case ECLAIREUR:
			chaine = "	Maître des chemin : Peut se déplacer sur n'importe quel terrain.\n"
					+ "	Foudroiement : En attaquant, inflige 3+1D6 dégâts à tout les ennemis supplémlentaires à 6 de distance";
			break;
		}
		return chaine;
	}
	
	/*Affiche les informations à propos de Personnage this*/
	public String toString() {
		String chaine = "   ";
		/*on construit la chaine petit à petit à partir de toutes les informations du Personnage*/
		//d'abord : on veut afficher le nom, le titre et le camp de la créature :
		chaine = chaine + this.nomPersonnage();
		//TEMPORAIRE : position du personnage : 
		chaine = chaine+"Position = "+this.position.getX()+":"+this.position.getY()+"\n";
		//maintenant, on génère un tableau contenant les informations caractéristiques sur la créature :
		chaine = chaine+"__________________________________________________________________________________\n";
		chaine = chaine+"|   PV   |  ATK  |   DEF   | TAILLE |  I  |  P  |  V  | R |   PM   |  EXP  | Niv |\n";
		chaine = chaine+"__________________________________________________________________________________\n";
		chaine = chaine+"|  "+this.getPvActuel()+"/"+this.getPvMax()+" |  "
						+this.getDegat()+"   |   "
						+this.getProtection()+"/"+this.getBlindage()+"  |   "
						+this.getTaille()+"    |  "
						+this.getId()+"  |  "
						+this.getPortee()+"  |  "
						+this.getVision()+"  | "
						+this.getRiposte()+" |  "
						+this.getPm()+"/"+this.getVitesse()+"   | "
						+this.getExp()+"/100 |  "
						+this.getNiveau()+"  |\n";
		//on ajoute les informations complémentaires : nemesis et capacités spéciales 
		chaine = chaine+"Nemesis = "+this.nomNemesis();
		chaine = chaine+"Capacité :\n";
		chaine = chaine+this.capacite()+"\n";
		return chaine;
	}
	
}
