package TP4;

public abstract class AbstractPolicy {
	public AbstractPolicy(int n) { // n=nbr de phases
		
	}
	public abstract Behavior instancePhase();
	public abstract double dureePhase(int indice);
	public abstract Behavior[] getListeComporte();
	public abstract double[] getListeDurees();
}
