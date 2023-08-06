package Utilitaires;
/*
 * This class is used to store a list of String terms.
 * Do not modify it.
 * Run the main method to see how you can use it.
 */


public class TermList {
    private String[]terms;
    private int nb;
    
  public TermList(){
        terms=new String[10000];
        nb=0;
}

public boolean isEmpty( ) { return nb==0; }


	public void add(String name){
		terms[nb++]=name;
	}

    public boolean exists(String term) {
        for (int i=0;i<nb;i++) {
            if (terms[i].equals(term))
            	return true;
        }
        return false;
    }
    public String termAt(int index){
    	return terms[index];
    }
    
    public int size() {return nb; }
    
    private int changeIndice(int current, int []indices) {
    	int change=0;
		for(int k=0;k<nb;k++) {
			for(int l=0;l<nb;l++) {
				if(change==indices[l]) {
					change++;
					if(change>=indices.length) {
						change=0;
					}
				}
			}
		}
    	return change;
    }
    
    private boolean indicePresent(int i,int []indices ) {
    	for(int c=0;c<indices.length;c++) {
    		if(i==indices[c]) {
    	    	return true;
    		}
    	}
    	return false;
    }
    
    public void trier() { // ordre alphabetique
    	String[]termsSorted=new String[nb]; // = null, … , null
    	int current_indice=0;
    	String actuel=terms[current_indice];
    	int []indiceTriees=new int[nb]; // = 0, … ,0
    	for(int i=0;i<nb;i++) {
    		indiceTriees[i]=-1;
    	}
    	
    	int compteur_mots_triees=0;

    	while(compteur_mots_triees<nb) {
    		if(!indicePresent(current_indice,indiceTriees)) {
    			for(int j=0;j<nb;j++) {
    				for(int i=0;i<nb;i++) {
    					if(actuel.compareTo(terms[i])>0 && !indicePresent(i,indiceTriees)) {	// si actuel est après terms[i]
    						actuel=terms[i];
    						current_indice=i;
    					}
    				}
    			}
    			termsSorted[compteur_mots_triees]=actuel;
    			indiceTriees[compteur_mots_triees]=current_indice;
    			compteur_mots_triees++;

    		}
    		current_indice=changeIndice(current_indice,indiceTriees);
    		actuel=terms[current_indice];
    	}
    	terms=termsSorted;
    }

    public static void main(String[] args) {
    	TermList set=new TermList();
    	String term;
    	//set.add("A");set.add("B");set.add("C");
    	set.add("orange");set.add("bleu");set.add("bonjour");set.add("araignee");set.add("trou");set.add("zoulou");set.add("jupe");
    	//if (set.exists("C"))
    	//	System.out.println("C already exists in the list");
    	
    	System.out.println("Liste triée des termes : ");
    	set.trier();
    	for (int i=0;i<set.size();i++){
    		term=set.termAt(i);
    		System.out.println("\t"+term);
    	}	
	}
} 



