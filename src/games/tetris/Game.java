package games.tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {
    private Block activeBlock; // Block that is in motion
    private final HashMap<Rectangle, Color> bottomTiles = new HashMap<>(); // All of the tiles on the bottom

    private Game() {
        setBackground(Color.BLACK);
    }

    private void newActiveBlock() {
        int random = (int)(Math.random() * 7);
        switch (random) {
            case 0:
                activeBlock = new Block(Tetromino.I);
                break;
            case 1:
                activeBlock = new Block(Tetromino.J);
                break;
            case 2:
                activeBlock = new Block(Tetromino.L);
                break;
            case 3:
                activeBlock = new Block(Tetromino.O);
                break;
            case 4:
                activeBlock = new Block(Tetromino.S);
                break;
            case 5:
                activeBlock = new Block(Tetromino.T);
                break;
            case 6:
                activeBlock = new Block(Tetromino.Z);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        activeBlock.paint(g2d);
        for (Rectangle r : bottomTiles.keySet()) g2d.draw(r);
    }

    // KeyListener methods (only keyPressed is needed)
    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        // TODO: Handle inputs
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) { }

    // Main method
    public static void main(String[] args) throws InterruptedException {
        // Initialization
        JFrame f = new JFrame("Tetris");
        Game tetris = new Game();

        // Frame setup
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        f.pack();
        f.add(tetris);
        f.setVisible(true);

        tetris.newActiveBlock();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                tetris.activeBlock.move();
            }
        };
        timer.schedule(task, 0, 500);

        while (true) {
            tetris.repaint();
            Thread.sleep(Constants.FRAME);
        }
    }
}
