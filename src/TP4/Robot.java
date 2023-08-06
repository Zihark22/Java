package TP4;

public class Robot {
	// ATTRIBUTS
	private Point P; // position courante
	private Point L; // position max teneur
	private double teneurCour; // teneur courant
	private double teneurBest; // max teneur
	private static Point G; // position max teneur colonie
	private static double teneurBestG; // max teneur colonie
	private Behavior behavior;
	
	// CONSTRUCTEURS
	public Robot() {
		P.setX(0);
		P.setY(0);
		behavior=null;
		teneurCour=0;
		teneurBest=0;
		L=new Point(0,0);
		G=new Point(0,0);
		teneurBestG=0;
	}
	public Robot(Point Pt) {
		P=Pt;
		behavior=null;
		teneurCour=0;
		teneurBest=0;
		L=new Point(0,0);
		G=new Point(0,0);
		teneurBestG=0;
	}
	
	// METHODES
	public void walk(){
		if (behavior!=null) {
			behavior.move(this);
		}
		else {
			System.out.println("erreur comportement");
		}
	}
	
	// GETTERS
	public Point getL() {
		return L;
	}
	public double getTeneurBest() {
		return teneurBest;
	}
	public Point getG() {
		return G;
	}
	public static double getTeneurBestG() {
		return teneurBestG;
	}
	public double getPositionX() {
		return P.getX();
	}
	public double getTeneurCour() {
		return teneurCour;
	}
	public double getPositionY() {
		return P.getY();
	}
	public Point getP() {
		return P;
	}
	
	// SETTERS
	public void setL(Point l) {
		L = l;
	}
	public void setTeneurBest(double teneurBest) {
		this.teneurBest = teneurBest;
	}
	public void setG(Point g) {
		G = g;
	}
	public void setTeneurBestG(double teneurBestG) {
		Robot.teneurBestG = teneurBestG;
	}
	public void setTeneurCour(double teneurCour) {
		this.teneurCour = teneurCour;
	}
	public void setP(Point position) {
		P = position;
	}
	public void setBehavior(Behavior comportement) {
		behavior=comportement;
	}
}
