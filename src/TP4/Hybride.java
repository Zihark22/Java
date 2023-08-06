package TP4;

public class Hybride  extends Behavior {
	private double proba;
	
	public Hybride(double prob) {
		proba=prob;
	}
	public void move(Robot R) {
		double rand=Math.random();
		//System.out.println("Proba="+rand+" seuil="+proba);
		if(rand<proba) {
			Follow suivre = new Follow();
			R.setBehavior(suivre);
		}
		else {
			Behavior recherche = new Behavior();
			R.setBehavior(recherche);
		}
	}
	
	public static void main(String[] args) {
	}
}