package com.life;

import javax.swing.*;
import java.awt.*;

public class RectGrid extends JPanel {
    private int sizeGrid;
    private int sizeRect;
    private int alignment;
    private Rectangle[][] grid;

    public RectGrid() {
        sizeGrid = Main.getSize();
        sizeRect = 800 / sizeGrid;
        alignment = 50;
        setSize(800, 800);
        grid = new Rectangle[sizeGrid][sizeGrid];
        for (int x = 0; x < sizeGrid ; x++) {
            for (int y = 0; y < sizeGrid ; y++) {
                Rectangle rect = new Rectangle(x * sizeRect + alignment, y * sizeRect + alignment, sizeRect, sizeRect);
                this.grid[x][y] = rect;
            }
        }
    }
    public int getSizeGrid() {
        return sizeGrid;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        char[][] matrix = Main.getCommonMatrix();
        for (int i = 0; i < sizeGrid; i++) {
            for (int j = 0; j < sizeGrid; j++) {
                if (matrix[i][j] == ' ') {
                    g.setColor(new Color(245, 245, 245));
                    g.fillRect(grid[i][j].x, grid[i][j].y, grid[i][j].width, grid[i][j].height);
                    g.setColor(Color.BLACK);
                    g.drawRect(grid[i][j].x, grid[i][j].y, grid[i][j].width, grid[i][j].height);
                }
                else {
                    g.setColor(Color.BLACK);
                    g.fillRect(grid[i][j].x, grid[i][j].y, grid[i][j].width, grid[i][j].height);
                    g.setColor(Color.BLACK);
                    g.drawRect(grid[i][j].x, grid[i][j].y, grid[i][j].width, grid[i][j].height);
                }
            }
        }
    }

}