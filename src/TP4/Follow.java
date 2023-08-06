package TP4;

public class Follow extends Behavior {
	public Follow() {
		indice=1;
	}
	public void move(Robot R) {
		Point cible=R.getG();
		Point positionRobot=R.getP();
		Point newP=positionRobot.move(cible, 0.05);
		R.setP(newP);
	}
}