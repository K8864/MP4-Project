package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {
    private boolean showDrawTime = false;
    public GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    //useless
    @Override
    public void keyTyped(KeyEvent e) {}
    //useless
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            } else if (code == KeyEvent.VK_A) {
                leftPressed = true;
            } else if (code == KeyEvent.VK_S) {
                downPressed = true;
            } else if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
        }
        if(gp.gameState == gp.titleState) {
            if(code == KeyEvent.VK_SPACE) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = false;
            } else if (code == KeyEvent.VK_A) {
                leftPressed = false;
            } else if (code == KeyEvent.VK_S) {
                downPressed = false;
            } else if (code == KeyEvent.VK_D) {
                rightPressed = false;
            }
        }
    }
}
