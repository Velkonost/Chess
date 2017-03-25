package chess.maven.interfaces;

import chess.maven.Figure;
import chess.maven.Game;

public interface KingInterface {
    void kill();
    Figure findEnemy(Figure prevFigure, int x, int y);
}
