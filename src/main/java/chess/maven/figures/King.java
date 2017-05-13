package chess.maven.figures;

import chess.maven.Figure;
import chess.maven.Game;
import static chess.maven.Constants.BLACK_KING;
import static chess.maven.Constants.WHITE_KING;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static chess.maven.Game.*;
import chess.maven.interfaces.GameInterface;
import chess.maven.interfaces.KingInterface;

public class King extends Figure implements KingInterface {
    
    public King(){
        super();
        live = true;
    }
    
    public King(GameInterface game, int playerSide, int positionX, int positionY) {
        super(positionX, positionY);
        this.game = game;
        
        live = true;
        
        this.playerSide = playerSide;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    


    @Override
    public void kill() {
        this.live = false;

        if (this.getSide() == 1) game.removeWhiteFigure(this);
        else game.removeBlackFigure(this);
    }

    private boolean inAngle() {
        return (positionX == 0 && positionY == 0)
                || (positionX == 7 && positionY == 7)
                || (positionX == 0 && positionY == 7)
                || (positionX == 7 && positionY == 0);
    }

    private boolean inWall() {
        return positionX == 0 || positionX == 7
                || positionY == 0 || positionY == 7;
    }
    
    @Override
    public Figure findEnemy(Figure prevFigure, int x, int y){
        char kingName = this.getSide() == 1 ? WHITE_KING : BLACK_KING;
        
        if ((game.getFigure(x, y) != '0') && (game.getFigure(x, y) != kingName))
            return new Figure(x, y);
        return prevFigure;
    }

    private Figure checkEnemies() {
        Figure enemyFigure = null;

        // sort by important in the future
//        synchronized (game) {

            if (inAngle()) {
                if (positionX == 7) {
                    if (positionY == 7) {
                        enemyFigure = findEnemy(enemyFigure, positionX - 1, positionY - 1);
                        enemyFigure = findEnemy(enemyFigure, positionX, positionY - 1);
                        enemyFigure = findEnemy(enemyFigure, positionX - 1, positionY);

                    } else {
                        enemyFigure = findEnemy(enemyFigure, positionX - 1, positionY + 1);
                        enemyFigure = findEnemy(enemyFigure, positionX, positionY + 1);
                        enemyFigure = findEnemy(enemyFigure, positionX - 1, positionY);
                    }
                } else {
                    if (positionY == 7) {
                        enemyFigure = findEnemy(enemyFigure, positionX + 1, positionY - 1);
                        enemyFigure = findEnemy(enemyFigure, positionX, positionY - 1);
                        enemyFigure = findEnemy(enemyFigure, positionX + 1, positionY);
                    } else {
                        enemyFigure = findEnemy(enemyFigure, positionX + 1, positionY + 1);
                        enemyFigure = findEnemy(enemyFigure, positionX, positionY + 1);
                        enemyFigure = findEnemy(enemyFigure, positionX + 1, positionY);
                    }
                }
            } else if (inWall()) {
                if (positionX == 0) {
                    enemyFigure = findEnemy(enemyFigure, positionX, positionY - 1);
                    enemyFigure = findEnemy(enemyFigure, positionX, positionY + 1);
                    enemyFigure = findEnemy(enemyFigure, positionX + 1, positionY);
                    enemyFigure = findEnemy(enemyFigure, positionX + 1, positionY + 1);
                    enemyFigure = findEnemy(enemyFigure, positionX + 1, positionY - 1);
                } else if (positionX == 7) {
                    enemyFigure = findEnemy(enemyFigure, positionX, positionY - 1);
                    enemyFigure = findEnemy(enemyFigure, positionX, positionY + 1);
                    enemyFigure = findEnemy(enemyFigure, positionX - 1, positionY);
                    enemyFigure = findEnemy(enemyFigure, positionX - 1, positionY + 1);
                    enemyFigure = findEnemy(enemyFigure, positionX - 1, positionY - 1);
                } else if (positionY == 0) {
                    enemyFigure = findEnemy(enemyFigure, positionX - 1, positionY);
                    enemyFigure = findEnemy(enemyFigure, positionX + 1, positionY);
                    enemyFigure = findEnemy(enemyFigure, positionX, positionY + 1);
                    enemyFigure = findEnemy(enemyFigure, positionX + 1, positionY + 1);
                    enemyFigure = findEnemy(enemyFigure, positionX - 1, positionY + 1);
                } else if (positionY == 7) {
                    enemyFigure = findEnemy(enemyFigure, positionX - 1, positionY);
                    enemyFigure = findEnemy(enemyFigure, positionX + 1, positionY);
                    enemyFigure = findEnemy(enemyFigure, positionX, positionY - 1);
                    enemyFigure = findEnemy(enemyFigure, positionX + 1, positionY - 1);
                    enemyFigure = findEnemy(enemyFigure, positionX - 1, positionY - 1);
                }
            } else {
                int[] byX = {-1, -1, -1, 0, 1, 1, 1, 0};
                int[] byY = {1, 0, -1, -1, -1, 0, 1, 1};

                for (int i = 0; i < 8; i++)
                    enemyFigure = findEnemy(enemyFigure, positionX + byX[i], positionY + byY[i]);
            }
//        }
        return enemyFigure;
    }
    
    @Override
    public void run() {
        while (live){
            if (this.playerSide == 1)
                try {
                    semaphoreWhite.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(King.class.getName()).log(Level.SEVERE, null, ex);
            }
            else try {
                semaphoreBlack.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(King.class.getName()).log(Level.SEVERE, null, ex);
            }
            try{
                
                if (game.getPlayer() == this.playerSide){

                    Figure enemyFigure = checkEnemies();

                    if (enemyFigure != null && enemyFigure.getX() != this.getX()
                            && enemyFigure.getY() != this.getY()) {
                        positionX = enemyFigure.getX();
                        positionY = enemyFigure.getY();

                        char whoKilled = game.getFigure(positionX, positionY); // need in the future
                        
                        ArrayList<King> kings = game.getKings();
                        
                        for (int i = 0; i < kings.size(); i++)
                            if (kings.get(i).getX() == positionX && kings.get(i).getY() == positionY
                                    && kings.get(i).getSide() != this.getSide()) {
                                kings.get(i).kill();
                                kings.remove(kings.get(i));
                                break;
                            }

                        // task - find killed figure and stop drawing it;
                    } else {
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
            } finally {
                if (this.playerSide == 1)
                    semaphoreBlack.release();
                else semaphoreWhite.release();
            }
        }
        
        return;
    } 
}
