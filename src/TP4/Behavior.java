package TP4;

public class Behavior {
	protected int indice; // indice pour reconnaitre le comportement et  faire la collect 
	
	public Behavior() {
		indice=0;
	}
	public void move(Robot R) {
		Point A=new Point(); // point aléatoire
		Point M=new Point(0.5,0.5);
		double d=0.05;
		double cibleX=0;
		double cibleY=0;
		double distanceCible=A.distance(M);

		if(distanceCible>d) {
			cibleX=R.getPositionX()+A.getX()-M.getX();
			cibleY=R.getPositionY()+A.getY()-M.getY();
		}
		Point cible=new Point(cibleX,cibleY);
		Point newP=R.getP().move(cible, d);
		R.setP(newP);
	}
	
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public static void main(String []args) {
		Point p=new Point(0,0); // position initiale pour le robot
		Robot robot=new Robot(p);
		Behavior explore=new Behavior();
		robot.setBehavior(explore); // le robot possède le comportement explore for (int i=0;i<10;i++){
		for (int i=0;i<30;i++){
			robot.walk();
			System.out.println(i+" : "+robot.getPositionX()+" , "+robot.getPositionY());
		}
	}
}