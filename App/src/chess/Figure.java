package chess;

class Figure implements Runnable {
    private int positionX;
    private int positionY;
    
    public Figure(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

   
    public int getX() { return positionX; }
    public int getY() { return positionY; }

    @Override
    public void run() {

    }
}
