package TP1;
//import java.util.Arrays;
import java.util.Scanner;
import Utilitaires.*;

public class InfoVols {
	// ATTRIBUTS
	private static String filename = "departs.txt";
	
	// METHODES
	public static void afficheVolsVille(String ville) {
		int nbDeparts=0;
		TabFileReader.readTextFile(filename, '\t', "data");
		for (int i = 0; i < TabFileReader.nrow(); i++) {
			if(ville.equals(TabFileReader.wordAt(i,1))) 
			{
				for (int j = 0; j < TabFileReader.ncol(); j++) 
				{
					System.out.print("\t"+TabFileReader.wordAt(i, j));
				}
				nbDeparts++;
				System.out.println();
			}
		}
		if(nbDeparts==0) {
			System.out.println("\nIl n'y a aucun départ de cette ville.");
		}
		else {
			System.out.println("\nIl y a "+nbDeparts+" départs de la ville '"+ville+"'.");
		}
	}
	
	public static void afficheVols2Villes(String ville1, String ville2) {
		TabFileReader.readTextFile(filename, '\t', "data"); // lecture du fichier
		System.out.println();
		
		for (int i = 0; i < TabFileReader.nrow(); i++) {
			if (ville1.equals(TabFileReader.wordAt(i, 1))) { // trouve ville de depart
				//SANS ESCALE
				if (ville2.equals(TabFileReader.wordAt(i, 2))) {
//					for (int j = 0; j < TabFileReader.ncol(); j++) 
//					{
//						System.out.print( "\t"+TabFileReader.wordAt(i, j));
//					}
//					System.out.println();
				}
				// AVEC ESCALE
				else 
				{
					String escale=TabFileReader.wordAt(i, 2); 
					String escaleStringHarrive=TabFileReader.wordAt(i, 4); // heure arrivee
					String [] escaleHtemp = escaleStringHarrive.split(":"); // separe h et min
					int escaleIntHarrive = Integer.parseInt(escaleHtemp[0])*60 + Integer.parseInt(escaleHtemp[1]); // conversion en min
							
					for (int j = 0; j < TabFileReader.nrow(); j++) {
						if(escale.equals(TabFileReader.wordAt(j,1)) && ville2.equals(TabFileReader.wordAt(j,2))) // recherche vol escale et ville d'arrivee
						{
							String escaleStringHdepart=TabFileReader.wordAt(j, 3);
							escaleHtemp = escaleStringHdepart.split(":");
							int escaleIntHdepart = Integer.parseInt(escaleHtemp[0])*60 + Integer.parseInt(escaleHtemp[1]); // sauvegarde min de depart de l'escale
							
							if(escaleIntHdepart > escaleIntHarrive + 60) // trouve temps escale > 1h
							{
								for (int k = 0; k < TabFileReader.ncol(); k++) // affiche vol avant l'escale
								{
									System.out.print("\t"+TabFileReader.wordAt(i, k));
								}
								System.out.println();
								
								for (int k = 0; k < TabFileReader.ncol(); k++) // affiche vol après l'escale
								{
									System.out.print("\t"+TabFileReader.wordAt(j, k));
								}
								System.out.println();
							}
						}
					}
				}
			}
		}
	}
	
	public static void afficheAeroport() {
		TabFileReader.readTextFile(filename, '\t', "data");
		TermList villeTab = new TermList();
		int villeTabIndex = 0;
		boolean found =false;
		
		for (int i = 0; i < TabFileReader.nrow(); i++) { // parcours des vols
			for(int j = 0; j < villeTabIndex; j++) { // parcours les villes
				if(villeTab.termAt(j).equals(TabFileReader.wordAt(i,2))) { // si la ville est en arrivee du vol
					found = true; // ville trouvee
				}
			}
			if (found == false) { // si aucune ville de trouvée
				villeTab.add(TabFileReader.wordAt(i,2)); // ajoute la ville d'arrivee du vol à la liste
				villeTabIndex++;
			}
			found = false; // on reiniitialise la recherche
		}
		System.out.println("Il y a "+villeTabIndex+" villes desservies :");
		villeTab.trier(); // trie par ordre alphabétique des noms de villes
		for(int j = 0; j < villeTabIndex; j++) { // affichage des villes
			System.out.println("\t"+villeTab.termAt(j));
		}
	}

	
	public static void main(String [] args) {
		System.out.println("Menu :");
		System.out.println("\tTaper 0: Quitter ");
		System.out.println("\tTaper 1: affiche tous les vols d'1 ville.");
		System.out.println("\tTaper 2: affiche les vols reliant 2 villes.");
		System.out.println("\tTaper 3: affiche les aéroports.");
		Scanner scan=new Scanner(System.in);
		int choix=-1;
		do {
			System.out.println("\nChoix : ");
			choix=scan.nextInt();
			scan.nextLine(); // LECTURE RETOUR CHARIOT
			switch(choix) {
				case 0: 
					System.out.println("\nFIN");
					break;
				case 1:
					System.out.println("Ville ?");
					String ville=scan.nextLine();
					afficheVolsVille(ville);
					break;
				case 2:
					System.out.println("Ville de départ :");
					String ville1=scan.nextLine();
					System.out.println("Ville d'arrivée :");
					String ville2=scan.nextLine();
					afficheVols2Villes(ville1,ville2);
					break;
				case 3:
					afficheAeroport();
					break;
				default :
					System.out.println("\n\tErreur !!");
			}
		}while(choix!=0);
		scan.close();
	}
	
}
