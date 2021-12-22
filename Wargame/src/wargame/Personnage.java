package wargame;

public class Personnage {
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
		if(this.id == 4){
			this.setDegat(this.degat + 1);
		}
	}
	
	/*Si le personnage est un chasseur/bête de guerre et que ses PV sont supérieur à ceux passé en paramètres, il augmente de 1 sa vitesse, mais réduit de 1 ses dégâts*/
	public void course(int pvavant){
		if(this.id == 2 || this.id == 9){
			if((pvavant > (this.pvMax/2)) && (this.pvActuel < (this.pvMax/2))){
			/*on sais ici que this viens de perdre plus de 50% de ses PV max*/
				this.degat -= 1;
				this.vitesse += 1;
			}
		}	
	}
	
	/*Si le personnage this est une bête de guerre, il ralentie sa cible D*/
	public void meurtrissure(Personnage D){
		if(this.id == 2){
			if(D.vitesse > 3){
				D.setVitesse(D.vitesse-1);
			}
		}
	}
	
	/*Si le personnage this est le Combattant, il gagne de l'experience
	Cette fonction est appelée à la fin de chaque tour de personnage.*/
	public void entrainement(){
		if(this.id == 6){
			this.gainExp(5);
		}
	}
	
	/*Si le personnage est un éclaireur, renvoi vrai*/
	public boolean chemin(){
		if(this.id == 10){
			return true;
		}
		return false;
	}
	
	/*Si l'attaquant est un assassin (chasseur ou artilleur) et si la cible D à 50%- de PV, renvois des dégâts supplémentaires, 0 sinon */
	public int assassin(Personnage D){
		if(this.id == 9 || this.id == 7){
			if(D.pvActuel <= D.pvMax/2){
				return 5;
			}
		}
		return 0;
	}
	
	/*si le Personnage this est le Gardien, il se soigne.*/
	public void regeneration(){
		if(this.id == 1){
			this.soin(3);
		}
	}
	/*Si le personnage this est le Gardien/Combattant, renvoi vrai */ 
	public boolean replique(){
		if(this.id == 1 || this.id == 6){
			return true;
		}
		return false;
	}
	
	/*si le personnage this est l'artilleur, alors elle gagne de l'experience selon la distance à laquelle elle tire sur sa cible D*/
	public int precision(Personnage D){
		if(this.id == 7){
			this.gainExp(this.distance(D));
		}
	}
	
	/*si le personnage est le Destructeur ou l'Incendiaire, renvois vrais.*/
	public boolean perceArmure(){
		if(this.id == 3 || this.id == 4){
			return true;
		}
		return false;
	}
	
	/*Renvoi vrai si le personnage est l'icendiaire*/
	public boolean embrasement(){
		if(this.id == 3){
			return true;
		}
		return false;
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
	public boolean peutRiposter(Personnage D){
		if((D.distance(this) > D.portee) || (D.riposte == 0) || this.embrasement()){
			return false;
		}
		return true;
	}
	
	/*le personnage this riposte : inflige une contre-attaque au personnage A*/
	public void Riposte(Personnage A){
		this.setRiposte(this.getRiposte()-1);
		this.attaque(A);
	}

	/*Le personnage this porte une attaque sur le personnage D*/
	public void attaque(Personnage D){
		/*mise en place des deg*/
		int degat = this.degat + 0 /*(valeur aléatoire entre 1 et 6)*/ + this.assassin(D) - this.distance(D);
	
		if(this.estNemesis(D)){
			/*Le défenseur est le némésis de l'attaquant : bonus de dégât*/
			degat += 5;
		}

		/*les  degats vont etre reduit par l'armure de la cible D, si l'A est un incendiaire (ID = 3) ou un destructeur (ID= 4), alors cette partie est sautée*/
		if(!this.perceArmure()){
			if(0 /*entre 1 et 100*/ <= D.protection){
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
	
		/*on appelle la fonction qui augmente des degats si le destructeur est attaque, 
		 * celle qui augmente la vitesse sur le Chasseur/Bête de guerre, 
		 * celle qui ralentie la cible si l'attaquant est une bête de guerre, 
		 * etcelle qui soigne un allie proche si l'attaquant est un enchanteur*/
		D.rage();
		D.course(pvactuel);
		this.meurtrissure(D); 
		this.vigueur();
	
		/*S'il l'attaquant tue la cible, il recupere un bonus d'exp*/
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
		this.soin(5+0 /*entre 1 et 6*/);

		/*si c'est l'enchanteur, il donne de l'EXP aux alliés proches et se soigne un peu plus*/
		this.encouragement(); 
	}
	
	/*Le personnage A augmente se soigne d'un montant egal a valeur*/
	public void soin(int valeur){
		this.setPvActuel(this.pvActuel + valeur);
		this.gainExp(valeur);
		if(this.pvActuel > this.pvMax){
			this.setPvActuel(this.pvMax);
		}
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
			if(this.id == 7){
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
			this.setEtat(0);
			/*IL reste probablement des choses à faire ici...*/
			return true;
		}
		return false;
	}
}
