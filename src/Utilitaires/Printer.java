package Utilitaires;

import java.io.*;
//import java.util.StringTokenizer;

/**
 * Class for printing text files.
 */
public class Printer {
   public PrintWriter out; 
  /**
   * 
   * @param filename the resulting file: previous file will be erased
 * @throws Exception 
   */
  public Printer(String filename){
      try{
        out= new PrintWriter(new BufferedWriter(new FileWriter(filename)));     
      }
      catch(IOException e) {
          System.err.println("Printer file error: "+filename);
          System.exit(-1);
          
      }
  }
 /**
  * Prints a row into the file
  * @param doc
  */
  public void println(String doc) {
		  out.println(doc);
  }
 /**
  * Closes the file
  */
   public void close() {
        out.close();   
  }
 
}
