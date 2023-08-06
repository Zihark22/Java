package TP7;

import java.io.*;
import java.util.*;

public class Dico {
	private BufferedReader in=null;
	private Hashtable<String,String> table;
	private Hashtable<String,String> table2;

	public Dico(String fic) {
		String line;
		table=new Hashtable<String,String>();
		table2=new Hashtable<String,String>();
		try {
			in= new BufferedReader(new FileReader(fic));
			while ((line = in.readLine()) != null)  {
				String []strs=line.split("\t");
				if (strs.length!=2) throw new Exception();
				table.put(strs[0], strs[1]);
				table2.put(strs[1], strs[0]);
			}
		}

		catch(Exception e){
			System.out.println("probleme de lecture du fichier");
			System.exit(-1);
		}     
	}

	public void add(String key, String value){
		table.put(key, value);
	}

	public void save(String fic){
		try{
			java.io.PrintWriter out= new java.io.PrintWriter(new java.io.BufferedWriter(new java.io.FileWriter(fic)));
			for (String key : table.keySet()) {
				out.println(key + "\t" + table.get(key));
			}
			out.close();
		}
		catch(java.io.IOException e) {
			System.out.println("probleme d'ecriture du fichier "+fic);
		}
	}

	public String find(String key){
		//System.out.println("table="+table.get(key));
		if(table.get(key)!=null) {
			return table.get(key);
		}
		else {
			return table2.get(key);
		}
	}
	
	public static void main(String [] args) {
		Dico dico=new Dico("data/dico.txt");
		String traduction=dico.find("papillon");
		if (traduction==null) {
			System.out.println("le mot ne figure pas dans le dictionnaire"); 
		}
		else { 
			System.out.println("la traduction est "+traduction);
		}
	}
}
