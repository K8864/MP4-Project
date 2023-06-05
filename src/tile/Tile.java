package tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public BufferedImage getImage() {return image;}
    public void setImage(BufferedImage image) {this.image = image;}
    public boolean collision = false;
    public Boolean isCollision() {return collision;}
    public void setCollision(boolean collision) {this.collision = collision;}
    public boolean flat = false;
    public boolean isFlat() {return flat;}
    public void setFlat(boolean flat) {this.flat = flat;}
}
