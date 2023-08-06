package TP2;

import Utilitaires.TermList;

public class Analysis {
	// ATTRIBUTS
	private TermList listeLignes;
	private TermList listeStat;
	private int []degres;
	private Network reseau;
	private Lines lignes;
	private int nbStations;
	private int nbLignes;
	
	// CONSTRUCTEUR
	public Analysis() {
		reseau=new Network("steps.txt",'\t',"data");
		nbStations=reseau.numberOfStations();
		lignes=new Lines(reseau); 
		listeStat=new TermList();
		listeLignes=lignes.getLignes();
		degres=new int[nbStations];
		nbLignes=listeLignes.size();
	}
	
	// METHODES
	public void affiche() {
		System.out.println("Liste des "+ nbLignes +" lignes : ");
		listeLignes.trier();
		for(int i=0;i<nbLignes;i++) {
			System.out.println("\t"+listeLignes.termAt(i));
    	}
		
		nbStations=reseau.numberOfStations();
		System.out.println("\nNombre de stations :"+nbStations);
		for (int i=0;i<nbStations;i++){
			System.out.println("\t"+listeStat.termAt(i)+" =  "+degres[i]);
    	}
		
	}
	
	// SETTERS
	public void setListeStations() {
		nbStations=reseau.numberOfStations();
		for (int i=0;i<nbStations;i++){
			listeStat.add(reseau.getListStations().termAt(i));
    	}
	}
	public void setDegres() {
		TermList stationVoisins;
		String currentStation;
		int nbSteps=reseau.getNsteps();
		System.out.println("Nombre de steps : "+nbSteps);
		for(int i=0;i<nbStations;i++) {
			stationVoisins=new TermList();
			currentStation=listeStat.termAt(i);
			degres[i]=0;
			for(int j=0;j<nbSteps;j++) {
				String station1=reseau.stepAt(j).getStation1();
				String station2=reseau.stepAt(j).getStation2();
				if(station1.equals(currentStation) && stationVoisins.exists(station2)==false) {
					degres[i]++;
					stationVoisins.add(station2);
				}
				else if(station2.equals(currentStation) && stationVoisins.exists(station1)==false ) {
					degres[i]++;
					stationVoisins.add(station1);
				}
			}
		}
	}
	
	public static void main(String []args) {
		Analysis analyse=new Analysis();
		analyse.setListeStations();
		analyse.setDegres();
		analyse.affiche();
	}
}
