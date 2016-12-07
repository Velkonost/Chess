package chess;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {
    private volatile int currentPlayer;

    public static final char WHITE_KING = 'K';
    public static final char BLACK_KING = 'k';
    
    private int step;
    
    private volatile char[][] gameField;
    private ArrayList<King> kings;
    
    public Game() {
        currentPlayer = 0;
        step = 0;
        
        kings = new ArrayList<>();
        
        gameField = new char[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                gameField[i][j] = 0;
    }
    
    public void createKing(int side, int positionX, int positionY) {
        positionX--; positionY--;
        
        gameField[positionY][positionX] = 1;
        
        King newKing = new King(this, side, positionX, positionY);
        kings.add(newKing);
        
        
    }
    
    public int getPlayer() { return currentPlayer; }
    public char getFigure(int x, int y){ return gameField[x][y]; }
    public void setPlayer(int currentPlayer) { this.currentPlayer = currentPlayer; }
    
    public void updateField() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                gameField[i][j] = 0;
        
        for (int i = 0; i < kings.size(); i++) {
            if (kings.get(i).isLive()) {
                if (kings.get(i).getSide() == 1)
                    gameField[kings.get(i).getY()][kings.get(i).getX()] = WHITE_KING;
                else gameField[kings.get(i).getY()][kings.get(i).getX()] = BLACK_KING;
            }
        }
    }
    
    public void printField(int playerSide) {
        updateField();
        step++;
        System.out.println("Ход: " + step + " Игрок: " + (playerSide + 1));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                System.out.print(gameField[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
    
    public synchronized void start() {
         ExecutorService threadPool = Executors.newCachedThreadPool();
         
         for (int i = 0; i < kings.size(); i++){
             threadPool.execute(kings.get(i));
         }
         threadPool.shutdown();
    }
}
