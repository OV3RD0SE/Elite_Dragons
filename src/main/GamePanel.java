package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.GraphicAttribute;

import javax.swing.JPanel;

import entity.Player;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable
{
	//Screen settings
	final int originalTileSize = 16; //Define the game tile (16x16)
	final int scaleTileSize = 3; //Scales originalTileSize
	public final int tileSize = originalTileSize * scaleTileSize; // 16*3 = 48x48
	
	//This is how many tiles will be displayed on the screen
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //1200
	public final int screenHeight = tileSize * maxScreenRow; //768
	
	//FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this,keyH);
	
	
	//Set Player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4; //Moves 4 pixels
	
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
		
		//This is so the game runs in 60 FPS
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
		player.update();
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); //You need to type this if you use paintComponent
		
		Graphics2D g2 = (Graphics2D)g; //Changed Graphics to Graphics2D, with Graphics2D you get more functions
		
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose(); //Its good to save some ram
	}
	
	
	
	
	
	
	
}
