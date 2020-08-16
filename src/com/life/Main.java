package com.life;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static char[][] commonMatrix;
    private char[][] matrix;
    private static int generation = 0;

    public Main() {
        int N = commonMatrix.length;
        matrix = new char[N][N];
        generation++;
    }
    public static int getSize() {
        return commonMatrix.length;
    }
    public static char[][] getCommonMatrix() {
        return commonMatrix.clone();
    }
    public static void initMatrix(int N) {
        commonMatrix = new char[N][N];
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (random.nextBoolean()) {
                    commonMatrix[i][j] = 'O';
                } else {
                    commonMatrix[i][j] = ' ';
                }
            }
        }
    }
    public static int getAlive() {
        int alive = 0;
        for (char[] chars : commonMatrix) {
            for (int j = 0; j < commonMatrix.length; j++) {
                if (chars[j] == 'O') {
                    alive++;
                }
            }
        }
        return alive;
    }
    public static int getGeneration() {
        return generation;
    }

    public static void resetGeneration() {generation = 0;}

    public void setMatrix() {
        int N = commonMatrix.length;
        for (int i = 0; i < N; i++) {
            System.arraycopy(commonMatrix[i], 0, this.matrix[i], 0, N);
        }
    }

    public static void printNewGen() {
        System.out.println("Generation #" + Main.generation);
        System.out.println("Alive: " + Main.getAlive());
        for (char[] chars : commonMatrix) {
            for (int j = 0; j < commonMatrix.length; j++) {
                System.out.print(chars[j]);
            }
            System.out.println();
        }
    }
    public void nextGen() {
        int N = matrix.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int posX = i;
                int posY = j;
                int rowDown;
                int rowUp ;
                int colLeft;
                int colRight;
                if (posX - 1 == -1) {
                    rowUp = N - 1;
                } else {
                    rowUp = posX - 1;
                }
                if (posX + 1 == N) {
                    rowDown = 0;
                } else {
                    rowDown = posX + 1;
                }
                if (posY - 1 == -1) {
                    colLeft = N - 1;
                } else {
                    colLeft = posY - 1;
                }
                if (posY + 1 == N) {
                    colRight = 0;
                } else {
                    colRight = posY + 1;
                }
                int[] neighbor =  {
                        matrix[rowUp][colLeft],
                        matrix[rowUp][posY],
                        matrix[rowUp][colRight],
                        matrix[posX][colLeft],
                        matrix[posX][colRight],
                        matrix[rowDown][colLeft],
                        matrix[rowDown][posY],
                        matrix[rowDown][colRight]
                };

                int aliveCount = 0;
                for (int m = 0; m < 8 ; m++) {
                    if (neighbor[m] == 'O') {
                        aliveCount++;
                    }
                }
                if (aliveCount < 2 || aliveCount > 3) {
                    commonMatrix[i][j] = ' ';
                } else if (aliveCount == 3) {
                    commonMatrix[i][j] = 'O';
                } else {
                    //do nothing
                }
            }
        }
    }


    public static void clrscr(){
        try {
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }
            else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (IOException | InterruptedException e) {}
    }
    public static void startNewGame(GameOfLife game, int size) throws InterruptedException {
        Main.resetGeneration();
        //set init matrix to matrix world
        Main.initMatrix(size);
        while (true) {
            //create new obj to save the current evolution world
            Main gen = new Main();
            //set common matrix to this matrix (to save the current matrix)
            gen.setMatrix();
            //gen new evolution world
            gen.nextGen();
            //print new world
            game.createGui(Main.getGeneration(), Main.getAlive());
            //waiting 400ms
            while (true) {
                game.getAction();
                if (!game.getPlayToggle()) {
                    Thread.sleep(400);
                    break;
                }
            }
            //clear screen
            game.getContentPane().removeAll();
            if (game.getReset()) {
                game.setReset();
                break;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        GameOfLife game = new GameOfLife();
        do {
            startNewGame(game, N);
        } while (true);
    }
}