package Utilitaires;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class may be used to read text file containing real values.
 */
public class Reader {
	protected BufferedReader in;
	protected char separator;
	protected String filename;

	public Reader(String filename,char separator){
		this.filename=filename;
		try {
			in= new BufferedReader(new FileReader(filename));
			this.separator=separator;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Reads the current line
	 */
		  public double[] readLine(){
				String line;
				try {
					if ((line = in.readLine()) != null) {
						String []fields=line.split(""+separator);
						double[]res=new double[fields.length];
						int i=0;
						for (String token: fields){
							res[i++]=Double.parseDouble(token);
						}
						return res;
					}
					else return null;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}  		  
		  }
		  
		  
		  public void close(){
			  try {
				in.close();
			} catch (IOException e) {
		          System.err.println("Reader file error: "+filename);
		          System.exit(-1);
			}
		  }
		public String getFilename() {
			return filename;
		}
		  
		  
}
