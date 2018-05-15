import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Help extends JFrame{
	
	public Help() {
		
		//Settings Up basic features of GUI
		setTitle("Help Information");
		setSize(1000,450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//Set the layout for the new GUI
		setLayout(new GridLayout(1,1));
		
		//Created a JTextPane to hold the text for the user to read
		JTextPane help = new JTextPane();
		
		help.setEditable(false);
		
		help.setText("Need Help? You're in the right place! \n\n"
				+ "Below you can find instructions to each part of the Program: \n\n"
				+ "'Add Vertex': Clicking on the Right side of the GUI will allow you to add Vertices. \n\n"
				+ "'Add Edge': Clicking on two different vertices will draw an edge from one of them to the other. \n\n"
				+ "'Move Vertex': Clicking on a Vertex, and then clicking anywhere else on the screen, will move the Vertex to that location. \n\n"
				+ "'Shortest Path': Clicking this option will find you the shortest path between any two vertices you click on. \n\n"
				+ "'Change Weight': Checking this option, you will be able to enter a weight, then click on any two vertices, and change the weight of the edge in between. \n\n"
				+ "'Add All Edges': Clicking this will form a complete Graph between all edges. \n\n"
				+ "'Random Weights': Clicking this will assign random weights between 1 and 10 to all edges on the Graph. \n\n"
				+ "'Minimal Spanning Tree': Clicking this will find the Minimal Spanning Tree for the Graph. \n\n"
				+ "***** REMEMBER *******\n"
				+ "Before clicking a new Radio button, UNCHECK the previous one clicked so you can use the function of the desired choice");
		
		add(help);
		
		setVisible(true);
	}
	
}