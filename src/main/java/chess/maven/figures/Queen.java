package chess.maven.figures;

import chess.maven.Figure;
import chess.maven.Game;
import static chess.maven.Constants.BLACK_KING;
import static chess.maven.Constants.WHITE_KING;

public class Queen extends Figure {

    private Game game;
    private int playerSide;
    private boolean live;

    private volatile int positionX;
    private volatile int positionY;

    public Queen(Game game, int playerSide, int positionX, int positionY) {
        super(positionX, positionY);
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
    public void kill() { this.live = false; }



    public void checkEnemies() {
        Figure enemyFigure = null;
        
        synchronized (game) {
            for (int i = (this.positionX + 1); i < 7; i++) {
                enemyFigure = findEnemy(enemyFigure, i, this.positionY);
            }
            
//            if ()
        }
    }
    
    private Figure findEnemy(Figure prevFigure, int x, int y){
        char kingName = this.getSide() == 1 ? WHITE_KING : BLACK_KING;
        if (!(game.getFigure(x, y) == ' ') || !(game.getFigure(x, y) == kingName))
            return new Figure(x, y);
        return prevFigure;
    }

    private boolean checkCell(boolean prevResult, int x, int y) {
        return game.getFigure(x, y) == ' ' || prevResult;
    }


    @Override
    public void run() {
        while (live){
            if (game.getPlayer() == this.playerSide){
                synchronized(game){

                    int previousX = this.getX();
                    int previousY = this.getY();

                    boolean ableToGo = false;

                    while (previousX == this.positionX && previousY == this.positionY && !ableToGo) {

                        int toX = (int) (8 * Math.random());
                        int toY = (int) (8 * Math.random());

                        while (positionX == toX && positionY == toY) {
                            toX = (int) (8 * Math.random());
                            toY = (int) (8 * Math.random());
                        }



                        if (positionX == toX) {
                            int min = positionY < toY ? positionY : toY;
                            int max = positionY > toY ? positionY : toY;

                            ++min;

                            ableToGo = false;
                            for (int i = min; i < max; i++)
                                ableToGo = checkCell(ableToGo, positionX, i);

                            if (ableToGo) {
                                positionY = toY;

                                break;
                            }

                        } else if (positionY == toY) {
                            int min = positionX < toX ? positionX : toX;
                            int max = positionX > toX ? positionX : toX;

                            ++min;

                            ableToGo = false;
                            for (int i = min; i < max; i++)
                                ableToGo = checkCell(ableToGo, i, positionY);

                            if (ableToGo) {
                                positionX = toX;

                                break;
                            }

                        } else {

                            ableToGo = false;

                            int distance = Math.abs(toX - positionX);

                            if (positionX < toX) {

                                int startX = positionX;
                                int startY = positionY;

                                if (startY < toY)
                                    for (int i = 1; i <= distance; i++)
                                        ableToGo = checkCell(ableToGo, startX + i, startY + i);
                                else
                                    for (int i = 1; i <= distance; i++)
                                        ableToGo = checkCell(ableToGo, startX + i, startY - i);
                            } else {

                                if (toY < positionX)
                                    for (int i = 1; i <= distance; i++)
                                        ableToGo = checkCell(ableToGo, toX + i, toY + i);
                                else
                                    for (int i = 1; i <= distance; i++)
                                        ableToGo = checkCell(ableToGo, toX + i, toY - i);

                            }
                        }
                    }

                    game.setPlayer(1 - this.playerSide);

                    game.printField(this.playerSide);
                }
            }
        }
    }
}
