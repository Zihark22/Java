package TP4;

public class SimplePolicy extends AbstractPolicy {
	// ATTRIBUTS
	private Behavior []listeComporte;
	private double []listeDurees;
	private int index;
	
	// CONSTRUCTEUR
	public SimplePolicy(int n) {
		super(n);
		// TODO Auto-generated constructor stub
		listeComporte=new Behavior[n];
		listeDurees=new double[n];
		index=0;
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
	public void afficheComporte() {
		System.out.println("Liste des comportements");
		System.out.print("{");
		for(int i=0;i<index;i++) {
			if(listeComporte[i].getIndice()==0) {
				System.out.print("Behavior");
			}
			else if(listeComporte[i].getIndice()==1) {
				System.out.print("Follow");
			}
			else if(listeComporte[i].getIndice()==2) {
				System.out.print("LocalBest");
			}
			if(i!=index-1) {
				System.out.print(",");
			}
		}
		System.out.print("}");
	}
	public void add(Behavior comporte,double duree) {
		listeComporte[index]=comporte;
		listeDurees[index]=duree;
		index++;
	}
	
	// GETTERS
	public Behavior[] getListeComporte() {
		return listeComporte;
	}
	public double[] getListeDurees() {
		return listeDurees;
	}
	
	// SETTERS
	public void setListeComporte(Behavior[] listeComporte) {
		this.listeComporte = listeComporte;
	}
	public void setListeDurees(double[] listeDurees) {
		this.listeDurees = listeDurees;
	}
	
	public static void main(String []args) {
		SimplePolicy policy=new SimplePolicy(3);
		policy.add(new Behavior(),60); // phase 0 de durée 60 itérations 
		policy.add(new LocalBest(),20); // phase 1
		policy.add(new Follow(),20); // phase 2
		policy.afficheComporte();
	}
}
