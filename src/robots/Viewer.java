package robots;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Utilitaires.Reader;

import TP4.*;

public class Viewer extends JPanel {
		private static final long serialVersionUID = 1L;
		protected Reader reader;
		protected static final int SPEED=200; // 200 ms
		protected static final int WIDTH=800, HEIGHT=800;
	    protected static final float saturation = (float) 0.90, brightness = (float) 0.70;
	    public static final Color ROBOT_COLOR = new Color(102,0,53);

		private BufferedImage img;
		private AbstractProblem pb;

        public Viewer(AbstractProblem pb) {
        	this.pb=pb;
        	this.img=null;
        	if (pb!=null) createImage();
        	reader=null;
            Timer timer = new Timer(SPEED, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
            timer.start();
        }
        
        public Viewer(String filename, AbstractProblem pb) {
        	this.pb=pb;
        	this.img=null;
        	if (pb!=null) createImage();
        	reader=new Reader(filename,'\t');
        	Timer timer = new Timer(SPEED, new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			repaint();
        		}
        	});
        	timer.start();
        }
        
        public void createImage(){
        	//create buffered image object img
        	img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        	for(int y = 0; y < HEIGHT; y++){
        		for(int x = 0; x < WIDTH; x++){
        			double dx=x/(WIDTH+0.0);
        			double dy=y/(HEIGHT+0.0);
        			Point p=new Point(dx,dy);
        			double gain=pb.teneur(p);
        			int id=(int)gain;
        			if ((id<0)||(id>255)){
        				System.err.println("The color index must be in [0,255]: "+id);
        				System.exit(-1);
        			}	
        			float hue = (float)((255-id)/360.0);
        			int rgb=Color.HSBtoRGB(hue, saturation, brightness);
        			img.setRGB(x,y,rgb);
        		}
        	}	
        }

        @Override
        public Dimension getPreferredSize() {
        	return new Dimension(WIDTH, HEIGHT);
        }

        @Override
        protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
        	if (img!=null)
        		g.drawImage(img, 0, 0, null);
        	if (reader!=null){
        		g.setColor(ROBOT_COLOR);
        		boolean done;
        		double[]line;
        		boolean end=false;
        		do {
        			line=reader.readLine();
        			done=(line==null);
        			if (done)end=true;
        			if ((!done)&&(line[0]==-1)) done=true;
        			if (!done){
        				double x=line[0];
        				int xp=(int)(x*WIDTH*0.99);
        				double y=line[1];
        				int yp=(int)(y*HEIGHT*0.99);
        				g.fillOval(xp,yp,8,8);
        				end=false;
        			}
        		}while(!done);
        		if (end) reader=new Reader(reader.getFilename(),'\t');
        	}
        }

        public static void display(AbstractProblem pb){
        	JFrame frame = new JFrame("Mission Viewer");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new BorderLayout());
        	File f = new File("data/robots.txt"); 
        	if (f.exists())
        		frame.add(new Viewer("data/robots.txt",pb));
        	else
        		frame.add(new Viewer(pb));
        	frame.pack();
        	frame.setVisible(true);
        }
}
