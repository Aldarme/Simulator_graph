package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PheromoneMatrix extends JFrame{
	
	private JPanel jpanel;
	private JTable jtable;
	private JScrollPane jtContainer;
	private PheroDynaDisplay pdd = new PheroDynaDisplay();

	public PheromoneMatrix() {
		//JPanel settings
		super("Pheromone adjacency matrix");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		
		jpanelSettings();
		
		jtableSettings();
		
	}
	
	public void update() {
		pdd.update();
		pack();
	}
	
	private void jpanelSettings() {
		jpanel = new JPanel();
		jpanel.setLayout(new BorderLayout());
		jtContainer = new JScrollPane(jtable);
		jpanel.add(jtContainer, BorderLayout.CENTER);
	}
	
	private void jtableSettings() {
			jtable = new JTable(pdd);
			getContentPane().add(new JScrollPane(jtable), BorderLayout.CENTER);
	}
	
}
