package wargame;

public class Dijkstra {
	
	public void dijkstra(Carte map,Position debut,Position fin) {
		
		/*--- Initialisation du tableau de couleur && Initialisation du tableau des distance ---*/
		boolean visiter[][]= new boolean[map.taille][map.taille];
		int distance[][]= new int[map.taille][map.taille];
		for(int i=0;i<map.taille;i++) {
			for(int j=0;j<map.taille;j++) {
				distance[i][j]=Integer.MAX_VALUE;
				if(i==0 && Math.floorMod(j, 2)==0) {
					visiter[i][j]=false;
				}
			}
		}
		distance[debut.getX()][debut.getY()]=0;
		Position tmp;
		
		do {
			/*--- Détermination de la position suivante ---*/
			tmp=coutPlusFaible(map,distance,visiter,debut);
			/*--- On la marque visiter --*/
			visiter[tmp.getX()][tmp.getY()]=true;
			/*--- Verif si de cout est rentable ---*/
			if(!visiter[tmp.getX()][tmp.getY()] && map.carte[tmp.getX()][tmp.getY()].getNature()<=1 && distance[tmp.getX()][tmp.getY()]+map.carte[tmp.getX()][tmp.getY()].getNature()+1<distance[tmp.getX()][tmp.getY()]) {
				distance[tmp.getX()][tmp.getY()]=distance[tmp.getX()][tmp.getY()]+map.carte[tmp.getX()][tmp.getY()].getNature()+1;
			}
			/*--- Condition de fin (arrive) ---*/
		}while(tmp.getX()!=fin.getX() && tmp.getX()!=fin.getX() && tmp.getX()!=fin.getX() && tmp.getX()!=fin.getX() );
		
	}
	
	private Position coutPlusFaible(Carte map,int distance[][],boolean visiter[][],Position debut) {
		/*--- Retourne l'index qui a le cout de deplacement le plus faible ---*/
		Position minIdex = new Position(0,0),tmp[]=map.PositionPossible(debut);
		int min=Integer.MAX_VALUE;
		
		for(int i=0;i<tmp.length;i++) {
			/*--- Verif que le sommet n'a pas ete visiter et que son cout est plus faible que les autres positions ---*/
			if(tmp[i]!=null) {
				if(!visiter[tmp[i].getX()][tmp[i].getY()] && distance[tmp[i].getX()][tmp[i].getY()]<min) {
					min=distance[tmp[i].getX()][tmp[i].getY()];
					minIdex.setX(tmp[i].getX());
					minIdex.setY(tmp[i].getY());
				}
			}
		}
		return minIdex;
	}
}
