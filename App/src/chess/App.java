package chess;

public class App {
    
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();

        // 1 - Capital letters
        // 0 - small

        game.createKing(0, 1, 1);
        
        game.createKing(1, 8, 8);
        
        game.start();
        


        
    }
}
