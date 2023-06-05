package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[27];
        getTileImage();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/map/map.txt");
    }

    public void getTileImage(){
        setUp(1, "01", true);
        setUp(2, "02", true);
        setUp(3, "03", true);
        setUp(4, "04", true);
        setUp(5, "05", true);
        setUp(6, "06", true);
        setUp(7, "07", true);
        setUp(8, "08", true);
        setUp(9, "09", true);
        setUp(10, "10", true);
        setUp(11, "11", true);
        setUp(12, "12", true);
        setUp(13, "13", true);
        setUp(14, "14", true);
        setUp(15, "15", true);
        setUp(16, "16", true);
        setUp(17, "17", true);
        setUp(18, "18", true);
        setUp(19, "19", true);
        setUp(20, "20", true);
        setUp(21, "21", true);
        setUp(22, "22", true);
        setUp(23, "23", true);
        setUp(24, "24", true);
        setUp(25, "25", false);
        setUp(26, "26", false);
        setUp(0, "25", false);
    }

    public void setUp(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png")));
            tile[index].setImage(uTool.scaleImage(tile[index].getImage(), gp.tileSize, gp.tileSize));
            tile[index].setCollision(collision);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while(row < gp.maxWorldRow) {
                String line = br.readLine();
                while(col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                col = 0;
                row++;
            }
            br.close();
        } catch(Exception e) {

        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            g2.drawImage(tile[mapTileNum[worldCol][worldRow]].getImage(), worldX, worldY, null);
            worldCol++;
            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}