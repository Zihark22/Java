package TP4;
import java.util.Random;

public class Point {
	// ATTRIBUTS
	private double x;
	private double y;
	private double ymin;
	private double ymax;
	private double xmin;
	private double xmax;
	private static Random random;
	
	// CONSTRUCTEURS
	public Point() {
		xmax=1;
		xmin=0;
		ymax=1;
		ymin=0;
		random=new Random();
		x=random.nextDouble();
		y=random.nextDouble();
	}
	public Point(double coordX, double coordY) {
		x=coordX;
		y=coordY;
		xmax=1;
		xmin=0;
		ymax=1;
		ymin=0;
	}
	
	// METHODES
	public double distance(Point P) {
		double distance=0;
		double coteX=Math.abs(P.x-this.x);
		double coteY=Math.abs(P.y-this.y);
		distance=Math.sqrt(Math.pow(coteX,2)+Math.pow(coteY,2));
		return distance;
	}
	public Point move(Point cible,double d) {
		double distanceCible=distance(cible);
		if(distanceCible<d) {
			return cible;
		}
		else {
			double NewPtX=x+d*(cible.x-x)/distanceCible;
			double NewPtY=y+d*(cible.y-y)/distanceCible;
			Point newPoint=new Point(NewPtX,NewPtY);
			
			if(NewPtX<xmin) {
				newPoint.x=0;
			}
			else if(NewPtX>xmax) {
				newPoint.x=1;
			}
			if(NewPtY<ymin) {
				newPoint.y=0;
			}
			else if(NewPtY>ymax) {
				newPoint.y=1;
			}
			return newPoint;
		}
	}
	public void displayCoord(String nom) {
		System.out.println(nom+"=("+x+" , "+y+")");
	}
	
	// GETTERS
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	// SETTERS
	public void setY(double y) {
		this.y = y;
	}
	public void setX(double x) {
		this.x = x;
	}
	
	public static void main(String args[]) {
		Point point1=new Point(1,1);
		Point point2=new Point(0,0);
		double dist12=point1.distance(point2);
		System.out.println("distance="+dist12);
		Point newPoint=point1.move(point2, dist12/2);
		System.out.println("nouveau point: \nx="+newPoint.x+"\ny="+newPoint.y);
	}
}
