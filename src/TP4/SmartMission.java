package TP4;

import Utilitaires.Printer;
import java.text.DecimalFormat;
import robots.Viewer;

public class SmartMission extends BasicMission {
	// ATTRIBUTS
	private AbstractPolicy policy;
	private int nbrPhases;
	private DecimalFormat df;
	
	// CONSTRUCTEUR
	public SmartMission(AbstractProblem gisement, int nRobots, AbstractPolicy police,int phases) {
		super(gisement, nRobots);
		this.policy=police;
		nbrPhases=phases;
		initi();
		df = new DecimalFormat();
		df.setMaximumFractionDigits(4);
		df.setMinimumFractionDigits(1);
	}
	
	// METHODES
	public void initi() {
		Point origine=new Point(0.5,0.5);
		for(int i=0;i<nbRobots;i++) {
			robots[i]=new Robot(origine);
			robots[i].setBehavior(policy.instancePhase());
		}
	}
	public void run() {
		pr=new Printer("data/robots.txt");
		for(int phase=0;phase<nbrPhases;phase++) {
			for(int i=0;i<nbRobots;i++) { // associe à chaque robot le comportement de la phase p
				robots[i].setBehavior(policy.getListeComporte()[phase]);
			}
			for(int i=0;i<policy.getListeDurees()[phase];i++) {
				if(policy.getListeComporte()[phase].getIndice()==0) // seulement pour la phase de recherche
				{
					collect();
				}
				store();
				if(policy.getListeComporte()[phase].getIndice()==0) // seulement pour la phase de recherche
				{
					System.out.println("iter="+i+" "+df.format(getAllBestGain()));
				}
				walk();
			}
			System.out.println();
		}
		pr.close();
		System.out.println("Teneur max G = "+df.format(teneurGmax)+" en ("+df.format(positionG.getX())+","+df.format(positionG.getY())+")");
		afficheTeneurMoy();
	}
	public void afficheTeneurMoy() {
		double teneurMoy=0;
		for(int i=0;i<nbRobots;i++) {
			teneurMoy+=robots[i].getTeneurCour();
		}
		teneurMoy/=nbRobots;
		System.out.println("Teneur moyen = "+df.format(teneurMoy));
	}
	
	public static void main(String []args) {
		//Eggholder pb=new Eggholder();
		Sphere pb=new Sphere();
		int nombre_robots=20;
		int nb_phases=1;
		int tempsPhase=40;
		//SimplePolicy policeSimple=new SimplePolicy(nb_phases);
			//policeSimple.add(new Behavior(),tempsPhase);
			//policeSimple.add(new Follow(),tempsPhase);
			//policeSimple.add(new Hybride(0.8),tempsPhase);
			
		EvolutiveHybride policeSimple=new EvolutiveHybride(nb_phases);
		for(int i=0;i<nb_phases;i++) {
			policeSimple.add(tempsPhase);
		}
		SmartMission mission=new SmartMission(pb,nombre_robots,policeSimple,nb_phases);
		//Viewer.display(pb);
		mission.run();
	}
}
