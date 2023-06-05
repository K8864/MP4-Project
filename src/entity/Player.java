package entity;

//import main.ClickDetection;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity {

    private final KeyHandler keyH;
    public boolean powered;
    public int score;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        solidArea = new Rectangle(4, 4, 8, 8);
        direction = "right";
        worldX = (int)(13.5*gp.tileSize);
        worldY = 23*gp.tileSize;
        speed = gp.scale;
        try {
            right = ImageIO.read(getClass().getResourceAsStream("/player/" + "Emma" + ".png"));
        }
        catch (Exception e){

        }
    }

    public void update() {
        if (keyH.upPressed) {
            directions.add("up");
        } else if (keyH.downPressed) {
            directions.add("down");
        } else if (keyH.leftPressed) {
            directions.add("left");
        } else if (keyH.rightPressed) {
            directions.add("right");
        }
        collisionOn = false;
        upCol = false;
        downCol = false;
        leftCol = false;
        rightCol = false;
        gp.cChecker.checkTile(this);
        if (directions.contains("up") && !upCol) {
            worldY -= speed;
            direction = "up";
            directions.clear();
        }
        else if (directions.contains("down") && !downCol) {
            worldY += speed;
            direction = "down";
            directions.clear();
        }
        else if (directions.contains("left") && !leftCol) {
            worldX -= speed;
            direction = "left";
            directions.clear();
        }
        else if (directions.contains("right") && !rightCol) {
            worldX += speed;
            direction = "right";
            directions.clear();
        }
        directions.clear();
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(right, worldX, worldY, 24 * gp.scale, 24 * gp.scale, null);
    }

}