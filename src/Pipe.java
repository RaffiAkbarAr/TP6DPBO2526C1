
import java.awt.Rectangle;

public class Pipe {
    private int x;
    private final int gapY;
    private final int width;
    private final int gap;
    private boolean scored;

    public Pipe(int x, int gapY, int width, int gap) {
        this.x = x;
        this.gapY = gapY;
        this.width = width;
        this.gap = gap;
        this.scored = false;
    }

    public int getX() { return x; }
    public int getGapY() { return gapY; }
    public int getWidth() { return width; }
    public int getGap() { return gap; }
    public boolean isScored() { return scored; }
    public void setScored(boolean scored) { this.scored = scored; }
    public void move(int velocity) { x += velocity; }

    public Rectangle getTopBounds() { return new Rectangle(x, 0, width, gapY); }
    public Rectangle getBottomBounds(int groundY) { return new Rectangle(x, gapY + gap, width, groundY - (gapY + gap)); }
}
