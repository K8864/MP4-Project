package main;
import javax.swing.*;

import Useless_Stuff.Recursion_10_1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static JFrame window;
    public static JFrame getWindow() {
        return window;
    }

    public static void main(String[] args) {
        Recursion_10_1.array(new int[]{1, 2, 3, 4, 5});
        Recursion_10_1.array(new int[]{6, 7, 8, 9, 10});
        Recursion_10_1.arrayList(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)));
        Recursion_10_1.arrayList(new ArrayList<Integer>(Arrays.asList(6, 7, 8, 9, 10)));

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pac Man");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();

    }
}