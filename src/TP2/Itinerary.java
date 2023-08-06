package TP2;

import Utilitaires.*;

public class Itinerary {
	// ATTRIBUTS
	private Network reseau;
	private int nsteps;
	private TermList visited;
	private String current;
	private int[]order;
	private TermList parcoursOptimise;
	private int tailleOptim;
	
	// CONSTRUCTEUR
	public Itinerary(Network reseau) {
		this.reseau=reseau;
		visited=new TermList();
		current=null;
		tailleOptim=100;
		parcoursOptimise=new TermList();
		nsteps=reseau.getNsteps();
		RandomIndexes rtab=new RandomIndexes(nsteps,0);

		order=rtab.swap();
		// AFFICHAGE DES INDEXES ALEATOIRES
//		System.out.println("Random order of the indexes:");
//		for (int j=0;j<order.length;j++)
//			System.out.print(order[j]+" ");
//		System.out.println();
		
		TabFileReader.readTextFile("steps.txt",'\t',"data");
		for (int j=0;j<order.length;j++){
			int pos=order[j];
			reseau.stepAt(j).setStation1(TabFileReader.wordAt(pos,0));
			reseau.stepAt(j).setLigne(TabFileReader.wordAt(pos,1));
			reseau.stepAt(j).setStation2(TabFileReader.wordAt(pos,2));
		}
	}
	
	// METHODES
	public String next(String current) {
		String suivante=null;
		int i=0;
		int index=0;
		Step step=new Step("","","");
		String nextS;
		do {
			index=order[i];
			step=reseau.stepAt(index);
			nextS=step.getNext(current);
			if(nextS!=null && visited.exists(nextS)==false) {
				suivante=nextS;
				//System.out.println("Station suivante : "+suivante);
			}
			i++;
		}while(suivante==null && i<nsteps);
		return suivante;
	}
	
	public TermList research(String depart, String arrivee) {
		boolean fini=false;
		current=depart;
		String suivante="";
		do {
			//System.out.println("Debut boucle");
			visited.add(current);
			if(current.equals(arrivee)) {
				//System.out.println("Bravo, itinéraire trouvé !!!!!!");
				if(tailleOptim>visited.size()) {
					parcoursOptimise=visited;
					tailleOptim=visited.size();
				}
				return visited;
			}
			else {
				suivante=next(current);
				if(suivante==null) {
					fini=true;
				}
				else {
					current=suivante;
				}
			}
		}while(!fini);
		return visited; // recherche infructueuse
	}
	
	public void marcheAlea(int n, String depart,String arrivee) {
		RandomIndexes rtab=new RandomIndexes(nsteps,0);
		TermList parcours=new TermList();
		for(int j=0;j<n;j++) {
			order=rtab.swap();
			parcours=this.research(depart,arrivee);
			visited=new TermList();
		}
		System.out.println("Itinéraire optimal trouvé pour "+tailleOptim+" stations : ");
		for(int i=0;i<tailleOptim;i++) {
			System.out.println("\t"+parcoursOptimise.termAt(i));
		}
		
	}
	
	// GETTERS
	public TermList getParcoursOptimise() {
		return parcoursOptimise;
	}
	
	public static void main(String []args) {
		Network reseau1=new Network("steps.txt",'\t',"data");
		Itinerary itineraire=new Itinerary(reseau1);
		itineraire.marcheAlea(10,"Baker Street","Green Park");
//		for (int j=0;j<reseau1.getNsteps();j++){ // affiche les steps
//			System.out.println(reseau1.stepAt(j));
//		}
//		System.out.println();	
	}
}
