
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Flappy Bird Nusantara");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            CardLayout cards = new CardLayout();
            JPanel root = new JPanel(cards);
            GamePanel game = new GamePanel(cards, root);
            JPanel menu = new MenuPanel();
            menu.setLayout(new GridBagLayout());
            JPanel box = new JPanel(); box.setOpaque(false); box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
            JLabel title = new JLabel("FLAPPY BIRD", SwingConstants.CENTER);
            title.setFont(new Font("Monospaced", Font.BOLD, 48)); title.setForeground(Color.WHITE); title.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel sub = new JLabel("NUSANTARA EDITION", SwingConstants.CENTER);
            sub.setFont(new Font("Monospaced", Font.BOLD, 20)); sub.setForeground(new Color(255,240,180)); sub.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton play = new JButton("MULAI BERMAIN"); style(play);
            JButton exit = new JButton("KELUAR"); style(exit);
            play.addActionListener(e -> game.startGame());
            exit.addActionListener(e -> System.exit(0));
            box.add(title); box.add(Box.createVerticalStrut(10)); box.add(sub); box.add(Box.createVerticalStrut(35)); box.add(play); box.add(Box.createVerticalStrut(14)); box.add(exit);
            menu.add(box);
            root.add(menu,"menu"); root.add(game,"game");
            frame.setContentPane(root); frame.pack(); frame.setLocationRelativeTo(null); frame.setVisible(true);
        });
    }
    private static void style(JButton button) {
        button.setFont(new Font("Monospaced", Font.BOLD, 18)); button.setForeground(Color.WHITE);
        button.setBackground(new Color(207,55,48)); button.setFocusPainted(false); button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(255,230,160),3),BorderFactory.createEmptyBorder(10,28,10,28)));
        button.setAlignmentX(Component.CENTER_ALIGNMENT); button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    static class MenuPanel extends JPanel {
        @Override protected void paintComponent(Graphics raw) {
            super.paintComponent(raw); Graphics2D g=(Graphics2D)raw;
            g.setColor(new Color(105,195,204));g.fillRect(0,0,900,600);
            g.setColor(new Color(218,242,236));for(int x=60;x<900;x+=210){g.fillRect(x,90,65,9);g.fillRect(x+15,80,35,10);}
            g.setColor(new Color(68,145,148));g.fillRect(50,360,80,120);g.fillRect(140,330,55,150);g.fillRect(700,350,75,130);g.fillRect(790,320,65,160);
            g.setColor(new Color(239,237,208));g.fillRect(430,315,35,165);g.fillRect(424,470,47,12);
            g.setColor(new Color(255,196,48));g.fillPolygon(new int[]{430,447,465},new int[]{315,265,315},3);
            g.setColor(new Color(217,47,47));g.fillRect(255,315,4,100);g.fillRect(259,320,45,15);g.setColor(Color.WHITE);g.fillRect(259,335,45,15);
            g.setColor(new Color(241,248,231));g.fillRect(0,480,900,120);g.setColor(new Color(72,160,82));g.fillRect(0,530,900,70);
            g.setColor(new Color(25,80,70,115));g.fillRoundRect(190,140,520,320,30,30);
            g.setColor(new Color(255,255,255,35));g.fillRoundRect(200,150,500,300,25,25);
            g.setColor(Color.WHITE);g.setFont(new Font("Monospaced",Font.BOLD,15));g.drawString("SPACE / KLIK UNTUK TERBANG",315,570);
        }
    }
}
