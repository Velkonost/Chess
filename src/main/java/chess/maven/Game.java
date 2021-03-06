package chess.maven;

import chess.maven.figures.King;
import static chess.maven.Constants.BLACK_KING;
import static chess.maven.Constants.KING;
import static chess.maven.Constants.WHITE_KING;
import static chess.maven.Constants.WHITE_SIDE;
import chess.maven.interfaces.GameInterface;
import chess.maven.db.DAO;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class Game implements GameInterface {
    private volatile int currentPlayer;

    public static Semaphore semaphoreWhite;
    public static Semaphore semaphoreBlack;
    
    private int step;
    
    private volatile char[][] gameField;
    private ArrayList<King> kings;
    
    private ArrayList<Figure> whiteFigures;
    private ArrayList<Figure> blackFigures;
    
    private DAO dao;
   
    public Game() {
        currentPlayer = 1;
        step = 0;
        
        kings = new ArrayList<>();
        
        whiteFigures = new ArrayList<>();
        blackFigures = new ArrayList<>();
        
        gameField = new char[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                gameField[i][j] = '0';
    }

     public void saveLastStep() {
        dao.saveStep(step);
    }

    public int getLastStep() {
        return dao.getLastStep().get(0);
    }
     
    @Autowired
    public void getDAO(DAO dao) {
        this.dao = dao;
    }
    
    @Override public ArrayList<King> getKings() { return kings; }
    @Override public int getPlayer() { return currentPlayer; }
    @Override public char getFigure(int x, int y){ return gameField[x][y]; }
    @Override public void setPlayer(int currentPlayer) { this.currentPlayer = currentPlayer; }

    @Override public void removeWhiteFigure(Figure figure){ this.whiteFigures.remove(figure); }
    @Override public void removeBlackFigure(Figure figure){ this.blackFigures.remove(figure); }

    public void updateField() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                gameField[i][j] = '0';
        
        for (int i = 0; i < kings.size(); i++) 
            if (kings.get(i).isLive()) 
                if (kings.get(i).getSide() == 1)
                    gameField[kings.get(i).getY()][kings.get(i).getX()] = WHITE_KING;
                else gameField[kings.get(i).getY()][kings.get(i).getX()] = BLACK_KING;
    }
    
    public char[][] getField() { return gameField; }
    
    @Override
    public void printField(int playerSide) {
        
        if (whiteFigures.size() != 0 && blackFigures.size() != 0) {
            updateField();
            step ++;

            System.out.println("Ход: " + step + " Игрок: " + (playerSide == WHITE_SIDE ? 1 : 2));

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++)
                    System.out.print(gameField[i][j] + " ");
                System.out.println();
            }
            System.out.println();
        } else if(blackFigures.size() == 0) {
            System.out.println(11111);
        } else if (whiteFigures.size() == 0) {
            System.out.println(2222);
//            for (int i = 0;)
        }
    }
    
    @Autowired
    public void getFigures(List<Figure> figures) {
        for (Figure figure : figures) {
            if (String.valueOf(figure.getClass())
                    .equals("class chess.maven.figures.King"))
                kings.add((King)figure);
            
            if (figure.getSide() == WHITE_SIDE) whiteFigures.add(figure);
            else blackFigures.add(figure);
        } 
    }
    
    public void start() {
 
        ExecutorService threadPool = Executors.newCachedThreadPool();
         
        semaphoreWhite = new Semaphore(1);
        semaphoreBlack = new Semaphore(0);
         
         for (int i = 0; i < kings.size(); i++)
             threadPool.execute(kings.get(i));
         threadPool.shutdown();
    }
}
