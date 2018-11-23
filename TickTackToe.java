package ru.nesterenko.se;

import java.util.Random;
import java.util.Scanner;

/**
 * TickTackToe done
 */
public class TickTackToe {
    private static final char DOT_EMPTY = '.';
    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';
    private static char[][] map;
    private static int size;
    private static int block;
    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    public static void main(String[] args) {
        final TickTackToe game = new TickTackToe();
        go();
    }

    private static void go() {
        customizeGame();
        iniMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Human won");
                break;
            }
            if (isMapFull()) {
                System.out.println("It's draw");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_0)) {
                System.out.println("AI won");
                break;
            }
            if (isMapFull()) {
                System.out.println("It's draw");
                break;
            }
        }
        System.out.println("Game over");
    }

    private static void customizeGame() {
        do {
            System.out.println("\nInput map size [3-10]: ");
            size = sc.nextInt();
        } while (size < 3 || size > 10);
        do {
            System.out.println("How many times to repeat blocks to win? [3-" + size + "]");
            block = sc.nextInt();
        } while (block < 3 || block > size);
    }

    private static boolean checkWin(char symbol) {
        for (int col = 0; col < size - block + 1; col++) {
            for (int row = 0; row < size - block + 1; row++) {
                if (checkDiagonnale(symbol, col, row) || checkLines(symbol, col, row)) return true;
            }
        }
        return false;
    }
    private static boolean checkDiagonnale(char symbol, int offsetX, int offsetY) {
        boolean toRight = true;
        boolean toLeft = true;
        for (int i = 0; i < block; i++) {
            toRight &= (map[i + offsetX][i + offsetY] == symbol);
            toLeft &= (map[block - i - 1 + offsetX][1 + offsetY] == symbol);
        }
        return toRight || toLeft;
    }
    private static boolean checkLines(char symb, int offsetX, int offsetY) {
        for (int col = offsetX; col < block + offsetX; col++) {
            boolean cols = true;
            boolean rows = true;
            for (int row = offsetY; row < block + offsetY; row++) {
                cols &= (map[col][row] == symb);
                rows &= (map[row][col] == symb);
            }
            if (cols || rows) return true;
        }
        return  false;
    }
    private static boolean isMapFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
    private static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
    } while (!isCellValid(x, y));
    System.out.println("AI made turn" + (x + 1) + " " + (y + 1));
    map[y][x] = DOT_0;
    }
    private static void humanTurn() {
        int x, y;
        do {
           System.out.println("Input coordinate using X Y");
           x = sc.nextInt() - 1;
           y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }
    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) return false;
        return map[y][x] == DOT_EMPTY;
    }
    private static void iniMap() {
        map = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    private static void printMap() {
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
