package TP4;

import robots.Viewer;

public class Eggholder  implements AbstractProblem {
	public double teneur(Point position) {
		int k=1024;
		double x=k*(position.getX()-0.5);
		double y=k*(position.getY()-0.5);
		double Yet47=y+47;
		double teneur=-Yet47*Math.sin(Math.sqrt(Math.abs(x/2+Yet47)))-x*Math.sin(Math.sqrt(Math.abs(x-Yet47)));
		teneur=-teneur;
		teneur+=1049.13; // ramene le minimum a 0
		teneur/=1049.13+959.64; // ramene le maximum à 1
		teneur*=255; // met le max à 255
		return teneur;
	}
	public static void  main(String args[]) {
		Eggholder pb=new Eggholder();
		Viewer.display(pb);
		Point point1=new Point(0,1);
		double ten=pb.teneur(point1);
		System.out.println(ten);
	}
}