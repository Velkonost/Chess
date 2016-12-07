package chess;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        
        game.createKing(0, 1, 1);
        
        game.createKing(1, 8, 8);
        
//        game.printField();
        
        game.start();
        
//        ExecutorService threadPool = Executors.newCachedThreadPool();
//        
//        threadPool.execute(new Play(game));
//   
//        threadPool.shutdown();
//        threadPool.awaitTermination(1, TimeUnit.MINUTES);


        
    }
}
