package wargame;
public class Position implements IConfig,java.io.Serializable {
	private int x, y;
	
	/**
	 * Constructeur d'une position
	 * @param x valeur sur l'axe x
	 * @param y valeur sur l'axe y
	 */
	public Position(int x, int y) { this.x = x; this.y = y; }
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	
	/**
	 * verifie si une position est valide
	 * @return renvoie si une position est dans la carte et faux sinon
	 */
	public boolean estValide() {
		if (x<0 || x>=LARGEUR_CARTE || y<0 || y>=HAUTEUR_CARTE) return false; else return true;
	}
	
	/**
	 * renvoie la transformation d'un eposition en chaine de caractere
	 * @return le texte renvoyé par la fonction
	 */
	public String toString() { return "("+x+","+y+")"; }
	
	/**
	 * Verifie si une position est la voisine de la position courante
	 * @param pos position voisine a verifier
	 * @return vrai si elle est voisine, faux sinon
	 */
	public boolean estVoisine(Position pos) {
		return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
	}
	
	/**
	 * renvoie la distance entre deux position
	 * @param p position de la position cible
	 * @return la distance en decimal entre les deux
	 */
	double distance(Position p) {
		return Math.sqrt((Math.pow((p.x-this.x),2)+Math.pow((p.y-this.y),2)));
	}
}