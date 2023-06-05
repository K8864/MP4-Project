package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;

public class Ghost2 extends Ghost{

    String last = "";
    int targetX;
    int targetY;

    public Ghost2(GamePanel gp) {
        super(gp);
        targetX = worldX;
        targetY = worldY;
        try {
            right = ImageIO.read(getClass().getResourceAsStream("/ghosts/" + "Nina" + ".png"));
        }
        catch (Exception e){

        }
    }

    public void chase() {
        if(worldY == targetY && worldX == targetX) {
            int upY = 0;
            int downY = 0;
            int leftX = 0;
            int rightX = 0;
            int offset = 4 * gp.tileSize;
            int yStuff = 0;
            int xStuff = 0;
            double uDist = Integer.MAX_VALUE;
            double dDist = Integer.MAX_VALUE;
            double lDist = Integer.MAX_VALUE;
            double rDist = Integer.MAX_VALUE;
            if(gp.player.direction.equals("up")) {
                yStuff = offset;
                xStuff = 0;
            }
            else if(gp.player.direction.equals("down")) {
                yStuff = -1 * offset;
                xStuff = 0;
            }
            else if(gp.player.direction.equals("left")) {
                yStuff = 0;
                xStuff = -1 * offset;
            }
            else if(gp.player.direction.equals("right")) {
                yStuff = 0;
                xStuff = offset;
            }
            if (!gp.cChecker.checkUp(this) && !direction.equals("down") && !last.equals("down")) {
                upY = worldY + 4 - 20;
                int yDist = Math.abs(gp.player.worldY + yStuff - upY);
                int xDist = Math.abs(gp.player.worldX + xStuff - worldX);
                uDist = Math.sqrt(yDist * yDist + xDist * xDist);
            }
            if (!gp.cChecker.checkDown(this) && !direction.equals("up") && !last.equals("up")) {
                downY = worldY + 4 + 20;
                int yDist = Math.abs(gp.player.worldY + yStuff - downY);
                int xDist = Math.abs(gp.player.worldX + xStuff - worldX);
                dDist = Math.sqrt(yDist * yDist + xDist * xDist);
            }
            if (!gp.cChecker.checkLeft(this) && !direction.equals("right") && !last.equals("right")) {
                leftX = worldX + 4 - 20;
                int yDist = Math.abs(gp.player.worldY + yStuff - worldY);
                int xDist = Math.abs(gp.player.worldX + xStuff - leftX);
                lDist = Math.sqrt(yDist * yDist + xDist * xDist);
            }
            if (!gp.cChecker.checkRight(this) && !direction.equals("left") && !last.equals("left")) {
                rightX = worldX + 4 + 20;
                int yDist = Math.abs(gp.player.worldY + yStuff - worldY);
                int xDist = Math.abs(gp.player.worldX + xStuff - rightX);
                rDist = Math.sqrt(yDist * yDist + xDist * xDist);
            }
            double min = Math.min(uDist, Math.min(dDist, Math.min(lDist, rDist)));
            if (min == uDist) {
                direction = "up";
                assign();
            }
            if (min == dDist) {
                direction = "down";
            }
            if (min == lDist) {
                direction = "left";
            }
            if (min == rDist) {
                direction = "right";
            }
            assign();
        }
    }

    public void scatter() {
        if(worldY == targetY && worldX == targetX) {
            int upY = 0;
            int downY = 0;
            int leftX = 0;
            int rightX = 0;
            double uDist = Integer.MAX_VALUE;
            double dDist = Integer.MAX_VALUE;
            double lDist = Integer.MAX_VALUE;
            double rDist = Integer.MAX_VALUE;
            if (!gp.cChecker.checkUp(this) && !direction.equals("down") && !last.equals("down")) {
                upY = worldY + 4 - 20;
                int yDist = Math.abs(upY);
                int xDist = Math.abs(worldX);
                uDist = Math.sqrt(yDist * yDist + xDist * xDist);
            }
            if (!gp.cChecker.checkDown(this) && !direction.equals("up") && !last.equals("up")) {
                downY = worldY + 4 + 20;
                int yDist = Math.abs(downY);
                int xDist = Math.abs(worldX);
                dDist = Math.sqrt(yDist * yDist + xDist * xDist);
            }
            if (!gp.cChecker.checkLeft(this) && !direction.equals("right") && !last.equals("right")) {
                leftX = worldX + 4 - 20;
                int yDist = Math.abs(worldY);
                int xDist = Math.abs(leftX);
                lDist = Math.sqrt(yDist * yDist + xDist * xDist);
            }
            if (!gp.cChecker.checkRight(this) && !direction.equals("left") && !last.equals("left")) {
                rightX = worldX + 4 + 20;
                int yDist = Math.abs(worldY);
                int xDist = Math.abs(rightX);
                rDist = Math.sqrt(yDist * yDist + xDist * xDist);
            }
            double min = Math.min(uDist, Math.min(dDist, Math.min(lDist, rDist)));
            if (min == uDist) {
                direction = "up";
                assign();
            }
            if (min == dDist) {
                direction = "down";
            }
            if (min == lDist) {
                direction = "left";
            }
            if (min == rDist) {
                direction = "right";
            }
            assign();
        }
    }

    public void assign() {
        if(direction.equals("up")) {
            targetX = worldX;
            targetY = (worldY / gp.tileSize-1) * gp.tileSize;
        }
        else if(direction.equals("down")) {
            targetX = worldX;
            targetY = (worldY / gp.tileSize+1) * gp.tileSize;
        }
        else if(direction.equals("left")) {
            targetX = (worldX / gp.tileSize-1) * gp.tileSize;
            targetY = worldY;
        }
        else if(direction.equals("right")) {
            targetX = (worldX / gp.tileSize+1) * gp.tileSize;
            targetY = worldY;
        }
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(right, worldX, worldY, 24 * gp.scale, 24 * gp.scale, null);
    }
}
