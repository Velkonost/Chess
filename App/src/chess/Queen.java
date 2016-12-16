package chess;

import java.util.ArrayList;

import static chess.Game.BLACK_KING;
import static chess.Game.WHITE_KING;

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



    private Figure findEnemy(Figure prevFigure, int x, int y){
        char kingName = this.getSide() == 1 ? WHITE_KING : BLACK_KING;
        if (!(game.getFigure(x, y) == ' ') || !(game.getFigure(x, y) == kingName))
            return new Figure(x, y);
        return prevFigure;
    }


    @Override
    public void run() {
        while (live){
            if (game.getPlayer() == this.playerSide){
                synchronized(game){

                    int previousX = this.getX();
                    int previousY = this.getY();

                    while (previousX == this.positionX && previousY == this.positionY) {

                        int toX = (int) (8 * Math.random());
                        int toY = (int) (8 * Math.random());

                        while (positionX == toX && positionY == toY) {
                            toX = (int) (8 * Math.random());
                            toY = (int) (8 * Math.random());
                        }

                        positionX += positionX < toX ? 1 : -1;
                        if (positionX < 0) positionX = 0;

                        positionY += positionY < toY ? 1 : -1;
                        if (positionY < 0) positionY = 0;

                    }
                }

                    game.setPlayer(1 - this.playerSide);

                    game.printField(this.playerSide);
                }
            }
        }
    }
}
