package chess.maven;

public class Figure implements Runnable {
    
    public Game game;
    
    private int positionX;
    private int positionY;
    
    public Figure(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Figure(){}
    
    public int getX() { return positionX; }
    public int getY() { return positionY; }
    
    public void setGame (Game game) { this.game = game; }

    @Override
    public void run() {

    }
}
