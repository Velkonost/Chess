package chess.maven;

import chess.maven.interfaces.GameInterface;

public class Figure implements Runnable {
    
    public GameInterface game;
    
    public int playerSide;
    public boolean live;
    
    public volatile int positionX;
    public volatile int positionY;
    
    public Figure(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Figure(){}
    
    public int getX() { return positionX; }
    public int getY() { return positionY; }
    public boolean isLive() { return live; }
    public int getSide() { return playerSide; }
    
    public void setX(int positionX) { this.positionX = positionX; }
    public void setY(int positionY) { this.positionY = positionY; }
    public void setSide (int playerSide) {this.playerSide = playerSide; }
    
    
    
    public void setGame (Game game) { this.game = game; }

    @Override
    public void run() {

    }
}
