package TP2;

import Utilitaires.*;
import java.util.Scanner;

public class Lines {
	// ATTRIBUTS
	private TermList lignes;
	private Network reseau;
	
	// CONSTRUCTEUR
	public Lines(Network reseau) {
		lignes=new TermList();
		this.reseau=reseau;
		for(int i=0;i<reseau.getNsteps();i++) {
			if(lignes.exists(reseau.stepAt(i).getLigne())==false) {
				lignes.add(reseau.stepAt(i).getLigne());
			}
		}
	}
	
	// METHODES
	public TermList findStations(int numLigne) {
		TermList listeStations=new TermList();
		int nbSteps=reseau.getNsteps();
		for(int i=0;i<nbSteps;i++) {
			if(lignes.termAt(numLigne).equals(reseau.stepAt(i).getLigne())) { // si la ligne correspond
				if(listeStations.exists(reseau.stepAt(i).getStation1())==false){ // si station1 pas deja enregistree
					listeStations.add(reseau.stepAt(i).getStation1());
				}
				if(listeStations.exists(reseau.stepAt(i).getStation2())==false){ // si station1 pas deja enregistree
					listeStations.add(reseau.stepAt(i).getStation2());
				}
			}
		}
		return listeStations;
	}
	
	// GETTERS
	public TermList getLignes() {
		return lignes;
	}
	
	// SETTERS
	public void setLignes(TermList lignes) {
		this.lignes = lignes;
	}

	public static void main(String []args) {
		Network reseau1=new Network("steps.txt",'\t',"data");
		Lines ligne1=new Lines(reseau1);
		String term;
		System.out.println("Liste de Lignes:");
		for(int i=0;i<ligne1.getLignes().size();i++) {
			System.out.println(i+" : "+ligne1.getLignes().termAt(i));
		}
		System.out.println("\nSaisir la ligne : ");
		Scanner scan=new Scanner(System.in);
		int numeroLigne=scan.nextInt();
		scan.close();
		TermList stations=ligne1.findStations(numeroLigne);
		System.out.println("Les "+stations.size()+" stations de la ligne "+numeroLigne+" sont :");
		for (int i=0;i<stations.size();i++){
    		term=stations.termAt(i);
    		System.out.println("\t"+term);
    	}
	}
}