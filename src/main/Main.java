package main;

import javax.swing.JFrame;

public class Main
{

	public static void main(String[] args)
	{
		GamePanel gamePanel = new GamePanel();
		//Window Settings
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When you press the x button the window will close
		window.setResizable(false); //Resizes the window
		window.setTitle("Elite Dragons"); //Window name
		
		window.add(gamePanel);
		window.pack(); //Causes the window to be sized from gamePanel
		
		window.setLocationRelativeTo(null); //Display in the center of the screen
		window.setVisible(true); //Make the window visible
		
		gamePanel.startGameThread();
		
	}

}
