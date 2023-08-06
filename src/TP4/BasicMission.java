package TP4;

import Utilitaires.Printer;
import robots.Viewer;

public class BasicMission {
	// ATTRIBUTS
	protected Robot []robots;
	protected AbstractProblem gise;
	protected int nbRobots;
	protected Printer pr;
	protected double teneurGmax;
	protected Point positionG;
	protected int toursRecherche;
	protected int toursFollow;
	protected int toursLocalBest;
	
	// CONSTRUCTEUR
	public BasicMission(AbstractProblem gisement,int nRobots) {
		this.nbRobots=nRobots;
		robots=new Robot[nRobots];
		gise=gisement;
		pr=null;
		teneurGmax=0;
		positionG=new Point(0,0);
		toursRecherche=50;
		toursFollow=30;
		toursLocalBest=30;
		init();
	}
	
	// METHODES
	public void init() {
		Point origine=new Point(0.5,0.5);
		Behavior explore=new Behavior();
		for(int i=0;i<nbRobots;i++) {
			robots[i]=new Robot(origine);
			robots[i].setBehavior(explore);
		}
	}
	public void collect() {
		for (int index=0;index<nbRobots;index++){ 
			robots[index].setTeneurCour(gise.teneur(robots[index].getP()));
			if(robots[index].getTeneurCour()>robots[index].getTeneurBest()) {
				robots[index].setTeneurBest(robots[index].getTeneurCour());
				robots[index].setL(robots[index].getP());
			}
		}
	}
	public void walk() {
		for (int index=0;index<nbRobots;index++){
			robots[index].walk();
		}
	}
	public void walkFollow() {
		Follow suivre=new Follow();
		for (int index=0;index<nbRobots;index++){
			suivre.move(robots[index]);
		}
	}
	public void walkLocalBest() {
		LocalBest suivre=new LocalBest();
		for (int index=0;index<nbRobots;index++){
			suivre.move(robots[index]);
		}
	}
	public void store() {
		pr.println("-1");
		for (int index=0;index<nbRobots;index++){
			pr.println(robots[index].getPositionX()+"\t"+robots[index].getPositionY());
		}
	}
	public void run() {
		pr=new Printer("data/robots.txt");
	// RECHERCHES
		for (int iter=0;iter<toursRecherche;iter++){
			collect();
			store();
			System.out.println("iter="+iter+" "+getAllBestGain());
			walk();
		}
	// FOLLOW
		for (int iter=0;iter<toursFollow;iter++){
			store();
			walkFollow();
		}
	// LOCALBEST
		for (int iter=0;iter<toursLocalBest;iter++){
			store();
			walkLocalBest();
		}
		System.out.println("Teneur max G ="+teneurGmax+" en "+positionG.getX()+","+positionG.getY());
		pr.close();
		
	}
	public double getAllBestGain() {
		for (int index=0;index<nbRobots;index++) {
			if(robots[index].getTeneurCour()>robots[index].getTeneurBest()) {
				robots[index].setTeneurBest(robots[index].getTeneurCour());
				robots[index].setL(robots[index].getP());;
			}
			if(robots[index].getTeneurBest()>teneurGmax) {
				teneurGmax=robots[index].getTeneurBest();
				positionG=robots[index].getL();
			}
		}
		for (int index=0;index<nbRobots;index++) {
			robots[index].setTeneurBestG(teneurGmax);
			robots[index].setG(positionG);
		}
		return teneurGmax;
	}
	
	public static void main(String []args) {
		Eggholder pb=new Eggholder();
		//Sphere pb=new Sphere();
		BasicMission mission=new BasicMission(pb,20);
		Viewer.display(pb);
		mission.run();
	}
}
