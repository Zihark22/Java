package TP4;

public class LocalBest extends Behavior {
	public LocalBest() {
		indice=2;
	}
	public void move(Robot R) {
		Point cible=R.getL();
		R.setP(R.getP().move(cible, 0.05));
	}
}
