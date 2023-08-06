package TP2;

import Utilitaires.*;

public class Network {
	// ATTRIBUTS
	private Step []steps;
	private int nsteps;
	private TermList listStations;
	private int nbStations;

	// CONSTRUCTEUR
	public Network(String fileName,char separator,String packageName) {
		lectureFichier(fileName,separator,packageName);
		nbStations=0;
		listStations = new TermList();
	}
	//METHODES
	private void lectureFichier(String fileName,char separator,String packageName) {
		TabFileReader.readTextFile(fileName,separator,packageName);
		nsteps=TabFileReader.nrow();
		steps = new Step[nsteps];
		for(int i=0;i<nsteps;i++) {
			steps[i]=new Step(TabFileReader.wordAt(i,0),
							  TabFileReader.wordAt(i,2),
							  TabFileReader.wordAt(i,1));
		}
	}
	public Step stepAt(int index) {
		return steps[index];
	}
	public void testSteps() {
		int nbSteps=this.getNsteps();
		System.out.println("nombre de steps ="+nbSteps);
		Step etape=this.stepAt(2);
		etape.Test();
	}
	public int numberOfStations() {
		int nbSteps=this.getNsteps();
		for(int i=0;i<nbSteps;i++) {
			if(listStations.exists(this.stepAt(i).getStation1())==false) {
				listStations.add(this.stepAt(i).getStation1());
				nbStations++;
			}
			if(listStations.exists(this.stepAt(i).getStation2())==false) {
				listStations.add(this.stepAt(i).getStation2());
				nbStations++;
			}
		}
		return nbStations;
		
	}
	public static void testStations(Network reseau) {
		reseau.numberOfStations();
		String term;
		System.out.println("Nombre de stations :"+reseau.nbStations);
		reseau.listStations.trier();
		for (int i=0;i<reseau.nbStations;i++){
    		term=reseau.listStations.termAt(i);
    		System.out.println("\t"+term);
    	}	
	}
	
	// GETTERS
	public TermList getListStations() {
		listStations.trier();
		return listStations;
	}
	public int getNsteps() {
		return nsteps;
	}
	
	// SETTERS
	public void setSteps(Step[] steps) {
		this.steps = steps;
	}
	
	public static void main(String []args) {
		Network reseau1=new Network("steps.txt",'\t',"data");
		//testSteps(reseau1);
		testStations(reseau1);
	}	
}
