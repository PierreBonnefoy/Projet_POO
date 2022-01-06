package wargame;

public class IA implements IConfig{
	
	public IA() {}
	
	public void tour(Carte jeu,Personnage[][] equipe,int indicePerso,boolean[] mort) {
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
		
		while(courant.getPm()>0) {
			for(int i=0;i<NBPERSONNAGE;i++) {
				/*--- On test si un perso ennemi est à pertée ---*/
				if(jeu.distance(courant.getPos(),equipe[i][JOUEUR].getPos())<=courant.getPortee() && courant.getAttaque()>0) {
					/*--- Si oui on l'attaque ---*/
					cible = equipe[i][JOUEUR];
					if(cible.getJoueur() != IA) {
						System.out.println(courant.nomPersonnage()+" attaque "+cible.nomPersonnage());
						courant.setAttaque(courant.getAttaque()-1); //attaque
						mort[0] = courant.attaque(cible);
						
						if(mort[0]) {
							jeu.carte[cible.getPos().getX()][(cible.getPos().getY())].enleverPersonnage(cible.getId(), cible.getJoueur());
						}
						else {
						/*si le defenseur peut riposter, il riposte*/
							if(cible.getPortee() >= jeu.distance(courant.getPos(),cible.getPos()) && cible.getRiposte() > 0) {
								System.out.println("portee riposte : "+cible.getPortee()+"\ndistance :"/*+jeu.distance(perso.getPos(),positionCase)*/);
								
								System.out.println(cible.nomPersonnage()+" riposte contre "+courant.nomPersonnage());
								mort[1] = cible.attaque(courant); //riposte
								cible.setRiposte(cible.getRiposte()-1);
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
					jeu.Deplacement(courant.getPos(), best);
					courant.setPm(courant.getPm()-1);
				}
			}
		}
		courant.passeTour();
	}
}
