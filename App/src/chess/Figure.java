package chess;

class Figure implements Runnable {
    public Figure(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    private int positionX;
    private int positionY;
   public int getX() { return positionX; }
    public int getY() { return positionY; }

    @Override
    public void run() {

    }
}
