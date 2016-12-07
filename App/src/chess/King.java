package chess;

public class King extends Figure {
    
    private Game game;
    private int playerSide;
    private boolean live;
    
    private volatile int positionX;
    private volatile int positionY;
    
    public King(Game game, int playerSide, int positionX, int positionY) {
        this.game = game;
        
        live = true;
        
        this.playerSide = playerSide;
        this.positionX = positionX;
        this.positionY = positionY;
    }
    
    public int getX() { return positionX; }
    public int getY() { return positionY; }
    public int getSide() { return playerSide; }
    
    
    @Override
    public void run() {
        int i = 0;
        while (live){
            if (game.getPlayer() == this.playerSide){
                synchronized(game){
                   
                    int previousX = this.getX();
                    int previousY = positionY;
                    
                    while (previousX == this.positionX && previousY == this.positionY){
                        
                        int toX = (int)(8 * Math.random());
                        int toY = (int)(8 * Math.random());

                        while(positionX == toX && positionY == toY) {
                            toX = (int)(8 * Math.random());
                            toY = (int)(8 * Math.random());
                        }

                        positionX += positionX < toX ? 1 : -1;
                        if (positionX < 0) positionX = 0;

                        positionY += positionY < toY ? 1 : -1;
                        if (positionY < 0) positionY = 0;
                        
                        System.out.println(previousX + " " + positionX);
                    }
                    
                    game.setPlayer(1 - this.playerSide);
           
                    game.printField(this.playerSide);
                 }
            }
        }
    } 
}
