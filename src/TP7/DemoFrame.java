package TP7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class DemoFrame  extends JFrame implements ActionListener {
	private JPanel top, bottom;
	private JComboBox box;
	private final String[] legend = { "UpperCase", "LowerCase", "FirstCharOnly"};
	private JButton ok;
	private JTextField text1;

	public DemoFrame(String arg0) {
		super(arg0);
		setResizable(false);
		Container pane=getContentPane();
		pane.setLayout(new GridLayout(2,0));
		top=new JPanel();
		top.setBackground(new Color(0xFF9933));
		bottom=new JPanel();
		bottom.setBackground(new Color(0xFFB266));
		pane.add(top);
		pane.add(bottom);
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 400);
	}

	public void completeTop(){
		top.setLayout(new GridLayout(0,2));
		JPanel first=new JPanel();
		first.setBackground(new Color(0xFF9933));
		first.setLayout(new FlowLayout(FlowLayout.LEADING,40,20));
		first.add(new JLabel("write something here:"));
		text1=new JTextField("to be completed");
		text1.setColumns(20);
		first.add(text1);
		JPanel second=new JPanel();
		second.setBackground(new Color(0x6666FF));
		top.add(first);
		top.add(second);
	}
	public void completeBottom(){
		bottom.setLayout(new FlowLayout(FlowLayout.LEADING,80,40));
		box = new JComboBox(legend);
		bottom.add(box);
		ok=new JButton("OK");
		bottom.add(ok);
		ok.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		String event=evt.getActionCommand();
		int index=box.getSelectedIndex();
		if (event.equals("OK")){
			String input=text1.getText();
			if (legend[index].equals("UpperCase")){
				text1.setText(input.toUpperCase());

			}
			if (legend[index].equals("LowerCase")){
				text1.setText(input.toLowerCase());

			}
			if (legend[index].equals("FirstCharOnly")){
				text1.setText(input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase());

			}
		}
	}
	private static void createAndShowGUI() {
		//Create and set up the window.
		DemoFrame frame = new DemoFrame("My Demo");
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