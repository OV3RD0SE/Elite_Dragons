package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.GraphicAttribute;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
	//Screen settings
	final int originalTileSize = 16; //Define the game tile (16x16)
	final int scaleTileSize = 3; //Scales originalTileSize
	final int tileSize = originalTileSize * scaleTileSize; // 16*3 = 48x48
	
	//This is how many tiles will be displayed on the screen
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; //1200
	final int screenHeight = tileSize * maxScreenRow; //768
	
	//FPS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	//Set Player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 10; //Moves 4 pixels
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //Set the size of this class
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //The process of drawing graphics into an off-screen image buffer and then copying the contents of the buffer to the screen all at once.
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();// This will automatically call the run method
	}

	@Override
	public void run()
	{
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		//Game Loop
		while (gameThread != null)
		{
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta>=1) 
			{
				update();
				repaint(); //you call paintComponent with repaint (kinda confused)
				delta--;
			}
		}
		
	}
	
	public void update() 
	{
		if(keyH.upPressed == true) 
		{
			playerY -=playerSpeed;
		}
		if(keyH.downPressed == true) 
		{
			playerY +=playerSpeed;
		}
		if(keyH.leftPressed == true) 
		{
			playerX -=playerSpeed;
		}
		if(keyH.rightPressed == true) 
		{
			playerX +=playerSpeed;
		}
		
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); //You need to type this if you use paintComponent
		
		Graphics2D g2 = (Graphics2D)g; //Changed Graphics to Graphics2D, with Graphics2D you get more functions
		
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose(); //Its good to save some ram
	}
	
	
	
	
	
	
	
}
