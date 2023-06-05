package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;

public class Ghost extends Entity{

    int clock = 0;
    boolean scatter = true;
    public boolean cantSwitch = false;
    public int switchTime = 0;

    public Ghost(GamePanel gp) {
        super(gp);
        speed = gp.scale;
        worldX = (int)(13.5 * gp.tileSize);
        worldY = 11 * gp.tileSize;
        solidArea = new Rectangle(4, 4, 8, 8);
        direction = "none";
        try {
            right = ImageIO.read(getClass().getResourceAsStream("/ghosts/" + "g1" + ".png"));
        }
        catch (Exception e){

        }
    }

    public void chase() {

    }

    public void scatter() {
        direction = "none";
    }

    public void assign() {

    }

    public void update() {
        if(!cantSwitch || switchTime > 10) {
            if (clock < 60 * 20)
                chase();
            else if (clock > 60 * 27) {
                clock = 0;
            } else {
                scatter();
            }
        }
        if(direction.equals("up")) {
            worldY -= speed;
        }
        if(direction.equals("down")) {
            worldY += speed;
        }
        if(direction.equals("left")) {
            worldX -= speed;
        }
        if(direction.equals("right")) {
            worldX += speed;
        }
        clock++;
        switchTime++;
        if(Math.abs(worldX-gp.player.worldX) <= gp.tileSize/2 && Math.abs(worldY-gp.player.worldY) <= gp.tileSize/2) {
            gp.gameState = gp.deadState;
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(right, worldX, worldY, null);
    }
}
