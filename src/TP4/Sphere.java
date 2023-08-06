package TP4;
import robots.*;

public class Sphere implements AbstractProblem {
	public double teneur(Point position) {
		double teneur=1-(Math.pow(position.getX(),2)+Math.pow(position.getY(),2))/2;
		teneur*=255;
		return teneur;
	}
	public static void  main(String args[]) {
		Sphere pb=new Sphere();
		Viewer.display(pb);
		Point point1=new Point(1,0);
		double ten=pb.teneur(point1);
		System.out.println(ten);
	}
}