import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GraphGUI extends JFrame{
	
	//MainCanvas Object
	protected MainCanvas RightSideCanvas;
	
	//Two JPanels, both for left side of GUI
	private JPanel leftSideOfGUI, weightSection;
	
	//All JRadio Buttons
	protected JRadioButton addVertex, addEdge, moveVertex, shortestPath, changeWeight; 
	
	//All Buttons
	private JButton addEdgesButton, randomWeightsButton, minimalSpanningTreeButton, helpButton;
	
	//TextField for Weight section
	protected JTextField weightInputText;
	
	//RadioButtonListener Object
	private RBListener radioButtonListener;
	
	//ButtonListener Object
	private BListener buttonListener;
	
	public GraphGUI() {
		
		//Sets basics for GUI
		setTitle("CS220 GRAPH PROJECT");
		setSize(1100,1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		
		//Initialize RadioButtonListener and ButtonListener for this class
		radioButtonListener = new RBListener(this);
		buttonListener = new BListener(this);
		
		/*
		 * Setting up main components of the GUI
		 */
		
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		
		//Left side of GUI
		leftSideOfGUI = new JPanel();
		leftSideOfGUI.setAlignmentX(LEFT_ALIGNMENT);
		leftSideOfGUI.setMaximumSize(new Dimension(400,1000));
		
		setUpAllButtons();
		
		//Adds left side (Buttons) to the GUI
		add(leftSideOfGUI);

		//Right side of GUI
		RightSideCanvas = new MainCanvas(this);
		RightSideCanvas.setAlignmentX(RIGHT_ALIGNMENT);
		
		//Add the right side (Canvas) to the GUI
		add(RightSideCanvas);
		
		setVisible(true);
		
	} //Constructor


	//This function sets up all the buttons for the left side of the GUI
	private void setUpAllButtons() {
		
		GridLayout leftSideGridLayout = new GridLayout(9,1);
		leftSideGridLayout.setVgap(20);
		
		leftSideOfGUI.setLayout(leftSideGridLayout);
		
		/*
		 * JRadioButtons
		 */
		
		addVertex = new JRadioButton("Add a vertex");
		addVertex.addActionListener(radioButtonListener);
		
		addEdge = new JRadioButton("Add an edge");
		addEdge.addActionListener(radioButtonListener);
		
		moveVertex = new JRadioButton("Move a vertex");
		moveVertex.addActionListener(radioButtonListener);
		
		shortestPath = new JRadioButton("Shortest Path");
		shortestPath.addActionListener(radioButtonListener);
		
		//Weight Sections consists of JRadioButton and JTextArea
		weightSection = new JPanel();
		weightSection.setLayout(new GridLayout(1,2));
		
		changeWeight = new JRadioButton("Change weight to :");
		changeWeight.addActionListener(radioButtonListener);
		
		weightInputText = new JTextField();
		weightInputText.setPreferredSize(new Dimension(20,20));
		
		//Adding JRadioButtons to left JPanel
		leftSideOfGUI.add(addVertex);
		leftSideOfGUI.add(addEdge);
		leftSideOfGUI.add(moveVertex);
		leftSideOfGUI.add(shortestPath);
		
		//Adding specific JPanel to the left side, consists of two components
		weightSection.add(changeWeight);
		weightSection.add(weightInputText);
		
		leftSideOfGUI.add(weightSection);
		
		
		/*
		 * Buttons
		 */
		
		addEdgesButton = new JButton("Add All Edges");
		addEdgesButton.setPreferredSize(new Dimension(50, 50));
		addEdgesButton.addActionListener(buttonListener);
		
		randomWeightsButton = new JButton("Random Weights");
		randomWeightsButton.setPreferredSize(new Dimension(50, 50));
		randomWeightsButton.addActionListener(buttonListener);
		
		minimalSpanningTreeButton = new JButton("Minimal Tree Spanning");
		minimalSpanningTreeButton.setPreferredSize(new Dimension(50, 50));
		minimalSpanningTreeButton.addActionListener(buttonListener);
		
		helpButton = new JButton("Help");
		helpButton.setPreferredSize(new Dimension(50, 50));
		helpButton.addActionListener(buttonListener);
		
		//Adding Buttons to left side of GUI
		leftSideOfGUI.add(addEdgesButton);
		leftSideOfGUI.add(randomWeightsButton);
		leftSideOfGUI.add(minimalSpanningTreeButton);
		leftSideOfGUI.add(helpButton);
		
	}
	

}