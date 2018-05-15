/*
 * Tristen Aguilar
 * CS 220
 * Graph Project
 */

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * For a UIManager, you must include a try and catch
		 */
		try {
			
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			
			System.out.println("There was an error with the UIManager, " + e);
			
		}

		//Calls Graph GUI
		GraphGUI newGUI = new GraphGUI();

	}

}