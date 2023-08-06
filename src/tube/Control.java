package tube;

//	import java.util.Scanner;

import javax.swing.JOptionPane;

import TP2.Itinerary;
import TP2.Lines;
import TP2.Network;
import tube.gui.TubeView;
import Utilitaires.TermList;

public class Control {
	private TubeView tv;
	private String begin, end;
	private Network reseau1;
	
	public Control(Network tub, TubeView tuv){
		tv=tuv;
		reseau1=tub;
		begin=end=null;
	}
	public void clearItinerary(){
      	System.out.println("USER ACTION: clear the map");
		begin=end=null;
	}
	 /* -----------------------------------------------------------
	  * show the stations belonging to a line.
	  * -----------------------------------------------------------
	  */
	  public void showLine(int numLigne){
     	System.out.println("USER ACTION: line selection, index= "+numLigne);
     	
     	// mon code
     	
		Lines ligne1=new Lines(reseau1);
		TermList list=ligne1.findStations(numLigne);
		String term;
		for (int i=0;i<list.size();i++){
    		term=list.termAt(i);
    		System.out.println("\t"+term);
    	}
     	//////////////////////////////////
     	
     	
     	tv.show(list);
	  }
	/* -----------------------------------------------------------
	 * show an itinerary between two stations.
	 * -----------------------------------------------------------
	 */	  
	  public void showItinerary(int x, int y){
     	String station=tv.findClosestStation(x,y);
     	TermList sel=new TermList();
     	if (begin==null) {
     		begin=station;
         	System.out.println("USER ACTION: departure selection = "+station);
     		sel.add(begin);
     		tv.show(sel);
     	}
     	else if (end==null) {
     		end=station;
         	System.out.println("USER ACTION: arrival selection = "+station);
    		TermList selection=null;
    		Itinerary itineraire=new Itinerary(reseau1);
    		itineraire.marcheAlea(100,begin,end);
    		selection=itineraire.getParcoursOptimise();
     		if (selection==null) {
     			JOptionPane.showMessageDialog(tv, "No path has been found between "+begin+" and "+end);
     			sel=null;
     			begin=null;
     			end=null;
     		}
     		JOptionPane.showMessageDialog(tv, "Ouf ! Un chemin a été trouvé entre "+begin+" and "+end);
     		tv.show(selection);
     	}
	  }
}
