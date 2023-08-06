package TP4;

public class EvolutiveHybride extends AbstractPolicy {
	// ATTRIBUTS
	private Behavior []listeHybrides;
	private double []listeDurees;
	private double []listeProbas;
	private int index;
	private double prob;
	
	// CONSTRUCTER
	public EvolutiveHybride(int n) {
		super(n);
		listeHybrides=new Behavior[n];
		listeDurees=new double[n];
		index=0;
		prob=Math.random();
	}
	
	// METHODES
	public Behavior instancePhase() {
		Behavior comportement=new Behavior();
		return comportement;
	}
	public double dureePhase(int indice) {
		return listeDurees[indice];
	}
	public void afficheDurees() {
		System.out.print("Durees : ");
		for(int i=0;i<index;i++) {
			System.out.println(listeDurees[i]+",");
		}
		System.out.println();
	}
	public void add(double duree) {
		double proba=calculeProba(index);
		Hybride comporte = new Hybride(proba);
		listeHybrides[index]=comporte;
		listeDurees[index]=duree;
		index++;
	}
	public void setProbas() {
		for(int i=0;i<index;i++) {
			listeProbas[i]=calculeProba(i);
		}
	}
	public double calculeProba(int indice) {
		double mu=1.5;
		prob+=mu*prob*(1-prob);
		return prob;
	}
	
	// GETTERS AND SETTERS
	public Behavior[] getListeComporte() {
		return listeHybrides;
	}
	public void setListeComporte(Behavior[] listeComporte) {
		this.listeHybrides = listeComporte;
	}
	public double[] getListeDurees() {
		return listeDurees;
	}
	public void setListeDurees(double[] listeDurees) {
		this.listeDurees = listeDurees;
	}
}
