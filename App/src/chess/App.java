package chess;

import java.lang.reflect.InvocationTargetException;

public class App {
    
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, NoSuchFieldException,
            NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        
        Class classGame = Class.forName("chess.Game");
        Object gameObj = classGame.newInstance(); 
        Game game  = (Game) gameObj; 

        // 1 - Capital letters
        // 0 - small
        game.start();
    }
}
