package chess.maven.interfaces;

import chess.maven.Figure;
import chess.maven.figures.King;
import java.util.ArrayList;

public interface GameInterface {
    
    public void removeWhiteFigure(Figure figure);
    public void removeBlackFigure(Figure figure);
    
    public char getFigure(int x, int y);
    public int getPlayer();
    public ArrayList<King> getKings();
    public void setPlayer(int currentPlayer);
    
   public void printField(int playerSide);
    
}
