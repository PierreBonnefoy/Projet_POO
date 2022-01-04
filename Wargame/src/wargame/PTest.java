package wargame;

public class PTest implements IConfig{
	/*Classe temporaire servant à faire des tests sur les personnages pour vérifier leur bon fonctionnement*/

	public static void main(String[] args) {
		System.out.println("________TEST PERSONNAGE_________");
		Personnage oeil = new Personnage(ECLAIREUR,1,1,1);
		Personnage chien = new Personnage(CHASSEUR,1,1,1);
		Personnage incube = new Personnage(DANCELAME,1,1,1);
		Personnage succube = new Personnage(ARTILLEUR,1,1,1);
		Personnage guerrier = new Personnage(COMBATTANT,1,1,1);
		Personnage diablotin = new Personnage(ENCHANTEUR,1,1,1);
		Personnage demon = new Personnage(DESTRUCTEUR,1,1,1);
		Personnage demoniste = new Personnage(INCENDIAIRE,1,1,1);
		Personnage mutilateur = new Personnage(BETE,1,1,1);
		Personnage abomination = new Personnage(GARDIEN,1,1,1);
		
		while((chien.getPvActuel() != 0) && (succube.getPvActuel() != 0)) {
			chien.entrainement();
			chien.attaque(succube);
			if(succube.getPvActuel() != 0){
				succube.attaque(chien);
				diablotin.encouragement(succube);
			}
		}
		
		System.out.println(chien.toString());
		
		System.out.println(succube.toString());
	}
}
