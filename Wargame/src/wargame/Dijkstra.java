package wargame;

public class Dijkstra {
	
	public void dijkstra(Carte map) {
		
		/*--- Initialisation du tableau de couleur && Initialisation du tableau des distance ---*/
		boolean visiter[]= new boolean[map.taille];
		int distance[]= new int[map.taille];
		for(int i=0;i<map.taille;i++) {
			distance[i]=Integer.MAX_VALUE;
			visiter[i]=false;
		}
		distance[0]=0;
		
		for(int i=0;i<map.taille;i++) {
			
			int tmp=coutPlusFaible(distance,visiter);
			visiter[tmp]=true;
			
			for(int j=0;j<map.taille;j++) {
				if(!visiter[j] && map.carte[tmp][j].getNature()<=1 && distance[tmp]+map.carte[tmp][j].getNature()+1<distance[j]) {
					distance[j]=distance[tmp]+map.carte[tmp][j].getNature()+1;
				}
			}
		}
		
	}
	
	private int coutPlusFaible(int distance[],boolean visiter[]) {
		/*--- Retourne l'index qui a le cout de deplacement le plus faible ---*/
		int minIdex = -1, min=Integer.MAX_VALUE;
		
		for(int i=0;i<distance.length;i++) {
			/*--- Verif que le sommet n'a pas ete visiter et que son cout est plus faible ---*/
			if(!visiter[i] && distance[i]<min) {
				min=distance[i];
				minIdex=i;
			}
		
		}
		return minIdex;
	}
}
