package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {
    public GamePanel gp;
    public int worldX, worldY;
    public BufferedImage left, left2, down, down2, right, right2, up, up2;
    public String direction;
    public ArrayList<String> directions = new ArrayList<>();
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public boolean upCol, downCol, rightCol, leftCol;
    public int speed;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.getpFinder().setNode(startCol, startRow, goalCol, goalRow);

        if(gp.getpFinder().search()) {
            int nextX = gp.getpFinder().getPathList().get(0).getCol() * gp.tileSize;
            int nextY = gp.getpFinder().getPathList().get(0).getRow() * gp.tileSize;

            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX > nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX < nextX && enRightX > nextX + gp.tileSize) {
                direction = "down";
            }
            else if(enTopY > nextY && enBottomY < nextY + gp.tileSize) {
                if(enLeftX >= nextX) {
                    direction = "left";
                }
                if(enLeftX <= nextX) {
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                checkCollision();
                if(collisionOn) {
                    collisionOn = false;
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX) {
                direction = "up";
                checkCollision();
                if(collisionOn) {
                    collisionOn = false;
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                checkCollision();
                if(collisionOn) {
                    collisionOn = false;
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX) {
                direction = "down";
                checkCollision();
                if(collisionOn) {
                    collisionOn = false;
                    direction = "right";
                }
            }
            else if(enTopY < nextY)
                direction = "down";
            else if(enBottomY > nextY)
                direction = "up";
            else if(enLeftX < nextX)
                direction = "down";
            else if(enRightX > nextX)
                direction = "up";
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
//        switch(direction) {
//            case "up":
//                if(spriteNum == 1)
//                    image = up;
//                else
//                    image = up2;
//                break;
//            case "down":
//                if(spriteNum == 1)
//                    image = down;
//                else
//                    image = down2;
//                break;
//            case "left":
//                if(spriteNum == 1)
//                    image = left;
//                else
//                    image = left2;
//                break;
//            case "right":
//                if(spriteNum == 1)
//                    image = right;
//                else
//                    image = right2;
//                break;
//        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}