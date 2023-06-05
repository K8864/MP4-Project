package main;

import entity.*;

import java.util.ArrayList;

public class CollisionChecker {
    public final GamePanel gp;
    int time = 0;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        int tileNum1, tileNum2;
        int gridX1, gridX2;
        int gridY1, gridY2;
        try {
            if (entity.directions.contains("up")) {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                gridX1 = entityLeftWorldX/gp.tileSize;
                gridX2 = entityRightWorldX/gp.tileSize;
                gridY1 = entityTopWorldY/gp.tileSize;
                if (gp.tileM.tile[tileNum1].isCollision() || gp.tileM.tile[tileNum2].isCollision()) {
                    entity.collisionOn = true;
                    entity.upCol = true;
                }
                if(gp.tileM.mapTileNum[gridX1][gridY1] == 26) {
                    gp.tiles--;
                    gp.tileM.mapTileNum[gridX1][gridY1] = 25;
                }
                if(gp.tileM.mapTileNum[gridX2][gridY1] == 26) {
                    gp.tiles--;
                    gp.tileM.mapTileNum[gridX2][gridY1] = 25;
                }
            }
            if (entity.directions.contains("down")) {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                gridX1 = entityLeftWorldX/gp.tileSize;
                gridX2 = entityRightWorldX/gp.tileSize;
                gridY1 = entityBottomWorldY/gp.tileSize;
                if (gp.tileM.tile[tileNum1].isCollision() || gp.tileM.tile[tileNum2].isCollision()) {
                    entity.collisionOn = true;
                    entity.downCol = true;
                }
                if(gp.tileM.mapTileNum[gridX1][gridY1] == 26) {
                    gp.tiles--;
                    gp.tileM.mapTileNum[gridX1][gridY1] = 25;
                }
                if(gp.tileM.mapTileNum[gridX2][gridY1] == 26) {
                    gp.tiles--;
                    gp.tileM.mapTileNum[gridX2][gridY1] = 25;
                }
            }
            if (entity.directions.contains("left")) {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                gridX1 = entityLeftWorldX/gp.tileSize;
                gridY1 = entityTopWorldY/gp.tileSize;
                gridY2 = entityBottomWorldY/gp.tileSize;
                if (gp.tileM.tile[tileNum1].isCollision() || gp.tileM.tile[tileNum2].isCollision()) {
                    entity.collisionOn = true;
                    entity.leftCol = true;
                }
                if(gp.tileM.mapTileNum[gridX1][gridY1] == 26) {
                    gp.tiles--;
                    gp.tileM.mapTileNum[gridX1][gridY1] = 25;
                }
                if(gp.tileM.mapTileNum[gridX1][gridY2] == 26) {
                    gp.tiles--;
                    gp.tileM.mapTileNum[gridX1][gridY2] = 25;
                }
            }
            if (entity.directions.contains("right")) {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                gridX1 = entityRightWorldX/gp.tileSize;
                gridY1 = entityTopWorldY/gp.tileSize;
                gridY2 = entityBottomWorldY/gp.tileSize;
                if (gp.tileM.tile[tileNum1].isCollision() || gp.tileM.tile[tileNum2].isCollision()) {
                    entity.collisionOn = true;
                    entity.rightCol = true;
                }
                if(gp.tileM.mapTileNum[gridX1][gridY1] == 26) {
                    gp.tiles--;
                    gp.tileM.mapTileNum[gridX1][gridY1] = 25;
                }
                if(gp.tileM.mapTileNum[gridX1][gridY2] == 26) {
                    gp.tiles--;
                    gp.tileM.mapTileNum[gridX1][gridY2] = 25;
                }
            }
            time++;
        }
        catch(Exception e) {
            if(time >= 30) {
                time = 0;
                if (entity.worldX < 0)
                    entity.worldX = gp.maxWorldCol * (gp.tileSize-1);
                else
                    entity.worldX = 0;
            }
            time++;
        }

    }

    public boolean checkUp(Entity e) {
        try {
            int X1 = (e.worldX + 2) / gp.tileSize;
            int X2 = (e.worldX + 14) / gp.tileSize;
            int Y = (e.worldY + 2) / gp.tileSize - 1;
            int tileNum1 = gp.tileM.mapTileNum[X1][Y];
            int tileNum2 = gp.tileM.mapTileNum[X2][Y];
            if (gp.tileM.tile[tileNum1].isCollision() || gp.tileM.tile[tileNum2].isCollision()) {
                //gp.tileM.mapTileNum[X1][Y] = 26;
                //gp.tileM.mapTileNum[X2][Y] = 26;
                return true;
            }
            //gp.tileM.mapTileNum[X1][Y] = 26;
            return false;
        }
        catch(Exception ex) {
            return false;
        }
    }

    public boolean checkDown(Entity e) {
        try {
            int X1 = (e.worldX + 2) / gp.tileSize;
            int X2 = (e.worldX + 14) / gp.tileSize;
            int Y = (e.worldY + 2) / gp.tileSize + 1;
            int tileNum1 = gp.tileM.mapTileNum[X1][Y];
            int tileNum2 = gp.tileM.mapTileNum[X2][Y];
            if (gp.tileM.tile[tileNum1].isCollision() || gp.tileM.tile[tileNum2].isCollision()) {
                //gp.tileM.mapTileNum[X1][Y] = 26;
                //gp.tileM.mapTileNum[X2][Y] = 26;
                return true;
            }
            //gp.tileM.mapTileNum[X1][Y] = 26;
            return false;
        }
        catch(Exception ex) {
            return false;
        }
    }

    public boolean checkLeft(Entity e) {
        try {
            int X = (e.worldX + 2) / gp.tileSize - 1;
            int Y1 = (e.worldY + 2) / gp.tileSize;
            int Y2 = (e.worldY + 14) / gp.tileSize;
            int tileNum1 = gp.tileM.mapTileNum[X][Y1];
            int tileNum2 = gp.tileM.mapTileNum[X][Y2];
            if (gp.tileM.tile[tileNum1].isCollision() || gp.tileM.tile[tileNum2].isCollision()) {
                //gp.tileM.mapTileNum[X][Y1] = 26;
                //gp.tileM.mapTileNum[X][Y2] = 26;
                return true;
            }
            //gp.tileM.mapTileNum[X][Y2] = 26;
            return false;
        }
        catch(Exception ex) {
            e.direction= "left";
            ((Ghost) e).cantSwitch = true;
            ((Ghost) e).switchTime = 0;
            e.worldX = (gp.maxWorldCol-1) * gp.tileSize-10;
            e.worldY = 14 * gp.tileSize;
            return false;
        }
    }
    public boolean checkRight(Entity e) {
        try {
            int X = (e.worldX + 2) / gp.tileSize + 1;
            int Y1 = (e.worldY + 2) / gp.tileSize;
            int Y2 = (e.worldY + 14) / gp.tileSize;
            int tileNum1 = gp.tileM.mapTileNum[X][Y1];
            int tileNum2 = gp.tileM.mapTileNum[X][Y2];
            if (gp.tileM.tile[tileNum1].isCollision() || gp.tileM.tile[tileNum2].isCollision()) {
                //gp.tileM.mapTileNum[X][Y1] = 26;
                //gp.tileM.mapTileNum[X][Y2] = 26;
                return true;
            }
            //gp.tileM.mapTileNum[X][Y2] = 26;
            return false;
        }
        catch(Exception ex) {
            e.direction = "right";
            ((Ghost) e).cantSwitch = true;
            ((Ghost) e).switchTime = 0;
            e.worldX = 0;
            e.worldY = 14 * gp.tileSize;
            return true;
        }
    }
}
