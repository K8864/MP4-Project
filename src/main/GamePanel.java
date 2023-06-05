package main;

import ai.PathFinder;
import entity.*;
import entity.Player;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    //Screen Settings
    public final int originalTileSize = 16; // 16 x 16 tiles (default sizes for things)
    public final int scale = 1;
    public final int tileSize = originalTileSize * scale; // 32 x 32 tile size
    public final int maxScreenCol = 28;
    public final int maxScreenRow = 31;
    public final int screenWidth = tileSize * maxScreenCol; // 960 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    //World settings
    public final int maxWorldCol = 28;
    public final int maxWorldRow = 31;

    private final PathFinder pFinder = new PathFinder(this);
    public PathFinder getpFinder() {return pFinder;}

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int deadState = 2;
    public final int winState = 3;

    public BufferedImage title;
    public BufferedImage lose;
    public BufferedImage win;


    public final int FPS = 60;



    public final TileManager tileM = new TileManager(this);
    private final KeyHandler keyH = new KeyHandler(this);
    public final Player player = new Player(this, keyH);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public final Ghost1 arth = new Ghost1(this);
    public final Ghost2 nina = new Ghost2(this);
    public final Ghost3 kyle = new Ghost3(this);
    public final Ghost4 quincy = new Ghost4(this);
    public int tiles = 296;

    public Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        //this.addMouseListener(clickChecker);
        this.setFocusable(true);
        try {
            title = ImageIO.read(getClass().getResourceAsStream("/screens/" + "Title" + ".png"));
            lose = ImageIO.read(getClass().getResourceAsStream("/screens/" + "Lose" + ".png"));
            win = ImageIO.read(getClass().getResourceAsStream("/screens/" + "Win" + ".png"));
        }
        catch (Exception e){

        }
    }

    public void setUpGame() {
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        gameState = titleState;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0.0167 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if(gameState == playState) {
            player.update();
            arth.update();
            nina.update();
            kyle.update();
            quincy.update();
            if (tiles == 0) {
                System.out.println("you win!");
                gameState = winState;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(gameState == titleState) {
            g2.drawImage(title, 0, 0, screenWidth, screenHeight, null);
        }
        if(gameState == playState) {
            tileM.draw(g2);
            player.draw(g2);
            arth.draw(g2);
            nina.draw(g2);
            kyle.draw(g2);
            quincy.draw(g2);
        }
        if(gameState == deadState) {
            g2.drawImage(lose, 0, 0, screenWidth, screenHeight, null);
        }
        if(gameState == winState) {
            g2.drawImage(win, 0, 0, screenWidth, screenHeight, null);
        }
    }
}