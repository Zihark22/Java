package TP2;

public class Step {
	// ATTRIBUTS
	private String station1;
	private String ligne;
	private String station2;
	
	// CONSTRUCTEUR
	public Step(String Station1, String Station2, String Ligne) {
		station1=Station1;
		station2=Station2;
		ligne=Ligne;
	}
	
	// METHODES
	public String getNext(String station) {
		if(station.equals(station1)) {
			return station2;
		}
		else if(station.equals(station2)) {
			return station1;
		}
		else {
			return null;
		}
	}
	public void Test() {
		String depart=this.getStation1();
		String arrivee=this.getStation2();
		String line=this.getLigne();
		String next=this.getNext(depart);
		System.out.println(depart+"\t"+line+"\t"+arrivee);
		if(next.equals(arrivee)) {
			System.out.println("Test bon");
		}
	}
	@Override
	public String toString() {
		return "Step [station1=" + station1 + ", ligne=" + ligne + ", station2=" + station2 + "]";
	}
	
	// GETTERS
	public String getStation1() {
		return station1;
	}
	public String getLigne() {
		return ligne;
	}
	public String getStation2() { 
		return station2;
	}
	
	// SETTERS
	public void setStation1(String station1) {
		this.station1 = station1;
	}
	public void setLigne(String ligne) {
		this.ligne = ligne;
	}
	public void setStation2(String station2) {
		this.station2 = station2;
	}
	
	public static void main(String [] args) {
		Step etape1 = new Step("Paris","Marseille","Charles de Gaulle");
		etape1.Test();
	}
}
