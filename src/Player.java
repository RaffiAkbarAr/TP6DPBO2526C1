
import java.awt.Rectangle;

public class Player {
    private double x, y, velocityY;
    private final int width = 52, height = 42;

    public Player(int x, int y) { this.x = x; this.y = y; }
    public void flap() { velocityY = -7.2; }
    public void update() { velocityY += 0.38; if (velocityY > 8) velocityY = 8; y += velocityY; }
    public void reset(int startY) { y = startY; velocityY = 0; }
    public int getX() { return (int)x; }
    public int getY() { return (int)y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Rectangle getBounds() { return new Rectangle(getX()+7, getY()+5, width-13, height-10); }
}
