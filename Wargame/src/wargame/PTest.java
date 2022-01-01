package wargame;

public class PTest implements IConfig{
	/*Classe temporaire servant à faire des tests sur les personnages pour vérifier leur bon fonctionnement*/

	public static void main(String[] args) {
		System.out.println("________TEST PERSONNAGE_________");
		Personnage chien = new Personnage(CHASSEUR,1,1,1);
		Personnage succube = new Personnage(ARTILLEUR,1,1,1);
		Personnage diablotin = new Personnage(ENCHANTEUR,1,1,1);
		Personnage abomination = new Personnage(GARDIEN,1,1,1);
		Personnage predator = new Personnage(CHASSEUR,0,1,1);
		Personnage golem = new Personnage(GARDIEN,0,1,1);
		
		System.out.println(succube.toString());
		System.out.println(chien.toString());
		System.out.println(diablotin.toString());
		System.out.println(abomination.toString());
		System.out.println(predator.toString());
		System.out.println(golem.toString());
	}
}
