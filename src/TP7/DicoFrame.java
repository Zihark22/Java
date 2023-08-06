package TP7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class DicoFrame  extends JFrame implements ActionListener {
	private JPanel top, bottom;
	private JComboBox box;
	private final String[] legend = { "--","Français/Anglais", "Français/Espagnol"};
	private JButton ok;
	private JButton clear;
	private JTextField text1;
	private JTextField text2;
	private Dico dico;
	private JMenuBar mainMenuBar;
	private JMenu menuFile; 
	private JMenuItem miOpen;
	private JMenuItem miSave;
	private JFileChooser fileChoice;
	private JDialog dicoCharge;
	private JLabel texteChargement;
	private FlowLayout layout1;
	private JButton valide;
	private Dimension dimEcran;
	private Dimension dimAppli;
	private JLabel newTrad;
	private JTextField textNewTrad;

	public DicoFrame(String arg0) {
		super(arg0);
		dico=new Dico("data/dico.txt");

	// DIMENSIONS ET POSITION
		dimEcran=Toolkit.getDefaultToolkit().getScreenSize();
		final int LARGEUR_APPLI=dimEcran.width/2;
		final int HAUTEUR_APPLI=dimEcran.height/2;
		//dimAppli=this.getSize();
		dimAppli=new Dimension();
		dimAppli.width=LARGEUR_APPLI;
		dimAppli.height=HAUTEUR_APPLI;
		setLocation((dimEcran.width-dimAppli.width)/2,(dimEcran.height-dimAppli.height)/2);
		setSize(dimAppli);
		setMinimumSize(new Dimension(dimAppli.width/2,dimAppli.height/2));
		setMaximumSize(dimEcran);
		setResizable(true);
		
		Container pane=getContentPane();
		pane.setLayout(new GridLayout(2,0));
		top=new JPanel();
		top.setBackground(new Color(0x220033));
		bottom=new JPanel();
		bottom.setBackground(new Color(0x5CBFF6));
		pane.add(top);
		pane.add(bottom);
		
		mainMenuBar=new JMenuBar();
		menuFile=new JMenu("File");
		miOpen=new JMenuItem("Open");
		miOpen.addActionListener(this);
		miSave=new JMenuItem("Save");
		miSave.addActionListener(this);
		menuFile.add(miOpen);
		menuFile.add(miSave);
		mainMenuBar.add(menuFile);
		setJMenuBar(mainMenuBar);
		
		fileChoice=new JFileChooser("/Users/badajozj/Desktop/Polytech/Cours/ETN4/Java/TPs/ProjetTP/data");
		fileChoice.setSize(400,300);
		
		dicoCharge=new JDialog(this,false);
		texteChargement=new JLabel("Dictionnaire chargé !");
		layout1=new FlowLayout(FlowLayout.CENTER,10,10);
		dicoCharge.setLayout(layout1);
		dicoCharge.add(texteChargement);
		valide=new JButton("Ok");
		valide.addActionListener(this);
		dicoCharge.add(valide);
		dicoCharge.setSize(200,100);
		dicoCharge.setLocation((dimEcran.width-200)/2,(dimEcran.height-100)/2);
		
		//dicoCharge.setSize();
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 400);
	}

	public void completeTop(){
		GridLayout grille=new GridLayout(2,3,10,6);
		top.setLayout(grille);
		
		JPanel first=new JPanel();
		first.setBackground(new Color(0xFF9933));
		JLabel mot=new JLabel("Mot");
		first.add(mot);
		first.setLayout(new GridLayout(2,5));
		text1=new JTextField("");
		text1.setColumns(20);
		first.add(text1);
		
		JPanel second=new JPanel();
		second.setBackground(new Color(0xFF9933));
		second.setLayout(new GridLayout(2,5));
		second.add(new JLabel("Traduction"));
		text2=new JTextField("");
		text2.setColumns(20);
		second.add(text2);

		top.add(new JLabel());
		top.add(first);
		top.add(new JLabel());
		top.add(new JLabel());
		top.add(second);
		top.add(new JLabel());
	}
	public void completeBottom(){
		GridLayout grille=new GridLayout(2,3);
		bottom.setLayout(grille);
		
		box = new JComboBox(legend);
		ok=new JButton("Traduire");
		clear=new JButton("Clear");
		newTrad=new JLabel("Nouvelle traduction : ");
		textNewTrad=new JTextField("traduction");
		Insets m=new Insets(0,150,0,0);
		textNewTrad.setMargin(m);
		textNewTrad.setColumns(20);
		textNewTrad.setBackground(new Color(0x009933));
		
		bottom.add(box);
		bottom.add(ok);
		bottom.add(clear);
		bottom.add(newTrad);
		bottom.add(textNewTrad);
		
		ok.addActionListener(this);
		clear.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		String event=evt.getActionCommand();
		int index=box.getSelectedIndex();
		if (event.equals("Traduire")){
			if (legend[index].equals("Français/Anglais")){
				dico=new Dico("data/dicoEN.txt");
			}
			else if (legend[index].equals("Français/Espagnol")){
				dico=new Dico("data/dicoES.txt");
			}
			String texte=dico.find(text1.getText());
			if(texte!=null) {
				text2.setText(texte);
			}
			else {
				text2.setText("Mot inconnu dans le dico choisit !");
				
			}
		}
		else if (event.equals("Clear")){
			text1.setText("");
			text2.setText("");
		}
		else if (event.equals("Open")){
			fileChoice.showOpenDialog(this);
			String nom=fileChoice.getSelectedFile().getName();
			System.out.println("chemin="+nom);
			dico=new Dico("data/"+nom);
			dicoCharge.setVisible(true);
		}
		else if (event.equals("Ok")){
			dicoCharge.setVisible(false);
		}
		else if (event.equals("Save")){
			dico.add(text1.getText(),textNewTrad.getText()); 
			dico.save("./data/diconew.txt");
		}
	}
	private static void createAndShowGUI() {
		//Create and set up the window.
		DicoFrame frame = new DicoFrame("My Dico");
		frame.completeTop();
		frame.completeBottom();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	// use a new task for GUI creation
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}