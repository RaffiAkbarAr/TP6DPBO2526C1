
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
    public static final int W = 900, H = 600;
    private final CardLayout cards;
    private final JPanel root;
    private final AudioManager audio = new AudioManager();
    private final Random random = new Random();
    private final ArrayList<Pipe> pipes = new ArrayList<>();
    private final Timer timer;
    private final JLabel scoreLabel = new JLabel("SKOR: 0");
    private BufferedImage birdImage;
    private Player player = new Player(180, 260);
    private int score = 0;
    private int bestScore = 0;
    private final Path bestScoreFile = Path.of("data", "best_score.txt");
    private int groundY = 550;
    private boolean started = false, gameOver = false, menu = true;
    private int bgOffset = 0;

    public GamePanel(CardLayout cards, JPanel root) {
        this.cards = cards; this.root = root;
        setPreferredSize(new Dimension(W, H)); setFocusable(true); addKeyListener(this); addMouseListener(this);
        try { birdImage = ImageIO.read(new File("assets/bird.png")); } catch (Exception e) {
            try { birdImage = ImageIO.read(new File("FlappyBird/assets/bird.png")); } catch (Exception ignored) { }
        }
        loadBestScore();
        timer = new Timer(16, this);
        scoreLabel.setFont(new Font("Monospaced", Font.BOLD, 22)); scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setOpaque(true); scoreLabel.setBackground(new Color(0,0,0,100));
        setLayout(null); scoreLabel.setBounds(20, 15, 150, 38); add(scoreLabel);
    }

    public void startGame() {
        resetGame(); menu = false; started = true; gameOver = false;
        cards.show(root, "game"); timer.start(); audio.playBgm(); requestFocusInWindow();
    }
    private void resetGame() {
        player = new Player(180, 260); pipes.clear(); score = 0; scoreLabel.setText("SKOR: 0");
        pipes.add(new Pipe(520, 210 + random.nextInt(100), 76, 175));
        pipes.add(new Pipe(850, 150 + random.nextInt(160), 76, 175));
        gameOver = false; started = true;
    }
    private void finishGame() {
        if (gameOver) return;
        gameOver = true;
        started = false;
        if (score > bestScore) {
            bestScore = score;
            saveBestScore();
        }
    }

    private void loadBestScore() {
        try {
            if (Files.exists(bestScoreFile)) bestScore = Integer.parseInt(Files.readString(bestScoreFile).trim());
        } catch (Exception e) { bestScore = 0; }
    }

    private void saveBestScore() {
        try {
            Files.createDirectories(bestScoreFile.getParent());
            Files.writeString(bestScoreFile, String.valueOf(bestScore));
        } catch (Exception e) {
            System.err.println("Best score tidak dapat disimpan: " + e.getMessage());
        }
    }

    @Override public void actionPerformed(ActionEvent e) {
        if (started && !gameOver) {
            player.update(); bgOffset = (bgOffset + 2) % W;
            for (Pipe p : pipes) p.move(-3);
            Iterator<Pipe> it = pipes.iterator();
            while (it.hasNext()) if (it.next().getX() + 80 < 0) it.remove();
            if (pipes.isEmpty() || pipes.get(pipes.size()-1).getX() < 560) {
                pipes.add(new Pipe(W + 10, 130 + random.nextInt(190), 76, 175));
            }
            Rectangle bird = player.getBounds();
            if (player.getY() + player.getHeight() >= groundY || player.getY() < -5) finishGame();
            for (Pipe p : pipes) {
                if (bird.intersects(p.getTopBounds()) || bird.intersects(p.getBottomBounds(groundY))) {
                    finishGame();
                    break;
                }
                if (!p.isScored() && p.getX() + p.getWidth() < player.getX()) {
                    p.setScored(true);
                    score++;
                    scoreLabel.setText("SKOR: " + score);
                }
            }
        }
        repaint();
    }

    @Override protected void paintComponent(Graphics raw) {
        super.paintComponent(raw); Graphics2D g = (Graphics2D)raw.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        drawIndonesiaBackground(g);
        if (!menu) {
            for (Pipe p : pipes) drawPipe(g, p);
            drawBird(g);
            if (!started && gameOver) {
                g.setColor(new Color(0,0,0,185)); g.fillRoundRect(235, 175, 430, 250, 24, 24);
                g.setColor(new Color(255, 220, 105));
                g.setFont(new Font("Monospaced", Font.BOLD, 42)); drawCentered(g, "GAME OVER", 235);
                g.setColor(Color.WHITE); g.setFont(new Font("Monospaced", Font.BOLD, 22));
                drawCentered(g, "SKOR AKHIR: " + score, 285);
                g.setColor(new Color(255, 230, 130)); drawCentered(g, "BEST SCORE: " + bestScore, 325);
                g.setColor(Color.WHITE); g.setFont(new Font("Monospaced", Font.BOLD, 16));
                drawCentered(g, "TEKAN R UNTUK MAIN LAGI", 375);
                drawCentered(g, "BEST SCORE TERSIMPAN OTOMATIS", 400);
            } else if (started) {
                g.setColor(new Color(255,255,255,220)); g.setFont(new Font("Monospaced", Font.BOLD, 16));
                g.drawString("SPACE / KLIK = TERBANG", 570, 30);
            }
        }
        g.dispose();
    }

    private void drawCentered(Graphics2D g, String text, int y) { FontMetrics fm=g.getFontMetrics(); g.drawString(text,(W-fm.stringWidth(text))/2,y); }
    private void drawIndonesiaBackground(Graphics2D g) {
        g.setColor(new Color(105,195,204)); g.fillRect(0,0,W,H);
        // Pixel clouds
        g.setColor(new Color(211,241,237));
        int[][] clouds={{70,105},{350,175},{690,90},{790,235}};
        for(int[] c:clouds){g.fillRect(c[0],c[1],50,8);g.fillRect(c[0]+10,c[1]-8,28,8);g.fillRect(c[0]+18,c[1]-16,14,8);}
        // distant Indonesian island hills
        g.setColor(new Color(114,190,151));
        int[] hx={0,0,90,145,210,290,360,430,510,590,670,760,840,900};
        int[] hy={440,420,390,415,385,420,395,430,400,425,390,420,395,415};
        g.fillPolygon(hx,hy,hx.length);
        // Jakarta skyline
        g.setColor(new Color(69,147,151));
        g.fillRect(40,350,70,100); g.fillRect(120,325,55,125); g.fillRect(185,365,65,85);
        g.fillRect(630,345,60,105); g.fillRect(700,315,75,135); g.fillRect(790,360,65,90);
        // Monas silhouette, gold flame
        g.setColor(new Color(238,237,207)); g.fillRect(438,330,24,120); g.fillRect(430,440,40,10);
        g.setColor(new Color(255,197,51)); g.fillPolygon(new int[]{438,450,462},new int[]{330,285,330},3);
        g.setColor(new Color(255,231,123)); g.fillRect(447,298,6,23);
        // Indonesian red-white flag
        g.setColor(new Color(70,73,69)); g.fillRect(285,330,4,105);
        g.setColor(new Color(218,48,48)); g.fillRect(289,333,48,16); g.setColor(Color.WHITE); g.fillRect(289,349,48,16);
        // tiny rice fields / ground
        g.setColor(new Color(240,248,231)); g.fillRect(0,465,W,135);
        g.setColor(new Color(220,238,215)); for(int x=0;x<W;x+=36) g.fillRect(x,480+(x%3)*4,22,5);
        g.setColor(new Color(82,164,93)); g.fillRect(0,groundY,W,H-groundY);
        g.setColor(new Color(52,123,66)); g.fillRect(0,groundY,W,8);
        g.setColor(new Color(245,245,225)); for(int x=0;x<W;x+=45)g.fillRect(x,groundY+25,22,4);
    }
    private void drawPipe(Graphics2D g, Pipe p) {
        int x=p.getX(), w=p.getWidth(), gapY=p.getGapY(), gap=p.getGap();
        drawPipePart(g,x,0,w,gapY,true); drawPipePart(g,x,gapY+gap,w,groundY-(gapY+gap),false);
    }
    private void drawPipePart(Graphics2D g,int x,int y,int w,int h,boolean top) {
        if(h<=0)return; g.setColor(new Color(48,42,55)); g.fillRect(x-4,y,w+8,h);
        g.setColor(new Color(35,155,52)); g.fillRect(x,y+3,w,h-6);
        g.setColor(new Color(71,203,79)); g.fillRect(x+7,y+3,10,Math.max(0,h-6));
        g.setColor(new Color(25,127,39)); g.fillRect(x+25,y+3,20,Math.max(0,h-6));
        g.setColor(new Color(66,188,66)); g.fillRect(x+47,y+3,15,Math.max(0,h-6));
        g.setColor(new Color(179,239,143)); g.fillRect(x+w-8,y+3,4,Math.max(0,h-6));
        if(top) { g.setColor(new Color(39,44,53)); g.fillRect(x-7,y+h-18,w+14,18); g.setColor(new Color(46,175,57)); g.fillRect(x-3,y+h-15,w+6,12); }
        else { g.setColor(new Color(39,44,53)); g.fillRect(x-7,y,w+14,18); g.setColor(new Color(46,175,57)); g.fillRect(x-3,y+3,w+6,12); }
    }
    private void drawBird(Graphics2D g) {
        int x=player.getX(),y=player.getY();
        if(birdImage!=null) g.drawImage(birdImage,x,y,player.getWidth()+12,player.getHeight()+8,null);
        else { g.setColor(Color.YELLOW);g.fillOval(x,y,48,38);g.setColor(Color.RED);g.fillPolygon(new int[]{x+38,x+57,x+38},new int[]{y+17,y+25,y+30},3);g.setColor(Color.WHITE);g.fillOval(x+29,y+4,14,14);g.setColor(Color.BLACK);g.fillRect(x+37,y+9,4,4); }
    }

    @Override public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_R && gameOver) { resetGame(); requestFocusInWindow(); }
        else if ((e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyCode()==KeyEvent.VK_UP) && started && !gameOver) { player.flap(); audio.playJump(); }
        repaint();
    }
    @Override public void mousePressed(MouseEvent e) { if(started && !gameOver) { player.flap(); audio.playJump(); } }
    @Override public void keyReleased(KeyEvent e){} @Override public void keyTyped(KeyEvent e){}
    @Override public void mouseClicked(MouseEvent e){} @Override public void mouseReleased(MouseEvent e){} @Override public void mouseEntered(MouseEvent e){} @Override public void mouseExited(MouseEvent e){}
}
