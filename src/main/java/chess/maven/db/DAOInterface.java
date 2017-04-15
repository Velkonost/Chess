package chess.maven.db;

import chess.maven.Figure;
import java.util.List;

public interface DAOInterface {
     void savePlayerOrder(int player);
     void saveStep(int step);
     void saveField(char[][] field);
     void saveFigures(int side, List<Figure> figures);

     List<Integer> getLastStep();
     int getStep();
     char[][] getField();
     List<Figure> getFigures(int side);
}
