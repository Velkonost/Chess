package chess;

public class App {
    
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();

        // 1 - Capital letters
        // 0 - small

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
