package wargame;

public class Personnage implements IConfig,java.io.Serializable{
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

	/**
	 * Constructeur d'un personnage
	 * @param id id du personnage
	 * @param joueur joueur auquel il appartient
	 * @param x position x
	 * @param y position y
	 */
	/*-- Constructeur --*/
	public Personnage(int id, int joueur,int x,int y) {
		switch(id) {
		case GARDIEN:
			this.position=new Position(x,y);
			this.pvMax = 95 ;
			this.pvActuel = this.pvMax;
			this.degat = 10;
			this.attaque = 1;
			this.protection = 80;
			this.blindage = 6;
			this.taille = 3;
			this.id = id;
			this.joueur = joueur;
			this.portee = 1;
			this.vision = 2;
			this.vitesse = 3;
			this.pm = this.vitesse;
			this.riposte = 3;
			this.nemesis = DANCELAME;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case BETE:
			this.position=new Position(x,y);
			this.pvMax = 95;
			this.pvActuel = this.pvMax;
			this.degat = 10;
			this.attaque = 1;
			this.protection = 40;
			this.blindage = 5;
			this.taille = 3;
			this.id = id;
			this.joueur = joueur;
			this.portee = 1;
			this.vision = 3;
			this.vitesse = 5;
			this.pm = this.vitesse;
			this.riposte = 1;
			this.nemesis = CHASSEUR;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case INCENDIAIRE:
			this.position=new Position(x,y);
			this.pvMax = 70;
			this.pvActuel = this.pvMax;
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
			this.pm = this.vitesse;
			this.riposte = 1;
			this.nemesis = COMBATTANT;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case DESTRUCTEUR:
			this.position=new Position(x,y);
			this.pvMax = 75;
			this.pvActuel = this.pvMax;
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
			this.pm = this.vitesse;
			this.riposte = 1;
			this.nemesis = GARDIEN;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case ENCHANTEUR:
			this.position=new Position(x,y);
			this.pvMax = 70;
			this.pvActuel = this.pvMax;
			this.degat = 8;
			this.attaque = 1;
			this.protection = 40;
			this.blindage = 3;
			this.taille = 1;
			this.id = id;
			this.joueur = joueur;
			this.portee = 4;
			this.vision = 4;
			this.vitesse = 4;
			this.pm = this.vitesse;
			this.riposte = 1;
			this.nemesis = DESTRUCTEUR;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case COMBATTANT:
			this.position=new Position(x,y);
			this.pvMax = 80;
			this.pvActuel = this.pvMax;
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
			this.pm = this.vitesse;
			this.riposte = 3;
			this.nemesis = BETE;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case ARTILLEUR:
			this.position=new Position(x,y);
			this.pvMax = 75;
			this.pvActuel = this.pvMax;
			this.degat = 8;
			this.attaque = 1;
			this.protection = 40;
			this.blindage = 4;
			this.taille = 2;
			this.id = id;
			this.joueur = joueur;
			this.portee = 6;
			this.vision = 4;
			this.vitesse = 3;
			this.pm = this.vitesse;
			this.riposte = 1;
			this.nemesis = ECLAIREUR;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case DANCELAME:
			this.position=new Position(x,y);
			this.pvMax = 60;
			this.pvActuel = this.pvMax;
			this.degat = 12;
			this.attaque = 1;
			this.protection = 40;
			this.blindage = 3;
			this.taille = 2;
			this.id = id;
			this.joueur = joueur;
			this.portee = 2;
			this.vision = 2;
			this.vitesse = 5;
			this.pm = this.vitesse;
			this.riposte = 1;
			this.nemesis = INCENDIAIRE;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case CHASSEUR:
			this.position=new Position(x,y);
			this.pvMax = 70;
			this.pvActuel = this.pvMax;
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
			this.pm = this.vitesse;
			this.riposte = 1;
			this.nemesis = ARTILLEUR;
			//this.position = ;
			this.exp = 0;
			this.niveau = 0;
			this.etat = VIVANT;
			break;
		case ECLAIREUR:
			this.position=new Position(x,y);
			this.pvMax = 60;
			this.pvActuel = this.pvMax;
			this.degat = 8;
			this.attaque = 1;
			this.protection = 40;
			this.blindage = 3;
			this.taille = 1;
			this.id = id;
			this.joueur = joueur;
			this.portee = 3;
			this.vision = 6;
			this.vitesse = 5;
			this.pm = this.vitesse;
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
			//System.out.println(this.nomPersonnage()+" active Rage : +1 Dégât.");
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
				//System.out.println(this.nomPersonnage()+" active Course : -1 Dégât et +1 Vitesse.");
			}
		}	
	}
	
	/*Si le personnage this est une bête de guerre, il ralentie sa cible D*/
	public void meurtrissure(Personnage D){
		if(this.id == BETE){
			if(D.vitesse > 3){
				D.setVitesse(D.vitesse-1);
				//System.out.println(this.nomPersonnage()+" meurtrie "+D.nomPersonnage()+" : -1 Vitesse. ("+D.getVitesse());
			}
		}
	}
	
	/*Si le personnage this est le Combattant, il gagne de l'experience
	Cette fonction est appelée à la fin de chaque tour de personnage.*/
	public void entrainement(){
		if(this.id == COMBATTANT){
			//System.out.println(this.nomPersonnage()+" active Entrainement : + 5 EXP.");
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
				//System.out.println(this.nomPersonnage()+" active Assassin : inflige 5 dégât supplémentaire.");
				return 5;
			}
		}
		return 0;
	}
	
	/*si le Personnage this est le Gardien, il se soigne.*/
	public void regeneration(){
		if(this.id == GARDIEN){
			//System.out.println(this.nomPersonnage()+" active Régénération.");
			this.soin(2+(int)(Math.random()*6));
		}
	}
	/*Si le personnage this est le Gardien/Combattant, renvoi vrai */ 
	public boolean replique(){
		if(this.id == GARDIEN || this.id == COMBATTANT){
			//System.out.println(this.nomPersonnage()+" active Réplique : +2 riposte\n");
			return true;
		}
		return false;
	}
	
	/*si le personnage this est l'artilleur, alors elle gagne de l'experience selon la distance à laquelle elle tire sur sa cible D*/
	public int precision(Personnage D){
		if(this.id == 7){
			int bonus = 2*D.taille;
			return bonus;
		}
		return 0;
	}
	
	/*si le personnage est le Destructeur ou l'Incendiaire, renvois vrais.*/
	public boolean perceArmure(){
		if(this.id == INCENDIAIRE || this.id == DESTRUCTEUR){
			//System.out.println(this.nomPersonnage()+" ignore l'armure de la cible.");
			return true;
		}
		return false;
	}
	
	/*Renvoi vrai si le personnage est l'icendiaire*/
	public boolean embrasement(){
		if(this.id == INCENDIAIRE){
			//System.out.println(this.nomPersonnage()+" empèche les ripostes.");
			return true;
		}
		return false;
	}
	
	/*Renvoi 0 si le personnage parvient a esquiver l'attaque ou degat sinon*/
	public int esquive(int degat) {
		if(this.id == DANCELAME) {
			int a = 1 + (int)(Math.random()*10);
			if(a <= 2) {
				//System.out.println(this.nomPersonnage()+" parvient à esquiver l'attaque.");
				return 0;
			}
		}
		return degat;
	}
	
	/*verifie si le personnage est le dance-lame, et le soigne d'une partie de la valeur passée en parametre*/
	public void drain(int degat) {
		if(this.id == DANCELAME) {
			int soin = degat/4;
			if(soin != 0) {
				if(this.pvActuel != this.pvMax) {
					this.soin(soin);
				}
			}
		}
	}
	/*verifie si le personnage est l'enchanteur, et renvois 5*/
	public int alchimie() {
		if(this.id == ENCHANTEUR) {
			return 5;
		}
		return 0;
	}
	
	/*si this est l'enchanteur, le personnage this soigne le personnage cible et récupère de l'expérience*/
	public void encouragement(Personnage cible) {
		if(this.id == ENCHANTEUR) {
			//System.out.println(this.nomPersonnage()+" encourage "+cible.nomPersonnage());
			int soin = this.degat + 1 + (int)(Math.random()*4); /* -distance;*/
			cible.soin(soin);
			this.gainExp(soin/2);
		}
		//System.out.println("\n");
	}
	/*renvoi vrai si l'éclaireur n'a pas encore riposter à ce tour*/
	public int foudroiement() {
		if(this.id == ECLAIREUR) {
			if(this.riposte > 0) {
				if(this.riposte != 0) {
					return 5;
				}
			}
		}
		return 0;
	}
		/*---- toutes les autres méthodes ----*/
	
	/*Si le personnage this est le Gardien/Combattant, il regagne 3 de riposte, sinon, il en regagne seulement 1. */
	public void recupRiposte(){
		if(this.replique()){
			this.riposte = 3;
		}
		else{
			this.riposte = 1;
		}
	}
	
	/*Le personnage this porte une attaque sur le personnage D*/
	public boolean attaque(Personnage D /*, int distance*/){
		/*mise en place des deg*/
		int degat = this.degat + 1 + (int)(Math.random()*4) + this.assassin(D) + this.foudroiement(); /* -distance;*/
		boolean resultat = false;
		
		if(this.estNemesis(D)){
			/*Le défenseur est le némésis de l'attaquant : bonus de dégât*/
			degat += 5;
		}
		degat = D.esquive(degat);
		
		if(degat != 0) {
			/*les  degats vont etre reduit par l'armure de la cible D, si l'A est un incendiaire (ID = 3) ou un destructeur (ID= 4), alors cette partie est sautée*/
			if(!this.perceArmure()){
				if(1+(int)(Math.random()*100) <= D.protection){
					//System.out.println(D.nomPersonnage()+" réduit de "+D.blindage+" les dégâts subies.");
					degat -= D.blindage; 
					D.gainExp(D.blindage);
					if(degat < 0){
						degat = 0;
					}
				} 
			}
			/*l'attaquant inflige donc ses degats à la cible et gagne l'experience correspondante
		On enregistre les pvactuels du D pour les utiliser dans la D.course()*/
			int pvactuel =  D.pvActuel;
			D.setPvActuel(D.pvActuel-degat);
			
			//System.out.println(this.nomPersonnage()+"inflige "+degat+" degats à "+D.nomPersonnage()+".");
			//System.out.println("Il reste "+D.pvActuel+"/"+D.pvMax+" à "+D.nomPersonnage()+".");
	
			/*on appelle les fonctions de personnage qui s'active seulement si l'attaque blesse :
			 * celle qui soigne le dancelame d'une partie des degats infliges
			 * celle qui augmente la vitesse sur le Chasseur/Bête de guerre*/
			D.course(pvactuel);
			this.drain(degat);
			/*S'il l'attaquant tue la cible, il recupere un bonus d'exp (augmenté si la cible est le nemesis*/
			if(D.deces()){
				degat += 50;
				resultat = true;
			}
		}
		/*on appelle les fonctions de personnage qui s'active même si l'attaque rate ou n'inflige pas de dégât:
		 * celle qui augmente des degats si le destructeur est attaqué 
		 * celle qui ralentie la cible si l'attaquant est une bête de guerre, 
		 * celle qui soigne un allie proche si l'attaquant est un enchanteur*/
		D.rage();
		this.meurtrissure(D); 
		degat += this.precision(D);
		/*porter une attaque rapport toujours un léger bonus d'expérience. augmenté si la cible est le nemesis*/
		degat += 5;
		if(this.estNemesis(D)){
			degat += 10;
		}
		/*au final, le personnage gagne de l'EXP.*/
		if(degat != 0) {
			this.gainExp(degat);
		}
		//System.out.println("\n");
		return resultat;
	}
	
	/*Le personnage passe son tour, mais n'ayant pas encore joué, il se repose pour se soigner*/
	public void repos(){
		this.setAttaque(0);
		this.setPm(0);
		//System.out.println(this.nomPersonnage()+" se repose :");
		this.soin(6+(int)(Math.random()*6)+this.alchimie());
		//System.out.println("\n");
	}
	
	/*Le personnage passe son tour, mais ayant joué, il termine juste son tour.*/
	public void passeTour() {
		this.setAttaque(0);
		this.setPm(0);
		//System.out.println(this.nomPersonnage()+" termine son tour.");
		//System.out.println("\n");
	}
	
	/*Le personnage A se soigne d'un montant egal a valeur*/
	public void soin(int valeur){
		if(this.pvActuel != this.pvMax) {
			this.setPvActuel(this.pvActuel + valeur);
			//System.out.println(this.nomPersonnage()+" se soigne de "+valeur+"PV.");
			if(this.pvActuel > this.pvMax){
				valeur -= this.pvActuel - this.pvMax;
				this.setPvActuel(this.pvMax);
			}
			this.gainExp(valeur);
		}
	}
	
	/*Le personnage gagne de l'experience, on verifie alors s'il passe un niveau et on applique ses bonus. */
	public void gainExp(int valeur){
		this.setExp(this.exp + valeur+this.alchimie());
		this.gainNiveau();	
	}
	
	/*Si le personnage depasse 100 d'exp, il gagne un niveau*/
	public void gainNiveau(){
		if(this.exp >= 100){
			//System.out.println(this.nomPersonnage()+" gagne un niveau !\n");
			this.setNiveau(this.niveau + 1);
			this.setExp(this.exp -100);
			/*Il peut arriver que le personnage gagne tant d'EXP en une fois qu'il gagne 2 niveaux : on verifie*/
			this.gainNiveau();
			this.setDegat(this.degat +2);
			this.setPvMax(this.pvMax + 10);
			this.setPvActuel(this.pvActuel + 10);
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
			//System.out.println(this.nomPersonnage()+" est mort !");
			this.setEtat(MORT);
			this.setPvActuel(0);
			//IL reste probablement des choses à faire ici...
			return true;
		}
		return false;
	}
	/*--- Methodes d'affichage et auxiliaires ---*/
	
	public String nomPersonnage() {
		//renvois une chaine de la forme suivante : nomPersonnage (classePersonnage-campPersonnage)
		String chaine ="";
		switch(this.id) {
		case GARDIEN:
			if(this.joueur == 0) {
				chaine = "GOLEM (gardien-donjon)";
			}
			else {
				chaine = "ABOMINATION (gardien-enfer)";
			}
			break;
		case BETE:
			if(this.joueur == 0) {
				chaine = "HYDRE (bête de guerre-donjon)";
			}
			else {
				chaine = "MUTILATEUR (bête de guerre-enfer)";
			}
			break;
		case INCENDIAIRE:
			if(this.joueur == 0) {
				chaine = "DRAKE (incendiaire-donjon)";
			}
			else {
				chaine = "DEMONISTE (incendiaire-enfer)";
			}
			break;
		case DESTRUCTEUR:
			if(this.joueur == 0) {
				chaine = "MINOTAURE (destructeur-donjon)";
			}
			else {
				chaine = "PRINCE DEMON (destructeur-enfer)";
			}
			break;
		case ENCHANTEUR:
			if(this.joueur == 0) {
				chaine = "MAGE GRENOUILLE (enchanteur-donjon)";
			}
			else {
				chaine = "DIABLOTIN (enchanteur-enfer)";
			}
			break;
		case COMBATTANT:
			if(this.joueur == 0) {
				chaine = "ARMURE ANIMEE (combattant-donjon)";
			}
			else {
				chaine = "GUERRIER DU CHAOS (combattant-enfer)";
			}
			break;
		case ARTILLEUR:
			if(this.joueur == 0) {
				chaine = "SAURUS (artilleur-donjon)";
			}
			else {
				chaine = "SUCCUBE (artilleur-enfer)";
			}
			break;
		case DANCELAME:
			if(this.joueur == 0) {
				chaine = "VAMPYRION (dancelame-donjon)";
			}
			else {
				chaine = "INCUBE (dancelame-enfer)";
			}
			break;
		case CHASSEUR:
			if(this.joueur == 0) {
				chaine = "PREDATOR (chasseur-donjon)";
			}
			else {
				chaine = "CHIEN DES ENFERS (chasseur-enfer)";
			}
			break;
		case ECLAIREUR:
			if(this.joueur == 0) {
				chaine = "WORM (eclaireur-donjon)";
			}
			else {
				chaine ="OEIL DU DIABLE (eclaireur-enfer)";
			}
			break;
		}
		return chaine;
	}
	
	public String getNom() {
		//renvois une chaine de la forme suivante : nomPersonnage (classePersonnage-campPersonnage)
		String chaine ="";
		switch(this.id) {
		case GARDIEN:
			if(this.joueur == 0) {
				chaine = "golem";
			}
			else {
				chaine = "abomination";
			}
			break;
		case BETE:
			if(this.joueur == 0) {
				chaine = "hydre";
			}
			else {
				chaine = "mutilateur";
			}
			break;
		case INCENDIAIRE:
			if(this.joueur == 0) {
				chaine = "drake";
			}
			else {
				chaine = "demoniste";
			}
			break;
		case DESTRUCTEUR:
			if(this.joueur == 0) {
				chaine = "minotaure";
			}
			else {
				chaine = "princeDemon";
			}
			break;
		case ENCHANTEUR:
			if(this.joueur == 0) {
				chaine = "mageGrenouille";
			}
			else {
				chaine = "diablotin";
			}
			break;
		case COMBATTANT:
			if(this.joueur == 0) {
				chaine = "armureAnimee";
			}
			else {
				chaine = "guerrierChaos";
			}
			break;
		case ARTILLEUR:
			if(this.joueur == 0) {
				chaine = "saurus";
			}
			else {
				chaine = "succube";
			}
			break;
		case DANCELAME:
			if(this.joueur == 0) {
				chaine = "vampyrion";
			}
			else {
				chaine = "incube";
			}
			break;
		case CHASSEUR:
			if(this.joueur == 0) {
				chaine = "predator";
			}
			else {
				chaine = "chienEnfer";
			}
			break;
		case ECLAIREUR:
			if(this.joueur == 0) {
				chaine = "worm";
			}
			else {
				chaine ="oeilDiable";
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
				chaine = "SAURUS\n";
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
			chaine = "	-Réplique : possède 3 ripostes<br>"
					+ " au lieu d'une seule.<br>"
					+ "	-Régénération : au début de <br>"
					+ "chaque tour, se soigne de 1+1D6.<br>";
			break;
		case BETE:
			chaine = "	-Course : Augmente de 1 sa vitesse<br>"
					+ " après avoir perdu 50% de ses PV max.<br>"
					+ "	-Meurtrissure : Ralentie de 1 les<br>"
					+ " ennemis attaqué (jusqu'à V = 3).<br>";
			break;
		case INCENDIAIRE:
			chaine = "	-Perce-armure : Les attaques ignorent<br>"
					+ "la défense ennemie.<br>"
					+ "	-Embrasement : les attaques ne peuvent<br>"
					+ "pas générer de riposte ennemie.<br>";
			break;
		case DESTRUCTEUR:
			chaine = "	-Perce-armure : Les attaques ignorent<br>"
					+ "la défense ennemie.<br>"
					+ "	-Rage : augmente de 1 ses dégâts à <br>"
					+ "chaque attaque subie (cumulable).<br>";
			break;
		case ENCHANTEUR:
			chaine = "	-Encouragement : Les attaques soignent <br>"
					+ "un Personnage allié à 2 de distance<br>"
					+ " de 5 PV.<br>"
					+ "	-Alchimie : Se reposer confère<br> "
					+ "5 de soin et d'EXP bonus. <br>";
			break;
		case COMBATTANT:
			chaine = "	-Réplique : possède 3 ripostes au<br>"
					+ " lieu d'une seule.<br>"
					+ "	-Entrainement : au début de chaque<br>"
					+ "tour, gagne 5 d'Expérience.<br>";
			break;
		case ARTILLEUR:
			chaine = "	-Assassin : Inflige 5 dégât<br>"
					+ "supplémentaire aux ennemis avec<br>"
					+ " moins de 50% de leurs PV max.<br>"
					+ "	-Précision : gagne de l'Expérience<br>"
					+ "bonus selon la taille des cibles et <br>"
					+ "+1 en portée par niveau.<br>";
			break;
		case DANCELAME:
			chaine = "	-Drain : se soigne de 25% des<br>"
					+ " dégâts infligés.<br>"
					+ "	-Esquive : esquive 20%<br>"
					+ "des attaques subies<br>";
			break;
		case CHASSEUR:
			chaine = "	-Course : Augmente de 1 sa vitesse <br>"
					+ "après avoir perdu 50% de ses PV max.<br>"
					+ "	-Assassin : Inflige 5 dégât<br>"
					+ "supplémentaire aux ennemis avec<br>"
					+ " moins de 50% de leurs PV max.<br>";
			break;
		case ECLAIREUR:
			chaine = "	-Maître des chemin : Peut se déplacer <br>"
					+ "sur n'importe quel terrain.<br>"
					+ "	-Foudroiement : Si le personnage peut<br>"
					+ "riposter, il fait 5 degats bonus<br>";
			break;
		}
		return chaine;
	}
	
	/*Affiche les informations à propos de Personnage this*/
	public String toString() {
		String chaine = "";
		chaine="<br><h3> "+this.nomPersonnage()
		+"</h3><br>PV : "+this.pvActuel +"/ "+this.pvMax
		+"<br>ATK : "+this.degat
		+"<br>DEF : "+this.protection+ " | "+this.blindage
		+"<br>Taille : " +this.taille
		+"<br>Initiative : "+this.id
		+"<br>Porte: "+this.portee
		+"<br>Vision : "+this.vision
		+"<br>Riposte : "+this.riposte
		+"<br>PM : "+this.pm + " / "+this.vitesse
		+"<br>Exp : "+this.exp + " / 100"
		+"<br>Niveau : "+this.getNiveau()
		+"<br>Nemesis : "+this.nomNemesis()
		+"<br>Position : "+this.position.getX()+":"+this.position.getY()
		+"<br><br>"+this.capacite()
		+"<br>";
		return chaine;
	}
}
