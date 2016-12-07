package chess;

import static chess.Game.BLACK_KING;
import static chess.Game.WHITE_KING;

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
    public boolean isLive() { return live; }
    public int getSide() { return playerSide; }

    private boolean inAngle() {
        if (positionX == 0 && positionY == 0
                || positionX == 7 && positionY == 7
                || positionX == 0 && positionY == 7
                || positionX == 7 && positionY == 0)
            return  true;
        return false;
    }

//    private

    private boolean inWall() {
        if(positionX == 0 || positionX == 7
                || positionY == 0 || positionY == 7)
            return true;
        return false;
    }
    private void findEnemy(int x, int y){
        char kingName = this.getSide() == 1 ? WHITE_KING : BLACK_KING;
        if (game.getFigure(x, y) == ' ' || game.getFigure(x, y) == kingName)
            System.out.print("");
        else live = false;
        System.out.print(game.getFigure(x, y));

//        return true;
    }

    private void checkEnemies() {
        // sort by important in the future
        synchronized (game) {
            if (inAngle()) {
                if (positionX == 7) {
                    if (positionY == 7) {
                        findEnemy(positionX - 1, positionY - 1);
                        findEnemy(positionX, positionY - 1);
                        findEnemy(positionX - 1, positionY);

                    } else {
                        findEnemy(positionX - 1, positionY + 1);
                        findEnemy(positionX, positionY + 1);
                        findEnemy(positionX - 1, positionY);
                    }
                } else {
                    if (positionY == 7) {
                        findEnemy(positionX + 1, positionY + 1);
                        findEnemy(positionX, positionY - 1);
                        findEnemy(positionX + 1, positionY);
                    } else {
                        findEnemy(positionX + 1, positionY + 1);
                        findEnemy(positionX, positionY + 1);
                        findEnemy(positionX + 1, positionY);
                    }
                }
            } else if (inWall()) {
                if (positionX == 0) {
                    findEnemy(positionX, positionY - 1);
                    findEnemy(positionX, positionY + 1);
                    findEnemy(positionX + 1, positionY);
                    findEnemy(positionX + 1, positionY + 1);
                    findEnemy(positionX + 1, positionY - 1);
                } else if (positionX == 7) {
                    findEnemy(positionX, positionY - 1);
                    findEnemy(positionX, positionY + 1);
                    findEnemy(positionX - 1, positionY);
                    findEnemy(positionX - 1, positionY + 1);
                    findEnemy(positionX - 1, positionY - 1);
                } else if (positionY == 0) {
                    findEnemy(positionX - 1, positionY);
                    findEnemy(positionX + 1, positionY);
                    findEnemy(positionX, positionY + 1);
                    findEnemy(positionX + 1, positionY + 1);
                    findEnemy(positionX - 1, positionY + 1);
                } else if (positionY == 7) {
                    findEnemy(positionX - 1, positionY);
                    findEnemy(positionX + 1, positionY);
                    findEnemy(positionX, positionY - 1);
                    findEnemy(positionX + 1, positionY - 1);
                    findEnemy(positionX - 1, positionY - 1);
                }
            } else {
                int[] byX = {-1, -1, -1, 0, 1, 1, 1, 0};
                int[] byY = {1, 0, -1, -1, -1, 0, 1, 1};

                for (int i = 0; i < 8; i++) {
                    findEnemy(positionX + byX[i], positionY + byY[i]);
                }

            }
        }
    }
    
    
    @Override
    public void run() {
        int i = 0;
        while (live){
            if (game.getPlayer() == this.playerSide){
                synchronized(game){

                    checkEnemies();

                    int previousX = this.getX();
                    int previousY = this.getY();
                    
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

                    }
                    
                    game.setPlayer(1 - this.playerSide);
           
                    game.printField(this.playerSide);
                 }
            }
        }
    } 
}
